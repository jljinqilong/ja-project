import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Row, Col, Modal, message, Button, Icon, DatePicker } from 'antd';
import { addOrProjectExperience } from '../../../../services/staffBaseInfo';

class ModalAddForm extends PureComponent {
  state = {
    staffId: '',
    startDate: '',
    endDate: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    this.setState({ staffId: nextProps.staffId });
  }

  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      startDate: dateString,
    });
    this.onChange('startValue', date);
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      endDate: dateString,
    });
    this.onChange('endValue', date);
  };

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
      //业务逻辑写在这里
      fieldsValue.startDate = this.state.startDate,
      fieldsValue.endDate = this.state.endDate,
        addOrProjectExperience(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          this.props.handleModalVisible(false, 1, 0);
          this.props.refreshTable(staffId);
          this.setState({
            startValue: '',
            endValue: '',
          });
        } else {
          message.error('操作失败：请稍后再试！');
        }
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
      //业务逻辑写在这里
      fieldsValue.startDate = this.state.startDate,
      fieldsValue.endDate = this.state.endDate,
      addOrProjectExperience(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          this.props.refreshTable(staffId);
          this.setState({
            startValue: '',
            endValue: '',
          });
        } else {
          message.error('操作失败：请稍后再试！');
        }
      });
    });
  };

  disabledStartDate = startValue => {
    const endValue = this.state.endValue;
    if (!startValue || !endValue) {
      return false;
    }
    return startValue.valueOf() > endValue.valueOf();
  };

  disabledEndDate = endValue => {
    const startValue = this.state.startValue;
    if (!endValue || !startValue) {
      return false;
    }
    return endValue.valueOf() <= startValue.valueOf();
  };

  onChange = (field, value) => {
    this.setState({
      [field]: value,
    });
  };

  render() {
    const { startValue, endValue } = this.state;
    const {
      form: { getFieldDecorator },
      modalVisibleAdd,
    } = this.props;

    return (
      <Modal
        title={'添加项目经历'}
        visible={modalVisibleAdd}
        onOk={this.handleSure}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, 0)}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={() => this.props.handleModalVisible(false, 1, 0)}
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="项目名称">
                {getFieldDecorator('projectName', {
                  rules: [{ required: true, message: '请输入项目名称' }],
                })(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="担任职务">
                {getFieldDecorator('fillPost', {
                  rules: [{ required: true, message: '请输入担任职务' }],
                })(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="开始时间">
                {getFieldDecorator('startDate', {
                  rules: [{ required: true, message: '请输入开始时间' }],
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangeStartDate}
                    format="YYYY-MM-DD"
                    setFieldsValue={startValue}
                    disabledDate={this.disabledStartDate}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="结束时间">
                {getFieldDecorator('endDate', {
                  rules: [{ required: true, message: '请输入结束时间' }],
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangeEndDate}
                    format="YYYY-MM-DD"
                    setFieldsValue={endValue}
                    disabledDate={this.disabledEndDate}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="负责内容">
                {getFieldDecorator('responsibleContent', {})(
                  <Input placeholder="请输入" maxLength={100}/>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="项目结果">
                {getFieldDecorator('projectResult', {})(
                  <Input placeholder="请输入" maxLength={100} />
                )}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
