package com.champlink.common.domain.staff.baseInfo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;

public class PushStaffInfo {

    private String method;

    private String jb_gh;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJb_gh() {
        return jb_gh;
    }

    public void setJb_gh(String jb_gh) {
        this.jb_gh = jb_gh;
    }

    /**
     * 工号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 性别
     */
    private Long sex;

    /**
     * 基地ID
     */
    private Long baseId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 职衔ID
     */
    private Long positionId;
    private String positionName;

    /**
     * 职级ID
     */
    private Long rankId;
    private String rankName;

    /**
     * 职等/赋值名称ID
     */
    private Long gradeId;
    private String gradeName;

    /**
     * 证件类型
     */
    private Long identityTypeId;

    /**
     * 证件号码
     */
    private String identityNo;

    /**
     * 证件到期时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date identityValidDate;

    /**
     * 出生日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    /**
     * 国籍
     */
    private Long nationality;

    /**
     * 民族
     */
    private Long nation;

    /**
     * 户口所在地
     */
    private String registeredResidence;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 生育状况
     */
    private Long fertilityStatus;

    /**
     * 婚姻状况
     */
    private Long maritalStatus;

    /**
     * 政治面貌
     */
    private Long politicalStatus;

    /**
     * 首次工作时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstWorkingTime;

    /**
     * 员工类型
     */
    private Long staffType;

    /**
     * 社保类型
     */
    private Long socialSecurityType;

    /**
     * 成本中心
     */
    private Long costCenter;

    /**
     * 办公地点
     */
    private String officePlace;

    /**
     * 入职日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    /**
     * 用工类型
     */
    private Long workType;

    /**
     * 在职状态
     */
    private Long jobStatus;

    /**
     * 离职日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;

    /**
     * 是否加入黑名单
     */
    private Long isBlacklist;

    /**
     * 备注
     */
    private String remark;

    private Long lines;//线别

    private String staffClassify;//员工分类 

    private Long operatingPost;//工作岗位

    private String mobile;//手机号

    private String email;//邮箱

    public Long getLines() {
        return lines;
    }

    public void setLines(Long lines) {
        this.lines = lines;
    }

    public String getStaffClassify() {
        return staffClassify;
    }

    public void setStaffClassify(String staffClassify) {
        this.staffClassify = staffClassify;
    }

    public Long getOperatingPost() {
        return operatingPost;
    }

