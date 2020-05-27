import React, { PureComponent } from 'react';
import { Form, Input, Modal, message ,Select,InputNumber,TimePicker  } from 'antd';
import { addJobNumber } from '../../../services/jobNumber';
import moment from 'moment';

class ModalAddForm extends PureComponent {

  state = {
    onWorkTime: '',
    offWorkTime:'',
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.isSpecial = 1;
      fieldsValue.onWorkTime = this.state.onWorkTime;
      fieldsValue.offWorkTime= this.state.offWorkTime;
      const format = 'HH:mm';
      const onWorkTime =  moment(fieldsValue.onWorkTime, format);
      const offWorkTime =  moment(fieldsValue.offWorkTime, format);
      console.log('++++'+onWorkTime + '------' + offWorkTime);
      // if(onWorkTime > offWorkTime){
      //       //   message.error('下班时间不能小于上班时间!');
      //       //   return;
      //       // }
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
      onWorkTime: dateString,
    });
  }

  onChange2 = (time,dateString) => {
    console.log('============>>>>>>>'+dateString);
    console.log('============>>>>>>>'+time);
    this.setState({
      offWorkTime: dateString,
    });
  }

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const format = 'HH:mm';
    return (
      <Modal
        title="添加排班"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="班次">
            {getFieldDecorator('jobNoName', {
              rules: [{ required: true, message: '班次为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="排班类型">
            {getFieldDecorator('typeId', {
              rules: [{ required: true, message: '排班类型为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="上班时间设置">
            {getFieldDecorator('onWorkTime', {
              rules: [{ required: true, message: '上班时间设置为必须' }],
            })(<TimePicker onChange={this.onChange} format={format} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="下班时间设置">
            {getFieldDecorator('offWorkTime', {
              rules: [{ required: true, message: '下班时间设置为必须' }],
            })(<TimePicker onChange={this.onChange2} format={format}  />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="午休时间(分钟)">
            {getFieldDecorator('noonBreak')(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 13 }} label="有效打卡时间（小时）">
            {getFieldDecorator('effectivePunching')(<InputNumber min={0} maxLength="12" precision="int" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
