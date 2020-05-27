import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { addAccumulationFund } from '../../../services/eltAccumulationFund';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addAccumulationFund(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
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
        title="添加公积金"
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
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.baseOrDeptName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规则名称">
            {getFieldDecorator('ruleName', {
              rules: [{ required: true, message: '规则名称为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="缴费基数">
            {getFieldDecorator('socialInsuranceBase')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人比例">
            {getFieldDecorator('personalProportion')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人金额">
            {getFieldDecorator('personalAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司比例">
            {getFieldDecorator('companyRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司金额">
            {getFieldDecorator('companyAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人补充比例">
            {getFieldDecorator('personalSupplementaryRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人补充金额">
            {getFieldDecorator('personalSupplementaryAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司补充比例">
            {getFieldDecorator('companySupplementaryProportion')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司补充金额">
            {getFieldDecorator('companySupplementaryAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
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
