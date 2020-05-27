package com.champlink.common.form.staff.baseInfo;

import java.util.Date;
import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;
import com.champlink.common.util.annotations.Validation;

public class ImportBaseInfoForm extends BaseForm {

    /**
     * 工号
     */
    @Excel(title = "工号")
    private String staffNo;

    /**
     * 姓名
     */
    @Excel(title = "姓名", isNull = false)
    private String staffName;

    /**
     * 性别
     */
    private Long sex;

    @Excel(title = "性别", isNull = false)
    private String sexStr;

    /**
     * 基地ID
     */
    private Long baseId;

    @Excel(title = "基地", isNull = false)
    private String baseStr;

    /**
     * 部门ID
     */
    private Long deptId;

    @Excel(title = "部门", isNull = false)
    private String deptStr;

    /**
     * 职衔ID
     */
    private Long positionId;

    @Excel(title = "职衔", isNull = false)
    private String positionStr;

    /**
     * 职级ID
     */
    private Long rankId;

    @Excel(title = "职级", isNull = false)
    private String rankStr;

    /**
     * 职等ID/赋值名称
     */
    private Long gradeId;

    @Excel(title = "职等/赋值名称", isNull = false)
    private String gradeStr;

    /**
     * 证件类型
     */
    private Long identityTypeId;

    @Excel(title = "证件类型", isNull = false)
    private String identityTypeStr;

    /**
     * 证件号码
     */
    @Excel(title = "证件号码", isNull = false)
    @Validation(rule = "^(\\d+|\\d{17}[0-9Xx])$", msg = "证件号码格式错误")
    private String identityNo;

    /**
     * 证件到期时间
     */
    @Excel(title = "证件到期时间", isNull = false)
    @Validation(rule = "^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))$", msg = "证件到期时间格式错误")
    private Date identityValidDate;

    /**
     * 出生日期
     */
    @Excel(title = "出生日期", isNull = false)
    @Validation(rule = "^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))$", msg = "出生日期期格式错误")
    private Date birthdate;

    /**
     * 国籍
     */
    @Excel(title = "国籍", isNull = false)
    private String nationalityStr;
    private Long nationality;

    /**
     * 民族
     */
    @Excel(title = "民族")
    private String nationStr;
    private Long nation;

    /**
     * 户口所在地
     */
    @Excel(title = "户口所在地")
    private String registeredResidence;

    /**
     * 籍贯
     */
    @Excel(title = "籍贯")
    private String nativePlace;

    /**
     * 生育状况
     */
    private Long fertilityStatus;

    @Excel(title = "生育状况", isNull = false)
    private String fertilityStatusStr;

    /**
     * 婚姻状况
     */
    private Long maritalStatus;

    @Excel(title = "婚姻状况", isNull = false)
    private String maritalStatusStr;

    /**
     * 政治面貌
     */
    private Long politicalStatus;

    @Excel(title = "政治面貌")
    private String politicalStatusStr;

    /**
     * 首次工作时间
     */
    @Excel(title = "首次工作时间")
    @Validation(rule = "^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))$", msg = "首次工作时间期格式错误")
    private Date firstWorkingTime;

    /**
     * 员工类型
     */
    private Long staffType;

    @Excel(title = "员工类型")
    private String staffTypeStr;

    /**
     * 社保类型
     */
    private Long socialSecurityType;

    @Excel(title = "社保类型")
    private String socialSecurityTypeStr;

    /**
     * 成本中心
     */
    private Long costCenter;

    @Excel(title = "成本中心")
    private String costCenterStr;

    /**
     * 办公地点
     */
    @Excel(title = "办公地点")
    private String officePlace;

    /**
     * 入职日期
     */
    @Excel(title = "入职日期", isNull = false)
    @Validation(rule = "^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))$", msg = "入职日期格式错误")
    private Date entryDate;

    /**
     * 用工类型
     */
    private Long workType;

    @Excel(title = "用工类型")
    private String workTypeStr;

    /**
     * 在职状态
     */
    private Long jobStatus;

    @Excel(title = "在职状态", isNull = false)
    private String jobStatusStr;

    /**
     * 离职日期
     */
    @Excel(title = "离职日期")
    @Validation(rule = "^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))|([0-9]{4})/((1[0-2])|(0?[1-9]))/((3[0-1])|(0?[1-9])|([1,2][0-9]))$", msg = "离职日期期格式错误")
    private Date leaveDate;

