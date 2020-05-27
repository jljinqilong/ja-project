import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Table, Input, Upload } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import { routerRedux } from 'dva/router';
import store from '../../index';
import AddForm from '../../components/Emolument/AccumulationFund/ModalAddForm';
import EditForm from '../../components/Emolument/AccumulationFund/ModalEditForm';
import SearchForm from '../../components/Emolument/AccumulationFund/SearchForm';

import { baseList } from '../../services/org';
import {
  delAccumulationFund,
  pageList,
  getById,
  beachDel,
  empty,
  exportExcel,
} from '../../services/eltAccumulationFund';

import styles from './AccumulationFund.less';
import { serverUrlPre } from '../../utils/request';
import { getToken } from '../../utils/authority';

export default class AccumulationFundCopy extends PureComponent {
  state = {
    data: [],
    orgBaseList: [],
    modalVisibleAdd: false,
    modalVisibleAddOne: false,
    modalVisibleEdit: false,
    selectedRows: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();
    baseList().then(content => {
      this.setState({ orgBaseList: content.data });
    });
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageList(params).then(response => {
      if (!!response.data) {
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
      ids: rows.map(d => d.rowId).join(','),
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
      getById(rowId).then(data => {
        this.setState({
          detailData: data.data,
          modalVisibleEdit: !!flag,
          currentEditUserId: rowId,
        });
      });
    } else if (type === 2) {
      const { ids } = this.state;
      const rt = this.refreshTable;
      Modal.confirm({
        title: '删除确认',
        content: '确定删除吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          beachDel(ids).then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              rt();
            } else if (data.code === 400) {
              message.error('删除失败');
            }
          });
        },
        onCancel() {},
      });
    } else if (type === 4) {
      this.setState({
        modalVisibleAddOne: !!flag,
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
      content: '确定删除此公积金吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delAccumulationFund(rowId).then(data => {
          if (data.code === 200) {
            message.success('删除成功');
            rt();
          } else if (data.code === 400) {
            message.error('删除失败');
          }
        });
      },
      onCancel() {},
    });
  };
  /***
   *
   * 维护公积金规则
   *
   */

  changeTitleValue = () => {};

  /**
   *
   * 查看员工，给员工绑定社保规则
   *
   * 跳转页面
   */
  handleJumpPage = rowId => {
    const { dispatch } = store;
    dispatch(
      routerRedux.push({
        pathname: `/emolument/LookStaff/${rowId}`,
      })
    );
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const { isViewing } = this.state;
    const token = getToken();
    const columns = [
      {
        title: '基地',
        dataIndex: 'transNames.baseId_baseOrDeptName',
      },
      {
        title: '规则名称',
        dataIndex: 'ruleName',
      },
      {
        title: '缴费基数',
        dataIndex: 'socialInsuranceBase',
      },
      {
        title: '个人比例',
        dataIndex: 'personalProportion',
      },
      {
        title: '个人金额',
        dataIndex: 'personalAmount',
      },
      {
        title: '公司比例',
        dataIndex: 'companyRatio',
      },
      {
        title: '公司金额',
        dataIndex: 'companyAmount',
      },
      {
        title: '个人补充比例',
        dataIndex: 'personalSupplementaryRatio',
      },
      {
        title: '个人补充金额',
        dataIndex: 'personalSupplementaryAmount',
      },
      {
        title: '公司补充比例',
        dataIndex: 'companySupplementaryProportion',
      },
      {
        title: '公司补充金额',
        dataIndex: 'companySupplementaryAmount',
      },
      {
        title: '备注',
        dataIndex: 'remarks',
      },
      {
        title: '操作',
        width: 120,
        fixed: 'right',
        render: (text, record) => (
          <Fragment>
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            <Divider type="vertical" />

            <a title="删除" onClick={() => this.showDelAccumulationConfirm(record.rowId)}>
              <Icon type="delete" />
            </a>
            <Divider type="vertical" />
            <a title="查看员工" onClick={() => this.handleJumpPage(record.rowId)}>
              <Icon type="user" />
            </a>
          </Fragment>
        ),
      },
    ];

    return (
      <PageHeaderLayout title="公积金列表">
        <div>
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm
                  orgBaseList={this.state.orgBaseList}
                  handleSearchTable={this.handleSearchTable.bind(this)}
                />
              </div>
              <div className={styles.tableListOperator}>
                {selectedRows.length > 0 && (
                  <Button type="primary" onClick={() => this.handleModalVisible(true, 2)}>
                    批量删除
                  </Button>
                )}
                <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                  新建
                </Button>
                <Button onClick={this.changeTitleValue}>维护公积金规则</Button>
                <Upload
                  name="excel"
                  action={`${serverUrlPre}/emolument/eltAccumulationFund/importExcel?token=${token}`}
                  onChange={this.handleChange}
                >
                  <Button>
                    <Icon type="upload" />导入公积金规则
                  </Button>
                </Upload>
              </div>
              <StandardTable
                rowKey="rowId"
                scroll={{ x: 1600, y: 0 }}
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
          <EditForm
            orgBaseList={this.state.orgBaseList}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
        </div>
      </PageHeaderLayout>
    );
  }
}
