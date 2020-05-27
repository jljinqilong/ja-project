package com.champlink.common.domain.org;

import com.champlink.common.domain.BaseBean;

public class OrgInfo extends BaseBean {

    /**
     * 组织架构ID
     */
    private Long orgId;

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
     * 是否有效 0:有效,1:无效
     */
    private Integer isValid;

    /**
     * 编制人数
     */
    private Long deptNum;

    /**
     * 获取组织架构ID
     *
     * @return org_id - 组织架构ID
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 设置组织架构ID
     *
     * @param orgId 组织架构ID
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取汇报关系-纬度
     *
     * @return report_latitude - 汇报关系-纬度
     */
    public String getReportLatitude() {
        return reportLatitude;
    }

    /**
     * 设置汇报关系-纬度
     *
     * @param reportLatitude 汇报关系-纬度
     */
    public void setReportLatitude(String reportLatitude) {
        this.reportLatitude = reportLatitude;
    }

    /**
     * 获取汇报关系-上级
     *
     * @return report_superior - 汇报关系-上级
     */
    public String getReportSuperior() {
        return reportSuperior;
    }

    /**
     * 设置汇报关系-上级
     *
     * @param reportSuperior 汇报关系-上级
     */
    public void setReportSuperior(String reportSuperior) {
        this.reportSuperior = reportSuperior;
    }

    /**
     * 获取联系方式-联系电话
     *
     * @return contact_phone - 联系方式-联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系方式-联系电话
     *
     * @param contactPhone 联系方式-联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取联系方式-传真
     *
     * @return contact_facsimile - 联系方式-传真
     */
    public String getContactFacsimile() {
        return contactFacsimile;
    }

    /**
     * 设置联系方式-传真
     *
     * @param contactFacsimile 联系方式-传真
     */
    public void setContactFacsimile(String contactFacsimile) {
        this.contactFacsimile = contactFacsimile;
    }

    /**
     * 获取联系方式-邮编
     *
     * @return contact_zipcode - 联系方式-邮编
     */
    public String getContactZipcode() {
        return contactZipcode;
    }

    /**
     * 设置联系方式-邮编
     *
     * @param contactZipcode 联系方式-邮编
     */
    public void setContactZipcode(String contactZipcode) {
        this.contactZipcode = contactZipcode;
    }

    /**
     * 获取联系方式-网址
     *
     * @return contact_url - 联系方式-网址
     */
    public String getContactUrl() {
        return contactUrl;
    }

    /**
     * 设置联系方式-网址
     *
     * @param contactUrl 联系方式-网址
     */
    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    /**
     * 获取联系方式-地址
     *
     * @return contact_address - 联系方式-地址
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * 设置联系方式-地址
     *
     * @param contactAddress 联系方式-地址
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * 获取总负责人-工号
     *
     * @return leader_no - 总负责人-工号
     */
    public String getLeaderNo() {
        return leaderNo;
    }

    /**
     * 设置总负责人-工号
     *
     * @param leaderNo 总负责人-工号
     */
    public void setLeaderNo(String leaderNo) {
        this.leaderNo = leaderNo;
    }

    /**
     * 获取总负责人-姓名
     *
     * @return leader_name - 总负责人-姓名
     */
    public String getLeaderName() {
        return leaderName;
    }

    /**
     * 设置总负责人-姓名
     *
     * @param leaderName 总负责人-姓名
     */
    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    /**
     * 获取总负责人-联系方式
     *
     * @return leader_phone - 总负责人-联系方式
     */
    public String getLeaderPhone() {
        return leaderPhone;
    }

    /**
     * 设置总负责人-联系方式
     *
     * @param leaderPhone 总负责人-联系方式
     */
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

}