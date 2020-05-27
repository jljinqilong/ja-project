import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva/index';
import { Form, Row, Col, Select, DatePicker, Input } from 'antd';
import { routerRedux } from 'dva/router';
import SimplePage from '../../../components/SimplePage/SimplePage';

const FormItem = Form.Item;
const { RangePicker } = DatePicker;

@connect(({ returnMoney, loading }) => ({
  returnMoney,
  loading: loading.models.returnMoney,
}))
@Form.create()
export default class Payment extends PureComponent {
  componentDidMount() {
    const { dispatch } = this.props;

    dispatch({
      type: 'returnMoney/fetchPayType',
    });
  }

  // ============ 明细 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      routerRedux.push({
        pathname: `/sale/returnMoney/payment/detail/${rowId}`,
      })
    );
  };

  // ============ 查询 ===============
  handleSearch = params => {
    const { dispatch } = this.props;

    const { pageSize, pageNum, departId, typeId, payDate: payDateRange, payeeName } = params;
    const payload = {
      departId,
      typeId,
      returnDateq: payDateRange && payDateRange[0] ? payDateRange[0].format('YYYY-MM-DD') : null,
      returnDatez: payDateRange && payDateRange[1] ? payDateRange[1].format('YYYY-MM-DD') : null,
      payeeName,
      pageSize,
      pageNum,
    };

    dispatch({
      type: 'returnMoney/fetchPayment',
      payload,
    });
  };

  // ============ 查询表单 ===============
  renderSearchForm = () => {
    const {
      form,
      returnMoney: { payTypeList },
    } = this.props;
    const { getFieldDecorator } = form;
    const labelCol = { span: 5 };
    const wrapperCol = { span: 19 };
    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="回款日期" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payDate')(
                <RangePicker format="YYYY-MM-DD" style={{ width: '100%' }} />
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="回款类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('typeId')(
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
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          {/*<Col md={8} sm={24}>*/}
          {/*<FormItem label="合同所属部门" labelCol={labelCol} wrapperCol={wrapperCol}>*/}
          {/*{getFieldDecorator('departId')(<Select placeholder="请选择" />)}*/}
          {/*</FormItem>*/}
          {/*</Col>*/}
          <Col md={8} sm={24}>
            <FormItem label="付款人" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('payeeName')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
        </Row>
      </Fragment>
    );
  };

  render() {
    const {
      form,
      returnMoney: { paymentData },
    } = this.props;

    const columns = [
      {
        title: '回款日期',
        dataIndex: 'createdTime',
      },
      {
        title: '回款金额',
        dataIndex: 'amount',
      },
      {
        title: '对应客户',
        dataIndex: 'customerName',
      },
      {
        title: '合同标题',
        dataIndex: 'contractTitle',
      },
      {
        title: '回款期次',
        dataIndex: 'period',
        render: text => {
          return `第${text}期`;
        },
      },
      {
        title: '付款方式',
        dataIndex: 'transNames.payMethod_name',
      },
      {
        title: '回款类型',
        dataIndex: 'transNames.typeId_name',
      },
      {
        title: '付款人',
        dataIndex: 'payeeName',
      },
    ];

    const accessKeys = {
      addKey: 'sale.paymentDetail.add',
      editKey: 'sale.paymentDetail.update',
      deleteKey: 'sale.paymentDetail.delete',
      viewKey: 'sale.paymentDetail.get',
      impKey: 'sale.paymentDetail.importExcel',
    };

    return (
      <SimplePage
        columns={columns}
        data={paymentData}
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
