import React, { PureComponent, Fragment } from 'react';
import { Form, message, Select, DatePicker, Row, Col, Card, Button, Input,Icon, Modal } from 'antd';
import moment from 'moment';
import ModalOrgTreeSelect from '../../Org/Org/ModalOrgTreeSelect';
import {addStaffMove, getAdjustmentWorkList} from '../../../services/adjustWork';
import { getByTypeCode } from '../../../services/systemCode';
import {baseList, getAllGradeByPosition, getAllPosition, getAllRankByPositionAndGrade, getSonOrgTree, getById} from '../../../services/org';
import {getStaffNoByBaseId} from "../../../services/staffBaseInfo";

class ModalRehire extends PureComponent {
  state = {
    staffId: '',
    workDate: '',
    baseId: '',
    treeData: [],
    allBaseList: [],
    yesOrNoList: [],
    allPosition: [],
    allGradeByPosition: [],
    allRankByPositionAndGrade: [],
    isOnJobList: [],
    staffClassifyList:[],
  };

  /**
   * 初始化
   */
  componentDidMount() {
    let staffId = this.props.staffId;
    let changeType = 'DIMISSION'
    getAdjustmentWorkList(staffId,changeType).then(data => {
      if(!!data) {
        this.setState({
          leaveDate: moment(data.data.leaveDate).format('YYYY-MM-DD'),
        });
      }
    });
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

    //是否在职
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      if (!!content.data) {
        this.setState({
          isOnJobList: content.data,
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
   * 日期选择
   * @param type
   */
  handleDatePickerChangeWorkDate = (date, dateString) => {
    this.setState({
      workDate: dateString,
    });
  };

  /**
   * 提交
   */
  handleSure = () => {
    const { form,getStaffInfoById,staffId } = this.props;
    if(this.props.jobStatus === '离职') {
      form.validateFields((err, fieldsValue) => {
        if (err) return;
        if (this.state.workDate < this.state.leaveDate) {
          message.warn('用工日期不能小于离职日期！离职日期为:'+this.state.leaveDate);
          return;
        }
        fieldsValue.staffId = this.props.staffId,
        fieldsValue.newDeptId = fieldsValue.newDept,
        fieldsValue.originalBase = this.props.originalBase,
        fieldsValue.originalStaffNo = this.props.originalStaffNo,
        fieldsValue.originalDept = this.props.originalDept,
        fieldsValue.originalPosition = this.props.originalPosition,
        fieldsValue.originalGrade = this.props.originalGrade,
        fieldsValue.originalRank = this.props.originalRank,
        fieldsValue.workDate = this.state.workDate;
        fieldsValue.currentId = this.state.currentId;
        fieldsValue.changeType = 'REHIRE',
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
                    message.success('重新雇佣成功');
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
      message.warning('该员工已'+ this.props.jobStatus)
    }
  };

  /**
   * 生成工号
   */
  getStaffNo = () => {
    const { form } = this.props;
    const values = form.getFieldsValue();
    form.resetFields();
    form.setFieldsValue({
      newBase: values.newBase,
      newStaffNo: values.newStaffNo,
      newDept: values.newDept,
      staffClassify: values.staffClassify,
      newPosition:values.newPosition,
      newGrade:values.newGrade,
      newRank:values.newRank,
      jobStatus:values.jobStatus,
      workDate: values.workDate,
      entryBeforeAge: values.entryBeforeAge,
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
        <Card title={'重新雇佣信息'} bordered={false} extra={action}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="基地">
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工号">
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门">
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工分类">
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职衔">
                {getFieldDecorator('newPosition', {
                  rules: [{ required: true, message: '请选择职衔' }],
                })(
                  <Select
                    placeholder={`可模糊搜索`}
                    showSearch
                    style={{ width: 300}}>
                    {this.state.allPosition.map(d => (
                      <Select.Option
                        key={d.rowId}
                        value={d.rowId}
                        onClick={() => this.handleChangePosition(d.rowId)}
                      >
                        {d.positionName}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="在职状态">
                {getFieldDecorator('jobStatus', {
                  initialValue:'在职',
                  rules: [{ required: true, message: '请选择在职状态' }],
                })(
                  <Select disabled style={{ width: 300 }}>
                    {this.state.isOnJobList.map(d => (
                      <Select.Option key={d.rowId} value={d.name}>
                        {d.name}
                      </Select.Option>
                    ))}
                  </Select>
                )}
              </Form.Item>
            </Col>
          </Row>
        </Card>
        <Card title={'职级信息'} bordered={false}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职等/赋值名称">
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职级">
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
          </Row>
        </Card>
        <Card title={'分类信息'} bordered={false}>
          <Row gutter={24}>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="用工日期">
                {getFieldDecorator('workDate', {
                  rules: [{ required: true, message: '请选择用工日期' }],
                })(
                  <DatePicker
                    style={{ width: 300 }}
                    onChange={this.handleDatePickerChangeWorkDate}
                    format={dateFormat}
                    placeholder="请选择用工日期"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="入职前工龄">
                {getFieldDecorator('entryBeforeAge', {
                  rules: [{ required: true, message: '请输入入职前工龄' }],
                })(<Input style={{ width: 300 }} />)}
              </Form.Item>
            </Col>
          </Row>
        </Card>
      </Fragment>
    );
  }
}

export default Form.create({})(ModalRehire);
