import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/Emolument/Subsidy/ModalAddForm';
import EditForm from '../../components/Emolument/Subsidy/ModalEditForm';
import SearchForm from '../../components/Emolument/Subsidy/SearchForm';

import { delSubsidy, pageList, getById, beachDel, empty } from '../../services/eltSubsidy';

import styles from './SocialSecurity.less';
import { baseList } from '../../services/org';

export default class SocialSecurity extends PureComponent {
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
    title: '补贴规则列表',
    isChangeing: true,
    maintainValue: '维护补贴规则',
    deleteBtnValue: '导入',
    isCheckBox: false,
    ids: '',
    isViewing: false,
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
      getById(rowId)
        .then(data => {
          this.setState({
            detailData: data.data,
            modalVisibleEdit: !!flag,
            currentEditUserId: rowId,
          });
        })
        .catch(() => {
          message.error('查询补贴规则信息失败');
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
    }
  };

  /**
   * 删除
   * @param rowId
   */
  showDelUserConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此补贴规则吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delSubsidy(rowId)
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

  changeTitleValue = () => {
    if (!!this.state.isChangeing) {
      const tableTitle = (
        <span>
          维护补贴规则<a
            style={{ padding: 20, fontSize: 14 }}
            onClick={() => {
              this.setState({
                title: '补贴规则列表',
                isChangeing: true,
                maintainValue: '维护补贴规则',
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
        deleteBtnValue: '删除',
      });
      console.log(this.state.isChangeing);
    } else {
      const rt = this.refreshTable;
      Modal.confirm({
        title: '清空确认',
        content: '确定清空补贴规则列表吗？',
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

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const { isChangeing } = this.state;
    const columns = [
      {
        title: '基地',
        dataIndex: 'transNames.baseId_baseOrDeptName',
      },
      {
        title: '补贴类型',
        dataIndex: 'subsidyType',
      },
      {
        title: '补贴金额',
        dataIndex: 'subsidies',
      },
      {
        title: '备注',
        width: 140,
        dataIndex: 'remarks',
      },
      {
        title: isChangeing ? '操作' : '',
        // fixed: 'right',
        render: (text, record) =>
          isChangeing ? (
            <Fragment>
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>

              <Divider type="vertical" />
              <a title="删除" onClick={() => this.showDelUserConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            </Fragment>
          ) : (
            ''
          ),
      },
    ];

    return (
      <PageHeaderLayout title={this.state.title}>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                orgBaseList={this.state.orgBaseList}
                handleSearchTable={this.handleSearchTable.bind(this)}
              />
            </div>
            <div className={styles.tableListOperator}>
              <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                新建
              </Button>
              <Button className={styles.btn1} onClick={this.changeTitleValue}>
                {this.state.maintainValue}
              </Button>
              <Button icon={isChangeing ? 'caret-right' : ''} type="primary">
                {this.state.deleteBtnValue}
              </Button>
              {selectedRows.length > 0 && (
                <span>
                  <Button onClick={() => this.handleModalVisible(true, 2)}>批量删除</Button>
                </span>
              )}
            </div>
            <StandardTable
              rowKey="rowId"
              isCheckBox={true}
              selectedRows={selectedRows}
              // scroll={{x:1600,y:0}}
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
            detailData={this.state.detailData}
            orgBaseList={this.state.orgBaseList}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
        ) : (
          ''
        )}
      </PageHeaderLayout>
    );
  }
}
