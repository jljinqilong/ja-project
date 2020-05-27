import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message } from 'antd';
import { addPositionType } from '../../../services/positionType';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addPositionType(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('职衔类别已存在');
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
        title="添加职衔类别"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔类别名称">
            {getFieldDecorator('typeName', {
              rules: [{ required: true, message: '职衔类别名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔类别描述">
            {getFieldDecorator('typeDesc', {
              rules: [{ required: true, message: '职衔类别描述为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
