import React, { PureComponent } from 'react';
import { Form, DatePicker, InputNumber, Row, Col } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';
import { getAddPaymentPlanPeriodBySaleContractId } from '../../../services/saleContract';

const FormItem = Form.Item;

@Form.create()
export default class AddSaleContractPaymentPlanForm extends PureComponent {
  state = {
    period: 0,
    applicableAmount: 0,
  };

  componentDidMount() {
    const { saleContractId } = this.props;
    this.getPeriod(saleContractId);
  }

  getPeriod = saleContractId => {
    getAddPaymentPlanPeriodBySaleContractId(saleContractId)
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          period: data.period,
          applicableAmount: data.applicableAmount,
        });
      });
  };

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
    } = this.props;
    const { period, applicableAmount } = this.state;

    return (
      <Form>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="回款期次" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('period', {
                initialValue: period,
              })(<span style={{ color: 'red' }}>第{period}期</span>)}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={24}>
            <FormItem label="可回款最大金额" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('applicableAmount', {
                initialValue: applicableAmount,
              })(<span style={{ color: 'red' }}>{applicableAmount}</span>)}
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <FormItem label="日期" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payDate', {
                rules: [
                  {
                    required: true,
                    message: '请输入日期',
                  },
                ],
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
        title="新增回款计划"
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
