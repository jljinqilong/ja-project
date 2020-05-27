import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Form, DatePicker, Input, Select, Row, Col, InputNumber } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';

const FormItem = Form.Item;

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class AddSaleContractInvoiceForm extends PureComponent {
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
      saleContract: { invoiceTypeList },
    } = this.props;

    return (
      <Form>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="开票日期" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('kprq', {
                rules: [
                  {
                    required: true,
                    message: '请输入开票日期',
                  },
                ],
              })(<DatePicker placeholder="请选择" style={{ width: '100%' }} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="票据内容" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('invoiceContext', {
                rules: [
                  {
                    required: true,
                    message: '请输入票据内容',
                  },
                ],
              })(<Input placeholder="请输入" maxLength={200} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="开票金额" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('invoiceAmount', {
                rules: [
                  {
                    required: true,
                    message: '请输入开票金额',
                  },
                ],
              })(
                <InputNumber
                  placeholder="请输入"
                  min={0}
                  max={999999999.99}
                  maxLength={12}
                  precision={2}
                  style={{ width: '100%' }}
                />
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="票据类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('invoiceTypeId', {
                rules: [
                  {
                    required: true,
                    message: '请输入票据类型',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {invoiceTypeList.map(d => (
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
            <FormItem label="发票号码" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('invoiceNo', {
                rules: [
                  {
                    required: true,
                    message: '请输入发票号码',
                  },
                ],
              })(<Input placeholder="请输入" maxLength={10} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="经手人" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('invoiceUserName', {
                rules: [
                  {
                    required: true,
                    message: '请输入经手人',
                  },
                ],
              })(<Input placeholder="请输入" maxLength={20} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="备注" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('remark')(<Input placeholder="请输入" maxLength={100} />)}
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
        title="新增发票"
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
