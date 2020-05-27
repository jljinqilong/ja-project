package com.champlink.common.domain.staff.baseInfo;

import com.champlink.common.domain.BaseBean;

/**
 * 内部亲属关系
 * 
 * @author natyu
 * @date 2018年7月21日 上午11:00:39
 *
 */
public class RelationshipInner extends BaseBean {

    /**
     * 亲属姓名
     */
    private String relativeName;

    /**
     * 与本人关系
     */
    private String relationship;

    /**
     * 所在单位
     */
    private String company;

    /**
     * 所在部门
     */
    private String dept;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 人员信息ID
     */
    private Long staffId;

    private String relativeId;//亲属工号

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    /**
     * 获取人员信息ID
     *
     * @return staff_id - 人员信息ID
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置人员信息ID
     *
     * @param staffId 人员信息ID
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取亲属姓名
     *
     * @return relative_name - 亲属姓名
     */
    public String getRelativeName() {
        return relativeName;
    }

    /**
     * 设置亲属姓名
     *
     * @param relativeName 亲属姓名
     */
    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    /**
     * 获取与本人关系
     *
     * @return relationship - 与本人关系
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 设置与本人关系
     *
     * @param relationship 与本人关系
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * 获取所在单位
     *
     * @return company - 所在单位
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所在单位
     *
     * @param company 所在单位
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取所在部门
     *
     * @return dept - 所在部门
     */
    public String getDept() {
        return dept;
    }

    /**
     * 设置所在部门
     *
     * @param dept 所在部门
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * 获取联系方式
     *
     * @return contact - 联系方式
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系方式
     *
     * @param contact 联系方式
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

}