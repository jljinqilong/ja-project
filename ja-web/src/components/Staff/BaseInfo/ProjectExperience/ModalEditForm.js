import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Row, Col, Modal, message, DatePicker } from 'antd';
import { addOrProjectExperience, getOrProjectExperience } from '../../../../services/staffBaseInfo';
import moment from 'moment';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
    startDate: '',
    endDate: '',
  };

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
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    this.setState({ staffId: nextProps.staffId });
    this.setState({ rowId: nextProps.currentEditStaffId });
    if (
      this.props.currentEditStaffId !== nextProps.currentEditStaffId &&
      nextProps.currentEditStaffId > 0
    ) {
      getOrProjectExperience(nextProps.currentEditStaffId).then(data => {
        if (!!data.data) {
          this.setState({
            rowId: data.data.rowId,
            projectName: data.data.projectName,
            fillPost: data.data.fillPost,
            startDate: data.data.startDate,
            endDate: data.data.endDate,
            responsibleContent: data.data.responsibleContent,
            projectResult: data.data.projectResult,
          });
        }
      });
    }
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
      //业务逻辑写在这里
      fieldsValue.startDate = this.state.startDate,
      fieldsValue.endDate = this.state.endDate,
      addOrProjectExperience(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          this.props.handleModalVisible(false, 2, 0);
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
      modalVisibleEdit,
    } = this.props;

    return (
      <Modal
        title={'修改项目经历'}
        visible={modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 2, 0)}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="项目名称">
                {getFieldDecorator('projectName', {
                  initialValue:this.state.projectName,
                  rules: [{ required: true, message: '请输入项目名称' }],
                })(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="担任职务">
                {getFieldDecorator('fillPost', {
                  initialValue:this.state.fillPost,
                  rules: [{ required: true, message: '请输入担任职务' }],
                })(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="开始时间">
                {getFieldDecorator('startDate', {
                  initialValue:moment(this.state.startDate),
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
                  initialValue:moment(this.state.endDate),
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
                {getFieldDecorator('responsibleContent', {
                  initialValue:this.state.responsibleContent,
                })(
                  <Input placeholder="请输入" maxLength={100}/>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="项目结果">
                {getFieldDecorator('projectResult', {
                  initialValue:this.state.projectResult,
                })(
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

export default Form.create({})(ModalEditForm);
