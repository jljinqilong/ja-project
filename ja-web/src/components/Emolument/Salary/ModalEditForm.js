import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { getById, editSalary } from '../../../services/eltSalary';

class ModalEditForm extends PureComponent {
  state = {
    baseId: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  // componentWillReceiveProps(nextProps) {
  //   if (
  //     this.props.currentEditUserId !== nextProps.currentEditUserId &&
  //     nextProps.currentEditUserId > 0
  //   ) {
  //     getById(nextProps.currentEditUserId)
  //       .then(data => {
  //         this.setState({
  //           rowId: data.data.rowId,
  //           base: data.data.base,
  //           wageCategory: data.data.wageCategory,
  //           payTheAmount: data.data.payTheAmount,
  //           remarks: data.data.remarks,
  //         });
  //       })
  //       .catch(() => {
  //         message.error('查询固定工资信息失败');
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
      editSalary(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, this.props.currentEditUserId);
            this.props.refreshTable();
          } else {
            message.error('编辑失败：请稍后再试！');
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
    return (
      <Modal
        title="编辑补贴规则"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseId', {
              initialValue: this.props.detailData.baseId,
              rules: [{ required: true, message: '基地为必须' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.orgBaseList.map(d => (
                  <Select.Option key={d.rowId} value={d.rowId}>
                    {d.baseOrDeptName}
                  </Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工资类别">
            {getFieldDecorator('wageCategory', {
              initialValue: this.props.detailData.wageCategory,
              rules: [{ required: true, message: '工资类别为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工资金额">
            {getFieldDecorator('payTheAmount', {
              initialValue: this.props.detailData.payTheAmount,
              rules: [{ required: true, message: '工资金额为必须' }],
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remarks', {
              initialValue: this.props.detailData.remarks,
            })(<Input placeholder="请输入" maxLength="100" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
