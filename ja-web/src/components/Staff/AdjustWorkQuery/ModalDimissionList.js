import React, { PureComponent } from 'react';
import { Form } from 'antd';

import moment from 'moment';
import StandardTable from 'components/StandardTable';
import SearchForm from '../../../components/Staff/AdjustWorkQuery/SearchForm';
import styles from '../../../routes/Staff/AdjustWork/AdjustWork.less';
import {queryAdjustmentWorkByChangeType} from "../../../services/adjustWork";

class ModalDimissionList extends PureComponent {
  state = {
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
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
    this.props.refreshTable(params);
  };

  /**
   * 搜索
   * @param params
   */
  handleSearchTable = params => {
    const sfvs = this.state.searchFormValues;
    params.pageNum = sfvs.pageNum;
    params.pageSize = sfvs.pageSize;
    params.deptId = this.props.deptId;
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
    sfvs.deptId = this.props.deptId;
    this.setState({
      searchFormValues: sfvs,
    });
    this.refreshTable();
  };
  render() {
    const { loading } = this.props;
    const columns = [
      {
        title: '姓名',
        dataIndex: 'transNames.staffId_staffName',
      },
      {
        title: '工号',
        dataIndex: 'transNames.staffId_staffNo',
      },
      {
        title: '离职类型',
        dataIndex: 'leaveType',
      },
      {
        title: '离职原因',
        dataIndex: 'leaveReason',
      },
      {
        title: '离职日期',
        render: (text, record) => {
          return moment(record.leaveDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '离职后去向',
        dataIndex: 'leaveDirection',
      },
      {
        title: '是否加入黑名单',
        dataIndex: 'isBlacklist',
      },
      {
        title: '加入黑名单原因',
        dataIndex: 'blacklistReason',
      },
      {
        title: '是否有代通知金',
        dataIndex: 'isLieuNoticeWages',
      },
      {
        title: '是否有补偿金',
        dataIndex: 'isCompensatoryPayment',
      },
      {
        title: '补偿月数',
        dataIndex: 'compensationMonth',
      },
      {
        title: '补偿金额',
        dataIndex: 'amountCompensation',
      },
      {
        title: '是否履行竞业限制',
        dataIndex: 'isPerformCompetitiveRestriction',
      },
      {
        title: '是否签订培训协议',
        dataIndex: 'isTrainAgreement',
      },
      {
        title: '开始履行时间',
        dataIndex: 'startPerformDate',
      },
      {
        title: '结束履行时间',
        dataIndex: 'endPerformDate',
      },
      {
        title: '未满服务期赔偿金额',
        dataIndex: 'underServiceCompensate',
      },
      {
        title: '创建时间',
        dataIndex: 'createdTime',
      },
    ];

    return (
      <div className={styles.tableList}>
        <div className={styles.tableListForm}>
          <SearchForm
            handleSearchTable={this.handleSearchTable.bind(this)}
            refreshTreeData={this.props.refreshTreeData.bind(this)}
          />
        </div>
        <div>
          <StandardTable
            rowKey="rowId"
            loading={loading}
            scroll={{ x: 2300}}
            data={this.props.dimData}
            columns={columns}
            onChange={this.handleStandardTableChange}
          />
        </div>
      </div>
    );
  }
}

export default Form.create({})(ModalDimissionList);
