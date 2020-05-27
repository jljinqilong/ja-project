import React, { PureComponent } from 'react';
import { Form } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import SearchForm from '../../../components/Staff/AdjustWorkQuery/SearchForm';
import styles from '../../../routes/Staff/AdjustWork/AdjustWork.less';
import {queryAdjustmentWorkByChangeType} from "../../../services/adjustWork";

class ModalToLoanList extends PureComponent {
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
        dataIndex: 'temporarilyStartDate',
      },
      {
        title: '结束时间',
        dataIndex: 'temporarilyEndDate',
      },
      {
        title: '描述',
        dataIndex: 'changeReason',
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
          scroll={{ x: 2000}}
          data={this.props.temData}
          columns={columns}
          onChange={this.handleStandardTableChange}
        />
        </div>
      </div>
    );
  }
}

export default Form.create({})(ModalToLoanList);
