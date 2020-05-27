import React, { PureComponent, Fragment } from 'react';
import { Form, Card } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import { queryAdjustmentWorkByChangeType } from '../../../services/adjustWork';

class ModalReturnRehireList extends PureComponent {
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
    params.changeType = 'RETURN_REHIRE';
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
        title: '新基地',
        dataIndex: 'newBase',
      },
      {
        title: '新部门',
        dataIndex: 'newDept',
      },
      {
        title: '新职衔',
        dataIndex: 'newPosition',
      },
      {
        title: '新职等/赋值名称',
        dataIndex: 'newGrade',
      },
      {
        title: '新职级',
        dataIndex: 'newRank',
      },
      {
        title: '在职状态',
        dataIndex: 'jobStatus',
      },
      {
        title: '用工日期',
        render: (text, record) => {
          return moment(record.workDate).format('YYYY-MM-DD');
        },
      },
      {
        title: '入职前工龄',
        dataIndex: 'entryBeforeAge',
      },
    ];

    return (
      <Card title={'返聘信息'} bordered={false}>
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

export default Form.create({})(ModalReturnRehireList);
