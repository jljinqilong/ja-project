import React, { PureComponent, Fragment } from 'react';
import { Card } from 'antd';
import styles from './ProductInfoTab.less';
import DescriptionList from '../../DescriptionList';

const { Description } = DescriptionList;

export default class ProductInfoTab extends PureComponent {
  render() {
    const { data } = this.props;

    const cellTechnologyName = data.transNames ? data.transNames.cellTechnologyId_name : '';
    const cellNumberName = data.transNames ? data.transNames.cellNumberId_name : '';
    const muduleTypeName = data.transNames ? data.transNames.muduleTypeId_name : '';
    const muduleCodeName = data.transNames ? data.transNames.muduleCodeId_name : '';
    const siliconTypeName = data.transNames ? data.transNames.siliconTypeId_name : '';
    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="small" col={2}>
            <Description term="晶澳" className={styles.info}>
              JA
            </Description>
            <Description term="产品编号" className={styles.info}>
              {data.productNo}
            </Description>
            <Description term="硅片类型" className={styles.info}>
              {siliconTypeName}
            </Description>
            <Description term="电池片数" className={styles.info}>
              {cellNumberName}
            </Description>
            <Description term="组件类型" className={styles.info}>
              {muduleTypeName}
            </Description>
            <Description term="组件关键信息码" className={styles.info}>
              {muduleCodeName}
            </Description>
            <Description term="组件功率" className={styles.info}>
              {data.ratedPower}
            </Description>
            <Description term="电池片技术" className={styles.info}>
              {cellTechnologyName}
            </Description>
          </DescriptionList>
        </Card>
      </Fragment>
    );
  }
}
