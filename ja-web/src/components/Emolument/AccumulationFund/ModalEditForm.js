import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { getById, editAccumulationFund } from '../../../services/eltAccumulationFund';

class ModalEditForm extends PureComponent {
  state = {
    baseId: '',
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentEditUserId !== nextProps.currentEditUserId &&
      nextProps.currentEditUserId > 0
    ) {
      getById(nextProps.currentEditUserId)
        .then(data => {
          this.setState({
            rowId: data.data.rowId,
            baseId: data.data.baseId,
            ruleName: data.data.ruleName,
            socialInsuranceBase: data.data.socialInsuranceBase,
            personalProportion: data.data.personalProportion,
            personalAmount: data.data.personalAmount,
            companyRatio: data.data.companyRatio,
            companyAmount: data.data.companyAmount,
            personalSupplementaryRatio: data.data.personalSupplementaryRatio,
            personalSupplementaryAmount: data.data.personalSupplementaryAmount,
            companySupplementaryProportion: data.data.companySupplementaryProportion,
            companySupplementaryAmount: data.data.companySupplementaryAmount,
            remarks: data.data.remarks,
          });
        })
        .catch(() => {
          message.error('查询公积金信息失败');
        });
    }
  }

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
      editAccumulationFund(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, -1);
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
        title="编辑公积金"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseId', {
              initialValue: this.state.baseId,
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
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规则名称">
            {getFieldDecorator('ruleName', {
              initialValue: this.state.ruleName,
              rules: [{ required: true, message: '规则名称为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="缴费基数">
            {getFieldDecorator('socialInsuranceBase', {
              initialValue: this.state.socialInsuranceBase,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人比例">
            {getFieldDecorator('personalProportion', {
              initialValue: this.state.personalProportion,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人金额">
            {getFieldDecorator('personalAmount', {
              initialValue: this.state.personalAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司比例">
            {getFieldDecorator('companyRatio', {
              initialValue: this.state.companyRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司金额">
            {getFieldDecorator('companyAmount', {
              initialValue: this.state.companyAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人补充比例">
            {getFieldDecorator('personalSupplementaryRatio', {
              initialValue: this.state.personalSupplementaryRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人补充金额">
            {getFieldDecorator('personalSupplementaryAmount', {
              initialValue: this.state.personalSupplementaryAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司补充比例">
            {getFieldDecorator('companySupplementaryProportion', {
              initialValue: this.state.companySupplementaryProportion,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司补充金额">
            {getFieldDecorator('companySupplementaryAmount', {
              initialValue: this.state.companySupplementaryAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remarks', {
              initialValue: this.state.remarks,
            })(<Input placeholder="请输入" maxLength="100" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
