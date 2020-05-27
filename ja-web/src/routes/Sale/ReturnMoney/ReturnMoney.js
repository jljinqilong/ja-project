import React, { PureComponent } from 'react';
import { Tabs } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import PaymentPlan from './PaymentPlan';
import Payment from './Payment';
import { hasAccessKey } from '../../../utils/authority';

export default class ReturnMoney extends PureComponent {
  render() {
    return (
      (hasAccessKey('sale.paymentPlan.list') || hasAccessKey('sale.paymentDetail.list')) && (
        <PageHeaderLayout title="回款管理">
          <Tabs type="card" defaultActivityKey="1">
            {hasAccessKey('sale.paymentPlan.list') && (
              <Tabs.TabPane tab="回款计划" key="1">
                <PaymentPlan />
              </Tabs.TabPane>
            )}
            {hasAccessKey('sale.paymentDetail.list') && (
              <Tabs.TabPane tab="回款记录" key="2">
                <Payment />
              </Tabs.TabPane>
            )}
          </Tabs>
        </PageHeaderLayout>
      )
    );
  }
}
