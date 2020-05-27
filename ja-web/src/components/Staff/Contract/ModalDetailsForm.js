import React, { PureComponent, Fragment } from 'react';
import { Form, Modal, message, Card, Table } from 'antd';
import { getById } from '../../../services/staffContract';
import { allListLog } from '../../../services/systemModuleLog';
import DescriptionList from '../../../components/DescriptionList';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';
import styles from './Contract.less'

import RelevanceAgreement from '../../../components/Staff/Contract/RelevanceAgreement';

const { Description } = DescriptionList;

class ModalDetailsForm extends PureComponent {
  state = {
    agreementDetails: false,
    staffId: '',
    list: [],
    agreementList: [],
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentDetailsUserId != nextProps.currentDetailsUserId &&
      nextProps.currentDetailsUserId > 0
    ) {
      getById(nextProps.currentDetailsUserId)
        .then(data => {
          if (data.data[0].baseInfo !== undefined) {
            this.setState({
              staffNo: data.data[0].baseInfo.staffNo,
              staffName: data.data[0].baseInfo.staffName,
            });
            if (data.data[0].baseInfo.transNames !== undefined) {
              this.setState({
                baseId: data.data[0].baseInfo.transNames.baseId_baseOrDeptName,
                deptId: data.data[0].baseInfo.transNames.deptId_baseOrDeptName,
                jobStatus: data.data[0].baseInfo.transNames.jobStatus_name,
                positionName: data.data[0].baseInfo.transNames.positionId_positionName,
                rankName: data.data[0].baseInfo.transNames.rankId_rankName,
                gradeName: data.data[0].baseInfo.transNames.gradeId_gradeName,
                contractType: data.data[0].transNames.contractType_name,
                contractPeriodType: data.data[0].transNames.contractPeriodType_name,
                getcontractBackups: data.data[0].transNames.getcontractBackups_name,
                signAgreement: data.data[0].transNames.signAgreement_name,
                contractState: data.data[0].transNames.contractState_name,
                renewStatus: data.data[0].transNames.renewStatus_name,
              });
            }
          }
          this.setState({
            rowId: data.data[0].rowId,
            staffId: data.data[0].staffId,
            contractNo: data.data[0].contractNo,
            contractPeriod: data.data[0].contractPeriod,
            owner: data.data[0].owner,
            file: data.data[0].file,
            describe: data.data[0].describe,
            signDate: data.data[0].signDate,
            contractDateStart: data.data[0].contractDateStart,
            contractDateEnd: data.data[0].contractDateEnd,
            relieveDate: data.data[0].relieveDate,
            relieveReason: data.data[0].relieveReason,
            endDate: data.data[0].endDate,
            endReason: data.data[0].endReason,
            responsiblePerson: data.data[0].responsiblePerson,
            fileList: [],
            agreementList: data.data[0].agreement,
            moduleLog: {
              appCode: 'staff_contract',
              tableId: nextProps.currentDetailsUserId,
            },
          });
          allListLog(this.state.moduleLog)
            .then(data => {
              this.setState({
                list: data.data,
              });
            })
            .catch(e => {
              console.log(e);
            });
        })
        .catch(e => {
          message.error('查询合同信息失败');
        });
    }
  }

  handleModalVisible = (flag, type, agreementNo) => {
    if (type === 1) {
      this.setState({
        agreementDetails: !!flag,
        currentAgreementNo: agreementNo,
      });
    }
  };

  render() {
    const columns = [
      {
        title: '变动类型',
        render: (text, record) => (
          <Fragment>
            {record.optType === 'update' && '修改'}
            {record.optType === 'relieve' && '解除'}
            {record.optType === 'end' && '终止'}
            {record.optType === 'renew' && '续签'}
          </Fragment>
        ),
      },
      {
        title: '变动时间',
        dataIndex: 'optTime',
      },
      {
        title: '变动内容',
        dataIndex: 'optDescribe',
      },
    ];
    const columns1 = [
      {
        title: '协议编号',
        render: (text, record) => (
          <Fragment>
            <a title="详情" onClick={() => this.handleModalVisible(true, 1, record.agreementNo)}>
              {record.agreementNo}
            </a>
          </Fragment>
        ),
      },
      {
        title: '协议类型',
        dataIndex: 'transNames.agreementType_name',
      },
      {
        title: '协议状态',
        dataIndex: 'transNames.agreementState_name',
      },
      {
        title: '协议生效日期',
        dataIndex: 'agreementDateStart',
      },
      {
        title: '协议终止日期',
        dataIndex: 'agreementDateEnd',
      },
    ];
    const data = this.state.list;
    const data1 = this.state.agreementList;
    const token = getToken();
    return (
      <Modal
        title="合同详情"
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
        <Card title={'合同基础信息'} bordered={false}>
          <DescriptionList size="large"col={2} style={{ marginBottom: 32 }}>
            <Description term="合同编号" className={styles.info}>{this.state.contractNo}</Description>
            <Description term="合同类型" className={styles.info}>{this.state.contractType}</Description>
            <Description term="合同期限类型" className={styles.info}>{this.state.contractPeriodType}</Description>
            <Description term="签订日期" className={styles.info}>{this.state.signDate}</Description>
            <Description term="合同生效日期" className={styles.info}>{this.state.contractDateStart}</Description>
            <Description term="合同终止日期" className={styles.info}>{this.state.contractDateEnd}</Description>
            <Description term="合同期限(月)" className={styles.info}>{this.state.contractPeriod}</Description>
            <Description term="甲方" className={styles.info}>{this.state.owner}</Description>
            <Description term="领取合同备份" className={styles.info}>{this.state.getcontractBackups}</Description>
            <Description term="签订相关协议" className={styles.info}>{this.state.signAgreement}</Description>
            <Description term="合同状态" className={styles.info}>{this.state.contractState}</Description>
            <Description term="续签状态" className={styles.info}>{this.state.renewStatus}</Description>
            <Description term="附件" className={styles.info}>{this.state.file !== undefined && this.state.file !== '' && (
              <a href={`${serverUrlPre}/system/file/download?filePath=${this.state.file}&token=${token}`} title={'附件下载'} alt="file">附件下载</a>)}
            </Description>
            <Description term="描述" className={styles.info}>{this.state.describe}</Description>
          </DescriptionList>
        </Card>
        {this.state.contractState === '4' && (
          <Card title={'解除信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="解除日期" className={styles.info}>{this.state.relieveDate}</Description>
              <Description term="解除原因" className={styles.info}>{this.state.relieveReason}</Description>
              <Description term="经办人" className={styles.info}>{this.state.responsiblePerson}</Description>
            </DescriptionList>
          </Card>
        )}
        {this.state.contractState === '5' && (
          <Card title={'终止信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="终止日期" className={styles.info}>{this.state.endDate}</Description>
              <Description term="终止原因" className={styles.info}>{this.state.endReason}</Description>
              <Description term="经办人" className={styles.info}>{this.state.responsiblePerson}</Description>
            </DescriptionList>
          </Card>
        )}
        <Card title={'关联协议详情'} bordered={false}>
          <Table columns={columns1} dataSource={data1} size="small" bordered pagination={false} />
        </Card>
        <Card title={'合同变动历史'} bordered={false}>
          <Table columns={columns} dataSource={data} size="small" bordered pagination={false} />
        </Card>
        <RelevanceAgreement
          agreementDetails={this.state.agreementDetails}
          currentAgreementNo={this.state.currentAgreementNo}
          handleModalVisible={this.handleModalVisible.bind(this)}
        />
      </Modal>
    );
  }
}

export default Form.create({})(ModalDetailsForm);
