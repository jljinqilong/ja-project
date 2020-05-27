import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Badge, Divider } from 'antd';
import StandardTable from 'components/StandardTable';

import AddForm from '../../../components/Staff/OccInjury/ModalAddForm';
import EditForm from '../../../components/Staff/OccInjury/ModalEditForm';

import { del, pageList } from '../../../services/occInjury';

import styles from './OccInjury.less';
import { getByTypeCode } from '../../../services/systemCode';
import {hasAccessKey} from "../../../utils/authority";

export default class OccInjury extends PureComponent {
  state = {
    data: {},
    staffId: this.props.staffId,
    staffNo: this.props.staffNo,
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    disabilityLevelCode: [],
    nurseLevelCode: [],
    accidentStateCode: [],
    occupationalInjuryTyleCode: [],
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
    getByTypeCode({ typeCode: 'DISABILITY_LEVEL' })
      .then(content => {
        this.setState({
          disabilityLevelCode: content.data,
        });
      })
      .catch(e => {
        console.log(e);
      });

    getByTypeCode({ typeCode: 'NURSE_LEVEL' })
      .then(content => {
        this.state.nurseLevelCode = content.data;
        this.setState({
          nurseLevelCode: content.data,
        });
      })
      .catch(e => {
        console.log(e);
      });

    getByTypeCode({ typeCode: 'ACCIDENT_STATE' })
      .then(content => {
        this.state.accidentStateCode = content.data;
        this.setState({
          accidentStateCode: content.data,
        });
      })
      .catch(e => {
        console.log(e);
      });

    getByTypeCode({ typeCode: 'OCCUPATIONAL_INJURY_TYPE' })
      .then(content => {
        this.state.occupationalInjuryTyleCode = content.data;
        this.setState({
          occupationalInjuryTyleCode: content.data,
        });
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
  showDelOccInjuryConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此条记录吗？',
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
        title: '工伤类别',
        dataIndex: 'transNames.occupationalInjuryTyle_name',
      },
      {
        title: '工伤发生日期',
        dataIndex: 'occupationalInjuryStartDate',
      },
      {
        title: '伤残程度鉴定日期',
        dataIndex: 'disabilityIdentificationTime',
      },
      {
        title: '伤残等级',
        dataIndex: 'transNames.disabilityLevel_name',
      },
      {
        title: '认定部位或名称',
        dataIndex: 'partName',
      },
      {
        title: '工伤认定时间',
        dataIndex: 'occupationalInjuryTime',
      },
      {
        title: '护理程度级别',
        dataIndex: 'transNames.nurseLevel_name',
      },
      {
        title: '工伤号',
        dataIndex: 'occupationalInjuryNo',
      },
      {
        title: '伤前12个月平均月缴费工资',
        dataIndex: 'beforeInjurySalary',
      },
      {
        title: '事故类别',
        dataIndex: 'transNames.accidentState_name',
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
            <a title="删除" onClick={() => this.showDelOccInjuryConfirm(record.rowId)}>
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
            <div className={styles.title}>工伤信息</div>
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
          staffId={this.state.staffId}
          staffNo={this.state.staffNo}
          occupationalInjuryTyleCode={this.state.occupationalInjuryTyleCode}
          disabilityLevelCode={this.state.disabilityLevelCode}
          nurseLevelCode={this.state.nurseLevelCode}
          accidentStateCode={this.state.accidentStateCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <EditForm
          modalVisibleEdit={this.state.modalVisibleEdit}
          currentEditUserId={this.state.currentEditUserId}
          staffNo={this.state.staffNo}
          occupationalInjuryTyleCode={this.state.occupationalInjuryTyleCode}
          disabilityLevelCode={this.state.disabilityLevelCode}
          nurseLevelCode={this.state.nurseLevelCode}
          accidentStateCode={this.state.accidentStateCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
      </div>
    );
  }
}
