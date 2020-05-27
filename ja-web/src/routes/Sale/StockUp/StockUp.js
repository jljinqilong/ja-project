import React, { Fragment, PureComponent } from 'react';
import { connect } from 'dva/index';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Select, Input, message } from 'antd';
import SimplePage from '../../../components/SimplePage/SimplePage';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import AddStockUpForm from '../../../components/Sale/StockUp/AddStockUpForm';
import EditStockUpForm from '../../../components/Sale/StockUp/EditStockUpForm';

const FormItem = Form.Item;

@connect(({ stockUp, loading }) => ({
  stockUp,
  loading: loading.models.stockUp,
}))
@Form.create()
export default class StockUp extends PureComponent {

  state = {
    addModalVisible: false,

  };

  componentDidMount() {
    const { dispatch } = this.props;

    // 查询客户list
    dispatch({
      type: 'stockUp/fetchAllCustomer',
    });

    // 备货地点
    dispatch({
      type: 'stockUp/fetchStockAddress',
    });


    // 客户付款信息
    dispatch({
      type: 'stockUp/fetchCustomerPaymentInfo',
    });

    // A类片高效/低效
    dispatch({
      type: 'stockUp/fetchCategoryA',
    });

    // 交货方式
    dispatch({
      type: 'stockUp/fetchDeliveryMethod',
    });

    // EVA
  }

  // ============ 查询 ===============
  handleSearch = params => {
    const { pageNum, pageSize } = params;
    const queryParams = {
      stockNo: params.stockNo,
      customer: params.customer,
      stockAddress: params.stockAddress,
      pageNum,
      pageSize,
    };

    const { dispatch } = this.props;
    dispatch({
      type: 'stockUp/fetch',
      payload: queryParams,
    });
  };

  // ============ 明细 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      // =========切换路由========
      routerRedux.push({
        pathname: `/sale/stockUp/detail/${rowId}`,
      })
    );
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
      type: 'stockUp/add',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 刷新 ===============
  refresh = (firstPage = false) => {
    this.page.refreshTable(firstPage);
  };

  // ==========删除==================
  handleDelete = payload => {
    const { dispatch } = this.props;
    // =====发起动作=========
    dispatch({
      type: 'stockUp/del',
      ...payload,
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
      type: 'stockUp/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  renderSearchForm = () => {
    const {
      form,
      stockUp: { customerList, stockAddressList },
    } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="备货编号">
              {getFieldDecorator('stockNo')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>

          <Col md={8} sm={24}>
            <FormItem label="客户名称">
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
            <FormItem label="备货地点">
              {getFieldDecorator('stockAddress')(
                <Select placeholder="请选择">
                  {stockAddressList.map(d => (
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
      stockUp: { data, stockAddressList, customerList, customerPaymentList, categoryAList, deliveryMethodList},
      form,
    } = this.props;

    const { addModalVisible, editModalVisible, currentEditId } = this.state;

    const scroll = { x: 3600 };
    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
      stockAddressList,
      customerList,
      customerPaymentList,
      categoryAList,
      deliveryMethodList,
    };

    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleModalVisible: this.handleEditModalVisible,
      stockAddressList,
      customerList,
      customerPaymentList,
      categoryAList,
      deliveryMethodList,
    };


    const columns = [
      {
        title: '备货编号',
        dataIndex: 'stockNo',
        width: 200,
        fixed: 'left',
      },
      {
        title: '销售',
        dataIndex: 'salesman',
        width: 150,
      },
      {
        title: '备货地点',
        dataIndex: 'transNames.stockAddress_name',
        width: 150,
      },
      {
        title: '备货通知日期',
        dataIndex: 'stockDate',
        width: 150,
      },
      {
        title: '客户名称',
        dataIndex: 'customerName',
        width: 150,
      },
      {
        title: '合同号码',
        dataIndex: 'contractNo',
        width: 200,
      },
      {
        title: '客户付款信息',
        dataIndex: 'paymentInfo',
        width: 150,
      },
      {
        title: '合同签约地',
        dataIndex: 'contractAddress',
        width: 150,
      },
      {
        title: '发货片数',
        dataIndex: 'sendNum',
        width: 150,
      },
      {
        title: '组件型号（新）',
        dataIndex: 'newComponentmodel',
        width: 150,
      },
      {
        title: '组件型号（旧）',
        dataIndex: 'oldComponentmodel',
        width: 150,
      },
      {
        title: '组件类型',
        dataIndex: 'componentType',
        width: 150,
      },
      {
        title: '电池工艺',
        dataIndex: 'batteryProcess',
        width: 150,
      },
      {
        title: '电池片封装片数',
        dataIndex: 'packageNum',
        width: 150,
      },
      {
        title: '镀膜(AR)',
        dataIndex: 'coating',
        width: 150,
      },
      {
        title: '组件等级',
        dataIndex: 'componentLevel',
        width: 150,
      },
      {
        title: '单片功率（w）',
        dataIndex: 'singlePower',
        width: 150,
      },
      {
        title: 'A类片高效/低效',
        dataIndex: 'typeA',
        width: 150,
      },
      {
        title: '总发货瓦数',
        dataIndex: 'totalSendNum',
        width: 150,
      },
      {
        title: '单位',
        dataIndex: 'unit',
        width: 150,
      },
      {
        title: '交货方式',
        dataIndex: 'deliveryMethod',
        width: 150,
      },
      {
        title: '铝框类型',
        dataIndex: 'alProcess',
        width: 150,
      },
      {
        title: '接线盒',
        dataIndex: 'junctionBox',
        width: 150,
      },
    ];

    return (
      <PageHeaderLayout title="备货计划">
        <SimplePage
          ref={page => {
            this.page = page;
          }}
          columns={columns}
          data={data}
          form={form}
          onSearch={this.handleSearch}
          renderSearchForm={this.renderSearchForm}
          onView={rowId => this.handleView(rowId)}
          scroll={scroll}
          opFixed={{ fixed: 'right' }}
          onDelete={payload => this.handleDelete(payload)}
          add
          onAdd={() => this.handleAddModalVisible(true)}
          onEdit={rowId => this.handleEditModalVisible(true, rowId)}

        />
        <AddStockUpForm {...parentAddMethods} modalVisible={addModalVisible} />
        <EditStockUpForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
        />
      </PageHeaderLayout>
    );
  }
}
