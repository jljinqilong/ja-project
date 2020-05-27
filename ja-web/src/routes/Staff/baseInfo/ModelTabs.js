import React, { PureComponent, Fragement } from 'react';
import { Tabs, Divider, Button } from 'antd';

import RecordIt from '../../../routes/Staff/recordIt/RecordIt.js';
import RecordLang from '../../../routes/Staff/recordLang/RecordLang.js';
import RecordProfession from '../recordProfession/RecordProfession.js';
import Award from '../award/Award.js';
import InternalRecord from '../InternalRecord/InternalRecord';
import Punishment from '../punishment/Punishment.js';
import Disability from '../disability/Disability.js';
import OccInjury from '../occInjury/OccInjury.js';
import ForeignVisa from '../foreign/ForeignVisa.js';
import ForeignReside from '../foreign/ForeignReside.js';

import CompanyRecord from '../companyRecord/CompanyRecord.js';
import ModalEditForm from '../../../components/Staff/BaseInfo/ModalEditForm';
import ModuleLog from './ModuleLog';
import store from '../../../index';

import { routerRedux } from 'dva/router';

export default class ModelTabs extends PureComponent {
  state = {
    staffId: !!this.props.match.params.rowId ? this.props.match.params.rowId : '',
    staffNo: !!this.props.match.params.staffNo ? this.props.match.params.staffNo : '',
  };

  /**
   * 跳转页面
   */
  handleJumpPage = () => {
    const { dispatch } = store;
    dispatch(
      routerRedux.push({
        pathname: `/staff/baseInfo`,
      })
    );
  };

  render() {
    const TabPane = Tabs.TabPane;
    return (
      <div>
        <Button type="primary" onClick={() => this.handleJumpPage()}>
          后退
        </Button>
        <Tabs defaultActiveKey="1">
          <TabPane tab="个人信息" key="1">
            <ModalEditForm rowId={this.state.staffId} staffNo={this.state.staffNo} />
          </TabPane>
          <TabPane tab="内部履历" key="2">
            <InternalRecord staffId={this.state.staffId} />
            <Divider style={{ marginBottom: 32 }} />
          </TabPane>
          <TabPane tab="能力档案" key="3">
            {/*<RecordIt staffId={this.state.staffId} staffNo={this.state.staffNo} />*/}
            {/*<Divider style={{ marginBottom: 32 }} />*/}
            {/*<RecordLang staffId={this.state.staffId} staffNo={this.state.staffNo} />*/}
            {/*<Divider style={{ marginBottom: 32 }} />*/}
            <RecordProfession staffId={this.state.staffId} staffNo={this.state.staffNo} />
          </TabPane>
          <TabPane tab="奖惩信息" key="4">
            <Award staffId={this.state.staffId} staffNo={this.state.staffNo} />
            <Divider style={{ marginBottom: 32 }} />
            <Punishment staffId={this.state.staffId} staffNo={this.state.staffNo} />
          </TabPane>
          {/*<TabPane tab="社保福利" key="5">Content of Tab Pane 3</TabPane>*/}
          <TabPane tab="其他信息" key="6">
            <Disability staffId={this.state.staffId} staffNo={this.state.staffNo} />
            <Divider style={{ marginBottom: 32 }} />
            <OccInjury staffId={this.state.staffId} staffNo={this.state.staffNo} />
          </TabPane>
          <TabPane tab="在华外籍员工信息" key="7">
            <ForeignVisa staffId={this.state.staffId} staffNo={this.state.staffNo} />
            <Divider style={{ marginBottom: 32 }} />
            <ForeignReside staffId={this.state.staffId} staffNo={this.state.staffNo} />
          </TabPane>
          <TabPane tab="公司档案" key="8">
            <CompanyRecord staffId={this.state.staffId} staffNo={this.state.staffNo} />
          </TabPane>
          <TabPane tab="变更记录" key="9">
            <ModuleLog staffId={this.state.staffId} />
          </TabPane>
        </Tabs>
      </div>
    );
  }
}
