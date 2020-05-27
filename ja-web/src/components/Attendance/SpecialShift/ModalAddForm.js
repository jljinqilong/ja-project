import React, { PureComponent } from 'react';
import { Form, Input, Modal, message ,Select,InputNumber,TimePicker } from 'antd';
import { addJobNumber } from '../../../services/jobNumber';
import moment from 'moment';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.isSpecial = 0; /*设置为特殊班次*/
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

      addJobNumber(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
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
        title="添加特殊班次"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
            {getFieldDecorator('jobNoName', {
              rules: [{ required: true, message: '班次为必须' }],
            })(<Input placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="需要工作时间(分钟)">
            {getFieldDecorator('effectivePunching', {
              rules: [{ required: true, message: '需要工作时间(分钟)为必须' }],
            })(<InputNumber min={0} maxLength="12" precision="int" />
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最早上班时间">
            {getFieldDecorator('earliestTime', {
              rules: [{ required: true, message: '最早上班时间为必须' }],
            })(<TimePicker onChange={this.onChange} format={format} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最晚上班时间">
            {getFieldDecorator('latestTime', {
              rules: [{ required: true, message: '最晚上班时间为必须' }],
            })(<TimePicker onChange={this.onChange2} format={format} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
