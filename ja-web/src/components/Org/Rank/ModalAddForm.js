import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, InputNumber } from 'antd';
import { addRank } from '../../../services/rank';

class ModalAddForm extends PureComponent {
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if (Number(fieldsValue.salaryMin) >= Number(fieldsValue.salaryMax)) {
        message.error('薪资下限不能>=薪资上限!');
        return;
      }
      form.resetFields();
      addRank(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
          } else if (data.code === 400) {
            message.error('职位已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };


  handleChangeNum = num => {
    const { form } = this.props;
    let salsry = num.target.value;
    if (salsry !== '') {
      salsry = salsry.replace(/[^\d.]/g, ''); //清除“数字”和“.”以外的字符
      salsry = salsry.replace(/\.{2,}/g, '.'); //只保留第一个. 清除多余的
      salsry = salsry
        .replace('.', '$#$')
        .replace(/\./g, '')
        .replace('$#$', '.');
      salsry = salsry.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
      if (salsry.indexOf('.') < 0 && salsry != '') {
        //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        salsry = parseFloat(salsry);
      }
    }
    form.setFieldsValue({
      salaryMax: salsry,
    });
  };

  handleChangeNum2 = num => {
    const { form } = this.props;
    let salsry = num.target.value;
    if (salsry !== '') {
      salsry = salsry.replace(/[^\d.]/g, ''); //清除“数字”和“.”以外的字符
      salsry = salsry.replace(/\.{2,}/g, '.'); //只保留第一个. 清除多余的
      salsry = salsry
        .replace('.', '$#$')
        .replace(/\./g, '')
        .replace('$#$', '.');
      salsry = salsry.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
      if (salsry.indexOf('.') < 0 && salsry != '') {
        //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        salsry = parseFloat(salsry);
      }
    }
    form.setFieldsValue({
      salaryMin: salsry,
    });
  };

  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加职级"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级名称">
            {getFieldDecorator('rankName', {
              rules: [{ required: true, message: '职级名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级描述">
            {getFieldDecorator('rankDesc', {
              rules: [{ required: true, message: '职级描述为必须' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="薪资上限">
            {getFieldDecorator('salaryMax', {
              rules: [{ required: true, message: '薪资上限为必须' }],
            })(<Input
              placeholder="请输入"
              maxLength="11"
              onKeyUp={this.handleChangeNum.bind(this)}
            />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="薪资下限">
            {getFieldDecorator('salaryMin', {
              rules: [{ required: true, message: '薪资下限为必须' }],
            })(<Input
              placeholder="请输入"
              maxLength="11"
              onKeyUp={this.handleChangeNum2.bind(this)}
            />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
