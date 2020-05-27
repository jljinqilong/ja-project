import React, { PureComponent } from 'react';
import {Form, Input, Modal, message, Select, DatePicker, Row, Col, Upload, Button, Icon, Card,} from 'antd';
import DescriptionList from '../../../components/DescriptionList';
import { addAgreement, getById } from '../../../services/agreement';
import { serverUrlPre } from '../../../utils/request';
import { getContractByStaffId } from '../../../services/staffContract';
import { getToken } from '../../../utils/authority';
import styles from './Agreement.less'
import moment from 'moment';

const { Description } = DescriptionList;

function getBase64(filePath, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(filePath);
}

class ModalRenewForm extends PureComponent {
  state = {
    startValue: '',
    endValue: '',
    staffId: '',
    signDate1: '',
    agreementDateStart1: '',
    agreementDateEnd1: '',
    renewStatus1: '',
    filePath: '',
    fileList: [],
    signDate: '',
    agreementDateStart: '',
    agreementDateEnd: '',
    contractInfo: [],
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeSignDate1 = (date, dateString) => {
    this.setState({
      signDate1: dateString,
    });
  };
  handleDatePickerChangeStartDate1 = (date, dateString) => {
    this.setState({
      agreementDateStart1: dateString,
    });
  };
  handleDatePickerChangeEndDate1 = (date, dateString) => {
    this.setState({
      agreementDateEnd1: dateString,
    });
  };

  /**
   * 添加写入时间
   * @param date
   * @param dateString
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
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentRenewUserId != nextProps.currentRenewUserId &&
      nextProps.currentRenewUserId > 0
    ) {
      getById(nextProps.currentRenewUserId)
        .then(data => {
          if (data.data[0].baseInfo !== undefined) {
            this.setState({
              staffNo1: data.data[0].baseInfo.staffNo,
              staffName1: data.data[0].baseInfo.staffName,
            });
            if (data.data[0].baseInfo.transNames !== undefined) {
              this.setState({
                baseId1: data.data[0].baseInfo.transNames.baseId_baseOrDeptName,
                deptId1: data.data[0].baseInfo.transNames.deptId_baseOrDeptName,
                jobStatus1: data.data[0].baseInfo.transNames.jobStatus_name,
                positionName1: data.data[0].baseInfo.transNames.positionId_positionName,
                rankName1: data.data[0].baseInfo.transNames.rankId_rankName,
                gradeName1: data.data[0].baseInfo.transNames.gradeId_gradeName,
                agreementType1: data.data[0].transNames.agreementType_name,
              });
            }
          }
          this.setState({
            rowId: data.data[0].rowId,
            staffId: data.data[0].staffId,
            agreementNo1: data.data[0].agreementNo,
            relevanceContract: data.data[0].relevanceContract,
            agreementPeriod1: data.data[0].agreementPeriod,
            owner1: data.data[0].owner,
            file1: data.data[0].file,
            describe1: data.data[0].describe,
            signDate1: data.data[0].signDate,
            agreementDateStart1: data.data[0].agreementDateStart,
            agreementDateEnd1: data.data[0].agreementDateEnd,
          });
          getContractByStaffId(data.data[0].staffId)
            .then(content => {
              this.setState({
                contractInfo: content.data,
              });
            })
            .catch(e => {
              console.log(e);
            });
        })
        .catch(e => {
          console.log(e);
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
      let today = moment(new Date()).format('YYYY-MM-DD');
      if (this.state.agreementDateEnd <= today) {
        message.warn('协议终止日期只能选择当前日期或之后日期！');
        return;
      }
      if (this.state.agreementDateStart > this.state.agreementDateEnd) {
        message.warn('协议生效日期不能大于协议终止日期！');
        return;
      }
      fieldsValue.rowId = this.props.currentRenewUserId;
      fieldsValue.signDate = this.state.signDate;
      fieldsValue.agreementDateStart = this.state.agreementDateStart;
      fieldsValue.agreementDateEnd = this.state.agreementDateEnd;
      fieldsValue.relieveDate = this.state.relieveDate;
      fieldsValue.status = 4;
      fieldsValue.file = this.state.filePath;
      form.resetFields();
      //业务逻辑写在这里
      addAgreement(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('添加成功');
            this.props.handleModalVisible(false, 3, -1);
            this.props.refreshTable();
            this.setState({
              fileList: [],
              filePath: '',
              startValue: '',
              endValue: '',
            });
          } else {
            message.error('添加失败：请稍后再试！');
          }
        })
        .catch(e => {
          message.error('添加失败：请联系管理员!');
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
        title="续签合同"
        visible={this.props.modalVisibleRenew}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 3, -1)}
      >
        <Form>
          <Card title={'员工基础信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="工号" className={styles.info}>{this.state.staffNo1}</Description>
              <Description term="姓名" className={styles.info}>{this.state.staffName1}</Description>
              <Description term="基地" className={styles.info}>{this.state.baseId1}</Description>
              <Description term="部门" className={styles.info}>{this.state.deptId1}</Description>
              <Description term="职衔" className={styles.info}>{this.state.positionName1}</Description>
              <Description term="职级" className={styles.info}>{this.state.rankName1}</Description>
              <Description term="职等/赋值名称" className={styles.info}>{this.state.gradeName1}</Description>
              <Description term="在职状态" className={styles.info}>{this.state.jobStatus1}</Description>
            </DescriptionList>
          </Card>
          <Card title={'协议基础信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="协议编号" className={styles.info}>{this.state.agreementNo1}</Description>
              <Description term="关联合同编号" className={styles.info}>{this.state.relevanceContract}</Description>
              <Description term="协议类型" className={styles.info}>{this.state.agreementType1}</Description>
              <Description term="签订日期" className={styles.info}>{this.state.signDate1}</Description>
              <Description term="协议生效日期" className={styles.info}>{this.state.agreementDateStart1}</Description>
              <Description term="协议终止日期" className={styles.info}>{this.state.agreementDateEnd1}</Description>
              <Description term="协议期限(月)" className={styles.info}>{this.state.agreementPeriod1}</Description>
              <Description term="签订单位" className={styles.info}>{this.state.owner1}</Description>
              <Description term="附件" className={styles.info}>
                {this.state.file1 !== undefined &&
                  this.state.file1 !== '' && (
                    <a
                      href={`${serverUrlPre}/system/file/download?filePath=${
                        this.state.file1
                      }&token=${token}`}
                      title={'附件下载'}
                      alt="file"
                    >
                      附件下载
                    </a>
                  )}
              </Description>
              <Description term="描述" className={styles.info}>{this.state.describe1}</Description>
            </DescriptionList>
          </Card>
          <Row gutter={24}>
            <Col>
              <Form.Item>
                {getFieldDecorator('staffId', {
                  initialValue: this.state.staffId,
                })(<Input type="hidden" disabled />)}
              </Form.Item>
              <Form.Item>
                {getFieldDecorator('renewStatus', {
                  initialValue: '1',
                })(<Input type="hidden" disabled />)}
              </Form.Item>
            </Col>
          </Row>
          <Card title={'协议续签'} bordered={false}>
            <Row gutter={24}>
              <Col span={12}>
                <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="协议编号">
                  {getFieldDecorator('agreementNo', {})(
                    <Input placeholder="可手动输入或者不填由系统生成" />
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
          </Card>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({})(ModalRenewForm);
