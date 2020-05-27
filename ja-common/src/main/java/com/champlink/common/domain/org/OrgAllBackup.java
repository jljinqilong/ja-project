package com.champlink.common.domain.org;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class OrgAllBackup extends BaseBean{

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 职能属性
     */
    private Long functionType;



	/**
	 * 编码
	 */
    private String baseOrDeptCode;
    
    /**
     *简称
     */
    private String baseOrDeptShortName;
    
    


	/**
     * 基地或者部门名称
     */
    private String baseOrDeptName;
    
    
    /**
     * 成立日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date establishDate;

    /**
     * 生效日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    /**
     * 业务单元
     */
    private String businessUnit;

    /**
     * 层级ID
     */
    private Long levelId;


    /**
     * 部门地点
     */
    private String address;

    /**
     * 是否虚拟部门
     */
    private Integer isFictitious;

    /**
     * 部门职责
     */
    private String deptDuty;
    
    /**
     * 简介
     */
    private String summary;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否公司或基地：-1：公司；0：基地；1：部门
     */
    private Integer isOrg;
	
    
    /**
     * 汇报关系-纬度
     */
    private String reportLatitude;

    /**
     * 汇报关系-上级
     */
    private String reportSuperior;

    /**
     * 联系方式-联系电话
     */
    private String contactPhone;

    /**
     * 联系方式-传真
     */
    private String contactFacsimile;

    /**
     * 联系方式-邮编
     */
    private String contactZipcode;

    /**
     * 联系方式-网址
     */
    private String contactUrl;

    /**
     * 联系方式-地址
     */
    private String contactAddress;

    /**
     * 总负责人-工号
     */
    private String leaderNo;

    /**
     * 总负责人-姓名
     */
    private String leaderName;

    /**
     * 总负责人-联系方式
     */
    private String leaderPhone;

    /**
     * 是否有效   0:有效,1:无效
     */
    private Integer isValid;

    /**
     * 编制人数
     */
    private Long deptNum;
    
    /**
     * 备份时间  历史记录
     */
    private Date backUpTime;
    
    /**
     * 在编人数
     */
    private int totalStaffNum;

    
    
    /**
     * 缺编人数
     */
    private Long vacancyNum;
    
    /**
     * 超编人数
     */
    private Long excessNum;
    
	public Long getVacancyNum() {
		return vacancyNum;
	}

	public void setVacancyNum(Long vacancyNum) {
		this.vacancyNum = vacancyNum;
	}

	public Long getExcessNum() {
		return excessNum;
	}

	public void setExcessNum(Long excessNum) {
		this.excessNum = excessNum;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Long functionType) {
		this.functionType = functionType;
	}

	public String getBaseOrDeptCode() {
		return baseOrDeptCode;
	}

	public void setBaseOrDeptCode(String baseOrDeptCode) {
		this.baseOrDeptCode = baseOrDeptCode;
	}

	public String getBaseOrDeptShortName() {
		return baseOrDeptShortName;
	}

	public void setBaseOrDeptShortName(String baseOrDeptShortName) {
		this.baseOrDeptShortName = baseOrDeptShortName;
	}

	public String getBaseOrDeptName() {
		return baseOrDeptName;
	}

	public void setBaseOrDeptName(String baseOrDeptName) {
		this.baseOrDeptName = baseOrDeptName;
	}

	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsFictitious() {
		return isFictitious;
	}

	public void setIsFictitious(Integer isFictitious) {
		this.isFictitious = isFictitious;
	}

	public String getDeptDuty() {
		return deptDuty;
	}

	public void setDeptDuty(String deptDuty) {
		this.deptDuty = deptDuty;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsOrg() {
		return isOrg;
	}

	public void setIsOrg(Integer isOrg) {
		this.isOrg = isOrg;
	}

	public String getReportLatitude() {
		return reportLatitude;
	}

	public void setReportLatitude(String reportLatitude) {
		this.reportLatitude = reportLatitude;
	}

	public String getReportSuperior() {
		return reportSuperior;
	}

	public void setReportSuperior(String reportSuperior) {
		this.reportSuperior = reportSuperior;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactFacsimile() {
		return contactFacsimile;
	}

	public void setContactFacsimile(String contactFacsimile) {
		this.contactFacsimile = contactFacsimile;
	}

	public String getContactZipcode() {
		return contactZipcode;
	}

	public void setContactZipcode(String contactZipcode) {
		this.contactZipcode = contactZipcode;
	}

	public String getContactUrl() {
		return contactUrl;
	}

	public void setContactUrl(String contactUrl) {
		this.contactUrl = contactUrl;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getLeaderNo() {
		return leaderNo;
	}

	public void setLeaderNo(String leaderNo) {
		this.leaderNo = leaderNo;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Long getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(Long deptNum) {
		this.deptNum = deptNum;
	}

	public Date getBackUpTime() {
		return backUpTime;
	}

	public void setBackUpTime(Date backUpTime) {
		this.backUpTime = backUpTime;
	}

	public int getTotalStaffNum() {
		return totalStaffNum;
	}

	public void setTotalStaffNum(int totalStaffNum) {
		this.totalStaffNum = totalStaffNum;
	}
    
    
    
}
