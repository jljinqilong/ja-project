import React, { PureComponent } from 'react';
import { Form, DatePicker, InputNumber, Row, Col, message } from 'antd';
import moment from 'moment';
import SimpleModal from '../../Modal/SimpleModal';
import {
  getPaymentPlanById,
  getUpdatePaymentPlanPeriodBySaleContractId,
} from '../../../services/saleContract';

const FormItem = Form.Item;

@Form.create()
export default class EditSaleContractPaymentPlanForm extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      detail: {},
      backup: {},
      applicableAmount: 0,
    };
  }

  componentDidMount() {
    const { currentEditId: currentEditPaymentPlanId, saleContractId } = this.props;
    this.get(currentEditPaymentPlanId);
    this.getPeriod(saleContractId, currentEditPaymentPlanId);
  }

  getPeriod = (saleContractId, paymentPlanId) => {
    if (!paymentPlanId) return;
    getUpdatePaymentPlanPeriodBySaleContractId(saleContractId, paymentPlanId)
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          applicableAmount: data.applicableAmount,
        });
      });
  };

  get = rowId => {
    if (rowId) {
      getPaymentPlanById(rowId)
        .then(resp => resp.data)
        .then(data => {
          this.setState({
            detail: data,
            backup: { ...data },
          });
        })
        .catch(() => {
          message.error('查询回款计划失败');
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
    } = this.props;
    const { detail, applicableAmount } = this.state;

    return (
      <Form>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="回款期次" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('period', {
                initialValue: detail.period,
              })(<span style={{ color: 'red' }}>第{detail.period}期</span>)}
            </FormItem>
          </Col>
          <Row gutter={24}>
            <Col span={24}>
              <FormItem label="可回款最大金额" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('applicableAmount', {
                  initialValue: applicableAmount,
                })(<span style={{ color: 'red' }}>{applicableAmount}</span>)}
              </FormItem>
            </Col>
          </Row>
          <Col span={24}>
            <FormItem label="日期" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payDate', {
                rules: [
                  {
                    required: true,
                    message: '请输入日期',
                  },
                ],
                initialValue: detail.payDate ? moment(detail.payDate) : null,
              })(<DatePicker placeholder="请选择" style={{ width: '100%' }} />)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="计划回款总金额" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payAmount', {
                rules: [
                  {
                    required: true,
                    message: '请输入计划回款总金额',
                  },
                ],
                initialValue: detail.payAmount,
              })(
                <InputNumber
                  placeholder="请输入"
                  min={1}
                  max={applicableAmount}
                  style={{ width: '100%' }}
                />
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
        title="修改回款计划"
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.renderBaseInfo()}
      </SimpleModal>
    );
  }
}
