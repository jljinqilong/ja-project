package com.champlink.staff.jxAppBean;

public class JaHrempKinsfolkRelationInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 亲属姓名
     */
    private String kinsfolkName;
    /**
     * 亲属工号
     */
    private String jobNum;
    /**
     * 与本人关系
     */
    private String relationship;
    /**
     * 所在单位
     */
    private String unit;
    /**
     * 所在部门
     */
    private String department;
    /**
     * 联系方式
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

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public JaHrempKinsfolkRelationInfoBean() {
        super();
    }

    public JaHrempKinsfolkRelationInfoBean(String guid, String empGuid, String kinsfolkName, String jobNum, String relationship, String unit, String department, String phoneNum) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.kinsfolkName = kinsfolkName;
        this.jobNum = jobNum;
        this.relationship = relationship;
        this.unit = unit;
        this.department = department;
        this.phoneNum = phoneNum;
    }

}
