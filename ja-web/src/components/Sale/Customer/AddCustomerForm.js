import React, { PureComponent, Fragment } from 'react';
import {
  Form,
  Card,
  Input,
  InputNumber,
  Select,
  DatePicker,
  Row,
  Col,
  Popconfirm,
  Icon,
} from 'antd';
import EditableTable from '../../EditableTable/EditableTable';
import SimpleModal from '../../Modal/SimpleModal';

const { TextArea } = Input;
const FormItem = Form.Item;

@Form.create()
export default class AddCustomerForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      regTime: '',
      contacts: [],
      count: 0,
    };

    this.columns = [
      {
        title: '联系人',
        dataIndex: 'name',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入联系人',
          },
          {
            max: 20,
            message: '最大长度20',
          },
        ],
      },
      {
        title: '联系人职衔',
        dataIndex: 'position',
        editable: true,
        rules: [
          {
            max: 20,
            message: '最大长度20',
          },
        ],
      },
      {
        title: '手机号码',
        dataIndex: 'mobile',
        editable: true,
        rules: [
          {
            required: true,
            message: '请输入手机号',
          },
          {
            len: 11,
            message: '输入11位手机号',
          },
          {
            validator: (rule, value, callback) => {
              if (!value.startsWith('1')) callback('手机号必须以1开头');
              else callback();
            },
          },
        ],
      },
      {
        title: '邮箱',
        dataIndex: 'mail',
        editable: true,
        rules: [
          {
            max: 50,
            message: '最大长度50',
          },
        ],
      },
      {
        title: '固定电话',
        dataIndex: 'tel',
        editable: true,
        rules: [
          {
            max: 20,
            message: '最大长度20',
          },
        ],
      },
      {
        title: '操作',
        width: '10%',
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

      const customer = {
        ...fieldsValue,
        regTime,
        customerContactsList: contacts,
      };

      handleAdd(customer);
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

  renderBaseInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      accountUnitList,
      customerLevelList,
      paymentTypeList,
      areaList,
    } = this.props;

    return (
      <Card title="基本信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="客户名称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customerName', {
                  rules: [
                    {
                      required: true,
                      message: '请输入客户名称',
                    },
                  ],
                })(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="简称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('shortName')(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="英文简称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('shortEnName')(<Input placeholder="请输入" maxLength={40} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="地址" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('address')(<Input placeholder="请输入" maxLength={100} />)}
              </FormItem>
            </Col>
            <Col span={9}>
              <FormItem label="账期" labelCol={{ span: 11 }} wrapperCol={{ span: 13 }}>
                {getFieldDecorator('paymentDays', {
                  rules: [
                    {
                      required: true,
                      message: '请输入账期',
                    },
                  ],
                })(
                  <InputNumber
                    style={{ width: '100%' }}
                    min={0}
                    maxLength={9}
                    placeholder="请输入"
                  />
                )}
              </FormItem>
            </Col>
            <Col span={3}>
              <FormItem>
                {getFieldDecorator('accountUnitId', {
                  rules: [
                    {
                      required: true,
                      message: '选择单位',
                    },
                  ],
                })(
                  <Select placeholder="单位">
                    {accountUnitList.map(d => (
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
              <FormItem label="所属区域" labelCol={labelCol} wrapperCol={wrapperCol}>
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
            <Col span={12}>
              <FormItem label="邮政编码" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('postalCode')(<Input placeholder="请输入" maxLength={6} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="传真" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('fax')(<Input placeholder="请输入" maxLength={50} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="客户等级" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customerLevelId')(
                  <Select placeholder="请选择">
                    {customerLevelList.map(d => (
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
              <FormItem label="税号" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('dutyParagraph')(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="发票寄送地址" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('invoiceSendingAddress')(
                  <Input placeholder="请选择" maxLength={100} />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="发票寄送邮编" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('invoiceSendingPcode')(
                  <Input placeholder="请输入" maxLength={6} />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="收货地址" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('deliveryAddress')(
                  <Input placeholder="请选择" maxLength={100} />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="银行账户" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('bankAccount')(<Input placeholder="请输入" maxLength={30} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="成立时间" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('regTime')(
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
              <FormItem label="注册资本" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('regCapital')(
                  <InputNumber
                    placeholder="请输入"
                    min={0}
                    maxLength={9}
                    style={{ width: '100%' }}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="付款方式" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('paymentTypeId', {
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
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <FormItem label="网址" labelCol={{ span: 4 }} wrapperCol={{ span: 20 }}>
                {getFieldDecorator('webSite')(<Input placeholder="请输入" maxLength={100} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <FormItem label="客户信息" labelCol={{ span: 4 }} wrapperCol={{ span: 20 }}>
                {getFieldDecorator('customerInfo')(
                  <TextArea
                    rows={4}
                    maxLength={100}
                    placeholder="名称/纳税人识别号/地址、电话/开户行及账号"
                  />
                )}
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    );
  }

  renderDebtLimitInfo() {
    const labelCol = { span: 8 };
    const wrapperCol = { span: 16 };
    const {
      form: { getFieldDecorator },
      currencyList,
    } = this.props;

    return (
      <Card title="欠款额度信息" bordered={false}>
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="币别" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('jaCurrencyId', {
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
            <Col span={12}>
              <FormItem label="晶澳欠款额度" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('debtLimit', {
                  rules: [
                    {
                      required: true,
                      message: '请输入晶澳欠款额度',
                    },
                  ],
                })(
                  <InputNumber
                    placeholder="请输入"
                    style={{ width: '100%' }}
                    min={0}
                    max={999999999.99}
                    maxLength={12}
                    precision={2}
                  />
                )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="币别" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('zxbCurrencyId', {
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
            <Col span={12}>
              <FormItem label="中信保欠款额度" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('zxbArrears', {
                  rules: [
                    {
                      required: true,
                      message: '请输入中信保欠款额度',
                    },
                  ],
                })(
                  <InputNumber
                    placeholder="请输入"
                    style={{ width: '100%' }}
                    min={0}
                    max={999999999.99}
                    maxLength={12}
                    precision={2}
                  />
                )}
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
    );
  }

  renderContactInfo() {
    const { contacts } = this.state;
    const { form } = this.props;

    const columns = this.columns.map(col => {
      return {
        ...col,
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: col.editable,
          inputtype: 'input',
          form,
          rules: col.rules,
          onChange: this.handleUpdateItem,
        }),
      };
    });

    return (
      <Card title="联系人信息" bordered={false}>
        <EditableTable
          title="联系人信息"
          dataSource={contacts}
          form={form}
          columns={columns}
          onAdd={() => this.handleAddItem()}
          onDelete={index => this.handleDeleteItem(index)}
        />
      </Card>
    );
  }

  render() {
    const { modalVisible } = this.props;

    return (
      <SimpleModal
        title="新增客户信息"
        width={1000}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
        afterClose={() => this.clear()}
      >
        {this.renderBaseInfo()}
        {this.renderDebtLimitInfo()}
        {this.renderContactInfo()}
      </SimpleModal>
    );
  }
}
