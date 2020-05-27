package com.champlink.common.domain.staff.baseInfo;

import com.champlink.common.domain.BaseBean;

/**
 * 通讯信息
 * 
 * @author natyu
 * @date 2018年7月21日 上午10:53:13
 *
 */
public class Address extends BaseBean {

    /**
     * 办公电话
     */
    private String workPhone;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 家庭电话
     */
    private String homePhone;

    /**
     * 电子邮件（工作）
     */
    private String emailWork;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 电子邮件（个人）
     */
    private String emailPersonal;

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
     * 获取办公电话
     *
     * @return work_phone - 办公电话
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * 设置办公电话
     *
     * @param workPhone 办公电话
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * 获取移动电话
     *
     * @return mobile - 移动电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置移动电话
     *
     * @param mobile 移动电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取家庭电话
     *
     * @return home_phone - 家庭电话
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * 设置家庭电话
     *
     * @param homePhone 家庭电话
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * 获取电子邮件（工作）
     *
     * @return email_work - 电子邮件（工作）
     */
    public String getEmailWork() {
        return emailWork;
    }

    /**
     * 设置电子邮件（工作）
     *
     * @param emailWork 电子邮件（工作）
     */
    public void setEmailWork(String emailWork) {
        this.emailWork = emailWork;
    }

    /**
     * 获取家庭地址
     *
     * @return home_address - 家庭地址
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * 设置家庭地址
     *
     * @param homeAddress 家庭地址
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * 获取电子邮件（个人）
     *
     * @return email_personal - 电子邮件（个人）
     */
    public String getEmailPersonal() {
        return emailPersonal;
    }

    /**
     * 设置电子邮件（个人）
     *
     * @param emailPersonal 电子邮件（个人）
     */
    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

}