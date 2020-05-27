import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, InputNumber, TimePicker } from 'antd';
import { editJobNumber } from '../../../services/jobNumber';
import moment from 'moment';
class ModalEditForm extends PureComponent {
  state = {
    onWorkTime: this.props.detailData.onWorkTime,
    offWorkTime: this.props.detailData.offWorkTime,
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
      fieldsValue.onWorkTime = this.state.onWorkTime;
      fieldsValue.offWorkTime = this.state.offWorkTime;
      const format = 'HH:mm';
      const onWorkTime = moment(fieldsValue.onWorkTime, format);
      const offWorkTime = moment(fieldsValue.offWorkTime, format);
      // console.log('++++'+onWorkTime + '------' + offWorkTime);
      // console.log('fieldsValue.onWorkTime'+ fieldsValue.onWorkTime);
      // console.log('this.state.onWorkTime'+ moment(this.props.detailData.offWorkTime,format));

      // if (onWorkTime > offWorkTime) {
      //   message.error('下班时间不能小于上班时间!');
      //   return;
      // }
      //业务逻辑写在这里
      editJobNumber(fieldsValue)
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

  onChange = (time, dateString) => {
    console.log('============>>>>>>>' + dateString);
    this.setState({
      onWorkTime: dateString,
    });
  };

  onChange2 = (time, dateString) => {
    console.log('============>>>>>>>' + dateString);
    this.setState({
      offWorkTime: dateString,
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const format = 'HH:mm';
    return (
      <Modal
        title="编辑排班"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
            {getFieldDecorator('jobNoName', {
              initialValue: this.props.detailData.jobNoName,
              rules: [{ required: true, message: '班次为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排班类型">
            {getFieldDecorator('typeId', {
              initialValue: this.props.detailData.typeId,
              rules: [{ required: true, message: '排班类型为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="上班时间设置">
            {getFieldDecorator('onWorkTime', {
              initialValue: moment(this.props.detailData.onWorkTime, format),
              rules: [{ required: true, message: '上班时间设置为必须' }],
            })(<TimePicker onChange={this.onChange} format={format} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="下班时间设置">
            {getFieldDecorator('offWorkTime', {
              initialValue: moment(this.props.detailData.offWorkTime, format),
              rules: [{ required: true, message: '下班时间设置为必须' }],
            })(<TimePicker onChange={this.onChange2} format={format} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="午休时间(分钟)">
            {getFieldDecorator('noonBreak', {
              initialValue: this.props.detailData.noonBreak,
            })(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="有效打卡时间(小时)">
            {getFieldDecorator('effectivePunching', {
              initialValue: this.props.detailData.effectivePunching,
            })(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
