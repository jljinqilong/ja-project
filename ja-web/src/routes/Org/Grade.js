import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/Org/Grade/ModalAddForm';
import EditForm from '../../components/Org/Grade/ModalEditForm';
import SearchForm from '../../components/Org/Grade/SearchForm';

import { delGrade, getById } from '../../services/grade';
import { getRankType } from '../../services/rank';
import { hasAccessKey } from '../../utils/authority';

import styles from './Grade.less';

@connect(({ grade, loading }) => ({
  grade,
  loading: loading.models.grade,
}))
export default class Grade extends PureComponent {
  state = {
    data: {},
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    RankAllList: [],
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
    getRankType()
      .then(content => {
        this.setState({ RankAllList: content.data });
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
      type: 'grade/getPageList',
      payload: params,
    });
  };

  /**
   * 搜索
   * @param params
   */
  handleSearchTable = params => {
    const sfvs = this.state.searchFormValues;
    console.log('搜索=====');
    console.log(sfvs);
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
          if (data.code === 200) {
            this.setState({
              detailData: data.data,
              modalVisibleEdit: !!flag,
              currentEditUserId: rowId,
            });
            // this.refreshTable();
            console.log('this.state.detailData ====');
            console.log(this.state.detailData);
          }
        });
    }
  };

  /**
   * 删除职等/赋值名称
   * @param rowId
   */
  showDelUserConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此职等/赋值名称吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delGrade(rowId)
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
    const { grade, loading } = this.props;
    const { selectedRows } = this.state;
    const { data } = grade;
    const columns = [
      {
        title: '职等名称/赋值名称',
        dataIndex: 'gradeName',
      },
      {
        title: '职级',
        dataIndex: 'ranks',
      },
      {
        title: '职等/赋值名称描述',
        dataIndex: 'gradeDesc',
      },
      {
        title: '岗位赋值',
        dataIndex: 'postAssignment',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey('org.grade.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey('org.grade.delete') && (
              <a title="删除" onClick={() => this.showDelUserConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('org.grade.list') && (
        <PageHeaderLayout title="职等/赋值名称列表">
          <Card bordered={false}>
            <div className={styles.tableList}>
              <div className={styles.tableListForm}>
                <SearchForm handleSearchTable={this.handleSearchTable.bind(this)} />
              </div>
              <div className={styles.tableListOperator}>
                {hasAccessKey('org.grade.add') && (
                  <Button
                    icon="plus"
                    type="primary"
                    onClick={() => this.handleModalVisible(true, 0)}
                  >
                    新建
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
                rowId={this.state.rowId}
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
            RankAllList={this.state.RankAllList}
            modalVisibleAdd={this.state.modalVisibleAdd}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
          {this.state.modalVisibleEdit ? (
            <EditForm
              detailData={this.state.detailData}
              RankAllList={this.state.RankAllList}
              codeTypeAllList={this.state.RankAllList}
              modalVisibleEdit={this.state.modalVisibleEdit}
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
