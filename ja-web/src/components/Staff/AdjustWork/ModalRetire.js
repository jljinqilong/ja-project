import React, { PureComponent, Fragment } from 'react';
import { Form, message, Select, DatePicker, Row, Col, Card, Button, Input, Modal } from 'antd';
import { addStaffMove } from '../../../services/adjustWork';
import { getByTypeCode } from '../../../services/systemCode';

class ModalRetire extends PureComponent {
  state = {
    staffId: '',
    retireDate: '',
    yesOrNoList: [],
    retireTypeList: [],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    //是否加入黑名单
    getByTypeCode({ typeCode: 'YES_OR_NO' }).then(content => {
      if (!!content.data) {
        this.setState({
          yesOrNoList: content.data,
        });
      }
    });
    //退休类型
    getByTypeCode({ typeCode: 'RETIRE_TYPE' }).then(content => {
      if (!!content.data) {
        this.setState({
          retireTypeList: content.data,
        });
      }
    });
  }

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeRetireDate = (date, dateString) => {
    this.setState({
      retireDate: dateString,
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
        fieldsValue.retireDate = this.state.retireDate;
        fieldsValue.changeType = 'RETIRE',
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
                    message.success('退休成功');
                    form.resetFields();
                    getStaffInfoById(staffId);
                  }
                }
              })
            },
            onCancel() {},
          });
      });
    }else{
      message.warning('该员工已'+ this.props.jobStatus);
    }
  };

  render() {
    const { TextArea } = Input;
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
        <Card title={'退休信息'} bordered={false} extra={action}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="退休类型">
                {getFieldDecorator('retireType', {
                  rules: [{ required: true, message: '请选择退休类型' }],
                })(
                  <Select style={{ width: 300 }}>
                    {this.state.retireTypeList.map(d => (
                      <Select.Option key={d.rowId} value={d.name}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="退休时间">
                {getFieldDecorator('retireDate', {
                  rules: [{ required: true, message: '请选择开始时间' }],
                })(
                  <DatePicker
                    style={{ width: 300 }}
                    onChange={this.handleDatePickerChangeRetireDate}
                    format={dateFormat}
                    placeholder="请选择开始时间"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="退休后管理单位">
                {getFieldDecorator('retireManagementUnit', {
                  rules: [{ required: true, message: '请输入退休后管理单位' }],
                })(<Input style={{ width: 300 }} maxLength={30}/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="审批单位">
                {getFieldDecorator('examineUnit', {
                  rules: [{ required: true, message: '请输入审批单位' }],
                })(<Input style={{ width: 300 }} maxLength={30}/>)}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="批准工号">
                {getFieldDecorator('approvalNumber', {
                  rules: [{ required: true, message: '请输入批准工号' }],
                })(<Input style={{ width: 300 }} maxLength={30}/>)}
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item labelCol={{ span: 3 }} wrapperCol={{ span: 17 }} label="其他说明">
                {getFieldDecorator('otherExplain', {})(
                  <TextArea autosize={{ minRows: 4, maxRows: 4 }} maxLength={100}/>
                )}
              </Form.Item>
            </Col>
          </Row>
        </Card>
      </Fragment>
    );
  }
}

export default Form.create({})(ModalRetire);
