package com.champlink.staff.jxAppBean;

//HR_EMP_SOCIAL_HISTORY_INFO

public class JaHrEmpSoclalHistoryInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 工作单位
     */
    private String corpName;
    /**
     * 任职部门
     */
    private String officeDepartment;
    /**
     * 任职职衔
     */
    private String officeJob;
    /**
     * 开始时间
     */
    private String jobStartDate;
    /**
     * 结束时间
     */
    private String jobEndDate;
    /**
     * 证明人
     */
    private String certifierName;
    /**
     * 证明人联系方式
     */
    private String certifierPhoneNum;
    /**
     * 薪酬状况
     */
    private String salary;

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

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getOfficeDepartment() {
        return officeDepartment;
    }

    public void setOfficeDepartment(String officeDepartment) {
        this.officeDepartment = officeDepartment;
    }

    public String getOfficeJob() {
        return officeJob;
    }

    public void setOfficeJob(String officeJob) {
        this.officeJob = officeJob;
    }

    public String getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(String jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    public String getJobEndDate() {
        return jobEndDate;
    }

    public void setJobEndDate(String jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public String getCertifierPhoneNum() {
        return certifierPhoneNum;
    }

    public void setCertifierPhoneNum(String certifierPhoneNum) {
        this.certifierPhoneNum = certifierPhoneNum;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public JaHrEmpSoclalHistoryInfoBean() {
        super();
    }

    public String getCertifierName() {
        return certifierName;
    }

    public void setCertifierName(String certifierName) {
        this.certifierName = certifierName;
    }

    public JaHrEmpSoclalHistoryInfoBean(String guid, String empGuid, String corpName, String officeDepartment, String officeJob, String jobStartDate, String jobEndDate, String certifierName,
                                        String certifierPhoneNum, String salary) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.corpName = corpName;
        this.officeDepartment = officeDepartment;
        this.officeJob = officeJob;
        this.jobStartDate = jobStartDate;
        this.jobEndDate = jobEndDate;
        this.certifierName = certifierName;
        this.certifierPhoneNum = certifierPhoneNum;
        this.salary = salary;
    }

}
