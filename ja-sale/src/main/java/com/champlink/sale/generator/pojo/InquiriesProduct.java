package com.champlink.sale.generator.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class InquiriesProduct {
    private Long rowId;

    private Long createdBy;

    private Date createdTime;

    private Long lastUpdateBy;

    private Date lastUpdateTime;

    private Integer delFlag;

    private Integer status;

    private String productNo;

    private Long cellNumberId;

    private Integer cellNumber;

    private String power;

    private BigDecimal unitPrice;

    private BigDecimal unit;

    private Long num;

    private BigDecimal amount;

    private Long batteryTypeId;

    private Long inquiriesId;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public Long getCellNumberId() {
        return cellNumberId;
    }

    public void setCellNumberId(Long cellNumberId) {
        this.cellNumberId = cellNumberId;
    }

    public Integer getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(Integer cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getBatteryTypeId() {
        return batteryTypeId;
    }

    public void setBatteryTypeId(Long batteryTypeId) {
        this.batteryTypeId = batteryTypeId;
    }

    public Long getInquiriesId() {
        return inquiriesId;
    }

    public void setInquiriesId(Long inquiriesId) {
        this.inquiriesId = inquiriesId;
    }
}