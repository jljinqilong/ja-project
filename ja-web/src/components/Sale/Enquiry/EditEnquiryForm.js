import React, { PureComponent } from 'react';
import { Form, Card, Input, Select, DatePicker, Row, Col, InputNumber, message } from 'antd';
import moment from 'moment';
import SimpleModal from '../../Modal/SimpleModal';
import { getEnquiryById } from '../../../services/enquiry';

const { TextArea } = Input;
const FormItem = Form.Item;

@Form.create()
export default class EditEnquiryForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      detail: {},
      backup: {},
      count: 0,
    };
  }

  componentDidMount() {
    const { currentEditId: currentEditCustomerId } = this.props;
    this.get(currentEditCustomerId);
  }

  getNextSeq = () => {
    const { count } = this.state;
    this.setState({
      count: count + 1,
    });
    return count;
  };

  /* 获取询价记录 */
  get = rowId => {
    if (rowId) {
      getEnquiryById(rowId)
        .then(resp => {
          const { data } = resp;
          return data;
        })
        .then(data => {
          this.setState({
            detail: data,
            backup: { ...data },
          });
        })
        .catch(() => {
          message.error('查询询价记录失败');
        });
    }
  };

  reset = () => {
    const { backup } = this.state;
    const { form } = this.props;
    form.resetFields();
    this.setState({ detail: { ...backup } });
  };

  handleOK = () => {
    const { form, handleEdit } = this.props;
    const { detail } = this.state;
    const { estimatedSigningDate, inquiryTime } = detail;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const enquiry = {
        rowId: detail.rowId,
        estimatedSigningDate, // 时间转换，下面的参数会覆盖此参数
        ...fieldsValue,
        inquiryTime,
      };
      // console.log(enquiry)
      handleEdit(enquiry);
    });
  };

  handleCancel = () => {
    const { handleEditModalVisible } = this.props;
    this.reset();
    handleEditModalVisible();
  };

  // 修改时间
  changeEstimatedSigningDate = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        estimatedSigningDate: dateString,
      },
    });
  };

  // string
  changeInquiryTime = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        inquiryTime: dateString,
      },
    });
  };

  changeActualFollowTime = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        actualFollowTime: dateString,
      },
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
    const { detail } = this.state;

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
                  initialValue: detail.inquiryTitle,
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
                  initialValue: detail.customerId,
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
                  initialValue: detail.estimatedSalesAmount,
                })(<InputNumber step={0.01} style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="预计签单日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('estimatedSigningDate', {
                  initialValue: detail.estimatedSigningDate
                    ? moment(detail.estimatedSigningDate, 'YYYY-MM-DD HH:mm:ss')
                    : null,
                })(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeEstimatedSigningDate}
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
                  initialValue: detail.salesPhaseId,
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
                  initialValue: detail.productTypeId,
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
                {getFieldDecorator('actualFollowTime', {
                  initialValue: detail.actualFollowTime
                    ? moment(detail.actualFollowTime, 'YYYY-MM-DD HH:mm:ss')
                    : null,
                })(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeActualFollowTime}
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
    const { detail } = this.state;

    return (
      <Card title="询单信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="项目地" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('projectAddress', {
                  initialValue: detail.projectAddress,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="目的国" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('destinationCountry', {
                  initialValue: detail.destinationCountry,
                })(<Input placeholder="请输入" />)}
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
                  initialValue: detail.power,
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
                  initialValue: detail.orderTotal,
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
                  initialValue: detail.batteryTypeId,
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
                  initialValue: detail.sellingPrice,
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="主栅根数" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('mainGateNumber', {
                  initialValue: detail.mainGateNumber,
                })(<InputNumber placeholder="请输入" />)}
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
                  initialValue: detail.tradeModeId,
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
                {getFieldDecorator('firstYearAttenuation', {
                  initialValue: detail.firstYearAttenuation,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="付款条件" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('paymentTerm', {
                  initialValue: detail.paymentTerm,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="认证要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('certificationRequire', {
                  initialValue: detail.certificationRequire,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="送功率" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('outputPower', {
                  initialValue: detail.outputPower,
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="标板要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('plateRequire', {
                  initialValue: detail.plateRequire,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="质保要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('warrantyRequire', {
                  initialValue: detail.warrantyRequire,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="特殊要求" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('specialRequire', {
                  initialValue: detail.specialRequire,
                })(<Input placeholder="请输入" />)}
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
                {getFieldDecorator('salesman', {
                  initialValue: detail.salesman,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="违约条款" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('violateClause', {
                  initialValue: detail.violateClause,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="交期要求日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('inquiryTime', {
                  initialValue: detail.inquiryTime
                    ? moment(detail.inquiryTime, 'YYYY-MM-DD')
                    : null,
                })(
                  <DatePicker
                    placeholder="请选择"
                    onChange={this.changeInquiryTime}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="交期要求数量（MW）" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('number', {
                  initialValue: detail.number,
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <FormItem label="BOM要求" labelCol={{ span: 4 }} wrapperCol={{ span: 20 }}>
                {getFieldDecorator('bomRequire', {
                  initialValue: detail.bomRequire,
                })(<TextArea rows={4} placeholder="备注信息" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="边框颜色" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('borderColorId', {
                  initialValue: detail.borderColorId,
                })(
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
                {getFieldDecorator('borderSpecificationId', {
                  initialValue: detail.borderSpecificationId,
                })(
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
                {getFieldDecorator('backboardColorId', {
                  initialValue: detail.backboardColorId,
                })(
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
                {getFieldDecorator('backboardMaterialId', {
                  initialValue: detail.backboardMaterialId,
                })(
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
                {getFieldDecorator('junctionBoxId', {
                  initialValue: detail.junctionBoxId,
                })(
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
                {getFieldDecorator('glass', {
                  initialValue: detail.glass,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="EVA" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('evaId', {
                  initialValue: detail.evaId,
                })(
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
                {getFieldDecorator('cost', {
                  initialValue: detail.cost,
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="毛利率" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('grossProfitRatio', {
                  initialValue: detail.grossProfitRatio,
                })(<InputNumber style={{ width: '100%' }} placeholder="请输入" />)}
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
        title="修改询价记录"
        width={1000}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.enquiryInfo()}
        {this.inquiriesInfo()}
      </SimpleModal>
    );
  }
}
