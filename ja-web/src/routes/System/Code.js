import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Badge, Divider } from 'antd';
import StandardTable from '../../components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import SearchForm from '../../components/System/Code/SearchForm';
import ModalAddForm from '../../components/System/Code/ModalAddForm';
import ModalEditForm from '../../components/System/Code/ModalEditForm';

import { allList } from '../../services/systemCodeType';
import { pageList, del, change, getById } from '../../services/systemCode';

import styles from './Code.less';
import { hasAccessKey } from '../../utils/authority';

const statusMap = ['success', 'error'];

const statusText = ['正常', '禁用'];

export default class Code extends PureComponent {
  state = {
    data: {},
    codeTypeAllList: [],
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
    allList()
      .then(content => {
        this.setState({ codeTypeAllList: content.data });
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
        console.log(response)
        console.log(response.data)
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
   * 删除
   * @param rowId
   */
  showDelConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此编码吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        del({
          codeIds: rowId,
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
    const codeIds = this.state.selectedRows.join(',');
    Modal.confirm({
      title: '删除确认',
      content: '确定删除吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        del({
          codeIds,
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
   * 启用/禁用
   * @param rowId
   */
  showChangeConfirm = (rowId, status) => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '确认',
      content: '确定此操作吗？',
      okText: '确定',
      cancelText: '取消',
      onOk() {
        change({
          codeIds: rowId,
          status: status,
        })
          .then(data => {
            if (data.code === 200) {
              message.success('成功');
              rt();
            } else if (data.code === 400) {
              message.error('失败');
            }
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
        title: '编码',
        dataIndex: 'code',
      },
      {
        title: '名称',
        dataIndex: 'name',
      },
      {
        title: '序号',
        dataIndex: 'orderNo',
      },
      {
        title: '类型',
        dataIndex: 'transNames.typeId_name',
      },
      {
        title: '状态',
        dataIndex: 'status',
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
        onFilter: (value, record) => record.status.toString() === value,

        render(val) {
          return <Badge status={statusMap[val]} text={statusText[val]} />;
        },
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('system.code.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey('system.code.delete') && (
              <a title="删除" onClick={() => this.showDelConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey('system.code.enabled') &&
              (record.status === 0 && (
                <a title="禁用" onClick={() => this.showChangeConfirm(record.rowId, 1)}>
                  <Icon type="lock" />
                </a>
              ))}
            {hasAccessKey('system.code.enabled') &&
              (record.status === 1 && (
                <a title="启用" onClick={() => this.showChangeConfirm(record.rowId, 0)}>
                  <Icon type="unlock" />
                </a>
              ))}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('system.code.list') && (
        <PageHeaderLayout title="编码规则列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm
                  codeTypeAllList={this.state.codeTypeAllList}
                  handleSearchTable={this.handleSearchTable.bind(this)}
                />
              </div>
              <div className={styles.tableListOperator}>
                {hasAccessKey('system.code.add') && (
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
          <ModalAddForm
            codeTypeAllList={this.state.codeTypeAllList}
            modalVisibleAdd={this.state.modalVisibleAdd}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
          {this.state.modalVisibleEdit && (
            <ModalEditForm
              detailData={this.state.detailData}
              codeTypeAllList={this.state.codeTypeAllList}
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
