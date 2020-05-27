import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber } from 'antd';
import { editMachine } from '../../../services/machine';

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
      editMachine(fieldsValue)
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
        title="编辑考勤机"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="考勤机名称">
            {getFieldDecorator('machineName', {
              initialValue: this.props.detailData.machineName,
              rules: [{ required: true, message: '考勤机名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseId', {
              initialValue: this.props.detailData.baseId,
              rules: [{ required: true, message: '基地为必须' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.orgBaseList.map(d => (
                  <Select.Option key={d.rowId}>{d.baseOrDeptName}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="厂别">
            {getFieldDecorator('factoryId', {
              initialValue: this.props.detailData.factoryId,
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.factoryIds.map(d => (
                  <Select.Option key={d.code}>{d.name}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="序列号">
            {getFieldDecorator('seq', {
              initialValue: this.props.detailData.seq,
            })(<Input placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最早提前签到(分钟)">
            {getFieldDecorator('earliestSignIn', {
              initialValue: this.props.detailData.earliestSignIn,
            })(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最晚推迟签到(分钟)">
            {getFieldDecorator('latestSignIn', {
              initialValue: this.props.detailData.latestSignIn,
            })(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最早提前签退(分钟)">
            {getFieldDecorator('earliestSignOff', {
              initialValue: this.props.detailData.earliestSignOff,
            })(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="最晚推迟签退(分钟)">
            {getFieldDecorator('latestSignOff', {
              initialValue: this.props.detailData.latestSignOff,
            })(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
