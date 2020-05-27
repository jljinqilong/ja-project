import React, { PureComponent, Fragment } from 'react';
import { connect } from 'dva';
import { Form, Row, Col, Input, message, Icon, Divider, Modal, Pagination, Select } from 'antd';
import PageHeaderLayout from '../../../layouts/PageHeaderLayout';
import SimplePage from '../../../components/SimplePage/SimplePage';
import AddAreaForm from '../../../components/Sale/Area/AddAreaForm';
import EditAreaForm from '../../../components/Sale/Area/EditAreaForm';
import { serverUrlPre } from '../../../utils/request';
import { getToken, hasAccessKey } from '../../../utils/authority';

const FormItem = Form.Item;

@connect(({ area, loading }) => ({
  area,
  loading: loading.models.area,
}))
@Form.create()
export default class Area extends PureComponent {
  state = {
    addModalVisible: false,
    editModalVisible: false,
    currentEditId: 0,
  };

  componentDidMount() {
    const { dispatch } = this.props;

    // 加载洲、国家、币别
    dispatch({
      type: 'area/fetchContinent',
    });
    dispatch({
      type: 'area/fetchCountry',
    });
    dispatch({
      type: 'area/fetchCurrency',
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
      type: 'area/fetch',
      payload: params,
    });
  };

  // ============ 页码变化 ===============
  handleTableChange = (page, pageSize) => {
    const { form } = this.page.props;

    form.validateFields(async (err, fieldsValue) => {
      if (err) return;

      const params = {
        pageNum: page,
        pageSize,
        ...fieldsValue,
      };

      this.handleSearch(params);
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
      type: 'area/add',
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
      type: 'area/edit',
      payload,
      successCallback: success,
      failCallback: fail,
    });
  };

  // ============ 删除 ===============
  handleDelete = rowId => {
    const success = () => {
      message.success('删除成功');
      this.refresh();
    };

    const fail = () => {
      message.error('删除失败');
    };

    const { dispatch } = this.props;

    Modal.confirm({
      title: '删除确认',
      content: '确定删除此记录吗？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        const params = {
          payload: { rowId },
          successCallback: success,
          failCallback: fail,
        };

        dispatch({
          type: 'area/del',
          ...params,
        });
      },
      onCancel() {},
    });
  };

  // ============ 查询表单 ===============
  renderSearchForm = () => {
    const {
      form,
      area: { countryList },
    } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
        <Col md={8} sm={24}>
          <FormItem label="区域">
            {getFieldDecorator('regionName')(<Input placeholder="请输入" />)}
          </FormItem>
        </Col>
        <Col md={8} sm={24}>
          <FormItem label="国家">
            {getFieldDecorator('countryId')(
              <Select placeholder="请选择">
                {countryList.map(d => (
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

  // ============ 计算操作列 ===============
  renderOpColumn = () => {
    if (hasAccessKey('sale.area.update') || hasAccessKey('sale.area.delete')) {
      return {
        title: '操作',
        width: 120,
        render: (text, record) => {
          if (record.countryNum) {
            return {
              children: (
                <Fragment>
                  {hasAccessKey('sale.area.update') ? (
                    <a
                      title="编辑"
                      onClick={() => this.handleEditModalVisible(true, record.regionId)}
                    >
                      <Icon type="edit" />
                    </a>
                  ) : null}
                  {hasAccessKey('sale.area.delete') ? (
                    hasAccessKey('sale.area.update') ? (
                      <Fragment>
                        <Divider type="vertical" />
                        <a title="删除" onClick={() => this.handleDelete(record.regionId)}>
                          <Icon type="delete" />
                        </a>
                      </Fragment>
                    ) : (
                      <a title="删除" onClick={() => this.handleDelete(record.regionId)}>
                        <Icon type="delete" />
                      </a>
                    )
                  ) : null}
                </Fragment>
              ),
              props: {
                rowSpan: record.countryNum,
              },
            };
          } else {
            return {
              props: {
                rowSpan: 0,
              },
            };
          }
        },
      };
    }
  };

  render() {
    const {
      loading,
      area: { data, continentList, countryList, currencyList },
      form,
    } = this.props;
    const { addModalVisible, editModalVisible, currentEditId } = this.state;

    const parentAddMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleAddModalVisible,
      continentList,
      countryList,
      currencyList,
    };

    const parentEditMethods = {
      handleEdit: this.handleEdit,
      handleModalVisible: this.handleEditModalVisible,
      continentList,
      countryList,
      currencyList,
    };

    const showQuickJumper = true;
    const showSizeChanger = true;

    const baseColumns = [
      {
        title: '区域',
        dataIndex: 'regionName',
        render: (text, record) => {
          if (record.countryNum) {
            return {
              children: text,
              props: {
                rowSpan: record.countryNum,
              },
            };
          } else {
            return {
              children: text,
              props: {
                rowSpan: 0,
              },
            };
          }
        },
      },
      {
        title: '洲',
        dataIndex: 'transNames.continentId_name',
      },
      {
        title: '国家',
        dataIndex: 'transNames.countryId_name',
      },
      {
        title: '币别',
        dataIndex: 'transNames.zCurrencyId_name',
      },
      {
        title: '单价',
        dataIndex: 'price',
      },
    ];

    const columns = [...baseColumns, this.renderOpColumn()]
      .filter(column => !!column)
      .map(column => column);

    const token = getToken();
    const impTplUrl = `${serverUrlPre}/sale/area/download?filePath=areaInfoImport.zip&token=${token}`;
    const impUrl = `${serverUrlPre}/sale/area/importExcel?token=${token}`;
    const accessKeys = {
      addKey: 'sale.area.add',
      impKey: 'sale.area.importExcel',
    };

    return (
      hasAccessKey('sale.area.list') && (
        <PageHeaderLayout title="区域管理">
          <SimplePage
            ref={page => {
              this.page = page;
            }}
            form={form}
            renderSearchForm={this.renderSearchForm}
            onSearch={this.handleSearch}
            add
            onAdd={() => this.handleAddModalVisible(true)}
            loading={loading}
            columns={columns}
            data={{ list: data.list, pagination: false }}
            tblProps={{ bordered: true }}
            op={false}
            impTplUrl={impTplUrl}
            impUrl={impUrl}
            imp
            accessKeys={accessKeys}
          >
            <Pagination
              current={data.pagination.pageNum}
              total={data.pagination.total}
              onChange={this.handleTableChange}
              onShowSizeChange={this.handleTableChange}
              showQuickJumper={showQuickJumper}
              showSizeChanger={showSizeChanger}
              style={{ float: 'right', marginTop: '20px' }}
            />
          </SimplePage>

          <AddAreaForm {...parentAddMethods} modalVisible={addModalVisible} />
          <EditAreaForm
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
