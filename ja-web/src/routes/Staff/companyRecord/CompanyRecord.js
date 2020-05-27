import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, Badge, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';

import AddForm from '../../../components/Staff/CompanyRecord/ModalAddForm';
import EditForm from '../../../components/Staff/CompanyRecord/ModalEditForm';

import { del, pageList } from '../../../services/companyRecord';

import styles from './CompanyRecord.less';
import { getByTypeCode } from '../../../services/systemCode';
import {hasAccessKey} from "../../../utils/authority";

export default class CompanyRecord extends PureComponent {
  state = {
    statusMap: { '1': '是', '2': '否' },
    data: {},
    staffId: this.props.staffId,
    staffNo: this.props.staffNo,
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    YesOrNoCode: [],
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
    getByTypeCode({ typeCode: 'YES_OR_NO' })
      .then(content => {
        const status = {};
        content.data.forEach(item => {
          status[item.rowId] = item.name;
        });
        this.setState({
          YesOrNoCode: content.data,
          statusMap: status,
        });
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
  showDelCompanyRecordConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此档案信息吗？',
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
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '职衔申请表A',
        dataIndex: 'jobApplicationA',
        render: text => this.state.statusMap[text],
      },
      {
        title: '竞业协议',
        dataIndex: 'competitionAgreement',
        render: text => this.state.statusMap[text],
      },
      {
        title: '员工登记表B',
        dataIndex: 'staffRegisterB',
        render: text => this.state.statusMap[text],
      },
      {
        title: 'HR制度阅读回执',
        dataIndex: 'hrReadReceipt',
        render: text => this.state.statusMap[text],
      },
      {
        title: '录用审核表',
        dataIndex: 'hireChack',
        render: text => this.state.statusMap[text],
      },
      {
        title: '员工手册',
        dataIndex: 'staffHandbook',
        render: text => this.state.statusMap[text],
      },
      {
        title: '个人简历',
        dataIndex: 'resume',
        render: text => this.state.statusMap[text],
      },
      {
        title: '照片',
        dataIndex: 'photo',
        render: text => this.state.statusMap[text],
      },
      {
        title: '身份证或护照复印件',
        dataIndex: 'idCardCopies',
        render: text => this.state.statusMap[text],
      },
      {
        title: '工资卡信息',
        dataIndex: 'payCardInfo',
        render: text => this.state.statusMap[text],
      },
      {
        title: '户口本复印件',
        dataIndex: 'residenceBookletCopies',
        render: text => this.state.statusMap[text],
      },
      {
        title: '岗位说明说书',
        dataIndex: 'positionDescription',
        render: text => this.state.statusMap[text],
      },
      {
        title: '毕业证书复印件',
        dataIndex: 'graduationCertificateCopies',
        render: text => this.state.statusMap[text],
      },
      {
        title: '学生证复印件',
        dataIndex: 'studentIdCardCopies',
        render: text => this.state.statusMap[text],
      },
      {
        title: '学位证书复印件',
        dataIndex: 'diplomaCopies',
        render: text => this.state.statusMap[text],
      },
      {
        title: '实习协议',
        dataIndex: 'internshipContract',
        render: text => this.state.statusMap[text],
      },
      {
        title: '相关证书复印件',
        dataIndex: 'relevantCertificateCopies',
        render: text => this.state.statusMap[text],
      },
      {
        title: '劳务协议',
        dataIndex: 'labourAgreement',
        render: text => this.state.statusMap[text],
      },
      {
        title: '体检报告',
        dataIndex: 'medicalExaminationReport',
        render: text => this.state.statusMap[text],
      },
      {
        title: '背调报告',
        dataIndex: 'backSurveyReport',
        render: text => this.state.statusMap[text],
      },
      {
        title: '上家公司离职证明',
        dataIndex: 'beforeCompanyDimission',
        render: text => this.state.statusMap[text],
      },
      {
        title: '劳动合同',
        dataIndex: 'laborContract',
        render: text => this.state.statusMap[text],
      },
      {
        title: '诚信行为暨知识产权协议书',
        dataIndex: 'intellectualPropertyAgreement',
        render: text => this.state.statusMap[text],
      },
      {
        title: '备注信息',
        dataIndex: 'remark',
      },
      {
        title: '操作',
        fixed: 'right',
        render: (text, record) => (
          <Fragment>
            {hasAccessKey(`staff.baseInfo.update`) && (
            <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
              <Icon type="edit" />
            </a>
            )}
            <Divider type="vertical" />
            {hasAccessKey(`staff.baseInfo.update`) && (
            <a title="删除" onClick={() => this.showDelCompanyRecordConfirm(record.rowId)}>
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
            <div className={styles.title}>档案信息</div>
            {!!this.state.data &&
              !!this.state.data.list &&
              this.state.data.list.length === 0 && (
                <div className={styles.tableListOperator}>
                  {hasAccessKey(`staff.baseInfo.update`) && (
                  <Button
                    icon="plus"
                    type="primary"
                    onClick={() => this.handleModalVisible(true, 0)}
                  >
                    新建
                  </Button>
                  )}
                </div>
              )}
            <StandardTable
              rowKey="rowId"
              scroll={{ x: 3000, y: 0 }}
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
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <EditForm
          modalVisibleEdit={this.state.modalVisibleEdit}
          currentEditUserId={this.state.currentEditUserId}
          staffNo={this.state.staffNo}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
      </div>
    );
  }
}
