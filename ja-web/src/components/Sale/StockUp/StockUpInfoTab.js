import React, { PureComponent, Fragment } from 'react';
import { Card } from 'antd';
import styles from './StockUpInfoTab.less';
import DescriptionList from '../../DescriptionList';

const { Description } = DescriptionList;

export default class StockUpInfoTab extends PureComponent {
  render() {
    const { data } = this.props;
    const stockAddressName = data.transNames ? data.transNames.stockAddress_name : '';
    const paymentInfoName = data.transNames ? data.transNames.paymentInfo_name : '';
    return (
      <Fragment>
        <Card title="基本信息" bordered={false}>
          <DescriptionList size="small" col={4}>
            <Description term="备货编号" className={styles.info}>
              {data.stockNo}
            </Description>
            <Description term="销售" className={styles.info}>
              {data.salesman}
            </Description>
            <Description term="备货地点" className={styles.info}>
              {stockAddressName}
            </Description>
            <Description term="备货日期" className={styles.info}>
              {data.stockDate}
            </Description>
            <Description term="评审编号/OA流程编号" className={styles.info}>
              {data.oaNo}
            </Description>
            <Description term="客户" className={styles.info}>
              {data.customerName}
            </Description>
            <Description term="客户简称" className={styles.info}>
              {data.jcustomerName}
            </Description>
            <Description term="合同号码" className={styles.info}>
              {data.contractNo}
            </Description>
            <Description term="客户付款信息" className={styles.info}>
              {paymentInfoName}
            </Description>
            <Description term="合同签约地" className={styles.info}>
              {data.contractAddress}
            </Description>
            <Description term="发票片数" className={styles.info}>
              {data.sendNum}
            </Description>
            <Description term="组件型号(新)" className={styles.info}>
              {data.newComponentmodel}
            </Description>
            <Description term="组件型号(旧)" className={styles.info}>
              {data.oldComponentmodel}
            </Description>
            <Description term="组件类型" className={styles.info}>
              {data.componentType}
            </Description>
            <Description term="电池工艺" className={styles.info}>
              {data.batteryProcess}
            </Description>
            <Description term="电池片封装片数" className={styles.info}>
              {data.packageNum}
            </Description>
            <Description term="镀膜" className={styles.info}>
              {data.coating}
            </Description>
            <Description term="组件等级" className={styles.info}>
              {data.componentLevel}
            </Description>
            <Description term="单片功率(W)" className={styles.info}>
              {data.singlePower}
            </Description>
            <Description term="A类片高效/低效" className={styles.info}>
              {data.typeA}
            </Description>
            <Description term="总发货瓦数" className={styles.info}>
              {data.totalSendNum}
            </Description>
            <Description term="单位" className={styles.info}>
              {data.unit}
            </Description>
            <Description term="交货方式" className={styles.info}>
              {data.deliveryMethod}
            </Description>
            <Description term="自产/代工" className={styles.info}>
              {data.mode}
            </Description>
            <Description term="铝框工艺" className={styles.info}>
              {data.alProcess}
            </Description>
            <Description term="接线盒" className={styles.info}>
              {data.junctionBox}
            </Description>
            <Description term="出货抽检" className={styles.info}>
              {data.shipmentsInspection}
            </Description>
            <Description term="功率要求" className={styles.info}>
              {data.powerRequire}
            </Description>
            <Description term="其他特殊说明" className={styles.info}>
              {data.otherInstruction}
            </Description>
            <Description term="是否要欧盟申报" className={styles.info}>
              {data.isDeclaration}
            </Description>
            <Description term="承诺发票号" className={styles.info}>
              {data.invoiceNo}
            </Description>
            <Description term="客户要求EXW" className={styles.info}>
              {data.customerRequirementExw}
            </Description>
            <Description term="客户要求ETD" className={styles.info}>
              {data.customerRequirementEtd}
            </Description>

            <Description term="客户要求ETA" className={styles.info}>
              {data.customerRequirementEta}
            </Description>
            <Description term="货物代理" className={styles.info}>
              {data.goodsAgent}
            </Description>
            <Description term="起运港" className={styles.info}>
              {data.shipmentPort}
            </Description>
            <Description term="目的港" className={styles.info}>
              {data.destinationPort}
            </Description>
            <Description term="客户地址" className={styles.info}>
              {data.customerAddress}
            </Description>
            <Description term="联系人" className={styles.info}>
              {data.contacts}
            </Description>
            <Description term="电话" className={styles.info}>
              {data.telephone}
            </Description>
            <Description term="目的国" className={styles.info}>
              {data.destinationCountry}
            </Description>
            <Description term="船公司" className={styles.info}>
              {data.boatCompany}
            </Description>
            <Description term="提单正本/电放" className={styles.info}>
              {data.telex}
            </Description>
            <Description term="是否投保质量保证保险" className={styles.info}>
              {data.isCover}
            </Description>
            <Description term="铝框厂家" className={styles.info}>
              {data.alVender}
            </Description>
            <Description term="栅线数量" className={styles.info}>
              {data.lineNum}
            </Description>
            <Description term="EVA" className={styles.info}>
              {data.eva}
            </Description>
            <Description term="玻璃厚度" className={styles.info}>
              {data.glass}
            </Description>
            <Description term="接线头" className={styles.info}>
              {data.wiringHead}
            </Description>
            <Description term="铭牌" className={styles.info}>
              {data.nameplate}
            </Description>
            <Description term="BOMID" className={styles.info}>
              {data.bomid}
            </Description>
            <Description term="电流档" className={styles.info}>
              {data.power}
            </Description>
            <Description term="贸易类型" className={styles.info}>
              {data.tradeType}
            </Description>
            <Description term="包装方式" className={styles.info}>
              {data.packageMethod}
            </Description>
            <Description term="接线盒线长" className={styles.info}>
              {data.lineLength}
            </Description>
            <Description term="边框安装孔位置" className={styles.info}>
              {data.holeLocation}
            </Description>
            <Description term="奉贤质量检测时间" className={styles.info}>
              {data.checkDate}
            </Description>
            <Description term="计划出厂时间" className={styles.info}>
              {data.planDate}
            </Description>
            <Description term="实际出厂时间" className={styles.info}>
              {data.actualDate}
            </Description>
            <Description term="实际发货块数" className={styles.info}>
              {data.blockNum}
            </Description>
            <Description term="实际发货瓦数" className={styles.info}>
              {data.tileNum}
            </Description>
            <Description term="未发数量" className={styles.info}>
              {data.unsentNum}
            </Description>
            <Description term="报关时间" className={styles.info}>
              {data.declareTime}
            </Description>
            <Description term="报关行" className={styles.info}>
              {data.declareBroker}
            </Description>
            <Description term="报关单号" className={styles.info}>
              {data.declareNo}
            </Description>
            <Description term="装柜时间" className={styles.info}>
              {data.loadTime}
            </Description>
            <Description term="离港时间" className={styles.info}>
              {data.leaveTime}
            </Description>
            <Description term="集装箱号" className={styles.info}>
              {data.loadNo}
            </Description>
            <Description term="发票号" className={styles.info}>
              {data.invoice}
            </Description>
            <Description term="提单号" className={styles.info}>
              {data.billNo}
            </Description>
            <Description term="正本提单/发票箱单" className={styles.info}>
              {data.invoiceBill}
            </Description>
            <Description term="发票箱单/提单复印件" className={styles.info}>
              {data.invoiceBillCopy}
            </Description>
            <Description term="保单" className={styles.info}>
              {data.policy}
            </Description>
            <Description term="DN" className={styles.info}>
              {data.dn}
            </Description>
            <Description term="INV" className={styles.info}>
              {data.inv}
            </Description>
            <Description term="B/L" className={styles.info}>
              {data.bl}
            </Description>
            <Description term="订仓委托书发出时间(一次确认)" className={styles.info}>
              {data.sureOne}
            </Description>
            <Description term="发货通知 DN NO/ERP NO. (二次确认)" className={styles.info}>
              {data.sureTwo}
            </Description>
          </DescriptionList>
        </Card>
      </Fragment>
    );
  }
}
