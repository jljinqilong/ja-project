import React, { PureComponent, Fragement } from 'react';
import {
  Form,
  Input,
  Modal,
  message,
  Select,
  DatePicker,
  Row,
  Col,
  Icon,
  Upload,
  Button,
} from 'antd';
import { add } from '../../../services/punishment';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalAddForm extends PureComponent {
  state = {
    punishmentDate: '',
    revocationPunishmentDate: '',
    filePath: '',
    fileList: [],
  };

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

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      console.log(fieldsValue);
      (fieldsValue.punishmentDate = this.state.punishmentDate),
        (fieldsValue.revocationPunishmentDate = this.state.revocationPunishmentDate),
        (fieldsValue.file = this.state.filePath);
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
            });
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
        });
    });
  };
  handleSureNext = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      fieldsValue.punishmentDate = this.state.punishmentDate;
      fieldsValue.revocationPunishmentDate = this.state.revocationPunishmentDate;
      fieldsValue.file = this.state.filePath;
      form.resetFields();
      add(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            form.resetFields();
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
            });
          } else if (data.code === 400) {
            message.error('信息已存在');
          }
        })
        .catch(e => {
          console.log(e);
          message.error('添加失败！');
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
        title="添加惩处信息"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        width={1200}
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
          <Row gutter={24}>
            <Form.Item>
              {getFieldDecorator('staffId', {
                initialValue: this.props.staffId,
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
                  rules: [{ required: true, message: '请输入惩处名称' }],
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处类别">
                {getFieldDecorator('punishmentType', {})(
                  <Input placeholder="请输入" maxLength="50" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="受惩处时间">
                {getFieldDecorator('punishmentDate', {})(
                  <DatePicker
                    onChange={this.handleDatePickerChangePunishmentDate}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处依据">
                {getFieldDecorator('punishmentGist', {})(
                  <Input placeholder="请输入" maxLength="80" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处事由">
                {getFieldDecorator('punishmentCause', {})(
                  <Input placeholder="请输入" maxLength="80" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处措施">
                {getFieldDecorator('punishmentMeasure', {})(
                  <Input placeholder="请输入" maxLength="80" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处期限">
                {getFieldDecorator('punishmentDeadline', {})(
                  <Input placeholder="请输入" maxLength="80" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="惩处单位">
                {getFieldDecorator('ratifyUnit', {})(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="撤销处分时间">
                {getFieldDecorator('revocationPunishmentDate', {})(
                  <DatePicker
                    onChange={this.handleDatePickerChangeremoveTime}
                    format="YYYY-MM-DD"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
                {getFieldDecorator('remark')(<Input placeholder="请输入" maxLength="80" />)}
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
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
