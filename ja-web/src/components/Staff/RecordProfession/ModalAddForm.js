import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, DatePicker, Upload, Button, Icon } from 'antd';
import { add } from '../../../services/recordProfession';

class ModalAddForm extends PureComponent {
  state = {
    qualifyTime: '',
    startValue: '',
    endValue: '',
    certificateStartDate:'',
    certificateEndDate:'',
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeQualifyTime = (date, dateString) => {
    this.setState({
      qualifyTime: dateString,
    });
  };

  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      certificateStartDate: dateString,
    });
    this.onChange('startValue', date);
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      certificateEndDate: dateString,
    });
    this.onChange('endValue', date);
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.qualifyTime = this.state.qualifyTime,
      fieldsValue.certificateEndDate = this.state.certificateEndDate,
      fieldsValue.certificateStartDate = this.state.certificateStartDate,
        add(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('添加成功');
              this.props.handleModalVisible(false, 0, 0);
              this.props.refreshTable();
              this.setState({
                qualifyTime: '',
                startValue: '',
                endValue: '',
              });
            } else if (data.code === 400) {
              message.error('信息已存在');
            }
          })
          .catch(e => {
            console.log(e);
            message.error('添加失败！');
          });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      (fieldsValue.qualifyTime = this.state.qualifyTime),
        add(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('添加成功');
              form.resetFields();
              this.props.refreshTable();
              this.setState({
                qualifyTime: '',
              });
            } else if (data.code === 400) {
              message.error('信息已存在');
            }
          })
          .catch(e => {
            console.log(e);
            message.error('添加失败！');
          });
    });
  };

  disabledStartDate = startValue => {
    const endValue = this.state.endValue;
    if (!startValue || !endValue) {
      return false;
    }
    return startValue.valueOf() > endValue.valueOf();
  };

  disabledEndDate = endValue => {
    const startValue = this.state.startValue;
    if (!endValue || !startValue) {
      return false;
    }
    return endValue.valueOf() <= startValue.valueOf();
  };

  onChange = (field, value) => {
    this.setState({
      [field]: value,
    });
  };

  render() {
    const { startValue, endValue } = this.state;
    const {
      form: { getFieldDecorator },
    } = this.props;

    return (
      <Modal
        title="添加专业技术"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
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
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="证书名称">
            {getFieldDecorator('certificateName', {
              rules: [{ required: true, message: '请输入证书名称' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="职称级别">
            {getFieldDecorator('professionalTitleRank', {
              rules: [{ required: true, message: '职称级别' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="取得资格时间">
            {getFieldDecorator('qualifyTime')(
              <DatePicker onChange={this.handleDatePickerChangeQualifyTime} format="YYYY-MM-DD" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="证书发放日期">
            {getFieldDecorator('certificateStartDate', {
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeStartDate}
                format="YYYY-MM-DD"
                setFieldsValue={startValue}
                disabledDate={this.disabledStartDate}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="证书有效日期">
            {getFieldDecorator('certificateEndDate', {
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeEndDate}
                format="YYYY-MM-DD"
                setFieldsValue={endValue}
                disabledDate={this.disabledEndDate}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="发放机构">
            {getFieldDecorator('grantOrg', {
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="发放级别">
            {getFieldDecorator('grantLevel', {
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remark')(<Input placeholder="请输入" maxLength="80" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
