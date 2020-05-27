import React, { PureComponent } from 'react';
import { Card, Button } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import { getPaymentPlan } from '../../../services/returnMoney';
import PaymentPlanInfoTab from '../../../components/Sale/ReturnMoney/PaymentPlanInfoTab';

export default class PaymentPlanTabs extends PureComponent {
  state = {
    data: {},
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    const paymentPlanId = params.id;
    this.get(paymentPlanId);
  }

  get = paymentPlanId => {
    getPaymentPlan(paymentPlanId)
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
    const contractTitle = data.saleContract ? data.saleContract.contractTitle : '';
    const period = data.period || '';
    const ourSignatory = data.saleContract ? data.saleContract.ourSignatory : '';

    const title = `${contractTitle} 第${period}期回款计划`;

    return (
      <PageHeaderLayout
        title="回款计划详情"
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
            负责人: {ourSignatory}
          </p>
          <PaymentPlanInfoTab data={data} />
        </Card>
      </PageHeaderLayout>
    );
  }
}
