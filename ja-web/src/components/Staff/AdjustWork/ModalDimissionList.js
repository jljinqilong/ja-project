import React, { PureComponent, Fragment } from 'react';
import { Form, Card } from 'antd';

import moment from 'moment';
import StandardTable from 'components/StandardTable';
import { queryAdjustmentWorkByChangeType } from '../../../services/adjustWork';

class ModalDimissionList extends PureComponent {
  state = {
    staffId: this.props.staffId,
    data: [],
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
    params.staffId = this.props.staffId;
    params.changeType = 'DIMISSION';
    queryAdjustmentWorkByChangeType(params)
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
  render() {
    const { loading } = this.props;
    const columns = [
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
        render: (text, record) => {
          return moment(record.startPerformDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '结束履行时间',
        render: (text, record) => {
          return moment(record.endPerformDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '未满服务期赔偿金额',
        dataIndex: 'underServiceCompensate',
      },
    ];

    return (
      <Card title={'离职信息'} bordered={false}>
        <StandardTable
          rowKey="rowId"
          loading={loading}
          scroll={{ x: 1800}}
          data={this.state.data}
          // data={[]}
          columns={columns}
          onChange={this.handleStandardTableChange}
        />
      </Card>
    );
  }
}

export default Form.create({})(ModalDimissionList);
