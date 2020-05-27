import React, { PureComponent, Fragment } from 'react';
import { Card, Table } from 'antd';
import DescriptionList from '../../DescriptionList';

import styles from './EnquiryInfoTab.less';

const { Description } = DescriptionList;

export default class EnquiryInfoTab extends PureComponent {
  render() {
    const {
      data,
      data: { transNames },
    } = this.props;

    const productColumns = [
      {
        title: '产品编号',
        dataIndex: 'productNo',
      },
      {
        title: '电池片数',
        dataIndex: 'transNames.cellNumberId_name',
      },
      {
        title: '电池型号',
        dataIndex: 'transNames.batteryTypeId_name',
      },
      {
        title: '标称功率',
        dataIndex: 'power',
      },
      {
        title: '单位',
        dataIndex: 'transNames.unitId_name',
      },
      {
        title: '数量',
        dataIndex: 'num',
      },
      {
        title: '赠送数量',
        dataIndex: 'giveNum',
      },
      {
        title: '总功率',
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
        title: '售价',
        dataIndex: 'amount',
        render: text => {
          return text.toFixed(2);
        },
      },
    ];

    const deliveryTimeColumns = [
      {
        title: '交期日期',
        dataIndex: 'deliveryTime',
      },
      {
        title: '交期',
        dataIndex: 'num',
      },
    ];

    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="询单标题" className={styles.info}>
              {data.inquiryTitle}
            </Description>
            <Description term="编号" className={styles.info}>
              {data.inquiryNo}
            </Description>
            <Description term="客户名称" className={styles.info}>
              {data.customerName}
            </Description>
            <Description term="项目地" className={styles.info}>
              {data.estimatedSalesAmount}
            </Description>
            <Description term="产品类型" className={styles.info}>
              {data.customerName}
            </Description>
            <Description term="功率档位" className={styles.info}>
              {data.estimatedSigningDate}
            </Description>
            <Description term="订单总量" className={styles.info}>
              {data.orderTotal}
            </Description>
            <Description term="主栅根数" className={styles.info}>
              {data.mainGateNumber}
            </Description>
            <Description term="贸易方式" className={styles.info}>
              {transNames ? transNames.tradeModeId_name : ''}
            </Description>
            <Description term="首年衰减" className={styles.info}>
              {data.firstYearAttenuation}
            </Description>
            <Description term="付款条件" className={styles.info}>
              {data.paymentTerm}
            </Description>
            <Description term="认证要求" className={styles.info}>
              {data.certificationRequire}
            </Description>
            <Description term="送功率" className={styles.info}>
              {data.outputPower}
            </Description>
            <Description term="标板要求" className={styles.info}>
              {data.plateRequire}
            </Description>
            <Description term="质保要求" className={styles.info}>
              {data.warrantyRequire}
            </Description>
            <Description term="特殊要求" className={styles.info}>
              {data.specialRequire}
            </Description>
            <Description term="销售区域" className={styles.info}>
              {data.saleAreaName}
            </Description>
            <Description term="业务员" className={styles.salesman}>
              {data.orderTotal}
            </Description>
            <Description term="违约条款" className={styles.info}>
              {data.violateClause}
            </Description>
            <Description term="BOM要求" className={styles.info}>
              {data.bomRequire}
            </Description>
            <Description term="边框颜色" className={styles.info}>
              {transNames ? transNames.borderColorId_name : ''}
            </Description>
            <Description term="边框规格" className={styles.info}>
              {transNames ? transNames.borderSpecificationId_name : ''}
            </Description>
            <Description term="背板颜色" className={styles.info}>
              {transNames ? transNames.backboardColorId_name : ''}
            </Description>
            <Description term="背板材质" className={styles.info}>
              {transNames ? transNames.backboardMaterialId_name : ''}
            </Description>
            <Description term="接线盒" className={styles.info}>
              {transNames ? transNames.junctionBoxId_name : ''}
            </Description>
            <Description term="玻璃" className={styles.info}>
              {data.glass}
            </Description>
            <Description term="EVA" className={styles.info}>
              {transNames ? transNames.evaId_name : ''}
            </Description>
            <Description term="成本" className={styles.info}>
              {data.cost}
            </Description>
            <Description term="毛利率" className={styles.info}>
              {data.grossProfitRatio}
            </Description>
          </DescriptionList>
        </Card>
        <Card title="产品信息" bordered={false}>
          <Table
            rowKey="rowId"
            columns={productColumns}
            dataSource={data.inquiriesProductList}
            pagination={false}
          />
        </Card>
        <Card title="交期信息" bordered={false}>
          <Table
            rowKey="rowId"
            columns={deliveryTimeColumns}
            dataSource={data.inquiriesDeliveryTimeList}
            pagination={false}
          />
        </Card>
      </Fragment>
    );
  }
}
