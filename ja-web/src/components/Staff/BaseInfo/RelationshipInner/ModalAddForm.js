import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Row, Col, Modal, message, Button, Icon } from 'antd';
import { addOrEditRelationshipInner } from '../../../../services/staffBaseInfo';

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
      addOrEditRelationshipInner(fieldsValue).then(data => {
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
      addOrEditRelationshipInner(fieldsValue).then(data => {
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
        title="添加内部亲属关系"
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
          <p>
            <Row gutter={24}>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="亲属姓名">
                  {getFieldDecorator('relativeName', {
                    rules: [{ required: true, message: '请输入亲属姓名' }],
                  })(<Input placeholder="请输入" maxLength={50} />)}
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
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="所在单位">
                  {getFieldDecorator('company', {})(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="所在部门">
                  {getFieldDecorator('dept', {})(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="联系方式">
                  {getFieldDecorator('contact', {
                    rules: [{ required: true, message: '请输入联系方式' }],
                  })(<Input placeholder="请输入" maxLength={30} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="亲属工号">
                  {getFieldDecorator('relativeId', {})(<Input placeholder="请输入" maxLength={30} />)}
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
