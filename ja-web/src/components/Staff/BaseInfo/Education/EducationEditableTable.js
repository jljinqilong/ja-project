import React, { PureComponent, Fragment } from 'react';
import { Table, Button, Divider, message, Icon, Modal } from 'antd';

import styles from '../BasicForm.less';
import { listEducation, delEducation } from '../../../../services/staffBaseInfo';
import ModalAddForm from './ModalAddForm';
import ModalEditForm from './ModalEditForm';
import {hasAccessKey} from "../../../../utils/authority";

export default class EducationEditableTable extends PureComponent {
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
    listEducation(rowId)
      .then(response => {
        if (response.data !== undefined) {
          this.setState({
            data: response.data,
          });
        }
      })
      .catch(e => {
        message.error('查询教育经历失败！');
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
        delEducation(rowId)
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
        title: '学校名称',
        dataIndex: 'schoolName',
      },
      {
        title: '专业/方向',
        dataIndex: 'majorName',
      },
      {
        title: '入学时间',
        dataIndex: 'entranceTime',
      },
      {
        title: '毕业时间',
        dataIndex: 'graduateTime',
      },
      {
        title: '学位',
        dataIndex: 'transNames.education_name',
      },
      {
        title: '学历',
        dataIndex: 'transNames.degree_name',
      },
      {
        title: '毕业情况',
        dataIndex: 'transNames.graduationSituation_name',
      },
      {
        title: '所获学历证书类',
        dataIndex: 'transNames.schoolingDocumentsType_name',
      },
      {
        title: '学位授予国家',
        dataIndex: 'transNames.degreeCountry_name',
      },
      {
        title: '学习方式',
        dataIndex: 'transNames.learningStyle_name',
      },
      {
        title: '是否最高学位',
        dataIndex: 'transNames.isHighestDegree_name',
      },
      {
        title: '是否最高学历',
        dataIndex: 'transNames.isHighestEducation_name',
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
