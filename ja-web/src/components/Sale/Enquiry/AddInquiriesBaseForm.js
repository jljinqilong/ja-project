import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Select, Card, Row, Col, Popconfirm, Icon, message } from 'antd';
import moment from 'moment';
import SimpleModal from '../../Modal/SimpleModal';
import EditableTable from '../../EditableTable/EditableTable';
import CascaderSelect from './CascaderSelect';
import { listCountryCascader } from '../../../services/area';

const FormItem = Form.Item;

// 询单基础信息
@Form.create()
export default class AddInquiriesBaseForm extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      regTime: '',
      count: 1,
      totalAmount: 0,
      totalPower: 0,
      inquiriesProductList: [],
      inquiriesDeliveryTimeList: [],
      countryProvCity: 0,
      countryCascaderList: [],
    };

    // 产品信息
    this.productColumns = [
      {
        title: '产品编号',
        dataIndex: 'productId',
        editable: true,
        width: 200,
        rules: [
          {
            required: true,
            message: '请输入产品编号',
          },
        ],
      },
      {
        title: '电池片数',
        dataIndex: 'cellNumberId',
        editable: true,
        width: 100,
        rules: [
          {
            required: true,
            message: '请选择电池片数',
          },
        ],
      },
      {
        title: '电池型号',
        dataIndex: 'batteryTypeId',
        editable: true,
        width: 200,
        rules: [
          {
            required: true,
            message: '请选择电池型号',
          },
        ],
      },
      {
        title: '标称功率',
        dataIndex: 'power',
        width: 120,
      },
      {
        title: '单位',
        dataIndex: 'unitId',
        editable: true,
        width: 100,
        rules: [
          {
            required: true,
            message: '请输入单位',
          },
        ],
      },
      {
        title: '数量',
        dataIndex: 'num',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入数量',
          },
        ],
        width: 100,
      },
      {
        title: '赠送数量',
        dataIndex: 'giveNum',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入数量',
          },
        ],
        width: 100,
      },
      {
        title: '总功率',
        dataIndex: 'totalPower',
        width: 80,
      },
      {
        title: '单价',
        dataIndex: 'unitPrice',
        editable: true,
        width: 100,
        rules: [
          {
            required: true,
            message: '请输入单价',
          },
        ],
      },
      {
        title: '售价',
        dataIndex: 'amount',
        width: 80,
      },
      {
        title: '操作',
        width: 50,
        render: (text, record, index) => (
          <Fragment>
            <Popconfirm title="确认删除?" onConfirm={() => this.handleDeleteItem(index)}>
              <a title="删除">
                <Icon type="delete" />
              </a>
            </Popconfirm>
          </Fragment>
        ),
      },
    ];

    // 交期信息
    this.deliveryColumns = [
      {
        title: '交期日期',
        dataIndex: 'deliveryTime',
        editable: true,
        width: 200,
        rules: [
          {
            required: false,
            message: '请输入产品编号',
          },
        ],
      },
      {
        title: '数量',
        dataIndex: 'num',
        editable: true,
        width: 200,
        rules: [
          {
            required: false,
            message: '请选择电池片数',
          },
        ],
      },
      {
        title: '操作',
        width: 80,
        render: (text, record, index) => (
          <Fragment>
            <Popconfirm title="确认删除?" onConfirm={() => this.handleDeleteDeliveryItem(index)}>
              <a title="删除">
                <Icon type="delete" />
              </a>
            </Popconfirm>
          </Fragment>
        ),
      },
    ];
  }

  componentWillMount() {
    // TODO 获取国家 list
    listCountryCascader({ pid: 1 })
      .then(resp => resp.data)
      .then(data => {
        this.setState({
          countryCascaderList: data,
        });
      });
  }

  getNextSeq = () => {
    const { count } = this.state;
    this.setState({
      count: count + 1,
    });
    return count;
  };

  clear = () => {
    const { form } = this.props;

    form.resetFields();
    this.setState({
      count: 1,
      inquiriesProductList: [],
      inquiriesDeliveryTimeList: [],
    });
  };

  handleOK = () => {
    const { form, handleAdd } = this.props;
    const {
      regTime,
      inquiriesProductList,
      inquiriesDeliveryTimeList,
      totalAmount,
      totalPower,
      countryProvCity, // 三级
    } = this.state;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      if (countryProvCity === null || countryProvCity === '') {
        message.error('请选择项目地');
        return;
      }
      if (!inquiriesProductList || inquiriesProductList.length === 0) {
        message.error('请先设置产品信息');
        return;
      }

      if (totalPower < 0 || totalAmount < 0) {
        message.error('赠送数量不能大于总数量，请检查');
        return;
      }

      const inquiries = {
        ...fieldsValue,
        inquiriesProductList,
        inquiriesDeliveryTimeList,
        totalAmount,
        totalPower,
        regTime,
        countryProvCity,
      };

      handleAdd(inquiries);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.clear();
    handleModalVisible();
  };

  // =========================== item start ==========================
  // 产品 list
  handleUpdateItem = record => {
    if (!record.rowKey) return;
    const { inquiriesProductList } = this.state;
    const rowKey = inquiriesProductList.findIndex(detail => record.rowKey === detail.rowKey);
    if (rowKey > -1) {
      const item = inquiriesProductList[rowKey];
      inquiriesProductList.splice(rowKey, 1, {
        ...item,
        ...this.handleCalculate(record),
      });
      const newProducts = [...inquiriesProductList];
      this.updateProducts(newProducts);
    } else {
      const newProducts = [...inquiriesProductList, record];
      this.updateProducts(newProducts);
    }
  };

  // 计算总功率、总金额
  handleCalculate = item => {
    const { productList, productUnitList } = this.props;
    const { productId, unitId } = item;
    const product = productList.find(pro => pro.rowId === productId);
    const productUnit = productUnitList.find(pro => pro.rowId === unitId);

    if (!product) return item;

    // 计算功率档
    const { ratedPower: power } = product;
    const unitPrice = parseFloat(item.unitPrice || 0);
    const powerToInt = parseInt(power, 10);
    // 计算总功率、总金额
    const num = parseFloat(item.num || 0);
    // 赠送数量
    const giveNum = parseFloat(item.giveNum || 0);
    // 有效数量
    const validNum = num - giveNum;

    let amount = 0;
    let totalPower = 0;
    if (productUnit) {
      if (productUnit.name === 'W') {
        totalPower = validNum * powerToInt;
        amount = (totalPower * unitPrice).toFixed(2);
      } else if (productUnit.name === 'PCS') {
        // PCS
        totalPower = validNum * powerToInt;
        amount = (validNum * unitPrice).toFixed(2);
      }
    }

    return {
      ...item,
      productionNo: product.name,
      power,
      totalPower,
      amount,
    };
  };

  handleAddItem = () => {
    const { inquiriesProductList } = this.state;

    const newProduct = {
      rowKey: this.getNextSeq(),
      productId: '',
      productionNo: '',
      power: 0,
      totalPower: 0,
      amount: 0.0,
    };

    const newProducts = [...inquiriesProductList, newProduct];
    this.updateProducts(newProducts);
  };

  // 添加交期行
  handleAddDeliveryItem = () => {
    const { inquiriesDeliveryTimeList } = this.state;

    const newDelivery = {
      rowKey: this.getNextSeq(),
      deliveryTime: moment(),
      num: 0,
    };

    const newDeliverys = [...inquiriesDeliveryTimeList, newDelivery];
    this.updateDelivery(newDeliverys);
  };

  // 删除交期行
  handleDeleteDeliveryItem = index => {
    const { inquiriesDeliveryTimeList } = this.state;
    inquiriesDeliveryTimeList.splice(index, 1);
    const newDeliverys = [...inquiriesDeliveryTimeList];
    this.updateDelivery(newDeliverys);
  };

  handleDeleteItem = index => {
    const { inquiriesProductList } = this.state;
    inquiriesProductList.splice(index, 1);
    const newProducts = [...inquiriesProductList];
    this.updateProducts(newProducts);
  };

  changeRegTime = (date, dateString) => {
    this.setState({
      regTime: dateString,
    });
  };

  // 更新交期
  updateDelivery = delivery => {
    if (delivery) {
      const newDeliverty = delivery.map(item => ({
        ...item,
        deliveryTime: moment(item.deliveryTime, 'YYYY-MM-DD'),
      }));

      this.setState({
        inquiriesDeliveryTimeList: newDeliverty,
      });
    }
  };

  updateProducts = products => {
    this.setState({
      inquiriesProductList: products,
      totalAmount: products
        .map(item => item.amount)
        .filter(item => !!item)
        .reduce((sum, current) => sum + parseFloat(current), 0),
      totalPower: products
        .map(item => item.totalPower)
        .filter(item => !!item)
        .reduce((sum, current) => sum + parseFloat(current), 0),
    });
  };

  // =========================== item end ==========================

  // 三级联动
  headleChangeCountryProvCity = targetOption => {
    const { countryCascaderList } = this.state;

    // console.log(targetOption)
    const param = {
      pid: targetOption.rowId,
    };

    listCountryCascader(param)
      .then(resp => resp.data)
      .then(data => {
        targetOption.loading = false; // eslint-disable-line no-param-reassign
        targetOption.children = data; // eslint-disable-line no-param-reassign

        this.setState({
          countryCascaderList: [...countryCascaderList],
        });
      })
      .catch(() => {
        message.error('查询地区失败');
      });
  };

  // 获取省市区结果
  headleGetCountryProvCity = async value => {
    await this.setState({
      countryProvCity: value.join(','),
      cascaderDefaultValue: value,
    });
  };

  inquiriesInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      tradeModeList,
      customerList,
      areaList,
      productTypeList,
      currencyList,
      inquiriesStatusList,
      dispatchPlaceList,
      projectDistributionList,
    } = this.props;

    const { countryCascaderList, cascaderDefaultValue } = this.state;

    return (
      <Form>
        <Row gutter={24}>
          <Col span={12}>
            <FormItem label="询单标题" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('inquiryTitle', {
                rules: [
                  {
                    required: true,
                    message: '请输入询单标题',
                  },
                ],
              })(<Input placeholder="请输入" maxLength="50" />)}
            </FormItem>
          </Col>
          <Col span={12}>
            <FormItem label="客户" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('customerId', {
                rules: [
                  {
                    required: true,
                    message: '请选择客户',
                  },
                ],
              })(
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
        </Row>
        <Row gutter={24}>
          <Col span={12}>
            <FormItem label="区域" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('saleAreaId', {
                rules: [
                  {
                    required: true,
                    message: '请选择',
                  },
                ],
              })(
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
          <Col span={12}>
            <FormItem label="项目/分销" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('projectDistributionId', {
                rules: [
                  {
                    required: true,
                    message: '请输入',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {projectDistributionList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={12}>
            <FormItem label="发货地" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('dispatchPlaceId', {
                rules: [
                  {
                    required: true,
                    message: '请选择发货地',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {dispatchPlaceList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col span={12}>
            <FormItem label="项目地" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('countryProvCity', {
                rules: [
                  {
                    required: false,
                    message: '请选择',
                  },
                ],
              })(
                <CascaderSelect
                  countryCascaderList={countryCascaderList}
                  onLoad={this.headleChangeCountryProvCity}
                  getCascaderSelectValue={this.headleGetCountryProvCity}
                  initialValue={cascaderDefaultValue}
                />
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={12}>
            <FormItem label="产品类型" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('productTypeId', {
                rules: [
                  {
                    required: true,
                    message: '请输入功率档位',
                  },
                ],
              })(
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
          <Col span={12}>
            <FormItem label="币种" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('currencyId', {
                rules: [
                  {
                    required: true,
                    message: '请输入',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {currencyList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>

        <Row gutter={24}>
          <Col span={12}>
            <FormItem label="贸易方式" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('tradeModeId', {
                rules: [
                  {
                    required: true,
                    message: '请选择',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {tradeModeList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col span={12}>
            <FormItem label="状态" labelCol={labelCol} wrapperCol={wrapperCol}>
              {getFieldDecorator('statusId', {
                rules: [
                  {
                    required: true,
                    message: '请选择',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {inquiriesStatusList.map(d => (
                    <Select.Option key={d.rowId} value={d.rowId}>
                      {d.name}
                    </Select.Option>
                  ))}
                </Select>
              )}
            </FormItem>
          </Col>
        </Row>
      </Form>
    );
  }

  // 询单产品 list
  inquiriesProductInfo() {
    const { inquiriesProductList, totalAmount, totalPower } = this.state;
    const { form, productUnitList, productList, batteryTypeList, cellNumberList } = this.props;

    const productColumns = this.productColumns.map(col => {
      return {
        ...col,
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype:
            col.dataIndex === 'productId' ||
            col.dataIndex === 'unitId' ||
            col.dataIndex === 'batteryTypeId' ||
            col.dataIndex === 'cellNumberId'
              ? 'select'
              : col.dataIndex === 'num' ||
                col.dataIndex === 'giveNum' ||
                col.dataIndex === 'unitPrice'
                ? 'number'
                : 'input',
          datasource:
            col.dataIndex === 'productId'
              ? productList
              : col.dataIndex === 'unitId'
                ? productUnitList
                : col.dataIndex === 'batteryTypeId'
                  ? batteryTypeList
                  : col.dataIndex === 'cellNumberId'
                    ? cellNumberList
                    : null,
          form,
          rules: col.rules,
          onChange: this.handleUpdateItem,
        }),
      };
    });

    return (
      <Card title="产品信息" bordered={false}>
        <EditableTable
          title="产品信息"
          dataSource={inquiriesProductList}
          form={form}
          columns={productColumns}
          onAdd={() => this.handleAddItem()}
          onDelete={index => this.handleDeleteItem(index)}
        />
        <h2 style={{ marginTop: '20px', float: 'right' }}>总功率: {totalPower.toFixed(2)}</h2>
        <h2 style={{ marginTop: '20px', marginRight: '30px', float: 'right' }}>
          总金额: {totalAmount.toFixed(2)}
        </h2>
      </Card>
    );
  }

  // 询单交期 list
  inquiriesDeliveryInfo() {
    const { inquiriesDeliveryTimeList } = this.state;
    const { form } = this.props;

    const deliveryColumns = this.deliveryColumns.map(col => {
      return {
        ...col,
        // 设置单元格属性
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype:
            col.dataIndex === 'deliveryTime'
              ? 'date'
              : col.dataIndex === 'num'
                ? 'number'
                : 'input',
          datasource: null,
          form,
          rules: col.rules,
        }),
      };
    });

    return (
      <Card title="交期信息" bordered={false}>
        <EditableTable
          title="交期信息"
          dataSource={inquiriesDeliveryTimeList}
          form={form}
          columns={deliveryColumns}
          onAdd={() => this.handleAddDeliveryItem()}
          onDelete={index => this.handleDeleteDeliveryItem(index)}
        />
      </Card>
    );
  }

  render() {
    const { modalVisible } = this.props;

    return (
      <SimpleModal
        title="新增询单记录单"
        width={1300}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        {this.inquiriesInfo()}
        {this.inquiriesProductInfo()}
        {this.inquiriesDeliveryInfo()}
      </SimpleModal>
    );
  }
}
