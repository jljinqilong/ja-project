import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Dropdown, Menu, Upload } from 'antd';
import moment from 'moment';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SearchForm from '../../../components/Attendance/CreditCardLog/SearchForm';
import { creditCardLogPageList, exportErrExcel } from '../../../services/creditCardLog';
import styles from './CreditCardLog.less';
import { serverUrlPre } from '../../../utils/request';
import { getToken,hasAccessKey} from '../../../utils/authority';

export default class CreditCardLog extends PureComponent {
  state = {
    data: [],
    creditCardLogTemplateUrl: 'creditCardLogImport.zip',
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
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    creditCardLogPageList(params)
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
        title: '工号',
        dataIndex: 'staffNo',
      },
      {
        title: '姓名',
        dataIndex: 'staffName',
      },
      {
        title: '部门',
        dataIndex: 'orgName',
      },
      {
        title: '状态',
        dataIndex: 'status',
      },
      {
        title: '考勤机',
        dataIndex: 'machineName',
      },
      {
        title: '时间',
        render: (text, record) => {
          return moment(record.time).format('YYYY-MM-DD HH:mm:ss');
        },
      },
    ];

    return (
      <PageHeaderLayout title="刷卡日志">
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                handleSearchTable={this.handleSearchTable.bind(this)}
                factoryIds={this.state.factoryIds}
              />
            </div>

            <div className={styles.tableListOperator}>
              {hasAccessKey(`attendance.creditCardLog.import`) && (
                <Fragment>
                  <Button
                    style={{ marginRight: '16px' }}
                    href={`${serverUrlPre}/attendance/creditCardLog/download?filePath=${
                      this.state.creditCardLogTemplateUrl
                    }&token=${token}`}
                  >
                    下载导入模板
                  </Button>
                  <Upload
                    name="excel"
                    action={`${serverUrlPre}/attendance/creditCardLog/importExcel?token=${token}`}
                    onChange={this.handleChange}
                  >
                    <Button type="primary">
                      <Icon type="upload" />导入刷卡日志
                    </Button>
                  </Upload>
                </Fragment>
              )}
            </div>
            {hasAccessKey(`attendance.creditCardLog.list`) && (
              <StandardTable
                rowKey="rowId"
                selectedRows={selectedRows}
                loading={loading}
                data={this.state.data}
                columns={columns}
                onSelectRow={this.handleSelectRows}
                onChange={this.handleStandardTableChange}
              />
            )}
          </div>
        </Card>
      </PageHeaderLayout>
    );
  }
}
