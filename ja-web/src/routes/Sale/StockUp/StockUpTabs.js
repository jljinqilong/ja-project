import React, { PureComponent } from 'react';
import { Card, Button } from 'antd';
import { routerRedux } from 'dva/router';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import store from '../../../index';
import { getStockUp } from '../../../services/stockUp';
import StockUpInfoTab from '../../../components/Sale/StockUp/StockUpInfoTab';

export default class StockUpTabs extends PureComponent {
  state = {
    data: {},
  };

  componentDidMount() {
    const {
      match: { params },
    } = this.props;
    const rowId = params.id;
    this.get(rowId);
  }

  // get 方法
  get = rowId => {
    getStockUp(rowId)
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
        pathname: '/sale/stockUp',
      })
    );
  };

  render() {
    const { data } = this.state;
    return (
      <PageHeaderLayout
        title="备货计划详情"
        action={
          <Button type="primary" onClick={this.back}>
            返回
          </Button>
        }
      >
        <Card>
          <StockUpInfoTab data={data} />
        </Card>
      </PageHeaderLayout>
    );
  }
}
