import React, { PureComponent } from 'react';
import { Form, DatePicker, Select, InputNumber, Input, Row, Col, message } from 'antd';
import { connect } from 'dva/index';
import moment from 'moment';
import SimpleModal from '../../Modal/SimpleModal';
import { getPaymentById } from '../../../services/saleContract';

const FormItem = Form.Item;

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class EditSaleContractPaymentForm extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      detail: {},
      backup: {},
    };
  }

  componentDidMount() {
    const { currentEditId: currentEditPaymentPlanId } = this.props;
    this.get(currentEditPaymentPlanId);
  }

  get = rowId => {
    if (rowId) {
      getPaymentById(rowId)
        .then(resp => resp.data)
        .then(data => {
          this.setState({
            detail: data,
            backup: { ...data },
          });
        })
        .catch(() => {
          message.error('查询回款记录失败');
        });
    }
  };

  clear = () => {
    const { form } = this.props;
    form.resetFields();
  };

  reset = () => {
    const { backup } = this.state;
    const { form } = this.props;
    form.resetFields();
    this.setState({ detail: { ...backup } });
  };

  handleOK = () => {
    const { form, handleEdit } = this.props;
    const { detail } = this.state;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const params = {
        rowId: detail.rowId,
        payPlanId: detail.payPlanId,
        saleContractId: detail.saleContractId,
        ...fieldsValue,
      };

      handleEdit(params);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.reset();
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
    const { detail } = this.state;

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
                initialValue: detail.createdTime ? moment(detail.createdTime) : null,
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
                initialValue: detail.amount,
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
                initialValue: detail.payMethod,
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
                initialValue: detail.typeId,
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
                initialValue: detail.payeeName,
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
        title="修改回款记录"
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.renderBaseInfo()}
      </SimpleModal>
    );
  }
}
