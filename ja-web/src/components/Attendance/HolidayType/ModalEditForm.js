import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, Select, message, Radio, InputNumber } from 'antd';
import { editHolidayType } from '../../../services/holidayType';

const RadioGroup = Radio.Group;

class ModalEditForm extends PureComponent {
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
  //           ruleName: data.data.ruleName,
  //           socialInsuranceBase: data.data.socialInsuranceBase,
  //           personalProportion: data.data.personalProportion,
  //           personalAmount: data.data.personalAmount,
  //           companyRatio: data.data.companyRatio,
  //           companyAmount: data.data.companyAmount,
  //           personalSupplementaryRatio: data.data.personalSupplementaryRatio,
  //           personalSupplementaryAmount: data.data.personalSupplementaryAmount,
  //           companySupplementaryProportion: data.data.companySupplementaryProportion,
  //           companySupplementaryAmount: data.data.companySupplementaryAmount,
  //           remarks: data.data.remarks,
  //         });
  //       })
  //       .catch(() => {
  //         message.error('查询公积金信息失败');
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
      editHolidayType(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('编辑成功');
          this.props.handleModalVisible(false, 1, this.props.currentEditUserId);
          this.props.refreshTable();
        } else {
          message.error('编辑失败：请稍后再试！');
        }
      });
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="编辑假期类型"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="请假类型名称">
            {getFieldDecorator('typeName', {
              initialValue: this.props.detailData.typeName,
              rules: [{ required: true, message: '请假类型为必须' }],
            })(<Input placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="是否启用">
            {getFieldDecorator('status', {
              initialValue: this.props.detailData.status,
              rules: [{ required: true, message: '基地为必须' }],
            })(
              <RadioGroup onChange={this.onChange}>
                <Radio value={0}>是</Radio>
                <Radio value={1}>否</Radio>
              </RadioGroup>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="请假时间单位">
            {getFieldDecorator('unit', {
              initialValue: this.props.detailData.unit,
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.timeTypes.map(d => (
                  <Select.Option key={d.code}>{d.name}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最小请假时间">
            {getFieldDecorator('minLeaveTime', {
              initialValue: this.props.detailData.minLeaveTime,
            })(<InputNumber min={0} precision={2} placeholder="请输入" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
