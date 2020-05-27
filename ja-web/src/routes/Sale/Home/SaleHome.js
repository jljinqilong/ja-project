import React, { PureComponent } from 'react';
import { Card, Row, Col } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import styles from './SaleHome.less';
import { hasAccessKey } from '../../../utils/authority';

export default class SaleHome extends PureComponent {
  render() {
    const Box = props => {
      const { url } = props;

      const navTo = () => {
        const { dispatch } = store;
        dispatch(routerRedux.push({ pathname: url }));
      };

      return <p onClick={() => navTo()}>{props.children}</p>;
    };

    const rowStyle = {
      marginBottom: '0px',
    };

    return (
      <Card className={styles.saleHome}>
        <Row gutter={48} style={rowStyle}>
          {hasAccessKey('sale.area') && (
            <Col span={8}>
              <Box url="/sale/area">区域管理</Box>
            </Col>
          )}
          {hasAccessKey('sale.customer') && (
            <Col span={8}>
              <Box url="/sale/customer">客户管理</Box>
            </Col>
          )}
          {hasAccessKey('sale.product') && (
            <Col span={8}>
              <Box url="/sale/product">产品管理</Box>
            </Col>
          )}
          {hasAccessKey('sale.inquiries') && (
            <Col span={8}>
              <Box url="/sale/inquiries">询单记录</Box>
            </Col>
          )}
          {hasAccessKey('sale.inquiriesAppraisal') && (
            <Col span={8}>
              <Box url="/sale/inquiriesAppraisal">询单评审</Box>
            </Col>
          )}
          {hasAccessKey('sale.saleContract') && (
            <Col span={8}>
              <Box url="/sale/saleContract">合同管理</Box>
            </Col>
          )}
          {hasAccessKey('sale.returnMoney') && (
            <Col span={8}>
              <Box url="/sale/returnMoney">回款管理</Box>
            </Col>
          )}
          {hasAccessKey('sale.stockUp') && (
            <Col span={8}>
              <Box url="/sale/stockUp">备货计划</Box>
            </Col>
          )}
        </Row>
      </Card>
    );
  }
}
