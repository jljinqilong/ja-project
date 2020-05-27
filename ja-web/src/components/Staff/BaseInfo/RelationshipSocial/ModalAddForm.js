import React, { PureComponent } from 'react';
import { Form, Input, Row, Col, Modal, message, DatePicker, Select, Button, Icon } from 'antd';
import { addOrEditRelationshipSocial } from '../../../../services/staffBaseInfo';
import { getByTypeCode } from '../../../../services/systemCode';

class ModalAddForm extends PureComponent {
  state = {
    staffId: '',
    rsBirthday: '',
    maleOrFemaleList: [],
  };

  componentDidMount() {
    //男女
    getByTypeCode({ typeCode: 'MALE_OR_FEMALE' }).then(content => {
      this.state.maleOrFemaleList = content.data;
    });
  }

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    this.setState({ staffId: nextProps.staffId });
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    const { staffId } = this.state;
    const { rowId } = this.state;
    form.getFieldsValue();
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if (rowId > 0) {
        fieldsValue.rowId = rowId;
      }
      fieldsValue.staffId = staffId;
      fieldsValue.rsBirthday = this.state.rsBirthday;
      //业务逻辑写在这里
      addOrEditRelationshipSocial(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('操作成功');
            form.resetFields();
            this.setState({ rsBirthday: '' });
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable(staffId);
          } else {
            message.error('操作失败：请稍后再试！');
          }
        })
        .catch(() => {
          message.error('操作失败：请联系管理员!');
        });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    const { staffId } = this.state;
    const { rowId } = this.state;
    form.getFieldsValue();
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if (rowId > 0) {
        fieldsValue.rowId = rowId;
      }
      fieldsValue.staffId = staffId;
      fieldsValue.rsBirthday = this.state.rsBirthday;
      //业务逻辑写在这里
      addOrEditRelationshipSocial(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('操作成功');
            form.resetFields();
            this.setState({ rsBirthday: '' });
            this.props.refreshTable(staffId);
          } else {
            message.error('操作失败：请稍后再试！');
          }
        })
        .catch(() => {
          message.error('操作失败：请联系管理员!');
        });
    });
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeRsBirthday = (date, dateString) => {
    this.setState({
      rsBirthday: dateString,
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
      modalVisibleAdd,
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';

    return (
      <Modal
        title={'添加社会关系'}
        visible={modalVisibleAdd}
        onOk={this.handleSure}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={() => this.props.handleModalVisible(false, 1, -1)}
          >
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={this.handleSure}>
            保存
          </Button>,
          <Button type="primary" onClick={this.handleSureNext}>
            添加下一条<Icon type="right" />
          </Button>,
        ]}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="亲属姓名">
                {getFieldDecorator('relativeName', {
                  rules: [{ required: true, message: '请输入亲属姓名' }],
                })(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="性别">
                {getFieldDecorator('sex', {})(
                  <Select style={{ width: 295 }}>
                    {this.state.maleOrFemaleList.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="出生日期">
                {getFieldDecorator('rsBirthday', {})(
                  <DatePicker
                    onChange={this.handleDatePickerChangeRsBirthday}
                    format={dateFormat}
                    placeholder="请输入"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="与本人关系">
                {getFieldDecorator('relationship', {})(
                  <Input placeholder="请输入" maxLength={50} />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证件号码">
                {getFieldDecorator('identityNo', {})(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="常住地址">
                {getFieldDecorator('permanentAddress', {})(
                  <Input placeholder="请输入" maxLength={50} />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="所在单位">
                {getFieldDecorator('company', {})(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="亲属职衔">
                {getFieldDecorator('position', {})(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="联系方式">
                {getFieldDecorator('contact', {
                  rules: [{ required: true, message: '请输入联系方式' }],
                })(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
