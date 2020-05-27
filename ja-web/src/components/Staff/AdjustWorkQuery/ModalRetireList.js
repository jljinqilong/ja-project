import React, { PureComponent } from 'react';
import { Form } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import SearchForm from '../../../components/Staff/AdjustWorkQuery/SearchForm';
import styles from '../../../routes/Staff/AdjustWork/AdjustWork.less';
import {queryAdjustmentWorkByChangeType} from "../../../services/adjustWork";

class ModalRetireList extends PureComponent {
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
        title: '退休类型',
        dataIndex: 'retireType',
      },
      {
        title: '退休时间',
        dataIndex: 'retireDate',
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
            data={this.props.retData}
            columns={columns}
            onChange={this.handleStandardTableChange}
          />
        </div>
      </div>
    );
  }
}

export default Form.create({})(ModalRetireList);
