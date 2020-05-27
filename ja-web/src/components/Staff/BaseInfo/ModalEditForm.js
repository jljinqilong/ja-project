import React, { PureComponent, Fragment } from 'react';
import { Select, Form, Input, Row, Col, message, Divider, DatePicker, Button, Card, Radio } from 'antd';
import { getById, editStaff, getCountByIdNo } from '../../../services/staffBaseInfo';
import { getByTypeCode } from '../../../services/systemCode';
import OuterExperienceEditableTable from './OuterExperience/OuterExperienceEditableTable';
import EducationEditableTable from './Education/EducationEditableTable';
import AddressEditableTable from './Address/AddressEditableTable';
import ContactEmergencyEditableTable from './ContactEmergency/ContactEmergencyEditableTable';
import RelationshipInnerEditableTable from './RelationshipInner/RelationshipInnerEditableTable';
import RelationshipSocialEditableTable from './RelationshipSocial/RelationshipSocialEditableTable';
import ProjectExperienceTable from './ProjectExperience/ProjectExperienceTable';
import ModalOrgTreeSelect from '../../Org/Org/ModalOrgTreeSelect';
import moment from 'moment';
import styles from './BasicForm.less';
import DescriptionList from '../../../components/DescriptionList';
import {
  baseList,
  deptList,
  getSonOrgTree,
  getAllPosition,
  getAllGradeByPosition,
  getAllRankByPositionAndGrade,
} from '../../../services/org';
import {hasAccessKey} from "../../../utils/authority";
const { Description } = DescriptionList;

class ModalEditForm extends PureComponent {
  state = {
    editing: false,
    rowIdList: '',
    fertilityStatusList: [],
    yesOrNoList: [],
    maleOrFemaleList: [],
    identityTypeList: [],
    maritalStatusList: [],
    politicalStatusList: [],
    staffTypeList: [],
    socialSecurityTypeList: [],
    costCenterList: [],
    typeOfLaborList: [],
    allBaseList: [],
    deptId: '',
    age: 0,
    workingLife: 0,
    workingYears: 0,
    treeData: [],
    allPosition: [],
    allGradeByPosition: [],
    allRankByPositionAndGrade: [],
    factoryCategoryList:[],
    recruitmentChannelList:[],
    gradeId: '',
    rankId: '',
    isShowing: true,
  };

