import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Attendance/Machine/ModalAddForm';
import EditForm from '../../../components/Attendance/Machine/ModalEditForm';
import SearchForm from '../../../components/Attendance/Machine/SearchForm';

import { baseList } from '../../../services/org';
import { delMachine, pageList ,getById} from '../../../services/machine';
import { getByTypeCode } from '../../../services/systemCode';
import styles from './Machine.less';
import { hasAccessKey } from '../../../utils/authority';

export default class Machine extends PureComponent {
  state = {
    data: [],
    orgBaseList:[],
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    factoryIds:[],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    detailData:{},
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

    getByTypeCode({ typeCode: 'FACTORY_CATEGORY' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          factoryIds: content.data,
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
              message.error('查询考勤机信息失败');
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
      content: '确定删除此考勤机吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delMachine(rowId)
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
        title: '考勤机名称',
        dataIndex: 'machineName',
      },
      {
        title: '基地',
        dataIndex: 'baseId',
      },
      {
        title: '厂别',
        dataIndex: 'transNames.factoryId_name',
      },
      {
        title: '序列号',
        dataIndex: 'seq',
      },
      {
        title: '最早提前签到(分钟)',
        dataIndex: 'earliestSignIn',
      },
      {
        title: '最晚推迟签到(分钟)',
        dataIndex: 'latestSignIn',
      },
      {
        title: '最早提前签退(分钟)',
        dataIndex: 'earliestSignOff',
      },
      {
        title: '最晚推迟签退(分钟)',
        dataIndex: 'latestSignOff',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('attendance.attendanceMachine.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}
            {hasAccessKey('attendance.attendanceMachine.delete') && (
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
      <PageHeaderLayout title="考勤机管理">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                orgBaseList ={this.state.orgBaseList}
                handleSearchTable={this.handleSearchTable.bind(this)}
                factoryIds={this.state.factoryIds}
              />
            </div>
            <div className={styles.tableListOperator}>
              {hasAccessKey('attendance.attendanceMachine.add') && (
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
            {hasAccessKey('attendance.attendanceMachine.list') && (
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
        />
        {
          this.state.modalVisibleEdit? <EditForm
            orgBaseList={this.state.orgBaseList}
            detailData={this.state.detailData}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
            factoryIds={this.state.factoryIds}
          />:""
        }
      </PageHeaderLayout>
    );
  }
}