    public void setOperatingPost(Long operatingPost) {
        this.operatingPost = operatingPost;
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

    private Integer currentId;

    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    /**
     * 获取工号
     *
     * @return staff_no - 工号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置工号
     *
     * @param staffNo 工号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Long getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Long sex) {
        this.sex = sex;
    }

    /**
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取职衔ID
     *
     * @return position_id - 职衔ID
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * 设置职衔ID
     *
     * @param positionId 职衔ID
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取职级ID
     *
     * @return rank_id - 职级ID
     */
    public Long getRankId() {
        return rankId;
    }

    /**
     * 设置职级ID
     *
     * @param rankId 职级ID
     */
    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    /**
     * 获取职等ID/赋值名称
     *
     * @return grade_id - 职等ID/赋值名称
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * 设置职等ID/赋值名称
     *
     * @param gradeId 职等ID/赋值名称
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 获取证件类型
     *
     * @return identity_type_id - 证件类型
     */
    public Long getIdentityTypeId() {
        return identityTypeId;
    }

    /**
     * 设置证件类型
     *
     * @param identityTypeId 证件类型
     */
    public void setIdentityTypeId(Long identityTypeId) {
        this.identityTypeId = identityTypeId;
    }

    /**
     * 获取证件号码
     *
     * @return identity_no - 证件号码
     */
    public String getIdentityNo() {
        return identityNo;
    }

    /**
     * 设置证件号码
     *
     * @param identityNo 证件号码
     */
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    /**
     * 获取证件到期时间
     *
     * @return identity_valid_date - 证件到期时间
     */
    public Date getIdentityValidDate() {
        return identityValidDate;
    }

    /**
     * 设置证件到期时间
     *
     * @param identityValidDate 证件到期时间
     */
    public void setIdentityValidDate(Date identityValidDate) {
        this.identityValidDate = identityValidDate;
    }

    /**
     * 获取出生日期
     *
     * @return birthdate - 出生日期
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * 设置出生日期
     *
     * @param birthdate 出生日期
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 获取国籍
     *
     * @return nationality_id - 国籍
     */
    public Long getNationality() {
        return nationality;
    }

    /**
     * 设置国籍
     *
     * @param nationality 国籍
     */
    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取民族
     *
     * @return nation_id - 民族
     */
    public Long getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nationId 民族
     */
    public void setNation(Long nation) {
        this.nation = nation;
    }

    /**
     * 获取户口所在地
     *
     * @return registered_residence - 户口所在地
     */
    public String getRegisteredResidence() {
        return registeredResidence;
    }

    /**
     * 设置户口所在地
     *
     * @param registeredResidence 户口所在地
     */
    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    /**
     * 获取籍贯
     *
     * @return native_place - 籍贯
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置籍贯
     *
     * @param nativePlace 籍贯
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 获取生育状况
     *
     * @return fertility_state - 生育状况
     */
    public Long getFertilityStatus() {
        return fertilityStatus;
    }

    /**
     * 设置生育状况
     *
     * @param fertilityState 生育状况
     */
    public void setFertilityStatus(Long fertilityStatus) {
        this.fertilityStatus = fertilityStatus;
    }

    /**
     * 获取婚姻状况
     *
     * @return marital_status - 婚姻状况
     */
    public Long getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚姻状况
     *
     * @param maritalStatus 婚姻状况
     */
    public void setMaritalStatus(Long maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 获取政治面貌
     *
     * @return political_status - 政治面貌
     */
    public Long getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置政治面貌
     *
     * @param politicalStatus 政治面貌
     */
    public void setPoliticalStatus(Long politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取首次工作时间
     *
     * @return first_working_time - 首次工作时间
     */
    public Date getFirstWorkingTime() {
        return firstWorkingTime;
    }

    /**
     * 设置首次工作时间
     *
     * @param firstWorkingTime 首次工作时间
     */
    public void setFirstWorkingTime(Date firstWorkingTime) {
        this.firstWorkingTime = firstWorkingTime;
    }

    /**
     * 获取员工类型
     *
     * @return staff_type - 员工类型
     */
    public Long getStaffType() {
        return staffType;
    }

    /**
     * 设置员工类型
     *
     * @param staffType 员工类型
     */
    public void setStaffType(Long staffType) {
        this.staffType = staffType;
    }

    /**
     * 获取社保类型
     *
     * @return social_security_type - 社保类型
     */
    public Long getSocialSecurityType() {
        return socialSecurityType;
    }

    /**
     * 设置社保类型
     *
     * @param socialSecurityType 社保类型
     */
    public void setSocialSecurityType(Long socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }

    /**
     * 获取成本中心
     *
     * @return cost_center - 成本中心
     */
    public Long getCostCenter() {
        return costCenter;
    }

    /**
     * 设置成本中心
     *
     * @param costCenter 成本中心
     */
    public void setCostCenter(Long costCenter) {
        this.costCenter = costCenter;
    }

    /**
     * 获取办公地点
     *
     * @return office_place - 办公地点
     */
    public String getOfficePlace() {
        return officePlace;
    }

    /**
     * 设置办公地点
     *
     * @param officePlace 办公地点
     */
    public void setOfficePlace(String officePlace) {
        this.officePlace = officePlace;
    }

    /**
     * 获取入职日期
     *
     * @return entry_date - 入职日期
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * 设置入职日期
     *
     * @param entryDate 入职日期
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * 获取用工类型
     *
     * @return work_type - 用工类型
     */
    public Long getWorkType() {
        return workType;
    }

    /**
     * 设置用工类型
     *
     * @param workType 用工类型
     */
    public void setWorkType(Long workType) {
        this.workType = workType;
    }

    /**
     * 获取在职状态
     *
     * @return job_statue - 在职状态
     */
    public Long getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置在职状态
     *
     * @param jobStatue 在职状态
     */
    public void setJobStatus(Long jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 获取离职日期
     *
     * @return leave_date - 离职日期
     */
    public Date getLeaveDate() {
        return leaveDate;
    }

    /**
     * 设置离职日期
     *
     * @param leaveDate 离职日期
     */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * 获取是否加入黑名单
     *
     * @return is_blacklist - 是否加入黑名单
     */
    public Long getIsBlacklist() {
        return isBlacklist;
    }

    /**
     * 设置是否加入黑名单
     *
     * @param isBlacklist 是否加入黑名单
     */
    public void setIsBlacklist(Long isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
