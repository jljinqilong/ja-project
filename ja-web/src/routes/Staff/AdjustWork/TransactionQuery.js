import React, { PureComponent, Fragment } from 'react';
import { Card, Tabs, Row, Col } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import ModalInternalTransferList from '../../../components/Staff/AdjustWorkQuery/ModalInternalTransferList';
import ModalToLoanList from '../../../components/Staff/AdjustWorkQuery/ModalToLoanList';
import ModalExpatriateList from '../../../components/Staff/AdjustWorkQuery/ModalExpatriateList';
import ModalRetireList from '../../../components/Staff/AdjustWorkQuery/ModalRetireList';
import ModalDimissionList from '../../../components/Staff/AdjustWorkQuery/ModalDimissionList';
import ModalRehireList from '../../../components/Staff/AdjustWorkQuery/ModalRehireList';
import ModalReturnRehireList from '../../../components/Staff/AdjustWorkQuery/ModalReturnRehireList';

import DescriptionList from '../../../components/DescriptionList/index';
import ModalOrgTree from '../../../components/Org/Org/ModalOrgTree';
import { queryAdjustmentWorkByChangeType } from '../../../services/adjustWork';
import { hasAccessKey } from '../../../utils/authority';

const { Description } = DescriptionList;

export default class ModalEditForm extends PureComponent {
  state = {
    tabKey: 'INNER_MOBILIZATION',
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    innData: [],
    temData: [],
    expData: [],
    retData: [],
    dimData: [],
    rehData: [],
    returnData: [],
  };

  /**
   * 接收ID，查询详细
   */

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    params.changeType = this.state.tabKey;
    queryAdjustmentWorkByChangeType(params)
      .then(response => {
        if (response.data !== undefined) {
          if (this.state.tabKey === 'INNER_MOBILIZATION') {
            this.setState({
              innData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
          if (this.state.tabKey === 'TEMPORARILY') {
            this.setState({
              temData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
          if (this.state.tabKey === 'EXPATRIATE') {
            this.setState({
              expData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
          if (this.state.tabKey === 'RETIRE') {
            this.setState({
              retData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
          if (this.state.tabKey === 'DIMISSION') {
            this.setState({
              dimData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
          if (this.state.tabKey === 'REHIRE') {
            this.setState({
              rehData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
          if (this.state.tabKey === 'RETURN_REHIRE') {
            this.setState({
              returnData: {
                list: response.data.list,
                pagination: {
                  total: response.data.total,
                },
              },
            });
          }
        }
      });
  };

  /**
   * 选择组织架构树回调
   * @param selectedKeys
   */
  handleClickTreeNode = selectedKeys => {
    this.child.handleFormReset();
    if (selectedKeys.length > 0) {
      const sfvs = this.state.searchFormValues;
      sfvs.deptId = selectedKeys[0];
      this.setState({
        deptId: selectedKeys[0],
        searchFormValues: sfvs,
      });
      this.refreshTable();
    } else {
      const sfvs = this.state.searchFormValues;
      sfvs.deptId = null;
      this.setState({
        searchFormValues: sfvs,
      });
      this.refreshTable();
    }
  };

  refreshTreeData = ref => {
    this.child = ref;
  };

  callback = key => {
    console.log('回调');
    this.state.tabKey = key;
    this.refreshTable();
  };

  render() {
    const TabPane = Tabs.TabPane;

    return (
      hasAccessKey('staff.transactionQuery') && (
      <PageHeaderLayout title="异动查询">
        <Row gutter={20}>
          <Col span={7} style={{ paddingRight: 0 }}>
            <Card>
              <ModalOrgTree
                handleClickTreeNode={this.handleClickTreeNode.bind(this)}
              />
            </Card>
          </Col>
          <Col span={17}>
            <Card>
              <Tabs type="card" onChange={this.callback}>
                {hasAccessKey('staff.transactionQuery.INNER_MOBILIZATION') && (
                  <TabPane tab="内部调动" key="INNER_MOBILIZATION">
                    <ModalInternalTransferList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      innData={this.state.innData}
                    />
                  </TabPane>
                )}
                {hasAccessKey('staff.transactionQuery.TEMPORARILY') && (
                  <TabPane tab="借调" key="TEMPORARILY">
                    <ModalToLoanList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      temData={this.state.temData}
                    />
                  </TabPane>
                )}
                {hasAccessKey('staff.transactionQuery.EXPATRIATE') && (
                  <TabPane tab="外派" key="EXPATRIATE">
                    <ModalExpatriateList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      expData={this.state.expData}
                    />
                  </TabPane>
                )}
                {hasAccessKey('staff.transactionQuery.RETIRE') && (
                  <TabPane tab="退休" key="RETIRE">
                    <ModalRetireList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      retData={this.state.retData}
                    />
                  </TabPane>
                )}
                {hasAccessKey('staff.transactionQuery.DIMISSION') && (
                  <TabPane tab="离职" key="DIMISSION">
                    <ModalDimissionList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      dimData={this.state.dimData}
                    />
                  </TabPane>
                )}
                {hasAccessKey('staff.transactionQuery.REHIRE') && (
                  <TabPane tab="重新雇佣" key="REHIRE">
                    <ModalRehireList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      rehData={this.state.rehData}
                    />
                  </TabPane>
                )}
                {hasAccessKey('staff.transactionQuery.RETURN_REHIRE') && (
                  <TabPane tab="返聘" key="RETURN_REHIRE">
                    <ModalReturnRehireList
                      refreshTable={this.refreshTable}
                      refreshTreeData={this.refreshTreeData.bind(this)}
                      deptId={this.state.deptId}
                      returnData={this.state.returnData}
                    />
                  </TabPane>
                )}
              </Tabs>
            </Card>
          </Col>
        </Row>
      </PageHeaderLayout>
      )
    );
  }
}
