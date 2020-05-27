import React, { PureComponent } from 'react';
import { Form, Input, Modal, message, Select, DatePicker, Row, Col, Divider, Card } from 'antd';
import { getById, updateContract } from '../../../services/staffContract';
import DescriptionList from '../../../components/DescriptionList';
import { serverUrlPre } from '../../../utils/request';
import { getToken } from '../../../utils/authority';
import moment from 'moment';
import styles from './Contract.less'
const { Description } = DescriptionList;

class ModalEndForm extends PureComponent {
  state = {
    staffId: '',
    signDate: '',
    contractDateStart: '',
    contractDateEnd: '',
    endDate: moment(new Date()).format('YYYY-MM-DD'),
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
      contractDateStart: dateString,
    });
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      contractDateEnd: dateString,
    });
  };
  handleDatePickerChangeTerminateDate = (date, dateString) => {
    this.setState({
      endDate: dateString,
    });
  };

  /**
   * 接收ID，查询详细
   * @param nextProps
   */
  componentWillReceiveProps(nextProps) {
    if (
      this.props.currentTerminateUserId != nextProps.currentTerminateUserId &&
      nextProps.currentTerminateUserId > 0
    ) {
      getById(nextProps.currentTerminateUserId)
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
                contractType: data.data[0].transNames.contractType_name,
                contractPeriodType: data.data[0].transNames.contractPeriodType_name,
                getcontractBackups: data.data[0].transNames.getcontractBackups_name,
                signAgreement: data.data[0].transNames.signAgreement_name,
              });
            }
          }
          this.setState({
            rowId: data.data[0].rowId,
            staffId: data.data[0].staffId,
            contractNo: data.data[0].contractNo,
            contractPeriod: data.data[0].contractPeriod,
            owner: data.data[0].owner,
            file: data.data[0].file,
            describe1: data.data[0].describe,
            signDate: data.data[0].signDate,
            contractDateStart: data.data[0].contractDateStart,
            contractDateEnd: data.data[0].contractDateEnd,
            fileList: [],
          });
        })
        .catch(e => {
          message.error('查询合同信息失败');
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
      fieldsValue.rowId = this.props.currentTerminateUserId;
      fieldsValue.signDate = this.state.signDate;
      fieldsValue.contractDateStart = this.state.contractDateStart;
      fieldsValue.contractDateEnd = this.state.contractDateEnd;
      fieldsValue.endDate = this.state.endDate;
      fieldsValue.file = this.state.filePath;
      fieldsValue.status = 3;
      form.resetFields();
      //业务逻辑写在这里
      updateContract(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.props.handleModalVisible(false, 4, -1);
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
        title="终止合同"
        visible={this.props.modalVisibleTerminate}
        onOk={this.handleSure}
        okText={`保存`}
        width={1200}
        onCancel={() => this.props.handleModalVisible(false, 4, -1)}
      >
        <Form>
          <Card title={'员工基础信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
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
          <Card title={'合同基础信息'} bordered={false}>
            <DescriptionList size="large" col={2} style={{ marginBottom: 32 }}>
              <Description term="合同编号" className={styles.info}>{this.state.contractNo}</Description>
              <Description term="合同类型" className={styles.info}>{this.state.contractType}</Description>
              <Description term="合同期限类型" className={styles.info}>{this.state.contractPeriodType}</Description>
              <Description term="签订日期" className={styles.info}>{this.state.signDate}</Description>
              <Description term="合同生效日期" className={styles.info}>{this.state.contractDateStart}</Description>
              <Description term="合同终止日期" className={styles.info}>{this.state.contractDateEnd}</Description>
              <Description term="合同期限(月)" className={styles.info}>{this.state.contractPeriod}</Description>
              <Description term="甲方" className={styles.info}>{this.state.owner}</Description>
              <Description term="领取合同备份" className={styles.info}>{this.state.getcontractBackups}</Description>
              <Description term="签订相关协议" className={styles.info}>{this.state.signAgreement}</Description>
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
                {getFieldDecorator('contractState', {
                  initialValue: 5,
                })(<Input type="hidden" disabled />)}
                {getFieldDecorator('contractNo', {
                  initialValue: this.state.contractNo,
                })(<Input type="hidden" disabled />)}
              </Form.Item>
            </Col>
          </Row>
          <Divider style={{ marginBottom: 32 }} />
          <div>终止合同信息</div>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 16 }} label="终止日期">
                {getFieldDecorator('endDate', {
                  initialValue: moment(this.state.endDate),
                  rules: [{ required: true, message: '请选择终止日期' }],
                })(
                  <DatePicker
                    onChange={this.handleDatePickerChangeTerminateDate}
                    format="YYYY-MM-DD"
                    style={{ width: 350 }}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="终止原因">
                {getFieldDecorator('endReason', {
                  rules: [{ required: true, message: '请输入终止原因' }],
                })(<Input placeholder="请输入终止原因" maxLength="100" />)}
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

export default Form.create({})(ModalEndForm);
