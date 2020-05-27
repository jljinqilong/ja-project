package com.champlink.common.domain.org;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

public class Org extends BaseBean {

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
     * 简称
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
     * 获取成立日期
     *
     * @return establish_date - 成立日期
     */
    public Date getEstablishDate() {
        return establishDate;
    }

    /**
     * 设置成立日期
     *
     * @param establishDate 成立日期
     */
    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    /**
     * 获取生效日期
     *
     * @return effective_date - 生效日期
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置生效日期
     *
     * @param effectiveDate 生效日期
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 获取业务单元
     *
     * @return business_unit - 业务单元
     */
    public String getBusinessUnit() {
        return businessUnit;
    }

    /**
     * 设置业务单元
     *
     * @param businessUnit 业务单元
     */
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    /**
     * 获取层级ID
     *
     * @return level_id - 层级ID
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * 设置层级ID
     *
     * @param levelId 层级ID
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取简介
     *
     * @return summary - 简介
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置简介
     *
     * @param summary 简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
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

    /**
     * 获取是否公司或基地：-1：公司；0：基地；1：部门
     *
     * @return is_org - 是否公司或基地：-1：公司；0：基地；1：部门
     */
    public Integer getIsOrg() {
        return isOrg;
    }

    /**
     * 设置是否公司或基地：-1：公司；0：基地；1：部门
     *
     * @param isOrg 是否公司或基地：-1：公司；0：基地；1：部门
     */
    public void setIsOrg(Integer isOrg) {
        this.isOrg = isOrg;
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

    public String getBaseOrDeptName() {
        return baseOrDeptName;
    }

    public void setBaseOrDeptName(String baseOrDeptName) {
        this.baseOrDeptName = baseOrDeptName;
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

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取父ID
     *
     * @return parent_id - 父ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId 父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取职能属性
     *
     * @return function_type - 职能属性
     */
    public Long getFunctionType() {
        return functionType;
    }

    /**
     * 设置职能属性
     *
     * @param functionType 职能属性
     */
    public void setFunctionType(Long functionType) {
        this.functionType = functionType;
    }

}