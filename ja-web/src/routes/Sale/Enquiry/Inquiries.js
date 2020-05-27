import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Select, Input, message, Icon, Divider, Modal } from 'antd';
import { stringify } from 'qs';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SimplePage from '../../../components/SimplePage/SimplePage';
import AddInquiriesBaseForm from '../../../components/Sale/Enquiry/AddInquiriesBaseForm';
import EditInquiriesBaseForm from '../../../components/Sale/Enquiry/EditInquiriesBaseForm';
import ModelView from '../../../components/Sale/Enquiry/ModelView';
import SupplementInquiriesForm from '../../../components/Sale/Enquiry/SupplementInquiriesForm';
import { getToken, hasAccessKey } from '../../../utils/authority';
import { serverUrlPre } from '../../../utils/request';

const FormItem = Form.Item;
// const { RangePicker } = DatePicker;

@connect(({ inquiries, loading }) => ({
  inquiries,
  loading: loading.models.inquiries,
}))
@Form.create()
export default class Inquiries extends PureComponent {
  state = {
    addModalVisible: false,
    editModalVisible: false,
    hisModalVisible: false,
    supplementModalVisible: false,
    currentEditId: undefined,
    supplementEditId: undefined,
    inquriesId: undefined,
    searchFormValues: [],
  };

  componentDidMount() {
    const { dispatch } = this.props;

    dispatch({
      type: 'inquiries/fetchAllCustomer',
    });
    dispatch({
      type: 'inquiries/fetchProductList',
    });
    // 数据字典：销售阶段
    dispatch({
      type: 'inquiries/fetchSalesPhase',
    });
    dispatch({
      type: 'inquiries/fetchBatteryTypeList',
    });
    dispatch({
      type: 'inquiries/fetchTradeModeList',
    });
    dispatch({
      type: 'inquiries/fetchBorderColorList',
    });
    dispatch({
      type: 'inquiries/fetchBorderSpecificationList',
    });
    dispatch({
      type: 'inquiries/fetchBackboardColorList',
    });
    dispatch({
      type: 'inquiries/fetchBackboardMaterialList',
    });
    dispatch({
      type: 'inquiries/fetchJunctionBoxList',
    });
    dispatch({
      type: 'inquiries/fetchEVAList',
    });
    dispatch({
      type: 'inquiries/fetchArea',
    });
    dispatch({
      type: 'inquiries/fetchProductUnitList',
    });
    dispatch({
      type: 'inquiries/fetchCellNumberList',
    });
    dispatch({
      type: 'inquiries/fetchCurrencyList',
    });
    dispatch({
      type: 'inquiries/fetchInquiriesStatusList',
    });
    dispatch({
      type: 'inquiries/fetchDispatchPlaceList',
    });
    dispatch({
      type: 'inquiries/fetchProjectDistributionList',
    });
    dispatch({
      type: 'inquiries/fetchProductTypeList',
    });
  }

  // ============ 详情 ===============
  handleView = rowId => {
    const { dispatch } = this.props;
    dispatch(
      routerRedux.push({
        pathname: `/sale/inquiries/detail/${rowId}`,
      })
    );
  };

