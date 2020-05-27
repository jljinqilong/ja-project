import React, { PureComponent } from 'react';
import { Tabs } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import Component from './Component';
import { hasAccessKey } from '../../../utils/authority';

export default class Product extends PureComponent {
  render() {
    return (
      hasAccessKey('sale.product.list') && (
        <PageHeaderLayout title="产品管理">
          <Tabs type="card" defaultActivityKey="1">
            {hasAccessKey('sale.product.list') && (
              <Tabs.TabPane tab="组件列表" key="1">
                <Component />
              </Tabs.TabPane>
            )}
            <Tabs.TabPane tab="电池列表" key="2">
              电池列表
            </Tabs.TabPane>
          </Tabs>
        </PageHeaderLayout>
      )
    );
  }
}
