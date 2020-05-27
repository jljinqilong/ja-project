import React, { PureComponent, Fragment } from 'react';
import { Card, Icon } from 'antd';
import StandardTable from '../../components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import SearchForm from '../../components/System/CodeType/SearchForm';
import ModalEditForm from '../../components/System/CodeType/ModalEditForm';

import {getById, pageList} from '../../services/systemCodeType';
import { hasAccessKey } from '../../utils/authority';
import styles from './CodeType.less';

export default class CodeType extends PureComponent {
  state = {
    data: [],
    modalVisibleEdit: false,
    selectedRows: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    detailData: {},
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();
  }

  /**
   * 刷新表格
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageList(params).then(response => {
      if (response.data !== undefined) {
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
   * 翻页事件
   * @param pagination
   * @param filtersArg
   * @param sorter
   */
  handleStandardTableChange = pagination => {
    const sfvs = this.state.searchFormValues;
    sfvs.pageNum = pagination.current;
    sfvs.pageSize = pagination.pageSize;
    this.setState({
      searchFormValues: sfvs,
    });
    this.refreshTable();
  };

  /**
   * 选择行事件
   * @param rows
   */
  handleSelectRows = rows => {
    this.setState({
      selectedRows: rows,
    });
  };

  /**
   * 显示/隐藏弹窗
   * @param flag
   */
  handleModalVisible = (flag, rowId) => {
    getById(rowId)
      .then(data => {
        this.setState({
          detailData: data.data,
          modalVisibleEdit: !!flag,
          currentEditId: rowId,
      });
    });
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '编码',
        dataIndex: 'code',
      },
      {
        title: '名称',
        dataIndex: 'name',
      },
      {
        title: '描述',
        dataIndex: 'desc',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('system.codetype.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('system.codetype.list') && (
        <PageHeaderLayout title="编码类型列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm handleSearchTable={this.handleSearchTable.bind(this)} />
              </div>
              <StandardTable
                rowKey="rowId"
                selectedRows={selectedRows}
                loading={loading}
                data={this.state.data}
                columns={columns}
                onSelectRow={this.handleSelectRows}
                onChange={this.handleStandardTableChange}
              />
            </div>
          </Card>
          {this.state.modalVisibleEdit && (
          <ModalEditForm
            detailData={this.state.detailData}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditId={this.state.currentEditId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
          )}
        </PageHeaderLayout>
      )
    );
  }
}