  /* 刷新页面 */
  refresh = (firstPage = false) => {
    /* SimplePage 需要定义 ref */
    this.page.refreshTable(firstPage);
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
      type: 'inquiries/add',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 编辑 ===============
  handleEditModalVisible = (flag, rowId) => {
    this.setState({
      editModalVisible: !!flag,
      currentEditId: rowId,
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
      type: 'inquiries/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // 更新询单补齐的信息
  handleUpdateSupplement = payload => {
    const { dispatch } = this.props;

    const success = () => {
      message.success('修改成功');
      this.handleSupplementInquiries(false);
      this.refresh();
    };

    const fail = () => {
      message.error('修改失败');
    };

    dispatch({
      type: 'inquiries/supplement',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'inquiries/del',
      ...payload,
    });
  };

  // ============ 分页查询 ===============
  handleSearch = params => {
    const { dispatch } = this.props;
    dispatch({
      type: 'inquiries/fetch',
      payload: params,
    });

    // 处理搜索框的值，用于导出过滤
    const { batteryTypeId, customerId, inquiryNo, productTypeId } = params;
    this.setState({
      searchFormValues: {
        batteryTypeId,
        customerId,
        inquiryNo,
        productTypeId,
      },
    });
  };

  // 询单历史记录
  inquiriesHistory = (flag, rowId) => {
    this.setState({
      hisModalVisible: !!flag,
      inquriesId: rowId,
    });
  };

  // 询单补齐信息
  handleSupplementInquiries = (flag, rowId) => {
    this.setState({
      supplementModalVisible: !!flag,
      supplementEditId: rowId,
    });
  };

  // ====== 询单确认 ====== TODO 对于modal.confirm 提示框不能直接调用外层方法，通过传递方法实现
  inquiriesAffirm = (rowId, refresh) => {
    const { dispatch } = this.props;

    Modal.confirm({
      title: '询单确认',
      content: '确认询单后此记录将不可编辑，确认操作吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const success = () => {
          message.success('操作成功');
          refresh();
        };

        const fail = () => {
          message.error('修改失败');
        };

        dispatch({
          type: 'inquiries/affrim',
          rowId,
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // ====== 询单转评审 ======
  turnAppraisal = (rowId, refresh) => {
    const { dispatch } = this.props;

    Modal.confirm({
      title: '转评审确认',
      content: '确认将此询单流转评审吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const success = () => {
          message.success('操作成功');
          refresh();
        };

        const fail = () => {
          message.error('修改失败');
        };

        dispatch({
          type: 'inquiries/turnAppraisal',
          rowId,
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // 转评审需补齐信息
  inquiriesTurnAppraisal = rowId => {
    const { dispatch } = this.props;

    Modal.confirm({
      title: '评审确认',
      content: '此询单需要流转评审，是否补齐信息？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const success = () => {
          message.success('操作成功');
          this.refresh();
        };

        const fail = () => {
          message.error('操作失败');
        };

        dispatch({
          type: 'inquiries/affrim',
          rowId,
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // ====== 转合同 ======
  inquiriesTurnContract = (rowId, refresh) => {
    const { dispatch } = this.props;

    Modal.confirm({
      title: '转合同确认',
      content: '确认转合同吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const success = () => {
          message.success('操作成功');
          refresh();
        };

        const fail = () => {
          message.error('操作失败');
        };

        dispatch({
          type: 'inquiries/turnContract',
          rowId,
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // ============ 表单搜索区域 ===============
  renderSearchForm = () => {
    const {
      form,
      inquiries: { customerList, batteryTypeList, productTypeList },
    } = this.props;
    const { getFieldDecorator } = form;
    const labelCol = { span: 5 };
    const wrapperCol = { span: 19 };

    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="编号" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('inquiryNo')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="客户名称" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('customerId')(
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
            <FormItem label="产品类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('productTypeId')(
                <Select placeholder="请选择">
                  {productTypeList.map(d => (
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
            <FormItem label="电池类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('batteryTypeId')(
                <Select placeholder="请选择">
                  {batteryTypeList.map(d => (
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
    const scroll = { x: 6000 };

    const {
      form,
      loading,
      inquiries: {
        data,
        salesPhaseList,
        batteryTypeList,
        customerList,
        areaList,
        tradeModeList,
        borderColorList,
        borderSpecificationList,
        backboardColorList,
        backboardMaterialList,
        junctionBoxList,
        evaList,
        productList,
        productUnitList,
        cellNumberList,
        currencyList,
        inquiriesStatusList,
        dispatchPlaceList,
        projectDistributionList,
        productTypeList,
      },
    } = this.props;

    /* 添加需要的数据字典信息 */
    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
      salesPhaseList,
      batteryTypeList,
      customerList,
      areaList,
      tradeModeList,
      borderColorList,
      borderSpecificationList,
      backboardColorList,
      backboardMaterialList,
      junctionBoxList,
      evaList,
      productList,
      productUnitList,
      cellNumberList,
      currencyList,
      inquiriesStatusList,
      dispatchPlaceList,
      projectDistributionList,
      productTypeList,
    };

    // 编辑页面需要的信息
    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleUpdateSupplement: this.handleUpdateSupplement,
      handleEditModalVisible: this.handleEditModalVisible,
      handleSupplementInquiries: this.handleSupplementInquiries,
      salesPhaseList,
      batteryTypeList,
      customerList,
      areaList,
      tradeModeList,
      borderColorList,
      borderSpecificationList,
      backboardColorList,
      backboardMaterialList,
      junctionBoxList,
      evaList,
      productList,
      productUnitList,
      cellNumberList,
      currencyList,
      inquiriesStatusList,
      dispatchPlaceList,
      projectDistributionList,
      productTypeList,
    };

    const {
      addModalVisible,
      editModalVisible,
      currentEditId,
      supplementEditId,
      hisModalVisible,
      supplementModalVisible,
      inquriesId,
      searchFormValues, // 搜索表单字段
    } = this.state;

    const columns = [
      {
        title: '编号',
        dataIndex: 'inquiryNo',
        fixed: 'left',
        width: 150,
      },
      {
        title: '状态',
        dataIndex: 'statusName',
        width: 80,
      },
      {
        title: '客户名称',
        dataIndex: 'customerName',
        width: 200,
      },
      {
        title: '项目地',
        dataIndex: 'countryProvCity',
        width: 200,
      },
      {
        title: '产品类型',
        dataIndex: 'transNames.productTypeId_name',
        width: 200,
      },
      {
        title: '功率档位',
        dataIndex: 'power',
        width: 200,
      },
      {
        title: '订单总量(MW)',
        dataIndex: 'orderTotal',
        width: 200,
      },
      // {
      //   title: '电池类型',
      //   dataIndex: 'transNames.batteryTypeId_name',
      //   width: 200,
      // },
      {
        title: '销售价格',
        dataIndex: 'totalAmount',
        width: 200,
      },
      {
        title: '主栅根数',
        dataIndex: 'mainGateNumber',
        width: 200,
      },
      {
        title: '贸易方式',
        dataIndex: 'transNames.tradeModeId_name',
        width: 200,
      },
      {
        title: '首年衰减',
        dataIndex: 'firstYearAttenuation',
        width: 200,
      },
      {
        title: '付款条件',
        dataIndex: 'paymentTerm',
        width: 200,
      },
      {
        title: '认证要求',
        dataIndex: 'certificationRequire',
        width: 200,
      },
      {
        title: '送功率',
        dataIndex: 'outputPower',
        width: 200,
      },
      {
        title: '标板要求',
        dataIndex: 'plateRequire',
        width: 200,
      },
      {
        title: '质保要求',
        dataIndex: 'warrantyRequire',
        width: 200,
      },
      {
        title: '特殊要求',
        dataIndex: 'specialRequire',
        width: 200,
      },
      {
        title: '销售区域',
        dataIndex: 'saleAreaName',
        width: 200,
      },
      {
        title: '业务员',
        dataIndex: 'salesman',
        width: 200,
      },
      {
        title: '违约条款',
        dataIndex: 'violateClause',
        width: 200,
      },
      {
        title: 'BOM要求',
        dataIndex: 'bomRequire',
        width: 200,
      },
      {
        title: '边框颜色',
        dataIndex: 'transNames.borderColorId_name',
        width: 200,
      },
      {
        title: '边框规格',
        dataIndex: 'transNames.borderSpecificationId_name',
        width: 200,
      },
      {
        title: '背板颜色',
        dataIndex: 'transNames.backboardColorId_name',
        width: 200,
      },
      {
        title: '背板材质',
        dataIndex: 'transNames.backboardMaterialId_name',
        width: 200,
      },
      {
        title: '接线盒',
        dataIndex: 'transNames.junctionBoxId_name',
        width: 200,
      },
      {
        title: '玻璃',
        dataIndex: 'glass',
        width: 200,
      },
      {
        title: 'EVA',
        dataIndex: 'transNames.evaId_name',
        width: 200,
      },
      {
        title: '成本',
        dataIndex: 'cost',
        width: 200,
      },
      {
        title: '毛利润',
        dataIndex: 'grossProfitRatio',
        width: 200,
      },
    ];

    const opAction = record => (
      <Fragment>
        {hasAccessKey('sale.inquiries.listInquiriesHistory') && (
          <Fragment>
            <Divider type="vertical" />
            <a title="历史记录" onClick={() => this.inquiriesHistory(true, record.rowId)}>
              <Icon type="database" />
            </a>
          </Fragment>
        )}
        {hasAccessKey('sale.inquiries.confirmInquiries') &&
          (record.status === 1 || record.status === 7 ? (
            <Fragment>
              <Divider type="vertical" />
              <a title="确认" onClick={() => this.inquiriesAffirm(record.rowId, this.refresh)}>
                <Icon type="check-square-o" />
              </a>
            </Fragment>
          ) : null)}
        {hasAccessKey('sale.inquiries.supplementInquiries') &&
          (record.status === 8 || record.status === 9 ? (
            <Fragment>
              <Divider type="vertical" />
              <a
                title="询单信息补齐"
                onClick={() => this.handleSupplementInquiries(true, record.rowId)}
              >
                <Icon type="book" />
              </a>
            </Fragment>
          ) : null)}
        {hasAccessKey('sale.inquiries.inquiriesTurnAppraisal') &&
          (record.status === 9 ? (
            <Fragment>
              <Divider type="vertical" />
              <a title="转评审" onClick={() => this.turnAppraisal(record.rowId, this.refresh)}>
                <Icon type="solution" />
              </a>
            </Fragment>
          ) : null)}
        {hasAccessKey('sale.inquiries.transferContract') &&
          (record.status === 2 || record.status === 6 ? (
            <Fragment>
              <Divider type="vertical" />
              <a
                title="转合同"
                onClick={() => this.inquiriesTurnContract(record.rowId, this.refresh)}
              >
                <Icon type="retweet" />
              </a>
            </Fragment>
          ) : null)}
      </Fragment>
    );

    const opAccessCheckFunc = () => {
      return hasAccessKey('sale.inquiries.listInquiriesHistory');
    };

    /*    const toolbarAction = () => {
      <Button>
        <Icon type="verticle-right" /> 测试
      </Button>
    } */
    const accessKeys = {
      addKey: 'sale.inquiries.add',
      editKey: 'sale.inquiries.update',
      deleteKey: 'sale.inquiries.delete',
      viewKey: 'sale.inquiries.get',
      expKey: 'sale.saleContract.importExcel',
    };

    const token = getToken();

    const expUrl = `${serverUrlPre}/sale/inquiries/exportExcel?token=${token}&${stringify(
      searchFormValues
    )}`;

    return (
      <PageHeaderLayout title="询单记录单管理">
        <SimplePage
          ref={page => {
            this.page = page;
          }}
          columns={columns}
          data={data}
          form={form}
          onSearch={this.handleSearch}
          renderSearchForm={this.renderSearchForm}
          add
          onAdd={() => this.handleAddModalVisible(true)}
          onDelete={payload => this.handleDelete(payload)}
          onEdit={rowId => this.handleEditModalVisible(true, rowId)}
          scroll={scroll}
          opWidth={180}
          opFixed={{ fixed: 'right' }}
          opAction={opAction}
          opAccessCheckFunc={opAccessCheckFunc}
          accessKeys={accessKeys}
          loading={loading}
          exp
          expUrl={expUrl}
        />
        <AddInquiriesBaseForm {...parentAddMethods} modalVisible={addModalVisible} />

        <EditInquiriesBaseForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
        />
        {/* 历史记录 */}
        <ModelView
          inquriesId={inquriesId}
          modalVisible={hisModalVisible}
          handleCancle={this.inquiriesHistory}
        />
        {/* 补齐信息 */}
        <SupplementInquiriesForm
          {...parentEditMethods}
          supplementEditId={supplementEditId}
          key={supplementEditId}
          modalVisible={supplementModalVisible}
          handleCancle={this.handleSupplementInquiries}
        />
      </PageHeaderLayout>
    );
  }
}
