import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Badge, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/System/Role/ModalAddForm';
import EditForm from '../../components/System/Role/ModalEditForm';
import SearchForm from '../../components/System/Role/SearchForm';
import ModalResourceTree from '../../components/System/Role/ModalResourceTree';
import ModalOrgDataAuthForm from '../../components/System/Role/ModalOrgDataAuthForm'

import {delRole, changeStatus, pageList, getById, searchRoleAuthorityData} from '../../services/systemRole';

import styles from './Role.less';
import { hasAccessKey } from '../../utils/authority';

const statusMap = ['success', 'error'];

const statusText = ['正常', '禁用'];

export default class User extends PureComponent {
  state = {
    data: [],
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    modalVisibleResource: false,
    modalVisibleData: false,
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
      this.setState({
      });
    } else if (type === 2) {
      //设置操作权限
      this.setState({
        modalVisibleResource: !!flag,
        currentResourceRoleId: rowId,
      });
    } else if (type === 3) {
      //设置操作权限
      if(rowId != -1){

      }
      this.setState({
        modalVisibleData: !!flag,
        currentDataRoleId: rowId,
      });
    }
  };

  /**
   * 启用/禁用
   * @param rowId
   */
  showChangeConfirm = (rowId, status) => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '确认',
      content: '确定要此操作吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        changeStatus(rowId, status).then(data => {
          if (data.code === 200) {
            message.success('成功');
            rt();
          }
        });
      },
      onCancel() {},
    });
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
        delRole({
          roleId: rowId,
        })
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
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '角色名',
        dataIndex: 'name',
      },
      {
        title: '描述',
        dataIndex: 'desc',
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
            {text.name !== '基本角色' && (
              <span>
                {hasAccessKey('system.role.update') && (
                  <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                    <Icon type="edit" />
                  </a>
                )}
                <Divider type="vertical" />
                {hasAccessKey('system.role.enabled') &&
                  (record.status === 0 && (
                    <a title="禁用" onClick={() => this.showChangeConfirm(record.rowId, 1)}>
                      <Icon type="lock" />
                    </a>
                  ))}
                {hasAccessKey('system.role.enabled') &&
                  (record.status === 1 && (
                    <a title="启用" onClick={() => this.showChangeConfirm(record.rowId, 0)}>
                      <Icon type="unlock" />
                    </a>
                  ))}
                <Divider type="vertical" />
                {hasAccessKey('system.role.delete') && (
                  <a title="删除" onClick={() => this.showDelConfirm(record.rowId)}>
                    <Icon type="delete" />
                  </a>
                )}
                <Divider type="vertical" />
              </span>
            )}
            {hasAccessKey('system.role.authority') && (
              <a title="操作权限" onClick={() => this.handleModalVisible(true, 2, record.rowId)}>
                <Icon type="key" />
              </a>
            )}
            {hasAccessKey('system.role.authorityData') && (
              <Fragment>
                <Divider type="vertical" />
                <a title="数据权限" onClick={() => this.handleModalVisible(true, 3, record.rowId)}>
                  <Icon type="database" />
                </a>
              </Fragment>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('system.role.list') && (
        <PageHeaderLayout title="角色列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm handleSearchTable={this.handleSearchTable.bind(this)} />
              </div>
              <div className={styles.tableListOperator}>
                {hasAccessKey('system.role.add') && (
                  <Button
                    icon="plus"
                    type="primary"
                    onClick={() => this.handleModalVisible(true, 0)}
                  >
                    新建
                  </Button>
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
          <ModalResourceTree
            modalVisibleResource={this.state.modalVisibleResource}
            currentResourceRoleId={this.state.currentResourceRoleId}
            handleModalVisible={this.handleModalVisible.bind(this)}
          />
          <AddForm
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
          <ModalOrgDataAuthForm
            currentDataRoleId={this.state.currentDataRoleId}
            modalVisibleData={this.state.modalVisibleData}
            handleModalVisible={this.handleModalVisible.bind(this)}
          />
        </PageHeaderLayout>
      )
    );
  }
}
