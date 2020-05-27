import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Form, DatePicker, InputNumber, Input, Select, Row, Col } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';

const FormItem = Form.Item;

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class AddSaleContractPaymentForm extends PureComponent {
  clear = () => {
    const { form } = this.props;
    form.resetFields();
  };

  handleOK = () => {
    const { form, handleAdd } = this.props;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const params = {
        ...fieldsValue,
      };

      handleAdd(params);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.clear();
    handleModalVisible();
  };

  renderBaseInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      saleContract: { paymentTypeList, payTypeList },
      planUnPaymentAmount,
    } = this.props;

    const amountPlaceholder = `最大可回款金额${planUnPaymentAmount}`;

    return (
      <Form>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="回款日期" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('createdTime', {
                rules: [
                  {
                    required: true,
                    message: '请输入回款日期',
                  },
                ],
              })(<DatePicker placeholder="请选择" style={{ width: '100%' }} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="回款金额" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('amount', {
                rules: [
                  {
                    required: true,
                    message: '请输入回款金额',
                  },
                ],
              })(<InputNumber placeholder={amountPlaceholder} min={1} style={{ width: '100%' }} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="付款方式" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payMethod', {
                rules: [
                  {
                    required: true,
                    message: '请选择付款方式',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {paymentTypeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="回款类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('typeId', {
                rules: [
                  {
                    required: true,
                    message: '请选择回款类型',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {payTypeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="付款人" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payeeName', {
                rules: [
                  {
                    required: true,
                    message: '请输入付款人',
                  },
                ],
              })(<Input placeholder="请输入" maxLength={20} />)}
            </FormItem>
          </Col>
        </Row>
      </Form>
    );
  }

  render() {
    const { modalVisible } = this.props;

    return (
      <SimpleModal
        title="新增回款记录"
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        {this.renderBaseInfo()}
      </SimpleModal>
    );
  }
}
