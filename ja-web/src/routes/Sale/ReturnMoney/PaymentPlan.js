import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva/index';
import { Form, Row, Col, Select, DatePicker, Input } from 'antd';
import { routerRedux } from 'dva/router';
import SimplePage from '../../../components/SimplePage/SimplePage';

const FormItem = Form.Item;
const { RangePicker } = DatePicker;
const paymentPlanOptions = [
  { rowId: null, name: '不限' },
  { rowId: '0', name: '未完成' },
  { rowId: '1', name: '完成' },
  { rowId: '2', name: '逾期未完成' },
];
const paymentPlanStatusKeys = [0, 1, 2];
const paymentPlanStatusValues = ['未完成', '完成', '逾期未完成'];

@connect(({ returnMoney, loading }) => ({
  returnMoney,
  loading: loading.models.returnMoney,
}))
@Form.create()
export default class PaymentPlan extends PureComponent {
  // ============ 明细 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      routerRedux.push({
        pathname: `/sale/returnMoney/paymentPlan/detail/${rowId}`,
      })
    );
  };

  // ============ 查询 ===============
  handleSearch = params => {
    const { dispatch } = this.props;

    const { pageSize, pageNum, status, departId, ourSignatory, payDate: payDateRange } = params;
    const payload = {
      status,
      departId,
      ourSignatory,
      returnDateq: payDateRange && payDateRange[0] ? payDateRange[0].format('YYYY-MM-DD') : null,
      returnDatez: payDateRange && payDateRange[1] ? payDateRange[1].format('YYYY-MM-DD') : null,
      pageSize,
      pageNum,
    };

    dispatch({
      type: 'returnMoney/fetchPaymentPlan',
      payload,
    });
  };

  // ============ 查询表单 ===============
  renderSearchForm = () => {
    const { form } = this.props;
    const { getFieldDecorator } = form;
    const labelCol = { span: 5 };
    const wrapperCol = { span: 19 };
    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="计划回款日期" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payDate')(
                <RangePicker format="YYYY-MM-DD" style={{ width: '100%' }} />
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="回款状态" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('status')(
                <Select placeholder="请选择">
                  {paymentPlanOptions.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          {/*<Col md={8} sm={24}>*/}
          {/*<FormItem label="合同所属部门" labelCol={labelCol} wrapperCol={wrapperCol}>*/}
          {/*{getFieldDecorator('departId')(<Select placeholder="请选择" />)}*/}
          {/*</FormItem>*/}
          {/*</Col>*/}
          <Col md={8} sm={24}>
            <FormItem label="合同负责人" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('ourSignatory')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
        </Row>
      </Fragment>
    );
  };

  render() {
    const {
      form,
      returnMoney: { paymentPlanData },
    } = this.props;

    const columns = [
      {
        title: '计划回款日期',
        dataIndex: 'payDate',
      },
      {
        title: '回款期次',
        dataIndex: 'period',
        render: text => {
          return `第${text}期`;
        },
      },
      {
        title: '计划回款金额',
        dataIndex: 'payAmount',
      },
      {
        title: '已回款金额',
        dataIndex: 'plan_paymentAmount',
      },
      {
        title: '合同标题',
        dataIndex: 'saleContract.contractTitle',
      },
      {
        title: '对应客户',
        dataIndex: 'customerName',
      },
      {
        title: '回款状态',
        dataIndex: 'status',
        render: text => {
          return paymentPlanStatusKeys.indexOf(text) !== -1
            ? paymentPlanStatusValues[paymentPlanStatusKeys.indexOf(text)]
            : '';
        },
      },
      {
        title: '未回款金额',
        dataIndex: 'plan_unPaymentAmount',
      },
      {
        title: '逾期天数',
        dataIndex: 'overDate',
      },
      {
        title: '合同负责人',
        dataIndex: 'saleContract.ourSignatory',
      },
      // {
      //   title: '合同所属部门',
      //   dataIndex: 'saleContract.departId',
      // },
    ];

    const accessKeys = {
      addKey: 'sale.paymentPlan.add',
      editKey: 'sale.paymentPlan.update',
      deleteKey: 'sale.paymentPlan.delete',
      viewKey: 'sale.paymentPlan.get',
      impKey: 'sale.paymentPlan.importExcel',
    };

    return (
      <SimplePage
        columns={columns}
        data={paymentPlanData}
        form={form}
        onSearch={this.handleSearch}
        renderSearchForm={this.renderSearchForm}
        onEdit={false}
        onDelete={false}
        onView={this.handleView}
        accessKeys={accessKeys}
      />
    );
  }
}
