package com.champlink.common.form.sale.inquiry;

import com.champlink.common.form.BaseForm;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class InquiryRecordForm extends BaseForm {



    private Long createdBy;
    private Integer delFlag;
    /**
     * 询价编号 Y
     * 默认展示系统生成的编号，编号生成规则：JA+年份+月份+日期+三位流水号，如JA180706001
     * @Author created by barrett in 18-8-6 下午5:11
     */
    private String inquiryNo;

    //客户名称 Y 下拉框
    private Long customerId;
    private String customerName;

    //基地 Y 下拉框
    private Long orgId;
    private String orgName;

    //产品类型 Y 下拉框
    private Long productTypeId;
    private String productTypeName;

    //订单总量 Y
    private BigDecimal orderTotal;

    //销售价格 Y
    private BigDecimal sellingPrice;

    //贸易方式 Y 下拉框
    private Long tradeModeId;

    //首年衰减 Y
    private String firstYearAttenuation;

    //付款条件 Y
    private String paymentTerm;

    //认证要求 N
    private String certificationRequire;

    //送功率 N
    private String outputPower;

    //标板要求 N
    private String plateRequire;

    //质保要求 N
    private String warrantyRequire;

    //特殊要求 N
    private String specialRequire;

    //销售区域 Y 根据客户默认带出
    private Long saleAreaId;
    private String saleAreaName;

    //业务员 Y 当前登录用户
    private Long salesmanId;
    private String salesmanName;

    //违约条款  N
    private String violateClause;

    //交期日期 N
    private String inquiryTime;

    //交期数量 N
    private Long number;

    //bom要求 N
    private String bomRequire;

    //成本 N
    private String cost;

    //毛利率 N
    private String grossProfitRatio;

    private String power;

    private Long batteryTypeId;

    private BigDecimal mainGateNumber;

    private String borderColorId;

    private String borderSpecificationId;

    private String backboardColorId;

    private String backboardMaterialId;

    private String junctionBox;

    private String glass;

    private String evaId;
    //询价标题
    private String inquiryTitle;

    //预计销售金额
    private Double estimatedSalesAmount;

    //预计签单日期
    private String estimatedSigningDateStart;
    //区间
    private String estimatedSigningDateEnd;

    //销售阶段 id
    private Long salesPhaseId;

    //实际更新时间
    private Date actualFollowTime;

    //合同状态
    private int contractStatus;
    //合同编号
    private String contractNo;
    //合同id
    private long contractId;

    public int getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(int contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public Double getEstimatedSalesAmount() {
        return estimatedSalesAmount;
    }

    public void setEstimatedSalesAmount(Double estimatedSalesAmount) {
        this.estimatedSalesAmount = estimatedSalesAmount;
    }

    public String getEstimatedSigningDateStart() {
        return estimatedSigningDateStart;
    }

    public void setEstimatedSigningDateStart(String estimatedSigningDateStart) {
        this.estimatedSigningDateStart = estimatedSigningDateStart;
    }

    public String getEstimatedSigningDateEnd() {
        return estimatedSigningDateEnd;
    }

    public void setEstimatedSigningDateEnd(String estimatedSigningDateEnd) {
        this.estimatedSigningDateEnd = estimatedSigningDateEnd;
    }

    public Long getSalesPhaseId() {
        return salesPhaseId;
    }

    public void setSalesPhaseId(Long salesPhaseId) {
        this.salesPhaseId = salesPhaseId;
    }

    public Date getActualFollowTime() {
        return actualFollowTime;
    }

    public void setActualFollowTime(Date actualFollowTime) {
        this.actualFollowTime = actualFollowTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(String inquiryNo) {
        this.inquiryNo = inquiryNo == null ? null : inquiryNo.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getTradeModeId() {
        return tradeModeId;
    }

    public void setTradeModeId(Long tradeModeId) {
        this.tradeModeId = tradeModeId;
    }

    public String getFirstYearAttenuation() {
        return firstYearAttenuation;
    }

    public void setFirstYearAttenuation(String firstYearAttenuation) {
        this.firstYearAttenuation = firstYearAttenuation == null ? null : firstYearAttenuation.trim();
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm == null ? null : paymentTerm.trim();
    }

    public String getCertificationRequire() {
        return certificationRequire;
    }

    public void setCertificationRequire(String certificationRequire) {
        this.certificationRequire = certificationRequire == null ? null : certificationRequire.trim();
    }

    public String getOutputPower() {
        return outputPower;
    }

    public void setOutputPower(String outputPower) {
        this.outputPower = outputPower == null ? null : outputPower.trim();
    }

    public String getPlateRequire() {
        return plateRequire;
    }

    public void setPlateRequire(String plateRequire) {
        this.plateRequire = plateRequire == null ? null : plateRequire.trim();
    }

    public String getWarrantyRequire() {
        return warrantyRequire;
    }

    public void setWarrantyRequire(String warrantyRequire) {
        this.warrantyRequire = warrantyRequire == null ? null : warrantyRequire.trim();
    }

    public String getSpecialRequire() {
        return specialRequire;
    }

    public void setSpecialRequire(String specialRequire) {
        this.specialRequire = specialRequire == null ? null : specialRequire.trim();
    }

    public Long getSaleAreaId() {
        return saleAreaId;
    }

    public void setSaleAreaId(Long saleAreaId) {
        this.saleAreaId = saleAreaId;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getViolateClause() {
        return violateClause;
    }

    public void setViolateClause(String violateClause) {
        this.violateClause = violateClause == null ? null : violateClause.trim();
    }

    public String getInquiryTime() {
        return inquiryTime;
    }

    public void setInquiryTime(String inquiryTime) {
        this.inquiryTime = inquiryTime == null ? null : inquiryTime.trim();
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getBomRequire() {
        return bomRequire;
    }

    public void setBomRequire(String bomRequire) {
        this.bomRequire = bomRequire == null ? null : bomRequire.trim();
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    public String getGrossProfitRatio() {
        return grossProfitRatio;
    }

    public void setGrossProfitRatio(String grossProfitRatio) {
        this.grossProfitRatio = grossProfitRatio == null ? null : grossProfitRatio.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getSaleAreaName() {
        return saleAreaName;
    }

    public void setSaleAreaName(String saleAreaName) {
        this.saleAreaName = saleAreaName;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Long getBatteryTypeId() {
        return batteryTypeId;
    }

    public void setBatteryTypeId(Long batteryTypeId) {
        this.batteryTypeId = batteryTypeId;
    }

    public BigDecimal getMainGateNumber() {
        return mainGateNumber;
    }

    public void setMainGateNumber(BigDecimal mainGateNumber) {
        this.mainGateNumber = mainGateNumber;
    }

    public String getBorderColorId() {
        return borderColorId;
    }

    public void setBorderColorId(String borderColorId) {
        this.borderColorId = borderColorId;
    }

    public String getBorderSpecificationId() {
        return borderSpecificationId;
    }

    public void setBorderSpecificationId(String borderSpecificationId) {
        this.borderSpecificationId = borderSpecificationId;
    }

    public String getBackboardColorId() {
        return backboardColorId;
    }

    public void setBackboardColorId(String backboardColorId) {
        this.backboardColorId = backboardColorId;
    }

    public String getBackboardMaterialId() {
        return backboardMaterialId;
    }

    public void setBackboardMaterialId(String backboardMaterialId) {
        this.backboardMaterialId = backboardMaterialId;
    }

    public String getJunctionBox() {
        return junctionBox;
    }

    public void setJunctionBox(String junctionBox) {
        this.junctionBox = junctionBox;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getEvaId() {
        return evaId;
    }

    public void setEvaId(String evaId) {
        this.evaId = evaId;
    }
}