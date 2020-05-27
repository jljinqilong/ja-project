import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Row, Col, Modal, message, Button, Icon } from 'antd';
import { addOrEditContactEmergency, getContactEmergency } from '../../../../services/staffBaseInfo';

class ModalAddForm extends PureComponent {
  state = {
    staffId: '',
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
      //业务逻辑写在这里
      addOrEditContactEmergency(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
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
      //业务逻辑写在这里
      addOrEditContactEmergency(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('操作成功');
          form.resetFields();
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
      modalVisibleAdd,
    } = this.props;

    return (
      <Modal
        title={'添加紧急联系人'}
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="紧急联系人姓名">
                {getFieldDecorator('contactName', {
                  rules: [{ required: true, message: '请输入紧急联系人姓名' }],
                })(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="与本人关系">
                {getFieldDecorator('relationship', {})(
                  <Input placeholder="请输入" maxLength={30} />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="电话号码">
                {getFieldDecorator('mobile', {
                  rules: [{ required: true, message: '请输入电话号码' }],
                })(<Input placeholder="请输入" maxLength={11} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="地址">
                {getFieldDecorator('address', {})(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="微信/QQ">
                {getFieldDecorator('wechatQq', {})(<Input placeholder="请输入" maxLength={50} />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
