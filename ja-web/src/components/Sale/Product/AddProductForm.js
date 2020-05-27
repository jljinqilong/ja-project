import React, { PureComponent } from 'react';
import { Form, Card, Select, Row, Col, InputNumber } from 'antd';
import SimpleModal from '../../Modal/SimpleModal';

const FormItem = Form.Item;
@Form.create()
export default class AddProductForm extends PureComponent {
  clear = () => {
    const { form } = this.props;
    form.resetFields();
  };

  handleOK = () => {
    const {
      form,
      siliconTypeList,
      cellNumberList,
      muduleTypeList,
      muduleCodeList,
      cellTechnologyList,
      handleAdd,
    } = this.props;
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
        ...fieldsValue,
        siliconType: siliconType.code,
        cellNumber: cellNumber.code,
        muduleType: muduleType.code,
        muduleCode: muduleCode.code,
        cellTechnology: cellTechnology.code,
      };
      handleAdd(params);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.clear();
    handleModalVisible();
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

    return (
      <Card title="基本信息" bordered={false}>
        <Form>
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
                })(
                  <InputNumber
                    placeholder="请输入"
                    maxLength={6}
                    min={0}
                    style={{ width: '100%' }}
                  />
                )}
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
        title="新增组件"
        width={1200}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        {this.renderBaseInfo()}
      </SimpleModal>
    );
  }
}
