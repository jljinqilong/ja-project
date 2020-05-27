import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva/index';
import { Form, message } from 'antd';
import SimplePage from '../../SimplePage/SimplePage';
import AddSaleContractInvoiceForm from './AddSaleContractInvoiceForm';
import EditSaleContractInvoiceForm from './EditSaleContractInvoiceForm';

@Form.create()
@connect(({ saleContract }) => ({ saleContract }))
export default class SaleContractInvoiceTab extends PureComponent {
  state = {
    currentEditId: 0,
    addModalVisible: false,
    editModalVisible: false,
  };

  componentDidMount() {
    const { dispatch } = this.props;

    dispatch({
      type: 'saleContract/fetchInvoiceType',
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
      type: 'saleContract/fetchInvoice',
      payload: saleContractId,
    });
  };

  // ============ 新增 ===============
  handleAddModalVisible = flag => {
    this.setState({
      addModalVisible: !!flag,
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
      type: 'saleContract/addInvoice',
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
      type: 'saleContract/editInvoice',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'saleContract/delInvoice',
      ...payload,
    });
  };

  render() {
    const {
      form,
      saleContract: { invoiceList },
    } = this.props;
    const { currentEditId, addModalVisible, editModalVisible } = this.state;

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
        title: '开票日期',
        dataIndex: 'kprq',
      },
      {
        title: '票据内容',
        dataIndex: 'invoiceContext',
      },
      {
        title: '开票金额',
        dataIndex: 'invoiceAmount',
      },
      {
        title: '票据类型',
        dataIndex: 'transNames.invoiceTypeId_name',
      },
      {
        title: '发票号码',
        dataIndex: 'invoiceNo',
      },
      {
        title: '经手人',
        dataIndex: 'invoiceUserName',
      },
      {
        title: '备注',
        dataIndex: 'remark',
      },
    ];

    const accessKeys = {
      addKey: 'sale.saleContract.addInvoice',
      editKey: 'sale.saleContract.updateInvoice',
      deleteKey: 'sale.saleContract.delInvoice',
    };

    return (
      <Fragment>
        <SimplePage
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
          data={{ list: invoiceList, pagination: false }}
          accessKeys={accessKeys}
        />
        <AddSaleContractInvoiceForm {...parentAddMethods} modalVisible={addModalVisible} />
        <EditSaleContractInvoiceForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
        />
      </Fragment>
    );
  }
}
