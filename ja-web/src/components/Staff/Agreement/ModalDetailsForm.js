import React, {PureComponent,Fragment} from 'react';
import {Form, Modal, message, Card, Table} from 'antd';
import {getById} from '../../../services/agreement';
import {allListLog} from '../../../services/systemModuleLog';
import DescriptionList from '../../../components/DescriptionList';
import {serverUrlPre} from "../../../utils/request";
import {getToken} from "../../../utils/authority";
import styles from './Agreement.less';
const { Description } = DescriptionList;

class ModalDetailsForm extends PureComponent {
  state = {
    staffId: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps){
    if (this.props.currentDetailsUserId != nextProps.currentDetailsUserId && nextProps.currentDetailsUserId > 0
    ) {
      getById(nextProps.currentDetailsUserId).then(data => {
        if(data.data[0].baseInfo !== undefined){
          this.setState({
            staffNo: data.data[0].baseInfo.staffNo,
            staffName: data.data[0].baseInfo.staffName,
          })
          if(data.data[0].baseInfo.transNames !== undefined){
            this.setState({
              baseId: data.data[0].baseInfo.transNames.baseId_baseOrDeptName,
              deptId: data.data[0].baseInfo.transNames.deptId_baseOrDeptName,
              jobStatus: data.data[0].baseInfo.transNames.jobStatus_name,
              positionName: data.data[0].baseInfo.transNames.positionId_positionName,
              rankName: data.data[0].baseInfo.transNames.rankId_rankName,
              gradeName: data.data[0].baseInfo.transNames.gradeId_gradeName,
              agreementType:data.data[0].transNames.agreementType_name,
              agreementState: data.data[0].transNames.agreementState_name,
              renewStatus: data.data[0].transNames.renewStatus_name,
            })

          }
        }
        this.setState({
          rowId: data.data[0].rowId,
          staffId: data.data[0].staffId,
          agreementNo:data.data[0].agreementNo,
          relevanceContract:data.data[0].relevanceContract,
          agreementPeriod: data.data[0].agreementPeriod,
          owner:data.data[0].owner,
          file:data.data[0].file,
          describe:data.data[0].describe,
          signDate:data.data[0].signDate,
          agreementDateStart:data.data[0].agreementDateStart,
          agreementDateEnd:data.data[0].agreementDateEnd,
          relieveDate: data.data[0].relieveDate,
          relieveReason: data.data[0].relieveReason,
          endDate: data.data[0].endDate,
          endReason: data.data[0].endReason,
          responsiblePerson: data.data[0].responsiblePerson,
          fileList: [],
          moduleLog: {
            appCode: 'staff_agreement',
            tableId: nextProps.currentDetailsUserId,
          },
        });
        allListLog(this.state.moduleLog).then(data => {
          this.setState({
            list:data.data,
          })
        })
          .catch(e => {
            console.log(e);
          });
      }).catch((e) => {
        message.error('查询协议信息失败');
      });
    }
  }


  render() {
    const columns = [{
      title: '变动类型',
      render: (text, record) => (
        <Fragment>
          {record.optType === 'update' && "修改"}
          {record.optType === 'relieve' && "解除"}
          {record.optType === 'end' && "终止"}
          {record.optType === 'renew' && "续签"}
        </Fragment>
      )
    },
      {
        title: '变动时间',
        dataIndex: 'optTime',
      },
      {
        title: '变动内容',
        dataIndex: 'optDescribe',
      }];
    const data = this.state.list;
    const token = getToken();
    return (
      <Modal
        title="协议详情"
        visible={this.props.modalDetails}
        width={1200}
        footer={null}
        onCancel={() => this.props.handleModalVisible(false, 5, -1)}
      >
        <Card title={'员工基础信息'} bordered={false}>
          <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
            <Description term="工号" className={styles.info}>{this.state.staffNo}</Description>
            <Description term="姓名" className={styles.info}>{this.state.staffName}</Description>
            <Description term="基地" className={styles.info}>{this.state.baseId}</Description>
            <Description term="部门" className={styles.info}>{this.state.deptId}</Description>
            <Description term="职衔" className={styles.info}>{this.state.positionName}</Description>
            <Description term="职级" className={styles.info}>{this.state.rankName}</Description>
            <Description term="职等/赋值名称" className={styles.info}>{this.state.gradeName}</Description>
            <Description term="在职状态" className={styles.info}>{this.state.jobStatus}</Description>
          </DescriptionList>
        </Card>
        <Card title={'协议基础信息'} bordered={false}>
          <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
            <Description term="协议编号" className={styles.info}>{this.state.agreementNo}</Description>
            <Description term="关联合同编号" className={styles.info}>{this.state.relevanceContract}</Description>
            <Description term="协议类型" className={styles.info}>{this.state.agreementType}</Description>
            <Description term="签订日期" className={styles.info}>{this.state.signDate}</Description>
            <Description term="协议生效日期" className={styles.info}>{this.state.agreementDateStart}</Description>
            <Description term="协议终止日期" className={styles.info}>{this.state.agreementDateEnd}</Description>
            <Description term="协议期限(月)" className={styles.info}>{this.state.agreementPeriod}</Description>
            <Description term="签订单位" className={styles.info}>{this.state.owner}</Description>
            <Description term="协议状态" className={styles.info}>{this.state.agreementState}</Description>
            <Description term="续签状态" className={styles.info}>{this.state.renewStatus}</Description>
            <Description term="附件" className={styles.info}>{this.state.file !== undefined && this.state.file !== '' && (
              <a href={`${serverUrlPre}/system/file/download?filePath=${this.state.file}&token=${token}`} title={'附件下载'} alt="file" >附件下载</a>)}</Description>
            <Description term="描述" className={styles.info}>{this.state.describe}</Description>
          </DescriptionList>
        </Card>
        {this.state.agreementState === '4' && (
          <Card title={'解除信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="解除日期" className={styles.info}>{this.state.relieveDate}</Description>
              <Description term="解除原因" className={styles.info}>{this.state.relieveReason}</Description>
              <Description term="经办人" className={styles.info}>{this.state.responsiblePerson}</Description>
            </DescriptionList>
          </Card>
        )}
        {this.state.agreementState === '5' && (
          <Card title={'终止信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="终止日期" className={styles.info}>{this.state.endDate}</Description>
              <Description term="终止原因" className={styles.info}>{this.state.endReason}</Description>
              <Description term="经办人" className={styles.info}>{this.state.responsiblePerson}</Description>
            </DescriptionList>
          </Card>
        )}
        <Card title={'协议变动历史'} bordered={false}>
          <Table columns={columns} dataSource={data} size="small" bordered pagination={false}/>
        </Card>
      </Modal>
    );
  }
}

export default Form.create({})(ModalDetailsForm);
