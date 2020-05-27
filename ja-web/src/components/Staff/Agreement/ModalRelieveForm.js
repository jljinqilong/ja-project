import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Select, DatePicker, Row, Col, Divider, Card } from 'antd';
import { getById, updateAgreement } from '../../../services/agreement';
import DescriptionList from '../../../components/DescriptionList';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';
import moment from 'moment';
import styles from './Agreement.less'

const { Description } = DescriptionList;

class ModalRelieveForm extends PureComponent {
  state = {
    staffId: '',
    signDate: '',
    agreementDateStart: '',
    agreementDateEnd: '',
    relieveDate: moment(new Date()).format('YYYY-MM-DD'),
    filePath: '',
    fileList: [],
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
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      agreementDateEnd: dateString,
    });
  };
  handleDatePickerChangeRelieveDate = (date, dateString) => {
    this.setState({
      relieveDate: dateString,
    });
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentRelieveUserId != nextProps.currentRelieveUserId &&
      nextProps.currentRelieveUserId > 0
    ) {
      getById(nextProps.currentRelieveUserId)
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
                agreementType: data.data[0].transNames.agreementType_name,
              });
            }
          }
          this.setState({
            rowId: data.data[0].rowId,
            staffId: data.data[0].staffId,
            agreementNo: data.data[0].agreementNo,
            relevanceContract: data.data[0].relevanceContract,
            agreementPeriod: data.data[0].agreementPeriod,
            owner: data.data[0].owner,
            file: data.data[0].file,
            describe1: data.data[0].describe,
            signDate: data.data[0].signDate,
            agreementDateStart: data.data[0].agreementDateStart,
            agreementDateEnd: data.data[0].agreementDateEnd,
            fileList: [],
          });
        })
        .catch(e => {
          message.error('查询协议信息失败');
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
      fieldsValue.rowId = this.props.currentRelieveUserId;
      fieldsValue.signDate = this.state.signDate;
      fieldsValue.agreementDateStart = this.state.agreementDateStart;
      fieldsValue.agreementDateEnd = this.state.agreementDateEnd;
      fieldsValue.relieveDate = this.state.relieveDate;
      fieldsValue.file = this.state.filePath;
      fieldsValue.status = 2;
      form.resetFields();
      //业务逻辑写在这里
      updateAgreement(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 2, -1);
            this.props.refreshTable();
          } else {
            message.error('编辑失败：请稍后再试！');
          }
        })
        .catch(e => {
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
        title="解除协议"
        visible={this.props.modalVisibleRelieve}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 2, -1)}
      >
        <Form>
          <Card title={'员工基础信息'} bordered={false}>
            <DescriptionList size="large"col={2} style={{ marginBottom: 32 }}>
              <Description term="工号" className={styles.info}>{this.state.staffNo}</Description>
              <Description term="姓名" className={styles.info}>{this.state.staffName}</Description>
              <Description term="基地" className={styles.info}>{this.state.baseId}</Description>
              <Description term="部门" className={styles.info}>{this.state.deptId}</Description>
              <Description term="职衔" className={styles.info}>{this.state.positionName}</Description>
              <Description term="职级" className={styles.info}>{this.state.rankName}</Description>
              <Description term="职等/赋值名称" className={styles.info}>{this.state.gradeName}</Description>
              <Description term="在职状态" className={styles.info}>{this.state.jobStatus}</Description>
            </DescriptionList>
          </Card>
          <Card title={'协议基础信息'} bordered={false}>
            <DescriptionList size="large"col={2} style={{ marginBottom: 32 }}>
              <Description term="协议编号" className={styles.info}>{this.state.agreementNo}</Description>
              <Description term="关联合同编号" className={styles.info}>{this.state.relevanceContract}</Description>
              <Description term="协议类型" className={styles.info}>{this.state.agreementType}</Description>
              <Description term="签订日期" className={styles.info}>{this.state.signDate}</Description>
              <Description term="协议生效日期" className={styles.info}>{this.state.agreementDateStart}</Description>
              <Description term="协议终止日期" className={styles.info}>{this.state.agreementDateEnd}</Description>
              <Description term="协议期限(月)" className={styles.info}>{this.state.agreementPeriod}</Description>
              <Description term="签订单位" className={styles.info}>{this.state.owner}</Description>
              <Description term="附件" className={styles.info}>
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
                {getFieldDecorator('agreementState', {
                  initialValue: '4',
                })(<Input type="hidden" disabled />)}
              </Form.Item>
            </Col>
          </Row>
          <Divider style={{ marginBottom: 32 }} />
          <div>解除协议信息</div>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 16 }} label="解除日期">
                {getFieldDecorator('relieveDate', {
                  initialValue: moment(this.state.relieveDate),
                  rules: [{ required: true, message: '请选择解除日期' }],
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangeRelieveDate}
                    format="YYYY-MM-DD"
                    style={{ width: 350 }}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="解除原因">
                {getFieldDecorator('relieveReason', {
                  rules: [{ required: true, message: '请输入解除原因' }],
                })(<Input placeholder="请输入解除原因" maxLength="100" />)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="经办人">
                {getFieldDecorator('responsiblePerson', {
                  rules: [{ required: true, message: '请输入经办人' }],
                })(<Input placeholder="请输入经办人" maxLength="20" />)}
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

export default Form.create({})(ModalRelieveForm);
