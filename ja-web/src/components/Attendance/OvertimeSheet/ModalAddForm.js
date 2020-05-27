import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber, DatePicker } from 'antd';
import moment from 'moment';

import { addOvertimeSheet } from '../../../services/overtimeSheet';
import ModalOrgTreeSelect from '../../Org/Org/ModalOrgTreeSelect';
import { getSonOrgTree } from '../../../services/org';
import { queryBaseInfoForParams } from '../../../services/staffBaseInfo';
import { getByTypeCode } from '../../../services/systemCode';

const RangePicker = DatePicker.RangePicker;

class ModalAddForm extends PureComponent {
  state = {
    treeData: [],
    isDisabled: 'disabled',
    staffAllList: [],
  };

  //选择基地
  handleChange(baseId) {
    getSonOrgTree(baseId).then(data => {
      this.setState({
        baseId: baseId,
        treeData: data.data,
        isDisabled: 'disabled',
        // deptId:'',
      });
    });
    this.child.onChange('');
  }

  //选择部门
  handleSetOrg = deptId => {
    console.log(1234569);
    this.setState({ deptId: deptId, isDisabled: '' });
    if (!!deptId && deptId !== '') {
      this.queryStaffByDept();
    }

    const { form } = this.props;
    form.setFieldsValue({
      deptId: deptId,
    });
  };

  //映射部门下拉树
  onRef = ref => {
    this.child = ref;
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.staffId = this.state.staffId;
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;

      addOvertimeSheet(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };

  /**
   * 下拉选择
   */
  handleChangeStaffName = staffId => {
    this.setState({
      staffId: staffId,
    });
  };

  queryStaffByDept() {
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content && !!content.data) {
        {
          content.data.map(
            d =>
              d.name === '在职' &&
              this.setState({
                jobStatus: d.rowId,
              })
          );
        }
        const jobStatus = this.state.jobStatus;
        queryBaseInfoForParams({ jobStatus: jobStatus, deptId: this.state.deptId }).then(data => {
          if (!!data && !!data.data) {
            this.setState({
              staffAllList: data.data,
            });
          }
        });
      }
    });
  }

  selectTime = (dates, dateStrings) => {
    console.log('1111--' + dates[0] + '---222--' + dates[1]);
    /*开始时间，结束时间*/
    const hours = this.calculateTime(dates[0], dates[1]);
    this.setState({
      startDate: dateStrings[0],
      endDate: dateStrings[1],
      overtimeTime: hours,
    });
  };

  /*计算两个时间的时间差*/
  calculateTime = (faultDate, completeTime) => {
    const usedTime = completeTime - faultDate; //两个时间戳相差的毫秒数
    //计算出小时数
    const hours = Math.floor((usedTime / (3600 * 1000)) * 100) / 100;
    console.log('两个时间相差 ==>> ' + hours + '时');
    return hours;
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加加班单"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseId', {
              rules: [{ required: true, message: '基地为必须' }],
            })(
              <Select style={{ width: 295 }} onChange={this.handleChange.bind(this)}>
                {this.props.orgBaseList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.baseOrDeptName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
            {getFieldDecorator('deptId', {
              rules: [{ required: true, message: '请选择部门' }],
            })(
              <ModalOrgTreeSelect
                treeData={this.state.treeData}
                handleSetOrg={this.handleSetOrg.bind(this)}
                onRef={this.onRef}
                deptId={''}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="选择员工">
            {getFieldDecorator('staffId', {
              rules: [{ required: true, message: '员工信息为必须' }],
            })(
              <Select
                showSearch
                style={{ width: 350 }}
                placeholder="可按工号姓名筛选"
                disabled={this.state.isDisabled}
              >
                {this.state.staffAllList.map(d => (
                  <Select.Option
                    key={d.rowId}
                    value={d.rowId + d.staffNo + d.staffName}
                    onClick={() => this.handleChangeStaffName(d.rowId)}
                  >
                    {d.staffNo +
                      '           ' +
                      d.staffName +
                      '           ' +
                      (d.transNames === undefined ||
                      d.transNames.baseId_baseOrDeptName === undefined
                        ? ''
                        : ' ' + d.transNames.baseId_baseOrDeptName)}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
            {getFieldDecorator('jobNoId')(
              <Select style={{ width: 295 }}>
                {this.props.commonAllList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.jobNoName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="选择时间段">
            {getFieldDecorator('startDate',{
              rules: [{ required: true, message: '时间段为必须' }],
            })(
              <RangePicker
                ranges={{
                  Today: [moment(), moment()],
                  'This Month': [moment(), moment().endOf('month')],
                }}
                showTime
                format="YYYY/MM/DD HH:mm"
                onChange={this.selectTime}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="加班时数">
            {getFieldDecorator('overtimeTime', {
              initialValue: this.state.overtimeTime,
            })(<Input placeholder="根据时间段自动计算" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="加班原因">
            {getFieldDecorator('reason')(<Input placeholder="请输入" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
