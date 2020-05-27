import React, { PureComponent } from 'react';
import { Form, Card, Input, Select, Row, Col, message } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';
import { getProduct } from '../../../services/product';

const FormItem = Form.Item;
@Form.create()
export default class EditProductForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      detail: {},
    };
  }

  componentDidMount() {
    const { currentEditId: currentEditProductId } = this.props;
    this.get(currentEditProductId);
  }

  get = rowId => {
    if (rowId) {
      getProduct(rowId)
        .then(resp => {
          const { data } = resp;

          return data;
        })
        .then(data => {
          this.setState({
            detail: data,
          });
        })
        .catch(() => {
          message.error('查询产品明细失败');
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
    const {
      form,
      handleEdit,
      siliconTypeList,
      cellNumberList,
      muduleTypeList,
      muduleCodeList,
      cellTechnologyList,
    } = this.props;
    const { detail } = this.state;
    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const siliconType = siliconTypeList.find(item => item.rowId === fieldsValue.siliconTypeId);
      const cellNumber = cellNumberList.find(item => item.rowId === fieldsValue.cellNumberId);
      const muduleType = muduleTypeList.find(item => item.rowId === fieldsValue.muduleTypeId);
      const muduleCode = muduleCodeList.find(item => item.rowId === fieldsValue.muduleCodeId);
      const cellTechnology = cellTechnologyList.find(
        item => item.rowId === fieldsValue.cellTechnologyId
      );
      const params = {
        rowId: detail.rowId,
        ...fieldsValue,
        siliconType: siliconType.code,
        cellNumber: cellNumber.code,
        muduleType: muduleType.code,
        muduleCode: muduleCode.code,
        cellTechnology: cellTechnology.code,
      };

      handleEdit(params);
    });
  };

  renderBaseInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      siliconTypeList,
      cellNumberList,
      muduleTypeList,
      muduleCodeList,
      cellTechnologyList,
    } = this.props;

    const { detail } = this.state;

    return (
      <Card title="基本信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="产品编号" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('productNo', {})(<span>{detail.productNo}</span>)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="硅片类型" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('siliconTypeId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择硅片类型',
                    },
                  ],
                  initialValue: detail.siliconTypeId,
                })(
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
            <Col span={12}>
              <FormItem label="电池片数" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('cellNumberId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择电池片数',
                    },
                  ],
                  initialValue: detail.cellNumberId,
                })(
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
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="组件类型" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('muduleTypeId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择组件类型',
                    },
                  ],
                  initialValue: detail.muduleTypeId,
                })(
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

            <Col span={12}>
              <FormItem label="组件关键信息码" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('muduleCodeId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择组件关键信息码',
                    },
                  ],
                  initialValue: detail.muduleCodeId,
                })(
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
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="组件功率" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('ratedPower', {
                  rules: [
                    {
                      required: true,
                      message: '请输入组件功率',
                    },
                  ],
                  initialValue: detail.ratedPower,
                })(<Input placeholder="请输入" maxLength={6} />)}
              </FormItem>
            </Col>

            <Col span={12}>
              <FormItem label="电池片技术" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('cellTechnologyId', {
                  rules: [
                    {
                      required: true,
                      message: '请选择电池片技术',
                    },
                  ],
                  initialValue: detail.cellTechnologyId,
                })(
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
        </Form>
      </Card>
    );
  }

  render() {
    const { modalVisible } = this.props;
    return (
      <SimpleModal
        title="编辑产品"
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
