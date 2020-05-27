import React, { PureComponent } from 'react';
import { Card, Button } from 'antd';
import { routerRedux } from 'dva/router';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import store from '../../../index';
import { getProduct } from '../../../services/product';
import ProductInfoTab from '../../../components/Sale/Product/ProductInfoTab';


export default class ProductTabs extends PureComponent {
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
    getProduct(rowId)
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
        pathname: '/sale/product',
      })
    );
  };

  render() {
    const { data } = this.state;
    return (
      <PageHeaderLayout
        title="产品详情"
        action={
          <Button type="primary" onClick={this.back}>
            返回
          </Button>
        }
      >
        <Card>
          <ProductInfoTab data={data} />
        </Card>
      </PageHeaderLayout>
    );
  }
}
