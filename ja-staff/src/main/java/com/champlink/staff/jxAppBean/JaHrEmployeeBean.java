package com.champlink.staff.jxAppBean;

import java.util.List;

public class JaHrEmployeeBean {
    private String guid;
    /**
     * 工号
     */
    private String jobNum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 证件类型
     */
    private String idType;
    /**
     * 证件号码
     */
    private String globalId;
    /**
     * 身份证有效开始日期
     */
    private String globalIdStartDate;
    /**
     * 身份证有效截至日期
     */
    private String globalIdEndDate;
    /**
     * 出生日期
     */
    private String birthDate;
    /**
     * 阴历阳历
     */
    private String lunarSolarCalendar;
    /**
     * 移动电话
     */
    private String movePhoneNum;
    /**
     * 现常居住地
     */
    private String address;
    /**
     * 户籍地
     */
    private String placeDomicile;
    /**
     * 民族
     */
    private String nation;
    /**
     * 籍贯
     */
    private String nationPlace;
    /**
     * 户籍性质
     */
    private String householdRegisterNature;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 婚姻状况
     */
    private String maritalStatus;
    /**
     * 生育状况
     */
    private String fertilityStatus;
    /**
     * 政治面貌
     */
    private String politicsStatus;
    /**
     * 职称情况
     */
    private String jobTitle;
    /**
     * 基地
     */
    private String unit;
    /**
     * 厂别
     */
    private String factory;
    /**
     * 合同类型
     */
    private String contractType;
    /**
     * 部门
     */
    private String department;
    /**
     * 工作岗位
     */
    private String operatingPost;
    /**
     * 进厂日期
     */
    private String enterFactoryDate;
    /**
     * 参加工作时间
     */
    private String workDate;
    /**
     * 合同开始日期
     */
    private String contractStartDate;
    /**
     * 合同结束日期
     */
    private String contractEndDate;
    /**
     * 离职日期
     */
    private String dimissionDate;
    /**
     * 卡号
     */
    private String cardNum;
    /**
     * 入职次数
     */
    private String inductionNum;
    /**
     * 班次信息
     */
    private String flightInformation;
    /**
     * 籍贯地址
     */
    private String nativeAddress;
    /**
     * 宿舍号
     */
    private String dormitoryNum;
    /**
     * 更衣箱/鞋柜
     */
    private String lockerNum;
    /**
     * 邮件地址
     */
    private String mailAddress;
    /**
     * 邮编
     */
    private String postcodeNum;
    /**
     * 银行账号
     */
    private String bankNum;
    /**
     * 状态
     */
    private String state;
    /**
     * 线别
     */
    private String jobLines;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 备注
     */
    private String jobRemark;
    /**
     * 特长信息
     */
    private String certficateName;
    /**
     * 招聘渠道
     */
    private String recruitmentChannel;
    /**
     * 员工分类
     */
    private String categoryEmp;

    List<JaHrEmpSateInfoBean> users2; // 附属信息 (社会关系)
    List<JaHrEmpEmerInfoBean> users3; // 紧急联系人 (紧急联系人)
    List<JaHrEmpEducationExperienceBean> users4; // 教育经历
    List<JaHrEmpSoclalHistoryInfoBean> users5; // 社会履历
    List<JaHrEmpCommunicationInfoBean> users6;// 员工通讯
    List<JaHrempKinsfolkRelationInfoBean> users7;// 内部亲属关系
    List<JaHrEmpSkllCertficateInfoBean> users8;// 所获技能证书
    List<JaHrEmpHonorInfoBean> users9;// 所获荣誉情况
    List<JaHrEmpProjectInfoBean> users10; // 查找数据
    List<JaHrEmpPromoteInfoBean> users11;// 内部晋升履历

    public String getCategoryEmp() {
        return categoryEmp;
    }

