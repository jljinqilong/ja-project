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
import { add } from '../../../services/award';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalAddForm extends PureComponent {
  state = {
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
      fieldsValue.awardTime = this.state.awardTime;
      fieldsValue.credentialIssueDate = this.state.credentialIssueDate;
      fieldsValue.year = this.state.year;
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
      form.resetFields();
      fieldsValue.awardTime = this.state.awardTime;
      fieldsValue.year = this.state.year;
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
        title="添加奖励信息"
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
                })(
                  <DatePicker onChange={this.handleDatePickerChangeCredentialIssueDate} format="YYYY-MM-DD" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="证书颁发机构">
                {getFieldDecorator('credentialIssueOrg', {
                })(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励形式">
                {getFieldDecorator('rewardForm', {
                })(<Input placeholder="请输入" maxLength="30" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励类别">
                {getFieldDecorator('awardType')(
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
                {getFieldDecorator('awardRank')(
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
                {getFieldDecorator('awardTime')(
                  <DatePicker onChange={this.handleDatePickerChangeAwardTime} format="YYYY-MM-DD" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="年度">
                {getFieldDecorator('year')(
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
                {getFieldDecorator('ratifyUnit')(<Input placeholder="请输入" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励事由">
                {getFieldDecorator('awardCause')(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励依据">
                {getFieldDecorator('awardGist')(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="奖励措施">
                {getFieldDecorator('awardMeasure')(<Input placeholder="请输入" maxLength="80" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="备注">
                {getFieldDecorator('remark')(<Input placeholder="请输入" maxLength="100" />)}
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
