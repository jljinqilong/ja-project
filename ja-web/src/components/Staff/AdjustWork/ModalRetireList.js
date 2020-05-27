import React, { PureComponent, Fragment } from 'react';
import { Form, Card } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import { queryAdjustmentWorkByChangeType } from '../../../services/adjustWork';

class ModalRetireList extends PureComponent {
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
    params.changeType = 'RETIRE';
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
        title: '退休类型',
        dataIndex: 'retireType',
      },
      {
        title: '退休时间',
        render: (text, record) => {
          return moment(record.retireDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '退休后管理单位',
        dataIndex: 'retireManagementUnit',
      },
      {
        title: '审批单位',
        dataIndex: 'examineUnit',
      },
      {
        title: '批准工号',
        dataIndex: 'approvalNumber',
      },
      {
        title: '其他说明',
        dataIndex: 'otherExplain',
      },
    ];

    return (
      <Card title={'退休信息'} bordered={false}>
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

export default Form.create({})(ModalRetireList);
