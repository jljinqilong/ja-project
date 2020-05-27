import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, } from 'antd';
import { changePassword } from '../../../services/systemUser';

class ModalChangePasswordForm extends PureComponent {
  state = {
    userName: '',
    oldPassword: '',
    newPassword: '',
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      changePassword(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('修改密码成功');
            form.resetFields();
            this.props.changePasswordView(false);
          }
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="修改密码"
        visible={this.props.modalVisiblePassword}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.changePasswordView(false)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="用户名">
            {getFieldDecorator('userName', {
              initialValue: this.state.userName,
              rules: [{ required: true, message: '请输入用户名' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="原密码">
            {getFieldDecorator('oldPassword', {
              initialValue: this.state.oldPassword,
              rules: [{ required: true, message: '请输入原密码' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="新密码">
            {getFieldDecorator('newPassword', {
              initialValue: this.state.newPassword,
              rules: [{ required: true, message: '请输入新密码' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalChangePasswordForm);