    public void setCategoryEmp(String categoryEmp) {
        this.categoryEmp = categoryEmp;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getGlobalIdStartDate() {
        return globalIdStartDate;
    }

    public void setGlobalIdStartDate(String globalIdStartDate) {
        this.globalIdStartDate = globalIdStartDate;
    }

    public String getGlobalIdEndDate() {
        return globalIdEndDate;
    }

    public void setGlobalIdEndDate(String globalIdEndDate) {
        this.globalIdEndDate = globalIdEndDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLunarSolarCalendar() {
        return lunarSolarCalendar;
    }

    public void setLunarSolarCalendar(String lunarSolarCalendar) {
        this.lunarSolarCalendar = lunarSolarCalendar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceDomicile() {
        return placeDomicile;
    }

    public void setPlaceDomicile(String placeDomicile) {
        this.placeDomicile = placeDomicile;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationPlace() {
        return nationPlace;
    }

    public void setNationPlace(String nationPlace) {
        this.nationPlace = nationPlace;
    }

    public String getHouseholdRegisterNature() {
        return householdRegisterNature;
    }

    public void setHouseholdRegisterNature(String householdRegisterNature) {
        this.householdRegisterNature = householdRegisterNature;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getFertilityStatus() {
        return fertilityStatus;
    }

    public void setFertilityStatus(String fertilityStatus) {
        this.fertilityStatus = fertilityStatus;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOperatingPost() {
        return operatingPost;
    }

    public void setOperatingPost(String operatingPost) {
        this.operatingPost = operatingPost;
    }

    public String getEnterFactoryDate() {
        return enterFactoryDate;
    }

    public void setEnterFactoryDate(String enterFactoryDate) {
        this.enterFactoryDate = enterFactoryDate;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(String dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getInductionNum() {
        return inductionNum;
    }

    public void setInductionNum(String inductionNum) {
        this.inductionNum = inductionNum;
    }

    public String getFlightInformation() {
        return flightInformation;
    }

    public void setFlightInformation(String flightInformation) {
        this.flightInformation = flightInformation;
    }

    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    public String getDormitoryNum() {
        return dormitoryNum;
    }

    public void setDormitoryNum(String dormitoryNum) {
        this.dormitoryNum = dormitoryNum;
    }

    public String getLockerNum() {
        return lockerNum;
    }

    public void setLockerNum(String lockerNum) {
        this.lockerNum = lockerNum;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPostcodeNum() {
        return postcodeNum;
    }

    public void setPostcodeNum(String postcodeNum) {
        this.postcodeNum = postcodeNum;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJobLines() {
        return jobLines;
    }

    public void setJobLines(String jobLines) {
        this.jobLines = jobLines;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getJobRemark() {
        return jobRemark;
    }

    public void setJobRemark(String jobRemark) {
        this.jobRemark = jobRemark;
    }

    public String getCertficateName() {
        return certficateName;
    }

    public void setCertficateName(String certficateName) {
        this.certficateName = certficateName;
    }

    public String getMovePhoneNum() {
        return movePhoneNum;
    }

    public void setMovePhoneNum(String movePhoneNum) {
        this.movePhoneNum = movePhoneNum;
    }

    public JaHrEmployeeBean() {
        super();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getRecruitmentChannel() {
        return recruitmentChannel;
    }

    public void setRecruitmentChannel(String recruitmentChannel) {
        this.recruitmentChannel = recruitmentChannel;
    }

    public JaHrEmployeeBean(String guid, String jobNum, String name, String sex, String globalId, String idType, String globalIdStartDate, String globalIdEndDate, String birthDate,
                            String lunarSolarCalendar, String movePhoneNum, String address, String placeDomicile, String nation, String nationPlace, String householdRegisterNature, String nationality,
                            String maritalStatus, String fertilityStatus, String politicsStatus, String jobTitle, String unit, String factory, String contractType, String department,
                            String operatingPost, String enterFactoryDate, String workDate, String contractStartDate, String contractEndDate, String dimissionDate, String cardNum, String inductionNum,
                            String flightInformation, String nativeAddress, String dormitoryNum, String lockerNum, String mailAddress, String postcodeNum, String bankNum, String state,
                            String jobLines, String headPortrait, String jobRemark, String certficateName, String recruitmentChannel, String categoryEmp) {
        super();
        this.guid = guid;
        this.jobNum = jobNum;
        this.name = name;
        this.sex = sex;
        this.globalId = globalId;
        this.idType = idType;
        this.globalIdStartDate = globalIdStartDate;
        this.globalIdEndDate = globalIdEndDate;
        this.birthDate = birthDate;
        this.lunarSolarCalendar = lunarSolarCalendar;
        this.movePhoneNum = movePhoneNum;
        this.address = address;
        this.placeDomicile = placeDomicile;
        this.nation = nation;
        this.nationPlace = nationPlace;
        this.householdRegisterNature = householdRegisterNature;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.fertilityStatus = fertilityStatus;
        this.politicsStatus = politicsStatus;
        this.jobTitle = jobTitle;
        this.unit = unit;
        this.factory = factory;
        this.contractType = contractType;
        this.department = department;
        this.operatingPost = operatingPost;
        this.enterFactoryDate = enterFactoryDate;
        this.workDate = workDate;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.dimissionDate = dimissionDate;
        this.cardNum = cardNum;
        this.inductionNum = inductionNum;
        this.flightInformation = flightInformation;
        this.nativeAddress = nativeAddress;
        this.dormitoryNum = dormitoryNum;
        this.lockerNum = lockerNum;
        this.mailAddress = mailAddress;
        this.postcodeNum = postcodeNum;
        this.bankNum = bankNum;
        this.state = state;
        this.jobLines = jobLines;
        this.headPortrait = headPortrait;
        this.jobRemark = jobRemark;
        this.certficateName = certficateName;
        this.recruitmentChannel = recruitmentChannel;
        this.categoryEmp = categoryEmp;
    }

    public List<JaHrEmpSateInfoBean> getUsers2() {
        return users2;
    }

    public void setUsers2(List<JaHrEmpSateInfoBean> users2) {
        this.users2 = users2;
    }

    public List<JaHrEmpEmerInfoBean> getUsers3() {
        return users3;
    }

    public void setUsers3(List<JaHrEmpEmerInfoBean> users3) {
        this.users3 = users3;
    }

    public List<JaHrEmpEducationExperienceBean> getUsers4() {
        return users4;
    }

    public void setUsers4(List<JaHrEmpEducationExperienceBean> users4) {
        this.users4 = users4;
    }

    public List<JaHrEmpSoclalHistoryInfoBean> getUsers5() {
        return users5;
    }

    public void setUsers5(List<JaHrEmpSoclalHistoryInfoBean> users5) {
        this.users5 = users5;
    }

    public List<JaHrEmpCommunicationInfoBean> getUsers6() {
        return users6;
    }

    public void setUsers6(List<JaHrEmpCommunicationInfoBean> users6) {
        this.users6 = users6;
    }

    public List<JaHrempKinsfolkRelationInfoBean> getUsers7() {
        return users7;
    }

    public void setUsers7(List<JaHrempKinsfolkRelationInfoBean> users7) {
        this.users7 = users7;
    }

    public List<JaHrEmpSkllCertficateInfoBean> getUsers8() {
        return users8;
    }

    public void setUsers8(List<JaHrEmpSkllCertficateInfoBean> users8) {
        this.users8 = users8;
    }

    public List<JaHrEmpHonorInfoBean> getUsers9() {
        return users9;
    }

    public void setUsers9(List<JaHrEmpHonorInfoBean> users9) {
        this.users9 = users9;
    }

    public List<JaHrEmpProjectInfoBean> getUsers10() {
        return users10;
    }

    public void setUsers10(List<JaHrEmpProjectInfoBean> users10) {
        this.users10 = users10;
    }

    public List<JaHrEmpPromoteInfoBean> getUsers11() {
        return users11;
    }

    public void setUsers11(List<JaHrEmpPromoteInfoBean> users11) {
        this.users11 = users11;
    }

}
