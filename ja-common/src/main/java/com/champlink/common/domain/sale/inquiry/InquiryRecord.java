package com.champlink.common.domain.sale.inquiry;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 询价记录
 * @Author created by barrett in 18-8-6 下午5:24
 * @Param
 * @return
 */
public class InquiryRecord extends BaseBean {

    /**
     * 询价编号 Y
     * 默认展示系统生成的编号，编号生成规则：JA+年份+月份+日期+三位流水号，如JA180706001
     * @Author created by barrett in 18-8-6 下午5:11
     */
    private String inquiryNo;

    //客户名称 Y 下拉框
    @NotNull(message = "客户名称不能为空")
    private Long customerId;
    private String customerName;

    //基地 Y 下拉框 -- 暂时不用
//    @NotNull(message = "基地不能为空")
    private Long orgId;
    private String orgName;

    //产品类型 Y 下拉框
    @NotNull(message = "产品类型不能为空")
    private Long productTypeId;
    private String productNo;

    //产品单价
    private BigDecimal productPrice;

    //订单总量 Y
    @NotNull(message = "订单总量不能为空")
    private BigDecimal orderTotal;

    //销售价格 Y
    @NotNull(message = "销售价格不能为空")
    private BigDecimal sellingPrice;

    //贸易方式 Y 下拉框
    @NotNull(message = "贸易方式不能为空")
    private Long tradeModeId;

    //首年衰减 Y
    @NotNull(message = "首年衰减不能为空")
    private String firstYearAttenuation;

    //付款条件 Y
    @NotNull(message = "付款条件不能为空")
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String inquiryTime;

    //交期数量 N
    private Long number;

    //bom要求 N
    private String bomRequire;

    //成本 N
    private String cost;

    //毛利率 N
    private String grossProfitRatio;

    //功率档位
    private String power;

    //电池类型
    private Long batteryTypeId;
    //主栅根数
    private BigDecimal mainGateNumber;
    //边框颜色 id
    private Long borderColorId;
    //边框规格 id
    private Long borderSpecificationId;

    //背板颜色 id
    private Long backboardColorId;

    //背板材质 id
    private Long backboardMaterialId;

    //接线盒 id
    private Long junctionBoxId;

    //玻璃
    private String glass;

    //eva id
    private Long evaId;

    //询价标题
    @NotNull(message = "询价标题不能为空")
    private String inquiryTitle;

    //预计销售金额
    private Double estimatedSalesAmount;

    //预计签单日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date estimatedSigningDate;

    //销售阶段 id
    @NotNull(message = "销售阶段不能为空")
    private Long salesPhaseId;

    //实际跟进时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date actualFollowTime;

    //合同状态
    private int contractStatus;
    //合同编号
    private String contractNo;
    //合同id
    private long contractId;

    //项目地 id
    private Long projectAddressId;
    private String projectAddress;

    //目的国
    private Long destinationCountryId;
    private String destinationCountry;

    //业务员 合同需要的
    private String salesman;

    public Long getProjectAddressId() {
        return projectAddressId;
    }

    public void setProjectAddressId(Long projectAddressId) {
        this.projectAddressId = projectAddressId;
    }

    public Long getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(Long destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public Long getJunctionBoxId() {
        return junctionBoxId;
    }

    public void setJunctionBoxId(Long junctionBoxId) {
        this.junctionBoxId = junctionBoxId;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public Long getBorderColorId() {
        return borderColorId;
    }

    public void setBorderColorId(Long borderColorId) {
        this.borderColorId = borderColorId;
    }

    public Long getBorderSpecificationId() {
        return borderSpecificationId;
    }

    public void setBorderSpecificationId(Long borderSpecificationId) {
        this.borderSpecificationId = borderSpecificationId;
    }

    public Long getBackboardColorId() {
        return backboardColorId;
    }

    public void setBackboardColorId(Long backboardColorId) {
        this.backboardColorId = backboardColorId;
    }

    public Long getBackboardMaterialId() {
        return backboardMaterialId;
    }

    public void setBackboardMaterialId(Long backboardMaterialId) {
        this.backboardMaterialId = backboardMaterialId;
    }

    public Long getEvaId() {
        return evaId;
    }

    public void setEvaId(Long evaId) {
        this.evaId = evaId;
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

    public Date getEstimatedSigningDate() {
        return estimatedSigningDate;
    }

    public void setEstimatedSigningDate(Date estimatedSigningDate) {
        this.estimatedSigningDate = estimatedSigningDate;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
}