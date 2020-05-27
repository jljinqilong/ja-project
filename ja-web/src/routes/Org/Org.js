import React, { PureComponent, Fragment } from 'react';
import { Card, Row, Col, Button, comment, DatePicker, message } from 'antd';
import moment from 'moment';

import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import ModalOrgTree from '../../components/Org/Org/ModalOrgTree';
import ModalEditForm from '../../components/Org/Org/ModalEditForm';
import OrgCharts from '../../components/Org/Org/OrgCharts';
import styles from './Org.less';
import { getOrgHistoryTreeData, backUpInfo } from '../../services/org';
import { hasAccessKey } from '../../utils/authority';

export default class Code extends PureComponent {
  state = {
    rowId: 0,
    isSelectOrg: true,
    display_name: 'none',
    value: '查看历史',
    historyDate: '',
    isLoading: false,
    treeHistoryData: [],
    isShowing: true,
    treeData: [],
    title: '组织架构 管理',
    isOrg: true,
  };

  componentWillMout() {
    this._isMounted = true;
    //根据日期查询
  }
  componentDidUnmount() {
    this._isMounted = false;
  }

  handleClickTreeNode = (selectedKeys, event) => {
    if (selectedKeys.length > 0) {
      this.setState({
        rowId: selectedKeys[0],
      });
      this.setState({ isSelectOrg: true });
      this.setState({ editing: false });
    } else {
      this.setState({ isSelectOrg: false });
      this.setState({ editing: false });
    }
  };

  refreshTreeData = ref => {
    this.child = ref;
  };

  handleRefreshTree = e => {
    this.child.initTreeData();
  };

  handlelickviewhistory = () => {
    if (this.state.display_name === 'none') {
      this.setState({
        display_name: 'block',
        value: '退出',
        isShowing: false,
      });
      if (this.state.historyDate !== '') {
        //根据日期查询
        getOrgHistoryTreeData(this.state.historyDate)
          .then(data => {
            this.setState({
              treeHistoryData: data.data,
              isLoading: false,
            });
          });
      }
    } else {
      this.setState({
        display_name: 'none',
        value: '查看历史',
        isShowing: true,
      });
    }
    if (this.state.value === '查看历史') {
      if (this.state.historyDate === '') {
        message.info('请选择查看的日期');
      } else {
        return 0;
      }
    }
  };

  disabledStartDate = current => {
    return current && current > moment().endOf('day');
  };

  onChangeViewDate = (date, dateString) => {
    this.setState({
      historyDate: dateString,
    });
    if (!!dateString) {
      //根据日期查询
      getOrgHistoryTreeData(dateString)
        .then(data => {
          this.setState({
            treeHistoryData: data.data,
            isLoading: false,
          });
        });
    }
  };

  //备份
  backupOrgData = e => {
    backUpInfo()
      .then(data => {
        message.info(data.msg);
      });
  };

  handleclickviewhierarchy = () => {
    if (!this.state.isOrg) {
      this.setState({
        title:'组织架构 管理',
        isOrg:true,
      });
    } else {
      const org = (
        <span>
          组织架构 层次图<a
            style={{ padding: 20, fontSize: 14 }}
            onClick={() => {
              this.setState({
                title: '组织架构 管理',
                isOrg: true,
              });
            }}
          >
            {' '}
            返回
          </a>{' '}
        </span>
      );
      this.setState({
        title: org,
        isOrg: false,
      });
    }
  };

  render() {
    const { rowId } = this.state;
    const { display_name, isShowing, historyDate } = this.state;
    const { isOrg } = this.state;
    return (
      hasAccessKey('org.org.list') && (
        <PageHeaderLayout title={this.state.title}>
          {isOrg ? (
            <Row gutter={20}>
              <Col span={7} style={{paddingRight: 0}}>
                <Card>
                  <div className={styles.titlePanel}>
                    <span>组织架构</span>
                    <span className={styles.btnPanel}>
                      {hasAccessKey('org.org.history') && (
                        <Button
                          type="button"
                          className={styles.btn_1}
                          onClick={this.handlelickviewhistory.bind(this)}
                        >
                          {this.state.value}
                        </Button>
                      )}
                      {hasAccessKey('org.org.backups') && (
                        <Button
                          type="button"
                          className={styles.btn_1}
                          style={{ display: this.state.isShowing ? '' : 'none' }}
                          onClick={this.backupOrgData.bind(this)}
                        >
                          备份
                        </Button>
                      )}
                      <Button
                        type="button"
                        className={styles.btn_1}
                        onClick={this.handleclickviewhierarchy.bind(this)}
                      >
                        查看层次图
                      </Button>
                    </span>
                  </div>
                  <div className={styles.datepanel} style={{ display: this.state.display_name }}>
                    <span>请选择查看的日期</span>
                    <DatePicker
                      className={styles.datepick}
                      ref={this.getViewDate}
                      onChange={this.onChangeViewDate}
                      disabledDate={this.disabledStartDate}
                      allowClear={false}
                    />{' '}
                    {/*默认选中当前日期  defaultValue={moment().endOf('day')}*/}
                  </div>
                  <ModalOrgTree
                    handleClickTreeNode={this.handleClickTreeNode.bind(this)}
                    refreshTreeData={this.refreshTreeData.bind(this)}
                    treeHistoryData={this.state.treeHistoryData}
                    display_name={display_name}
                    isShowing={isShowing}
                    historyDate={historyDate}
                  />
                </Card>
              </Col>
              <Col span={17}>
                <ModalEditForm
                  rowId={rowId}
                  isSelectOrg={this.state.isSelectOrg}
                  isShowing={isShowing}
                  historyDate={this.state.historyDate}
                  editing={this.editing}
                  treeHistoryData={this.state.treeHistoryData}
                  handleRefreshTree={this.handleRefreshTree.bind(this)}
                />
              </Col>
            </Row>
          ) : (
            <OrgCharts isShowing={isShowing} historyDate={this.state.historyDate} />
          )}
        </PageHeaderLayout>
      )
    );
  }
}
