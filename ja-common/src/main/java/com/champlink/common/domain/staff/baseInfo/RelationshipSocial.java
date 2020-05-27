package com.champlink.common.domain.staff.baseInfo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

/**
 * 社会关系
 * 
 * @author natyu
 * @date 2018年7月21日 上午11:02:12
 *
 */
public class RelationshipSocial extends BaseBean {

    /**
     * 亲属姓名
     */
    private String relativeName;

    /**
     * 性别
     */
    private Long sex;

    /**
     * 出生日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rsBirthday;

    /**
     * 与本人关系
     */
    private String relationship;

    /**
     * 证件号码
     */
    private String identityNo;

    /**
     * 常住地址
     */
    private String permanentAddress;

    /**
     * 所在单位
     */
    private String company;

    /**
     * 亲属职衔
     */
    private String position;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 人员信息ID
     */
    private Long staffId;

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
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getRsBirthday() {
        return rsBirthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setRsBirthday(Date rsBirthday) {
        this.rsBirthday = rsBirthday;
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
     * 获取常住地址
     *
     * @return permanent_address - 常住地址
     */
    public String getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * 设置常住地址
     *
     * @param permanentAddress 常住地址
     */
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
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
     * 获取亲属职衔
     *
     * @return position - 亲属职衔
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置亲属职衔
     *
     * @param position 亲属职衔
     */
    public void setPosition(String position) {
        this.position = position;
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