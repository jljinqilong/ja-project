import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, DatePicker, Upload, Button, Icon } from 'antd';
import moment from 'moment';
import { getById, update } from '../../../services/foreignReside';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalEditForm extends PureComponent {
  state = {
    startValue: '',
    endValue: '',
    base: '',
    certifyingDate: '',
    startDate: '',
    endDate: '',
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

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentEditUserId != nextProps.currentEditUserId &&
      nextProps.currentEditUserId > 0
    ) {
      const { form } = this.props;
      form.resetFields();
      getById(nextProps.currentEditUserId)
        .then(data => {
          this.setState({
            rowId: data.data.rowId,
            staffId: data.data.staffId,
            workPermitNo: data.data.workPermitNo,
            certifyingAuthority: data.data.certifyingAuthority,
            file: data.data.file,
            certifyingDate: data.data.certifyingDate,
            startDate: data.data.startDate,
            endDate: data.data.endDate,
            fileList: [],
          });
        })
        .catch(() => {
          message.error('查询信息失败');
        });
    }
  }

  handleChange = info => {
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

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      fieldsValue.rowId = this.props.currentEditUserId;
      (fieldsValue.certifyingDate = this.state.certifyingDate),
        (fieldsValue.startDate = this.state.startDate),
        (fieldsValue.endDate = this.state.endDate),
        (fieldsValue.file = this.state.filePath);
      //业务逻辑写在这里
      update(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
              startValue: '',
              endValue: '',
            });
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
    const token = getToken();
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="编辑居留签注证信息"
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
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="工号">
            <span>{this.props.staffNo}</span>
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="工作许可编号">
            {getFieldDecorator('workPermitNo', {
              initialValue: this.state.workPermitNo,
              rules: [{ required: true, message: '请输入工作许可编号' }],
            })(<Input placeholder="请输入" maxLength="50" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="发证机关">
            {getFieldDecorator('certifyingAuthority', {
              initialValue: this.state.certifyingAuthority,
              rules: [{ required: true, message: '请输入发证机关' }],
            })(<Input placeholder="请输入" maxLength="30" />)}
          </Form.Item>
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="发证日期">
            {getFieldDecorator('certifyingDate', {
              initialValue: moment(this.state.certifyingDate),
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
              initialValue: moment(this.state.startDate),
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
              initialValue: moment(this.state.endDate),
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
          <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 15 }} label="附件">
            <Upload
              name="file"
              action={`${serverUrlPre}/system/file/upload/single?token=${token}`}
              onChange={this.handleChange}
              fileList={this.state.fileList}
            >
              <Button>
                <Icon type="upload" />点击上传附件
              </Button>
            </Upload>
            {this.state.file !== undefined &&
              this.state.file !== '' && (
                <a
                  href={`${serverUrlPre}/system/file/download?filePath=${
                    this.state.file
                  }&token=${token}`}
                  title={'附件下载'}
                  alt="file"
                >
                  附件下载
                </a>
              )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
