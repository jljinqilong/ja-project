import React, { PureComponent, Fragment } from 'react';
import { Form, message } from 'antd';
import { connect } from 'dva/index';
import SimplePage from '../../SimplePage/SimplePage';
import AddSaleContractPaymentPlanForm from './AddSaleContractPaymentPlanForm';
import EditSaleContractPaymentPlanForm from './EditSaleContractPaymentPlanForm';
import SaleContractPaymentTab from './SaleContractPaymentTab';

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class SaleContractPaymentPlanTab extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      currentAddId: 1, // 用于销毁控件
      currentEditId: 0,
      addModalVisible: false,
      editModalVisible: false,
    };

    this.expandedRowKeys = [];
  }

  componentDidMount() {
    const { dispatch } = this.props;

    dispatch({
      type: 'saleContract/fetchPayType',
    });

    // TODO 考虑优化，避免重复加载（与 SaleContract 组件）
    dispatch({
      type: 'saleContract/fetchPaymentType',
    });
  }

  // ============ 刷新 ===============
  refresh = (firstPage = false) => {
    this.page.refreshTable(firstPage);
  };

  // ============ 查询 ===============
  handleSearch = () => {
    const { saleContractId } = this.props;
    const { dispatch } = this.props;
    dispatch({
      type: 'saleContract/fetchPaymentPlan',
      payload: saleContractId,
    });
  };

  // ============ 新增 ===============
  handleAddModalVisible = (flag, rowId) => {
    this.setState({
      addModalVisible: !!flag,
      currentAddId: rowId, // 会影响key，需要注意，是优化的一个手段
    });
  };

  handleAdd = payload => {
    const { dispatch, saleContractId } = this.props;

    const success = () => {
      message.success('添加成功');
      this.handleAddModalVisible(false);
      this.refresh();
    };

    const fail = () => {
      message.error('添加失败');
    };

    dispatch({
      type: 'saleContract/addPaymentPlan',
      payload: {
        ...payload,
        saleContractId,
      },
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 编辑 ===============
  handleEditModalVisible = (flag, rowId) => {
    this.setState({
      editModalVisible: !!flag,
      currentEditId: rowId, // 会影响key，需要注意，是优化的一个手段
    });
  };

  handleEdit = payload => {
    const { dispatch } = this.props;

    const success = () => {
      message.success('修改成功');
      this.handleEditModalVisible(false);
      this.refresh();
    };

    const fail = () => {
      message.error('修改失败');
    };

    dispatch({
      type: 'saleContract/editPaymentPlan',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'saleContract/delPaymentPlan',
      ...payload,
    });
  };

  // ============ 行展开 ===============
  handleExtend = () => {
    const expandedRowRender = record => {
      const title = `第${record.period}期的回款记录`;
      return (
        <Fragment>
          <SaleContractPaymentTab
            title={title}
            saleContractId={record.saleContractId}
            payPlanId={record.rowId}
            planUnPaymentAmount={record.plan_unPaymentAmount}
          />
        </Fragment>
      );
    };

    const updateExpandRowKeys = rows => {
      this.expandedRowKeys = rows;
    };

    return {
      expandedRowRender,
      updateExpandRowKeys,
      initExpandedRowKeys: this.expandedRowKeys,
    };
  };

  render() {
    const {
      form,
      saleContract: { paymentPlanList },
      saleContractId,
    } = this.props;
    const { addModalVisible, editModalVisible, currentEditId, currentAddId } = this.state;

    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
    };

    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleModalVisible: this.handleEditModalVisible,
    };

    const columns = [
      {
        title: '回款计划',
        dataIndex: 'period',
        render: text => `第 ${text} 期`,
      },
      {
        title: '日期',
        dataIndex: 'payDate',
      },
      {
        title: '计划回款总金额',
        dataIndex: 'payAmount',
        render: text => {
          return <span style={{ color: 'red' }}>{text}</span>;
        },
      },
      {
        title: '已回款总金额',
        dataIndex: 'plan_paymentAmount',
        render: text => {
          return <span style={{ color: 'red' }}>{text}</span>;
        },
      },
      {
        title: '未回款总金额',
        dataIndex: 'plan_unPaymentAmount',
        render: text => {
          return <span style={{ color: 'red' }}>{text}</span>;
        },
      },
    ];

    const accessKeys = {
      addKey: 'sale.paymentPlan.add',
      editKey: 'sale.paymentPlan.update',
      deleteKey: 'sale.paymentPlan.delete',
      viewKey: 'sale.paymentPlan.get',
      impKey: 'sale.paymentPlan.importExcel',
    };

    return (
      <Fragment>
        <SimplePage
          rowKey="rowId"
          ref={page => {
            this.page = page;
          }}
          form={form}
          onSearch={this.handleSearch}
          add
          onAdd={() => this.handleAddModalVisible(true, 1)}
          onEdit={rowId => this.handleEditModalVisible(true, rowId)}
          onDelete={payload => this.handleDelete(payload)}
          onExtend={() => this.handleExtend()}
          columns={columns}
          data={{ list: paymentPlanList, pagination: false }}
          accessKeys={accessKeys}
        />
        <AddSaleContractPaymentPlanForm
          {...parentAddMethods}
          modalVisible={addModalVisible}
          key={currentAddId}
          saleContractId={saleContractId}
        />
        <EditSaleContractPaymentPlanForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
          saleContractId={saleContractId}
        />
      </Fragment>
    );
  }
}
