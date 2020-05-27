package com.champlink.staff.jxAppBean;

public class JaHrEmpSateInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 亲属姓名
     */
    private String kinsfolkName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private String birthDate;
    /**
     * 与本人关系
     */
    private String relationship;
    /**
     * 所在单位
     */
    private String placeUnit;
    /**
     * 常住地址
     */
    private String adress;
    /**
     * 职务
     */
    private String duty;
    /**
     * 联系电话
     */
    private String phoneNum;

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

    public String getKinsfolkName() {
        return kinsfolkName;
    }

    public void setKinsfolkName(String kinsfolkName) {
        this.kinsfolkName = kinsfolkName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPlaceUnit() {
        return placeUnit;
    }

    public void setPlaceUnit(String placeUnit) {
        this.placeUnit = placeUnit;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public JaHrEmpSateInfoBean() {
        super();
    }

    public JaHrEmpSateInfoBean(String guid, String empGuid, String kinsfolkName, String sex, String birthDate, String relationship, String placeUnit, String adress, String duty, String phoneNum) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.kinsfolkName = kinsfolkName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.relationship = relationship;
        this.placeUnit = placeUnit;
        this.adress = adress;
        this.duty = duty;
        this.phoneNum = phoneNum;
    }

}
