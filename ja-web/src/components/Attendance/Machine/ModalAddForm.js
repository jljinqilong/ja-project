import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { addMachine } from '../../../services/machine';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addMachine(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('已存在');
          }
        })
        .catch(e => {
          console.log(e);
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
        title="添加考勤机"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="考勤机名称">
            {getFieldDecorator('machineName', {
              rules: [{ required: true, message: '考勤机名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseId', {
              rules: [{ required: true, message: '基地为必须' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.orgBaseList.map(d => (
                  <Select.Option key={d.rowId}>{d.baseOrDeptName}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="厂别">
            {getFieldDecorator('factoryId')(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.factoryIds.map(d => (
                  <Select.Option key={d.code}>{d.name}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="序列号">
            {getFieldDecorator('seq')(<Input placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最早提前签到(分钟)">
            {getFieldDecorator('earliestSignIn')(
              <InputNumber min={0} maxLength="12" precision="int" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最晚推迟签到(分钟)">
            {getFieldDecorator('latestSignIn')(
              <InputNumber min={0} maxLength="12" precision="int" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最早提前签退(分钟)">
            {getFieldDecorator('earliestSignOff')(
              <InputNumber min={0} maxLength="12" precision="int" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最晚推迟签退(分钟)">
            {getFieldDecorator('latestSignOff')(
              <InputNumber min={0} maxLength="12" precision="int" />
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
