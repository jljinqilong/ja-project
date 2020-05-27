import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Attendance/Exemptions/ModalAddForm';
import SearchForm from '../../../components/Attendance/Exemptions/SearchForm';

import { delExemptions, pageList, getById } from '../../../services/exemptions';
import styles from './Exemptions.less';
import { getByTypeCode } from '../../../services/systemCode';
import { queryBaseInfoForParams } from '../../../services/staffBaseInfo';
import { hasAccessKey } from '../../../utils/authority';

export default class Exemptions extends PureComponent {
  state = {
    data: [],
    staffAllList: [],
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
        queryBaseInfoForParams({ jobStatus: jobStatus }).then(content => {
          if (!!content && !!content.data) {
            this.setState({
              staffAllList: content.data,
            });
          }
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
      content: '确定删除此豁免考勤吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delExemptions(rowId)
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
        title: '姓名',
        dataIndex: 'staffName',
      },
      {
        title: '工号',
        dataIndex: 'staffNo',
      },
      {
        title: '部门',
        dataIndex: 'deptName',
      },
      {
        title: '职等/赋值名称',
        dataIndex: 'gradeName',
      },
      {
        title: '职级',
        dataIndex: 'rankName',
      },
      {
        title: '职衔',
        dataIndex: 'positionName',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey(`attendance.exemptions.delete`) && (
              <a title="删除" onClick={() => this.showDelAccumulationConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      <PageHeaderLayout title="豁免考勤管理">
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
              {hasAccessKey(`attendance.exemptions.add`) && (
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
            {hasAccessKey(`attendance.exemptions.list`) && (
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
          staffAllList={this.state.staffAllList}
        />
      </PageHeaderLayout>
    );
  }
}
