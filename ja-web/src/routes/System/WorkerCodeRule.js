import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Badge } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/System/WorkerCodeRule/ModalAddForm';
import EditForm from '../../components/System/WorkerCodeRule/ModalEditForm';
import SearchForm from '../../components/System/WorkerCodeRule/SearchForm';

import { delWorkerCodeRule, pageList, getById } from '../../services/systemWorkerCodeRule';
import { hasAccessKey } from '../../utils/authority';
import { delRole } from '../../services/systemRole';

import styles from './WorkerCodeRule.less';
import { baseList } from '../../services/org';

const statusMap = ['success', 'error'];
const statusText = ['正常', '禁用'];

export default class WorkerCodeRule extends PureComponent {
  state = {
    data: [],
    orgBaseList: [],
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
    baseList()
      .then(content => {
        if (!!content && !!content.data) {
          this.setState({ orgBaseList: content.data });
        }
      });
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageList(params)
      .then(response => {
        if (response.data !== undefined) {
          this.setState({
            data: {
              list: response.data.list,
              pagination: {
                total: response.data.total,
              },
            },
            detailData: {},
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
   * 显示/隐藏弹窗
   * @param flag
   */
  handleModalVisible = (flag, type, rowId) => {
    if (type === 0) {
      //新增
      this.setState({
        modalVisibleAdd: !!flag,
      });
    } else if (type === 1) {
      //编辑
      getById(rowId)
        .then(data => {
        this.setState({
          detailData: data.data,
          modalVisibleEdit: !!flag,
          currentEditRoleId: rowId,
        });
      });
    } else if (type === 2) {
      //设置资源
      this.setState({
        modalVisibleResource: !!flag,
        currentResourceRoleId: rowId,
      });
    }
  };

  /**
   * 删除
   * @param rowId
   */
  showDelConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delWorkerCodeRule({
          rowId: rowId,
        })
          .then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              rt();
            } else if (data.code === 400) {
              message.error('删除失败');
            }
          })
          .catch(() => {
            message.error('删除异常');
          });
      },
      onCancel() {},
    });
  };
  /**
   * 批量删除
   * @param rowId
   */
  showBatchDelConfirm = () => {
    const rt = this.refreshTable;
    const roleIds = this.state.selectedRows.join(',');
    Modal.confirm({
      title: '删除确认',
      content: '确定删除吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delRole({
          roleIds,
        })
          .then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              rt();
            } else if (data.code === 400) {
              message.error('删除失败');
            }
          })
          .catch(() => {
            message.error('删除异常');
          });
      },
      onCancel() {},
    });
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '基地名称',
        dataIndex: 'baseId',
      },
      {
        title: '工号前缀',
        dataIndex: 'workerCodePrefix',
      },
      {
        title: '工号长度',
        dataIndex: 'workerCodeLen',
      },
      {
        title: '格式',
        dataIndex: 'format',
      },
      {
        title: '是否可用',
        dataIndex: 'usable',
        filters: [
          {
            text: statusText[0],
            value: 0,
          },
          {
            text: statusText[1],
            value: 1,
          },
        ],
        onFilter: (value, record) => record.usable.toString() === value,
        render(val) {
          return <Badge status={statusMap[val]} text={statusText[val]} />;
        },
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('system.workerCodeRule.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}
            {hasAccessKey('system.workerCodeRule.delete') && (
              <Fragment>
                <Divider type="vertical" />
                <a title="删除" onClick={() => this.showDelConfirm(record.rowId)}>
                  <Icon type="delete" />
                </a>
              </Fragment>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('system.workerCodeRule.list') && (
        <PageHeaderLayout title="工号规则列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm
                  handleSearchTable={this.handleSearchTable.bind(this)}
                  orgBaseList={this.state.orgBaseList}
                />
              </div>
              <div className={styles.tableListOperator}>
                {hasAccessKey('system.workerCodeRule.add') && (
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
                    <Button onClick={this.showBatchDelConfirm}>批量删除</Button>
                  </span>
                )}
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
          <AddForm
            orgBaseList={this.state.orgBaseList}
            modalVisibleAdd={this.state.modalVisibleAdd}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
          {this.state.modalVisibleEdit &&
            <EditForm
              detailData={this.state.detailData}
              modalVisibleEdit={this.state.modalVisibleEdit}
              currentEditRoleId={this.state.currentEditRoleId}
              handleModalVisible={this.handleModalVisible.bind(this)}
              refreshTable={this.refreshTable.bind(this)}
            />
          }
        </PageHeaderLayout>
      )
    );
  }
}
