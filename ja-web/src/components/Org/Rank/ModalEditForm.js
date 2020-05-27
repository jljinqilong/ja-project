import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, InputNumber } from 'antd';
import { getById, editRank } from '../../../services/rank';

class ModalEditForm extends PureComponent {
  state = {
    base: '',
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
  //           rankName: data.data.rankName,
  //           rankDesc: data.data.rankDesc,
  //           salaryMax:data.data.salaryMax,
  //           salaryMin:data.data.salaryMin,
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
      if (fieldsValue.salaryMin >= fieldsValue.salaryMax) {
        message.error('薪资下限不能>=薪资上限!');
        return;
      }
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      //业务逻辑写在这里
      editRank(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable();
          }
        })
        .catch(() => {
          message.error('编辑失败：请联系管理员!');
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
        title="编辑职衔"
        visible={this.props.modalVisibleEdit}
        detailData={this.props.detailData}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, this.props.detailData.rowId)}
      >
        <Form>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级名称">
            {getFieldDecorator('rankName', {
              initialValue: this.props.detailData.rankName,
              rules: [{ required: true, message: '职级名称为必须' }],
            })(<Input placeholder="请输入" maxLength="20"/>)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级描述">
            {getFieldDecorator('rankDesc', {
              initialValue: this.props.detailData.rankDesc,
              rules: [{ required: true, message: '职级描述为必须' }],
            })(<Input placeholder="请输入" maxLength="50"/>)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="薪资上限">
            {getFieldDecorator('salaryMax', {
              initialValue: this.props.detailData.salaryMax,
              rules: [{ required: true, message: '薪资上限为必须' }],
            })(<Input
              placeholder="请输入"
              maxLength="11"
              onKeyUp={this.handleChangeNum.bind(this)}
            />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="薪资下限">
            {getFieldDecorator('salaryMin', {
              initialValue: this.props.detailData.salaryMin,
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

export default Form.create({})(ModalEditForm);
