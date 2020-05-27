import React, { Fragment, PureComponent } from 'react';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Input, Select, DatePicker, Badge, message } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SimplePage from '../../../components/SimplePage/SimplePage';
import AddSaleContractForm from '../../../components/Sale/SaleContract/AddSaleContractForm';
import EditSaleContractForm from '../../../components/Sale/SaleContract/EditSaleContractForm';
import { getToken, hasAccessKey } from '../../../utils/authority';
import { serverUrlPre } from '../../../utils/request';

const FormItem = Form.Item;

const saleContractOptions = [
  { rowId: null, name: '不限' },
  { rowId: '0', name: '未完成' },
  { rowId: '1', name: '完成' },
  { rowId: '2', name: '逾期未完成' },
];
const saleContractStatusMap = ['error', 'success', 'error'];
const saleContractStatusKeys = [0, 1, 2];
const saleContractStatusValues = ['未完成', '完成', '逾期未完成'];

@connect(({ saleContract, loading }) => ({
  saleContract,
  loading: loading.models.saleContract,
}))
@Form.create()
export default class SaleContract extends PureComponent {
  state = {
    addModalVisible: false,
    editModalVisible: false,
    currentEditId: 0,
    signDateRange: [],
  };

  componentDidMount() {
    const { dispatch } = this.props;

    // TODO 合并加载
    // 加载合同类型、付款类型、客户、产品单位、产品、币别
    dispatch({
      type: 'saleContract/fetchPaymentType',
    });

    dispatch({
      type: 'saleContract/fetchSaleContractType',
    });

    dispatch({
      type: 'saleContract/fetchAllCustomer',
    });

    dispatch({
      type: 'saleContract/fetchProductUnit',
    });

    dispatch({
      type: 'saleContract/fetchProduct',
    });

    dispatch({
      type: 'saleContract/fetchCurrency',
    });

    dispatch({
      type: 'saleContract/fetchDispatchPlace',
    });

    dispatch({
      type: 'saleContract/fetchArea',
    });
  }

  // ============ 刷新 ===============
  refresh = (firstPage = false) => {
    this.page.refreshTable(firstPage);
  };

  // ============ 查询 ===============
  handleSearch = params => {
    const { signDateRange } = this.state;

    const { pageNum, pageSize } = params;

    const queryParams = {
      contractNo: params.contractNo,
      contractTitle: params.contractTitle,
      contractType: params.contractType,
      status: params.status ? parseInt(params.status, 10) : undefined,
      customer: params.customer,
      contractDateQ: signDateRange ? signDateRange[0] : null,
      contractDateZ: signDateRange ? signDateRange[1] : null,
      pageNum,
      pageSize,
    };

    const { dispatch } = this.props;
    dispatch({
      type: 'saleContract/fetch',
      payload: queryParams,
    });
  };

  // ============ 重置 ===============
  handleReset = () => {
    this.setState({
      signDateRange: null,
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
      type: 'saleContract/add',
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
      type: 'saleContract/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'saleContract/del',
      ...payload,
    });
  };

