import React, { PureComponent } from 'react';
import { Form, Card, Input, Select, DatePicker, Row, Col, message } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';
import SaleContractProductTable from './SaleContractProductTable';
import SaleContractDeliveryTable from './SaleContractDeliveryTable';
import CascaderSelect from '../Enquiry/CascaderSelect';
import { listCountryCascader } from '../../../services/area';

const { TextArea } = Input;
const FormItem = Form.Item;

@Form.create()
export default class AddSaleContractForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      products: [],
      totalAmount: 0,
      totalPower: 0,
      deliveries: [],
      signDate: '',
      getNoDate: '',
      countryCascaderList:[],
      countryProvCity:0,
      countryId:0,
      provinceId:0,
      cityId:0,
    };
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

  clear = () => {
    const { form } = this.props;
    form.resetFields();
    this.setState({
      products: [],
      deliveries: [],
    });
  };

  handleOK = () => {
    const { form, handleAdd } = this.props;
    const { signDate, getNoDate, products, deliveries, totalAmount, totalPower,countryProvCity,countryId,provinceId,cityId } = this.state;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      if (!products || products.length === 0) {
        message.error('请先设置产品信息');
        return;
      }

      if (totalPower < 0 || totalAmount < 0) {
        message.error('赠送数量不能大于总数量，请检查');
        return;
      }

      const customer = {
        ...fieldsValue,
        signDate,
        getNoDate,
        saleContractDetailList: products,
        saleDeliveryList: deliveries,
        totalAmount,
        totalPower,
        countryProvCity,countryId,provinceId,cityId,
      };

      handleAdd(customer);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.clear();
    handleModalVisible();
  };

  changeSignDate = (date, dateString) => {
    this.setState({
      signDate: dateString,
    });
  };

  changeGetNoDate = (date, dateString) => {
    this.setState({
      getNoDate: dateString,
    });
  };

  updateProducts = (products, totalAmount, totalPower) => {
    this.setState({
      products,
      totalAmount,
      totalPower,
    });
  };

  updateDeliveries = deliveries => {
    this.setState({
      deliveries,
    });
  };


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
    console.log('value=====>',value);
    await this.setState({
      countryProvCity: value.join(','),

      countryId:value[0]?value[0]:null,
      provinceId:value[1]?value[1]:null,
      cityId:value[2]?value[2]:null,
      cascaderDefaultValue: value,
    });
    const {countryProvCity,countryId,provinceId,cityId} = this.state;
    console.log('countryProvCity====>',countryProvCity);
    console.log('countryId====>',countryId);
    console.log('provinceId====>',provinceId);
    console.log('cityId====>',cityId);
  };



  renderBaseInfo() {
    const leftLabelCol = { span: 6 };
    const wrapperCol = { span: 18 };
    const {
      form: { getFieldDecorator },
      contractTypeList,
      customerList,
      paymentTypeList,
      currencyList,
      areaList,
      dispatchPlaceList,
    } = this.props;

    const { countryCascaderList, cascaderDefaultValue } = this.state;

    return (
      <Card title="基本信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="合同编号" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractNo', {
                  rules: [
                    {
                      required: true,
                      message: '请输入合同编号',
                    },
                  ],
                })(<Input placeholder="请输入" maxLength={50} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="合同类型" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractType', {
                  rules: [
                    {
                      required: true,
                      message: '请选择合同类型',
                    },
                  ],
                })(
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
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="合同标题" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractTitle', {
                  rules: [
                    {
                      required: true,
                      message: '请选择合同标题',
                    },
                  ],
                })(<Input placeholder="请输入" maxLength={50} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="对应客户" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customer', {
                  rules: [
                    {
                      required: true,
                      message: '请选择对应客户',
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
              <FormItem label="付款方式" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('payMethod', {
                  rules: [
                    {
                      required: true,
                      message: '请选择付款方式',
                    },
                  ],
                })(
                  <Select placeholder="请选择">
                    {paymentTypeList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="客户方签约人" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('clientContractor', {
                  rules: [
                    {
                      required: true,
                      message: '请选择客户方签约人',
                    },
                  ],
                })(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="签约日期" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('signDate', {
                  rules: [
                    {
                      required: true,
                      message: '请选择签约日期',
                    },
                  ],
                })(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeSignDate}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="取号日期" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('getNoDate', {
                  // rules: [
                  //   {
                  //     required: true,
                  //     message: '请选择取号日期',
                  //   },
                  // ],
                })(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeGetNoDate}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="币别" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('saleCurrency', {
                  rules: [
                    {
                      required: true,
                      message: '请选择币别',
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
              <FormItem label="业务人员" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('businessPerson', {
                  rules: [
                    {
                      required: true,
                      message: '请选择业务人员',
                    },
                  ],
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="所属区域" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('areaId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择所属区域',
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
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="发货地" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('deliveryAddress', {
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
              <FormItem label="项目地" labelCol={leftLabelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('countryProvCity', {
                  rules: [
                    {
                      required: true,
                      message: '请选择项目地',
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
            <Col span={24}>
              <FormItem label="备注" labelCol={{ span: 3 }} wrapperCol={{ span: 21 }}>
                {getFieldDecorator('remark')(
                  <TextArea rows={4} maxLength={100} placeholder="请输入" />
                )}
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    );
  }

  renderPaymentInfo = () => {
    return <Card title="付款条件" bordered={false} />;
  };

  renderProductInfo() {
    const { form, productUnitList, productList } = this.props;
    const { products, totalPower, totalAmount } = this.state;

    return (
      <Card title="产品信息" bordered={false}>
        <SaleContractProductTable
          form={form}
          productUnitList={productUnitList}
          productList={productList}
          products={products}
          totalPower={totalPower}
          totalAmount={totalAmount}
          onUpdate={this.updateProducts}
        />
      </Card>
    );
  }

  renderDeliveryInfo = () => {
    const { deliveries } = this.state;
    const { form } = this.props;

    return (
      <Card title="交期信息" bordered={false}>
        <SaleContractDeliveryTable
          form={form}
          deliveries={deliveries}
          onUpdate={this.updateDeliveries}
        />
      </Card>
    );
  };

  render() {
    const { modalVisible } = this.props;

    return (
      <SimpleModal
        title="新增合同"
        width={1200}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        {this.renderBaseInfo()}
        {this.renderPaymentInfo()}
        {this.renderProductInfo()}
        {this.renderDeliveryInfo()}
      </SimpleModal>
    );
  }
}
