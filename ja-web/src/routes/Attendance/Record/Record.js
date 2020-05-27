import React, { PureComponent, Fragment } from 'react';
import { Card, } from 'antd';
import moment from 'moment';

import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Attendance/Record/ModalAddForm';
import EditForm from '../../../components/Attendance/Record/ModalEditForm';
import SearchForm from '../../../components/Attendance/Record/SearchForm';

import { pageList} from '../../../services/record';
import styles from './Record.less';
import { getByTypeCode } from '../../../services/systemCode';
import { queryBaseInfoForParams } from '../../../services/staffBaseInfo';
import { hasAccessKey } from '../../../utils/authority';

export default class Record extends PureComponent {
  state = {
    staffAllList: [],
    data: [],
    modalVisibleAdd: false,
    modalVisibleEdit: false,
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
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content && !!content.data) {
        {
          content.data.map(
            d =>
              d.name === '在职' &&
              this.setState({
                jobStatus: d.rowId,
              })
          );
        }
        const jobStatus = this.state.jobStatus;
        queryBaseInfoForParams({ jobStatus: jobStatus }).then(content => {
          if (!!content && !!content.data) {
            this.setState({
              staffAllList: content.data,
            });
          }
        });
      }
    });

    this.refreshTable();
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
    });
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '工号',
        dataIndex: 'staffNo',
      },
      {
        title: '姓名',
        dataIndex: 'staffName',
      },
      {
        title: '基地名称',
        dataIndex: 'baseName',
      },
      {
        title: '部门名称',
        dataIndex: 'deptName',
      },
      {
        title: '班次名称',
        dataIndex: 'jobNumName',
      },
      {
        title: '岗位名称',
        dataIndex: 'operatingPostName',
      },
      {
        title: '职位名称',
        dataIndex: 'positionName',
      },
      {
        title: '职级名称',
        dataIndex: 'rankName',
      },
      {
        title: '职等名称',
        dataIndex: 'gradeName',
      },
      {
        title: '内部职级名称',
        dataIndex: 'internalRankName',
      },
      {
        title: '入职日期',
        // dataIndex: 'entryDate',
        render: (text, record) => {
          return moment(record.entryDate).format('YYYY-MM-DD HH:mm');
        },
      },
      {
        title: '总出勤',
        dataIndex: 'totalAttendance',
      },
      {
        title: '应出勤天数',
        dataIndex: 'attendanceDays',
      },
      {
        title: '前夜',
        dataIndex: 'beforeNight',
      },
      {
        title: '后夜',
        dataIndex: 'lateNight',
      },
      {
        title: '加班天数',
        dataIndex: 'overtimeDays',
      },
      {
        title: '值班天数',
        dataIndex: 'dutyDays',
      },
      {
        title: '节假日加班',
        dataIndex: 'holidayOvertime',
      },
      {
        title: '节假日值班',
        dataIndex: 'dutyHolidays',
      },
      {
        title: '事假',
        dataIndex: 'compassionateLeave',
      },
      {
        title: '带薪病假',
        dataIndex: 'paidSickLeave',
      },
      {
        title: '病假',
        dataIndex: 'sickLeave',
      },
      {
        title: '旷工',
        dataIndex: 'absenteeism',
      },
      {
        title: '丧假',
        dataIndex: 'bereavement',
      },
      {
        title: '婚假',
        dataIndex: 'marriageHoliday',
      },
      {
        title: '产假',
        dataIndex: 'maternityLeave',
      },
      {
        title: '工伤',
        dataIndex: 'injuryJob',
      },
      {
        title: '年假',
        dataIndex: 'annualLeave',
      },
      {
        title: '夜班餐补天数',
        dataIndex: 'nightShiftDays',
      },
      {
        title: '节假日加班工资',
        dataIndex: 'overtimePayDuringHolidays',
      },
      {
        title: '节假日值班工资',
        dataIndex: 'dutyPayHolidays',
      },
      {
        title: '前夜班补助',
        dataIndex: 'beforeNightShiftSubsidy',
      },
      {
        title: '后夜班补助',
        dataIndex: 'lateNightShiftSubsidy',
      },
      {
        title: '日常加班',
        dataIndex: 'dailyOvertime',
      },
      {
        title: '转正前出勤',
        dataIndex: 'attendanceBeforeCorrection',
      },
      {
        title: '转正后出勤',
        dataIndex: 'attendanceAfterCorrection',
      },
      {
        title: '转正前加班',
        dataIndex: 'overtimeBeforeCorrection',
      },
      {
        title: '转正后加班',
        dataIndex: 'overtimeAfterCorrection',
      },
      {
        title: '转正前应出勤',
        dataIndex: 'attendanceShouldBeforeCorrection',
      },
      {
        title: '转正后应出勤',
        dataIndex: 'attendanceShouldAfterCorrection',
      },
      {
        title: '转正前节假日加班',
        dataIndex: 'workingOvertimeBeforeHolidays',
      },
      {
        title: '转正后节假日加班',
        dataIndex: 'workingOvertimeAfterHolidays',
      },
      {
        title: '迟到',
        dataIndex: 'late',
      },
      {
        title: '早退',
        dataIndex: 'earlyRetreat',
      },
      {
        title: '产检',
        dataIndex: 'productionInspection',
      },
      {
        title: '哺乳假',
        dataIndex: 'lactationLeave',
      },
      {
        title: '探亲',
        dataIndex: 'visitFamily',
      },
      // {
      //   title: '操作',
      //   render: (text, record) => (
      //     <Fragment>
      //       <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
      //         <Icon type="edit" />
      //       </a>
      //       <Divider type="vertical" />
      //
      //     </Fragment>
      //   ),
      // },
    ];

    return (
      hasAccessKey('attendance.record.list') && (
      <PageHeaderLayout title="考勤查询">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                orgBaseList={this.state.orgBaseList}
                handleSearchTable={this.handleSearchTable.bind(this)}
                staffAllList={this.state.staffAllList}
              />
            </div>
            {/*<div className={styles.tableListOperator}>*/}
              {/*<Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>*/}
                {/*新建*/}
              {/*</Button>*/}
              {/*{selectedRows.length > 0 && (*/}
                {/*<span>*/}
                  {/*<Button>批量删除</Button>*/}
                {/*</span>*/}
              {/*)}*/}
            {/*</div>*/}
            <StandardTable
              scroll={{ x: 5000, y: 0 }}
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
        {/*<AddForm*/}
          {/*modalVisibleAdd={this.state.modalVisibleAdd}*/}
          {/*handleModalVisible={this.handleModalVisible.bind(this)}*/}
          {/*refreshTable={this.refreshTable.bind(this)}*/}
          {/*factoryIds={this.state.factoryIds}*/}
        {/*/>*/}
        {/*{this.state.modalVisibleEdit ? (*/}
          {/*<EditForm*/}
            {/*detailData={this.state.detailData}*/}
            {/*modalVisibleEdit={this.state.modalVisibleEdit}*/}
            {/*currentEditUserId={this.state.currentEditUserId}*/}
            {/*handleModalVisible={this.handleModalVisible.bind(this)}*/}
            {/*refreshTable={this.refreshTable.bind(this)}*/}
            {/*factoryIds={this.state.factoryIds}*/}
          {/*/>*/}
        {/*) : (*/}
          {/*''*/}
        {/*)}*/}
      </PageHeaderLayout>
      )
    );
  }
}
