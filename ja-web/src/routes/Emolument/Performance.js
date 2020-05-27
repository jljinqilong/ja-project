import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Dropdown, Menu, Upload } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import SearchForm from '../../components/Emolument/Performance/SearchForm';
import { performancePageList, exportErrExcel } from '../../services/performance';
import styles from './Performance.less';
import { serverUrlPre } from '../../utils/request';
import { getToken } from '../../utils/authority';
import { baseList } from '../../services/org';

export default class Performance extends PureComponent {
  state = {
    performanceTemplateUrl: 'performanceImport.zip',
    orgBaseList: [],
    data: [],
    selectedRows: [],
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
    baseList()
      .then(content => {
        this.setState({ orgBaseList: content.data });
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
    performancePageList(params)
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

  handleChange = info => {
    const { handleRefreshMoveOrMergeTree } = this;
    const { handleRefreshTree } = this.props;
    if (!!info.file.response) {
      if (info.file.response.code === 200) {
        message.success('导入成功');
        this.refreshTable(); //刷新表格
      } else if (info.file.response.code === 205) {
        message.error('有部分异常数据导出');
        exportErrExcel(info.file.response.redisKey);
      } else {
        message.error(info.file.response.msg);
      }
    }
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const token = getToken();

    const columns = [
      {
        title: '员工号',
        dataIndex: 'staffNo',
      },
      {
        title: '月份',
        dataIndex: 'month',
      },
      {
        title: '绩效金额',
        dataIndex: 'amountOfPerformance',
      },
    ];

    return (
      <PageHeaderLayout title="绩效导入">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                handleSearchTable={this.handleSearchTable.bind(this)}
                factoryIds={this.state.factoryIds}
                orgBaseList={this.state.orgBaseList}
              />
            </div>
            <div className={styles.tableListOperator}>
              <Button
                style={{ marginRight: '16px' }}
                href={`${serverUrlPre}/emolument/eltPerformance/download?filePath=${
                  this.state.performanceTemplateUrl
                }&token=${token}`}
              >
                下载导入模板
              </Button>
              <Upload
                name="excel"
                action={`${serverUrlPre}/emolument/eltPerformance/importExcel?token=${token}`}
                onChange={this.handleChange}
              >
                <Button type="primary">
                  <Icon type="upload" />导入绩效
                </Button>
              </Upload>
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
      </PageHeaderLayout>
    );
  }
}
