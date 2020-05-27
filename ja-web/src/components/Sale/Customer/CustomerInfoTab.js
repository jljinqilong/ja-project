import React, { PureComponent, Fragment } from 'react';
import { Card } from 'antd';
import DescriptionList from '../../DescriptionList';

import styles from './CustomerInfoTab.less';

const { Description } = DescriptionList;

export default class CustomerInfoTab extends PureComponent {
  render() {
    const { detail } = this.props;

    const customerLevelName = detail.transNames ? detail.transNames.customerLevelId_name : '';
    const accountUnitIdName = detail.transNames ? detail.transNames.accountUnitId_name : '';
    const paymentTypeIdName = detail.transNames ? detail.transNames.paymentTypeId_name : '';
    const jaCurrencyIdName = detail.transNames ? detail.transNames.jaCurrencyId_name : '';
    const zxbCurrencyIdName = detail.transNames ? detail.transNames.zxbCurrencyId_name : '';
    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="客户名称" className={styles.info}>
              {detail.customerName}
            </Description>
            <Description term="账期" className={styles.info}>
              {detail.paymentDays}
              {accountUnitIdName}
            </Description>
            <Description term="英文简称" className={styles.info}>
              {detail.shortEnName}
            </Description>
            <Description term="付款方式" className={styles.info}>
              {paymentTypeIdName}
            </Description>
            <Description term="简称" className={styles.info}>
              {detail.shortName}
            </Description>
            <Description term="客户等级" className={styles.info}>
              {customerLevelName}
            </Description>
            <Description term="成立时间" className={styles.info}>
              {detail.regTime}
            </Description>
            <Description term="银行账户" className={styles.info}>
              {detail.bankAccount}
            </Description>
            <Description
              term="收货地址"
              className={styles.info}
              style={{ height: '60px', lineHeight: '60px' }}
            >
              {detail.deliveryAddress}
            </Description>
            <Description
              term="注册资本"
              className={styles.info}
              style={{ height: '60px', lineHeight: '60px' }}
            >
              {detail.regCapital}
            </Description>
          </DescriptionList>
        </Card>
        <Card title="开票信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="税号" className={styles.info}>
              {detail.dutyParagraph}
            </Description>
            <Description term="发票寄送邮编" className={styles.info}>
              {detail.invoiceSendingPcode}
            </Description>
            <Description
              term="发票寄送地址"
              className={styles.info}
              style={{ height: '60px', lineHeight: '60px' }}
            >
              {detail.invoiceSendingAddress}
            </Description>
          </DescriptionList>
        </Card>
        <Card title="联系信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="地区" className={styles.info}>
              {detail.regionName}
            </Description>
            <Description term="传真" className={styles.info}>
              {detail.fax}
            </Description>
            <Description term="邮编" className={styles.info}>
              {detail.postalCode}
            </Description>
            <Description term="网址" className={styles.info}>
              {detail.webSite}
            </Description>
            <Description
              term="地址"
              className={styles.info}
              style={{ height: '60px', lineHeight: '60px' }}
            >
              {detail.address}
            </Description>
          </DescriptionList>
        </Card>

        <Card title="欠款额度信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="币别" className={styles.info}>
              {jaCurrencyIdName}
            </Description>
            <Description term="晶澳欠款额度" className={styles.info}>
              {detail.debtLimit}
            </Description>
            <Description term="币别" className={styles.info}>
              {zxbCurrencyIdName}
            </Description>
            <Description term="中信保欠款额度" className={styles.info}>
              {detail.zxbArrears}
            </Description>
          </DescriptionList>
        </Card>
      </Fragment>
    );
  }
}
