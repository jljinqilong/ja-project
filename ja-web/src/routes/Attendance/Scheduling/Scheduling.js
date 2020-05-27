import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Attendance/Scheduling/ModalAddForm';
import EditForm from '../../../components/Attendance/Scheduling/ModalEditForm';
import SearchForm from '../../../components/Attendance/Scheduling/SearchForm';

import { delJobNumber, commonPageList, getById } from '../../../services/jobNumber';
import { hasAccessKey } from '../../../utils/authority';
import styles from './Scheduling.less';

export default class Scheduling extends PureComponent {
  state = {
    data: [],
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
    if (!params) {
      params = this.state.searchFormValues;
    }
    commonPageList(params)
      .then(response => {
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
      })
      .catch(e => {
        console.log(e);
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
      // this.setState({
      //   modalVisibleEdit: !!flag,
      //   currentEditUserId: rowId,
      // });
      getById(rowId)
        .then(data => {
          this.setState({
            detailData: data.data,
            modalVisibleEdit: !!flag,
            currentEditUserId: rowId,
          });
        })
        .catch(() => {
          message.error('查询排班信息失败');
        });
    }
  };

  /**
   * 删除公积金
   * @param rowId
   */
  showDelAccumulationConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此排班吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delJobNumber(rowId)
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
        title: '班次',
        dataIndex: 'jobNoName',
      },
      {
        title: '排班类型',
        dataIndex: 'typeId',
      },
      {
        title: '上班时间设置',
        dataIndex: 'onWorkTime',
      },
      {
        title: '下班时间设置',
        dataIndex: 'offWorkTime',
      },
      {
        title: '午休时间(分钟)',
        dataIndex: 'noonBreak',
      },
      {
        title: '有效打卡时间(小时)',
        dataIndex: 'effectivePunching',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('attendance.scheduling.update') && (
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            )}
            {hasAccessKey('attendance.scheduling.delete') && (
              <Fragment>
                <Divider type="vertical" />
                <a title="删除" onClick={() => this.showDelAccumulationConfirm(record.rowId)}>
                  <Icon type="delete" />
                </a>
              </Fragment>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      <PageHeaderLayout title="排班管理">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                orgBaseList={this.state.orgBaseList}
                handleSearchTable={this.handleSearchTable.bind(this)}
                factoryIds={this.state.factoryIds}
              />
            </div>
            <div className={styles.tableListOperator}>
              {hasAccessKey('attendance.scheduling.add') && (
                <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                  新建
                </Button>
              )}
              {selectedRows.length > 0 && (
                <span>
                  <Button>批量删除</Button>
                </span>
              )}
            </div>
            {hasAccessKey('attendance.scheduling.list') && (
              <StandardTable
                rowKey="rowId"
                selectedRows={selectedRows}
                loading={loading}
                data={this.state.data}
                columns={columns}
                onSelectRow={this.handleSelectRows}
                onChange={this.handleStandardTableChange}
              />
            )}
          </div>
        </Card>
        <AddForm
          modalVisibleAdd={this.state.modalVisibleAdd}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
          factoryIds={this.state.factoryIds}
        />
        {this.state.modalVisibleEdit ? (
          <EditForm
            detailData={this.state.detailData}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
            factoryIds={this.state.factoryIds}
          />
        ) : (
          ''
        )}
      </PageHeaderLayout>
    );
  }
}