  // ============ 明细 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      routerRedux.push({
        pathname: `/sale/saleContract/detail/${rowId}`,
      })
    );
  };

  // ============ 查询：修改签约时间 ===============
  changeSignDateRange = (dates, dateStrings) => {
    this.setState({
      signDateRange: dateStrings,
    });
  };

  // ============ 查询表单 ===============
  renderSearchForm = () => {
    const {
      form,
      saleContract: { customerList, contractTypeList },
    } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="合同编号">
              {getFieldDecorator('contractNo')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="合同标题">
              {getFieldDecorator('contractTitle')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="合同类型">
              {getFieldDecorator('contractType')(
                <Select placeholder="请选择">
                  {contractTypeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="对应客户">
              {getFieldDecorator('customer')(
                <Select placeholder="请选择">
                  {customerList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="签约时间">
              {getFieldDecorator('signDate')(
                <DatePicker.RangePicker
                  format="YYYY-MM-DD"
                  onChange={this.changeSignDateRange}
                  style={{ width: '100%' }}
                />
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="合同状态">
              {getFieldDecorator('status')(
                <Select placeholder="请选择">
                  {saleContractOptions.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
      </Fragment>
    );
  };

  render() {
    const {
      loading,
      saleContract: {
        data,
        paymentTypeList,
        contractTypeList,
        customerList,
        productUnitList,
        productList,
        currencyList,
        dispatchPlaceList,
        areaList,
      },
      form,
    } = this.props;
    const { addModalVisible, editModalVisible, currentEditId } = this.state;

    const scroll = { x: 3000 };

    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
      paymentTypeList,
      contractTypeList,
      customerList,
      productUnitList,
      productList,
      currencyList,
      dispatchPlaceList,
      areaList,
    };

    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleModalVisible: this.handleEditModalVisible,
      paymentTypeList,
      contractTypeList,
      customerList,
      productUnitList,
      productList,
      currencyList,
      dispatchPlaceList,
      areaList,
    };

    const columns = [
      {
        title: '合同编号',
        dataIndex: 'contractNo',
        width: 250,
        fixed: 'left',
      },
      {
        title: '合同标题',
        dataIndex: 'contractTitle',
        width: 250,
      },
      {
        title: '合同类型',
        dataIndex: 'transNames.contractType_name',
        width: 200,
      },
      {
        title: '合同状态',
        dataIndex: 'status',
        width: 200,
        render(val) {
          return (
            <Badge
              status={saleContractStatusMap[val]}
              text={saleContractStatusValues[saleContractStatusKeys.indexOf(val)]}
            />
          );
        },
      },
      {
        title: '对应客户',
        dataIndex: 'customerName',
        width: 150,
      },
      {
        title: '客户方签约人',
        dataIndex: 'clientContractor',
        width: 150,
      },
      {
        title: '我方签约人',
        dataIndex: 'ourSignatory',
        width: 150,
      },
      {
        title: '签约日期',
        dataIndex: 'signDate',
        width: 150,
      },
      {
        title: '付款方式',
        dataIndex: 'transNames.payMethod_name',
        width: 150,
      },
      {
        title: '总金额',
        dataIndex: 'totalAmount',
        width: 150,
        render: text => {
          return text.toFixed(2);
        },
      },
      {
        title: '已回款金额',
        dataIndex: 'paymentAmount',
        width: 150,
        render: text => {
          return text.toFixed(2);
        },
      },
      {
        title: '未回款金额',
        dataIndex: 'unPaymentAmount',
        width: 150,
        render: text => {
          return text.toFixed(2);
        },
      },
      {
        title: '已开票金额',
        dataIndex: 'ticketAmount',
        width: 150,
        render: text => {
          return text.toFixed(2);
        },
      },
      {
        title: '未开票金额',
        dataIndex: 'unTicketAmount',
        width: 150,
        render: text => {
          return text.toFixed(2);
        },
      },
      {
        title: '最新跟进记录',
        dataIndex: 'lastUpdateTime',
        width: 150,
      },
      {
        title: '下次跟进时间',
        dataIndex: 'nextTime',
        width: 150,
      },
    ];

    const accessKeys = {
      addKey: 'sale.saleContract.add',
      editKey: 'sale.saleContract.update',
      deleteKey: 'sale.saleContract.del',
      viewKey: 'sale.saleContract.get',
      impKey: 'sale.saleContract.importExcel',
    };

    const token = getToken();
    const impTplUrl = `${serverUrlPre}/sale/saleContract/download?filePath=contractModel.zip&token=${token}`;
    const impUrl = `${serverUrlPre}/sale/saleContract/importExcel?token=${token}`;

    return (
      hasAccessKey('sale.saleContract.list') && (
        <PageHeaderLayout title="合同管理">
          <SimplePage
            ref={page => {
              this.page = page;
            }}
            form={form}
            renderSearchForm={this.renderSearchForm}
            onSearch={this.handleSearch}
            onReset={this.handleReset}
            add
            onAdd={() => this.handleAddModalVisible(true)}
            onEdit={rowId => this.handleEditModalVisible(true, rowId)}
            onDelete={payload => this.handleDelete(payload)}
            onView={rowId => this.handleView(rowId)}
            loading={loading}
            columns={columns}
            data={data}
            scroll={scroll}
            opFixed={{ fixed: 'right' }}
            accessKeys={accessKeys}
            impTplUrl={impTplUrl}
            impUrl={impUrl}
            imp
          />
          <AddSaleContractForm {...parentAddMethods} modalVisible={addModalVisible} />
          <EditSaleContractForm
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
