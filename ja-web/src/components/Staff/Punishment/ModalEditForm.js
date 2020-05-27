import React, { PureComponent, Fragement } from 'react';
import { Form, Input, Modal, message, DatePicker, Row, Col, Upload, Button, Icon } from 'antd';
import moment from 'moment';
import { getById, update } from '../../../services/punishment';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalEditForm extends PureComponent {
  state = {
    staffId: '',
    punishmentDate: '',
    revocationPunishmentDate: '',
    filePath: '',
    fileList: [],
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
            punishmentName: data.data.punishmentName,
            punishmentType: data.data.punishmentType,
            punishmentGist: data.data.punishmentGist,
            punishmentCause: data.data.punishmentCause,
            punishmentMeasure: data.data.punishmentMeasure,
            punishmentDeadline: data.data.punishmentDeadline,
            ratifyUnit: data.data.ratifyUnit,
            remark: data.data.remark,
            file: data.data.file,
            punishmentDate: data.data.punishmentDate,
            revocationPunishmentDate: data.data.revocationPunishmentDate,
          });
        })
        .catch(() => {
          message.error('查询惩处信息失败');
        });
    }
  }

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangePunishmentDate = (date, dateString) => {
    this.setState({
      punishmentDate: dateString,
    });
  };
  handleDatePickerChangeremoveTime = (date, dateString) => {
    this.setState({
      revocationPunishmentDate: dateString,
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

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.rowId = this.props.currentEditUserId;
      (fieldsValue.punishmentDate = this.state.punishmentDate),
        (fieldsValue.revocationPunishmentDate = this.state.revocationPunishmentDate),
        (fieldsValue.file = this.state.filePath);
      //业务逻辑写在这里
      update(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            form.resetFields();
            this.props.handleModalVisible(false, 1, -1);
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
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

  render() {
    const token = getToken();
    const {
      form: { getFieldDecorator },
    } = this.props;
    return (
      <Modal
        title="修改惩处信息"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <Row gutter={24}>
            <Form.Item>
              {getFieldDecorator('staffId', {
                initialValue: this.state.staffId,
              })(<Input type="hidden" disabled />)}
            </Form.Item>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号">
                <span>{this.props.staffNo}</span>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处名称">
                {getFieldDecorator('punishmentName', {
                  initialValue: this.state.punishmentName,
                  rules: [{ required: true, message: '请输入惩处名称' }],
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处类别">
                {getFieldDecorator('punishmentType', {
                  initialValue: this.state.punishmentType,
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="受惩处时间">
                {getFieldDecorator('punishmentDate', {
                  initialValue: moment(this.state.punishmentDate),
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangePunishmentDate}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处依据">
                {getFieldDecorator('punishmentGist', {
                  initialValue: this.state.punishmentGist,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处事由">
                {getFieldDecorator('punishmentCause', {
                  initialValue: this.state.punishmentCause,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处措施">
                {getFieldDecorator('punishmentMeasure', {
                  initialValue: this.state.punishmentMeasure,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处期限">
                {getFieldDecorator('punishmentDeadline', {
                  initialValue: this.state.punishmentDeadline,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处单位">
                {getFieldDecorator('ratifyUnit', {
                  initialValue: this.state.ratifyUnit,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="撤销处分时间">
                {getFieldDecorator('revocationPunishmentDate', {
                  initialValue: moment(this.state.revocationPunishmentDate),
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangeremoveTime}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
                {getFieldDecorator('remark', {
                  initialValue: this.state.remark,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="附件">
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
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
