import React, { PureComponent } from 'react';
import {
  Form,
  Input,
  Modal,
  message,
  Select,
  DatePicker,
  Row,
  Col,
  Divider,
  Upload,
  Button,
  Icon,
} from 'antd';
import moment from 'moment';
import { getById, updateAgreement } from '../../../services/agreement';
import { serverUrlPre } from '../../../utils/request';
import { getContractByStaffId } from '../../../services/staffContract';
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
    staffId: '',
    signDate: '',
    agreementDateStart: '',
    agreementDateEnd: '',
    filePath: '',
    fileList: [],
    contractInfo: [],
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeSignDate = (date, dateString) => {
    this.setState({
      signDate: dateString,
    });
  };
  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      agreementDateStart: dateString,
    });
    this.onChange('startValue', date);
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      agreementDateEnd: dateString,
    });
    this.onChange('endValue', date);
  };

  /**
   * 下拉选择
   */
  handleChangeStaffName = staffName => {
    this.setState({
      staffName: staffName,
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
      const { form } = this.props;
      form.resetFields();
      getById(nextProps.currentEditUserId)
        .then(data => {
          if (data.data[0].baseInfo !== undefined) {
            this.setState({
              staffNo: data.data[0].baseInfo.staffNo,
              staffName: data.data[0].baseInfo.staffName,
            });
            if (data.data[0].baseInfo.transNames !== undefined) {
              this.setState({
                baseId: data.data[0].baseInfo.transNames.baseId_baseOrDeptName,
                deptId: data.data[0].baseInfo.transNames.deptId_baseOrDeptName,
                jobStatus: data.data[0].baseInfo.transNames.jobStatus_name,
                positionName: data.data[0].baseInfo.transNames.positionId_positionName,
                rankName: data.data[0].baseInfo.transNames.rankId_rankName,
                gradeName: data.data[0].baseInfo.transNames.gradeId_gradeName,
              });
            }
          }
          this.setState({
            rowId: data.data[0].rowId,
            staffId: data.data[0].staffId,
            agreementNo: data.data[0].agreementNo,
            agreementState: data.data[0].agreementState,
            agreementType: data.data[0].agreementType,
            owner: data.data[0].owner,
            file: data.data[0].file,
            describe: data.data[0].describe,
            signDate: data.data[0].signDate,
            agreementDateStart: data.data[0].agreementDateStart,
            agreementDateEnd: data.data[0].agreementDateEnd,
            relevanceContract: data.data[0].relevanceContract,
            fileList: [],
          });
          getContractByStaffId(this.state.staffId)
            .then(content => {
              this.setState({
                contractInfo: content.data,
              });
            })
            .catch(e => {
              console.log(e);
            });
        })
        .catch(() => {
          message.error('查询协议信息失败');
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
      if (this.state.agreementDateStart > this.state.agreementDateEnd) {
        message.warn('协议生效日期不能大于协议终止日期！');
        return;
      }
      fieldsValue.rowId = this.props.currentEditUserId;
      fieldsValue.signDate = this.state.signDate;
      fieldsValue.agreementDateStart = this.state.agreementDateStart;
      fieldsValue.agreementDateEnd = this.state.agreementDateEnd;
      fieldsValue.file = this.state.filePath;
      fieldsValue.status = 1;
      form.resetFields();
      //业务逻辑写在这里
      updateAgreement(fieldsValue)
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
        title="修改协议"
        visible={this.props.modalVisibleEdit}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 1, -1)}
      >
        <Form>
          <div>员工基础信息</div>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="工号">
                {getFieldDecorator('staffNo', {
                  initialValue: this.state.staffNo,
                  rules: [{ required: true, message: '请选择工号' }],
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="姓名">
                {getFieldDecorator('staffName', {
                  initialValue: this.state.staffName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="基地">
                {getFieldDecorator('baseId', {
                  initialValue: this.state.baseId,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="部门">
                {getFieldDecorator('deptId', {
                  initialValue: this.state.deptId,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职衔">
                {getFieldDecorator('positionName', {
                  initialValue: this.state.positionName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职等/赋值名称">
                {getFieldDecorator('gradeName', {
                  initialValue: this.state.gradeName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="职级">
                {getFieldDecorator('rankName', {
                  initialValue: this.state.rankName,
                })(<Input disabled />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="在职状态">
                {getFieldDecorator('jobStatus', {
                  initialValue: this.state.jobStatus,
                })(<Input disabled />)}
              </Form.Item>
              <Form.Item>
                {getFieldDecorator('staffId', {
                  initialValue: this.state.staffId,
                })(<Input type="hidden" disabled />)}
              </Form.Item>
              <Form.Item>
                {getFieldDecorator('agreementState', {
                  initialValue: this.state.agreementState,
                })(<Input type="hidden" disabled />)}
              </Form.Item>
            </Col>
          </Row>
          <Divider style={{ marginBottom: 32 }} />
          <div>协议基础信息</div>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议编号">
                {getFieldDecorator('agreementNo', {
                  initialValue: this.state.agreementNo,
                  rules: [{ message: '请输入协议编号' }],
                })(<Input disabled placeholder="手动输入或者可不填由系统生成" maxLength="50" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              {this.state.agreementState === '1' ? (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议类型">
                  {getFieldDecorator('agreementType', {
                    initialValue: this.state.agreementType,
                    rules: [{ required: true, message: '请选择协议类型' }],
                  })(
                    <Select style={{ width: 350 }}>
                      {this.props.agreementCode.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              ) : (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议类型">
                  {getFieldDecorator('agreementType', {
                    initialValue: this.state.agreementType,
                    rules: [{ required: true, message: '请选择协议类型' }],
                  })(
                    <Select disabled style={{ width: 350 }}>
                      {this.props.agreementCode.map(d => (
                        <Select.Option key={d.rowId} value={d.rowId}>
                          {d.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              )}
            </Col>
            <Col span={12}>
              {this.state.agreementState === '1' ? (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="签订日期">
                  {getFieldDecorator('signDate', {
                    initialValue: moment(this.state.signDate),
                    rules: [{ required: true, message: '请选择签订日期' }],
                  })(
                    <DatePicker
                      onChange={this.handleDatePickerChangeSignDate}
                      format="YYYY-MM-DD"
                      style={{ width: 350 }}
                    />
                  )}
                </Form.Item>
              ) : (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="签订日期">
                  {getFieldDecorator('signDate', {
                    initialValue: moment(this.state.signDate),
                    rules: [{ required: true, message: '请选择签订日期' }],
                  })(
                    <DatePicker
                      disabled
                      onChange={this.handleDatePickerChangeSignDate}
                      format="YYYY-MM-DD"
                      style={{ width: 350 }}
                    />
                  )}
                </Form.Item>
              )}
            </Col>
            <Col span={12}>
              {this.state.agreementState === '1' ? (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议生效日期">
                  {getFieldDecorator('agreementDateStart', {
                    initialValue: moment(this.state.agreementDateStart),
                    rules: [{ required: true, message: '请选择协议生效日期' }],
                  })(
                    <DatePicker
                      onChange={this.handleDatePickerChangeStartDate}
                      format="YYYY-MM-DD"
                      setFieldsValue={startValue}
                      disabledDate={this.disabledStartDate}
                      style={{ width: 350 }}
                    />
                  )}
                </Form.Item>
              ) : (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议生效日期">
                  {getFieldDecorator('agreementDateStart', {
                    initialValue: moment(this.state.agreementDateStart),
                    rules: [{ required: true, message: '请选择协议生效日期' }],
                  })(
                    <DatePicker
                      disabled
                      onChange={this.handleDatePickerChangeStartDate}
                      format="YYYY-MM-DD"
                      style={{ width: 350 }}
                    />
                  )}
                </Form.Item>
              )}
            </Col>
            <Col span={12}>
              {this.state.agreementState === '1' ? (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议终止日期">
                  {getFieldDecorator('agreementDateEnd', {
                    initialValue: moment(this.state.agreementDateEnd),
                    rules: [{ required: true, message: '请选择协议终止日期' }],
                  })(
                    <DatePicker
                      onChange={this.handleDatePickerChangeEndDate}
                      style={{ width: 350 }}
                      format="YYYY-MM-DD"
                      setFieldsValue={endValue}
                      disabledDate={this.disabledEndDate}
                    />
                  )}
                </Form.Item>
              ) : (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议终止日期">
                  {getFieldDecorator('agreementDateEnd', {
                    initialValue: moment(this.state.agreementDateEnd),
                    rules: [{ required: true, message: '请选择协议终止日期' }],
                  })(
                    <DatePicker
                      disabled
                      onChange={this.handleDatePickerChangeEndDate}
                      format="YYYY-MM-DD"
                      style={{ width: 350 }}
                    />
                  )}
                </Form.Item>
              )}
            </Col>
            <Col span={12}>
              {this.state.agreementState === '1' ? (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="签订单位">
                  {getFieldDecorator('owner', {
                    initialValue: this.state.owner,
                    rules: [{ required: true, message: '请选择签订单位' }],
                  })(
                    <Select showSearch style={{ width: 350 }}>
                      {this.props.allBaseList.map(d => (
                        <Select.Option key={d.rowId} value={d.baseOrDeptName}>
                          {d.baseOrDeptName}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              ) : (
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="签订单位">
                  {getFieldDecorator('owner', {
                    initialValue: this.state.owner,
                    rules: [{ required: true, message: '请选择签订单位' }],
                  })(
                    <Select disabled showSearch style={{ width: 350 }}>
                      {this.props.allBaseList.map(d => (
                        <Select.Option key={d.rowId} value={d.baseOrDeptName}>
                          {d.baseOrDeptName}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                </Form.Item>
              )}
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="关联合同编号">
                {getFieldDecorator('relevanceContract', {
                  initialValue: this.state.relevanceContract,
                })(
                  <Select style={{ width: 350 }}>
                    {this.state.contractInfo.map(d => (
                      <Select.Option key={d.rowId} value={d.contractNo}>
                        {d.contractNo +
                          '    ' +
                          (d.transNames === undefined ||
                          d.transNames.contractType_name === undefined
                            ? ''
                            : ' ' + d.transNames.contractType_name)}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="附件">
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
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
                {getFieldDecorator('describe', {
                  initialValue: this.state.describe,
                })(<Input placeholder="请输入" maxLength="100" />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalEditForm);
