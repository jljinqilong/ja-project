import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { addSocialSecurity } from '../../../services/eltSocialSecurity';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addSocialSecurity(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('公积金已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加社保规则"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseId', {
              rules: [{ required: true, message: '基地为必须' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.orgBaseList.map(d => (
                  <Select.Option key={d.rowId}>{d.baseOrDeptName}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规则名称">
            {getFieldDecorator('ruleName', {
              rules: [{ required: true, message: '规则名称为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="缴费基数">
            {getFieldDecorator('socialInsuranceBase')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人养老比例">
            {getFieldDecorator('iindividualPersonRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人养老金额">
            {getFieldDecorator('personalPensionAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人医疗比例">
            {getFieldDecorator('personalMedicalRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人医疗金额">
            {getFieldDecorator('personalMedicalAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人工伤比例">
            {getFieldDecorator('personalInjuryRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人工伤金额">
            {getFieldDecorator('personalInjuryAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人失业比例">
            {getFieldDecorator('personalUnemploymentRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人失业金额">
            {getFieldDecorator('personalUnemploymentAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人生育比例">
            {getFieldDecorator('personalFertilityRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="个人生育金额">
            {getFieldDecorator('personalFertilityAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司养老比例">
            {getFieldDecorator('companyPensionRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司养老金额">
            {getFieldDecorator('companyPensionAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司医疗比例">
            {getFieldDecorator('companyMedicalRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司医疗金额">
            {getFieldDecorator('companyMedicalAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司工伤比例">
            {getFieldDecorator('companyInjuryRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司工伤金额">
            {getFieldDecorator('companyInjuryAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司失业比例">
            {getFieldDecorator('companyUnemploymentRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司失业金额">
            {getFieldDecorator('companyUnemploymentAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司生育比例">
            {getFieldDecorator('companyBirthRatio')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="公司生育金额">
            {getFieldDecorator('companyBirthAmount')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remarks')(<Input placeholder="请输入" maxLength="100" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
