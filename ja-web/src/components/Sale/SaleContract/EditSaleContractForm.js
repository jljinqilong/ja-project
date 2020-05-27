import React, { PureComponent } from 'react';
import { Form, Card, Input, Select, DatePicker, Row, Col, message } from 'antd';
import moment from 'moment/moment';
import SaleContractProductTable from './SaleContractProductTable';
import SaleContractDeliveryTable from './SaleContractDeliveryTable';
import { getSaleContractById } from '../../../services/saleContract';
import SimpleModal from '../../Modal/SimpleModal';
import CascaderSelect from '../Enquiry/CascaderSelect';
import { listCountryCascader } from '../../../services/area';

const { TextArea } = Input;
const FormItem = Form.Item;

const saleContractOptions = [
  { rowId: 0, name: '未完成' },
  { rowId: 1, name: '完成' },
  { rowId: 2, name: '逾期未完成' },
];

@Form.create()
export default class EditSaleContractForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      detail: {},
      backup: {},
      countryCascaderList:[],
      countryId:0,
      provinceId:0,
      cityId:0,

    };
  }

  componentDidMount() {
    const { currentEditId: currentEditCustomerId } = this.props;
    this.get(currentEditCustomerId);
  }

  get = rowId => {
    if (rowId) {
      getSaleContractById(rowId)
        .then(resp => resp.data)
        .then(data => {
          console.log('data====>',data);
          // 默认的国省市\ 1, 7 ,247
          const provId = data.provinceId;
          // 组装数据
          data.countryList.forEach(d => {
            if (d.rowId === data.countryId) {
              d.children = data.provinceList; // eslint-disable-line no-param-reassign
              data.provinceList.forEach(p => {
                if (p.rowId === provId) {
                  p.children = data.cityList; // eslint-disable-line no-param-reassign
                }
              });
            }
          });

          this.setState({
            detail: data,
            backup: { ...data },
            countryCascaderList: data.countryList,
            cascaderDefaultValue: [data.countryId, provId, data.cityId],
          });
        })
        .catch(() => {
          message.error('查询合同明细失败');
        });
    }
  };

  clear = () => {
    const { form } = this.props;

    form.resetFields();
    this.updateProducts([]);
  };

  reset = () => {
    const { backup } = this.state;
    const { form } = this.props;
    form.resetFields();
    this.setState({ detail: { ...backup } });
  };

  handleOK = () => {
    const { form, handleEdit } = this.props;
    const { detail ,countryId,provinceId,cityId} = this.state;
    const {
      detail: { totalAmount, totalPower },
    } = this.state;
    const { signDate, getNoDate, nextTime } = detail;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      if (!detail.productList || detail.productList.length === 0) {
        message.error('请先设置产品信息');
        return;
      }

      if (totalPower < 0 || totalAmount < 0) {
        message.error('赠送数量不能大于总数量，请检查');
        return;
      }

      const params = {
        rowId: detail.rowId,
        ...fieldsValue,
        signDate,
        getNoDate,
        nextTime,
        totalAmount,
        totalPower,
        countryId,
        provinceId,
        cityId,
        saleContractDetailList: detail.productList,
        saleDeliveryList: detail.saleDeliveryList,
      };

      handleEdit(params);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.reset();
    handleModalVisible();
  };

  changeSignDate = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        signDate: dateString,
      },
    });
  };

  changeGetNoDate = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        getNoDate: dateString,
      },
    });
  };

  changeNextTime = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        nextTime: dateString,
      },
    });
  };

  updateProducts = (products, totalAmount, totalPower) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        productList: products,
        totalAmount,
        totalPower,
      },
    });
  };

  updateDeliveries = deliveries => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        saleDeliveryList: deliveries,
      },
    });
  };

  // ================三级联动  ==================== start
  headleChangeCountryProvCity = targetOption => {
    const { countryCascaderList } = this.state;

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
      countryId:value[0]?value[0]:null,
      provinceId:value[1]?value[1]:null,
      cityId:value[2]?value[2]:null,
      cascaderDefaultValue: value, // 编辑时 value值需要动态赋值，否则回显后无法更改
    });
  };



  renderBaseInfo() {
    const labelCol = { span: 6 };
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
    const { detail } = this.state;
    const { countryCascaderList, cascaderDefaultValue } = this.state;

    return (
      <Card title="基本信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="合同编号" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractNo', {
                  rules: [
                    {
                      required: true,
                      message: '请输入合同编号',
                    },
                  ],
                  initialValue: detail.contractNo,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="合同类型" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractType', {
                  rules: [
                    {
                      required: true,
                      message: '请选择合同类型',
                    },
                  ],
                  initialValue: detail.contractType,
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
              <FormItem label="合同标题" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractTitle', {
                  rules: [
                    {
                      required: true,
                      message: '请选择合同标题',
                    },
                  ],
                  initialValue: detail.contractTitle,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="对应客户" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customer', {
                  rules: [
                    {
                      required: true,
                      message: '请选择对应客户',
                    },
                  ],
                  initialValue: detail.customer,
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
              <FormItem label="付款方式" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('payMethod', {
                  rules: [
                    {
                      required: true,
                      message: '请选择付款方式',
                    },
                  ],
                  initialValue: detail.payMethod,
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
              <FormItem label="客户方签约人" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('clientContractor', {
                  rules: [
                    {
                      required: true,
                      message: '请选择客户方签约人',
                    },
                  ],
                  initialValue: detail.clientContractor,
                })(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="签约日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('signDate', {
                  rules: [
                    {
                      required: true,
                      message: '请选择签约日期',
                    },
                  ],
                  initialValue: detail.signDate ? moment(detail.signDate) : null,
                })(
                  <DatePicker
                    placeholder="请选择签约日期"
                    onChange={this.changeSignDate}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="取号日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('getNoDate', {
                  initialValue: detail.getNoDate ? moment(detail.getNoDate) : null,
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
              <FormItem label="币别" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('saleCurrency', {
                  rules: [
                    {
                      required: true,
                      message: '请选择币别',
                    },
                  ],
                  initialValue: detail.saleCurrency,
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
            <Col span={12}>
              <FormItem label="合同状态" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('status', {
                  initialValue: detail.status,
                })(
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
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="下次跟进时间" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('nextTime', {
                  initialValue: detail.nextTime ? moment(detail.nextTime) : null,
                })(
                  <DatePicker
                    placeholder="请选择下次跟进时间"
                    onChange={this.changeNextTime}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="业务人员" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('businessPerson', {
                  rules: [
                    {
                      required: true,
                      message: '请选择业务人员',
                    },
                  ],
                  initialValue: detail.businessPerson,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="所属区域" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('areaId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择所属区域',
                    },
                  ],
                  initialValue: detail.areaId,
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
              <FormItem label="发货地" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('deliveryAddress', {
                  rules: [
                    {
                      required: true,
                      message: '请选择发货地',
                    },
                  ],
                  initialValue: detail.deliveryAddress,
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
                {getFieldDecorator('countryProvCity')(
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
                {getFieldDecorator('remark', {
                  initialValue: detail.remark,
                })(<TextArea rows={4} maxLength={100} placeholder="请输入" />)}
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
    const {
      detail: { totalPower, totalAmount },
    } = this.state;
    let {
      detail: { productList: products },
    } = this.state;

    if (products) {
      products = products.map((item, index) => ({ ...item, rowKey: index + 1 }));
    }

    const { form, productUnitList, productList } = this.props;
    return (
      <Card title="产品信息" bordered={false}>
        <SaleContractProductTable
          form={form}
          productUnitList={productUnitList}
          productList={productList}
          products={products}
          totalAmount={totalAmount}
          totalPower={totalPower}
          onUpdate={this.updateProducts}
        />
      </Card>
    );
  }

  renderDeliveryInfo = () => {
    let {
      detail: { saleDeliveryList },
    } = this.state;
    const { form } = this.props;

    if (saleDeliveryList) {
      saleDeliveryList = saleDeliveryList.map((item, index) => ({
        deliveryNum: item.deliveryNum || 0,
        deliveryTime: moment(item.deliveryTime),
        rowKey: index + 1,
      }));
    }

    return (
      <Card title="交期信息" bordered={false}>
        <SaleContractDeliveryTable
          form={form}
          deliveries={saleDeliveryList}
          onUpdate={this.updateDeliveries}
        />
      </Card>
    );
  };

  render() {
    const { modalVisible } = this.props;

    return (
      <SimpleModal
        title="编辑合同"
        width={1200}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.renderBaseInfo()}
        {this.renderPaymentInfo()}
        {this.renderProductInfo()}
        {this.renderDeliveryInfo()}
      </SimpleModal>
    );
  }
}
