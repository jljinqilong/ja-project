import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, InputNumber, DatePicker } from 'antd';
import moment from 'moment';
import { editOvertimeSheet } from '../../../services/overtimeSheet';

const RangePicker = DatePicker.RangePicker;

class ModalEditForm extends PureComponent {
  state = {
    baseId: '',
    overtimeTime: this.props.detailData.overtimeTime,
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
      console.log('this.state.startDate===>>' + this.state.startDate);
      fieldsValue.startDate = this.state.startDate;
      fieldsValue.endDate = this.state.endDate;
      // fieldsValue.reason = this.state.reason;
      //业务逻辑写在这里
      editOvertimeSheet(fieldsValue)
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

  selectTime = (dates, dateStrings) => {
    console.log('1111--' + dates[0] + '---222--' + dates[1]);
    /*开始时间，结束时间*/
    const hours = this.calculateTime(dates[0], dates[1]);
    this.setState({
      startDate: dateStrings[0],
      endDate: dateStrings[1],
      overtimeTime: hours,
    });
  };

  /*计算两个时间的时间差*/
  calculateTime = (faultDate, completeTime) => {
    const usedTime = completeTime - faultDate; //两个时间戳相差的毫秒数
    //计算出小时数
    const hours = Math.floor((usedTime / (3600 * 1000)) * 100) / 100;
    console.log('两个时间相差 ==>> ' + hours + '时');
    return hours;
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="编辑加班单"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
            {getFieldDecorator('baseName', {
              initialValue: this.props.detailData.baseName,
              rules: [{ required: true, message: '基地为必须' }],
            })(<Input placeholder="请输入" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
            {getFieldDecorator('deptName', {
              initialValue: this.props.detailData.deptName,
            })(<Input placeholder="请输入" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 13 }} label="班次">
            {getFieldDecorator('jobNoName', {
              initialValue: this.props.detailData.jobNoName,
            })(<Input placeholder="请输入" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号">
            {getFieldDecorator('staffNo', {
              initialValue: this.props.detailData.staffNo,
            })(<Input placeholder="请输入" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="姓名">
            {getFieldDecorator('staffName', {
              initialValue: this.props.detailData.staffName,
            })(<Input placeholder="请输入" disabled />)}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="加班时间段">
            {getFieldDecorator('startDate', {
              initialValue: [
                moment(this.props.detailData.startDate),
                moment(this.props.detailData.endDate),
              ],
              rules: [{ required: true, message: '时间段为必须' }],
            })(
              <RangePicker
                ranges={{
                  Today: [moment(), moment()],
                  'This Month': [moment(), moment().endOf('month')],
                }}
                showTime
                format="YYYY/MM/DD HH:mm"
                onChange={this.selectTime}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="加班时数">
            {getFieldDecorator('overtimeTime', {
              initialValue: this.state.overtimeTime,
            })(<Input placeholder="请输入" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="加班原因">
            {getFieldDecorator('reason', {
              initialValue: this.props.detailData.reason,
            })(<Input placeholder="请输入" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
