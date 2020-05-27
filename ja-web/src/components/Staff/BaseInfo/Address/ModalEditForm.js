import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Row, Col, Modal, message } from 'antd';
import { addOrEditAddress, getAddress } from '../../../../services/staffBaseInfo';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
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
      getAddress(nextProps.currentEditStaffId).then(data => {
        if (!!data.data) {
          this.setState({
            rowId: data.data.rowId,
            workPhone: data.data.workPhone,
            mobile: data.data.mobile,
            homePhone: data.data.homePhone,
            emailWork: data.data.emailWork,
            homeAddress: data.data.homeAddress,
            emailPersonal: data.data.emailPersonal,
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
      addOrEditAddress(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          this.props.handleModalVisible(false, 2, 0);
          this.props.refreshTable(staffId);
        } else {
          message.error('操作失败：请稍后再试！');
        }
      });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
      modalVisibleEdit,
    } = this.props;

    return (
      <Modal
        title={'修改通讯信息'}
        visible={modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 2, 0)}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="办公电话">
                {getFieldDecorator('workPhone', {
                  initialValue: this.state.workPhone,
                })(<Input placeholder="请输入" maxLength={20} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="移动电话">
                {getFieldDecorator('mobile', {
                  rules: [{ required: true, message: '请输入移动电话' }],
                  initialValue: this.state.mobile,
                })(<Input placeholder="请输入" maxLength={11} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="家庭电话">
                {getFieldDecorator('homePhone', {
                  initialValue: this.state.homePhone,
                })(<Input placeholder="请输入" maxLength={20} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="电子邮件（工作）">
                {getFieldDecorator('emailWork', {
                  initialValue: this.state.emailWork,
                })(<Input placeholder="请输入" addonAfter="@jasolar.com" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="家庭地址">
                {getFieldDecorator('homeAddress', {
                  initialValue: this.state.homeAddress,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="电子邮件（个人）">
                {getFieldDecorator('emailPersonal', {
                  initialValue: this.state.emailPersonal,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
