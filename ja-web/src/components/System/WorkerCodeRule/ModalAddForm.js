import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, Select, Radio, InputNumber } from 'antd';
import { addWorkerCodeRule } from '../../../services/systemWorkerCodeRule';
import { getByTypeCode } from '../../../services/systemCode';
const RadioGroup = Radio.Group;
const Option = Select.Option;
class ModalAddForm extends PureComponent {
  state = {
    yesOrNoList: [],
    value: 0,
  };

  onChange = e => {
    console.log('radio checked', e.target.value);
    this.setState({
      value: e.target.value,
    });
  };
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if(fieldsValue.workerCodePrefix.length >= fieldsValue.workerCodeLen){
        message.error('工号前缀长度不能>=工号长度!');
        return;
      }
      form.resetFields();

      addWorkerCodeRule(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('添加失败');
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
        title="添加工号编码规则"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地名称">
            {getFieldDecorator('baseId', {
              rules: [{ required: true, message: '必填' }],
            })(
              <Select placeholder="请选择" style={{ width: '100%' }}>
                {this.props.orgBaseList.map(d => <Option key={d.rowId}>{d.baseOrDeptName}</Option>)}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号前缀">
            {getFieldDecorator('workerCodePrefix', {
              rules: [{ required: true, message: '必填' }],
            })(<Input placeholder="请输入" maxLength={8} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号长度">
            {getFieldDecorator('workerCodeLen', {
              rules: [{ required: true, message: '必填' }],
            })(<InputNumber min={1} max={20} placeholder="请输入" maxLength={2} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="是否可用">
            {getFieldDecorator('usable', {
              initialValue: this.state.value,
              rules: [{ required: true, message: '必填' }],
            })(
              <RadioGroup onChange={this.onChange}>
                <Radio value={0}>是</Radio>
                <Radio value={1}>否</Radio>
              </RadioGroup>
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
