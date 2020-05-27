import React, { PureComponent, Fragment } from 'react';
import { Table, Button, Divider, message, Icon, Modal } from 'antd';

import styles from '../BasicForm.less';
import { listOuterExperience, delOuterExperience } from '../../../../services/staffBaseInfo';
import ModalAddForm from './ModalAddForm';
import ModalEditForm from './ModalEditForm';
import {hasAccessKey} from "../../../../utils/authority";

export default class OuterExperienceEditableTable extends PureComponent {
  state = {
    staffId: 0,
    currentEditStaffId: 0,
    modalVisibleAdd: false,
    modalVisibleEdit: false,
  };

  componentDidMount() {
    this.refreshTable(this.props.rowId);
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      staffId: nextProps.rowId,
    });
    if (this.props.rowId != nextProps.rowId && nextProps.rowId > 0) {
      this.refreshTable(nextProps.rowId);
    }
  }

  /**
   * 请求数据
   */
  refreshTable = rowId => {
    if (!rowId) {
      return;
    }
    listOuterExperience(rowId)
      .then(response => {
        if (response.data !== undefined) {
          this.setState({
            data: response.data,
          });
        }
      })
      .catch(e => {
        message.error('查询外部工作经历失败！');
      });
  };

  /**
   * 显示/隐藏弹窗
   * @param flag
   */
  handleModalVisible = (flag, type, rowId) => {
    if (type === 1) {
      this.setState({
        modalVisibleAdd: !!flag,
      });
    } else if (type === 2) {
      this.setState({
        modalVisibleEdit: !!flag,
        currentEditStaffId: rowId,
      });
    }
  };

  /**
   * 删除
   * @param rowId
   */
  showDelStaffConfirm = rowId => {
    const { staffId } = this.state;
    const refreshTable = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delOuterExperience(rowId)
          .then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              refreshTable(staffId);
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
    const { data } = this.state;
    const { staffId } = this.state;
    const columns = [
      {
        title: '工作单位',
        dataIndex: 'workUnit',
        key: 'workUnit',
      },
      {
        title: '任职部门',
        dataIndex: 'deptName',
        key: 'deptName',
      },
      {
        title: '任职职衔',
        dataIndex: 'positionName',
        key: 'positionName',
      },
      {
        title: '开始日期',
        dataIndex: 'startDate',
        key: 'startDate',
      },
      {
        title: '结束日期',
        dataIndex: 'endDate',
        key: 'endDate',
      },
      {
        title: '证明人及职务',
        dataIndex: 'proofPersonAndDuty',
        key: 'proofPersonAndDuty',
      },
      {
        title: '证明人联系方式',
        dataIndex: 'proofContact',
        key: 'proofContact',
      },
      {
        title: '薪酬状况',
        dataIndex: 'salary',
        key: 'salary',
      },
      {
        title: '操作',
        dataIndex: 'operation',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey(`staff.baseInfo.update`) && (
            <a title="编辑" onClick={() => this.handleModalVisible(true, 2, record.rowId)}>
              <Icon type="edit" />
            </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey(`staff.baseInfo.update`) && (
            <a title="删除" onClick={() => this.showDelStaffConfirm(record.rowId)}>
              <Icon type="delete" />
            </a>
            )}
          </Fragment>
        ),
      },
    ];
    return (
      <div className={styles.tableList}>
        <div className={styles.tableListOperator}>
          {hasAccessKey(`staff.baseInfo.update`) && (
          <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 1, 0)}>
            新建
          </Button>
          )}
        </div>
        <Table bordered={true} pagination={false} dataSource={data} columns={columns} />
        <ModalAddForm
          modalVisibleAdd={this.state.modalVisibleAdd}
          handleModalVisible={this.handleModalVisible.bind(this)}
          staffId={staffId}
          refreshTable={this.refreshTable.bind(this)}
        />
        <ModalEditForm
          modalVisibleEdit={this.state.modalVisibleEdit}
          handleModalVisible={this.handleModalVisible.bind(this)}
          currentEditStaffId={this.state.currentEditStaffId}
          staffId={staffId}
          refreshTable={this.refreshTable.bind(this)}
        />
      </div>
    );
  }
}
