import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, DatePicker } from 'antd';
import { getById, update } from '../../../services/disability';
import moment from 'moment';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
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
            disabilityType: data.data.disabilityType,
            disabilityRank: data.data.disabilityRank,
            disabilityNo: data.data.disabilityNo,
            remark: data.data.remark,
            disabilityCardDate: data.data.disabilityCardDate,
            validityCertificateStart: data.data.validityCertificateStart,
            validityCertificateEnd: data.data.validityCertificateEnd,
          });
        })
        .catch(() => {
          message.error('查询残疾信息失败');
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
      fieldsValue.rowId = this.props.currentEditUserId;
      (fieldsValue.disabilityCardDate = this.state.disabilityCardDate),
        (fieldsValue.validityCertificateStart = this.state.validityCertificateStart),
        (fieldsValue.validityCertificateEnd = this.state.validityCertificateEnd),
        //form.resetFields();
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
        title="修改残疾信息"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <Form.Item>
            {getFieldDecorator('staffId', {
              initialValue: this.state.staffId,
            })(<Input type="hidden" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾类别">
            {getFieldDecorator('disabilityType', {
              initialValue: this.state.disabilityType,
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
              initialValue: this.state.disabilityRank,
              rules: [{ required: true, message: '请输入残疾等级' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾证号">
            {getFieldDecorator('disabilityNo', {
              initialValue: this.state.disabilityNo,
              rules: [{ required: true, message: '请输入残疾证号' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 8 }} wrapperCol={{ span: 15 }} label="残疾证发放日期">
            {getFieldDecorator('disabilityCardDate', {
              initialValue: moment(this.state.disabilityCardDate),
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
              initialValue: moment(this.state.validityCertificatetart),
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
              initialValue: moment(this.state.validityCertificateEnd),
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
            {getFieldDecorator('remark', {
              initialValue: this.state.remark,
            })(<Input placeholder="请输入" maxLength="80" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
