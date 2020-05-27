package com.champlink.common.domain.staff.baseInfo;

import com.champlink.common.domain.BaseBean;

/**
 * 紧急联系人
 * 
 * @author natyu
 * @date 2018年7月21日 上午10:55:50
 *
 */
public class ContactEmergency extends BaseBean {

    /**
     * 紧急联系人姓名
     */
    private String contactName;

    /**
     * 与本人关系
     */
    private String relationship;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 人员信息ID
     */
    private Long staffId;

    private String wechatQq;//微信、qq

    public String getWechatQq() {
        return wechatQq;
    }

    public void setWechatQq(String wechatQq) {
        this.wechatQq = wechatQq;
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
     * 获取紧急联系人姓名
     *
     * @return contact_name - 紧急联系人姓名
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置紧急联系人姓名
     *
     * @param contactName 紧急联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
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
     * 获取电话号码
     *
     * @return mobile - 电话号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话号码
     *
     * @param mobile 电话号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

}