import React, { PureComponent, Fragment } from 'react';
import { Form, message, Select, DatePicker, Row, Col, Card, Button, Input,Icon, Modal } from 'antd';
import moment from 'moment';
import ModalOrgTreeSelect from '../../Org/Org/ModalOrgTreeSelect';
import {addStaffMove, getAdjustmentBy} from '../../../services/adjustWork';
import { getByTypeCode } from '../../../services/systemCode';
import {baseList, getAllGradeByPosition, getAllPosition, getAllRankByPositionAndGrade, getSonOrgTree, getById} from '../../../services/org';
import {getStaffNoByBaseId} from "../../../services/staffBaseInfo";

class ModalExpatriate extends PureComponent {
  state = {
    staffId: '',
    expatriateStartDate: '',
    expatriateEndDate: '',
    baseId: '',
    yesOrNoList: [],
    treeData: [],
    allBaseList: [],
    temporarilyTypeList: [],
    allPosition: [],
    allGradeByPosition: [],
    allRankByPositionAndGrade: [],
    staffClassifyList:[],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    //员工分类
    getByTypeCode({ typeCode: 'STAFF_CLASSIFY' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          staffClassifyList: content.data,
        });
      }
    });
    //是否加入黑名单
    getByTypeCode({ typeCode: 'YES_OR_NO' }).then(content => {
      if (!!content.data) {
        this.setState({
          yesOrNoList: content.data,
        });
      }
    });
    //外派类型
    getByTypeCode({ typeCode: 'TEMPORARILY_TYPE' }).then(content => {
      if (!!content.data) {
        this.setState({
          temporarilyTypeList: content.data,
        });
      }
    });

    //获取基地列表
    baseList().then(content => {
      if (!!content.data) {
        this.setState({
          allBaseList: content.data,
        });
      }
    });

    //查询所有职衔
    getAllPosition().then(content => {
      if (!!content && !!content.data) {
        this.setState({
          allPosition: content.data,
        });
      }
    });
  }

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeStartDate = (date, dateString) => {
    this.setState({
      expatriateStartDate: dateString,
    });
  };
  handleDatePickerChangeEndDate = (date, dateString) => {
    this.setState({
      expatriateEndDate: dateString,
    });
  };

  handleChangePosition(rowId) {
    const { form } = this.props;
    form.setFieldsValue({
      newGrade: '',
      newRank: '',
    });
    //查询该职衔下所有职等/赋值名称
    getAllGradeByPosition(rowId).then(content => {
      if (!!content && !!content.data) {
        if (rowId !== this.state.rowId) {
          this.setState({
            // gradeId: '',
            // rankId: '',
            allGradeByPosition: content.data,
            positionIdBy: rowId,
          });
        } else {
          this.setState({
            gradeId: 0,
            rankId: 0,
          });
        }
      }
    });
  }

  handleChangeGrade(rowId) {
    const { form } = this.props;
    form.setFieldsValue({
      newRank: '',
    });
    //查询该职衔职等/赋值名称下所有职级
    getAllRankByPositionAndGrade(this.state.positionIdBy, rowId).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          allRankByPositionAndGrade: content.data,
        });
      }
    });
  }

  //选择部门
  handleSetOrg = deptId => {
    if(deptId !== ''){
      getById(deptId).then(data => {
        this.setState({
          deptName: data.data.baseOrDeptName,
        });
      });
    }
    this.setState({ deptId: deptId });
    const { form } = this.props;
    form.setFieldsValue({
      newDept: deptId,
    });
  };

  //选择基地
  handleChange(baseId) {
    getSonOrgTree(baseId).then(data => {
      this.setState({
        baseId: baseId,
        treeData: data.data,
      });
    });
    this.child.onChange('');
  }

  //映射部门下拉树
  onRef = ref => {
    this.child = ref;
  };

  /**
   * 提交
   */
  handleSure = () => {
    let staffId1 = this.props.staffId;
    let changeType = 'TEMPORARILY';
    let changeType1 = 'EXPATRIATE';
    let today = moment(new Date()).format('YYYY-MM-DD');
    const { form,getStaffInfoById,staffId } = this.props;
    getAdjustmentBy(staffId1,changeType).then(data => {
      if(!!data && data.data.length >0) {
        if (!!data.data[0].realEndTime) {
          if (moment(data.data[0].realEndTime).format('YYYY-MM-DD') >= today) {
            message.warn('该员工目前正在借调中，不能再次外派！');
            return;
          }
        } else {
          if (!!data.data[0].temporarilyEndDate) {
            if (moment(data.data[0].temporarilyEndDate).format('YYYY-MM-DD') >= today) {
              message.warn('该员工目前正在借调中，不能再次外派！');
              return;
            }
          }
        }
      }
      getAdjustmentBy(staffId1,changeType1).then(data => {
        if(!!data && data.data.length >0) {
          if (!!data.data[0].realEndTime) {
            if (moment(data.data[0].realEndTime).format('YYYY-MM-DD') >= today) {
              message.warn('该员工目前正在外派中，不能再次外派！');
              return;
            }
          } else {
            if (!!data.data[0].expatriateEndDate) {
              if (moment(data.data[0].expatriateEndDate).format('YYYY-MM-DD') >= today) {
                message.warn('该员工目前正在外派中，不能再次外派！');
                return;
              }
            }
          }
        }
        if(this.props.jobStatus === '在职' || this.props.jobStatus === '待岗') {
          form.validateFields((err, fieldsValue) => {
            if (err) return;

            if (this.state.expatriateStartDate > this.state.expatriateEndDate) {
              message.warn('开始日期不能大于结束日期！');
              return;
            }
            fieldsValue.staffId=this.props.staffId,
            fieldsValue.newDeptId = this.props.newDeptId,
            fieldsValue.originalBase=this.props.originalBase,
            fieldsValue.originalStaffNo = this.props.originalStaffNo,
            fieldsValue.originalDept=this.props.originalDept,
            fieldsValue.originalPosition=this.props.originalPosition,
            fieldsValue.originalGrade=this.props.originalGrade,
            fieldsValue.originalRank=this.props.originalRank,
            fieldsValue.expatriateStartDate = this.state.expatriateStartDate;
            fieldsValue.expatriateEndDate = this.state.expatriateEndDate;
            fieldsValue.currentId = this.state.currentId;
            fieldsValue.changeType = 'EXPATRIATE',
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
                        message.success('外派成功');
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
      });
    });
  };

  /**
   * 生成工号
   */
  getStaffNo = () => {
    const { form } = this.props;
    const values = form.getFieldsValue();
    form.resetFields();
    form.setFieldsValue({
      expatriateType: values.expatriateType,
      newBase: values.newBase,
      newStaffNo: values.newStaffNo,
      newDept: values.newDept,
      staffClassify: values.staffClassify,
      newPosition:values.newPosition,
      newGrade:values.newGrade,
      newRank:values.newRank,
      expatriateStartDate:values.expatriateStartDate,
      expatriateEndDate:values.expatriateEndDate,
      jobContent: values.jobContent,
      changeReason: values.changeReason,
    });
    if (this.state.baseId !== '') {
      getStaffNoByBaseId(this.state.baseId).then(data => {
        if (data.code === 200) {
          form.setFieldsValue({
            newStaffNo: data.data.staffNo,
          });
          this.setState({
            currentId: data.data.currentId,
          });
        }
      });
    } else {
      message.info('请选择基地');
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
        <Card title={'外派信息'} bordered={false} extra={action}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="外派类型">
                {getFieldDecorator('expatriateType', {
                  rules: [{ required: true, message: '请选择外派类型' }],
                })(
                  <Select style={{ width: 300 }}>
                    {this.state.temporarilyTypeList.map(d => (
                      <Select.Option key={d.rowId} value={d.name}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="外派基地">
                {getFieldDecorator('newBase', {
                  rules: [{ required: true, message: '请选择基地' }],
                })(
                  <Select style={{ width: 300 }}>
                    {this.state.allBaseList.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() => this.handleChange(d.rowId)}
                      >
                        {d.baseOrDeptName}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="工号">
                {getFieldDecorator('newStaffNo', {
                  initialValue: this.props.originalStaffNo,
                  rules: [{ required: true, message: '请输入工号' }],
                })(
                  <Input
                    placeholder="请输入"
                    suffix={
                      <Icon
                        type="plus-square"
                        title="自动生成工号"
                        style={{ fontSize: 28 }}
                        onClick={this.getStaffNo}
                      />
                    }
                    maxLength={20}
                    style={{ width: 300 }}/>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="外派部门">
                {getFieldDecorator('newDept', {
                  initialValue: this.state.deptId,
                  rules: [{ required: true, message: '请选择部门' }],
                })(
                  <ModalOrgTreeSelect
                    treeData={this.state.treeData}
                    handleSetOrg={this.handleSetOrg.bind(this)}
                    onRef={this.onRef}
                    deptId={''}
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="员工分类">
                {getFieldDecorator('staffClassify', {
                  rules: [{ required: true, message: '请选择员工分类' }],
                })(
                  <Select style={{ width: 300 }}>
                    {this.state.staffClassifyList.map(d => (
                      <Select.Option key={d.rowId} value={d.code}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="外派职衔">
                {getFieldDecorator('newPosition', {
                  rules: [{ required: true, message: '请选择职衔' }],
                })(
                  <Select
                    placeholder={`可模糊搜索`}
                    showSearch
                    style={{ width: 300 }}>
                    {this.state.allPosition.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() => this.handleChangePosition(d.rowId)}>
                        {d.positionName}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="外派职等/赋值名称">
                {getFieldDecorator('newGrade', {
                  rules: [{ required: true, message: '请选择职等/赋值名称' }],
                })(
                  <Select style={{ width: 300 }} dropdownMatchSelectWidth={true}>
                    {this.state.allGradeByPosition.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() => this.handleChangeGrade(d.rowId)}
                      >
                        {d.gradeName}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="外派职级">
                {getFieldDecorator('newRank', {
                  rules: [{ required: true, message: '请选择职级' }],
                })(
                  <Select style={{ width: 300 }}>
                    {this.state.allRankByPositionAndGrade.map(d => (
                      <Select.Option key={d.rowId} value={d.rowId}>
                        {d.rankName}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="开始时间">
                {getFieldDecorator('expatriateStartDate', {
                  rules: [{ required: true, message: '请选择开始时间' }],
                })(
                  <DatePicker
                    style={{ width: 300 }}
                    onChange={this.handleDatePickerChangeStartDate}
                    format={dateFormat}
                    placeholder="请选择开始时间"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 6 }} wrapperCol={{ span: 17 }} label="结束时间">
                {getFieldDecorator('expatriateEndDate', {
                  rules: [{ required: true, message: '请选择结束时间' }],
                })(
                  <DatePicker
                    style={{ width: 300 }}
                    onChange={this.handleDatePickerChangeEndDate}
                    format={dateFormat}
                    placeholder="请选择结束时间"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item labelCol={{ span: 3 }} wrapperCol={{ span: 17 }} label="工作内容">
                {getFieldDecorator('jobContent', {})(
                  <TextArea autosize={{ minRows: 4, maxRows: 4 }} maxLength={100}/>
                )}
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item labelCol={{ span: 3 }} wrapperCol={{ span: 17 }} label="描述">
                {getFieldDecorator('changeReason', {})(
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

export default Form.create({})(ModalExpatriate);
