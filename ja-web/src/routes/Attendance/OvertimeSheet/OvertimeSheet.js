import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Attendance/OvertimeSheet/ModalAddForm';
import EditForm from '../../../components/Attendance/OvertimeSheet/ModalEditForm';
import SearchForm from '../../../components/Attendance/OvertimeSheet/SearchForm';

import { baseList } from '../../../services/org';
import { delOvertimeSheet, pageList, getById } from '../../../services/overtimeSheet';
import { getByTypeCode } from '../../../services/systemCode';
import styles from './OvertimeSheet.less';
import { queryBaseInfoForParams } from '../../../services/staffBaseInfo';

import { commonAllList } from '../../../services/jobNumber';
import { hasAccessKey } from '../../../utils/authority';

export default class OvertimeSheet extends PureComponent {
  state = {
    data: [],
    orgBaseList: [],
    staffAllList: [],
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    factoryIds: [],
    commonAllList: [],
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
        this.setState({ orgBaseList: content.data });
      })
      .catch(e => {
        console.log(e);
      });

    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content && !!content.data) {
        {
          content.data.map(
            d =>
              d.name === '在职' &&
              this.setState({
                jobStatus: d.rowId,
              })
          );
        }
        const jobStatus = this.state.jobStatus;
        queryBaseInfoForParams({ jobStatus: jobStatus }).then(data => {
          if (!!data && !!data.data) {
            this.setState({
              staffAllList: data.data,
            });
          }
        });
      }
    });

    commonAllList().then(data => {
      if (!!data && !!data.data) {
        this.setState({
          commonAllList: data.data,
        });
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
      getById(rowId)
        .then(data => {
          this.setState({
            detailData: data.data,
            modalVisibleEdit: !!flag,
            currentEditUserId: rowId,
          });
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
      content: '确定删除此加班单吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delOvertimeSheet(rowId)
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
        title: '基地',
        dataIndex: 'baseName',
      },
      {
        title: '部门',
        dataIndex: 'deptName',
      },
      {
        title: '工号',
        dataIndex: 'staffNo',
      },
      {
        title: '姓名',
        dataIndex: 'staffName',
      },
      {
        title: '班次',
        dataIndex: 'jobNoName',
      },
      {
        title: '开始日期',
        render: (text, record) => {
          return moment(record.startDate).format('YYYY-MM-DD HH:mm');
        },
      },
      {
        title: '结束日期',
        render: (text, record) => {
          return moment(record.endDate).format('YYYY-MM-DD HH:mm');
        },
      },
      {
        title: '加班时数',
        dataIndex: 'overtimeTime',
      },
      {
        title: '加班原因',
        dataIndex: 'reason',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('attendance.overtimeSheet.update') && (
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            )}
            {hasAccessKey('attendance.overtimeSheet.update') && (
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
      <PageHeaderLayout title="加班单管理">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                orgBaseList={this.state.orgBaseList}
                handleSearchTable={this.handleSearchTable.bind(this)}
                staffAllList={this.state.staffAllList}
              />
            </div>
            <div className={styles.tableListOperator}>
              {hasAccessKey('attendance.overtimeSheet.add') && (
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
            {hasAccessKey('attendance.overtimeSheet.list') && (
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
          orgBaseList={this.state.orgBaseList}
          modalVisibleAdd={this.state.modalVisibleAdd}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
          factoryIds={this.state.factoryIds}
          commonAllList={this.state.commonAllList}
        />
        {this.state.modalVisibleEdit ? (
          <EditForm
            orgBaseList={this.state.orgBaseList}
            detailData={this.state.detailData}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
            staffAllList={this.state.staffAllList}
            commonAllList={this.state.commonAllList}
          />
        ) : (
          ''
        )}
      </PageHeaderLayout>
    );
  }
}
