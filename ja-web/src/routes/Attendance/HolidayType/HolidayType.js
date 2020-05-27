import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Badge } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Attendance/HolidayType/ModalAddForm';
import EditForm from '../../../components/Attendance/HolidayType/ModalEditForm';

import { baseList } from '../../../services/org';
import { delHolidayType, pageList, getById } from '../../../services/holidayType';
import { getByTypeCode } from '../../../services/systemCode';
import styles from './HolidayType.less';
import { hasAccessKey } from '../../../utils/authority';

const statusMap = ['success', 'error'];
const statusText = ['正常', '禁用'];

export default class HolidayType extends PureComponent {
  state = {
    data: [],
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    timeTypes: [],
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

    getByTypeCode({ typeCode: 'TIME_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          timeTypes: content.data,
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
      content: '确定删除此假期类型吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delHolidayType(rowId)
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
        title: '假期类型',
        dataIndex: 'typeName',
      },
      {
        title: '是否可用',
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
        onFilter: (value, record) => record.usable.toString() === value,
        render(val) {
          return <Badge status={statusMap[val]} text={statusText[val]} />;
        },
      },
      {
        title: '请假时间单位',
        dataIndex: 'transNames.unit_name',
      },
      {
        title: '最小请假时间',
        dataIndex: 'minLeaveTime',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('attendance.holidayType.update') && (
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            )}
            {hasAccessKey('attendance.holidayType.delete') && (
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
      <PageHeaderLayout title="假期类型管理">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              {/*<SearchForm*/}
              {/*orgBaseList ={this.state.orgBaseList}*/}
              {/*handleSearchTable={this.handleSearchTable.bind(this)}*/}
              {/*factoryIds={this.state.factoryIds}*/}
              {/*/>*/}
            </div>
            <div className={styles.tableListOperator}>
              {hasAccessKey('attendance.holidayType.add') && (
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
            {hasAccessKey('attendance.holidayType.list') && (
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
          timeTypes={this.state.timeTypes}
        />
        {this.state.modalVisibleEdit ? (
          <EditForm
            detailData={this.state.detailData}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
            timeTypes={this.state.timeTypes}
          />
        ) : (
          ''
        )}
      </PageHeaderLayout>
    );
  }
}
