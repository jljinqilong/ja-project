import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Select, Input, message, Icon, Divider, Modal } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SimplePage from '../../../components/SimplePage/SimplePage';
import ModelView from '../../../components/Sale/Enquiry/ModelView';
import { hasAccessKey } from '../../../utils/authority';

const FormItem = Form.Item;

@connect(({ inquiriesAppraisal, loading }) => ({
  inquiriesAppraisal,
  loading: loading.models.inquiriesAppraisal,
}))
@Form.create()
export default class InquiriesAppraisal extends PureComponent {
  state = {
    hisModalVisible: false,
    inquriesId: 0,
  };

  componentDidMount() {
    const { dispatch } = this.props;

    dispatch({
      type: 'inquiriesAppraisal/fetchAllCustomer',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchProductList',
    });
    // 数据字典：销售阶段
    dispatch({
      type: 'inquiriesAppraisal/fetchSalesPhase',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchBatteryTypeList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchTradeModeList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchBorderColorList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchBorderSpecificationList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchBackboardColorList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchBackboardMaterialList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchJunctionBoxList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchEVAList',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchArea',
    });
    dispatch({
      type: 'inquiriesAppraisal/fetchProductTypeList',
    });
  }

  // ============ 详情 ===============
  handleView = rowId => {
    const { dispatch } = this.props;
    dispatch(
      routerRedux.push({
        pathname: `/sale/inquiriesAppraisal/detail/${rowId}`,
      })
    );
  };

  /* 刷新页面 */
  refresh = (firstPage = false) => {
    /* SimplePage 需要定义 ref */
    this.page.refreshTable(firstPage);
  };

  // ============ 新增 ===============

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
      type: 'inquiriesAppraisal/add',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = payload => {
    const { dispatch } = this.props;

    dispatch({
      type: 'inquiriesAppraisal/del',
      ...payload,
    });
  };

  // ============ 分页查询 ===============
  handleSearch = params => {
    const { dispatch } = this.props;
    dispatch({
      type: 'inquiriesAppraisal/fetch',
      payload: params,
    });
  };

  // 询单历史记录
  inquiriesHistory = (flag, rowId) => {
    this.setState({
      hisModalVisible: !!flag,
      inquriesId: rowId,
    });
  };

  // ====== 评审确认 ======
  appraisalAffirm = (rowId, status, refresh) => {
    const { dispatch } = this.props;
    const params = { rowId, status };

    Modal.confirm({
      title: '询单评审确认',
      content: '确认通过此评审吗？',
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
          type: 'inquiriesAppraisal/fetchAffrimAppraisal',
          params,
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // ====== 评审驳回 ======
  appraisalReject = (rowId, status, refresh) => {
    const { dispatch } = this.props;
    const params = { rowId, status };

    Modal.confirm({
      title: '询单评审驳回',
      content: '确认驳回此评审吗？',
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
          type: 'inquiriesAppraisal/fetchAffrimAppraisal',
          params,
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // ====== 转合同 ======
  inquiriesTurnContract = rowId => {
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
          this.refresh();
        };

        const fail = () => {
          message.error('操作失败');
        };

        dispatch({
          type: 'inquiriesAppraisal/turnContract',
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
      inquiriesAppraisal: { customerList, batteryTypeList, productTypeList },
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
    const scroll = { x: 2400 };

    const {
      form,
      loading,
      inquiriesAppraisal: { data },
    } = this.props;

    const { hisModalVisible, inquriesId } = this.state;

    const columns = [
      {
        title: '编号',
        dataIndex: 'inquiryNo',
        fixed: 'left',
        width: 150,
      },
      {
        title: '客户名称',
        dataIndex: 'customerName',
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
        title: '贸易方式',
        dataIndex: 'transNames.tradeModeId_name',
        width: 200,
      },
      {
        title: '销售区域',
        dataIndex: 'specialRequire',
        width: 200,
      },
      {
        title: '业务员',
        dataIndex: 'salesman',
        width: 200,
      },
      {
        title: '创建人',
        dataIndex: 'createName',
        width: 200,
      },
      {
        title: '创建时间',
        dataIndex: 'createdTime',
        width: 200,
      },
      {
        title: '更新人',
        dataIndex: 'updateName',
        width: 200,
      },
      {
        title: '更新时间',
        dataIndex: 'lastUpdateTime',
        width: 200,
      },
    ];

    const opAction = record => (
      <Fragment>
        {hasAccessKey('sale.inquiriesAppraisal.confirmInquiriesAppraisal') &&
          (record.status === 5 ? (
            <Fragment>
              <Divider type="vertical" />
              <a title="通过" onClick={() => this.appraisalAffirm(record.rowId, 6, this.refresh)}>
                <Icon type="check-square-o" style={{ color: '#05F51A' }} />
              </a>
              <Divider type="vertical" />
              <a title="驳回" onClick={() => this.appraisalReject(record.rowId, 7, this.refresh)}>
                <Icon type="close-square" style={{ color: '#f5222d' }} />
              </a>
            </Fragment>
          ) : null)}
      </Fragment>
    );

    /*    const toolbarAction = () => {
      <Button>
        <Icon type="verticle-right" /> 测试
      </Button>
    } */

    const accessKeys = {
      viewKey: 'sale.inquiriesAppraisal.get',
    };

    return (
      <PageHeaderLayout title="询单评审管理">
        <SimplePage
          ref={page => {
            this.page = page;
          }}
          columns={columns}
          data={data}
          form={form}
          onSearch={this.handleSearch}
          renderSearchForm={this.renderSearchForm}
          scroll={scroll}
          opWidth={120}
          opFixed={{ fixed: 'right' }}
          opAction={opAction}
          onView={this.handleView}
          onEdit={false}
          onDelete={false}
          accessKeys={accessKeys}
          loading={loading}
        />
        <ModelView
          inquriesId={inquriesId}
          modalVisible={hisModalVisible}
          handleCancle={this.inquiriesHistory}
        />
        {/* <ViewInquiriesHistory inquiriesId={inquiriesId} /> */}
      </PageHeaderLayout>
    );
  }
}
