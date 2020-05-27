import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider, Upload } from 'antd';
import StandardTable from 'components/StandardTable';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';

import AddForm from '../../components/Emolument/SocialSecurity/ModalAddForm';
import EditForm from '../../components/Emolument/SocialSecurity/ModalEditForm';
import SearchForm from '../../components/Emolument/SocialSecurity/SearchForm';

import { baseList } from '../../services/org';
import {
  delSocialSecurity,
  pageList,
  getById,
  beachDel,
  empty,
} from '../../services/eltSocialSecurity';

import styles from './SocialSecurity.less';
import { serverUrlPre } from '../../utils/request';
import { getToken } from '../../utils/authority';

export default class SocialSecurity extends PureComponent {
  state = {
    data: [],
    orgBaseList: [],
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    selectedRows: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
    detailData: {},
    title: '社保规则列表',
    isChangeing: true,
    maintainValue: '维护社保规则',
    deleteBtnValue: '导入',
    isCheckBox: false,
    ids: '',
  };

  /**
   * 初始化
   */
  componentDidMount() {
    this.refreshTable();

    baseList().then(content => {
      this.setState({ orgBaseList: content.data });
    });
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
   * 选择行事件
   * @param rows
   */
  handleSelectRows = rows => {
    this.setState({
      selectedRows: rows,
      ids: rows.map(d => d.rowId).join(','),
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
      getById(rowId).then(data => {
        this.setState({
          detailData: data.data,
          modalVisibleEdit: !!flag,
          currentEditUserId: rowId,
        });
      });
    } else if (type === 2) {
      const { ids } = this.state;
      const rt = this.refreshTable;
      beachDel(ids).then(data => {
        if (data.code === 150) {
          message.success('删除成功');
          rt();
        } else if (data.code === 400) {
          message.error('删除失败');
        }
      });
    }
  };

  /**
   * 删除用户
   * @param rowId
   */
  showDelUserConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此社保规则吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delSocialSecurity(rowId)
          .then(data => {
            if (data.code === 150) {
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

  changeTitleValue = () => {
    if (!!this.state.isChangeing) {
      const tableTitle = (
        <span>
          维护社保规则<a
            style={{ padding: 20, fontSize: 14 }}
            onClick={() => {
              this.setState({
                title: '社保规则列表',
                isChangeing: true,
                maintainValue: '维护社保规则',
                deleteBtnValue: '导入',
              });
            }}
          >
            {' '}
            返回
          </a>{' '}
        </span>
      );
      this.setState({
        isChangeing: false,
        title: tableTitle,
        maintainValue: '清空',
      });
      console.log(this.state.isChangeing);
    } else {
      const rt = this.refreshTable;
      Modal.confirm({
        title: '清空确认',
        content: '确定清空社保规则列表吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          empty().then(data => {
            if (data.code === 200) {
              message.success('清空成功');
              rt();
            } else if (data.code === 400) {
              message.error('清空失败');
            }
          });
        },
        onCancel() {},
      });
    }
  };

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const { isChangeing } = this.state;
    const token = getToken();
    const columns = [
      {
        title: '基地',
        width: 150,
        dataIndex: 'transNames.baseId_baseOrDeptName',
      },
      {
        title: '规则名称',
        width: 150,
        dataIndex: 'ruleName',
      },
      {
        title: '缴费基数',
        width: 150,
        dataIndex: 'socialInsuranceBase',
      },
      {
        title: '个人养老比例',
        width: 150,
        dataIndex: 'iindividualPersonRatio',
      },
      {
        title: '个人养老金额',
        width: 150,
        dataIndex: 'personalPensionAmount',
      },
      {
        title: '个人医疗比例',
        width: 150,
        dataIndex: 'personalMedicalRatio',
      },
      {
        title: '个人医疗金额',
        width: 150,
        dataIndex: 'personalMedicalAmount',
      },
      {
        title: '个人工伤比例',
        width: 150,
        dataIndex: 'personalInjuryRatio',
      },
      {
        title: '个人工伤金额',
        width: 150,
        dataIndex: 'personalInjuryAmount',
      },
      {
        title: '个人失业比例',
        width: 150,
        dataIndex: 'personalUnemploymentRatio',
      },
      {
        title: '个人失业金额',
        width: 150,
        dataIndex: 'personalUnemploymentAmount',
      },
      {
        title: '个人生育比例',
        width: 150,
        dataIndex: 'personalFertilityRatio',
      },
      {
        title: '个人生育金额',
        width: 150,
        dataIndex: 'personalFertilityAmount',
      },
      {
        title: '公司养老比例',
        width: 150,
        dataIndex: 'companyPensionRatio',
      },
      {
        title: '公司养老金额',
        width: 150,
        dataIndex: 'companyPensionAmount',
      },
      {
        title: '公司医疗比例',
        width: 150,
        dataIndex: 'companyMedicalRatio',
      },
      {
        title: '公司医疗金额',
        width: 150,
        dataIndex: 'companyMedicalAmount',
      },
      {
        title: '公司工伤比例',
        width: 150,
        dataIndex: 'companyInjuryRatio',
      },
      {
        title: '公司工伤金额',
        width: 150,
        dataIndex: 'companyInjuryAmount',
      },
      {
        title: '公司失业比例',
        width: 150,
        dataIndex: 'companyUnemploymentRatio',
      },
      {
        title: '公司失业金额',
        width: 150,
        dataIndex: 'companyUnemploymentAmount',
      },
      {
        title: '公司生育比例',
        width: 150,
        dataIndex: 'companyBirthRatio',
      },
      {
        title: '公司生育金额',
        width: 150,
        dataIndex: 'companyBirthAmount',
      },
      {
        title: '备注',
        width: 300,
        dataIndex: 'remarks',
      },
      {
        title: isChangeing ? '操作' : '',
        width: 150,
        fixed: isChangeing ? 'right' : '',
        render: (text, record) =>
          isChangeing ? (
            <Fragment>
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
              <Divider type="vertical" />
              <a title="删除" onClick={() => this.showDelUserConfirm(record.rowId)}>
                <Icon type="delete" />
              </a>
            </Fragment>
          ) : (
            ''
          ),
      },
    ];

    return (
      <PageHeaderLayout title={this.state.title}>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                orgBaseList={this.state.orgBaseList}
                handleSearchTable={this.handleSearchTable.bind(this)}
              />
            </div>
            <div className={styles.tableListOperator}>
              <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                新建
              </Button>
              <Button className={styles.btn1} onClick={this.changeTitleValue}>
                {this.state.maintainValue}
              </Button>
              {selectedRows.length > 0 && (
                <span>
                  <Button onClick={() => this.handleModalVisible(true, 2)}>批量删除</Button>
                </span>
              )}
              <Upload
                name="excel"
                action={`${serverUrlPre}/emolument/eltAccumulationFund/importExcel?token=${token}`}
                onChange={this.handleChange}
              >
                <Button>
                  <Icon type="upload" />导入公积金规则
                </Button>
              </Upload>
            </div>
            <StandardTable
              rowKey="rowId"
              isCheckBox={true}
              selectedRows={selectedRows}
              loading={loading}
              data={this.state.data}
              columns={columns}
              scroll={{ x: 3750, y: 0 }}
              onSelectRow={this.handleSelectRows}
              onChange={this.handleStandardTableChange}
            />
          </div>
        </Card>
        <AddForm
          orgBaseList={this.state.orgBaseList}
          modalVisibleAdd={this.state.modalVisibleAdd}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        {this.state.modalVisibleEdit ? (
          <EditForm
            detailData={this.state.detailData}
            orgBaseList={this.state.orgBaseList}
            modalVisibleEdit={this.state.modalVisibleEdit}
            currentEditUserId={this.state.currentEditUserId}
            handleModalVisible={this.handleModalVisible.bind(this)}
            refreshTable={this.refreshTable.bind(this)}
          />
        ) : (
          ''
        )}
      </PageHeaderLayout>
    );
  }
}
