import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Input, Badge, Select, message } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SimplePage from '../../../components/SimplePage/SimplePage';
import AddCustomerForm from '../../../components/Sale/Customer/AddCustomerForm';
import EditCustomerForm from '../../../components/Sale/Customer/EditCustomerForm';
import { hasAccessKey, getToken } from '../../../utils/authority';
import { serverUrlPre } from '../../../utils/request';

const FormItem = Form.Item;

const customerStatusMap = ['success', 'success', 'error', 'success'];
const customerStatusKeys = [1, 2, 3, 4];
const customerStatusValues = ['新增(未分配销售员)', '签核中', '签核不通过', '已分配销售员'];

@connect(({ customer, loading }) => ({
  customer,
  loading: loading.models.customer,
}))
@Form.create()
export default class Customer extends PureComponent {
  state = {
    addModalVisible: false,
    editModalVisible: false,
    currentEditId: 0,
  };

  componentDidMount() {
    const { dispatch } = this.props;

    // 账期单位，客户等级，付款方式，币别，区域
    dispatch({
      type: 'customer/fetchAccountUnit',
    });
    dispatch({
      type: 'customer/fetchCustomerLevel',
    });
    dispatch({
      type: 'customer/fetchPaymentType',
    });
    dispatch({
      type: 'customer/fetchCurrency',
    });
    dispatch({
      type: 'customer/fetchArea',
    });
  }

  // ============ 刷新 ===============
  refresh = (firstPage = false) => {
    this.page.refreshTable(firstPage);
  };

  // ============ 查询 ===============
  handleSearch = params => {
    const { dispatch } = this.props;
    dispatch({
      type: 'customer/fetch',
      payload: params,
    });
  };

  // ============ 新增 ===============
  handleAddModalVisible = flag => {
    this.setState({
      addModalVisible: !!flag,
    });
  };

  handleAdd = payload => {
    const { dispatch } = this.props;

    const success = () => {
      message.success('添加成功');
      this.handleAddModalVisible(false);
      this.refresh();
    };

    const fail = () => {
      message.error('添加失败');
    };

    dispatch({
      type: 'customer/add',
      payload,
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
      type: 'customer/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'customer/del',
      ...payload,
    });
  };

  // ============ 明细 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      routerRedux.push({
        pathname: `/sale/customer/detail/${rowId}`,
      })
    );
  };

  // ============ 查询表单 ===============
  renderSearchForm = () => {
    const {
      form,
      customer: { areaList },
    } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="客户名称">
            {getFieldDecorator('customerName')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="区域">
            {getFieldDecorator('areaId')(
              <Select placeholder="请选择">
                {areaList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.name}
                  </Select.Option>
                ))}
              </Select>
            )}
          </FormItem>
        </Col>
      </Row>
    );
  };

  render() {
    const {
      loading,
      customer: {
        data,
        accountUnitList,
        customerLevelList,
        paymentTypeList,
        currencyList,
        areaList,
      },
      form,
    } = this.props;
    const { addModalVisible, editModalVisible, currentEditId } = this.state;

    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
      accountUnitList,
      customerLevelList,
      paymentTypeList,
      currencyList,
      areaList,
    };

    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleModalVisible: this.handleEditModalVisible,
      accountUnitList,
      customerLevelList,
      paymentTypeList,
      currencyList,
      areaList,
    };

    const columns = [
      {
        title: '客户名称',
        dataIndex: 'customerName',
      },
      {
        title: '区域',
        dataIndex: 'regionName',
      },
      {
        title: '地址',
        dataIndex: 'address',
      },
      {
        title: '欠款额度',
        dataIndex: 'debtLimit',
      },
      {
        title: '账期',
        dataIndex: 'paymentDays',
      },
      {
        title: '账期单位',
        dataIndex: 'transNames.accountUnitId_name',
      },
      {
        title: '状态',
        dataIndex: 'customerStatus',
        render(val) {
          return (
            <Badge
              status={customerStatusMap[val]}
              text={customerStatusValues[customerStatusKeys.indexOf(val)]}
            />
          );
        },
      },
    ];

    const token = getToken();
    const impTplUrl = `${serverUrlPre}/sale/customer/download?filePath=customerModel.zip&token=${token}`;
    const impUrl = `${serverUrlPre}/sale/customer/importExcel?token=${token}`;
    const accessKeys = {
      addKey: 'sale.customer.add',
      editKey: 'sale.customer.update',
      deleteKey: 'sale.customer.delete',
      viewKey: 'sale.customer.get',
      impKey: 'sale.customer.importExcel',
    };

    return (
      hasAccessKey('sale.customer.list') && (
        <PageHeaderLayout title="客户管理">
          <SimplePage
            ref={page => {
              this.page = page;
            }}
            form={form}
            renderSearchForm={this.renderSearchForm}
            onSearch={this.handleSearch}
            add
            onAdd={() => this.handleAddModalVisible(true)}
            onEdit={rowId => this.handleEditModalVisible(true, rowId)}
            onDelete={payload => this.handleDelete(payload)}
            onView={rowId => this.handleView(rowId)}
            loading={loading}
            columns={columns}
            data={data}
            impUrl={impUrl}
            impTplUrl={impTplUrl}
            imp
            accessKeys={accessKeys}
          />
          <AddCustomerForm {...parentAddMethods} modalVisible={addModalVisible} />
          <EditCustomerForm
            {...parentEditMethods}
            modalVisible={editModalVisible}
            currentEditId={currentEditId}
            key={currentEditId}
          />
        </PageHeaderLayout>
      )
    );
  }
}