  /**
   * 接收ID，查询详细
   */
  componentDidMount() {
    //线别
    getByTypeCode({ typeCode: 'LINES' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          linesList: content.data,
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
    //工作岗位
    getByTypeCode({ typeCode: 'OPERATING_POST' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          operatingPostList: content.data,
        });
      }
    });
    //厂别
    getByTypeCode({ typeCode: 'FACTORY_CATEGORY' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          factoryCategoryList: content.data,
        });
      }
    });
    //招聘渠道
    getByTypeCode({ typeCode: 'RECRUITMENT_CHANNEL' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          recruitmentChannelList: content.data,
        });
      }
    });
    //生育状况
    getByTypeCode({ typeCode: 'FERTILITY_STATUS' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          fertilityStatusList: content.data,
        });
      }
    });
    //是否
    getByTypeCode({ typeCode: 'YES_OR_NO' }).then(content => {
      if (!!content && !!content.data) {
        this.state.yesOrNoList = content.data;
      }
    });
    //男女
    getByTypeCode({ typeCode: 'MALE_OR_FEMALE' }).then(content => {
      if (!!content && !!content.data) {
        this.state.maleOrFemaleList = content.data;
      }
    });
    //证件类型
    getByTypeCode({ typeCode: 'IDENTITY_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.state.identityTypeList = content.data;
      }
    });
    //国籍
    getByTypeCode({ typeCode: 'NATIONALITY' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          nationalityList: content.data,
        });
      }
    });
    //民族
    getByTypeCode({ typeCode: 'NATION' }).then(content => {
      if (!!content && !!content.data) {
        this.setState({
          nationList: content.data,
        });
      }
    });
    //婚姻状况
    getByTypeCode({ typeCode: 'MARITAL_STATUS' }).then(content => {
      if (!!content && !!content.data) {
        this.state.maritalStatusList = content.data;
      }
    });
    //政治面貌
    getByTypeCode({ typeCode: 'POLITICAL_STATUS' }).then(content => {
      if (!!content && !!content.data) {
        this.state.politicalStatusList = content.data;
      }
    });
    //员工类型
    getByTypeCode({ typeCode: 'STAFF_TYPE' }).then(content => {
      if (!!content && !!content.data) {
        this.state.staffTypeList = content.data;
      }
    });
    //社保类型
    getByTypeCode({ typeCode: 'SOCIAL_SECURITY_TYPE' }).then(content => {
      this.state.socialSecurityTypeList = content.data;
    });
    //用工类型
    getByTypeCode({ typeCode: 'TYPE_OF_LABOR' }).then(content => {
      this.state.typeOfLaborList = content.data;
    });
    //是否在职
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      this.state.isOnJobList = content.data;
    });
    //获取基地
    baseList().then(content => {
      this.setState({
        allBaseList: content.data,
      });
    });
    if (this.props.rowId > 0) {
      this.getStaffInfoById(this.props.rowId);
    }
  }

  /**
   * 查询用户信息
   */
  getStaffInfoById = staffId => {
    getById(staffId)
      .then(data => {
        this.setState({
          rowId: data.data.rowId,
          staffNo: data.data.staffNo,
          staffName: data.data.staffName,
          sex: data.data.sex,
          sex_name: data.data.transNames.sex_name,
          baseId: data.data.baseId,
          baseId_baseOrDeptName: data.data.transNames.baseId_baseOrDeptName,
          deptId: data.data.deptId,
          deptId_baseOrDeptName: data.data.transNames.deptId_baseOrDeptName,
          positionId: data.data.positionId,
          rankId: data.data.rankId,
          gradeId: data.data.gradeId,
          positionName: data.data.transNames.positionId_positionName,
          rankName: data.data.transNames.rankId_rankName,
          gradeName: data.data.transNames.gradeId_gradeName,
          identityTypeId: data.data.identityTypeId,
          identityTypeId_name: data.data.transNames.identityTypeId_name,
          identityNo: data.data.identityNo,
          entryTime: data.data.entryTime,
          identityValidDate: data.data.identityValidDate,
          age: moment().diff(moment(data.data.birthdate).format('YYYY-MM-DD'), 'years'),
          birthdate: data.data.birthdate,
          nationality: data.data.nationality,
          nationality_name: data.data.transNames.nationality_name,
          nation: data.data.nation,
          nation_name: data.data.transNames.nation_name,
          registeredResidence: data.data.registeredResidence,
          nativePlace: data.data.nativePlace,
          fertilityStatus: data.data.fertilityStatus,
          fertilityStatus_name: data.data.transNames.fertilityStatus_name,
          maritalStatus: data.data.maritalStatus,
          maritalStatus_name: data.data.transNames.maritalStatus_name,
          politicalStatus: data.data.politicalStatus,
          politicalStatus_name: data.data.transNames.politicalStatus_name,
          firstWorkingTime: data.data.firstWorkingTime,
          workingLife: moment().diff(
            moment(data.data.firstWorkingTime).format('YYYY-MM-DD'),
            'years'
          ),
          staffType: data.data.staffType,
          staffType_name: data.data.transNames.staffType_name,
          socialSecurityType: data.data.socialSecurityType,
          socialSecurityType_name: data.data.transNames.socialSecurityType_name,
          costCenter: data.data.costCenter,
          costCenter_baseOrDeptName: data.data.transNames.costCenter_baseOrDeptName,
          officePlace: data.data.officePlace,
          entryDate: data.data.entryDate,
          workingYears: moment().diff(moment(data.data.entryDate).format('YYYY-MM-DD'), 'years'),
          workType: data.data.workType,
          workType_name: data.data.transNames.workType_name,
          jobStatus: data.data.jobStatus,
          jobStatus_name: data.data.transNames.jobStatus_name,
          leaveDate: data.data.leaveDate,
          isBlacklist: data.data.isBlacklist,
          isBlacklist_name: data.data.transNames.isBlacklist_name,
          remark: data.data.remark,
          isOnJobListDefaultId: data.data.jobStatus,
          lines: data.data.lines,
          lines_name: data.data.transNames.lines_name,
          staffClassify: data.data.staffClassify,
          staffClassify_name: data.data.transNames.staffClassify_name,
          operatingPost: data.data.operatingPost,
          operatingPost_name: data.data.transNames.operatingPost_name,
          mobile: data.data.mobile,
          email: data.data.email,
          factoryCategory:data.data.factoryCategory,
          factoryCategory_name:data.data.transNames.factoryCategory_name,
          classes:data.data.classes,
          recruitmentChannel: data.data.recruitmentChannel,
          recruitmentChannel_name: data.data.transNames.recruitmentChannel_name,
          workCard: data.data.workCard,
          dormitoryNo: data.data.dormitoryNo,
          lockerShoebox: data.data.lockerShoebox,
          speciality: data.data.speciality,
          jobTitle: data.data.jobTitle,
          lunarSolarCalendar: data.data.lunarSolarCalendar,
        });
        this.handleChangeGetCount(this.state.baseId, this.state.identityNo);

        //查询所有职衔
        getAllPosition().then(content => {
          if (!!content && !!content.data) {
            this.setState({
              allPosition: content.data,
            });
          }
        });

        //查询该职衔下所有职等/赋值名称
        if(!!this.state.positionId){
          getAllGradeByPosition(this.state.positionId).then(content => {
            if (!!content && !!content.data) {
              this.setState({
                allGradeByPosition: content.data,
              });
            }
          });
        }

        //查询该职衔职等/赋值名称下所有职级
        if(!!this.state.positionId && !!this.state.gradeId){
          getAllRankByPositionAndGrade(this.state.positionId, this.state.gradeId).then(content => {
            if (!!content && !!content.data) {
              this.setState({
                allRankByPositionAndGrade: content.data,
              });
            }
          });
        }
      });
  };

  handleChangePosition(rowId) {
    this.setState({
      positionId:rowId,
    })
    const { form } = this.props;
    form.setFieldsValue({
      gradeId: '',
      rankId: '',
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
      rankId: '',
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

  handleChangeOperatingPost(rowId) {
    this.setState({
      operatingPost: rowId,
    })
  }
  handleChangeNationality(rowId) {
    this.setState({
      nationality: rowId,
    })
  }
  handleChangeNation(rowId) {
    this.setState({
      nation: rowId,
    })
  }

  /**
   * 确定事件
   */
  handleSure = () => {
    const { form } = this.props;
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      let myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
      if (!myreg.test(fieldsValue.mobile)) {
        message.warn('请输入正确的手机号！');
        return;
      }
      let today = moment(new Date()).format('YYYY-MM-DD');
      if (this.state.birthdate >= today) {
        message.warn('出生日期不能大于等于当前日期！');
        return;
      }
      if (this.state.firstWorkingTime >= today) {
        message.warn('首次工作时间不能大于等于当前日期！');
        return;
      }
      if(!!this.state.nationality) {
        fieldsValue.nationality = this.state.nationality;
      }else{
        {this.state.nationalityList.map(d => (
          d.name  === fieldsValue.nationality && (
            fieldsValue.nationality = d.rowId
          )
        ))}
      }
      if(!!this.state.nation) {
        fieldsValue.nation = this.state.nation;
      }else{
        {this.state.nationList.map(d => (
          d.name  === fieldsValue.nation && (
            fieldsValue.nation = d.rowId
          )
        ))}
      }
      if(!!this.state.operatingPost) {
        fieldsValue.operatingPost = this.state.operatingPost;
      }else{
        {this.state.operatingPostList.map(d => (
          d.name  === fieldsValue.operatingPost && (
            fieldsValue.operatingPost = d.rowId
          )
        ))}
      }
      if(!!this.state.positionId) {
        fieldsValue.positionId = this.state.positionId;
      }else{
        {this.state.allPosition.map(d => (
          d.name  === fieldsValue.positionId && (
            fieldsValue.positionId = d.rowId
          )
        ))}
      }
      fieldsValue.rowId = this.props.rowId;
      fieldsValue.identityValidDate = this.state.identityValidDate;
      fieldsValue.birthdate = this.state.birthdate;
      fieldsValue.firstWorkingTime = this.state.firstWorkingTime;
      fieldsValue.entryDate = this.state.entryDate;
      fieldsValue.leaveDate = this.state.leaveDate;
      //业务逻辑写在这里
      editStaff(fieldsValue)
        .then(data => {
          if (data.code === 200) {
            message.success('编辑成功');
            this.setState({ editing: false });
            this.getStaffInfoById(this.props.rowId);
            form.getFieldsValue();
          }
        })
        .catch(() => {
          message.error('编辑失败：请联系管理员!');
        });
    });
  };

  /**
   * 日期选择
   * @param type
   */
  handleDatePickerChangeIdentityValidDate = (date, dateString) => {
    this.setState({
      identityValidDate: dateString,
    });
  };
  handleDatePickerChangeBirthday = (date, dateString) => {
    const strbirthString = dateString.split('-');
    const birthYear = strbirthString[0];
    const birthMonth = strbirthString[1];
    // const birthDay = strbirthString[2];
    const d = new Date();
    const newYear = d.getFullYear();
    const newMonth = d.getMonth() + 1;
    // const newDay = d.getDate();
    const staffYear = newYear - birthYear;
    const staffMonth = newMonth - birthMonth;
    const staffAge = Math.floor((staffYear * 12 + parseInt(staffMonth)) / 12);
    if (staffAge < 0 || isNaN(staffAge)) {
      return 0;
    }
    this.setState({
      age: staffAge,
      birthdate: dateString,
    });
  };
  handleDatePickerChangeFirstWorkingTime = (date, dateString) => {
    const strbirthString = dateString.split('-');
    const workYear = strbirthString[0];
    const workMonth = strbirthString[1];
    // const workDay = strbirthString[2];
    const d = new Date();
    const newYear = d.getFullYear();
    const newMonth = d.getMonth() + 1;
    let workYearA = newYear - workYear;
    let workMonthA = newMonth - workMonth;
    const workLifetime = Math.floor((workYearA * 12 + parseInt(workMonthA)) / 12);
    // const workLifetime = workYearA + '年' + workMonthA +'月';
    if (workLifetime < 0 || isNaN(workLifetime)) {
      return 0;
    }
    this.setState({
      workingLife: workLifetime,
      firstWorkingTime: dateString,
    });
  };
  handleDatePickerChangeEntryDate = (date, dateString) => {
    const strbirthString = dateString.split('-');
    const workYear = strbirthString[0];
    const workMonth = strbirthString[1];
    // const workDay = strbirthString[2];
    const d = new Date();
    const newYear = d.getFullYear();
    const newMonth = d.getMonth() + 1;
    const workYearA = newYear - workYear;
    const workMonthA = newMonth - workMonth;
    const workingtime = Math.floor((workYearA * 12 + parseInt(workMonthA)) / 12);
    if (workingtime < 0 || isNaN(workingtime)) {
      return 0;
    }
    this.setState({
      workingYears: workingtime,
      entryDate: dateString,
    });
  };
  handleDatePickerChangeLeaveDate = (date, dateString) => {
    this.setState({
      leaveDate: dateString,
    });
  };

  //选择部门
  handleSetOrg = deptId => {
    this.setState({deptId: deptId,});
    const { form } = this.props;
    form.setFieldsValue({
      deptId: deptId,
    });
  };

  //映射部门下拉树
  onRef = ref => {
    this.child = ref;
  };

  /**
   * 根据基地级联部门
   * @param baseId
   * @param idNo
   */
  handleChange(baseId) {
    getSonOrgTree(baseId).then(data => {
      this.setState({
        baseId: baseId,
        treeData: data.data,
      });
    });
    this.child.onChange('');
  }

  /**
   * 初始化加载部门
   * @param baseId
   * @param idNo
   */
  handleChangeGetCount(baseId, idNo) {
    getSonOrgTree(baseId).then(data => {
      this.setState({
        treeData: data.data,
      });
    });
    getCountByIdNo(idNo).then(data => {
      this.setState({
        idCount: data,
      });
    });
  }

  //入职次数
  handleChangeEntryTime = idNo => {
    if (idNo.target.value !== '') {
      getCountByIdNo(idNo.target.value).then(data => {
        if (!!data) {
          this.setState({
            idCount: data,
          });
        }
      });
    }
    if (idNo.target.value.length === 18) {
      this.setState({
        birthdate: moment(idNo.target.value.substring(6, 14)).format('YYYY-MM-DD'),
      });
    }
  };

  isOnJobOnChange = value => {
    this.setState({ isOnJobListDefaultId: value });
  };

  render() {
    const RadioGroup = Radio.Group;
    const {
      form: { getFieldDecorator },
    } = this.props;
    const dateFormat = 'YYYY-MM-DD';
    const { editing } = this.state;
    const { jobStatus } = this.state;
    const { isOnJobList } = this.state;
    let isOnJobListLeaveId = '';

    for (let index in isOnJobList) {
      if (index === '1') {
        isOnJobListLeaveId = isOnJobList[index].rowId;
      }
    }

    const action = (
      <Fragment>
        {editing ? (
          <div>
            <Button type="danger" onClick={() => this.setState({ editing: false })}>
              取消
            </Button>&nbsp;&nbsp;
            <Button type="primary" onClick={this.handleSure}>
              保存
            </Button>
          </div>
        ) : (
          <div>
            {hasAccessKey(`staff.baseInfo.update`) && (
              <Button type="primary" onClick={() => this.setState({ editing: true })}>
                编辑
              </Button>
            )}
          </div>
        )}
      </Fragment>
    );

    return (
      <div>
        <Card title={'基础信息'} bordered={false} extra={action}>
          {editing ? (
            <div className={styles.container}>
              <Form>
                <Row gutter={24}>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工号">
                      {getFieldDecorator('staffNo', {
                        initialValue: this.state.staffNo,
                        rules: [{ required: true, message: '请输入工号' }],
                      })(<Input placeholder="请输入" disabled />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="姓名">
                      {getFieldDecorator('staffName', {
                        initialValue: this.state.staffName,
                        rules: [{ required: true, message: '请输入姓名' }],
                      })(<Input placeholder="请输入" maxLength={50} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="性别">
                      {getFieldDecorator('sex', {
                        initialValue: this.state.sex,
                        rules: [{ required: true, message: '请选择性别' }],
                      })(
                        <Select>
                          {this.state.maleOrFemaleList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="基地">
                      {getFieldDecorator('baseId', {
                        initialValue: this.state.baseId,
                        rules: [{ required: true, message: '请选择基地' }],
                      })(
                        <Select disabled onChange={this.handleChange.bind(this)}>
                          {this.state.allBaseList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.baseOrDeptName}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门">
                      {getFieldDecorator('deptId', {
                        initialValue: this.state.deptId,
                        rules: [{ required: true, message: '请选择部门' }],
                      })(
                        <ModalOrgTreeSelect
                          disabled
                          treeData={this.state.treeData}
                          handleSetOrg={this.handleSetOrg.bind(this)}
                          onRef={this.onRef}
                          deptId={this.state.deptId}
                        />
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职衔">
                      {getFieldDecorator('positionId', {
                        initialValue: this.state.positionName,
                        rules: [{ required: true, message: '请选择职衔' }],
                      })(
                        <Select
                          placeholder={`可模糊搜索`}
                          showSearch
                        >
                          {this.state.allPosition.map(d => (
                            <Select.Option key={d.rowId} value={d.positionName} onClick={() => this.handleChangePosition(d.rowId)}>
                              {d.positionName}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item
                      labelCol={{ span: 7 }}
                      wrapperCol={{ span: 17 }}
                      label="职等/赋值名称"
                      setFieldsValue={this.state.gradeName}
                    >
                      {getFieldDecorator('gradeId', {
                        initialValue: this.state.isShowing ? this.state.gradeId : '',
                        rules: [{ required: true, message: '请选择职等/赋值名称' }],
                      })(
                        <Select
                          onChange={this.handleChangeGrade.bind(this)}
                          value={this.state.gradeName}
                        >
                          {this.state.allGradeByPosition.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.gradeName}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item
                      labelCol={{ span: 7 }}
                      wrapperCol={{ span: 17 }}
                      label="职级"
                      setFieldsValue={this.state.rankName}
                    >
                      {getFieldDecorator('rankId', {
                        initialValue: this.state.isShowing ? this.state.rankId : '',
                        rules: [{ required: true, message: '请选择职级' }],
                      })(
                        <Select
                          value={this.state.rankName}>
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
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工作岗位">
                      {getFieldDecorator('operatingPost', {
                        initialValue:this.state.operatingPost_name,
                        rules: [{ required: true, message: '请选择工作岗位' }],
                      })(
                        <Select
                          placeholder={`可模糊搜索`}
                          showSearch
                        >
                          {this.state.operatingPostList.map(d => (
                            <Select.Option key={d.rowId} value={d.name} onClick={() => this.handleChangeOperatingPost(d.rowId)}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工分类">
                      {getFieldDecorator('staffClassify', {
                        initialValue:this.state.staffClassify,
                        rules: [{ required: true, message: '请选择员工分类' }],
                      })(
                        <Select>
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
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证件类型">
                      {getFieldDecorator('identityTypeId', {
                        initialValue: this.state.identityTypeId,
                        rules: [{ required: true, message: '请选择证件类型' }],
                      })(
                        <Select>
                          {this.state.identityTypeList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证件号码">
                      {getFieldDecorator('identityNo', {
                        initialValue: this.state.identityNo,
                        rules: [{ required: true, message: '请输入证件号码' }],
                      })(
                        <Input
                          placeholder="请输入"
                          onChange={this.handleChangeEntryTime.bind(this)}
                          maxLength={30}
                        />
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="入职次数">
                      {getFieldDecorator('entryTime', {
                        initialValue: this.state.idCount,
                      })(<Input placeholder="请输入" disabled />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证件到期时间">
                      {getFieldDecorator('identityValidDate', {
                        initialValue: moment(this.state.identityValidDate),
                        rules: [{ required: true, message: '请选择证件到期时间' }],
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeIdentityValidDate}
                          format={dateFormat}
                          placeholder="请输入"
                        />
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="出生日期">
                      {getFieldDecorator('birthdate', {
                        initialValue: moment(this.state.birthdate),
                        rules: [{ required: true, message: '请选择出生日期' }],
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeBirthday}
                          format={dateFormat}
                          placeholder="请输入"
                        />
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="阴历阳历">
                      {getFieldDecorator('lunarSolarCalendar', {
                        initialValue: this.state.lunarSolarCalendar,
                        rules: [{ required: true, message: '请选择阴历阳历' }],
                      })(
                        <RadioGroup>
                          <Radio value="阴历">阴历</Radio>
                          <Radio value="阳历">阳历</Radio>
                        </RadioGroup>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="年龄">
                      {getFieldDecorator('age', {
                        initialValue: this.state.age,
                      })(<Input placeholder="" disabled />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="国籍">
                      {getFieldDecorator('nationality', {
                        initialValue: this.state.nationality_name,
                        rules: [{ required: true, message: '请输入国籍' }],
                      })(
                        <Select
                          placeholder={`可模糊搜索`}
                          showSearch
                         >
                          {this.state.nationalityList.map(d => (
                            <Select.Option key={d.rowId} value={d.name} onClick={() => this.handleChangeNationality(d.rowId)}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="民族">
                      {getFieldDecorator('nation', {
                        initialValue: this.state.nation_name,
                      })(
                        <Select
                          placeholder={`可模糊搜索`}
                          showSearch
                         >
                          {this.state.nationList.map(d => (
                            <Select.Option key={d.rowId} value={d.name} onClick={() => this.handleChangeNation(d.rowId)}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="户口所在地">
                      {getFieldDecorator('registeredResidence', {
                        initialValue: this.state.registeredResidence,
                      })(<Input placeholder="请输入" maxLength={50} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="籍贯">
                      {getFieldDecorator('nativePlace', {
                        initialValue: this.state.nativePlace,
                      })(<Input placeholder="请输入" maxLength={50} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="生育状况">
                      {getFieldDecorator('fertilityStatus', {
                        initialValue: this.state.fertilityStatus,
                        rules: [{ required: true, message: '请选择生育状况' }],
                      })(
                        <Select>
                          {this.state.fertilityStatusList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="婚姻状况">
                      {getFieldDecorator('maritalStatus', {
                        initialValue: this.state.maritalStatus,
                        rules: [{ required: true, message: '请选择婚姻状况' }],
                      })(
                        <Select>
                          {this.state.maritalStatusList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="政治面貌">
                      {getFieldDecorator('politicalStatus', {
                        initialValue: this.state.politicalStatus,
                      })(
                        <Select>
                          {this.state.politicalStatusList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item
                      labelCol={{ span: 7 }}
                      wrapperCol={{ span: 17 }}
                      label="首次工作时间"
                    >
                      {getFieldDecorator('firstWorkingTime', {
                        initialValue: moment(this.state.firstWorkingTime),
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeFirstWorkingTime}
                          format={dateFormat}
                          placeholder="请输入"
                        />
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工作年限">
                      {getFieldDecorator('workingLife', {
                        initialValue: this.state.workingLife,
                      })(<Input disabled />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工类型">
                      {getFieldDecorator('staffType', {
                        initialValue: this.state.staffType,
                      })(
                        <Select>
                          {this.state.staffTypeList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="社保类型">
                      {getFieldDecorator('socialSecurityType', {
                        initialValue: this.state.socialSecurityType,
                      })(
                        <Select>
                          {this.state.socialSecurityTypeList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="成本中心">
                      {getFieldDecorator('costCenter', {
                        initialValue: this.state.costCenter,
                      })(
                        <Select>
                          {this.state.allBaseList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.baseOrDeptName}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="办公地点">
                      {getFieldDecorator('officePlace', {
                        initialValue: this.state.officePlace,
                      })(<Input placeholder="请输入" maxLength={50} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                  {this.state.contractState === 'null' ? (
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="入职日期">
                      {getFieldDecorator('entryDate', {
                        initialValue: this.state.entryDate,
                        rules: [{ required: true, message: '请选择入职日期' }],
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeEntryDate}
                          format={dateFormat}
                          placeholder="请输入"
                        />
                      )}
                    </Form.Item>
                  ):(
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="入职日期">
                      {getFieldDecorator('entryDate', {
                        initialValue: moment(this.state.entryDate),
                        rules: [{ required: true, message: '请选择入职日期' }],
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeEntryDate}
                          format={dateFormat}
                          placeholder="请输入"
                        />
                      )}
                    </Form.Item>
                    )}
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="司龄">
                      {getFieldDecorator('workingYears', {
                        initialValue: this.state.workingYears,
                      })(<Input disabled />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="用工类型">
                      {getFieldDecorator('workType', {
                        initialValue: this.state.workType,
                      })(
                        <Select>
                          {this.state.typeOfLaborList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="手机号">
                      {getFieldDecorator('mobile', {
                        initialValue: this.state.mobile,
                        rules: [{ required: true, message: '请输入手机号' }],
                      })(<Input placeholder="请输入" maxLength={15} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="邮箱">
                      {getFieldDecorator('email', {
                        initialValue: this.state.email,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="线别">
                      {getFieldDecorator('lines', {
                        initialValue:this.state.lines,
                      })(
                        <Select>
                          {this.state.linesList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="厂别">
                      {getFieldDecorator('factoryCategory', {
                      })(
                        <Select>
                          {this.state.factoryCategoryList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="班次">
                      {getFieldDecorator('classes', {
                        initialValue:this.state.classes,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="招聘渠道">
                      {getFieldDecorator('recruitmentChannel', {
                        initialValue:this.state.recruitmentChannel,
                      })(
                        <Select>
                          {this.state.recruitmentChannelList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工卡卡号">
                      {getFieldDecorator('workCard', {
                        initialValue:this.state.workCard,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="宿舍号">
                      {getFieldDecorator('dormitoryNo', {
                        initialValue:this.state.dormitoryNo,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="更衣箱鞋柜">
                      {getFieldDecorator('lockerShoebox', {
                        initialValue:this.state.lockerShoebox,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="特长">
                      {getFieldDecorator('speciality', {
                        initialValue:this.state.speciality,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职称">
                      {getFieldDecorator('jobTitle', {
                        initialValue:this.state.jobTitle,
                      })(<Input placeholder="请输入" maxLength={30} />)}
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="在职状态">
                      {getFieldDecorator('jobStatus', {
                        initialValue: this.state.jobStatus,
                        rules: [{ required: true, message: '请选择在职状态' }],
                      })(
                        <Select disabled onChange={this.isOnJobOnChange.bind(this)}>
                          {this.state.isOnJobList.map(d => (
                            <Select.Option key={d.rowId} value={d.rowId}>
                              {d.name}
                            </Select.Option>
                          ))}
                        </Select>
                      )}
                    </Form.Item>
                  </Col>
                  {this.state.isOnJobListDefaultId == isOnJobListLeaveId && (
                    <Col span={12}>
                      <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="离职日期">
                        {getFieldDecorator('leaveDate', {
                          initialValue: moment(this.state.leaveDate),
                          rules: [{ required: true, message: '请选择离职日期' }],
                        })(
                          <DatePicker
                            onChange={this.handleDatePickerChangeLeaveDate}
                            format={dateFormat}
                            placeholder="请输入"
                          />
                        )}
                      </Form.Item>
                    </Col>
                  )}
                  {this.state.isOnJobListDefaultId == isOnJobListLeaveId && (
                    <Col span={12}>
                      <Form.Item
                        labelCol={{ span: 7 }}
                        wrapperCol={{ span: 17 }}
                        label="是否加入黑名单"
                      >
                        {getFieldDecorator('isBlacklist', {
                          initialValue: this.state.isBlacklist,
                        })(
                          <Select>
                            {this.state.yesOrNoList.map(d => (
                              <Select.Option key={d.rowId} value={d.rowId}>
                                {d.name}
                              </Select.Option>
                            ))}
                          </Select>
                        )}
                      </Form.Item>
                    </Col>
                  )}
                  <Col span={12}>
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="备注">
                      {getFieldDecorator('remark', {
                        initialValue: this.state.remark,
                      })(<Input placeholder="请输入" maxLength={200} />)}
                    </Form.Item>
                  </Col>
                </Row>
              </Form>
            </div>
          ) : (
            <DescriptionList size="samll" col={2} style={{ marginBottom: 32, width: 1500 }}>
              <Description term="工号" className={styles.info}>{this.state.staffNo}</Description>
              <Description term="姓名" className={styles.info}>{this.state.staffName}</Description>
              <Description term="性别" className={styles.info}>{this.state.sex_name}</Description>
              <Description term="基地" className={styles.info}>{this.state.baseId_baseOrDeptName}</Description>
              <Description term="部门" className={styles.info}>{this.state.deptId_baseOrDeptName}</Description>
              <Description term="职衔" className={styles.info}>{this.state.positionName}</Description>
              <Description term="职等/赋值名称" className={styles.info}>{this.state.gradeName}</Description>
              <Description term="职级" className={styles.info}>{this.state.rankName}</Description>
              <Description term="工作岗位" className={styles.info}>{this.state.operatingPost_name}</Description>
              <Description term="员工分类" className={styles.info}>{this.state.staffClassify_name}</Description>
              <Description term="证件类型" className={styles.info}>{this.state.identityTypeId_name}</Description>
              <Description term="证件号码" className={styles.info}>{this.state.identityNo}</Description>
              <Description term="入职次数" className={styles.info}>{this.state.idCount}</Description>
              <Description term="证件到期时间" className={styles.info}>{this.state.identityValidDate}</Description>
              <Description term="出生日期" className={styles.info}>{this.state.birthdate}</Description>
              <Description term="阴历阳历" className={styles.info}>{this.state.lunarSolarCalendar}</Description>
              <Description term="年龄" className={styles.info}>{this.state.age}</Description>
              <Description term="国籍" className={styles.info}>{this.state.nationality_name}</Description>
              <Description term="民族" className={styles.info}>{this.state.nation_name}</Description>
              <Description term="户口所在地" className={styles.info}>{this.state.registeredResidence}</Description>
              <Description term="籍贯" className={styles.info}>{this.state.nativePlace}</Description>
              <Description term="生育状况" className={styles.info}>{this.state.fertilityStatus_name}</Description>
              <Description term="婚姻状况" className={styles.info}>{this.state.maritalStatus_name}</Description>
              <Description term="政治面貌" className={styles.info}>{this.state.politicalStatus_name}</Description>
              <Description term="首次工作时间" className={styles.info}>{this.state.firstWorkingTime}</Description>
              <Description term="工作年限" className={styles.info}>{this.state.workingLife}</Description>
              <Description term="员工类型" className={styles.info}>{this.state.staffType_name}</Description>
              <Description term="社保类型" className={styles.info}>{this.state.socialSecurityType_name}</Description>
              <Description term="成本中心" className={styles.info}>{this.state.costCenter_baseOrDeptName}</Description>
              <Description term="办公地点" className={styles.info}>{this.state.officePlace}</Description>
              <Description term="入职日期" className={styles.info}>{this.state.entryDate}</Description>
              <Description term="司龄" className={styles.info}>{this.state.workingYears}</Description>
              <Description term="用工类型" className={styles.info}>{this.state.workType_name}</Description>
              <Description term="手机号" className={styles.info}>{this.state.mobile}</Description>
              <Description term="邮箱" className={styles.info}>{this.state.email}</Description>
              <Description term="线别" className={styles.info}>{this.state.lines_name}</Description>
              <Description term="厂别" className={styles.info}>{this.state.factoryCategory_name}</Description>
              <Description term="班次" className={styles.info}>{this.state.classes}</Description>
              <Description term="招聘渠道" className={styles.info}>{this.state.recruitmentChannel_name}</Description>
              <Description term="工卡卡号" className={styles.info}>{this.state.workCard}</Description>
              <Description term="宿舍号" className={styles.info}>{this.state.dormitoryNo}</Description>
              <Description term="更衣箱鞋柜" className={styles.info}>{this.state.lockerShoebox}</Description>
              <Description term="特长" className={styles.info}>{this.state.speciality}</Description>
              <Description term="职称" className={styles.info}>{this.state.jobTitle}</Description>
              <Description term="在职状态" className={styles.info}>{this.state.jobStatus_name}</Description>
              {this.state.isOnJobListDefaultId == isOnJobListLeaveId && (
                <Description term="离职日期" className={styles.info}>{this.state.leaveDate}</Description>
              )}
              {this.state.isOnJobListDefaultId == isOnJobListLeaveId && (
                <Description term="是否加入黑名单" className={styles.info}>{this.state.isBlacklist_name}</Description>
              )}
              <Description term="备注" className={styles.info}>{this.state.remark}</Description>
            </DescriptionList>
          )}

          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>外部工作经历</div>
          <OuterExperienceEditableTable rowId={this.props.rowId} />
          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>项目经历</div>
          <ProjectExperienceTable rowId={this.props.rowId} />
          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>教育经历</div>
          <EducationEditableTable rowId={this.props.rowId} />
          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>通讯信息</div>
          <AddressEditableTable rowId={this.props.rowId} />
          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>社会关系</div>
          <RelationshipSocialEditableTable rowId={this.props.rowId} />
          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>内部亲属关系</div>
          <RelationshipInnerEditableTable rowId={this.props.rowId} />
          <Divider style={{ marginBottom: 32 }} />
          <div className={styles.title}>紧急联系人</div>
          <ContactEmergencyEditableTable rowId={this.props.rowId} />
        </Card>
      </div>
    );
  }
}

export default Form.create({})(ModalEditForm);
