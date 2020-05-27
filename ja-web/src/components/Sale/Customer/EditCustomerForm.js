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
  message,
} from 'antd';
import moment from 'moment';
import SimpleModal from '../../Modal/SimpleModal';
import { getCustomerById } from '../../../services/customer';
import EditableTable from '../../EditableTable/EditableTable';

const { TextArea } = Input;
const FormItem = Form.Item;

@Form.create()
export default class EditCustomerForm extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      detail: {},
      backup: {},
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

  get = rowId => {
    if (rowId) {
      getCustomerById(rowId)
        .then(resp => {
          const { data } = resp;
          const { customerContactsList } = data;
          if (customerContactsList && Array.isArray(customerContactsList)) {
            const newDetails = customerContactsList.map(item => {
              return {
                ...item,
                rowKey: this.getNextSeq(),
              };
            });

            return {
              ...data,
              customerContactsList: newDetails,
            };
          }

          return data;
        })
        .then(data => {
          this.setState({
            detail: data,
            backup: { ...data },
          });
        })
        .catch(() => {
          message.error('查询客户明细失败');
        });
    }
  };

  clear = () => {
    const { form } = this.props;
    form.resetFields();
    this.updateContacts([]);
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
    const { regTime } = detail;

    form.validateFields((err, fieldsValue) => {
      if (err) return;

      const customer = {
        rowId: detail.rowId,
        ...fieldsValue,
        regTime,
        customerContactsList: detail.customerContactsList,
      };

      handleEdit(customer);
    });
  };

  handleCancel = () => {
    const { handleModalVisible } = this.props;
    this.reset();
    handleModalVisible();
  };

  handleUpdateItem = record => {
    if (!record.rowKey) return;
    const {
      detail: { customerContactsList: contacts },
    } = this.state;
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
    const {
      detail: { customerContactsList: contacts },
    } = this.state;

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
    const {
      detail: { customerContactsList: contacts },
    } = this.state;
    contacts.splice(index, 1);
    const newDetails = [...contacts];
    this.updateContacts(newDetails);
  };

  changeRegTime = (date, dateString) => {
    const { detail } = this.state;
    this.setState({
      detail: {
        ...detail,
        regTime: dateString,
      },
    });
  };

  updateContacts = contacts => {
    const { detail } = this.state;
    const newDetail = {
      ...detail,
      customerContactsList: contacts,
    };
    this.setState({
      detail: newDetail,
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
    const { detail } = this.state;

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
                  initialValue: detail.customerName,
                })(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="简称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('shortName', {
                  initialValue: detail.shortName,
                })(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="英文简称" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('shortEnName', {
                  initialValue: detail.shortEnName,
                })(<Input placeholder="请输入" maxLength={40} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="地址" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('address', {
                  initialValue: detail.address,
                })(<Input placeholder="请输入" maxLength={100} />)}
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
                  initialValue: detail.paymentDays,
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
                  initialValue: detail.accountUnitId,
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
            <Col span={12}>
              <FormItem label="邮政编码" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('postalCode', {
                  initialValue: detail.postalCode,
                })(<Input placeholder="请输入" maxLength={6} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="传真" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('fax', {
                  initialValue: detail.fax,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="客户等级" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('customerLevelId', {
                  initialValue: detail.customerLevelId,
                })(
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
                {getFieldDecorator('dutyParagraph', {
                  initialValue: detail.dutyParagraph,
                })(<Input placeholder="请输入" maxLength={20} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="发票寄送地址" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('invoiceSendingAddress', {
                  initialValue: detail.invoiceSendingAddress,
                })(<Input placeholder="请选择" maxLength={100} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="发票寄送邮编" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('invoiceSendingPcode', {
                  initialValue: detail.invoiceSendingPcode,
                })(<Input placeholder="请输入" maxLength={6} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="收货地址" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('deliveryAddress', {
                  initialValue: detail.deliveryAddress,
                })(<Input placeholder="请选择" maxLength={100} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={12}>
              <FormItem label="银行账户" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('bankAccount', {
                  initialValue: detail.bankAccount,
                })(<Input placeholder="请输入" maxLength={30} />)}
              </FormItem>
            </Col>
            <Col span={12}>
              <FormItem label="成立时间" labelCol={labelCol} wrapperCol={wrapperCol}>
                {getFieldDecorator('regTime', {
                  initialValue: detail.regTime ? moment(detail.regTime, 'YYYY-MM-DD') : null,
                })(
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
                {getFieldDecorator('regCapital', {
                  initialValue: detail.regCapital,
                })(
                  <InputNumber
                    min={0}
                    placeholder="请输入"
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
                  initialValue: detail.paymentTypeId,
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
                {getFieldDecorator('webSite', {
                  initialValue: detail.webSite,
                })(<Input placeholder="请输入" maxLength={100} />)}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={24}>
              <FormItem label="客户信息" labelCol={{ span: 4 }} wrapperCol={{ span: 20 }}>
                {getFieldDecorator('customerInfo', {
                  initialValue: detail.customerInfo,
                })(
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
    const { detail } = this.state;

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
                  initialValue: detail.jaCurrencyId,
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
                      message: '请输入欠款额度',
                    },
                  ],
                  initialValue: detail.debtLimit,
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
                  initialValue: detail.zxbCurrencyId,
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
                  initialValue: detail.zxbArrears,
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
    const {
      detail: { customerContactsList: contacts },
    } = this.state;
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
        title="修改客户信息"
        width={1000}
        visible={modalVisible}
        onOk={this.handleOK}
        onCancel={this.handleCancel}
      >
        {this.renderBaseInfo()}
        {this.renderDebtLimitInfo()}
        {this.renderContactInfo()}
      </SimpleModal>
    );
  }
}