    /**
     * 是否加入黑名单
     */
    private Long isBlacklist;

    @Excel(title = "是否加入黑名单")
    private String isBlacklistStr;

    /**
     * 备注
     */
    @Excel(title = "备注")
    private String remark;

    private Long lines;//线别
    @Excel(title = "线别")
    private String linesStr;

    private String staffClassify;//员工分类 
    @Excel(title = "员工分类", isNull = false)
    private String staffClassifyStr;

    private Long operatingPost;
    @Excel(title = "工作岗位", isNull = false)
    private String operatingPostStr;//工作岗位

    @Excel(title = "手机号", isNull = false)
    @Validation(rule = "^[1][3,4,5,7,8][0-9]{9}$", msg = "手机号格式错误")
    private String mobile;//手机号

    @Excel(title = "邮箱")
    @Validation(rule = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", msg = "邮箱格式错误")
    private String email;//邮箱

    private Long factoryCategory;//厂别
    @Excel(title = "厂别")
    private String factoryCategoryStr;//厂别

    @Excel(title = "班次")
    private String classes;//班次

    private Long recruitmentChannel;//招聘渠道
    @Excel(title = "招聘渠道")
    private String recruitmentChannelStr;//招聘渠道
    @Excel(title = "工卡卡号")
    private String workCard;//工卡卡号
    @Excel(title = "宿舍号")
    private String dormitoryNo;//宿舍号
    @Excel(title = "更衣箱鞋柜")
    private String lockerShoebox;//更衣箱鞋柜
    @Excel(title = "特长")
    private String speciality;//特长
    @Excel(title = "职称")
    private String jobTitle;//职称
    @Excel(title = "阴历阳历")
    private String lunarSolarCalendar;// 阴历阳历

    /**
     * 错误描述
     */
    private String errMsg;

    public Long getFactoryCategory() {
        return factoryCategory;
    }

    public void setFactoryCategory(Long factoryCategory) {
        this.factoryCategory = factoryCategory;
    }

    public String getFactoryCategoryStr() {
        return factoryCategoryStr;
    }

    public void setFactoryCategoryStr(String factoryCategoryStr) {
        this.factoryCategoryStr = factoryCategoryStr;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Long getRecruitmentChannel() {
        return recruitmentChannel;
    }

    public void setRecruitmentChannel(Long recruitmentChannel) {
        this.recruitmentChannel = recruitmentChannel;
    }

    public String getRecruitmentChannelStr() {
        return recruitmentChannelStr;
    }

    public void setRecruitmentChannelStr(String recruitmentChannelStr) {
        this.recruitmentChannelStr = recruitmentChannelStr;
    }

    public String getWorkCard() {
        return workCard;
    }

    public void setWorkCard(String workCard) {
        this.workCard = workCard;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    public String getLockerShoebox() {
        return lockerShoebox;
    }

    public void setLockerShoebox(String lockerShoebox) {
        this.lockerShoebox = lockerShoebox;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLunarSolarCalendar() {
        return lunarSolarCalendar;
    }

    public void setLunarSolarCalendar(String lunarSolarCalendar) {
        this.lunarSolarCalendar = lunarSolarCalendar;
    }

    public Long getLines() {
        return lines;
    }

    public void setLines(Long lines) {
        this.lines = lines;
    }

    public String getLinesStr() {
        return linesStr;
    }

    public void setLinesStr(String linesStr) {
        this.linesStr = linesStr;
    }

    public String getStaffClassify() {
        return staffClassify;
    }

    public void setStaffClassify(String staffClassify) {
        this.staffClassify = staffClassify;
    }

    public String getStaffClassifyStr() {
        return staffClassifyStr;
    }

    public void setStaffClassifyStr(String staffClassifyStr) {
        this.staffClassifyStr = staffClassifyStr;
    }

    public Long getOperatingPost() {
        return operatingPost;
    }

    public void setOperatingPost(Long operatingPost) {
        this.operatingPost = operatingPost;
    }

    public String getOperatingPostStr() {
        return operatingPostStr;
    }

    public void setOperatingPostStr(String operatingPostStr) {
        this.operatingPostStr = operatingPostStr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBaseStr() {
        return baseStr;
    }

    public void setBaseStr(String baseStr) {
        this.baseStr = baseStr;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptStr() {
        return deptStr;
    }

    public void setDeptStr(String deptStr) {
        this.deptStr = deptStr;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public String getRankStr() {
        return rankStr;
    }

    public void setRankStr(String rankStr) {
        this.rankStr = rankStr;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeStr() {
        return gradeStr;
    }

    public void setGradeStr(String gradeStr) {
        this.gradeStr = gradeStr;
    }

    public Long getIdentityTypeId() {
        return identityTypeId;
    }

    public void setIdentityTypeId(Long identityTypeId) {
        this.identityTypeId = identityTypeId;
    }

    public String getIdentityTypeStr() {
        return identityTypeStr;
    }

    public void setIdentityTypeStr(String identityTypeStr) {
        this.identityTypeStr = identityTypeStr;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Date getIdentityValidDate() {
        return identityValidDate;
    }

    public void setIdentityValidDate(Date identityValidDate) {
        this.identityValidDate = identityValidDate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getNationality() {
        return nationality;
    }

    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Long getNation() {
        return nation;
    }

    public void setNation(Long nation) {
        this.nation = nation;
    }

    public String getNationalityStr() {
        return nationalityStr;
    }

    public void setNationalityStr(String nationalityStr) {
        this.nationalityStr = nationalityStr;
    }

    public String getNationStr() {
        return nationStr;
    }

    public void setNationStr(String nationStr) {
        this.nationStr = nationStr;
    }

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Long getFertilityStatus() {
        return fertilityStatus;
    }

    public void setFertilityStatus(Long fertilityStatus) {
        this.fertilityStatus = fertilityStatus;
    }

    public String getFertilityStatusStr() {
        return fertilityStatusStr;
    }

    public void setFertilityStatusStr(String fertilityStatusStr) {
        this.fertilityStatusStr = fertilityStatusStr;
    }

    public Long getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Long maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusStr() {
        return maritalStatusStr;
    }

    public void setMaritalStatusStr(String maritalStatusStr) {
        this.maritalStatusStr = maritalStatusStr;
    }

    public Long getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(Long politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPoliticalStatusStr() {
        return politicalStatusStr;
    }

    public void setPoliticalStatusStr(String politicalStatusStr) {
        this.politicalStatusStr = politicalStatusStr;
    }

    public Date getFirstWorkingTime() {
        return firstWorkingTime;
    }

    public void setFirstWorkingTime(Date firstWorkingTime) {
        this.firstWorkingTime = firstWorkingTime;
    }

    public Long getStaffType() {
        return staffType;
    }

    public void setStaffType(Long staffType) {
        this.staffType = staffType;
    }

    public String getStaffTypeStr() {
        return staffTypeStr;
    }

    public void setStaffTypeStr(String staffTypeStr) {
        this.staffTypeStr = staffTypeStr;
    }

    public Long getSocialSecurityType() {
        return socialSecurityType;
    }

    public void setSocialSecurityType(Long socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    public String getSocialSecurityTypeStr() {
        return socialSecurityTypeStr;
    }

    public void setSocialSecurityTypeStr(String socialSecurityTypeStr) {
        this.socialSecurityTypeStr = socialSecurityTypeStr;
    }

    public Long getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(Long costCenter) {
        this.costCenter = costCenter;
    }

    public String getCostCenterStr() {
        return costCenterStr;
    }

    public void setCostCenterStr(String costCenterStr) {
        this.costCenterStr = costCenterStr;
    }

    public String getOfficePlace() {
        return officePlace;
    }

    public void setOfficePlace(String officePlace) {
        this.officePlace = officePlace;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Long getWorkType() {
        return workType;
    }

    public void setWorkType(Long workType) {
        this.workType = workType;
    }

    public String getWorkTypeStr() {
        return workTypeStr;
    }

    public void setWorkTypeStr(String workTypeStr) {
        this.workTypeStr = workTypeStr;
    }

    public Long getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Long jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatusStr() {
        return jobStatusStr;
    }

    public void setJobStatusStr(String jobStatusStr) {
        this.jobStatusStr = jobStatusStr;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Long getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Long isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public String getIsBlacklistStr() {
        return isBlacklistStr;
    }

    public void setIsBlacklistStr(String isBlacklistStr) {
        this.isBlacklistStr = isBlacklistStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}