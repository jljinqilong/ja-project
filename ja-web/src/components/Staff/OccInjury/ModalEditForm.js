import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, DatePicker, Row, Col } from 'antd';
import moment from 'moment';
import { getById, update } from '../../../services/occInjury';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
    occupationalInjuryStartDate: '',
    disabilityIdentificationTime: '',
    occupationalInjuryTime: '',
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeOccInjuryStartDate = (date, dateString) => {
    this.setState({
      occupationalInjuryStartDate: dateString,
    });
  };
  handleDatePickerChangeIdentificationTime = (date, dateString) => {
    this.setState({
      disabilityIdentificationTime: dateString,
    });
  };
  handleDatePickerChangeValidityCertificate = (date, dateString) => {
    this.setState({
      occupationalInjuryTime: dateString,
    });
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentEditUserId != nextProps.currentEditUserId &&
      nextProps.currentEditUserId > 0
    ) {
      getById(nextProps.currentEditUserId)
        .then(data => {
          this.setState({
            rowId: data.data.rowId,
            staffId: data.data.staffId,
            occupationalInjuryTyle: data.data.occupationalInjuryTyle,
            disabilityLevel: data.data.disabilityLevel,
            partName: data.data.partName,
            nurseLevel: data.data.nurseLevel,
            occupationalInjuryNo: data.data.occupationalInjuryNo,
            beforeInjurySalary: data.data.beforeInjurySalary,
            accidentState: data.data.accidentState,
            occupationalInjuryStartDate: data.data.occupationalInjuryStartDate,
            disabilityIdentificationTime: data.data.disabilityIdentificationTime,
            occupationalInjuryTime: data.data.occupationalInjuryTime,
          });
        })
        .catch(() => {
          message.error('查询工伤信息失败');
        });
    }
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      (fieldsValue.occupationalInjuryStartDate = this.state.occupationalInjuryStartDate),
        (fieldsValue.disabilityIdentificationTime = this.state.disabilityIdentificationTime),
        (fieldsValue.occupationalInjuryTime = this.state.occupationalInjuryTime),
        //业务逻辑写在这里
        update(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('编辑成功');
              this.props.handleModalVisible(false, 1, -1);
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

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="修改工伤信息"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <Row gutter={24}>
            <Form.Item>
              {getFieldDecorator('staffId', {
                initialValue: this.state.staffId,
              })(<Input type="hidden" disabled />)}
            </Form.Item>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="工号">
                <span>{this.props.staffNo}</span>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="工伤类别">
                {getFieldDecorator('occupationalInjuryTyle', {
                  initialValue: this.state.occupationalInjuryTyle,
                  rules: [{ required: true, message: '请输入工伤类别' }],
                })(
                  <Select style={{ width: 330 }}>
                    {this.props.occupationalInjuryTyleCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="工伤发生日期">
                {getFieldDecorator('occupationalInjuryStartDate', {
                  initialValue: moment(this.state.occupationalInjuryStartDate),
                })(
                  <DatePicker
                    placeholder="请选择日期"
                    onChange={this.handleDatePickerChangeOccInjuryStartDate}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="伤残程度鉴定日期">
                {getFieldDecorator('disabilityIdentificationTime', {
                  initialValue: moment(this.state.disabilityIdentificationTime),
                })(
                  <DatePicker
                    placeholder="请选择日期"
                    onChange={this.handleDatePickerChangeIdentificationTime}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="伤残等级">
                {getFieldDecorator('disabilityLevel', {
                  initialValue: this.state.disabilityLevel,
                })(
                  <Select style={{ width: 330 }}>
                    {this.props.disabilityLevelCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="认定部位或名称">
                {getFieldDecorator('partName', {
                  initialValue: this.state.partName,
                })(<Input placeholder="请输入" maxLength="30" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="工伤认定时间">
                {getFieldDecorator('occupationalInjuryTime', {
                  initialValue: moment(this.state.occupationalInjuryTime),
                })(
                  <DatePicker
                    placeholder="请选择日期"
                    onChange={this.handleDatePickerChangeValidityCertificate}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="护理程度级别">
                {getFieldDecorator('nurseLevel', {
                  initialValue: this.state.nurseLevel,
                })(
                  <Select style={{ width: 330 }}>
                    {this.props.nurseLevelCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="工伤号">
                {getFieldDecorator('occupationalInjuryNo', {
                  initialValue: this.state.occupationalInjuryNo,
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                labelCol={{ span: 10 }}
                wrapperCol={{ span: 14 }}
                label="伤前12个月平均月缴费工资"
              >
                {getFieldDecorator('beforeInjurySalary', {
                  initialValue: this.state.beforeInjurySalary,
                })(<Input placeholder="请输入伤前12个月平均月缴费工资" maxLength="10" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="事故类别">
                {getFieldDecorator('accidentState', {
                  initialValue: this.state.accidentState,
                })(
                  <Select style={{ width: 330 }}>
                    {this.props.accidentStateCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
