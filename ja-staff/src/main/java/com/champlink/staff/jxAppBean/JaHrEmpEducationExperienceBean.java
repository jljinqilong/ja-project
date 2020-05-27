package com.champlink.staff.jxAppBean;

/**
 * @author 17600 whg
 * @date: 2018年7月09日 下午11:01:00
 */

public class JaHrEmpEducationExperienceBean {
    private String guid;
    private String empGuid;
    /**
     * 毕业学校名称
     */
    private String schoolName;
    /**
     * 专业/方向
     */
    private String specialty;
    /**
     * 入学时间
     */
    private String schoolStartDate;
    /**
     * 毕业时间
     */
    private String schoolEndDate;
    /**
     * 学位
     */
    private String degree;
    /**
     * 学历
     */
    private String education;
    /**
     * 毕业情况
     */
    private String graduationTitle;
    /**
     * 所获得学历证书类型
     */
    private String educationCertificate;
    /**
     * 学位授权国家
     */
    private String degreeAuthorizedCountry;
    /**
     * 学习方式
     */
    private String educationWay;
    /**
     * 是否最高学位
     */
    private String highDegree;
    /**
     * 是否最高学历
     */
    private String highEducation;

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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchoolStartDate() {
        return schoolStartDate;
    }

    public void setSchoolStartDate(String schoolStartDate) {
        this.schoolStartDate = schoolStartDate;
    }

    public String getSchoolEndDate() {
        return schoolEndDate;
    }

    public void setSchoolEndDate(String schoolEndDate) {
        this.schoolEndDate = schoolEndDate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduationTitle() {
        return graduationTitle;
    }

    public void setGraduationTitle(String graduationTitle) {
        this.graduationTitle = graduationTitle;
    }

    public String getEducationCertificate() {
        return educationCertificate;
    }

    public void setEducationCertificate(String educationCertificate) {
        this.educationCertificate = educationCertificate;
    }

    public String getDegreeAuthorizedCountry() {
        return degreeAuthorizedCountry;
    }

    public void setDegreeAuthorizedCountry(String degreeAuthorizedCountry) {
        this.degreeAuthorizedCountry = degreeAuthorizedCountry;
    }

    public String getEducationWay() {
        return educationWay;
    }

    public void setEducationWay(String educationWay) {
        this.educationWay = educationWay;
    }

    public String getHighDegree() {
        return highDegree;
    }

    public void setHighDegree(String highDegree) {
        this.highDegree = highDegree;
    }

    public String getHighEducation() {
        return highEducation;
    }

    public void setHighEducation(String highEducation) {
        this.highEducation = highEducation;
    }

    public JaHrEmpEducationExperienceBean(String guid, String empGuid, String schoolName, String specialty, String schoolStartDate, String schoolEndDate, String degree, String education,
                                          String graduationTitle, String educationCertificate, String degreeAuthorizedCountry, String educationWay, String highDegree, String highEducation) {
        super();
        this.guid = guid;
        this.empGuid = empGuid;
        this.schoolName = schoolName;
        this.specialty = specialty;
        this.schoolStartDate = schoolStartDate;
        this.schoolEndDate = schoolEndDate;
        this.degree = degree;
        this.education = education;
        this.graduationTitle = graduationTitle;
        this.educationCertificate = educationCertificate;
        this.degreeAuthorizedCountry = degreeAuthorizedCountry;
        this.educationWay = educationWay;
        this.highDegree = highDegree;
        this.highEducation = highEducation;
    }

    public JaHrEmpEducationExperienceBean() {
        super();
    }

}
