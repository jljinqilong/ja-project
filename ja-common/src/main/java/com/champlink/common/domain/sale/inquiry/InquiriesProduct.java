package com.champlink.common.domain.sale.inquiry;

import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 询单产品list
 * @Author created by barrett in 2018/8/25 16:38
 */
public class InquiriesProduct extends BaseBean {

    //产品编号
    private String productNo;
    private Long productId;

    //电池片数id
    private Long cellNumberId;

    //电池片数名称
    private Integer cellNumber;

    //功率
    private String power;

    //单价
    private BigDecimal unitPrice;

    //单位 id
    private Long unitId;

    //数量
    private Long num;

    //产品售价
    private BigDecimal amount;

    //电池型号
    private Long batteryTypeId;
    /**
     * 电池型号名称：仅导出使用
     */
    private String batteryTypeName;

    private Long inquiriesId;

    private String totalPower;
    //赠送数量
    private Long giveNum;

    public Long getGiveNum() {
        return giveNum;
    }

    public void setGiveNum(Long giveNum) {
        this.giveNum = giveNum;
    }


    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
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

    public String getBatteryTypeName() {
        return batteryTypeName;
    }

    public void setBatteryTypeName(String batteryTypeName) {
        this.batteryTypeName = batteryTypeName;
    }
}