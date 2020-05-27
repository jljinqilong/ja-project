import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Select, Radio, InputNumber } from 'antd';
import { getById, editWorkerCodeRule } from '../../../services/systemWorkerCodeRule';
import { baseList } from '../../../services/org';
const RadioGroup = Radio.Group;

class ModalEditForm extends PureComponent {
  state = {
    name: '',
    desc: '',
    yesOrNoList: [],
    orgBaseList: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    //获取所有基地
    baseList()
      .then(content => {
        if (!!content && !!content.data) {
          this.setState({ orgBaseList: content.data });
      }
    });
  }

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  // componentWillReceiveProps(nextProps) {
  //   if (this.props.currentEditRoleId !== nextProps.currentEditRoleId && nextProps.currentEditRoleId > 0) {
  //     getById(nextProps.currentEditRoleId)
  //       .then(data => {
  //         this.setState({
  //           baseId: data.data.baseId,
  //           workerCodePrefix: data.data.workerCodePrefix,
  //           workerCodeLen: data.data.workerCodeLen,
  //           usable: data.data.usable,
  //       });
  //     });
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
      fieldsValue.rowId = this.props.currentEditRoleId;
      //业务逻辑写在这里
      editWorkerCodeRule(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            this.setState({
              name: fieldsValue.name,
              desc: fieldsValue.desc,
            });
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, this.props.currentEditRoleId);
            this.props.refreshTable();
          } else {
            message.error('编辑失败：请稍后再试！');
          }
        });
    });
  };

  render() {
    const {
      form: { getFieldDecorator }, detailData
    } = this.props;
    return (
      <Modal
        title="编辑工号规则"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.currentEditRoleId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地名称">
            {getFieldDecorator('baseId', {
              initialValue: detailData.baseId,
              rules: [{ required: true, message: '必填' }],
            })(
              <Select style={{ width: '100%' }} disabled>
                {this.state.orgBaseList.map(d => (
                  <Select.Option key={d.rowId}>{d.baseOrDeptName}</Select.Option>
                ))}
              </Select>
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号前缀">
            {getFieldDecorator('workerCodePrefix', {
              initialValue: detailData.workerCodePrefix,
              rules: [{ required: true, message: '必填' }],
            })(<Input placeholder="请输入" maxLength={8} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号长度">
            {getFieldDecorator('workerCodeLen', {
              initialValue: detailData.workerCodeLen,
              rules: [{ required: true, message: '必填' }],
            })(<InputNumber min={1} max={20} placeholder="请输入" maxLength={2} />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="是否可用">
            {getFieldDecorator('usable', {
              initialValue: detailData.usable,
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

export default Form.create({})(ModalEditForm);
