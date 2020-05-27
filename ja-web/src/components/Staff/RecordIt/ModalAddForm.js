import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Button, Icon } from 'antd';
import { addRecordIt } from '../../../services/recordIt';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addRecordIt(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('添加成功');
          this.props.handleModalVisible(false, 0, 0);
          this.props.refreshTable();
        } else if (data.code === 400) {
          message.error('信息已存在');
        }
      });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      addRecordIt(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('添加成功');
          form.resetFields();
          this.props.refreshTable();
        } else if (data.code === 400) {
          message.error('信息已存在');
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
        title="添加计算机能力"
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
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="证书名称">
            {getFieldDecorator('certificateName', {
              rules: [{ required: true, message: '请输入证书名称' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="计算机等级">
            {getFieldDecorator('computerRank', {
              rules: [{ required: true, message: '请输入计算机等级' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
