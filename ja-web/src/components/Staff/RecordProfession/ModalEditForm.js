import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, DatePicker } from 'antd';
import moment from 'moment';
import { getById, update } from '../../../services/recordProfession';

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
    qualifyTime: '',
    startValue: '',
    endValue: '',
    certificateStartDate:'',
    certificateEndDate:'',
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeQualifyTime = (date, dateString) => {
    this.setState({
      qualifyTime: dateString,
    });
  };

  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      certificateStartDate: dateString,
    });
    this.onChange('startValue', date);
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      certificateEndDate: dateString,
    });
    this.onChange('endValue', date);
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentEditUserId != nextProps.currentEditUserId &&
      nextProps.currentEditUserId > 0
    ) {
      getById(nextProps.currentEditUserId)
        .then(data => {
          this.setState({
            rowId: data.data.rowId,
            staffId: data.data.staffId,
            certificateName: data.data.certificateName,
            professionalTitleRank: data.data.professionalTitleRank,
            remark: data.data.remark,
            qualifyTime: data.data.qualifyTime,
            certificateStartDate:data.data.certificateStartDate,
            certificateEndDate:data.data.certificateEndDate,
          });
        })
        .catch(() => {
          message.error('查询证书信息失败');
        });
    }
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      fieldsValue.qualifyTime = this.state.qualifyTime,
      fieldsValue.certificateEndDate = this.state.certificateEndDate,
      fieldsValue.certificateStartDate = this.state.certificateStartDate,
        //业务逻辑写在这里
        update(fieldsValue)
          .then(data => {
            if (data.code === 200) {
              message.success('编辑成功');
              this.props.handleModalVisible(false, 1, -1);
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

  disabledStartDate = startValue => {
    const endValue = this.state.endValue;
    if (!startValue || !endValue) {
      return false;
    }
    return startValue.valueOf() > endValue.valueOf();
  };

  disabledEndDate = endValue => {
    const startValue = this.state.startValue;
    if (!endValue || !startValue) {
      return false;
    }
    return endValue.valueOf() <= startValue.valueOf();
  };

  onChange = (field, value) => {
    this.setState({
      [field]: value,
    });
  };


  render() {
    const { startValue, endValue } = this.state;
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="修改信息"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <Form.Item>
            {getFieldDecorator('staffId', {
              initialValue: this.state.staffId,
            })(<Input type="hidden" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="职称证书名称">
            {getFieldDecorator('certificateName', {
              initialValue: this.state.certificateName,
              rules: [{ required: true, message: '请输入职称证书名称' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="职称级别">
            {getFieldDecorator('professionalTitleRank', {
              initialValue: this.state.professionalTitleRank,
              rules: [{ required: true, message: '职称级别' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="取得资格时间">
            {getFieldDecorator('qualifyTime', {
              initialValue: moment(this.state.qualifyTime),
            })(
              <DatePicker onChange={this.handleDatePickerChangeQualifyTime} format="YYYY-MM-DD" />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="证书发放日期">
            {getFieldDecorator('certificateStartDate', {
              initialValue:moment(this.state.certificateStartDate),
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeStartDate}
                format="YYYY-MM-DD"
                setFieldsValue={startValue}
                disabledDate={this.disabledStartDate}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="证书有效日期">
            {getFieldDecorator('certificateEndDate', {
              initialValue:moment(this.state.certificateEndDate),
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeEndDate}
                format="YYYY-MM-DD"
                setFieldsValue={endValue}
                disabledDate={this.disabledEndDate}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="发放机构">
            {getFieldDecorator('grantOrg', {
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="发放级别">
            {getFieldDecorator('grantLevel', {
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>

          <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 15 }} label="备注">
            {getFieldDecorator('remark', {
              initialValue: this.state.remark,
            })(<Input placeholder="请输入" maxLength="80" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
