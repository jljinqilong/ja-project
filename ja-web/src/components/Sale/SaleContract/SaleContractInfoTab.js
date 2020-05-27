import React, { PureComponent, Fragment } from 'react';
import { Card, Table } from 'antd';
import DescriptionList from '../../DescriptionList';

import styles from './SaleContractInfoTab.less';

const { Description } = DescriptionList;

export default class SaleContractInfoTab extends PureComponent {
  render() {
    const { detail } = this.props;
    const { status } = detail;
    const saleContractStatusKeys = [0, 1, 2];
    const saleContractStatusValues = ['未完成', '完成', '逾期未完成'];
    const statusName =
      saleContractStatusKeys.indexOf(status) !== -1
        ? saleContractStatusValues[saleContractStatusKeys.indexOf(status)]
        : '';
    const contractTypeName = detail.transNames ? detail.transNames.contractType_name : '';
    const payMethodName = detail.transNames ? detail.transNames.payMethod_name : '';
    const currencyName = detail.transNames ? detail.transNames.saleCurrency_name : '';
    const deliveryAddressName = detail.transNames ? detail.transNames.deliveryAddress_name : '';


    const countryName = detail.countryName != null ? detail.countryName :null;
    const provinceName = detail.provinceName != null ?  (`/${detail.provinceName}`) :null;
    const cityName = detail.cityName != null ? (`/${detail.cityName}`) :null;

    const productColumns = [
      {
        title: '产品编号',
        dataIndex: 'product.productNo',
      },
      {
        title: '标称功率',
        dataIndex: 'power',
      },
      {
        title: '单位',
        dataIndex: 'transNames.unit_name',
      },
      {
        title: '总数量',
        dataIndex: 'num',
      },
      {
        title: '赠送数量',
        dataIndex: 'presentNum',
      },
      {
        title: '功率',
        dataIndex: 'totalPower',
      },
      {
        title: '单价',
        dataIndex: 'unitPrice',
        render: text => {
          return text.toFixed(2);
        },
      },
      {
        title: '金额',
        dataIndex: 'amount',
        render: text => {
          return text.toFixed(2);
        },
      },
    ];

    const deliveryColumns = [
      {
        title: '交期要求日期',
        dataIndex: 'deliveryTime',
        width: 200,
      },
      {
        title: '交期要求数量（MW）',
        dataIndex: 'deliveryNum',
        width: 200,
      },
    ];

    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="large" col={2}>
            <Description term="合同编号" className={styles.info}>
              {detail.contractNo}
            </Description>
            <Description term="合同标题" className={styles.info}>
              {detail.contractTitle}
            </Description>
            <Description term="合同总金额" className={styles.info}>
              {detail.totalAmount ? detail.totalAmount.toFixed(2) : 0.0}
            </Description>
            <Description term="对应客户" className={styles.info}>
              {detail.customerName}
            </Description>
          </DescriptionList>
        </Card>

        <Card title="其他信息" bordered={false}>
          <DescriptionList size="large" col={2}>
            <Description term="合同状态" className={styles.info}>
              {statusName}
            </Description>
            <Description term="取号日期" className={styles.info}>
              {detail.getNoDate}
            </Description>
            <Description term="合同类型" className={styles.info}>
              {contractTypeName}
            </Description>
            <Description term="已回款金额" className={styles.info}>
              {detail.paymentAmount}
            </Description>
            <Description term="付款方式" className={styles.info}>
              {payMethodName}
            </Description>
            <Description term="未回款金额" className={styles.info}>
              {detail.unPaymentAmount}
            </Description>
            <Description term="业务人员" className={styles.info}>
              {detail.businessPerson}
            </Description>
            <Description term="已开票金额" className={styles.info}>
              {detail.ticketAmount}
            </Description>
            <Description term="所属区域" className={styles.info}>
              {detail.regionName}
            </Description>
            <Description term="未开票金额" className={styles.info}>
              {detail.unTicketAmount}
            </Description>
            <Description term="发货地" className={styles.info}>
              {deliveryAddressName}
            </Description>
            <Description term="项目地" className={styles.info}>
              {countryName}{provinceName}{cityName}
            </Description>
            <Description term="币别" className={styles.info}>
              {currencyName}
            </Description>
            <Description term="总功率" className={styles.info}>
              {detail.totalPower}
            </Description>
          </DescriptionList>
        </Card>

        <Card title="产品信息" bordered={false}>
          <Table
            rowKey="rowId"
            columns={productColumns}
            dataSource={detail.productList}
            pagination={false}
          />
        </Card>

        <Card title="交期信息" bordered={false}>
          <Table
            rowKey="rowId"
            columns={deliveryColumns}
            dataSource={detail.saleDeliveryList}
            pagination={false}
          />
        </Card>
      </Fragment>
    );
  }
}
