import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, DatePicker, Row, Col, Button, Icon } from 'antd';
import { add } from '../../../services/occInjury';

class ModalAddForm extends PureComponent {
  state = {
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

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.occupationalInjuryStartDate = this.state.occupationalInjuryStartDate;
      fieldsValue.disabilityIdentificationTime = this.state.disabilityIdentificationTime;
      fieldsValue.occupationalInjuryTime = this.state.occupationalInjuryTime;
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
            this.setState({
              occupationalInjuryStartDate: '',
              disabilityIdentificationTime: '',
              occupationalInjuryTime: '',
            });
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          message.error('添加失败！');
        });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.occupationalInjuryStartDate = this.state.occupationalInjuryStartDate;
      fieldsValue.disabilityIdentificationTime = this.state.disabilityIdentificationTime;
      fieldsValue.occupationalInjuryTime = this.state.occupationalInjuryTime;
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            form.resetFields();
            this.props.refreshTable();
            this.setState({
              occupationalInjuryStartDate: '',
              disabilityIdentificationTime: '',
              occupationalInjuryTime: '',
            });
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          message.error('添加失败！');
        });
    });
  };

  handleChangeNum = num => {
    const { form } = this.props;
    let salsry = num.target.value;
    if (salsry !== '') {
      salsry = salsry.replace(/[^\d.]/g, ''); //清除“数字”和“.”以外的字符
      salsry = salsry.replace(/\.{2,}/g, '.'); //只保留第一个. 清除多余的
      salsry = salsry
        .replace('.', '$#$')
        .replace(/\./g, '')
        .replace('$#$', '.');
      salsry = salsry.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
      if (salsry.indexOf('.') < 0 && salsry != '') {
        //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        salsry = parseFloat(salsry);
      }
    }
    form.setFieldsValue({
      beforeInjurySalary: salsry,
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;

    return (
      <Modal
        title="添加工伤信息"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={() => this.props.handleModalVisible(false, 0, -1)}
          >
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={this.handleSure}>
            保存
          </Button>,
          <Button type="primary" onClick={this.handleSureNext}>
            添加下一条<Icon type="right" />
          </Button>,
        ]}
      >
        <Form>
          <Row gutter={24}>
            <Form.Item>
              {getFieldDecorator('staffId', {
                initialValue: this.props.staffId,
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
                {getFieldDecorator('occupationalInjuryStartDate')(
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
                {getFieldDecorator('disabilityIdentificationTime')(
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
                {getFieldDecorator('disabilityLevel')(
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
                {getFieldDecorator('partName')(<Input placeholder="请输入" maxLength="30" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="工伤认定时间">
                {getFieldDecorator('occupationalInjuryTime')(
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
                {getFieldDecorator('nurseLevel')(
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
                {getFieldDecorator('occupationalInjuryNo')(
                  <Input placeholder="请输入" maxLength="50" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                labelCol={{ span: 10 }}
                wrapperCol={{ span: 14 }}
                label="伤前12个月平均月缴费工资"
              >
                {getFieldDecorator('beforeInjurySalary')(
                  <Input
                    placeholder="请输入伤前12个月平均月缴费工资"
                    maxLength="10"
                    onKeyUp={this.handleChangeNum.bind(this)}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 10 }} wrapperCol={{ span: 14 }} label="事故类别">
                {getFieldDecorator('accidentState')(
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

export default Form.create({})(ModalAddForm);
