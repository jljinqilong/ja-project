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
  Divider,
  Upload,
  Button,
  Icon,
} from 'antd';
import { addAgreement } from '../../../services/agreement';
import { getContractByStaffId } from '../../../services/staffContract';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';
import moment from 'moment';
import { getById } from '../../../services/staffBaseInfo';

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalAddForm extends PureComponent {
  state = {
    startValue: '',
    endValue: '',
    data: {},
    signDate: moment(new Date()).format('YYYY-MM-DD'),
    agreementDateStart: moment(new Date()).format('YYYY-MM-DD'),
    agreementDateEnd: '',
    staffId: '',
    loading: false,
    filePath: '',
    fileList: [],
    contractInfo: [],
    baseId: '',
    baseId_baseOrDeptName: '',
    deptId_baseOrDeptName: '',
    jobStatus_name: '',
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

  componentWillReceiveProps(nextProps) {
    if(nextProps.currentAddUserId === -1) {
      const { form } = this.props;
      form.resetFields();
      this.setState({
        staffId: '',
        staffName: '',
        baseId: '',
        deptId: '',
        positionName: '',
        rankName: '',
        gradeName: '',
        jobStatus: '',
      });
    }
  }
  /**
   * 下拉选择
   */
  handleChangeStaffName = staffId => {
    getById(staffId).then(data => {
      if (!!data && !!data.data) {
        this.setState({
          staffId: staffId,
          staffName: data.data.staffName,
          baseId: data.data.transNames.baseId_baseOrDeptName,
          deptId: data.data.transNames.deptId_baseOrDeptName,
          positionName: data.data.transNames.positionId_positionName,
          rankName: data.data.transNames.rankId_rankName,
          gradeName: data.data.transNames.gradeId_gradeName,
          jobStatus: data.data.transNames.jobStatus_name,
        });
      }
    });
    getContractByStaffId(staffId).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          contractInfo: content.data,
        });
      }
    });
  };

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

  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      if (this.state.agreementDateStart > this.state.agreementDateEnd) {
        message.warn('协议生效日期不能大于协议终止日期！');
        return;
      }
      form.resetFields();
      fieldsValue.signDate = this.state.signDate;
      fieldsValue.agreementDateStart = this.state.agreementDateStart;
      fieldsValue.agreementDateEnd = this.state.agreementDateEnd;
      fieldsValue.staffId = this.state.staffId;
      fieldsValue.file = this.state.filePath;
      addAgreement(fieldsValue)
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
          console.log(e);
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
        title="添加协议信息"
        visible={this.props.modalVisibleAdd}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 0, -1)}
      >
        <Form>
          <div>员工基础信息</div>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="员工信息">
                {getFieldDecorator('staffId', {
                  rules: [{ required: true, message: '员工信息' }],
                })(
                  <Select showSearch style={{ width: 350 }} placeholder="可按工号姓名组织架构筛选">
                    {this.props.staffAllList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={
                          d.rowId +
                          d.staffNo +
                          d.staffName +
                          (d.transNames === undefined ||
                          d.transNames.baseId_baseOrDeptName === undefined
                            ? ''
                            : ' ' + d.transNames.baseId_baseOrDeptName)
                        }
                        onClick={() => this.handleChangeStaffName(d.rowId)}
                      >
                        {d.staffNo +
                          '           ' +
                          d.staffName +
                          '           ' +
                          (d.transNames === undefined ||
                          d.transNames.baseId_baseOrDeptName === undefined
                            ? ''
                            : ' ' + d.transNames.baseId_baseOrDeptName)}
                      </Select.Option>
                    ))}
                  </Select>
                )}
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
            </Col>
          </Row>
          <Divider style={{ marginBottom: 32 }} />
          <div>协议基础信息</div>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议编号">
                {getFieldDecorator('agreementNo', {})(
                  <Input placeholder="可手动输入或者不填由系统生成" maxLength="50" />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="关联合同编号">
                {getFieldDecorator('relevanceContract', {})(
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
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议类型">
                {getFieldDecorator('agreementType', {
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
            </Col>
            <Col span={12}>
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
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议生效日期">
                {getFieldDecorator('agreementDateStart', {
                  initialValue: moment(this.state.agreementDateStart),
                  rules: [{ required: true, message: '请选择协议生效日期' }],
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangeStartDate}
                    style={{ width: 350 }}
                    format="YYYY-MM-DD"
                    setFieldsValue={startValue}
                    disabledDate={this.disabledStartDate}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议终止日期">
                {getFieldDecorator('agreementDateEnd', {
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
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="签订单位">
                {getFieldDecorator('owner', {
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
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="描述">
                {getFieldDecorator('describe')(<Input placeholder="请输入" maxLength="100" />)}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalAddForm);
