import React, { PureComponent } from 'react';
import { Card, Button } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import { getPayment } from '../../../services/returnMoney';
import PaymentInfoTab from '../../../components/Sale/ReturnMoney/PaymentInfoTab';

export default class PaymentTabs extends PureComponent {
  state = {
    data: {},
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    const paymentId = params.id;
    this.get(paymentId);
  }

  get = paymentId => {
    getPayment(paymentId)
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          data,
        });
      });
  };

  back = () => {
    const { dispatch } = store;

    dispatch(
      routerRedux.push({
        pathname: '/sale/returnMoney',
      })
    );
  };

  render() {
    const { data } = this.state;
    const contractTitle = data.contractTitle || '';
    const period = data.period || '';
    const payeeName = data.payeeName || '';

    const title = `${contractTitle} 第${period}期回款记录`;

    return (
      <PageHeaderLayout
        title="回款记录详情"
        action={
          <Button type="primary" onClick={this.back}>
            返回
          </Button>
        }
      >
        <Card title={title}>
          <p
            style={{
              fontSize: 14,
              color: 'rgba(0, 0, 0, 0.85)',
              marginBottom: 16,
              fontWeight: 500,
            }}
          >
            付款人: {payeeName}
          </p>
          <PaymentInfoTab data={data} />
        </Card>
      </PageHeaderLayout>
    );
  }
}
