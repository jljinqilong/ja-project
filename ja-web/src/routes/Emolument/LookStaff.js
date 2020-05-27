import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Table } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import SearchForm from '../../components/Emolument/AccumulationFund/SearchForm';

import {
  delAccumulationFund,
  pageList,
  getById,
  beachDel,
  empty,
  exportExcel,
} from '../../services/eltAccumulationFund';

import styles from './AccumulationFund.less';

export default class AccumulationFund extends PureComponent {
  state = {
    rowId: !!this.props.match.params.rowId ? this.props.match.params.rowId : '',
    data: [],
    orgBaseList: [],
    selectedRows: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();
    getById(this.state.rowId).then(data => {
      this.setState({
        infoDetail: data.data,
      });
    });
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageList(params).then(response => {
      if (!!response.data) {
        this.setState({
          data: {
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
   * 搜索
   * @param params
   */
  handleSearchTable = params => {
    const sfvs = this.state.searchFormValues;
    params.pageNum = sfvs.pageNum;
    params.pageSize = sfvs.pageSize;
    this.setState({
      searchFormValues: params,
    });
    this.refreshTable(params);
  };

  /**
   * 删除公积金
   * @param rowId
   */
  showDelAccumulationConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此公积金吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delAccumulationFund(rowId).then(data => {
          if (data.code === 200) {
            message.success('删除成功');
            rt();
          } else if (data.code === 400) {
            message.error('删除失败');
          }
        });
      },
      onCancel() {},
    });
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const obj = [this.state.infoDetail];
    const columns = [
      {
        title: '基地111',
        dataIndex: 'baseId',
      },
      {
        title: '规则名称',
        dataIndex: 'ruleName',
      },
      {
        title: '缴费基数',
        dataIndex: 'socialInsuranceBase',
      },
      {
        title: '个人比例',
        dataIndex: 'personalProportion',
      },
      {
        title: '个人金额',
        dataIndex: 'personalAmount',
      },
      {
        title: '公司比例',
        dataIndex: 'companyRatio',
      },
      {
        title: '公司金额',
        dataIndex: 'companyAmount',
      },
      {
        title: '个人补充比例',
        dataIndex: 'personalSupplementaryRatio',
      },
      {
        title: '个人补充金额',
        dataIndex: 'personalSupplementaryAmount',
      },
      {
        title: '公司补充比例',
        dataIndex: 'companySupplementaryProportion',
      },
      {
        title: '公司补充金额',
        dataIndex: 'companySupplementaryAmount',
      },
      {
        title: '备注',
        dataIndex: 'remarks',
      },
    ];

    return (
      <PageHeaderLayout title="公积金列表">
        <div>
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm
                  orgBaseList={this.state.orgBaseList}
                  handleSearchTable={this.handleSearchTable.bind(this)}
                />
              </div>
              <div className={styles.tableListOperator}>
                <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                  新建
                </Button>
              </div>
              <StandardTable
                rowKey="rowId"
                scroll={{ x: 1600, y: 0 }}
                selectedRows={selectedRows}
                loading={loading}
                data={this.state.data}
                columns={columns}
                onSelectRow={this.handleSelectRows}
                onChange={this.handleStandardTableChange}
              />
              <Table dataSource={obj} columns={columns} />
            </div>
          </Card>
        </div>
      </PageHeaderLayout>
    );
  }
}
