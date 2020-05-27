import React, { PureComponent, Fragment } from 'react';
import { Form, message, Select, DatePicker, Row, Col, Card, Button, Input, Modal } from 'antd';
import { addStaffMove } from '../../../services/adjustWork';
import { getByTypeCode } from '../../../services/systemCode';

class ModalDimission extends PureComponent {
  state = {
    staffId: '',
    leaveDate: '',
    startPerformDate:'',
    endPerformDate:'',
    yesOrNoList: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    //是否加入黑名单
    getByTypeCode({ typeCode: 'YES_OR_NO' }).then(content => {
      this.state.yesOrNoList = content.data;
    });
  }

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeLeaveDate = (date, dateString) => {
    this.setState({
      leaveDate: dateString,
    });
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      startPerformDate: dateString,
    });
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      endPerformDate: dateString,
    });
  };

  handleChangeLeaveType = leaveType => {
    this.setState({
      leaveType: leaveType,
    });
  };

  handleChangeIsBlacklist = (isBlack) => {
    this.setState({
      isBlackState: isBlack,
    });
  };

  handleChangeIsCompensatoryPayment = (isCompensatoryPayment) => {
    this.setState({
      isCompensatoryPaymentState: isCompensatoryPayment,
    });
  };

  handleChangeIsPerform = (isPerform) => {
    this.setState({
      isPerformState: isPerform,
    });
  };

  /**
   * 提交
   */
  handleSure = () => {
    const { form,getStaffInfoById,staffId } = this.props;
    if(this.props.jobStatus === '在职' || this.props.jobStatus === '待岗') {
      form.validateFields((err, fieldsValue) => {
        if (err) return;
        if (this.state.startPerformDate > this.state.endPerformDate) {
          message.warn('开始日期不能大于结束日期！');
          return;
        }
        fieldsValue.staffId = this.props.staffId,
        fieldsValue.newDeptId = this.props.newDeptId,
        fieldsValue.originalBase = this.props.originalBase,
        fieldsValue.originalStaffNo = this.props.originalStaffNo,
        fieldsValue.originalDept = this.props.originalDept,
        fieldsValue.originalPosition = this.props.originalPosition,
        fieldsValue.originalGrade = this.props.originalGrade,
        fieldsValue.originalRank = this.props.originalRank,
        fieldsValue.newBase = this.props.originalBase,
        fieldsValue.newStaffNo = this.props.originalStaffNo,
        fieldsValue.newDept = this.props.originalDept,
        fieldsValue.newPosition = this.props.originalPosition,
        fieldsValue.newGrade = this.props.originalGrade,
        fieldsValue.newRank = this.props.originalRank,
        fieldsValue.leaveDate = this.state.leaveDate;
        fieldsValue.startPerformDate = this.state.startPerformDate;
        fieldsValue.endPerformDate = this.state.endPerformDate;
        fieldsValue.changeType = 'DIMISSION',
          Modal.confirm({
            title: '添加确认',
            content: '确定添加此条记录吗？',
            okText: '确定',
            okType: 'danger',
            cancelText: '取消',
            onOk() {
              addStaffMove(fieldsValue).then(data => {
                if (!!data) {
                  if (data.code === 200) {
                    message.success('离职异动成功');
                    form.resetFields();
                    getStaffInfoById(staffId);
                  }
                }
              })
            },
            onCancel() {},
          });
      });
    }else {
      message.warning('该员工已'+ this.props.jobStatus);
    }
  };

  handleChangeMonth = num => {
    const { form } = this.props;
    let salsry = num.target.value;
    if (salsry !== '') {
      salsry = salsry.replace(/[^\d]/g, ''); //清除“数字”
      if (salsry.indexOf('.') < 0 && salsry != '') {
        //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        salsry = parseFloat(salsry);
      }
    }
    form.setFieldsValue({
      compensationMonth: salsry,
    });
  };

  handleChangeNum = num => {
    const { form } = this.props;
    let salsry = num.target.value;
    if (salsry !== '') {
      salsry = salsry.replace(/[^\d.]/g, ''); //清除“数字”和“.”以外的字符
      salsry = salsry.replace(/\.{2,}/g, '.'); //只保留第一个. 清除多余的
      salsry = salsry
        .replace('.', '$#$')
        .replace(/\./g, '')
        .replace('$#$', '.');
      salsry = salsry.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
      if (salsry.indexOf('.') < 0 && salsry != '') {
        //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        salsry = parseFloat(salsry);
      }
    }
    form.setFieldsValue({
      amountCompensation: salsry,
    });
  };


  render() {
    const {
      form: { getFieldDecorator },
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';

    const action = (
      <Fragment>
        <Button type="primary" onClick={this.handleSure}>
          提交
        </Button>
      </Fragment>
    );

    return (
      <Fragment>
        <Card title={'离职信息'} bordered={false} extra={action}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职类型">
                {getFieldDecorator('leaveType', {
                  rules: [{ required: true, message: '请选择离职类型' }],
                })(
                  <Select style={{ width: 300 }} onChange={this.handleChangeLeaveType}>
                    <Select.Option key={1} value="主动离职">
                      主动离职
                    </Select.Option>
                    <Select.Option key={2} value="公司辞退">
                      公司辞退
                    </Select.Option>
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              {this.state.leaveType === undefined && (
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职原因">
                  {getFieldDecorator('leaveReason', {
                    rules: [{ required: true, message: '请选择离职类型' }],
                  })(<Select style={{ width: 300 }} />)}
                </Form.Item>
              )}
              {this.state.leaveType === '主动离职' && (
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职原因">
                  {getFieldDecorator('leaveReason', {
                    rules: [{ required: true, message: '请选择离职类型' }],
                  })(
                    <Select style={{ width: 300 }}>
                      <Select.Option key={1} value="不适应工作环境">不适应工作环境</Select.Option>
                      <Select.Option key={2} value="健康原因">健康原因</Select.Option>
                      <Select.Option key={3} value="工作量/工作压力">工作量/工作压力</Select.Option>
                      <Select.Option key={4} value="个人/家庭原因">个人/家庭原因</Select.Option>
                      <Select.Option key={5} value="薪酬待遇">薪酬待遇</Select.Option>
                      <Select.Option key={6} value="个人发展空间">个人发展空间</Select.Option>
                      <Select.Option key={7} value="内部调动">内部调动</Select.Option>
                    </Select>
                  )}
                </Form.Item>
              )}
              {this.state.leaveType === '公司辞退' && (
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职原因">
                  {getFieldDecorator('leaveReason', {
                    rules: [{ required: true, message: '请选择离职类型' }],
                  })(
                    <Select style={{ width: 300 }}>
                      <Select.Option key={1} value="不符合岗位要求">不符合岗位要求</Select.Option>
                      <Select.Option key={2} value="违纪辞退">违纪辞退</Select.Option>
                      <Select.Option key={3} value="其他辞退原因">其他辞退原因</Select.Option>
                    </Select>
                  )}
                </Form.Item>
              )}
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职日期">
                {getFieldDecorator('leaveDate', {
                  rules: [{ required: true, message: '请选择离职日期' }],
                })(
                  <DatePicker
                    style={{ width: 300 }}
                    onChange={this.handleDatePickerChangeLeaveDate}
                    format={dateFormat}
                    placeholder="请选择离职日期..."
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职后去向">
                {getFieldDecorator('leaveDirection', {
                  rules: [{ required: true, message: '请输入离职后去向' }],
                })(<Input style={{ width: 300 }} maxLength={30}/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否加入黑名单">
                {getFieldDecorator('isBlacklist', {
                  rules: [{ required: true, message: '请选择是否加入黑名单' }],
                })(
                  <Select style={{ width: 300 }} onChange={this.handleChangeIsBlacklist}>
                    <Select.Option key={1} value='是'>是</Select.Option>
                    <Select.Option key={2} value='否'>否</Select.Option>
                  </Select>
                )}
              </Form.Item>
            </Col>
            {this.state.isBlackState === '是' && (
              <Col span={12}>
                <Form.Item labelCol={{ span:7 }} wrapperCol={{ span: 17 }} label="加入黑名单原因">
                  {getFieldDecorator('blacklistReason', {
                    rules: [{ required: true, message: '请输入加入黑名单原因' }],
                  })(<Input style={{ width: 300 }} maxLength={50}/>)}
                </Form.Item>
              </Col>
            )}
          </Row>
        </Card>
        <Card title={'离职补偿信息'} bordered={false}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否有代通知金">
                {getFieldDecorator('isLieuNoticeWages', {
                  rules: [{ required: true, message: '是否有代通知金' }],
                })(
                  <Select style={{ width: 300 }}>
                    <Select.Option key={1} value="是">
                      是
                    </Select.Option>
                    <Select.Option key={2} value="否">
                      否
                    </Select.Option>
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否有补偿金">
                {getFieldDecorator('isCompensatoryPayment', {
                  rules: [{ required: true, message: '是否有补偿金' }],
                })(
                  <Select style={{ width: 300 }} onChange={this.handleChangeIsCompensatoryPayment}>
                    <Select.Option key={1} value='是'>是</Select.Option>
                    <Select.Option key={2} value='否'>否</Select.Option>
                  </Select>
                )}
              </Form.Item>
            </Col>
            {this.state.isCompensatoryPaymentState === '是' && (
              <Col span={12}>
                <Form.Item labelCol={{ span:7 }} wrapperCol={{ span: 17 }} label="补偿月数">
                  {getFieldDecorator('compensationMonth', {
                    rules: [{ required: true, message: '请输入补偿月数' }],
                  })(<Input
                     style={{ width: 300 }}
                     maxLength={2}
                     onKeyUp={this.handleChangeMonth.bind(this)}
                  />)}
                </Form.Item>
              </Col>
            )}
            {this.state.isCompensatoryPaymentState === '是' && (
              <Col span={12}>
                <Form.Item labelCol={{ span:7 }} wrapperCol={{ span: 17 }} label="补偿金额">
                  {getFieldDecorator('amountCompensation', {
                    rules: [{ required: true, message: '请输入补偿金额' }],
                  })(<Input
                    style={{ width: 300 }}
                    maxLength={10}
                    onKeyUp={this.handleChangeNum.bind(this)}/>)}
                </Form.Item>
              </Col>
            )}
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否履行竞业限制">
                {getFieldDecorator('isPerformCompetitiveRestriction', {
                  rules: [{ required: true, message: '是否履行竞业限制' }],
                })(
                  <Select style={{ width: 300 }} onChange={this.handleChangeIsPerform}>
                    <Select.Option key={1} value='是'>是</Select.Option>
                    <Select.Option key={2} value='否'>否</Select.Option>
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否签订培训协议">
                {getFieldDecorator('isTrainAgreement', {
                  rules: [{ required: true, message: '是否签订培训协议' }],
                })(
                  <Select style={{ width: 300 }}>
                    <Select.Option key={1} value="是">
                      是
                    </Select.Option>
                    <Select.Option key={2} value="否">
                      否
                    </Select.Option>
                  </Select>
                )}
              </Form.Item>
            </Col>
            {this.state.isPerformState === '是' && (
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="开始履行时间">
                  {getFieldDecorator('startPerformDate', {
                    rules: [{ required: true, message: '请选择开始履行时间' }],
                  })(
                    <DatePicker style={{ width: 300 }}
                                onChange={this.handleDatePickerChangeStartDate}
                                format={dateFormat}
                                placeholder="请选择开始履行时间"
                    />
                  )}
                </Form.Item>
              </Col>
            )}
            {this.state.isPerformState === '是' && (
              <Col span={12}>
                <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="结束履行时间">
                  {getFieldDecorator('endPerformDate', {
                    rules: [{ required: true, message: '请选择结束履行时间' }],
                  })(
                    <DatePicker style={{ width: 300 }}
                                onChange={this.handleDatePickerChangeEndDate}
                                format={dateFormat}
                                placeholder="请选择结束履行时间"
                    />
                  )}
                </Form.Item>
              </Col>
            )}
            <Col span={12}>
              <Form.Item
                labelCol={{ span: 7 }}
                wrapperCol={{ span: 17 }}
                label="未满服务期赔偿金额"
              >
                {getFieldDecorator('underServiceCompensate', {
                  rules: [{ required: true, message: '请输入未满服务期赔偿金额' }],
                })(<Input style={{ width: 300 }} maxLength={10}/>)}
              </Form.Item>
            </Col>
          </Row>
        </Card>
      </Fragment>
    );
  }
}

export default Form.create({})(ModalDimission);
