import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { addAllowance } from '../../../services/eltAllowance';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addAllowance(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('岗位津贴已存在');
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
        title="添加岗位津贴"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
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
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="津贴类别">
            {getFieldDecorator('allowanceCategory', {
              rules: [{ required: true, message: '津贴类别为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="金额">
            {getFieldDecorator('money', {
              rules: [{ required: true, message: '金额为必须' }],
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remarks')(<Input placeholder="请输入" maxLength="100" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
