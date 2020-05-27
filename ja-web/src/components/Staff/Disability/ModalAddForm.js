import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, DatePicker, Upload, Button, Icon } from 'antd';
import { add } from '../../../services/disability';

class ModalAddForm extends PureComponent {
  state = {
    disabilityCardDate: '',
    validityCertificateStart: '',
    validityCertificateEnd: '',
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeDisabilityCardDate = (date, dateString) => {
    this.setState({
      disabilityCardDate: dateString,
    });
  };
  handleDatePickerChangeValidityCertificateStart = (date, dateString) => {
    this.setState({
      validityCertificateStart: dateString,
    });
  };
  handleDatePickerChangeValidityCertificateEnd = (date, dateString) => {
    this.setState({
      validityCertificateEnd: dateString,
    });
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      (fieldsValue.disabilityCardDate = this.state.disabilityCardDate),
        (fieldsValue.validityCertificateStart = this.state.validityCertificateStart),
        (fieldsValue.validityCertificateEnd = this.state.validityCertificateEnd),
        add(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('添加成功');
              this.props.handleModalVisible(false, 0, 0);
              this.props.refreshTable();
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
      (fieldsValue.disabilityCardDate = this.state.disabilityCardDate),
        (fieldsValue.validityCertificateStart = this.state.validityCertificateStart),
        (fieldsValue.validityCertificateEnd = this.state.validityCertificateEnd),
        add(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('添加成功');
              form.resetFields();
              this.props.refreshTable();
            } else if (data.code === 400) {
              message.error('信息已存在');
            }
          })
          .catch(e => {
            message.error('添加失败！');
          });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;

    return (
      <Modal
        title="添加残疾信息"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
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
          <Form.Item>
            {getFieldDecorator('staffId', {
              initialValue: this.props.staffId,
            })(<Input type="hidden" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾类别">
            {getFieldDecorator('disabilityType', {
              rules: [{ required: true, message: '请输入残疾类别' }],
            })(
              <Select style={{ width: 295 }}>
                {this.props.disabilityTypeCode.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.name}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾等级">
            {getFieldDecorator('disabilityRank', {
              rules: [{ required: true, message: '请输入残疾等级' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾证号">
            {getFieldDecorator('disabilityNo', {
              rules: [{ required: true, message: '请输入残疾证号' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾证发放日期">
            {getFieldDecorator('disabilityCardDate', {
              rules: [{ required: true, message: '请选择日期' }],
            })(
              <DatePicker
                placeholder="请选择日期"
                onChange={this.handleDatePickerChangeDisabilityCardDate}
                format="YYYY-MM-DD"
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="证件有效期开始时间">
            {getFieldDecorator('validityCertificateStart', {
              rules: [{ required: true, message: '请选择证件有效期开始时间' }],
            })(
              <DatePicker
                placeholder="请选择日期"
                onChange={this.handleDatePickerChangeValidityCertificateStart}
                format="YYYY-MM-DD"
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="证件有效期结束时间">
            {getFieldDecorator('validityCertificateEnd', {
              rules: [{ required: true, message: '请选择证件有效期结束时间' }],
            })(
              <DatePicker
                placeholder="请选择日期"
                onChange={this.handleDatePickerChangeValidityCertificateEnd}
                format="YYYY-MM-DD"
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remark')(<Input placeholder="请输入" maxLength="80" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
