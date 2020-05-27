import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, DatePicker, Icon, Upload, Button } from 'antd';
import { add } from '../../../services/foreignVisa';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';
import moment from 'moment';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalAddForm extends PureComponent {
  state = {
    startValue: '',
    endValue: '',
    certifyingDate: '',
    startDate: '',
    endDate: '',
    annualInspectionDate: '',
    loading: false,
    filePath: '',
    fileList: [],
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeCertifyingDate = (date, dateString) => {
    this.setState({
      certifyingDate: dateString,
    });
  };
  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      startDate: dateString,
    });
    this.onChange('startValue', date);
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      endDate: dateString,
    });
    this.onChange('endValue', date);
  };
  handleDatePickerChangeAnnualInspectionDateStartDate = (date, dateString) => {
    this.setState({
      annualInspectionDate: dateString,
    });
  };

  handleChangeFile = info => {
    let fileList = info.fileList;
    fileList = fileList.slice(-1);
    this.setState({ fileList });
    if (info.file.status === 'uploading') {
      this.setState({ loading: true });
      return;
    }
    if (info.file.status === 'done') {
      if (info.file.response != null) {
        if (info.file.response.code === 200) {
          getBase64(info.file.originFileObj, () =>
            this.setState({
              loading: false,
              filePath: info.file.response.data,
            })
          );
        }
      }
    }
  };

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.certifyingDate = this.state.certifyingDate,
      fieldsValue.startDate = this.state.startDate,
      fieldsValue.endDate = this.state.endDate,
      fieldsValue.annualInspectionDate = this.state.annualInspectionDate,
      fieldsValue.file = this.state.filePath;
      form.resetFields();
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 0, 0);
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
              startValue: '',
              endValue: '',
            });
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          message.error('添加失败！');
        });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.certifyingDate = this.state.certifyingDate,
      fieldsValue.startDate = this.state.startDate,
      fieldsValue.endDate = this.state.endDate,
      fieldsValue.annualInspectionDate = this.state.annualInspectionDate,
      fieldsValue.file = this.state.filePath;
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            form.resetFields();
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
              startValue: '',
              endValue: '',
            });
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          message.error('添加失败！');
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
    const token = getToken();
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="添加外籍员工工作许可证信息"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
        footer={[
          <Button
            key="back"
            type="primary"
            onClick={() => this.props.handleModalVisible(false, 0, -1)}
          >
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={this.handleSure}>
            保存
          </Button>,
          <Button type="primary" onClick={this.handleSureNext}>
            添加下一条<Icon type="right" />
          </Button>,
        ]}
      >
        <Form>
          <Form.Item>
            {getFieldDecorator('staffId', {
              initialValue: this.props.staffId,
            })(<Input type="hidden" disabled />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="工作许可编号">
            {getFieldDecorator('workPermitNo', {
              rules: [{ required: true, message: '请输入工作许可编号' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="发证机关">
            {getFieldDecorator('certifyingAuthority', {
              rules: [{ required: true, message: '请输入发证机关' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="发证日期">
            {getFieldDecorator('certifyingDate', {
              rules: [{ required: true, message: '请输入发证日期' }],
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeCertifyingDate}
                format="YYYY-MM-DD"
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="有效期开始时间">
            {getFieldDecorator('startDate', {
              rules: [{ required: true, message: '请输入有效期开始时间' }],
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeStartDate}
                format="YYYY-MM-DD"
                setFieldsValue={startValue}
                disabledDate={this.disabledStartDate}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="有效期结束时间">
            {getFieldDecorator('endDate', {
              rules: [{ required: true, message: '请输入有效期结束时间' }],
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeEndDate}
                format="YYYY-MM-DD"
                setFieldsValue={endValue}
                disabledDate={this.disabledEndDate}
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="年检时间">
            {getFieldDecorator('annualInspectionDate', {
              rules: [{ required: true, message: '请输入年检时间' }],
            })(
              <DatePicker
                onChange={this.handleDatePickerChangeAnnualInspectionDateStartDate}
                format="YYYY-MM-DD"
              />
            )}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="附件">
            <Upload
              name="file"
              action={`${serverUrlPre}/system/file/upload/single?token=${token}`}
              onChange={this.handleChangeFile}
              fileList={this.state.fileList}
            >
              <Button>
                <Icon type="upload" />点击上传附件
              </Button>
            </Upload>
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
