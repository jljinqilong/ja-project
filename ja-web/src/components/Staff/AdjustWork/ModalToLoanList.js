import React, { PureComponent, Fragment } from 'react';
import { Form, Card } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import { queryAdjustmentWorkByChangeType } from '../../../services/adjustWork';
import { hasAccessKey } from '../../../utils/authority';

class ModalToLoanList extends PureComponent {
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
    params.changeType = 'TEMPORARILY';
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
        title: '原基地',
        dataIndex: 'originalBase',
      },
      {
        title: '原部门',
        dataIndex: 'originalDept',
      },
      {
        title: '原职衔',
        dataIndex: 'originalPosition',
      },
      {
        title: '原职级',
        dataIndex: 'originalRank',
      },
      {
        title: '原职等/赋值名称',
        dataIndex: 'originalGrade',
      },
      {
        title: '借调类型',
        dataIndex: 'temporarilyType',
      },
      {
        title: '借调基地',
        dataIndex: 'newBase',
      },
      {
        title: '借调部门',
        dataIndex: 'newDept',
      },
      {
        title: '借调职衔',
        dataIndex: 'newPosition',
      },
      {
        title: '借调职级',
        dataIndex: 'newRank',
      },
      {
        title: '借调职等/赋值名称',
        dataIndex: 'newGrade',
      },
      {
        title: '开始时间',
        render: (text, record) => {
          return moment(record.temporarilyStartDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '结束时间',
        render: (text, record) => {
          return moment(record.temporarilyEndDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '描述',
        dataIndex: 'changeReason',
      },
    ];

    return (
      <Card title={'借调信息'} bordered={false}>
        <StandardTable
          rowKey="rowId"
          loading={loading}
          data={this.state.data}
          // data={[]}
          columns={columns}
          onChange={this.handleStandardTableChange}
        />
      </Card>
    );
  }
}

export default Form.create({})(ModalToLoanList);
