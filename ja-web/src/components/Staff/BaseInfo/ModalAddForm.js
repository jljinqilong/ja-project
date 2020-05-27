import React, { PureComponent } from 'react';
import { Select, Form, Input, Modal, Row, Col, message, DatePicker, Icon,Radio } from 'antd';
import { addStaff, getCountByIdNo, getStaffNoByBaseId } from '../../../services/staffBaseInfo';
import ModalOrgTreeSelect from '../../Org/Org/ModalOrgTreeSelect';
import moment from 'moment';
import styles from './BasicForm.less';
import { getByTypeCode } from '../../../services/systemCode';
import {
  baseList,
  getSonOrgTree,
  getAllPosition,
  getAllGradeByPosition,
  getAllRankByPositionAndGrade,
} from '../../../services/org';

class ModalAddForm extends PureComponent {
  state = {
    birthdate: '',
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
    isOnJobList: [],
    allBaseList: [],
    age: 0,
    workLife: 0,
    workYears: 0,
    baseId: '',
    treeData: [],
    allPosition: [],
    allGradeByPosition: [],
    allRankByPositionAndGrade: [],
    nationalityList: [],
    nationList: [],
    linesList: [],
    staffClassifyList: [],
    operatingPostList: [],
    factoryCategoryList:[],
    recruitmentChannelList:[],
    gradeId: '',
    rankId: '',
  };

