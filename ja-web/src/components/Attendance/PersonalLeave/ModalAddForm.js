import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber, Col, Row, DatePicker } from 'antd';
import moment from 'moment';
import { addPersonalLeave } from '../../../services/personalLeave';
import { getById } from '../../../services/staffBaseInfo';

const RangePicker = DatePicker.RangePicker;

class ModalAddForm extends PureComponent {
  state = {};

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.staffId = this.state.staffId;
      fieldsValue.jobNoName = this.state.jobNoName;
      fieldsValue.staffNo = this.state.staffNo;
      fieldsValue.holidayTypeName = this.state.holidayTypeName;
      fieldsValue.baseId = this.state.baseId;
      fieldsValue.baseName = this.state.baseName;
      fieldsValue.deptId = this.state.deptId;
      fieldsValue.agentStaffNo = this.state.agentStaffNo;
      fieldsValue.agentStaffId = this.state.agentStaffId;
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      this.setState({
        staffId: '',
        staffName: '',
        baseId: '',
        deptId: '',
        deptName:'',
        agentStaffName:'',
        hours:'',
      });
      addPersonalLeave(fieldsValue)
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
  handleChangeStaffName = (
    staffId,
    staffName,
  ) => {
    this.setState({
      staffId: staffId,
      staffName: staffName,
    });

    getById(staffId).then(data => {
      if (data.code === 200) {
        this.setState({
          baseId: data.data.baseId,
          staffNo: data.data.staffNo,
          baseName: data.data.transNames.baseId_baseOrDeptName,
          deptId: data.data.deptId,
          deptName: data.data.transNames.deptId_baseOrDeptName,
        });
      }
    });
  };


  /**
   * 下拉选择
   */
  handleChangeagentStaff = (
    staffId,
    staffName,
  ) => {
    this.setState({
      agentStaffId: staffId,
      agentStaffName: staffName,
    });

    getById(staffId).then(data => {
      if (data.code === 200) {
        this.setState({
          agentStaffNo: data.data.staffNo,
        });
      }
    });
  };


  handleChangejobNoName = (
    jobNoName,
  ) => {
    this.setState({
      jobNoName: jobNoName,
    });
  };

  handleChangeHolidayTypeName = (
    holidayTypeName,
  ) => {
    this.setState({
      holidayTypeName: holidayTypeName,
    });
  };

  selectTime = (dates, dateStrings) => {
    console.log('1111--' + dates[0] + '---222--' + dates[1]);
    /*开始时间，结束时间*/
    const hours = this.calculateTime(dates[0], dates[1]);
    this.setState({
      startDate: dateStrings[0],
      endDate: dateStrings[1],
      hours: hours,
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
    const statusType = [{ id: 0, name: '未销假' }, { id: 1, name: '已销假' }];
    return (
      <Modal
        title="添加个人请假"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
        width={1200}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工信息">
                {getFieldDecorator('staffId', {
                  rules: [{ required: true, message: '员工信息' }],
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选">
                    {this.props.staffAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId + d.staffNo + d.staffName}
                        onClick={() =>
                          this.handleChangeStaffName(
                            d.rowId,
                            d.staffName,
                          )
                        }
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
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="姓名">
                {getFieldDecorator('staffName', {
                  initialValue: this.state.staffName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            {/*<Col span={12}>*/}
            {/*<Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">*/}
            {/*{getFieldDecorator('baseName', {*/}
            {/*initialValue: this.state.baseName,*/}
            {/*})(<Input disabled/>)}*/}
            {/*</Form.Item>*/}
            {/*</Col>*/}
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
                {getFieldDecorator('deptName', {
                  initialValue: this.state.deptName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
                {getFieldDecorator('jobNoId')(
                  <Select style={{ width: 295 }}>
                    {this.props.commonAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() =>
                          this.handleChangejobNoName(
                            d.jobNoName,
                          )
                        }
                      >
                        {d.jobNoName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="请假类型">
                {getFieldDecorator('holidayTypeId',{
                  rules: [{ required: true, message: '请假类型为必须' }],
                })(
                  <Select style={{ width: 295 }}>
                    {this.props.holidayTypeList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() =>
                          this.handleChangeHolidayTypeName(
                            d.typeName,
                          )
                        }
                      >
                        {d.typeName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="状态">
                {getFieldDecorator('status', {
                  initialValue: this.state.status,
                  rules: [{ required: true, message: '状态为必须' }],
                })(<Select placeholder="请选择" style={{ width: 295 }}>
                  {statusType.map(d => (
                    <Select.Option key={d.id} value={d.id}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="代理人信息">
                {getFieldDecorator('agentStaffId', {
                  rules: [{ required: true, message: '代理人信息' }],
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选">
                    {this.props.staffAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId + d.staffNo + d.staffName}
                        onClick={() =>
                          this.handleChangeagentStaff(
                            d.rowId,
                            d.staffName,
                          )
                        }
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
                  </Select>,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="代理人姓名">
                {getFieldDecorator('agentStaffName', {
                  initialValue: this.state.agentStaffName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="选择请假时间段">
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
                  />,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="时长">
                {getFieldDecorator('hours', {
                  initialValue: this.state.hours,
                })(<Input placeholder="根据时间段自动计算" disabled />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
