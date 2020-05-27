import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/Org/Rank/ModalAddForm';
import EditForm from '../../components/Org/Rank/ModalEditForm';
import SearchForm from '../../components/Org/Rank/SearchForm';

import { delRank, getById } from '../../services/rank';
import { hasAccessKey } from '../../utils/authority';

import styles from './Rank.less';

@connect(({ rank, loading }) => ({
  rank,
  loading: loading.models.rank,
}))
export default class Rank extends PureComponent {
  state = {
    modalVisibleAdd: false,
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
   * 请求数据
   */
  refreshTable = params => {
    const { dispatch } = this.props;
    if (!params) {
      params = this.state.searchFormValues;
    }
    dispatch({
      type: 'rank/getPageList',
      payload: params,
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
  handleModalVisible = (flag, type, rowId) => {
    if (type === 0) {
      this.setState({
        modalVisibleAdd: !!flag,
      });
    } else if (type === 1) {
      getById(rowId)
        .then(data => {
          if (data.code === 200) {
            this.setState({
              detailData: data.data,
              modalVisibleEdit: !!flag,
              currentEditUserId: rowId,
            });
          }
        });
    }
  };

  /**
   * 删除职级
   * @param rowId
   */
  showDelUserConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此职级吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delRank(rowId)
          .then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              rt();
            }
          });
      },
      onCancel() {},
    });
  };

  render() {
    const { rank, loading } = this.props;
    const { selectedRows } = this.state;
    const { data } = rank;
    const columns = [
      {
        title: '职级名称',
        dataIndex: 'rankName',
      },
      {
        title: '职级描述',
        dataIndex: 'rankDesc',
      },
      {
        title: '薪资上限',
        dataIndex: 'salaryMax',
      },
      {
        title: '薪资下限',
        dataIndex: 'salaryMin',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('org.rank.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey('org.rank.delete') && (
              <a title="删除" onClick={() => this.showDelUserConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('org.rank.list') && (
        <PageHeaderLayout title="职级列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm handleSearchTable={this.handleSearchTable.bind(this)} />
              </div>
              <div className={styles.tableListOperator}>
                {hasAccessKey('org.rank.add') && (
                  <Button
                    icon="plus"
                    type="primary"
                    onClick={() => this.handleModalVisible(true, 0)}
                  >
                    新建
                  </Button>
                )}
                {selectedRows.length > 0 && (
                  <span>
                    <Button>批量删除</Button>
                  </span>
                )}
              </div>
              <StandardTable
                rowKey="rowId"
                selectedRows={selectedRows}
                loading={loading}
                data={data}
                columns={columns}
                onSelectRow={this.handleSelectRows}
                onChange={this.handleStandardTableChange}
              />
            </div>
          </Card>
          <AddForm
            modalVisibleAdd={this.state.modalVisibleAdd}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
          {this.state.modalVisibleEdit ? (
            <EditForm
              detailData={this.state.detailData}
              modalVisibleEdit={this.state.modalVisibleEdit}
              currentEditUserId={this.state.currentEditUserId}
              handleModalVisible={this.handleModalVisible.bind(this)}
              refreshTable={this.refreshTable.bind(this)}
            />
          ) : (
            ''
          )}
        </PageHeaderLayout>
      )
    );
  }
}
