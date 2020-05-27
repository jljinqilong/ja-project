import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message } from 'antd';
import { getById, edit } from '../../../services/systemCodeType';

class ModalEditForm extends PureComponent {
  state = {
    rowId: -1,
    name: '',
    code: '',
    desc: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (this.props.currentEditId !== nextProps.currentEditId && nextProps.currentEditId > 0) {
      getById(nextProps.currentEditId)
        .then(data => {
          this.setState({
            rowId: data.data.rowId,
            name: data.data.name,
            code: data.data.code,
            desc: data.data.desc,
          });
        });
    }
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form, detailData } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = detailData.rowId;
      //业务逻辑写在这里
      edit(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, detailData.rowId);
            this.props.refreshTable();
          } else {
            message.error('编辑失败：请稍后再试！');
          }
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator }, detailData
    } = this.props;
    return (
      <Modal
        title="编辑编码类型"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="编码">
            <Input defaultValue={detailData.code} disabled />
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="名称">
            {getFieldDecorator('name', {
              initialValue: detailData.name,
              rules: [{ required: true, message: '请输入名称' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
            {getFieldDecorator('desc', {
              initialValue: detailData.desc,
            })(<Input placeholder="请输入" maxLength={100} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
