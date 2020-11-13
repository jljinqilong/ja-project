import React, { PureComponent, Fragment } from 'react';
import { Card, Icon, Button, Modal, message, Divider } from 'antd';
import StandardTable from 'components/StandardTable';

import moment from 'moment';

import DetailsForm from '../../../components/Staff/Contract/ModalDetailsForm';
import AddForm from '../../../components/Staff/Contract/ModalAddForm';
import EditForm from '../../../components/Staff/Contract/ModalEditForm';
import RelieveForm from '../../../components/Staff/Contract/ModalRelieveForm';
import EndForm from '../../../components/Staff/Contract/ModalEndForm';
import RenewForm from '../../../components/Staff/Contract/ModalRenewForm';
import DiscontinueForm from '../../../components/Staff/Contract/ModalDiscontinueForm';
import SearchForm from '../../../components/Staff/Contract/SearchForm';

import { delContract, pageList } from '../../../services/staffContract';
import { queryBaseInfoForParams } from '../../../services/staffBaseInfo';
import { getByTypeCode } from '../../../services/systemCode';
import { baseList } from '../../../services/org';
import { hasAccessKey } from '../../../utils/authority';

import styles from './Contract.less';

export default class Contract extends PureComponent {
  state = {
    // data: {},
    modalVisibleAdd: false,
    modalVisibleEdit: false,
    modalVisibleRelieve: false,
    modalVisibleTerminate: false,
    modalVisibleRenew: false,
    modalDetails: false,
    modalDiscontinue: false,
    list1: [],
    selectedRows: [],
    jobCode: [],
    staffAllList: [],
    allBaseList: [],
    contractTypeCode: [],
    contractPeriodTypeCode: [],
    contractStateCode: [],
    renewStatusCode: [],
    YesOrNoCode: [],
    searchFormValues: {
      pageNum: 1,
      pageSize: 10,
    },
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
                jobStatus: d.rowId,
              })
          );
        }
      }
    });

    // this.refreshTable();

    getByTypeCode({ typeCode: 'CONTRACT_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          contractTypeCode: content.data,
        });
      }
    });

    getByTypeCode({ typeCode: 'CONTRACT_PERIOD_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          contractPeriodTypeCode: content.data,
        });
      }
    });

    getByTypeCode({ typeCode: 'CONTRACT_STATE' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          contractStateCode: content.data,
        });
      }
    });

    getByTypeCode({ typeCode: 'RENEW_STATUS' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          renewStatusCode: content.data,
        });
      }
    });

    getByTypeCode({ typeCode: 'YES_OR_NO' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          YesOrNoCode: content.data,
        });
      }
    });

    baseList().then(content => {
      if (!!content && !!content.data) {
        this.setState({
          allBaseList: content.data,
        });
      }
    });
  }

  /**
   * 请求数据
   */
  refreshTable = params => {
    if (!params) {
      params = this.state.searchFormValues;
    }
    this.props.refreshTableContract(params);
    // pageList(params)
    //   .then(response => {
    //     if (response.data !== undefined) {
    //       this.setState({
    //         data: {
    //           list: response.data.list,
    //           pagination: {
    //             total: response.data.total,
    //           },
    //         },
    //       });
    //     }
    //   });
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
      const jobStatus = this.state.jobStatus;
      const deptId = this.props.deptId;
      console.log(`---===${deptId}`);

      if (!!deptId && deptId !== 1) {
        queryBaseInfoForParams({ jobStatus: jobStatus, deptId: deptId }).then(content => {
          if (!!content && !!content.data) {
            this.setState({
              staffAllList: content.data,
            });
          }
        });
      }
      this.setState({
        modalVisibleAdd: !!flag,
        currentAddUserId: rowId,
      });
    } else if (type === 1) {
      this.setState({
        modalVisibleEdit: !!flag,
        currentEditUserId: rowId,
      });
    } else if (type === 2) {
      this.setState({
        modalVisibleRelieve: !!flag,
        currentRelieveUserId: rowId,
      });
    } else if (type === 3) {
      this.setState({
        modalVisibleRenew: !!flag,
        currentRenewUserId: rowId,
      });
    } else if (type === 4) {
      this.setState({
        modalVisibleTerminate: !!flag,
        currentTerminateUserId: rowId,
      });
    } else if (type === 5) {
      this.setState({
        modalDetails: !!flag,
        currentDetailsUserId: rowId,
      });
    } else if (type === 6) {
      this.setState({
        modalDiscontinue: !!flag,
        currentDiscontinueUserId: rowId,
      });
    }
  };

  /**
   * 删除合同
   * @param rowId
   */
  showDelContractConfirm = rowId => {
    const rt = this.refreshTable;
    Modal.confirm({
      title: '删除确认',
      content: '确定删除此合同吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        delContract(rowId)
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

  render() {
    const { loading } = this.props;
    const { selectedRows } = this.state;
    const columns = [
      {
        title: '合同编号',
        dataIndex: 'contractNo',
      },
      {
        title: '姓名',
        dataIndex: 'transNames.staffId_staffName',
      },
      {
        title: '工号',
        dataIndex: 'transNames.staffId_staffNo',
        //transNames.staffId
      },
      {
        title: '类型',
        dataIndex: 'transNames.contractType_name',
      },
      {
        title: '期限类型',
        dataIndex: 'transNames.contractPeriodType_name',
      },
      {
        title: '甲方',
        dataIndex: 'owner',
      },
      {
        title: '合同生效日期',
        dataIndex: 'contractDateStart',
      },
      {
        title: '合同终止日期',
        dataIndex: 'contractDateEnd',
      },
      {
        title: '合同剩余天数',
        render: (text, record) => {
          return moment(record.contractDateEnd).diff(moment().format('YYYY-MM-DD'), 'days') < 0
            ? '0'
            : moment(record.contractDateEnd).diff(moment().format('YYYY-MM-DD'), 'days');
        },
      },
      {
        title: '合同状态',
        dataIndex: 'transNames.contractState_name',
      },
      {
        title: '续签状态',
        dataIndex: 'transNames.renewStatus_name',
      },
      {
        title: '操作',
        fixed: 'right',
        width: 150,
        render: (text, record) => (
          <Fragment>
            <a title="详情" onClick={() => this.handleModalVisible(true, 5, record.rowId)}>
              <Icon type="file-text" />
            </a>
            <Divider type="vertical" />
            {hasAccessKey('staff.contract.update') && (
              <a title="编辑" onClick={() => this.handleModalVisible(true, 1, record.rowId)}>
                <Icon type="edit" />
              </a>
            )}

            {/*<Divider type="vertical" />
            <a title="删除" onClick={() => this.showDelContractConfirm(record.rowId)}>
              <Icon type="delete" />
            </a>*/}
            {record.contractState === '3' &&
              record.renewStatus !== '1' && <Divider type="vertical" />}
            {hasAccessKey('staff.contract.renew') &&
              record.contractState === '3' &&
              record.renewStatus !== '1' && (
                <a title="续签合同" onClick={() => this.handleModalVisible(true, 3, record.rowId)}>
                  <Icon type="file" />
                </a>
              )}
            {record.contractState === '3' &&
              record.renewStatus !== '1' && <Divider type="vertical" />}
            {hasAccessKey('staff.contract.termination') &&
              record.contractState === '3' &&
              record.renewStatus !== '1' && (
                <a title="终止合同" onClick={() => this.handleModalVisible(true, 4, record.rowId)}>
                  <Icon type="close" />
                </a>
              )}
            {record.contractState === '2' && <Divider type="vertical" />}
            {hasAccessKey('staff.contract.remove') &&
              record.contractState === '2' && (
                <a title="解除合同" onClick={() => this.handleModalVisible(true, 2, record.rowId)}>
                  <Icon type="disconnect" />
                </a>
              )}
            {record.contractState === '2' && <Divider type="vertical" />}
            {hasAccessKey('staff.contract.suspend') &&
              record.contractState === '2' && (
                <a title="中止合同" onClick={() => this.handleModalVisible(true, 6, record.rowId)}>
                  <Icon type="pause" />
                </a>
              )}
            {record.contractState === '6' && <Divider type="vertical" />}
            {hasAccessKey('staff.contract.remove') &&
              record.contractState === '6' && (
                <a title="解除合同" onClick={() => this.handleModalVisible(true, 2, record.rowId)}>
                  <Icon type="disconnect" />
                </a>
              )}
            {record.contractState === '6' &&
              record.renewStatus !== '1' && <Divider type="vertical" />}
            {hasAccessKey('staff.contract.renew') &&
              record.contractState === '6' &&
              record.renewStatus !== '1' && (
                <a title="续签合同" onClick={() => this.handleModalVisible(true, 3, record.rowId)}>
                  <Icon type="file" />
                </a>
              )}
          </Fragment>
        ),
      },
    ];

    return (
      <div>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <SearchForm
                contractTypeCode={this.state.contractTypeCode}
                contractStateCode={this.state.contractStateCode}
                renewStatusCode={this.state.renewStatusCode}
                handleSearchTable={this.handleSearchTable.bind(this)}
              />
            </div>
            <div className={styles.tableListOperator}>
              {hasAccessKey('staff.contract.add') && (
                <Button icon="plus" type="primary" onClick={() => this.handleModalVisible(true, 0)}>
                  新建
                </Button>
              )}
            </div>
            <StandardTable
              rowKey="rowId"
              selectedRows={selectedRows}
              loading={loading}
              data={this.props.contractData}
              columns={columns}
              scroll={{ x: 1500 }}
              onSelectRow={this.handleSelectRows}
              onChange={this.handleStandardTableChange}
            />
          </div>
        </Card>
        <DetailsForm
          modalDetails={this.state.modalDetails}
          currentDetailsUserId={this.state.currentDetailsUserId}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <AddForm
          modalVisibleAdd={this.state.modalVisibleAdd}
          currentAddUserId={this.state.currentAddUserId}
          staffAllList={this.state.staffAllList}
          allBaseList={this.state.allBaseList}
          contractTypeCode={this.state.contractTypeCode}
          contractPeriodTypeCode={this.state.contractPeriodTypeCode}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.handleSearchTable.bind(this)}
        />
        <EditForm
          modalVisibleEdit={this.state.modalVisibleEdit}
          currentEditUserId={this.state.currentEditUserId}
          staffAllList={this.state.staffAllList}
          allBaseList={this.state.allBaseList}
          contractTypeCode={this.state.contractTypeCode}
          contractPeriodTypeCode={this.state.contractPeriodTypeCode}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.handleSearchTable.bind(this)}
        />
        <RelieveForm
          modalVisibleRelieve={this.state.modalVisibleRelieve}
          currentRelieveUserId={this.state.currentRelieveUserId}
          staffAllList={this.state.staffAllList}
          allBaseList={this.state.allBaseList}
          contractTypeCode={this.state.contractTypeCode}
          contractPeriodTypeCode={this.state.contractPeriodTypeCode}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <EndForm
          modalVisibleTerminate={this.state.modalVisibleTerminate}
          currentTerminateUserId={this.state.currentTerminateUserId}
          staffAllList={this.state.staffAllList}
          allBaseList={this.state.allBaseList}
          contractTypeCode={this.state.contractTypeCode}
          contractPeriodTypeCode={this.state.contractPeriodTypeCode}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <RenewForm
          modalVisibleRenew={this.state.modalVisibleRenew}
          currentRenewUserId={this.state.currentRenewUserId}
          staffAllList={this.state.staffAllList}
          allBaseList={this.state.allBaseList}
          contractTypeCode={this.state.contractTypeCode}
          contractPeriodTypeCode={this.state.contractPeriodTypeCode}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
        <DiscontinueForm
          modalDiscontinue={this.state.modalDiscontinue}
          currentDiscontinueUserId={this.state.currentDiscontinueUserId}
          staffAllList={this.state.staffAllList}
          allBaseList={this.state.allBaseList}
          contractTypeCode={this.state.contractTypeCode}
          contractPeriodTypeCode={this.state.contractPeriodTypeCode}
          YesOrNoCode={this.state.YesOrNoCode}
          handleModalVisible={this.handleModalVisible.bind(this)}
          refreshTable={this.refreshTable.bind(this)}
        />
      </div>
    );
  }
}
