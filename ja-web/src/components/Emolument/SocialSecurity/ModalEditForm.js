import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { getById, editSocialSecurity } from '../../../services/eltSocialSecurity';

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
  //           baseId: data.data.baseId,
  //           ruleName: data.data.ruleName,
  //           socialInsuranceBase: data.data.socialInsuranceBase,
  //           iindividualPersonRatio: data.data.iindividualPersonRatio,
  //           personalPensionAmount: data.data.personalPensionAmount,
  //           personalMedicalRatio: data.data.personalMedicalRatio,
  //           personalMedicalAmount: data.data.personalMedicalAmount,
  //           personalInjuryRatio: data.data.personalInjuryRatio,
  //           personalInjuryAmount: data.data.personalInjuryAmount,
  //           personalUnemploymentRatio: data.data.personalUnemploymentRatio,
  //           personalUnemploymentAmount: data.data.personalUnemploymentAmount,
  //
  //           personalFertilityRatio: data.data.personalFertilityRatio,
  //           personalFertilityAmount: data.data.personalFertilityAmount,
  //           companyPensionRatio: data.data.companyPensionRatio,
  //           companyPensionAmount: data.data.companyPensionAmount,
  //           companyMedicalRatio: data.data.companyMedicalRatio,
  //           companyMedicalAmount: data.data.companyMedicalAmount,
  //           companyInjuryRatio: data.data.companyInjuryRatio,
  //           companyInjuryAmount: data.data.companyInjuryAmount,
  //           companyUnemploymentRatio: data.data.companyUnemploymentRatio,
  //           companyUnemploymentAmount: data.data.companyUnemploymentAmount,
  //           companyBirthRatio: data.data.companyBirthRatio,
  //           companyBirthAmount: data.data.companyBirthAmount,
  //           remarks: data.data.remarks,
  //         });
  //       })
  //       .catch(() => {
  //         message.error('查询社保规则信息失败');
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
      editSocialSecurity(fieldsValue)
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
        title="编辑社保规则"
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
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规则名称">
            {getFieldDecorator('ruleName', {
              initialValue: this.props.detailData.ruleName,
              rules: [{ required: true, message: '规则名称为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="缴费基数">
            {getFieldDecorator('socialInsuranceBase', {
              initialValue: this.props.detailData.socialInsuranceBase,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人养老比例">
            {getFieldDecorator('iindividualPersonRatio', {
              initialValue: this.props.detailData.iindividualPersonRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人养老金额">
            {getFieldDecorator('personalPensionAmount', {
              initialValue: this.props.detailData.personalPensionAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人医疗比例">
            {getFieldDecorator('personalMedicalRatio', {
              initialValue: this.props.detailData.personalMedicalRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人医疗金额">
            {getFieldDecorator('personalMedicalAmount', {
              initialValue: this.props.detailData.personalMedicalAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人工伤比例">
            {getFieldDecorator('personalInjuryRatio', {
              initialValue: this.props.detailData.personalInjuryRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人工伤金额">
            {getFieldDecorator('personalInjuryAmount', {
              initialValue: this.props.detailData.personalInjuryAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人失业比例">
            {getFieldDecorator('personalUnemploymentRatio', {
              initialValue: this.props.detailData.personalUnemploymentRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人失业金额">
            {getFieldDecorator('personalUnemploymentAmount', {
              initialValue: this.props.detailData.personalUnemploymentAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人生育比例">
            {getFieldDecorator('personalFertilityRatio', {
              initialValue: this.props.detailData.personalFertilityRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人生育金额">
            {getFieldDecorator('personalFertilityAmount', {
              initialValue: this.props.detailData.personalFertilityAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司养老比例">
            {getFieldDecorator('companyPensionRatio', {
              initialValue: this.props.detailData.companyPensionRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司养老金额">
            {getFieldDecorator('companyPensionAmount', {
              initialValue: this.props.detailData.companyPensionAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司医疗比例">
            {getFieldDecorator('companyMedicalRatio', {
              initialValue: this.props.detailData.companyMedicalRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司医疗金额">
            {getFieldDecorator('companyMedicalAmount', {
              initialValue: this.props.detailData.companyMedicalAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司工伤比例">
            {getFieldDecorator('companyInjuryRatio', {
              initialValue: this.props.detailData.companyInjuryRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司工伤金额">
            {getFieldDecorator('companyInjuryAmount', {
              initialValue: this.props.detailData.companyInjuryAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司失业比例">
            {getFieldDecorator('companyUnemploymentRatio', {
              initialValue: this.props.detailData.companyUnemploymentRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司失业金额">
            {getFieldDecorator('companyUnemploymentAmount', {
              initialValue: this.props.detailData.companyUnemploymentAmount,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司生育比例">
            {getFieldDecorator('companyBirthRatio', {
              initialValue: this.props.detailData.companyBirthRatio,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司生育金额">
            {getFieldDecorator('companyBirthAmount', {
              initialValue: this.props.detailData.companyBirthAmount,
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
