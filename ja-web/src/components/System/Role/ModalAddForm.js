import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message } from 'antd';
import { addRole } from '../../../services/systemRole';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      addRole(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('添加成功');
          form.resetFields();
          this.props.handleModalVisible(false, 0, 0);
          this.props.refreshTable();
        } else if (data.code === 400) {
          message.error('添加失败');
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
        title="添加角色"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="角色名">
            {getFieldDecorator('name', {
              rules: [{ required: true, message: '请输入角色名' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
            {getFieldDecorator('desc')(
              <Input.TextArea cols={4} placeholder="请输入" maxLength={50} />
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
