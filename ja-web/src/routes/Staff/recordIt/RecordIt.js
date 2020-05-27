import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';

import AddForm from '../../../components/Staff/RecordIt/ModalAddForm';
import EditForm from '../../../components/Staff/RecordIt/ModalEditForm';

import { delRecordIt, getById, pageList } from '../../../services/recordIt';

import styles from './RecordIt.less';

export default class RecordIt extends PureComponent {
  state = {
    data: {},
    staffId: this.props.staffId,
    staffNo: this.props.staffNo,
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
      staffId: this.props.staffId,
    },
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
    console.log(params);
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
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (nextProps.currentEditUserId > 0) {
      getById(nextProps.currentEditUserId).then(data => {
        this.setState({
          rowId: data.data.rowId,
          staffId: data.data.staffId,
          certificateName: data.data.certificateName,
          computerRank: data.data.computerRank,
        });
      });
    }
  }

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
      this.setState({
        modalVisibleEdit: !!flag,
        currentEditUserId: rowId,
      });
    }
  };

  /**
   * 删除
   * @param rowId
   */
  showDelItConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此证书吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delRecordIt(rowId)
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
        title: '证书名称',
        dataIndex: 'certificateName',
      },
      {
        title: '计算机等级',
        dataIndex: 'computerRank',
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            <Divider type="vertical" />
            <a title="删除" onClick={() => this.showDelItConfirm(record.rowId)}>
              <Icon type="delete" />
            </a>
          </Fragment>
        ),
      },
    ];

    return (
      <div>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.title}>计算机能力</div>
            <div className={styles.tableListOperator}>
              <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                添加
              </Button>
            </div>
            <StandardTable
              rowKey="rowId"
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
          modalVisibleAdd={this.state.modalVisibleAdd}
          staffId={this.state.staffId}
          staffNo={this.state.staffNo}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <EditForm
          modalVisibleEdit={this.state.modalVisibleEdit}
          currentEditUserId={this.state.currentEditUserId}
          staffNo={this.state.staffNo}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
      </div>
    );
  }
}
