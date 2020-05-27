package com.champlink.staff.jxAppBean;

/**
 * @author 17600 whg
 * @date: 2018年7月09日 下午11:37:29
 */
public class JaHrEmpCommunicationInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 办公电话
     */
    private String workPhoneNum;
    /**
     * 移动电话
     */
    private String movePhoneNum;
    /**
     * 家庭电话
     */
    private String familyPhoneNum;
    /**
     * 电子邮箱/工作
     */
    private String workEmail;
    /**
     * 家庭地址
     */
    private String familyAddress;
    /**
     * 电子邮箱/个人
     */
    private String personallyEmail;
    /**
     * 座机号
     */
    private String specialPlane;
    /**
     * 分机号
     */
    private String extension;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEmpGuid() {
        return empGuid;
    }

    public void setEmpGuid(String empGuid) {
        this.empGuid = empGuid;
    }

    public String getWorkPhoneNum() {
        return workPhoneNum;
    }

    public void setWorkPhoneNum(String workPhoneNum) {
        this.workPhoneNum = workPhoneNum;
    }

    public String getMovePhoneNum() {
        return movePhoneNum;
    }

    public void setMovePhoneNum(String movePhoneNum) {
        this.movePhoneNum = movePhoneNum;
    }

    public String getFamilyPhoneNum() {
        return familyPhoneNum;
    }

    public void setFamilyPhoneNum(String familyPhoneNum) {
        this.familyPhoneNum = familyPhoneNum;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getPersonallyEmail() {
        return personallyEmail;
    }

    public void setPersonallyEmail(String personallyEmail) {
        this.personallyEmail = personallyEmail;
    }

    public String getSpecialPlane() {
        return specialPlane;
    }

    public void setSpecialPlane(String specialPlane) {
        this.specialPlane = specialPlane;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public JaHrEmpCommunicationInfoBean() {
        super();
    }

    public JaHrEmpCommunicationInfoBean(String guid, String empGuid, String workPhoneNum, String movePhoneNum, String familyPhoneNum, String workEmail, String familyAddress, String personallyEmail,
                                        String specialPlane, String extension) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.workPhoneNum = workPhoneNum;
        this.movePhoneNum = movePhoneNum;
        this.familyPhoneNum = familyPhoneNum;
        this.workEmail = workEmail;
        this.familyAddress = familyAddress;
        this.personallyEmail = personallyEmail;
        this.specialPlane = specialPlane;
        this.extension = extension;
    }

}
