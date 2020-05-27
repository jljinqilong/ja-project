import React, { PureComponent, Fragment } from 'react';
import { Card } from 'antd';
import DescriptionList from '../../DescriptionList';

import styles from './PaymentInfoTab.less';

const { Description } = DescriptionList;

export default class PaymentInfoTab extends PureComponent {
  render() {
    const { data } = this.props;

    const payMethodName = data.transNames ? data.transNames.payMethod_name : '';
    const typeName = data.transNames ? data.transNames.typeId_name : '';

    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="回款日期" className={styles.info}>
              {data.createdTime}
            </Description>
            <Description term="合同标题" className={styles.info}>
              {data.contractTitle}
            </Description>
            <Description term="回款金额" className={styles.info}>
              {data.amount}
            </Description>
            <Description term="对应客户" className={styles.info}>
              {data.customerName}
            </Description>
            <Description term="回款期次" className={styles.info}>
              {data.period}
            </Description>
            <Description term="付款方式" className={styles.info}>
              {payMethodName}
            </Description>
            <Description term="回款类型" className={styles.info}>
              {typeName}
            </Description>
          </DescriptionList>
        </Card>
      </Fragment>
    );
  }
}
