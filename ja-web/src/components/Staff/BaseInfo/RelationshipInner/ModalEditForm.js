import React, { PureComponent, Fragment } from 'react';
import { Form, Input, Row, Col, Modal, message } from 'antd';
import {
  addOrEditRelationshipInner,
  getRelationshipInner,
} from '../../../../services/staffBaseInfo';

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
      getRelationshipInner(nextProps.currentEditStaffId).then(data => {
        if (!!data.data) {
          this.setState({
            rowId: data.data.rowId,
            relativeName: data.data.relativeName,
            relationship: data.data.relationship,
            company: data.data.company,
            dept: data.data.dept,
            contact: data.data.contact,
            relativeId: data.data.relativeId,
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
      addOrEditRelationshipInner(fieldsValue)
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

  render() {
    const {
      form: { getFieldDecorator },
      modalVisibleEdit,
    } = this.props;

    return (
      <Modal
        title="修改内部亲属关系"
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
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="亲属姓名">
                  {getFieldDecorator('relativeName', {
                    initialValue: this.state.relativeName,
                    rules: [{ required: true, message: '请输入亲属姓名' }],
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="与本人关系">
                  {getFieldDecorator('relationship', {
                    initialValue: this.state.relationship,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="所在单位">
                  {getFieldDecorator('company', {
                    initialValue: this.state.company,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="所在部门">
                  {getFieldDecorator('dept', {
                    initialValue: this.state.dept,
                  })(<Input placeholder="请输入" maxLength={50} />)}
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="联系方式">
                  {getFieldDecorator('contact', {
                    initialValue: this.state.contact,
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

export default Form.create({})(ModalEditForm);
