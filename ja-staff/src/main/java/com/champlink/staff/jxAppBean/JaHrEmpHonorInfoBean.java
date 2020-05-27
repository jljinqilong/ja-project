package com.champlink.staff.jxAppBean;

public class JaHrEmpHonorInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 荣誉名称
     */
    private String honorName;
    /**
     * 证书颁发日期
     */
    private String honorDate;
    /**
     * 证书颁发机构
     */
    private String honorOrganization;
    /**
     * 奖励形式
     */
    private String rewardForm;

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

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorDate() {
        return honorDate;
    }

    public void setHonorDate(String honorDate) {
        this.honorDate = honorDate;
    }

    public String getHonorOrganization() {
        return honorOrganization;
    }

    public void setHonorOrganization(String honorOrganization) {
        this.honorOrganization = honorOrganization;
    }

    public String getRewardForm() {
        return rewardForm;
    }

    public void setRewardForm(String rewardForm) {
        this.rewardForm = rewardForm;
    }

    public JaHrEmpHonorInfoBean() {
        super();
    }

    public JaHrEmpHonorInfoBean(String guid, String empGuid, String honorName, String honorDate, String honorOrganization, String rewardForm) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.honorName = honorName;
        this.honorDate = honorDate;
        this.honorOrganization = honorOrganization;
        this.rewardForm = rewardForm;
    }

}
