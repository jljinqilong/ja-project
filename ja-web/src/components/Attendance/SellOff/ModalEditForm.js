import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, InputNumber, DatePicker, Row, Select, Col } from 'antd';
import moment from 'moment';
import { editPersonalLeave } from '../../../services/personalLeave';
import { getById } from '../../../services/staffBaseInfo';

const RangePicker = DatePicker.RangePicker;

class ModalEditForm extends PureComponent {
  state = {
    baseId: '',
    hours: this.props.detailData.hours,
    agentStaffName:this.props.detailData.agentStaffName
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  // componentWillReceiveProps(nextProps) {
  //   if (
  //     this.props.currentEditUserId !== nextProps.currentEditUserId &&
  //     nextProps.currentEditUserId > 0
  //   ) {
  //     getById(nextProps.currentEditUserId)
  //       .then(data => {
  //         this.setState({
  //           rowId: data.data.rowId,
  //           base: data.data.base,
  //           ruleName: data.data.ruleName,
  //           socialInsuranceBase: data.data.socialInsuranceBase,
  //           personalProportion: data.data.personalProportion,
  //           personalAmount: data.data.personalAmount,
  //           companyRatio: data.data.companyRatio,
  //           companyAmount: data.data.companyAmount,
  //           personalSupplementaryRatio: data.data.personalSupplementaryRatio,
  //           personalSupplementaryAmount: data.data.personalSupplementaryAmount,
  //           companySupplementaryProportion: data.data.companySupplementaryProportion,
  //           companySupplementaryAmount: data.data.companySupplementaryAmount,
  //           remarks: data.data.remarks,
  //         });
  //       })
  //       .catch(() => {
  //         message.error('查询公积金信息失败');
  //       });
  //   }
  // }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      console.log('this.state.startDate===>>' + this.state.startDate);
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      fieldsValue.holidayTypeName = this.state.holidayTypeName;
      fieldsValue.agentStaffNo = this.state.agentStaffNo;
      fieldsValue.agentStaffId = this.state.agentStaffId;
      fieldsValue.realStartDate= this.state.realStartDate;
      fieldsValue.realEndDate = this.state.realEndDate;
      fieldsValue.status = '1';

      //业务逻辑写在这里
      editPersonalLeave(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, this.props.currentEditUserId);
            this.props.refreshTable();
          } else {
            message.error('编辑失败：请稍后再试！');
          }
        })
        .catch(() => {
          message.error('编辑失败：请联系管理员!');
        });
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

  selectTime2 = (dates, dateStrings) => {
    console.log('1111--' + dates[0] + '---222--' + dates[1]);
    /*开始时间，结束时间*/
    const hours = this.calculateTime(dates[0], dates[1]);
    this.setState({
      realStartDate: dateStrings[0],
      realEndDate: dateStrings[1],
      realHours: hours,
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

  handleChangeHolidayTypeName = (
    holidayTypeName,
  ) => {
    this.setState({
      holidayTypeName: holidayTypeName,
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

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const statusType = [{ id: 0, name: '未销假' }, { id: 1, name: '已销假' }];
    return (
      <Modal
        title="编辑个人请假"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
        width={1200}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工信息">
                {getFieldDecorator('staffId', {
                  rules: [{ required: true, message: '员工信息' }],
                  initialValue: this.props.detailData.staffId,
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选" disabled>
                    {this.props.staffAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
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
                  initialValue: this.props.detailData.staffName,
                })(<Input disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
                {getFieldDecorator('deptName', {
                  initialValue: this.props.detailData.deptName,
                })(<Input placeholder="请输入" disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 13 }} label="班次">
                {getFieldDecorator('jobNoName', {
                  initialValue: this.props.detailData.jobNoName,
                })(<Input placeholder="请输入" disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="请假类型">
                {getFieldDecorator('holidayTypeId', {
                  initialValue: this.props.detailData.holidayTypeId,
                })(
                  <Select style={{ width: 295 }} disabled>
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
                  initialValue: this.props.detailData.status,

                })(<Select placeholder="请选择" style={{ width: 295 }} disabled>
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
                  initialValue: this.props.detailData.agentStaffId + this.props.detailData.agentStaffNo + this.props.detailData.agentStaffName,
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名筛选" disabled>
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
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="请假时间段">
                {getFieldDecorator('startDate', {
                  rules: [{ required: true, message: '请假时间段' }],
                  initialValue: [
                    moment(this.props.detailData.startDate),
                    moment(this.props.detailData.endDate),
                  ],
                })(
                  <RangePicker
                    disabled
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
                })(<Input placeholder="请输入" disabled/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="实际请假时间段">
                {getFieldDecorator('realStartDate',{
                  rules: [{ required: true, message: '实际请假时间段为必须' }],
                })(
                  <RangePicker
                    ranges={{
                      Today: [moment(), moment()],
                      'This Month': [moment(), moment().endOf('month')],
                    }}
                    showTime
                    format="YYYY/MM/DD HH:mm"
                    onChange={this.selectTime2}
                  />,
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="实际时长">
                {getFieldDecorator('realHours', {
                  initialValue: this.state.realHours,
                })(<Input placeholder="根据实际请假时间段自动计算" disabled />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
