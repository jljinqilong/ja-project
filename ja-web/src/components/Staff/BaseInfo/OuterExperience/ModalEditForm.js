import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Row, Col, Modal, message, DatePicker } from 'antd';
import { addOrEditOuterExperience, getOuterExperience } from '../../../../services/staffBaseInfo';
import moment from 'moment';

class ModalEditForm extends PureComponent {
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
    this.setState({ rowId: nextProps.currentEditStaffId });
    if (
      this.props.currentEditStaffId !== nextProps.currentEditStaffId &&
      nextProps.currentEditStaffId > 0
    ) {
      getOuterExperience(nextProps.currentEditStaffId).then(data => {
        if (!!data.data) {
          this.setState({
            rowId: data.data.rowId,
            workUnit: data.data.workUnit,
            deptName: data.data.deptName,
            positionName: data.data.positionName,
            startDate: data.data.startDate,
            endDate: data.data.endDate,
            proofPersonAndDuty: data.data.proofPersonAndDuty,
            proofContact: data.data.proofContact,
            salary: data.data.salary,
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
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      if (this.state.startDate > this.state.endDate) {
        message.warn('开始时间不能大于结束时间！');
        return;
      }
      //业务逻辑写在这里
      addOrEditOuterExperience(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('操作成功');
            form.resetFields();
            this.props.handleModalVisible(false, 2, -1);
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
      modalVisibleEdit,
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';

    return (
      <Modal
        title={'修改外部工作经历'}
        visible={modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 2, -1)}
      >
        <Form>
          <p>
            <Row gutter={24}>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工作单位">
                  {getFieldDecorator('workUnit', {
                    initialValue: this.state.workUnit,
                    rules: [{ required: true, message: '请输入工作单位' }],
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="任职部门">
                  {getFieldDecorator('deptName', {
                    initialValue: this.state.deptName,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="任职职衔">
                  {getFieldDecorator('positionName', {
                    initialValue: this.state.positionName,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="开始日期">
                  {getFieldDecorator('startDate', {
                    initialValue: !!this.state.startDate ? moment(this.state.startDate) : '',
                  })(
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
                  {getFieldDecorator('endDate', {
                    initialValue: !!this.state.endDate ? moment(this.state.endDate) : '',
                  })(
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
                    initialValue: this.state.proofPersonAndDuty,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证明人联系方式">
                  {getFieldDecorator('proofContact', {
                    initialValue: this.state.proofContact,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="薪酬状况">
                  {getFieldDecorator('salary', {
                    initialValue: this.state.salary,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
            </Row>
          </p>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
