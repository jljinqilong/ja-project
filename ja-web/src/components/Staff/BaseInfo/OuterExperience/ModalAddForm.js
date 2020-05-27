import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Row, Col, Modal, message, DatePicker, Button, Icon } from 'antd';
import { addOrEditOuterExperience } from '../../../../services/staffBaseInfo';

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
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      if (this.state.startDate > this.state.endDate) {
        message.warn('开始时间不能大于结束时间！');
        return;
      }
      //业务逻辑写在这里
      addOrEditOuterExperience(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          //清空日期组件
          this.setState({ startDate: '' });
          this.setState({ endDate: '' });
          this.props.handleModalVisible(false, 1, -1);
          this.props.refreshTable(staffId);
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
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      if (this.state.startDate > this.state.endDate) {
        message.warn('开始时间不能大于结束时间！');
        return;
      }
      //业务逻辑写在这里
      addOrEditOuterExperience(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          //清空日期组件
          this.setState({ startDate: '' });
          this.setState({ endDate: '' });
          this.props.refreshTable(staffId);
        } else {
          message.error('操作失败：请稍后再试！');
        }
      });
    });
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      startDate: dateString,
    });
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      endDate: dateString,
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
        title={'添加外部工作经历'}
        visible={modalVisibleAdd}
        width={1200}
        onOk={this.handleSure}
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
          <p>
            <Row gutter={24}>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工作单位">
                  {getFieldDecorator('workUnit', {
                    rules: [{ required: true, message: '请输入工作单位' }],
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="任职部门">
                  {getFieldDecorator('deptName', {})(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="任职职衔">
                  {getFieldDecorator('positionName', {})(
                    <Input placeholder="请输入" maxLength={50} />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="开始日期">
                  {getFieldDecorator('startDate')(
                    <DatePicker
                      onChange={this.handleDatePickerChangeStartDate}
                      format={dateFormat}
                      placeholder="请输入"
                    />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="结束日期">
                  {getFieldDecorator('endDate')(
                    <DatePicker
                      onChange={this.handleDatePickerChangeEndDate}
                      format={dateFormat}
                      placeholder="请输入"
                    />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证明人及职务">
                  {getFieldDecorator('proofPersonAndDuty', {
                    rules: [{ required: true, message: '请输入证明人及职务' }],
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证明人联系方式">
                  {getFieldDecorator('proofContact', {})(
                    <Input placeholder="请输入" maxLength={50} />
                  )}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="薪酬状况">
                  {getFieldDecorator('salary', {})(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
            </Row>
          </p>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
