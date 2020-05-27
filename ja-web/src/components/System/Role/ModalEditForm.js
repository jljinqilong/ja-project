import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message } from 'antd';
import { editRole } from '../../../services/systemRole';

class ModalEditForm extends PureComponent {
  state = {
    name: '',
    desc: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  // componentWillReceiveProps(nextProps) {
  //   if (this.props.currentEditRoleId !== nextProps.currentEditRoleId && nextProps.currentEditRoleId > 0) {
  //     getById(nextProps.currentEditRoleId)
  //       .then(data => {
  //         this.setState({
  //           rowId: data.data.rowId,
  //           name: data.data.name,
  //           desc: data.data.desc,
  //         });
  //       });
  //   }
  // }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form, detailData } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.rowId = detailData.rowId;
      //业务逻辑写在这里
      editRole(fieldsValue).then(data => {
        if (data.code === 200) {
          this.setState({
            name: fieldsValue.name,
            desc: fieldsValue.desc,
          });
          form.resetFields();
          message.success('编辑成功');
          this.props.handleModalVisible(false, 1, detailData.rowId);
          this.props.refreshTable();
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
        title="编辑用户"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.currentEditRoleId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="角色名">
            {getFieldDecorator('name', {
              initialValue: detailData.name,
              rules: [{ required: true, message: '请输入角色名' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
            {getFieldDecorator('desc', {
              initialValue: detailData.desc,
            })(<Input.TextArea cols={4} placeholder="请输入" maxLength={50} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
