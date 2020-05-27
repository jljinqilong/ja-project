package com.champlink.common.domain.staff.contract;

import com.champlink.common.domain.BaseBean;

public class RecordLang extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 证书名称
     */
    private String certificateName;

    /**
     * 语种
     */
    private String language;

    /**
     * 等级程度
     */
    private String rank;

    /**
     * 考试分数
     */
    private String grade;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取证书名称
     *
     * @return certificate_name - 证书名称
     */
    public String getCertificateName() {
        return certificateName;
    }

    /**
     * 设置证书名称
     *
     * @param certificateName 证书名称
     */
    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    /**
     * 获取语种
     *
     * @return language - 语种
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语种
     *
     * @param language 语种
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取等级程度
     *
     * @return rank - 等级程度
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置等级程度
     *
     * @param rank 等级程度
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取考试分数
     *
     * @return grade - 考试分数
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置考试分数
     *
     * @param grade 考试分数
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

}