import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { Card, Icon, Button, Modal, message, Badge, Divider } from 'antd';
import StandardTable from 'components/StandardTable';

import AddForm from '../../../components/Staff/Award/ModalAddForm';
import EditForm from '../../../components/Staff/Award/ModalEditForm';
import { del, pageList } from '../../../services/award';
import { getByTypeCode } from '../../../services/systemCode';

import styles from './Award.less';
import { serverUrlPre } from '../../../utils/request';
import {getToken, hasAccessKey} from '../../../utils/authority';

export default class Award extends PureComponent {
  state = {
    data: {},
    staffId: this.props.staffId,
    staffNo: this.props.staffNo,
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    awardRankCode: [],
    awardTypeCode: [],
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
    getByTypeCode({ typeCode: 'AWARD_RANK' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          awardRankCode: content.data,
        });
      }
    });
    getByTypeCode({ typeCode: 'AWARD_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          awardTypeCode: content.data,
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
  showDelAwardConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此奖励吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        del(rowId)
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
    const token = getToken();
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '荣誉名称',
        dataIndex: 'awardName',
      },
      {
        title: '证书颁发日期',
        dataIndex: 'credentialIssueDate',
      },
      {
        title: '证书颁发机构',
        dataIndex: 'credentialIssueOrg',
      },
      {
        title: '奖励形式',
        dataIndex: 'rewardForm',
      },
      {
        title: '奖励类别',
        dataIndex: 'transNames.awardType_name',
      },
      {
        title: '奖励级别',
        dataIndex: 'transNames.awardRank_name',
      },
      {
        title: '获奖时间',
        dataIndex: 'awardTime',
      },
      {
        title: '年度',
        dataIndex: 'year',
      },
      {
        title: '批准单位',
        dataIndex: 'ratifyUnit',
      },
      {
        title: '奖励事由',
        dataIndex: 'awardCause',
      },
      {
        title: '获奖依据',
        dataIndex: 'awardGist',
      },
      {
        title: '奖励措施',
        dataIndex: 'awardMeasure',
      },
      {
        title: '备注',
        dataIndex: 'remark',
      },
      {
        title: '附件',
        render: (text, record) => {
          return (
            record.file !== undefined &&
            record.file !== '' && (
              <a
                href={`${serverUrlPre}/system/file/download?filePath=${record.file}&token=${token}`}
                title={'附件下载'}
                alt="file"
              >
                附件下载
              </a>
            )
          );
        },
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey(`staff.baseInfo.update`) && (
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey(`staff.baseInfo.update`) && (
            <a title="删除" onClick={() => this.showDelAwardConfirm(record.rowId)}>
              <Icon type="delete" />
            </a>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      <div>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.title}>荣誉信息</div>
            <div className={styles.tableListOperator}>
              {hasAccessKey(`staff.baseInfo.update`) && (
              <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                添加
              </Button>
              )}
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
          awardRankCode={this.state.awardRankCode}
          awardTypeCode={this.state.awardTypeCode}
          staffId={this.state.staffId}
          staffNo={this.state.staffNo}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <EditForm
          modalVisibleEdit={this.state.modalVisibleEdit}
          currentEditUserId={this.state.currentEditUserId}
          awardRankCode={this.state.awardRankCode}
          awardTypeCode={this.state.awardTypeCode}
          staffNo={this.state.staffNo}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
      </div>
    );
  }
}
