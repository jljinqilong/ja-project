import React, { PureComponent } from 'react';
import { Card, Tabs, Row, Col } from 'antd';
import Contract from './Contract';
import Agreement from '../agreement/Agreement';
import ModalOrgTree from '../../../components/Org/Org/ModalOrgTree';
import { hasAccessKey } from '../../../utils/authority';
import { pageListAgreement } from '../../../services/agreement';
import { pageListContract } from '../../../services/staffContract';
export default class ContractTabs extends PureComponent {
  state = {
    contractData: [],
    agreementData: [],
    tabKey: '1',
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
  };

  /**
   * 请求数据
   */
  refreshTableContract = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageListContract(params).then(response => {
      if (response.data !== undefined) {
        this.setState({
          contractData: {
            list: response.data.list,
            pagination: {
              total: response.data.total,
            },
          },
        });
      }
    });
  };

  /**
   * 请求数据
   */
  refreshTableAgreement = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageListAgreement(params).then(response => {
      if (response.data !== undefined) {
        this.setState({
          agreementData: {
            list: response.data.list,
            pagination: {
              total: response.data.total,
            },
          },
        });
      }
    });
  };

  /**
   * 选择组织架构树回调
   * @param selectedKeys
   */
  handleClickTreeNode = selectedKeys => {
    if (selectedKeys.length > 0) {
      const sfvs = this.state.searchFormValues;
      sfvs.deptId = selectedKeys[0];
      this.setState({
        deptId: selectedKeys[0],
        searchFormValues: sfvs,
      });
      if (this.state.tabKey === '1') {
        this.refreshTableContract();
      }
      if (this.state.tabKey === '2') {
        this.refreshTableAgreement();
      }
    } else {
      const sfvs = this.state.searchFormValues;
      sfvs.deptId = null;
      this.setState({
        searchFormValues: sfvs,
      });
      if (this.state.tabKey === '1') {
        this.refreshTableContract();
      }
      if (this.state.tabKey === '2') {
        this.refreshTableAgreement();
      }
    }
  };

  callback = key => {
    this.setState({
      tabKey: key,
    });
    if (key === '1') {
      this.refreshTableContract();
    }
    if (key === '2') {
      this.refreshTableAgreement();
    }
  };

  render() {
    const TabPane = Tabs.TabPane;
    return (
      <Row gutter={20}>
        <Col span={7} style={{ paddingRight: 0 }}>
          <Card>
            <ModalOrgTree
              handleClickTreeNode={this.handleClickTreeNode.bind(this)}
              refreshTable={this.refreshTableContract.bind(this)}
            />
          </Card>
        </Col>
        <Col span={17}>
          <Card>
            <Tabs
              onChange={this.callback}
              defaultActiveKey={
                hasAccessKey('staff.contract.list')
                  ? '1'
                  : hasAccessKey('staff.agreement.list')
                    ? '2'
                    : ''
              }
            >
              {hasAccessKey('staff.contract.list') && (
                <TabPane tab="" key="1">
                  <Contract
                    refreshTableContract={this.refreshTableContract}
                    deptId={this.state.deptId}
                    contractData={this.state.contractData}
                  />
                </TabPane>
              )}
              {hasAccessKey('staff.agreement.list') && (
                <TabPane tab="协议列表" key="2">
                  <Agreement
                    refreshTableAgreement={this.refreshTableAgreement}
                    deptId={this.state.deptId}
                    agreementData={this.state.agreementData}
                  />
                </TabPane>
              )}
            </Tabs>
          </Card>
        </Col>
      </Row>
    );
  }
}
