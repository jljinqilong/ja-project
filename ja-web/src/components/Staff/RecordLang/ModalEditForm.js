import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message } from 'antd';
import { getById, update } from '../../../services/recordLang';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
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
            certificateName: data.data.certificateName,
            language: data.data.language,
            rank: data.data.rank,
            grade: data.data.grade,
          });
        })
        .catch(() => {
          message.error('查询证书信息失败');
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
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
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
        title="修改合同"
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
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="证书名称">
            {getFieldDecorator('certificateName', {
              initialValue: this.state.certificateName,
              rules: [{ required: true, message: '请输入证书名称' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="语种">
            {getFieldDecorator('language', {
              initialValue: this.state.language,
              rules: [{ required: true, message: '请输入语种' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="等级程度">
            {getFieldDecorator('rank', {
              initialValue: this.state.rank,
              rules: [{ required: true, message: '请输入等级程度' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="考试分数">
            {getFieldDecorator('grade', {
              initialValue: this.state.grade,
            })(<Input placeholder="请输入" maxLength="10" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
