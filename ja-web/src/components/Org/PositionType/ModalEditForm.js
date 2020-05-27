import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message } from 'antd';
import { getById, editPositionType } from '../../../services/positionType';

class ModalEditForm extends PureComponent {
  state = {
    base: '',
  };

  // /**
  //  * 接收ID，查询详细
  //  * @param nextProps
  //  */
  // componentWillReceiveProps(nextProps) {
  //   if (
  //     this.props.currentEditUserId !== nextProps.currentEditUserId &&
  //     nextProps.currentEditUserId > 0
  //   ) {
  //     getById(nextProps.currentEditUserId)
  //       .then(data => {
  //         this.setState({
  //           rowId: data.data.rowId,
  //           typeName: data.data.typeName,
  //           typeDesc: data.data.typeDesc,
  //         });
  //       })
  //       .catch(() => {
  //         message.error('查询职衔类别失败');
  //       });
  //   }
  // }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      //业务逻辑写在这里
      editPositionType(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable();
          }
        })
        .catch(() => {
          message.error('编辑失败：请联系管理员!');
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const { rowId } = this.state;
    const { detailData } = this.state;
    return (
      <Modal
        title="编辑职衔类别"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        detailData={this.props.detailData}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔类别名称">
            {getFieldDecorator('typeName', {
              initialValue: this.props.detailData.typeName,
              rules: [{ required: true, message: '职衔类别名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔类别描述">
            {getFieldDecorator('typeDesc', {
              initialValue: this.props.detailData.typeDesc,
              rules: [{ required: true, message: '职衔类别描述为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
