package com.champlink.staff.jxAppBean;

public class JaHrEmpSkllCertficateInfoBean {
    private String guid;
    private String empGuid;
    /**
     * 证书名称
     */
    private String certficateName;
    /**
     * 证书发放日期
     */
    private String certficateStartDate;
    /**
     * 证书有效期限
     */
    private String certficateEndDate;
    /**
     * 发放机构
     */
    private String certficateOrganization;
    /**
     * 证书级别
     */
    private String certficateLevel;

    public JaHrEmpSkllCertficateInfoBean(String guid, String empGuid, String certficateName, String certficateStartDate, String certficateEndDate, String certficateOrganization,
                                         String certficateLevel) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.certficateName = certficateName;
        this.certficateStartDate = certficateStartDate;
        this.certficateEndDate = certficateEndDate;
        this.certficateOrganization = certficateOrganization;
        this.certficateLevel = certficateLevel;
    }

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

    public String getCertficateName() {
        return certficateName;
    }

    public void setCertficateName(String certficateName) {
        this.certficateName = certficateName;
    }

    public String getCertficateStartDate() {
        return certficateStartDate;
    }

    public void setCertficateStartDate(String certficateStartDate) {
        this.certficateStartDate = certficateStartDate;
    }

    public String getCertficateEndDate() {
        return certficateEndDate;
    }

    public void setCertficateEndDate(String certficateEndDate) {
        this.certficateEndDate = certficateEndDate;
    }

    public String getCertficateOrganization() {
        return certficateOrganization;
    }

    public void setCertficateOrganization(String certficateOrganization) {
        this.certficateOrganization = certficateOrganization;
    }

    public String getCertficateLevel() {
        return certficateLevel;
    }

    public void setCertficateLevel(String certficateLevel) {
        this.certficateLevel = certficateLevel;
    }

    public JaHrEmpSkllCertficateInfoBean() {
        super();
    }

}
