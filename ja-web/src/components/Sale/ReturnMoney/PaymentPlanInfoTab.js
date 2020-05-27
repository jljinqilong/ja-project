import React, { PureComponent, Fragment } from 'react';
import { Card } from 'antd';
import DescriptionList from '../../DescriptionList';

import styles from './PaymentPlanInfoTab.less';

const { Description } = DescriptionList;

export default class PaymentPlanInfoTab extends PureComponent {
  render() {
    const { data } = this.props;

    const paymentPlanStatusKeys = [0, 1, 2];
    const paymentPlanStatusValues = ['未完成', '完成', '逾期未完成'];
    const statusName =
      paymentPlanStatusKeys.indexOf(data.status) !== -1
        ? paymentPlanStatusValues[paymentPlanStatusKeys.indexOf(data.status)]
        : '';

    const contractTitle = data.saleContract ? data.saleContract.contractTitle : '';

    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="计划回款日期" className={styles.info}>
              {data.payDate}
            </Description>
            <Description term="合同标题" className={styles.info}>
              {contractTitle}
            </Description>
            <Description term="计划回款金额" className={styles.info}>
              {data.payAmount}
            </Description>
            <Description term="对应客户" className={styles.info}>
              {data.customerName}
            </Description>
            <Description term="回款期次" className={styles.info}>
              {data.period}
            </Description>
          </DescriptionList>
        </Card>

        <Card title="其他信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="回款状态" className={styles.info}>
              {statusName}
            </Description>
            <Description term="已回款金额" className={styles.info}>
              {data.plan_paymentAmount}
            </Description>
            <Description term="逾期天数" className={styles.info}>
              {data.overDate}
            </Description>
            <Description term="未回款金额" className={styles.info}>
              {data.plan_unPaymentAmount}
            </Description>
          </DescriptionList>
        </Card>
      </Fragment>
    );
  }
}
