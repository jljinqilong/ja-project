import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Form, DatePicker, Input, Select, Row, Col, message } from 'antd';
import moment from 'moment';
import { getInvoiceById } from '../../../services/saleContract';
import SimpleModal from '../../Modal/SimpleModal';

const FormItem = Form.Item;

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class EditSaleContractInvoiceForm extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      detail: {},
      backup: {},
    };
  }

  componentDidMount() {
    const { currentEditId: currentEditInvoiceId } = this.props;
    this.get(currentEditInvoiceId);
  }

  get = rowId => {
    if (rowId) {
      getInvoiceById(rowId)
        .then(resp => resp.data)
        .then(data => {
          this.setState({
            detail: data,
            backup: { ...data },
          });
        })
        .catch(() => {
          message.error('查询发票明细失败');
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
      saleContract: { invoiceTypeList },
    } = this.props;
    const { detail } = this.state;

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
                initialValue: moment(detail.kprq),
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
                initialValue: detail.invoiceContext,
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
                initialValue: detail.invoiceAmount,
              })(<Input placeholder="请输入" maxLength={30} />)}
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
                initialValue: detail.invoiceTypeId,
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
                initialValue: detail.invoiceNo,
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
                initialValue: detail.invoiceUserName,
              })(<Input placeholder="请输入" maxLength={20} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="备注" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('remark', { initialValue: detail.remark })(
                <Input placeholder="请输入" maxLength={100} />
              )}
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
        title="修改发票"
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.renderBaseInfo()}
      </SimpleModal>
    );
  }
}
