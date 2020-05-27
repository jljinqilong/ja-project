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
  Upload,
  Button,
  Icon,
} from 'antd';
import moment from 'moment';
import { getById, update } from '../../../services/award';
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
    awardTime: '',
    credentialIssueDate: '',
    year: '',
    filePath: '',
    fileList: [],
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeCredentialIssueDate = (date, dateString) => {
    this.setState({
      credentialIssueDate: dateString,
    });
  };
  handleDatePickerChangeAwardTime = (date, dateString) => {
    this.setState({
      awardTime: dateString,
    });
  };
  handleDatePickerChangeYear = (date, dateString) => {
    this.setState({
      year: dateString,
    });
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
            awardName: data.data.awardName,
            awardType: data.data.awardType,
            awardRank: data.data.awardRank,
            ratifyUnit: data.data.ratifyUnit,
            awardCause: data.data.awardCause,
            awardGist: data.data.awardGist,
            awardMeasure: data.data.awardMeasure,
            remark: data.data.remark,
            awardTime: data.data.awardTime,
            year: data.data.year,
            file: data.data.file,
            credentialIssueDate: data.data.credentialIssueDate,
            credentialIssueOrg: data.data.credentialIssueOrg,
            rewardForm: data.data.rewardForm,
          });
        })
        .catch(() => {
          message.error('查询信息失败');
        });
    }
  }

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
      fieldsValue.awardTime = this.state.awardTime;
      fieldsValue.credentialIssueDate = this.state.credentialIssueDate;
      fieldsValue.year = this.state.year;
      fieldsValue.file = this.state.filePath;
      form.resetFields();
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
        title="修改奖励"
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
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="荣誉名称">
                {getFieldDecorator('awardName', {
                  initialValue: this.state.awardName,
                  rules: [{ required: true, message: '请输入荣誉名称' }],
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="证书颁发日期">
                {getFieldDecorator('credentialIssueDate', {
                  initialValue: moment(this.state.credentialIssueDate),
                })(
                  <DatePicker onChange={this.handleDatePickerChangeCredentialIssueDate} format="YYYY-MM-DD" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="证书颁发机构">
                {getFieldDecorator('credentialIssueOrg', {
                  initialValue: this.state.credentialIssueOrg,
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励形式">
                {getFieldDecorator('rewardForm', {
                  initialValue: this.state.rewardForm,
                })(<Input placeholder="请输入" maxLength="30" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励类别">
                {getFieldDecorator('awardType', {
                  initialValue: this.state.awardType,
                })(
                  <Select style={{ width: 355 }}>
                    {this.props.awardTypeCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励级别">
                {getFieldDecorator('awardRank', {
                  initialValue: this.state.awardRank,
                })(
                  <Select style={{ width: 355 }}>
                    {this.props.awardRankCode.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="获奖时间">
                {getFieldDecorator('awardTime', {
                  initialValue: moment(this.state.awardTime),
                })(
                  <DatePicker onChange={this.handleDatePickerChangeAwardTime} format="YYYY-MM-DD" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="年度">
                {getFieldDecorator('year', {
                  initialValue: moment(this.state.year),
                })(
                  <DatePicker
                    placeholder="选到年即可"
                    onChange={this.handleDatePickerChangeYear}
                    format="YYYY"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="批准单位">
                {getFieldDecorator('ratifyUnit', {
                  initialValue: this.state.ratifyUnit,
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励事由">
                {getFieldDecorator('awardCause', {
                  initialValue: this.state.awardCause,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励依据">
                {getFieldDecorator('awardGist', {
                  initialValue: this.state.awardGist,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励措施">
                {getFieldDecorator('awardMeasure', {
                  initialValue: this.state.awardMeasure,
                })(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
                {getFieldDecorator('remark', {
                  initialValue: this.state.remark,
                })(<Input placeholder="请输入" maxLength="100" />)}
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
