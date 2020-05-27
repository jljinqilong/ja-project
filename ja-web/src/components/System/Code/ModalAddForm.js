import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { add } from '../../../services/systemCode';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, -1);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('添加失败');
          }
        })
        .catch(e => {
          message.error('添加异常');
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加编码"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="类型">
            {getFieldDecorator('typeId', {
              rules: [{ required: true, message: '请选择...' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.codeTypeAllList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.name}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="编码">
            {getFieldDecorator('code', {
              rules: [{ required: true, message: '请输入编码...' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="名称">
            {getFieldDecorator('name', {
              rules: [{ required: true, message: '请输入名称...' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="序号">
            {getFieldDecorator('orderNo', {
              rules: [{ required: true, message: '请输入序号...' }],
            })(<InputNumber min={0} maxLength={5} />)}
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
