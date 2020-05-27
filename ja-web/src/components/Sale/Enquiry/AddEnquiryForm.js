import React, { PureComponent } from 'react';
import { Form, Card, Input, Select, DatePicker, Row, Col, InputNumber } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';

const { TextArea } = Input;
const FormItem = Form.Item;

@Form.create()
export default class AddEnquiryForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      regTime: '',
      contacts: [],
      count: 0,
    };
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
      contacts: [],
    });
  };

  handleOK = () => {
    const { form, handleAdd } = this.props;
    const { regTime, contacts } = this.state;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const enquiry = {
        ...fieldsValue,
        regTime,
        customerContactsList: contacts,
      };

      handleAdd(enquiry);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.clear();
    handleModalVisible();
  };

  handleUpdateItem = record => {
    if (!record.rowKey) return;
    const { contacts } = this.state;
    const rowKey = contacts.findIndex(detail => record.rowKey === detail.rowKey);
    if (rowKey > -1) {
      const item = contacts[rowKey];
      contacts.splice(rowKey, 1, {
        ...item,
        ...record,
      });
      const newContacts = [...contacts];
      this.updateContacts(newContacts);
    } else {
      const newContacts = [...contacts, record];
      this.updateContacts(newContacts);
    }
  };

  handleAddItem = () => {
    const { contacts } = this.state;

    const newContact = {
      rowKey: this.getNextSeq(),
      name: '',
      position: '',
      mobile: '',
      mail: '',
      tel: '',
    };

    const newContacts = [...contacts, newContact];
    this.updateContacts(newContacts);
  };

  handleDeleteItem = index => {
    const { contacts } = this.state;
    contacts.splice(index, 1);
    const newDetails = [...contacts];
    this.updateContacts(newDetails);
  };

  changeRegTime = (date, dateString) => {
    this.setState({
      regTime: dateString,
    });
  };

  updateContacts = contacts => {
    this.setState({
      contacts,
    });
  };

  enquiryInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      salesPhaseList,
      customerList,
      // productList,
    } = this.props;

    return (
      <Card title="询价信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="询价标题" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('inquiryTitle', {
                  rules: [
                    {
                      required: true,
                      message: '请输入询价标题',
                    },
                  ],
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="对应客户" labelCol={labelCol} wrapperCol={wrapperCol}>
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
              <FormItem label="预计销售金额" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('estimatedSalesAmount', {
                  rules: [
                    {
                      required: true,
                      message: '请输入询价标题',
                    },
                  ],
                })(<InputNumber step={0.01} style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="预计签单日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('estimatedSigningDate')(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeRegTime}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="销售阶段" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('salesPhaseId', {
                  rules: [
                    {
                      required: true,
                      message: '选择销售阶段',
                    },
                  ],
                })(
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
            <Col span={12}>
              <FormItem label="关联产品" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('productTypeId', {
                  rules: [
                    {
                      required: false,
                      message: '请选择关联产品',
                    },
                  ],
                })(
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
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="实际跟进时间" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('actualFollowTime')(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeRegTime}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    );
  }

  inquiriesInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      batteryTypeList,
      tradeModeList,
      borderColorList,
      borderSpecificationList,
      backboardColorList,
      backboardMaterialList,
      junctionBoxList,
      evaList,
      // areaList,
    } = this.props;

    return (
      <Card title="询单信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="项目地" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('projectAddress')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="目的国" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('destinationCountry')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="功率档位" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('power', {
                  rules: [
                    {
                      required: true,
                      message: '请输入功率档位',
                    },
                  ],
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="订单总量" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('orderTotal', {
                  rules: [
                    {
                      required: true,
                      message: '请输入订单总量',
                    },
                  ],
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="电池类型" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('batteryTypeId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择电池类型',
                    },
                  ],
                })(
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
            <Col span={12}>
              <FormItem label="销售价格" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('sellingPrice', {
                  rules: [
                    {
                      required: true,
                      message: '请输入销售价格',
                    },
                  ],
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="主栅根数" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('mainGateNumber')(<InputNumber placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="贸易方式" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('tradeModeId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择贸易方式',
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
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="首年衰减" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('firstYearAttenuation')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="付款条件" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('paymentTerm')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="认证要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('certificationRequire')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="送功率" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('outputPower')(
                  <InputNumber style={{ width: '100%' }} placeholder="请输入" />
                )}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="标板要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('plateRequire')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="质保要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('warrantyRequire')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="特殊要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('specialRequire')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            {/* <Col span={12}>
              <FormItem label="销售区域" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('saleAreaId')(
                  <Select placeholder="请选择">
                    {areaList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col> */}
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="业务员" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('salesman')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="违约条款" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('violateClause')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="交易日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('inquiryTime')(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeRegTime}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="交期要求数量（MW）" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('number')(
                  <InputNumber style={{ width: '100%' }} placeholder="请输入" />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <FormItem label="BOM要求" labelCol={{ span: 4 }} wrapperCol={{ span: 20 }}>
                {getFieldDecorator('bomRequire')(<TextArea rows={4} placeholder="备注信息" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="边框颜色" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('borderColorId')(
                  <Select placeholder="请选择">
                    {borderColorList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="边框规格" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('borderSpecificationId')(
                  <Select placeholder="请选择">
                    {borderSpecificationList.map(d => (
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
              <FormItem label="背板颜色" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('backboardColorId')(
                  <Select placeholder="请选择">
                    {backboardColorList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="背板材质" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('backboardMaterialId')(
                  <Select placeholder="请选择">
                    {backboardMaterialList.map(d => (
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
              <FormItem label="接线盒" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('junctionBoxId')(
                  <Select placeholder="请选择">
                    {junctionBoxList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="玻璃" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('glass')(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="EVA" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('evaId')(
                  <Select placeholder="请选择">
                    {evaList.map(d => (
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
              <FormItem label="成本" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('cost')(
                  <InputNumber style={{ width: '100%' }} placeholder="请输入" />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="毛利率" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('grossProfitRatio')(
                  <InputNumber style={{ width: '100%' }} placeholder="请输入" />
                )}
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    );
  }

  render() {
    const { modalVisible } = this.props;

    return (
      <SimpleModal
        title="新增询价记录"
        width={1000}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        {this.enquiryInfo()}
        {this.inquiriesInfo()}
      </SimpleModal>
    );
  }
}
