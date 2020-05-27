import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message ,Select,InputNumber,TimePicker} from 'antd';
import {editJobNumber } from '../../../services/jobNumber';
import moment from 'moment';

class ModalEditForm extends PureComponent {
  state = {
    earliestTime: this.props.detailData.earliestTime,
    latestTime:this.props.detailData.latestTime,
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
      fieldsValue.earliestTime = this.state.earliestTime;
      fieldsValue.latestTime= this.state.latestTime;
      const format = 'HH:mm';
      const earliestTime =  moment(fieldsValue.earliestTime, format);
      const latestTime =  moment(fieldsValue.latestTime, format);
      console.log('++++'+earliestTime + '------' + latestTime);
      if(earliestTime > latestTime){
        message.error('最早上班时间不能大于最晚上班时间!');
        return;
      }
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

  onChange = (time,dateString) => {
    console.log('============>>>>>>>'+dateString)
    this.setState({
      earliestTime: dateString,
    });
  }

  onChange2 = (time,dateString) => {
    console.log('============>>>>>>>'+dateString)
    this.setState({
      latestTime: dateString,
    });
  }



  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const format = 'HH:mm';
    return (
      <Modal
        title="编辑特殊班次"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
            {getFieldDecorator('jobNoName', {
              initialValue: this.props.detailData.jobNoName,
              rules: [{ required: true, message: '班次为必须' }],
            })(<Input placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="需要工作时间(分钟)">
            {getFieldDecorator('effectivePunching', {
              initialValue: this.props.detailData.effectivePunching,
              rules: [{ required: true, message: '需要工作时间(分钟)为必须' }],
            })(
              <InputNumber min={0} maxLength="12" precision="int" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最早上班时间">
            {getFieldDecorator('earliestTime', {
              initialValue: moment(this.props.detailData.earliestTime, format),
              rules: [{ required: true, message: '最早上班时间为必须' }],
            })(
              <TimePicker onChange={this.onChange} format={format} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最晚上班时间">
            {getFieldDecorator('latestTime', {
              initialValue: moment(this.props.detailData.latestTime, format),
              rules: [{ required: true, message: '最晚上班时间为必须' }],
            })(<TimePicker onChange={this.onChange2} format={format} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
