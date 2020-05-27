import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Select, DatePicker, Input, message } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SimplePage from '../../../components/SimplePage/SimplePage';
import AddEnquiryForm from '../../../components/Sale/Enquiry/AddEnquiryForm';
import EditEnquiryForm from '../../../components/Sale/Enquiry/EditEnquiryForm';

const FormItem = Form.Item;
const { RangePicker } = DatePicker;

@connect(({ enquiry, loading }) => ({
  enquiry,
  loading: loading.models.enquiry,
}))
@Form.create()
export default class Enquiry extends PureComponent {
  state = {
    addModalVisible: false,
    editModalVisible: false,
    currentEditId: 0,
  };

  componentDidMount() {
    const { dispatch } = this.props;

    dispatch({
      type: 'enquiry/fetchAllCustomer',
    });
    // dispatch({
    //   type: 'enquiry/fetchProductList',
    // });
    // 数据字典：销售阶段
    dispatch({
      type: 'enquiry/fetchSalesPhase',
    });
    dispatch({
      type: 'enquiry/fetchBatteryTypeList',
    });
    dispatch({
      type: 'enquiry/fetchTradeModeList',
    });
    dispatch({
      type: 'enquiry/fetchBorderColorList',
    });
    dispatch({
      type: 'enquiry/fetchBorderSpecificationList',
    });
    dispatch({
      type: 'enquiry/fetchBackboardColorList',
    });
    dispatch({
      type: 'enquiry/fetchBackboardMaterialList',
    });
    dispatch({
      type: 'enquiry/fetchJunctionBoxList',
    });
    dispatch({
      type: 'enquiry/fetchEVAList',
    });
    dispatch({
      type: 'enquiry/fetchArea',
    });
  }

  // ============ 详情 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      routerRedux.push({
        pathname: `/sale/enquiry/detail/${rowId}`,
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
      type: 'enquiry/add',
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
      type: 'enquiry/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'enquiry/del',
      ...payload,
    });
  };

  // ============ 查询 =============== models/ 下的 namespace: 'enquiry'
  handleSearch = params => {
    const { dispatch } = this.props;
    dispatch({
      type: 'enquiry/fetch',
      payload: params,
    });
  };

  // ============ 查询表单 ===============
  renderSearchForm = () => {
    const {
      form,
      enquiry: { salesPhaseList },
    } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="预计签约日期">
              {getFieldDecorator('estimatedSigningDate')(<RangePicker style={{ width: '100%' }} />)}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="销售阶段">
              {getFieldDecorator('salesPhaseId')(
                <Select placeholder="请选择">
                  {salesPhaseList.map(d => (
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
            <FormItem label="负责人">
              {getFieldDecorator('ourSignatory')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
        </Row>
      </Fragment>
    );
  };

  render() {
    const {
      form,
      enquiry: {
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
    };

    // 编辑页面需要的信息
    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleEditModalVisible: this.handleEditModalVisible,
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
    };
    const { addModalVisible, editModalVisible, currentEditId } = this.state;

    const columns = [
      {
        title: '询价标题',
        dataIndex: 'inquiryTitle',
      },
      {
        title: '对应客户',
        dataIndex: 'customerName',
      },
      {
        title: '预计销售金额',
        dataIndex: 'estimatedSalesAmount',
      },
      {
        title: '预计签单日期',
        dataIndex: 'estimatedSigningDate',
      },
      {
        title: '销售阶段',
        dataIndex: 'transNames.salesPhaseId_name',
      },
      {
        title: '关联产品',
        dataIndex: 'productNo',
      },
      {
        title: '最新跟进记录',
        dataIndex: 'lastUpdateTime',
      },
      {
        title: '实际跟进时间',
        dataIndex: 'actualFollowTime',
      },
    ];

    return (
      <PageHeaderLayout title="询价管理">
        <SimplePage
          ref={page => {
            this.page = page;
          }}
          columns={columns}
          data={data}
          form={form}
          onSearch={this.handleSearch}
          renderSearchForm={this.renderSearchForm}
          onView={this.handleView}
          add
          onAdd={() => this.handleAddModalVisible(true)}
          onDelete={payload => this.handleDelete(payload)}
          onEdit={rowId => this.handleEditModalVisible(true, rowId)}
        />
        <AddEnquiryForm {...parentAddMethods} modalVisible={addModalVisible} />
        <EditEnquiryForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
        />
      </PageHeaderLayout>
    );
  }
}
