import React, { Fragment, PureComponent } from 'react';
import { connect } from 'dva/index';
import { routerRedux } from 'dva/router';
import { Form, Row, Col, Input, message, Select, Icon, Divider, Modal, Badge } from 'antd';
import SimplePage from '../../../components/SimplePage/SimplePage';
import AddProductForm from '../../../components/Sale/Product/AddProductForm';
import EditProductForm from '../../../components/Sale/Product/EditProductForm';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

const FormItem = Form.Item;

const productStatusMap = ['success', 'error'];
const productStatusKeys = [0, 1];
const productStatusValues = ['启用', '禁用'];

@connect(({ product, loading }) => ({
  product,
  loading: loading.models.product,
}))
@Form.create()
export default class Component extends PureComponent {
  state = {
    addModalVisible: false,
    editModalVisible: false,
    currentEditId: 0,
  };

  componentDidMount() {
    const { dispatch } = this.props;

    // 硅片类型
    dispatch({
      type: 'product/fetchSiliconType',
    });

    // 电池片数
    dispatch({
      type: 'product/fetchCellNumber',
    });

    // 组件类型
    dispatch({
      type: 'product/fetchMuduleType',
    });

    // 组件关键信息码
    dispatch({
      type: 'product/fetchMuduleCode',
    });

    // 电池片技术
    dispatch({
      type: 'product/fetchCellTechnology',
    });
  }

  // ============ 查询 ===============
  handleSearch = params => {
    const { dispatch } = this.props;
    dispatch({
      type: 'product/fetch',
      payload: params,
    });
  };

  // ============ 增加 ===============
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
      type: 'product/add',
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
  // handleDelete = payload => {
  //   const { dispatch } = this.props;
  //   // =====发起动作=========
  //   dispatch({
  //     type: 'product/del',
  //     ...payload,
  //   });
  // };

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
      type: 'product/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 明细 ===============
  handleView = rowId => {
    const { dispatch } = this.props;

    dispatch(
      // =========切换路由========
      routerRedux.push({
        pathname: `/sale/product/detail/${rowId}`,
      })
    );
  };