  /**
   * 初始化
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
      this.state.yesOrNoList = content.data;
    });
    //男女
    getByTypeCode({ typeCode: 'MALE_OR_FEMALE' }).then(content => {
      this.state.maleOrFemaleList = content.data;
    });
    //证件类型
    getByTypeCode({ typeCode: 'IDENTITY_TYPE' }).then(content => {
      this.state.identityTypeList = content.data;
    });
    //国籍
    getByTypeCode({ typeCode: 'NATIONALITY' }).then(content => {
      this.state.nationalityList = content.data;
    });
    //民族
    getByTypeCode({ typeCode: 'NATION' }).then(content => {
      this.state.nationList = content.data;
    });
    //婚姻状况
    getByTypeCode({ typeCode: 'MARITAL_STATUS' }).then(content => {
      this.state.maritalStatusList = content.data;
    });
    //政治面貌
    getByTypeCode({ typeCode: 'POLITICAL_STATUS' }).then(content => {
      this.state.politicalStatusList = content.data;
    });
    //员工类型
    getByTypeCode({ typeCode: 'STAFF_TYPE' }).then(content => {
      this.state.staffTypeList = content.data;
    });
    //社保类型
    getByTypeCode({ typeCode: 'SOCIAL_SECURITY_TYPE' }).then(content => {
      this.state.socialSecurityTypeList = content.data;
    });
    //成本中心
    getByTypeCode({ typeCode: 'COST_CENTER' }).then(content => {
      this.state.costCenterList = content.data;
    });
    //用工类型
    getByTypeCode({ typeCode: 'TYPE_OF_LABOR' }).then(content => {
      this.state.typeOfLaborList = content.data;
    });
    //是否在职
    getByTypeCode({ typeCode: 'IS_ON_JOB' }).then(content => {
      this.state.isOnJobList = content.data;
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
      let email =/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

      if(!!fieldsValue.email && email !== ''){
        if (!email.test(fieldsValue.email)) {
          message.warn('请输入正确的邮箱！');
          return;
        }
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
      fieldsValue.operatingPost = this.state.operatingPost;
      fieldsValue.positionId = this.state.positionId;
      fieldsValue.identityValidDate = this.state.identityValidDate;
      fieldsValue.birthdate = this.state.birthdate;
      fieldsValue.firstWorkingTime = this.state.firstWorkingTime;
      fieldsValue.entryDate = this.state.entryDate;
      fieldsValue.leaveDate = this.state.leaveDate;
      fieldsValue.currentId = this.state.currentId;
      addStaff(fieldsValue).then(data => {
        if (data.code === 200) {
          message.success('添加成功');
          form.resetFields();
          this.setState({
            treeData: [],
            allGradeByPosition: [],
            allRankByPositionAndGrade: [],
            staffId: '',
            deptId: '',
          });
          this.child.onChange('');
          this.props.handleModalVisible(false, 0, 0);
          this.props.refreshTable();
        }
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

  //入职次数
  handleChangeEntryTime = idNo => {
    if (idNo.target.value !== '') {
      getCountByIdNo(idNo.target.value).then(data => {
        this.setState({
          idCount: data,
        });
      });
    }
    if (idNo.target.value.length === 18) {
      let myreg = /^[1-9]\d{5}(18|19|2([0-9]))\d{2}(0[0-9]|10|11|12)([0-2][1-9]|30|31)\d{3}[0-9Xx]$/;
      if (myreg.test(idNo.target.value)) {
        this.setState({
          birthdate: moment(idNo.target.value.substring(6, 14)).format('YYYY-MM-DD'),
        });
      } else {
        message.warn('请输入正确的身份证！');
      }
      this.setState({
        age: moment().diff(moment(idNo.target.value.substring(6, 14)).format('YYYY-MM-DD'), 'years'),
      });
    }
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
      workLife: workLifetime,
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
    // const workingtime = workYearA + '年' + workMonthA +'月';
    this.setState({
      workYears: workingtime,
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
    this.setState({ deptId: deptId });
    const { form } = this.props;
    form.setFieldsValue({
      deptId: deptId,
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
   * 生成工号
   */
  getStaffNo = () => {
    const { form } = this.props;
    const values = form.getFieldsValue();
    form.resetFields();
    form.setFieldsValue({
      baseId: values.baseId,
      costCenter: values.costCenter,
      deptId: values.deptId,
      entryDate: values.entryDate,
      fertilityStatus: values.fertilityStatus,
      firstWorkingTime: values.firstWorkingTime,
      gradeId: values.gradeId,
      identityNo: values.identityNo,
      identityTypeId: values.identityTypeId,
      identityValidDate: values.identityValidDate,
      isBlacklist: values.isBlacklist,
      jobStatus: values.jobStatus,
      leaveDate: values.leaveDate,
      maritalStatus: values.maritalStatus,
      nation: values.nation,
      nationality: values.nationality,
      nativePlace: values.nativePlace,
      officePlace: values.officePlace,
      politicalStatus: values.politicalStatus,
      positionId: values.positionId,
      rankId: values.rankId,
      registeredResidence: values.registeredResidence,
      remark: values.remark,
      sex: values.sex,
      socialSecurityType: values.socialSecurityType,
      staffName: values.staffName,
      staffType: values.staffType,
      workType: values.workType,
    });
    if (this.state.baseId !== '') {
      getStaffNoByBaseId(this.state.baseId).then(data => {
        if (data.code === 200) {
          this.setState({
            staffId: data.data.staffNo,
            currentId: data.data.currentId,
          });
        }
      });
    } else {
      message.info('请选择基地');
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
    const { maritalStatusList } = this.state;
    let maritalStatusListDefaultId = '';
    const { fertilityStatusList } = this.state;
    let fertilityStatusListDefaultId = '';
    const { isOnJobList } = this.state;
    let isOnJobListDefaultId = '';
    let isOnJobListLeaveId = '';
    const { yesOrNoList } = this.state;
    let yesOrNoListDefaultId = '';
    const { identityTypeList } = this.state;
    let identityTypeListDefaultId = '';
    const { nationalityList } = this.state;
    let nationalityListDefaultId = '';
    const { nationList } = this.state;
    let nationListDefaultId = '';

    for (let index in maritalStatusList) {
      if (index === '1') {
        maritalStatusListDefaultId = maritalStatusList[index].rowId;
      }
    }

    for (let index in fertilityStatusList) {
      if (index === '0') {
        fertilityStatusListDefaultId = fertilityStatusList[index].rowId;
      }
    }

    for (let index in isOnJobList) {
      if (index === '0') {
        isOnJobListDefaultId = isOnJobList[index].rowId;
      }
      if (index === '1') {
        isOnJobListLeaveId = isOnJobList[index].rowId;
      }
    }

    for (let index in yesOrNoList) {
      yesOrNoListDefaultId = yesOrNoList[index].rowId;
    }

    for (let index in identityTypeList) {
      if (index === '0') {
        identityTypeListDefaultId = identityTypeList[index].rowId;
      }
    }

    for (let index in nationalityList) {
      if (index === '235') {
        nationalityListDefaultId = nationalityList[index].name;
      }
    }

    for (let index in nationList) {
      if (index === '0') {
        nationListDefaultId = nationList[index].name;
      }
    }

    return (
      <div>
        <Modal
          title="添加员工"
          visible={this.props.modalVisibleAdd}
          onOk={this.handleSure}
          okText={`保存`}
          width={1200}
          onCancel={() => this.props.handleModalVisible(false, 0, -1)}
        >
          <div className={styles.panel}>
            <div className={styles.title}>基础信息</div>
            <Form>
              <Row gutter={24}>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="基地">
                    {getFieldDecorator('baseId', {
                      rules: [{ required: true, message: '请选择基地' }],
                    })(
                      <Select onChange={this.handleChange.bind(this)}>
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
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="工号">
                    {getFieldDecorator('staffNo', {
                      initialValue: this.state.staffId,
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
                      />
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="姓名">
                    {getFieldDecorator('staffName', {
                      rules: [{ required: true, message: '请输入姓名' }],
                    })(<Input placeholder="请输入" maxLength={50} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="性别">
                    {getFieldDecorator('sex', {
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
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="部门">
                    {getFieldDecorator('deptId', {
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
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职衔">
                    {getFieldDecorator('positionId', {
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
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职等/赋值名称">
                    {getFieldDecorator('gradeId', {
                      // initialValue:this.state.gradeId,
                      rules: [{ required: true, message: '请选择职等/赋值名称' }],
                    })(
                      <Select
                        onChange={this.handleChangeGrade.bind(this)}
                        dropdownMatchSelectWidth={true}>
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
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职级">
                    {getFieldDecorator('rankId', {
                      rules: [{ required: true, message: '请选择职级' }],
                    })(
                      <Select>
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
                      initialValue: identityTypeListDefaultId,
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
                    })(<Input disabled />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="证件到期时间">
                    {getFieldDecorator('identityValidDate', {
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
                  {this.state.birthdate === '' ? (
                    <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="出生日期">
                      {getFieldDecorator('birthdate', {
                        rules: [{ required: true, message: '请选择出生日期' }],
                      })(
                        <DatePicker
                          onChange={this.handleDatePickerChangeBirthday}
                          format={dateFormat}
                          placeholder="请输入"
                        />
                      )}
                    </Form.Item>
                  ) : (
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
                  )}
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="阴历阳历">
                    {getFieldDecorator('lunarSolarCalendar', {
                      initialValue:this.state.lunarSolarCalendar,
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
                    })(<Input disabled />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="国籍">
                    {getFieldDecorator('nationality', {
                      initialValue: nationalityListDefaultId,
                      rules: [{ required: true, message: '请输入国籍' }],
                    })(
                      <Select
                        placeholder={`可模糊搜索`}
                        showSearch>
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
                      initialValue: nationListDefaultId,
                    })(
                      <Select
                        placeholder={`可模糊搜索`}
                        showSearch>
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
                    {getFieldDecorator('registeredResidence', {})(
                      <Input placeholder="请输入" maxLength={50} />
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="籍贯">
                    {getFieldDecorator('nativePlace', {})(
                      <Input placeholder="请输入" maxLength={50} />
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="生育状况">
                    {getFieldDecorator('fertilityStatus', {
                      initialValue: fertilityStatusListDefaultId,
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
                      initialValue: maritalStatusListDefaultId,
                      rules: [{ required: true, message: '请选择婚姻状态' }],
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
                    {getFieldDecorator('politicalStatus')(
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
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="首次工作时间">
                    {getFieldDecorator('firstWorkingTime', {})(
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
                      initialValue: this.state.workLife,
                    })(<Input disabled />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="员工类型">
                    {getFieldDecorator('staffType', {
                      rules: [{ required: true, message: '请选择员工类型' }],
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
                    {getFieldDecorator('socialSecurityType', {})(
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
                    {getFieldDecorator('costCenter')(
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
                    {getFieldDecorator('officePlace', {})(
                      <Input placeholder="请输入" maxLength={50} />
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="入职日期">
                    {getFieldDecorator('entryDate', {
                      rules: [{ required: true, message: '请输入入职日期' }],
                    })(
                      <DatePicker
                        onChange={this.handleDatePickerChangeEntryDate}
                        format={dateFormat}
                        placeholder="请输入"
                      />
                    )}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="司龄">
                    {getFieldDecorator('workingYears', {
                      initialValue: this.state.workYears,
                    })(<Input disabled />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="用工类型">
                    {getFieldDecorator('workType', {})(
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
                      rules: [{ required: true, message: '请输入手机号' }],
                    })(<Input placeholder="请输入" maxLength={15} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="邮箱">
                    {getFieldDecorator('email', {})(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="线别">
                    {getFieldDecorator('lines', {})(
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
                    })(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="招聘渠道">
                    {getFieldDecorator('recruitmentChannel', {
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
                    })(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="宿舍号">
                    {getFieldDecorator('dormitoryNo', {})(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="更衣箱鞋柜">
                    {getFieldDecorator('lockerShoebox', {})(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="特长">
                    {getFieldDecorator('speciality', {})(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="职称">
                    {getFieldDecorator('jobTitle', {})(<Input placeholder="请输入" maxLength={30} />)}
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item labelCol={{ span: 7 }} wrapperCol={{ span: 17 }} label="在职状态">
                    {getFieldDecorator('jobStatus', {
                      initialValue: isOnJobListDefaultId,
                      rules: [{ required: true, message: '请选择在职状态' }],
                    })(
                      <Select onChange={this.isOnJobOnChange.bind(this)}>
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
                        initialValue: yesOrNoListDefaultId,
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
                    {getFieldDecorator('remark')(<Input placeholder="请输入" maxLength={200} />)}
                  </Form.Item>
                </Col>
              </Row>
            </Form>
          </div>
        </Modal>
      </div>
    );
  }
}

export default Form.create({})(ModalAddForm);
