import React, { PureComponent, Fragment } from 'react';
import {
  Card,
  Icon,
  Button,
  Modal,
  message,
  Divider,
  Table,
  Input,
  Dropdown,
  Menu,
  Upload,
} from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/Emolument/AccumulationFund/ModalAddForm';
import EditForm from '../../components/Emolument/AccumulationFund/ModalEditForm';
import SearchForm from '../../components/Emolument/AccumulationFund/SearchForm';
import ModalTransferForm from '../../components/Emolument/AccumulationFund/ModalTransferForm';

import { baseList } from '../../services/org';
import {
  delAccumulationFund,
  pageList,
  getById,
  beachDel,
  empty,
} from '../../services/eltAccumulationFund';

import styles from './AccumulationFund.less';
import { serverUrlPre } from '../../utils/request';
import { getToken } from '../../utils/authority';

export default class AccumulationFund extends PureComponent {
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
    detailData: {},
    title: '公积金列表',
    isChangeing: true,
    maintainValue: '维护公积金规则',
    deleteBtnValue: '导入',
    isCheckBox: false,
    ids: '',
    isViewing: true,
    infoDetail: [],
    newArrayData: [],
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
        console.log(response);
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
          message.error('查询公积金信息失败');
        });
    } else if (type === 2) {
      const { ids } = this.state;
      const rt = this.refreshTable;
      beachDel(ids)
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
        delAccumulationFund(rowId)
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
  /***
   *
   * 维护公积金规则
   *
   */

  changeTitleValue = () => {
    if (!!this.state.isChangeing) {
      const tableTitle = (
        <span>
          维护公积金规则<a
            style={{ padding: 20, fontSize: 14 }}
            onClick={() => {
              this.setState({
                title: '公积金列表',
                isChangeing: true,
                maintainValue: '维护公积金规则',
                deleteBtnValue: '导入',
              });
            }}
          >
            {' '}
            返回
          </a>{' '}
        </span>
      );
      this.setState({
        isChangeing: false,
        title: tableTitle,
        maintainValue: '清空',
      });
      console.log(this.state.isChangeing);
    } else {
      const rt = this.refreshTable;
      Modal.confirm({
        title: '清空确认',
        content: '确定清空公积金列表吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          empty()
            .then(data => {
              if (data.code === 200) {
                message.success('清空成功');
                rt();
              } else if (data.code === 400) {
                message.error('清空失败');
              }
            })
            .catch(() => {
              message.error('清空异常');
            });
        },
        onCancel() {},
      });
    }
  };

  /**
   *
   * 查看员工，给员工绑定社保规则
   *
   * */
  viewingEmployees = rowId => {
    console.log(rowId);
    const viewingTitle = (
      <span>
        查看员工<a
          style={{ padding: 20, fontSize: 14 }}
          onClick={() => {
            this.setState({
              title: '公积金列表',
              isViewing: true,
            });
          }}
        >
          {' '}
          返回
        </a>{' '}
      </span>
    );
    this.setState({
      isViewing: false,
      title: viewingTitle,
    });
    if (this.state.isViewing) {
      getById(rowId).then(data => {
        this.setState({
          infoDetail: data.data,
        });
      });
    }
  };

  /***
   *
   * 显示穿梭框
   *
   */
  openTranferModal = (flag, type) => {
    if (type === 0) {
      this.setState({ modalVisibleAdd: !!flag });
    }
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const { isChangeing } = this.state;
    const { isViewing } = this.state;
    const { rowKey } = this.props;
    const token = getToken();
    const columns = [
      {
        title: '基地',
        width: 150,
        dataIndex: 'transNames.baseId_baseOrDeptName',
      },
      {
        title: '规则名称',
        width: 150,
        dataIndex: 'ruleName',
      },
      {
        title: '缴费基数',
        width: 150,
        dataIndex: 'socialInsuranceBase',
      },
      {
        title: '个人比例',
        width: 150,
        dataIndex: 'personalProportion',
      },
      {
        title: '个人金额',
        width: 150,
        dataIndex: 'personalAmount',
      },
      {
        title: '公司比例',
        width: 150,
        dataIndex: 'companyRatio',
      },
      {
        title: '公司金额',
        width: 150,
        dataIndex: 'companyAmount',
      },
      {
        title: '个人补充比例',
        width: 150,
        dataIndex: 'personalSupplementaryRatio',
      },
      {
        title: '个人补充金额',
        width: 150,
        dataIndex: 'personalSupplementaryAmount',
      },
      {
        title: '公司补充比例',
        width: 150,
        dataIndex: 'companySupplementaryProportion',
      },
      {
        title: '公司补充金额',
        width: 150,
        dataIndex: 'companySupplementaryAmount',
      },
      {
        title: '备注',
        width: 150,
        dataIndex: 'remarks',
      },

      {
        title: isChangeing ? '操作' : '',
        width: 150,
        fixed: isChangeing ? 'right' : '',
        render: (text, record) =>
          isChangeing ? (
            <Fragment>
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
              <Divider type="vertical" />
              <a title="删除" onClick={() => this.showDelAccumulationConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
              <Divider type="vertical" />
              <a title="查看员工" onClick={() => this.viewingEmployees(record.rowId)}>
                <Icon type="user" />
              </a>
            </Fragment>
          ) : (
            ''
          ),
      },
    ];
    const obj = [this.state.infoDetail];
    const columns1 = [
      {
        title: '基地',
        width: 150,
        dataIndex: 'baseId',
        key: 'baseId',
      },
      {
        title: '规则名称',
        width: 150,
        dataIndex: 'ruleName',
        key: 'ruleName',
      },
      {
        title: '缴费基数',
        width: 150,
        dataIndex: 'socialInsuranceBase',
        key: 'socialInsuranceBase',
      },
      {
        title: '个人比例',
        width: 150,
        dataIndex: 'personalProportion',
        key: 'personalProportion',
      },
      {
        title: '个人金额',
        width: 150,
        dataIndex: 'personalAmount',
        key: 'personalAmount',
      },
      {
        title: '公司比例',
        width: 150,
        dataIndex: 'companyRatio',
        key: 'companyRatio',
      },
      {
        title: '公司金额',
        width: 150,
        dataIndex: 'companyAmount',
        key: 'companyAmount',
      },
      {
        title: '个人补充比例',
        width: 150,
        dataIndex: 'personalSupplementaryRatio',
        key: 'personalSupplementaryRatio',
      },
      {
        title: '个人补充金额',
        width: 150,
        dataIndex: 'personalSupplementaryAmount',
        key: 'personalSupplementaryAmount',
      },
      {
        title: '公司补充比例',
        width: 150,
        dataIndex: 'companySupplementaryProportion',
        key: 'companySupplementaryProportion',
      },
      {
        title: '公司补充金额',
        width: 150,
        dataIndex: 'companySupplementaryAmount',
        key: 'companySupplementaryAmount',
      },
      {
        title: '备注',
        width: 150,
        dataIndex: 'remarks',
        key: 'remarks',
      },
    ];
    const columns0 = [
      {
        title: '工号',
        dataIndex: 'baseId',
      },
      {
        title: '系统工号',
        dataIndex: 'ruleName',
      },
      {
        title: '姓名',
        dataIndex: 'socialInsuranceBase',
      },
      {
        title: '性别',
        dataIndex: 'personalProportion',
      },
      {
        title: '基地',
        dataIndex: 'personalAmount',
      },
      {
        title: '部门',
        dataIndex: 'companyRatio',
      },
      {
        title: '职务',
        dataIndex: 'companyAmount',
      },
      {
        title: '职衔',
        dataIndex: 'personalSupplementaryRatio',
      },
      {
        title: '职等/赋值名称',
        dataIndex: 'personalSupplementaryAmount',
      },
      {
        title: '值级',
        dataIndex: 'companySupplementaryProportion',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            <a title="删除" onClick={() => this.handleDeleteStaffRule(record.rowId)}>
              <Icon type="delete" />
            </a>
          </Fragment>
        ),
      },
    ];

    return (
      <PageHeaderLayout title={this.state.title}>
        {isViewing ? (
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
                  <Button
                    icon="plus"
                    type="primary"
                    onClick={() => this.handleModalVisible(true, 0)}
                  >
                    新建
                  </Button>
                  <Button className={styles.btn1} onClick={this.changeTitleValue}>
                    {this.state.maintainValue}
                  </Button>
                  {selectedRows.length > 0 && (
                    <span>
                      <Button onClick={() => this.handleModalVisible(true, 2)}>批量删除</Button>
                    </span>
                  )}
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
                  isCheckBox={true}
                  scroll={{ x: 1800, y: 0 }}
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
            {this.state.modalVisibleEdit ? (
              <EditForm
                orgBaseList={this.state.orgBaseList}
                detailData={this.state.detailData}
                modalVisibleEdit={this.state.modalVisibleEdit}
                currentEditUserId={this.state.currentEditUserId}
                handleModalVisible={this.handleModalVisible.bind(this)}
                refreshTable={this.refreshTable.bind(this)}
              />
            ) : (
              ''
            )}
          </div>
        ) : (
          <Card bordered={false}>
            <Table
              rowKey={(record, index) => index}
              isChangeing={this.props.isChangeing}
              scroll={this.props.scroll}
              loading={loading}
              dataSource={obj}
              columns={columns1}
              pagination={false}
              onChange={this.handleTableChange}
            />
            <div className={styles.staffSearchContext}>
              <div className={styles.staffList}>
                <span style={{ fontSize: 16 }}>员工号</span>
                <Input style={{ width: 200, marginLeft: 15 }} />
                <Button type="primary" style={{ width: 100, marginLeft: 20 }}>
                  查询
                </Button>
              </div>
              <div className={styles.btnList}>
                {/*<Dropdown overlay={menu} placement="bottomRight">*/}
                <Button className={styles.btn1} icon="caret-right">
                  导入
                </Button>
                <Button
                  icon="plus"
                  type="primary"
                  style={{ marginLeft: 20 }}
                  onClick={this.showModal}
                >
                  新增
                </Button>
              </div>
            </div>
            <ModalTransferForm
              isViewing={this.state.isViewing}
              modalVisibleAdd={this.state.modalVisibleAddOne}
              handleModalVisible={this.handleModalVisible.bind(this)}
            />
            <StandardTable
              selectedRows={selectedRows}
              loading={loading}
              data={this.state.data}
              columns={columns0}
              onSelectRow={this.handleSelectRows}
              onChange={this.handleStandardTableChange}
            />
          </Card>
        )}
      </PageHeaderLayout>
    );
  }
}
