package com.champlink.common.domain.staff.contract;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;
import com.champlink.common.domain.staff.baseInfo.BaseInfo;

public class Agreement extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 人员信息ID
     */
    private Long staffId;

    private String agreementNo;

    private Long agreementType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date signDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date agreementDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date agreementDateEnd;

    private Integer agreementPeriod;// 合同期限

    private String workPlace;

    private String owner;

    private String file;

    private String describe;

    private Integer signTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    private String responsiblePerson;

    private String endReason;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date relieveDate;

    private String relieveReason;

    private String agreementState;

    private String renewStatus;

    private String relevanceContract;

    private BaseInfo baseInfo;

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

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public Integer getAgreementPeriod() {
        return agreementPeriod;
    }

    public void setAgreementPeriod(Integer agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
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

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Long getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(Long agreementType) {
        this.agreementType = agreementType;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getAgreementDateStart() {
        return agreementDateStart;
    }

    public void setAgreementDateStart(Date agreementDateStart) {
        this.agreementDateStart = agreementDateStart;
    }

    public Date getAgreementDateEnd() {
        return agreementDateEnd;
    }

    public void setAgreementDateEnd(Date agreementDateEnd) {
        this.agreementDateEnd = agreementDateEnd;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getSignTime() {
        return signTime;
    }

    public void setSignTime(Integer signTime) {
        this.signTime = signTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }

    public Date getRelieveDate() {
        return relieveDate;
    }

    public void setRelieveDate(Date relieveDate) {
        this.relieveDate = relieveDate;
    }

    public String getRelieveReason() {
        return relieveReason;
    }

    public void setRelieveReason(String relieveReason) {
        this.relieveReason = relieveReason;
    }

    public String getAgreementState() {
        return agreementState;
    }

    public void setAgreementState(String agreementState) {
        this.agreementState = agreementState;
    }

    public String getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(String renewStatus) {
        this.renewStatus = renewStatus;
    }

    public String getRelevanceContract() {
        return relevanceContract;
    }

    public void setRelevanceContract(String relevanceContract) {
        this.relevanceContract = relevanceContract;
    }

}