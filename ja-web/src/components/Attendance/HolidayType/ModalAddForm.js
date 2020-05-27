import React, { Fragement, PureComponent } from 'react';
import { Form, Input, message, Modal, Select, Radio, InputNumber } from 'antd';
import { addHolidayType } from '../../../services/holidayType';

const RadioGroup = Radio.Group;

class ModalAddForm extends PureComponent {
  state = {
    value: 0,
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      addHolidayType(fieldsValue)
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

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加假期类型"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="假期类型">
            {getFieldDecorator('typeName', {
              rules: [{ required: true, message: '假期类型为必须' }],
            })(<Input placeholder="请输入" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="是否启用">
            {getFieldDecorator('status', {
              initialValue: this.state.value,
              rules: [{ required: true, message: '是否启用为必须' }],
            })(
              <RadioGroup onChange={this.onChange}>
                <Radio value={0}>是</Radio>
                <Radio value={1}>否</Radio>
              </RadioGroup>
            )}
          </Form.Item>

          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="请假时间单位">
            {getFieldDecorator('unit')(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.timeTypes.map(d => (
                  <Select.Option key={d.code}>{d.name}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="最小请假时间">
            {getFieldDecorator('minLeaveTime')(
              <InputNumber min={0} precision={2} placeholder="请输入" />
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
