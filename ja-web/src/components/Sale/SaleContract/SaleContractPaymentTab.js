import React, { PureComponent, Fragment } from 'react';
import { Form, message } from 'antd';
import { connect } from 'dva/index';
import SimplePage from '../../SimplePage/SimplePage';
import AddSaleContractPaymentForm from './AddSaleContractPaymentForm';
import EditSaleContractPaymentForm from './EditSaleContractPaymentForm';
import { listPaymentByPlanId } from '../../../services/saleContract';

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class SaleContractPaymentTab extends PureComponent {
  state = {
    data: [],
    currentEditId: 0,
    addModalVisible: false,
    editModalVisible: false,
  };

  // ============ 刷新 ===============
  refresh = (firstPage = false) => {
    this.page.refreshTable(firstPage);

    this.refreshParent();
  };

  refreshParent = () => {
    // 刷新回款计划表
    const { dispatch, saleContractId } = this.props;
    dispatch({
      type: 'saleContract/fetchPaymentPlan',
      payload: saleContractId,
    });
  };

  // ============ 查询 ===============
  handleSearch = () => {
    const { payPlanId } = this.props;
    this.get(payPlanId);
  };

  get = payPlanId => {
    listPaymentByPlanId(payPlanId)
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          data,
        });
      });
  };

  // ============ 新增 ===============
  handleAddModalVisible = flag => {
    this.setState({
      addModalVisible: !!flag,
    });
  };

  handleAdd = payload => {
    const { dispatch, saleContractId, payPlanId } = this.props;

    const success = () => {
      message.success('添加成功');
      this.handleAddModalVisible(false);
      this.refresh();
    };

    const fail = () => {
      message.error('添加失败');
    };

    dispatch({
      type: 'saleContract/addPayment',
      payload: {
        ...payload,
        saleContractId,
        payPlanId,
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
    const { dispatch, saleContractId, payPlanId } = this.props;

    const success = () => {
      message.success('修改成功');
      this.handleEditModalVisible(false);
      this.refresh();
    };

    const fail = () => {
      message.error('修改失败');
    };

    dispatch({
      type: 'saleContract/editPayment',
      payload: {
        ...payload,
        saleContractId,
        payPlanId,
      },
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'saleContract/delPayment',
      ...payload,
    });

    this.refreshParent();
  };

  render() {
    const { form, title, planUnPaymentAmount } = this.props;
    const { data, addModalVisible, editModalVisible, currentEditId } = this.state;

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
        title: '回款日期',
        dataIndex: 'createdTime',
      },
      {
        title: '回款金额',
        dataIndex: 'amount',
        render: text => {
          return <span style={{ color: 'red' }}>{text}</span>;
        },
      },
      {
        title: '付款方式',
        dataIndex: 'transNames.payMethod_name',
      },
      {
        title: '回款类型',
        dataIndex: 'transNames.typeId_name',
      },
      {
        title: '付款人',
        dataIndex: 'payeeName',
      },
    ];

    const accessKeys = {
      addKey: 'sale.paymentDetail.add',
      editKey: 'sale.paymentDetail.update',
      deleteKey: 'sale.paymentDetail.del',
      viewKey: 'sale.paymentDetail.get',
      impKey: 'sale.paymentDetail.importExcel',
    };

    return (
      <Fragment>
        <h2>{title}</h2>
        <SimplePage
          rowKey="rowId"
          ref={page => {
            this.page = page;
          }}
          form={form}
          onSearch={this.handleSearch}
          add
          onAdd={() => this.handleAddModalVisible(true)}
          onEdit={rowId => this.handleEditModalVisible(true, rowId)}
          onDelete={payload => this.handleDelete(payload)}
          columns={columns}
          data={{ list: data, pagination: false }}
          accessKeys={accessKeys}
        />
        <AddSaleContractPaymentForm
          {...parentAddMethods}
          planUnPaymentAmount={planUnPaymentAmount}
          modalVisible={addModalVisible}
        />
        <EditSaleContractPaymentForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
          planUnPaymentAmount={planUnPaymentAmount}
        />
      </Fragment>
    );
  }
}
