import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Divider, Row, Col,Button, Upload, message } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import SearchForm from '../../../components/Staff/BaseInfo/SearchForm';

import {pageList} from '../../../services/staffBaseInfo';
import {exportErrExcel} from '../../../services/adjustWork';
import ModalOrgTree from '../../../components/Org/Org/ModalOrgTree';

import styles from './AdjustWork.less';
import { getByTypeCode } from '../../../services/systemCode';
import {getToken, hasAccessKey} from '../../../utils/authority';
import {serverUrlPre} from "../../../utils/request";

export default class AdjustWork extends PureComponent {
  state = {
    data: {},
    modalVisibleLeaveOffice: false, //离职
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    adjustWorkTemplateUrl: 'adjustWorkImport.zip',
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
                searchFormValues: {
                  pageNum: 1,
                  pageSize: 10,
                  jobStatus: d.rowId,
                },
              })
          );
        }
        // this.refreshTable();
      }
    });
    // this.refreshTable();
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    pageList(params).then(response => {
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
    params.deptId = sfvs.deptId;
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
   * 跳转页面
   */
  handleJumpPage = (rowId, type) => {
    const { dispatch } = store;
    dispatch(
      routerRedux.push({
        pathname: `/staff/ModalDetailForm/${rowId}/${type}`,
      })
    );
  };

  /**
   * 选择组织架构树回调
   * @param selectedKeys
   */
  handleClickTreeNode = selectedKeys => {
    if (selectedKeys.length > 0) {
      const sfvs = this.state.searchFormValues;
      sfvs.deptId = selectedKeys[0];
      this.setState({
        searchFormValues: sfvs,
      });
      this.refreshTable();
    } else {
      const sfvs = this.state.searchFormValues;
      sfvs.deptId = null;
      this.setState({
        searchFormValues: sfvs,
      });
      this.refreshTable();
    }
  };

  handleChange = info => {
    if (!!info.file.response) {
      if (info.file.response.code === 200) {
        message.success('导入成功');
        this.refreshTable();
      } else if (info.file.response.code === 205) {
        message.error('有部分异常数据导出');
        exportErrExcel(info.file.response.redisKey);
      } else {
        message.error(info.file.response.msg);
      }
    }
  };

  render() {
    const token = getToken();
    const { loading } = this.props;
    const columns = [
      {
        title: '工号',
        width: 130,
        dataIndex: 'staffNo',
      },
      {
        title: '系统工号',
        width: 100,
        // dataIndex: 'rowId',
        render: (text, record) => (
          <Fragment>
            {'10000000'.substring(0, 8 - record.rowId.toString().length) + record.rowId.toString()}
          </Fragment>
        ),
      },
      {
        title: '姓名',
        width: 100,
        dataIndex: 'staffName',
      },
      {
        title: '性别',
        width: 70,
        dataIndex: 'transNames.sex_name',
      },
      {
        title: '基地',
        width: 100,
        dataIndex: 'transNames.baseId_baseOrDeptName',
      },
      {
        title: '部门',
        width: 150,
        dataIndex: 'transNames.deptId_baseOrDeptName',
      },
      {
        title: '在职状态',
        width: 100,
        dataIndex: 'transNames.jobStatus_name',
      },
      {
        title: '任职日期',
        width: 100,
        dataIndex: 'entryDate',
      },
      {
        title: '职衔',
        width: 100,
        dataIndex: 'transNames.positionId_positionName',
      },
      {
        title: '职等/赋值名称',
        width: 100,
        dataIndex: 'transNames.gradeId_gradeName',
      },
      {
        title: '职级',
        width: 100,
        dataIndex: 'transNames.rankId_rankName',
      },
      {
        title: '用工形式',
        width: 100,
        dataIndex: 'transNames.workType_name',
      },
      {
        title: '操作',
        fixed: 'right',
        width: 150,
        render: (text, record) => (
          <Fragment>
            <Fragment>
              {hasAccessKey('staff.transaction.moveTransact') && (
              <a title="异动办理" onClick={() => this.handleJumpPage(record.rowId, 1)}>
                <Icon type="form" />
              </a>
              )}
            </Fragment>
            <Fragment>
              <Divider type="vertical" />
              {hasAccessKey('staff.transaction.disposeExpire') && (
              <a title="处理到期" onClick={() => this.handleJumpPage(record.rowId, 2)}>
                <Icon type="clock-circle-o" />
              </a>
              )}
            </Fragment>
            <Fragment>
              <Divider type="vertical" />
              {hasAccessKey('staff.transaction.moveQuery') && (
              <a title="异动查询" onClick={() => this.handleJumpPage(record.rowId, 3)}>
                <Icon type="file-text" />
              </a>
              )}
            </Fragment>
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey('staff.transaction.list') && (
      <PageHeaderLayout>
        <Row gutter={20}>
          <Col span={7} style={{paddingRight: 0}}>
            <Card>
              <ModalOrgTree
                handleClickTreeNode={this.handleClickTreeNode.bind(this)}
                refreshTable={this.refreshTable.bind(this)}
              />
            </Card>
          </Col>
          <Col span={17}>
            <Card bordered={false}>
              <div className={styles.tableList}>
                <div className={styles.tableListForm}>
                  <SearchForm handleSearchTable={this.handleSearchTable.bind(this)} />
                </div>
                <div className={styles.tableListOperator}>
                  {hasAccessKey('staff.transaction.export') && (
                    <a
                      href={`${serverUrlPre}/staff/adjustmentWork/download?filePath=${
                        this.state.adjustWorkTemplateUrl
                        }&token=${token}`}
                      title={'批量离职模板下载'}
                      alt="file"
                    >
                      <Button>
                        <Icon type="download"/>批量离职导入模板下载
                      </Button>
                    </a>
                  )}
                  {hasAccessKey('staff.transaction.import') && (
                    <Upload
                      name="excel"
                      action={`${serverUrlPre}/staff/adjustmentWork/importExcel?token=${token}`}
                      showUploadList={false}
                      onChange={this.handleChange}
                    >
                      <Button>
                        <Icon type="upload" />批量离职
                      </Button>
                    </Upload>
                  )}
                </div>
                <StandardTable
                  rowKey="rowId"
                  scroll={{ x: 1500, y: 0 }}
                  loading={loading}
                  data={this.state.data}
                  columns={columns}
                  onChange={this.handleStandardTableChange}
                />
              </div>
            </Card>
          </Col>
        </Row>
      </PageHeaderLayout>
      )
    );
  }
}
