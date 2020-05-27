import React, { PureComponent, Fragment } from 'react';
import { Form, message, Select, DatePicker, Row, Col, Card, Button,Input,Icon, Modal } from 'antd';
import moment from 'moment';
import ModalOrgTreeSelect from '../../Org/Org/ModalOrgTreeSelect';
import {addStaffMove, getAdjustmentBy} from '../../../services/adjustWork';
import { getByTypeCode } from '../../../services/systemCode';
import {baseList, getAllGradeByPosition, getAllPosition, getAllRankByPositionAndGrade, getSonOrgTree, getById} from '../../../services/org';
import {getStaffNoByBaseId} from "../../../services/staffBaseInfo";

class ModalInternalTransfer extends PureComponent {
  state = {
    staffId:'',
    changeDate: '',
    baseId: '',
    treeData: [],
    allBaseList: [],
    yesOrNoList: [],
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
            allGradeByPosition: content.data,
            positionIdBy: rowId,
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
  handleDatePickerChangeDate = (date, dateString) => {
    this.setState({
      changeDate: dateString,
    });
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
            message.warn('该员工目前正在借调中，请先结束借调！');
            return;
          }
        } else {
          if (!!data.data[0].temporarilyEndDate) {
            if (moment(data.data[0].temporarilyEndDate).format('YYYY-MM-DD') >= today) {
              message.warn('该员工目前正在借调中，请先结束借调！');
              return;
            }
          }
        }
      }
      getAdjustmentBy(staffId1,changeType1).then(data => {
        if(!!data && data.data.length >0) {
          if (!!data.data[0].realEndTime) {
            if (moment(data.data[0].realEndTime).format('YYYY-MM-DD') >= today) {
              message.warn('该员工目前正在外派中，请先结束外派！');
              return;
            }
          } else {
            if (!!data.data[0].expatriateEndDate) {
              if (moment(data.data[0].expatriateEndDate).format('YYYY-MM-DD') >= today) {
                message.warn('该员工目前正在外派中，请先结束外派！');
                return;
              }
            }
          }
        }
        if(this.props.jobStatus === '在职' || this.props.jobStatus === '待岗') {
          form.validateFields((err, fieldsValue) => {
            if (err) return;

            fieldsValue.staffId = this.props.staffId,
            fieldsValue.newDeptId = fieldsValue.newDept,
            fieldsValue.originalBase = this.props.originalBase,
            fieldsValue.originalStaffNo = this.props.originalStaffNo,
            fieldsValue.originalDept = this.props.originalDept,
            fieldsValue.originalPosition = this.props.originalPosition,
            fieldsValue.originalGrade = this.props.originalGrade,
            fieldsValue.originalRank = this.props.originalRank,
            fieldsValue.changeDate = this.state.changeDate;
            fieldsValue.currentId = this.state.currentId;
            fieldsValue.changeType = 'INNER_MOBILIZATION',
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
                        message.success('内部调动成功');
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
          message.warning('该员工已'+this.props.jobStatus);
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
      newBase: values.newBase,
      newStaffNo: values.newStaffNo,
      newDept: values.newDept,
      staffClassify: values.staffClassify,
      newPosition:values.newPosition,
      changeDate:values.changeDate,
      isWageAdjustment:values.isWageAdjustment,
      newGrade:values.newGrade,
      newRank:values.newRank,
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
        <Card title={'内部调动信息'} bordered={false} extra={action}>
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
                    style={{ width: 300 }}
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
                    style={{ width: 300 }}>
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
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="内部调动时间">
                {getFieldDecorator('changeDate', {
                  rules: [{ required: true, message: '请选择内部调动时间' }],
                })(
                  <DatePicker
                    style={{ width: 300 }}
                    onChange={this.handleDatePickerChangeDate}
                    format={dateFormat}
                    placeholder="请选择内部调动时间"
                  />
                )}
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="是否调薪">
                {getFieldDecorator('isWageAdjustment', {
                  rules: [{ required: true, message: '是否调薪' }],
                })(
                  <Select style={{ width: 300 }}>
                    {this.state.yesOrNoList.map(d => (
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
      </Fragment>
    );
  }
}

export default Form.create({})(ModalInternalTransfer);
