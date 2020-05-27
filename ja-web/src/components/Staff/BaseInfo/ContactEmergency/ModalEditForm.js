import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Row, Col, Modal, message } from 'antd';
import { addOrEditContactEmergency, getContactEmergency } from '../../../../services/staffBaseInfo';

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
      getContactEmergency(nextProps.currentEditStaffId)
        .then(data => {
          if (!!data.data) {
            this.setState({
              rowId: data.data.rowId,
              contactName: data.data.contactName,
              relationship: data.data.relationship,
              mobile: data.data.mobile,
              address: data.data.address,
              wechatQq:data.data.wechatQq,
            });
          }
        })
        .catch(() => {
          message.error('查询失败');
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
      addOrEditContactEmergency(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
          this.props.handleModalVisible(false, 2, -1);
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
        title={'修改紧急联系人'}
        visible={modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 2, -1)}
      >
        <Form>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="紧急联系人姓名">
                {getFieldDecorator('contactName', {
                  rules: [{ required: true, message: '请输入紧急联系人姓名' }],
                  initialValue: this.state.contactName,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="与本人关系">
                {getFieldDecorator('relationship', {
                  initialValue: this.state.relationship,
                })(<Input placeholder="请输入" maxLength={30} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="电话号码">
                {getFieldDecorator('mobile', {
                  rules: [{ required: true, message: '请输入电话号码' }],
                  initialValue: this.state.mobile,
                })(<Input placeholder="请输入" maxLength={11} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="地址">
                {getFieldDecorator('address', {
                  initialValue: this.state.address,
                })(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="微信/QQ">
                {getFieldDecorator('wechatQq', {
                  initialValue:this.state.wechatQq,
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
