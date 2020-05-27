import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { getById, edit } from '../../../services/systemCode';

class ModalEditForm extends PureComponent {
  state = {
    rowId: -1,
    typeId: -1,
    code: '',
    name: '',
    desc: '',
    orderNo: 0,
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  // componentWillReceiveProps(nextProps) {
  //   console.log('=================我是可爱的分割线==================')
  //   if (this.props.currentEditId !== nextProps.currentEditId && nextProps.currentEditId > 0) {
  //     getById(nextProps.currentEditId)
  //       .then(data => {
  //         this.setState({
  //           rowId: data.data.rowId,
  //           typeId: data.data.typeId,
  //           code: data.data.code,
  //           name: data.data.name,
  //           desc: data.data.desc,
  //           orderNo: data.data.orderNo,
  //         });
  //       });
  //   }
  // }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    const { rowId } = this.props.detailData;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.detailData.rowId;

      //业务逻辑写在这里
      edit(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            this.setState({
              typeId: fieldsValue.typeId,
              code: fieldsValue.code,
              name: fieldsValue.name,
              desc: fieldsValue.desc,
              orderNo: fieldsValue.orderNo,
            });
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, this.props.detailData.rowId);
            this.props.refreshTable();
          }
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator }, detailData
    } = this.props;
    return (
      <Modal
        title="编辑用户"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="类型">
            {getFieldDecorator('typeId', {
              initialValue: detailData.typeId,
              rules: [{ required: true, message: '请选择类型' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.codeTypeAllList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.name}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="编码">
            {getFieldDecorator('code', {
              initialValue: detailData.code,
              rules: [{ required: true, message: '请输入编码' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="名称">
            {getFieldDecorator('name', {
              initialValue: detailData.name,
              rules: [{ required: true, message: '请输入名称' }],
            })(<Input placeholder="请输入" maxLength={20} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="序号">
            {getFieldDecorator('orderNo', {
              initialValue: detailData.orderNo,
              rules: [{ required: true, message: '请输入序号' }],
            })(<InputNumber min={0} maxLength={5} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
            {getFieldDecorator('desc', {
              initialValue: detailData.desc,
            })(<Input.TextArea cols={4} placeholder="请输入" maxLength={50} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
