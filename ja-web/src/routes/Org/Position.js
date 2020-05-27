import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/Org/Position/ModalAddForm';
import EditForm from '../../components/Org/Position/ModalEditForm';
import SearchForm from '../../components/Org/Position/SearchForm';

import { delPosition, exportExcel, getById } from '../../services/position';
import { getByPositionType } from '../../services/positionType';
import { hasAccessKey } from '../../utils/authority';
import styles from './Position.less';
import { exportErrExcel } from '../../services/staffBaseInfo';

@connect(({ position, loading }) => ({
  position,
  loading: loading.models.position,
}))
export default class Position extends PureComponent {
  state = {
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    positionTypeCode: [],
    erportParams: '',
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

    /**
     *职衔类别
     *
     */
    getByPositionType()
      .then(content => {
        this.setState({ positionTypeCode: content.data });
      })
      .catch(e => {
        console.log(e);
      });
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    const { dispatch } = this.props;
    if (!params) {
      params = this.state.searchFormValues;
    }
    console.log(params);
    dispatch({
      type: 'position/getPageList',
      payload: params,
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
      erportParams: params,
    });
    this.refreshTable(params);
  };
  handleExport = () => {
    exportExcel();
  };
  handleChange = info => {
    if (!!info.file.response) {
      if (info.file.response.code === 200) {
        message.success('导入成功');
        this.refreshTable();
      } else if (info.file.response.code === 205) {
        message.error('有部分异常数据导出');
        exportErrExcel(info.file.response.redisKey);
      } else {
        message.error('导入失败');
      }
    }
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
   * 删除职衔
   * @param rowId
   */
  showDelUserConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此职衔吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delPosition(rowId)
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
    const { position, loading } = this.props;
    const { selectedRows } = this.state;
    const { data } = position;
    const columns = [
      {
        title: '职衔类别',
        dataIndex: 'typeName',
      },
      {
        title: '职衔名称',
        dataIndex: 'positionName',
      },
      {
        title: '职等/赋值名称',
        dataIndex: 'grades',
      },
      {
        title: '职级',
        dataIndex: 'ranks',
      },
      {
        title: '编制人数',
        dataIndex: 'staffSize',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('org.position.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}

            <Divider type="vertical" />
            {hasAccessKey('org.position.delete') && (
              <a title="删除" onClick={() => this.showDelUserConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('org.position.list') && (
        <PageHeaderLayout title="职衔列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm
                  positionTypeCode={this.state.positionTypeCode}
                  handleSearchTable={this.handleSearchTable.bind(this)}
                />
              </div>
              <div className={styles.tableListOperator}>
                {hasAccessKey('org.position.add') && (
                  <Button
                    icon="plus"
                    type="primary"
                    onClick={() => this.handleModalVisible(true, 0)}
                  >
                    新建
                  </Button>
                )}
                {hasAccessKey('org.position.export') && (
                  <Button type="primary" onClick={() => this.handleExport()}>
                    导出
                  </Button>
                )}
                {selectedRows.length > 0 && (
                  <span>
                    <Button>批量删除</Button>
                  </span>
                )}
              </div>
              <StandardTable
                rowKey="rowId"
                selectedRows={selectedRows}
                loading={loading}
                data={data}
                columns={columns}
                onSelectRow={this.handleSelectRows}
                onChange={this.handleStandardTableChange}
              />
            </div>
          </Card>
          <AddForm
            modalVisibleAdd={this.state.modalVisibleAdd}
            positionTypeCode={this.state.positionTypeCode}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
          {this.state.modalVisibleEdit ? (
            <EditForm
              detailData={this.state.detailData}
              modalVisibleEdit={this.state.modalVisibleEdit}
              positionTypeCode={this.state.positionTypeCode}
              currentEditUserId={this.state.currentEditUserId}
              handleModalVisible={this.handleModalVisible.bind(this)}
              refreshTable={this.refreshTable.bind(this)}
            />
          ) : (
            ''
          )}
        </PageHeaderLayout>
      )
    );
  }
}