  // ============启用 禁用=================
  handleStartOrClose = (rowId, status) => {
    const { dispatch } = this.props;

    let str;
    const refresh = () => this.refresh();
    if (status === 0) {
      str = '启用';
    } else {
      str = '禁用';
    }

    Modal.confirm({
      title: `${str}页面`,
      content: `确定${str}此产品？`,
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const success = () => {
          message.success('成功');
          refresh();
        };

        const fail = () => {
          message.error('失败');
        };

        dispatch({
          type: 'product/update',
          payload: { rowId, status },
          successCallback: success,
          failCallback: fail,
        });
      },
      onCancel() {},
    });
  };

  // 查询form
  renderSearchForm = () => {
    const {
      form,
      product: {
        siliconTypeList,
        cellNumberList,
        muduleTypeList,
        muduleCodeList,
        cellTechnologyList,
      },
    } = this.props;
    const { getFieldDecorator } = form;

    const labelCol = { span: 6 };
    const wrapperCol = { span: 18 };

    return (
      <Fragment>
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <FormItem label="产品型号">
              {getFieldDecorator('productNo')(<Input placeholder="请输入" />)}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="硅片类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('siliconTypeId')(
                <Select placeholder="请选择">
                  {siliconTypeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="电池片数" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('cellNumberId')(
                <Select placeholder="请选择">
                  {cellNumberList.map(d => (
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
            <FormItem label="组件类型">
              {getFieldDecorator('muduleTypeId')(
                <Select placeholder="请选择">
                  {muduleTypeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="组件关键信息码" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('muduleCodeId')(
                <Select placeholder="请选择">
                  {muduleCodeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col md={8} sm={24}>
            <FormItem label="电池片技术" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('cellTechnologyId')(
                <Select placeholder="请选择">
                  {cellTechnologyList.map(d => (
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
      product: {
        data,
        siliconTypeList,
        cellNumberList,
        muduleTypeList,
        muduleCodeList,
        cellTechnologyList,
      },
      form,
    } = this.props;

    const { addModalVisible, editModalVisible, currentEditId } = this.state;

    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
      siliconTypeList,
      cellNumberList,
      muduleTypeList,
      muduleCodeList,
      cellTechnologyList,
    };

    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleModalVisible: this.handleEditModalVisible,
      siliconTypeList,
      cellNumberList,
      muduleTypeList,
      muduleCodeList,
      cellTechnologyList,
    };

    const columns = [
      {
        title: '产品型号',
        dataIndex: 'productNo',
        width: 150,
      },
      {
        title: '硅片类型',
        dataIndex: 'transNames.siliconTypeId_name',
        width: 150,
      },
      {
        title: '电池片数',
        dataIndex: 'transNames.cellNumberId_name',
        width: 80,
      },
      {
        title: '组件类型',
        dataIndex: 'transNames.muduleTypeId_name',
        width: 180,
      },
      {
        title: '组件关键信息码',
        dataIndex: 'transNames.muduleCodeId_name',
        width: 200,
      },
      {
        title: '组件功率',
        dataIndex: 'ratedPower',
        width: 80,
      },
      {
        title: '电池片技术',
        dataIndex: 'transNames.cellTechnologyId_name',
        width: 150,
      },
      {
        title: '状态',
        dataIndex: 'status',
        width: 80,
        render(val) {
          return (
            <Badge
              status={productStatusMap[val]}
              text={productStatusValues[productStatusKeys.indexOf(val)]}
            />
          );
        },
      },
    ];

    const token = getToken();
    const impTplUrl = `${serverUrlPre}/sale/product/download?filePath=productModel.zip&token=${token}`;
    const impUrl = `${serverUrlPre}/sale/product/importExcel?token=${token}`;

    // const opAction = record => (
    //   <Fragment>
    //     <Divider type="vertical" />
    //     <a title="启用">
    //       <Icon
    //         type="lock"
    //         style={{ color: 'green' }}
    //         onClick={() => this.handleStartOrClose(record.rowId, 0)}
    //       />
    //     </a>
    //     <Divider type="vertical" />
    //     <a title="禁用">
    //       <Icon
    //         type="unlock"
    //         style={{ color: 'red' }}
    //         onClick={() => this.handleStartOrClose(record.rowId, 1)}
    //       />
    //     </a>
    //   </Fragment>
    // );

    const opAction = record => {
      if(record.status === 0 ){
        return (
          <Fragment>
            <Divider type="vertical" />
            <a title="禁用">
              <Icon
                type="unlock"
                style={{ color: 'red' }}
                onClick={() => this.handleStartOrClose(record.rowId, 1)}
              />
            </a>
          </Fragment>
        );
      }else{
        return (
          <Fragment>
            <Divider type="vertical" />
            <a title="启用">
              <Icon
                type="lock"
                style={{ color: 'green' }}
                onClick={() => this.handleStartOrClose(record.rowId, 0)}
              />
            </a>
          </Fragment>
        );
      }
    };

    const accessKeys = {
      addKey: 'sale.product.add',
      editKey: 'sale.product.update',
      deleteKey: 'sale.product.delete',
      viewKey: 'sale.product.get',
      impKey: 'sale.product.importExcel',
    };

    return (
      <Fragment>
        <SimplePage
          ref={page => {
            this.page = page;
          }}
          columns={columns}
          data={data}
          form={form}
          onSearch={this.handleSearch}
          renderSearchForm={this.renderSearchForm}
          // onDelete={payload => this.handleDelete(payload)}
          // onEdit={rowId => this.handleEditModalVisible(true, rowId)}
          onDelete={false}
          onEdit={false}
          onView={rowId => this.handleView(rowId)}
          add
          onAdd={() => this.handleAddModalVisible(true)}
          impTplUrl={impTplUrl}
          impUrl={impUrl}
          imp
          opAction={opAction}
          accessKeys={accessKeys}
        />
        <AddProductForm {...parentAddMethods} modalVisible={addModalVisible} />
        <EditProductForm
          {...parentEditMethods}
          modalVisible={editModalVisible}
          currentEditId={currentEditId}
          key={currentEditId}
        />
      </Fragment>
    );
  }
}
