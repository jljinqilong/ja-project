package com.champlink.staff.jxAppBean;

/**
 * @author 17600 whg
 * @date: 2018年7月09日 下午11:09:19
 */
public class JaHrEmpEmerInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 联系人姓名
     */
    private String name;
    /**
     * 与本人关系
     */
    private String relationship;
    /**
     * 地址
     */
    private String adress;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 微信/qq
     */
    private String wechatQqNum;

    public String getEmpGuid() {
        return empGuid;
    }

    public void setEmpGuid(String empGuid) {
        this.empGuid = empGuid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getWechatQqNum() {
        return wechatQqNum;
    }

    public void setWechatQqNum(String wechatQqNum) {
        this.wechatQqNum = wechatQqNum;
    }

    public JaHrEmpEmerInfoBean() {
        super();
    }

    public JaHrEmpEmerInfoBean(String guid, String empGuid, String name, String relationship, String adress, String phoneNum, String wechatQqNum) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.name = name;
        this.relationship = relationship;
        this.adress = adress;
        this.phoneNum = phoneNum;
        this.wechatQqNum = wechatQqNum;
    }

}
