import React, { PureComponent, Fragment } from 'react';
import { Button, Card, Tabs } from 'antd';
import { getById } from '../../../services/staffBaseInfo';
import { routerRedux } from 'dva/router';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import styles from './AdjustWork.less';
import DescriptionList from '../../../components/DescriptionList/index';
import ModalInternalTransfer from '../../../components/Staff/AdjustWork/ModalInternalTransfer';
import ModalToLoan from '../../../components/Staff/AdjustWork/ModalToLoan';
import ModalExpatriate from '../../../components/Staff/AdjustWork/ModalExpatriate';
import ModalRetire from '../../../components/Staff/AdjustWork/ModalRetire';
import ModalDimission from '../../../components/Staff/AdjustWork/ModalDimission';
import ModalRehire from '../../../components/Staff/AdjustWork/ModalRehire';
import ModalReturnRehire from '../../../components/Staff/AdjustWork/ModalReturnRehire';

import ModalInternalTransferList from '../../../components/Staff/AdjustWork/ModalInternalTransferList';
import ModalToLoanList from '../../../components/Staff/AdjustWork/ModalToLoanList';
import ModalToLoanListHandle from '../../../components/Staff/AdjustWork/ModalToLoanListHandle';
import ModalExpatriateList from '../../../components/Staff/AdjustWork/ModalExpatriateList';
import ModalExpatriateListHandle from '../../../components/Staff/AdjustWork/ModalExpatriateListHandle';
import ModalRetireList from '../../../components/Staff/AdjustWork/ModalRetireList';
import ModalDimissionList from '../../../components/Staff/AdjustWork/ModalDimissionList';
import ModalRehireList from '../../../components/Staff/AdjustWork/ModalRehireList';
import ModalReturnRehireList from '../../../components/Staff/AdjustWork/ModalReturnRehireList';
import store from '../../../index';
const { Description } = DescriptionList;

export default class ModalEditForm extends PureComponent {
  state = {
    staffId: !!this.props.match.params.rowId ? this.props.match.params.rowId : '',
    type: !!this.props.match.params.type ? this.props.match.params.type : '',
    rowIdList: '',
    deptId: '',
    gradeId: '',
    rankId: '',
  };

  /**
   * 接收ID，查询详细
   */
  componentDidMount() {
    if (this.state.staffId > 0) {
      this.getStaffInfoById(this.state.staffId);
    }
  }

  /**
   * 查询用户信息
   */
  getStaffInfoById = staffId => {
    getById(staffId).then(data => {
      this.setState({
        rowId: data.data.rowId,
        staffNo: data.data.staffNo,
        staffName: data.data.staffName,
        baseId: data.data.baseId,
        baseId_baseOrDeptName: data.data.transNames.baseId_baseOrDeptName,
        deptId: data.data.deptId,
        deptId_baseOrDeptName: data.data.transNames.deptId_baseOrDeptName,
        positionName: data.data.transNames.positionId_positionName,
        rankName: data.data.transNames.rankId_rankName,
        gradeName: data.data.transNames.gradeId_gradeName,
        jobStatus: data.data.jobStatus,
        jobStatus_name: data.data.transNames.jobStatus_name,
      });
    });
  };

  /**
   * 跳转页面
   */
  handleJumpPage = url => {
    const { dispatch } = store;
    dispatch(
      routerRedux.push({
        pathname: url,
      })
    );
  };

  render() {
    const { type } = this.state;
    const TabPane = Tabs.TabPane;
    const action = (
      <Fragment>
        <Button type="danger" onClick={() => this.handleJumpPage('/staff/transaction')}>
          返回
        </Button>
      </Fragment>
    );

    return (
      <PageHeaderLayout title="异动处理">
        <Card title={'基础信息'} bordered={false} extra={action}>
          <DescriptionList size="samll" col={2} style={{ marginBottom: 32, width: 1500 }}>
            <Description term="工号" className={styles.info}>{this.state.staffNo}</Description>
            <Description term="姓名" className={styles.info}>{this.state.staffName}</Description>
            <Description term="基地" className={styles.info}>{this.state.baseId_baseOrDeptName}</Description>
            <Description term="部门" className={styles.info}>{this.state.deptId_baseOrDeptName}</Description>
            <Description term="职衔" className={styles.info}>{this.state.positionName}</Description>
            <Description term="职等/赋值名称" className={styles.info}>{this.state.gradeName}</Description>
            <Description term="职级" className={styles.info}>{this.state.rankName}</Description>
            <Description term="在职状态" className={styles.info}>{this.state.jobStatus_name}</Description>
          </DescriptionList>
          {(type === '1' && (
            <Tabs defaultActiveKey="1" type="card">
              <TabPane tab="内部调动" key="1">
                <ModalInternalTransfer
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
              <TabPane tab="借调" key="2">
                <ModalToLoan
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  newDeptId={this.state.deptId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
              <TabPane tab="外派" key="3">
                <ModalExpatriate
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  newDeptId={this.state.deptId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
              <TabPane tab="退休" key="4">
                <ModalRetire
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  newDeptId={this.state.deptId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
              <TabPane tab="离职" key="5">
                <ModalDimission
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  newDeptId={this.state.deptId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
              <TabPane tab="重新雇佣" key="6">
                <ModalRehire
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
              <TabPane tab="返聘" key="7">
                <ModalReturnRehire
                  getStaffInfoById={this.getStaffInfoById.bind(this)}
                  staffId={this.state.rowId}
                  originalStaffNo={this.state.staffNo}
                  originalBase={this.state.baseId_baseOrDeptName}
                  originalDept={this.state.deptId_baseOrDeptName}
                  originalPosition={this.state.positionName}
                  originalGrade={this.state.gradeName}
                  originalRank={this.state.rankName}
                  jobStatus={this.state.jobStatus_name}
                />
              </TabPane>
            </Tabs>
          )) ||
            (type === '2' && (
              <Tabs defaultActiveKey="2" type="card">
                {/*<TabPane tab="内部调动" key="1">*/}
                  {/*<ModalInternalTransferList*/}
                    {/*staffId={this.state.staffId}*/}
                  {/*/>*/}
                {/*</TabPane>*/}
                <TabPane tab="借调" key="2">
                  <ModalToLoanListHandle
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="外派" key="3">
                  <ModalExpatriateListHandle
                    staffId={this.state.staffId}
                  />
                </TabPane>
              </Tabs>
            )) ||
            (type === '3' && (
              <Tabs defaultActiveKey="1" type="card">
                <TabPane tab="内部调动" key="1">
                  <ModalInternalTransferList
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="借调" key="2">
                  <ModalToLoanList
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="外派" key="3">
                  <ModalExpatriateList
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="退休" key="4">
                  <ModalRetireList
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="离职" key="5">
                  <ModalDimissionList
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="重新雇佣" key="6">
                  <ModalRehireList
                    staffId={this.state.staffId}
                  />
                </TabPane>
                <TabPane tab="返聘" key="7">
                  <ModalReturnRehireList
                    staffId={this.state.staffId}
                  />
                </TabPane>
              </Tabs>
            ))}
        </Card>
      </PageHeaderLayout>
    );
  }
}
