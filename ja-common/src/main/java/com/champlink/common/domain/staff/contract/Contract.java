package com.champlink.common.domain.staff.contract;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;

public class Contract extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同类型
     */
    private Long contractType;

    /**
     * 合同期限类型(固定期限 、无固定期限 、以完成一定工作任务为期限)
     */
    private Long contractPeriodType;

    /**
     * 签订日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date signDate;

    /**
     * 合同生效日期（YYYY-MM-DD）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date contractDateStart;

    /**
     * 合同终止日期（YYYY-MM-DD）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date contractDateEnd;

    /**
     * 合同期限
     */
    private Integer contractPeriod;

    /**
     * 工作地点
     */
    private String workPlace;

    /**
     * 甲方（展示所有基地名称）
     */
    private String owner;

    /**
     * 员工已领取合同备份（是、否）
     */
    private Long getcontractBackups;

    /**
     * 需要签订相关协议(是、否)
     */
    private Long signAgreement;

    /**
     * 附件
     */
    private String file;

    /**
     * 描述
     */
    private String describe;

    /**
     * 连续签订次数
     */
    private Integer signTime;

    /**
     * 终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 经办人
     */
    private String responsiblePerson;

    /**
     * 终止原因
     */
    private String endReason;

    /**
     * 解除日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date relieveDate;

    /**
     * 解除原因
     */
    private String relieveReason;

    /**
     * 合同状态（未生效 、履行中 、已解除 、已终止）
     */
    private String contractState;

    /**
     * 续签状态（已续签 、未续签）
     */
    private String renewStatus;

    /**
     * 合同版本号
     */
    private Long versionNumber;

    /**
     * 绑定协议
     */
    private String relevanceAgreement;

    private BaseInfo baseInfo;

    private List<Agreement> agreement;

    /**
     * 中止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date discontinueDate;

    /**
     * 中止原因
     */
    private String discontinueReason;

    public Date getDiscontinueDate() {
        return discontinueDate;
    }

    public void setDiscontinueDate(Date discontinueDate) {
        this.discontinueDate = discontinueDate;
    }

    public String getDiscontinueReason() {
        return discontinueReason;
    }

    public void setDiscontinueReason(String discontinueReason) {
        this.discontinueReason = discontinueReason;
    }

    public List<Agreement> getAgreement() {
        return agreement;
    }

    public void setAgreement(List<Agreement> agreement) {
        this.agreement = agreement;
    }

    public String getRelevanceAgreement() {
        return relevanceAgreement;
    }

    public void setRelevanceAgreement(String relevanceAgreement) {
        this.relevanceAgreement = relevanceAgreement;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取合同编号
     *
     * @return contract_no - 合同编号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置合同编号
     *
     * @param contractNo 合同编号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * 获取合同类型
     *
     * @return contract_type - 合同类型
     */
    public Long getContractType() {
        return contractType;
    }

    /**
     * 设置合同类型
     *
     * @param contractType 合同类型
     */
    public void setContractType(Long contractType) {
        this.contractType = contractType;
    }

    /**
     * 获取合同期限类型(固定期限 、无固定期限 、以完成一定工作任务为期限)
     *
     * @return contract_period_type - 合同期限类型(固定期限 、无固定期限 、以完成一定工作任务为期限)
     */
    public Long getContractPeriodType() {
        return contractPeriodType;
    }

    /**
     * 设置合同期限类型(固定期限 、无固定期限 、以完成一定工作任务为期限)
     *
     * @param contractPeriodType 合同期限类型(固定期限 、无固定期限 、以完成一定工作任务为期限)
     */
    public void setContractPeriodType(Long contractPeriodType) {
        this.contractPeriodType = contractPeriodType;
    }

    /**
     * 获取签订日期
     *
     * @return sign_date - 签订日期
     */
    public Date getSignDate() {
        return signDate;
    }

    /**
     * 设置签订日期
     *
     * @param signDate 签订日期
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * 获取合同生效日期（YYYY-MM-DD）
     *
     * @return contract_date_start - 合同生效日期（YYYY-MM-DD）
     */
    public Date getContractDateStart() {
        return contractDateStart;
    }

    /**
     * 设置合同生效日期（YYYY-MM-DD）
     *
     * @param contractDateStart 合同生效日期（YYYY-MM-DD）
     */
    public void setContractDateStart(Date contractDateStart) {
        this.contractDateStart = contractDateStart;
    }

    /**
     * 获取合同终止日期（YYYY-MM-DD）
     *
     * @return contract_date_end - 合同终止日期（YYYY-MM-DD）
     */
    public Date getContractDateEnd() {
        return contractDateEnd;
    }

    /**
     * 设置合同终止日期（YYYY-MM-DD）
     *
     * @param contractDateEnd 合同终止日期（YYYY-MM-DD）
     */
    public void setContractDateEnd(Date contractDateEnd) {
        this.contractDateEnd = contractDateEnd;
    }

    /**
     * 获取合同期限
     *
     * @return contract_period - 合同期限
     */
    public Integer getContractPeriod() {
        return contractPeriod;
    }

    /**
     * 设置合同期限
     *
     * @param contractPeriod 合同期限
     */
    public void setContractPeriod(Integer contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    /**
     * 获取工作地点
     *
     * @return work_place - 工作地点
     */
    public String getWorkPlace() {
        return workPlace;
    }

    /**
     * 设置工作地点
     *
     * @param workPlace 工作地点
     */
    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    /**
     * 获取甲方（展示所有基地名称）
     *
     * @return owner - 甲方（展示所有基地名称）
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置甲方（展示所有基地名称）
     *
     * @param owner 甲方（展示所有基地名称）
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取员工已领取合同备份（是、否）
     *
     * @return getContract_backups - 员工已领取合同备份（是、否）
     */
    public Long getGetcontractBackups() {
        return getcontractBackups;
    }

    /**
     * 设置员工已领取合同备份（是、否）
     *
     * @param getcontractBackups 员工已领取合同备份（是、否）
     */
    public void setGetcontractBackups(Long getcontractBackups) {
        this.getcontractBackups = getcontractBackups;
    }

    /**
     * 获取需要签订相关协议(是、否)
     *
     * @return sign_agreement - 需要签订相关协议(是、否)
     */
    public Long getSignAgreement() {
        return signAgreement;
    }

    /**
     * 设置需要签订相关协议(是、否)
     *
     * @param signAgreement 需要签订相关协议(是、否)
     */
    public void setSignAgreement(Long signAgreement) {
        this.signAgreement = signAgreement;
    }

    /**
     * 获取附件
     *
     * @return file - 附件
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置附件
     *
     * @param file 附件
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * 获取描述
     *
     * @return describe - 描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置描述
     *
     * @param describe 描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取连续签订次数
     *
     * @return sign_time - 连续签订次数
     */
    public Integer getSignTime() {
        return signTime;
    }

    /**
     * 设置连续签订次数
     *
     * @param signTime 连续签订次数
     */
    public void setSignTime(Integer signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取终止日期
     *
     * @return end_date - 终止日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置终止日期
     *
     * @param endDate 终止日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取经办人
     *
     * @return responsible_person - 经办人
     */
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    /**
     * 设置经办人
     *
     * @param responsiblePerson 经办人
     */
    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    /**
     * 获取终止原因
     *
     * @return end_reason - 终止原因
     */
    public String getEndReason() {
        return endReason;
    }

    /**
     * 设置终止原因
     *
     * @param endReason 终止原因
     */
    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }

    /**
     * 获取解除日期
     *
     * @return relieve_date - 解除日期
     */
    public Date getRelieveDate() {
        return relieveDate;
    }

    /**
     * 设置解除日期
     *
     * @param relieveDate 解除日期
     */
    public void setRelieveDate(Date relieveDate) {
        this.relieveDate = relieveDate;
    }

    /**
     * 获取解除原因
     *
     * @return relieve_reason - 解除原因
     */
    public String getRelieveReason() {
        return relieveReason;
    }

    /**
     * 设置解除原因
     *
     * @param relieveReason 解除原因
     */
    public void setRelieveReason(String relieveReason) {
        this.relieveReason = relieveReason;
    }

    /**
     * 获取合同状态（未生效 、履行中 、已解除 、已终止）
     *
     * @return contract_state - 合同状态（未生效 、履行中 、已解除 、已终止）
     */
    public String getContractState() {
        return contractState;
    }

    /**
     * 设置合同状态（未生效 、履行中 、已解除 、已终止）
     *
     * @param contractState 合同状态（未生效 、履行中 、已解除 、已终止）
     */
    public void setContractState(String contractState) {
        this.contractState = contractState;
    }

    /**
     * 获取续签状态（已续签 、未续签）
     *
     * @return renew_status - 续签状态（已续签 、未续签）
     */
    public String getRenewStatus() {
        return renewStatus;
    }

    /**
     * 设置续签状态（已续签 、未续签）
     *
     * @param renewStatus 续签状态（已续签 、未续签）
     */
    public void setRenewStatus(String renewStatus) {
        this.renewStatus = renewStatus;
    }

    /**
     * 获取合同版本号
     *
     * @return versionNumber 合同版本号
     */
    public Long getVersionNumber() {
        return versionNumber;
    }

    /**
     * 设置合同版本号
     *
     * @param renewStatus 合同版本号
     */
    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

}