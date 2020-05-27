import React, { PureComponent } from 'react';
import { Form, Card, Input, Select, Row, Col, message, DatePicker } from 'antd';
import moment from 'moment';
import SimpleModal from '../../Modal/SimpleModal';
import { getStockUp } from '../../../services/stockUp';

const FormItem = Form.Item;
@Form.create()
export default class EditStockUpForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      detail: {},
    };
  }

  componentDidMount() {
    const { currentEditId: currentEditStockUpId } = this.props;
    this.get(currentEditStockUpId);
  }

  get = rowId => {
    if (rowId) {
      getStockUp(rowId)
        .then(resp => resp.data)
        .then(data => {
          console.log(data);
          this.setState({
            detail: data,
          });
        })
        .catch(() => {
          message.error('查询备货计划明细失败');
        });
    }
  };

  clear = () => {
    const { form } = this.props;

    form.resetFields();
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    handleModalVisible();
  };

  handleOK = () => {
    const { form, handleEdit } = this.props;
    const { detail } = this.state;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      const params = {
        rowId: detail.rowId,
        ...fieldsValue,
      };

      handleEdit(params);
    });
  };

  renderBaseInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      stockAddressList,
      customerList,
      customerPaymentList,
      categoryAList,
      deliveryMethodList,
    } = this.props;

    const { detail } = this.state;

    return (
      <Card title="基本信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="备货编号" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('stockNo', {
                  rules: [
                    {
                      required: true,
                      message: '请输入备货编号',
                    },
                  ],
                  initialValue: detail.stockNo,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="销售" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('salesman', {
                  rules: [
                    {
                      required: true,
                      message: '请输入销售人员',
                    },
                  ],
                  initialValue: detail.salesman,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="备货地点" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('stockAddress', {
                  rules: [
                    {
                      required: true,
                      message: '请选择备货地点',
                    },
                  ],
                  initialValue: detail.stockAddress,
                })(
                  <Select placeholder="请选择">
                    {stockAddressList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="备货通知日期" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('stockDate', {
                  rules: [
                    {
                      required: true,
                      message: '请选择备货通知日期',
                    },
                  ],
                  initialValue: detail.stockDate ? moment(detail.stockDate) : detail.stockDate,
                })(<DatePicker placeholder="请选择" style={{ width: '100%' }} />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="评审单号/OA流程编号" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('oaNo', {
                  rules: [
                    {
                      required: true,
                      message: '请输入评审单号/OA流程编号',
                    },
                  ],
                  initialValue: detail.oaNo,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="客户名称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customer', {
                  rules: [
                    {
                      required: true,
                      message: '请选择客户名称',
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
              <FormItem label="客户简称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customerName', {
                  rules: [
                    {
                      required: true,
                      message: '请输入客户简称',
                    },
                  ],
                  initialValue: detail.customerName,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="合同号码" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractNo', {
                  rules: [
                    {
                      required: true,
                      message: '请输入合同号码',
                    },
                  ],
                  initialValue: detail.contractNo,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="客户付款信息" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('paymentInfo', {
                  rules: [
                    {
                      required: true,
                      message: '请选择客户付款信息',
                    },
                  ],
                  initialValue: detail.paymentInfo,
                })(
                  <Select placeholder="请选择">
                    {customerPaymentList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="合同签约地" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('contractAddress', {
                  rules: [
                    {
                      required: true,
                      message: '请输入合同签约地',
                    },
                  ],
                  initialValue: detail.contractAddress,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="发货片数" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('sendNum', {
                  rules: [
                    {
                      required: true,
                      message: '请输入发货片数',
                    },
                  ],
                  initialValue: detail.sendNum,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="组件型号（新）" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('newComponentmodel', {
                  rules: [
                    {
                      required: true,
                      message: '请输入组件型号',
                    },
                  ],
                  initialValue: detail.newComponentmodel,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="组件型号（旧）" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('oldComponentmodel', {
                  rules: [
                    {
                      required: true,
                      message: '请输入组件型号',
                    },
                  ],
                  initialValue: detail.oldComponentmodel,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="组件类型" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('componentType', {
                  rules: [
                    {
                      required: true,
                      message: '请输入组件类型',
                    },
                  ],
                  initialValue: detail.componentType,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="电池工艺" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('batteryProcess', {
                  rules: [
                    {
                      required: true,
                      message: '请输入电池工艺',
                    },
                  ],
                  initialValue: detail.batteryProcess,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="电池片封装片数" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('packageNum', {
                  rules: [
                    {
                      required: true,
                      message: '请输入电池片封装片数',
                    },
                  ],
                  initialValue: detail.packageNum,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="镀膜(AR)" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('coating', {
                  rules: [
                    {
                      required: true,
                      message: '请输入镀膜',
                    },
                  ],
                  initialValue: detail.coating,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="组件等级" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('componentLevel', {
                  rules: [
                    {
                      required: true,
                      message: '请输入组件等级',
                    },
                  ],
                  initialValue: detail.componentLevel,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="单片功率（w）" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('singlePower', {
                  rules: [
                    {
                      required: true,
                      message: '请输入单片功率',
                    },
                  ],
                  initialValue: detail.singlePower,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="A类片高效/低效" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('typeA', {
                  rules: [
                    {
                      required: true,
                      message: '请选择A类片',
                    },
                  ],
                  initialValue: detail.typeA,
                })(
                  <Select placeholder="请选择">
                    {categoryAList.map(d => (
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
              <FormItem label="总发货瓦数" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('totalSendNum', {
                  rules: [
                    {
                      required: true,
                      message: '请输入总发货瓦数',
                    },
                  ],
                  initialValue: detail.totalSendNum,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="单位" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('unit', {
                  rules: [
                    {
                      required: true,
                      message: '请输入单位',
                    },
                  ],
                  initialValue: detail.unit,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="交货方式" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('deliveryMethod', {
                  rules: [
                    {
                      required: true,
                      message: '请选择交货方式',
                    },
                  ],
                  initialValue: detail.deliveryMethod,
                })(
                  <Select placeholder="请选择">
                    {deliveryMethodList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="自产/代工" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('mode', {
                  rules: [
                    {
                      required: true,
                      message: '请输入自产/代工',
                    },
                  ],
                  initialValue: detail.mode,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="铝框工艺" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('alProcess', {
                  rules: [
                    {
                      required: true,
                      message: '请输入铝框工艺',
                    },
                  ],
                  initialValue: detail.alProcess,
                })(<Input placeholder="请输入" />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="接线盒" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('junctionBox', {
                  rules: [
                    {
                      required: true,
                      message: '请输入接线盒',
                    },
                  ],
                  initialValue: detail.junctionBox,
                })(<Input placeholder="请输入" />)}
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
        title="编辑备货计划"
        width={1200}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.renderBaseInfo()}
      </SimpleModal>
    );
  }
}
