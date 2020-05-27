import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Row, Col, Dropdown, Menu, Upload, } from 'antd';
import { routerRedux } from 'dva/router';
import store from '../../../index';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';

import AddForm from '../../../components/Staff/BaseInfo/ModalAddForm';
import ModalOrgTree from '../../../components/Org/Org/ModalOrgTree';
import SearchForm from '../../../components/Staff/BaseInfo/SearchForm';

import { delStaff, pageList, exportExcel, exportErrExcel ,exportPositionErrExcel} from '../../../services/staffBaseInfo';

import { serverUrlPre } from '../../../utils/request';

import styles from './StaffBaseInfo.less';
import { getToken, hasAccessKey } from '../../../utils/authority';
import { getByTypeCode } from '../../../services/systemCode';

export default class User extends PureComponent {
  state = {
    data: {},
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    exportParams: {},
    loading: false,
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    staffBaseInfoTemplateUrl: 'staffBaseInfoImport.zip',
    staffBaseInfoPositionTemplateUrl :'staffBaseInfoPositionTemplateUrl.zip',
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
    let exportParams = {};
    exportParams.staffNo = params.staffNo;
    exportParams.staffName = params.staffName;
    exportParams.identityNo = params.identityNo;
    exportParams.deptId = params.deptId;
    exportParams.jobStatus = params.jobStatus;
    this.setState({
      exportParams: exportParams,
    });
    pageList(params)
      .then(response => {
        if (!!response.data) {
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
    let exportParams = {};
    exportParams.staffNo = params.staffNo;
    exportParams.staffName = params.staffName;
    exportParams.identityNo = params.identityNo;
    exportParams.deptId = sfvs.deptId;
    exportParams.jobStatus = params.jobStatus;
    params.pageNum = sfvs.pageNum;
    params.pageSize = sfvs.pageSize;
    params.deptId = sfvs.deptId;
    this.setState({
      searchFormValues: params,
      exportParams: exportParams,
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
   * 选择行事件
   * @param rows
   */
  handleSelectRows = rows => {
    this.setState({
      selectedRows: rows,
    });
  };

  /**
   * 显示/隐藏弹窗
   * @param flag
   */
  handleModalVisible = (flag, type, rowId) => {
    if (type === 0) {
      this.setState({
        modalVisibleAdd: !!flag,
      });
    } else if (type === 1) {
      this.setState({
        modalVisibleEdit: !!flag,
        currentEditStaffId: rowId,
      });
    }
  };

  /**
   * 跳转页面
   */
  handleJumpPage = (rowId, staffNo) => {
    const { dispatch } = store;
    dispatch(
      routerRedux.push({
        pathname: `/staff/ModelTabs/${rowId}/${staffNo}`,
      })
    );
  };

  /**
   * 导出
   * @param flag
   * @param type
   * @param rowId
   */
  handleExport = () => {
    const { list } = this.state.data;
    if (list.length <= 0) {
      message.warn('没有数据可以导出');
      return;
    }
    const params = this.state.exportParams;
    if(params.deptId === undefined ||params.deptId=== null || params.deptId === '1'){
      message.warn('请选择基地或部门');
    }else{
      exportExcel(params);
    }
  };

  /**
   * 删除
   * @param rowId
   */
  showDelStaffConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此员工吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delStaff(rowId)
          .then(data => {
            if (data.code === 200) {
              message.success('删除成功');
              rt();
            } else if (data.code === 400) {
              message.error('删除失败');
            }
          })
          .catch(() => {
            message.error('删除异常');
          });
      },
      onCancel() {},
    });
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

  handleChange2 = info => {
    if (!!info.file.response) {
      if (info.file.response.code === 200) {
        message.success('导入成功');
        this.refreshTable();
      } else if (info.file.response.code === 205) {
        message.error('有部分异常数据导出');
        exportPositionErrExcel(info.file.response.redisKey);
      } else {
        message.error(info.file.response.msg);
      }
    }
  };

  render() {
    const token = getToken();
    const menu = (
      <Menu>
        <Menu.Item>
          {hasAccessKey('staff.baseInfo.importInfo') && (
            <Upload
              name="excel"
              action={`${serverUrlPre}/staff/baseInfo/importExcel?token=${token}`}
              showUploadList={false}
              onChange={this.handleChange}
            >
              <Button>
                <Icon type="upload" />导入员工基础信息
              </Button>
            </Upload>
          )}
          <br/>
          {hasAccessKey('staff.baseInfo.importPosition') && (
            <Upload
              name="excel"
              action={`${serverUrlPre}/staff/baseInfo/importPositionExcel?token=${token}`}
              showUploadList={false}
              onChange={this.handleChange2}
            >
              <Button>
                <Icon type="upload" />导入员工职衔
              </Button>
            </Upload>
          )}
        </Menu.Item>
      </Menu>
    );
    const menuExcelTemplate = (
      <Menu>
        <Menu.Item>
          <a
            href={`${serverUrlPre}/staff/baseInfo/download?filePath=${
              this.state.staffBaseInfoTemplateUrl
            }&token=${token}`}
            title={'员工基础信息导入模板下载'}
            alt="file"
          >
            员工基础信息导入模板下载
          </a>
        </Menu.Item>
        <Menu.Item>
          <a
            href={`${serverUrlPre}/staff/baseInfo/download?filePath=${
              this.state.staffBaseInfoPositionTemplateUrl
              }&token=${token}`}
            title={'员工职衔导入模板下载'}
            alt="file"
          >
            员工职衔导入模板下载
          </a>
        </Menu.Item>
      </Menu>
    );
    const { loading } = this.props;
    const { selectedRows } = this.state;
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
        width: 100,
        render: (text, record) => (
          <Fragment>
            <a title="详情" onClick={() => this.handleJumpPage(record.rowId, record.staffNo)}>
              <Icon type="file-text" />
            </a>
            {hasAccessKey(`staff.baseInfo.delete`) && (
              <span>
                <Divider type="vertical" />
                <a title="删除" onClick={() => this.showDelStaffConfirm(record.rowId)}>
                  <Icon type="delete" />
                </a>
              </span>
            )}
          </Fragment>
        ),
      },
    ];

    return (
      hasAccessKey(`staff.baseInfo.list`) && (
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
                    {hasAccessKey(`staff.baseInfo.add`) && (
                      <Button
                        icon="plus"
                        type="primary"
                        onClick={() => this.handleModalVisible(true, 0)}
                      >
                        新增
                      </Button>
                    )}
                    <Dropdown overlay={menuExcelTemplate} placement="bottomRight">
                      <Button>下载导入模板</Button>
                    </Dropdown>
                    <Dropdown overlay={menu} placement="bottomRight">
                      <Button>导入员工</Button>
                    </Dropdown>
                    {hasAccessKey('staff.baseInfo.export') && (
                      <Button type="primary" onClick={() => this.handleExport()}>
                        导出
                      </Button>
                    )}
                    {selectedRows.length > 0 && (
                      <span>
                        <Button>批量删除</Button>
                      </span>
                    )}
                  </div>
                  <StandardTable
                    rowKey="rowId"
                    scroll={{ x: 1450, y: 0 }}
                    selectedRows={selectedRows}
                    loading={loading}
                    data={this.state.data}
                    columns={columns}
                    onSelectRow={this.handleSelectRows}
                    onChange={this.handleStandardTableChange}
                  />
                </div>
              </Card>
            </Col>
          </Row>
          <AddForm
            modalVisibleAdd={this.state.modalVisibleAdd}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
        </PageHeaderLayout>
      )
    );
  }
}
