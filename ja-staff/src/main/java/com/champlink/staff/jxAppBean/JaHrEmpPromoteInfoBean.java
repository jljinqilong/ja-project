package com.champlink.staff.jxAppBean;

public class JaHrEmpPromoteInfoBean {

    private String guid;
    private String empGuid;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 任职基地
     */
    private String dase;
    /**
     * 任职部门
     */
    private String department;
    /**
     * 职衔
     */
    private String jobPosition;
    /**
     * 职级
     */
    private String jobRank;
    /**
     * 职等/赋值名称
     */
    private String jobEtc;
    /**
     * 职称
     */
    private String jobName;

    public String getEmpGuid() {
        return empGuid;
    }

    public JaHrEmpPromoteInfoBean(String guid, String empGuid, String startDate, String endDate, String dase, String department, String jobPosition, String jobRank, String jobEtc, String jobName) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dase = dase;
        this.department = department;
        this.jobPosition = jobPosition;
        this.jobRank = jobRank;
        this.jobEtc = jobEtc;
        this.jobName = jobName;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDase() {
        return dase;
    }

    public void setDase(String dase) {
        this.dase = dase;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobRank() {
        return jobRank;
    }

    public void setJobRank(String jobRank) {
        this.jobRank = jobRank;
    }

    public String getJobEtc() {
        return jobEtc;
    }

    public void setJobEtc(String jobEtc) {
        this.jobEtc = jobEtc;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JaHrEmpPromoteInfoBean() {
        super();
    }

}
