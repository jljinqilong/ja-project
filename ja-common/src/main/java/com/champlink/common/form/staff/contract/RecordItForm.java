package com.champlink.common.form.staff.contract;

import com.champlink.common.form.BaseForm;

public class RecordItForm extends BaseForm {

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 证书名称
     */
    private String certificateName;

    /**
     * 计算机等级
     */
    private String computerRank;

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
     * 获取计算机等级
     *
     * @return computer_rank - 计算机等级
     */
    public String getComputerRank() {
        return computerRank;
    }

    /**
     * 设置计算机等级
     *
     * @param computerRank 计算机等级
     */
    public void setComputerRank(String computerRank) {
        this.computerRank = computerRank;
    }

}