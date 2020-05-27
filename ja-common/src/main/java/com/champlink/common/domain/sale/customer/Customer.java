package com.champlink.common.domain.sale.customer;

import com.champlink.common.domain.BaseBean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
　* 客户信息
　* @author created by barrett in 18-8-1 下午12:53
　*/
public class Customer extends BaseBean {
    private static final long serialVersionUID = 1L;

    private Integer  modifiction; //更改次数
    private String  isValid = "N";//作废标示 默认否N 作废Y

    //客户名称 Y 唯一
    @NotBlank(message = "客户名称不能为空")
    private String customerName;
    //简称 N 唯一
    private String shortName;
    //地址 N
    private String address;
    //账期 Y
    @NotNull(message = "账期不能为空")
    private int paymentDays;
    //账期单位 Y 选择框
    @NotNull(message = "账期单位不能为空")
    private Long accountUnitId;
    //所属区域id Y 下拉框
    @NotNull(message = "所属区域不能为空")
    private Long areaId;
    private String regionName;
    //邮政编码 N
    private String postalCode;
    //传真 N
    private String  fax;
    //客户等级id N 下拉框
    private Long customerLevelId;
    //税号 N
    private String  dutyParagraph;
    //发票寄送地址 N
    private String invoiceSendingAddress;
    //发票寄送邮编 N
    private String invoiceSendingPcode;
    //收货地址 N
    private String  deliveryAddress;
    //银行账户 N
    private String  bankAccount;
    //成立时间 N
    private String regTime;
    //注册资本 N
    private String  regCapital;
    //付款方式 id Y 下拉框
    @NotNull(message = "付款方式不能为空")
    private Long  paymentTypeId;
    //网址 N
    private String  webSite;
    //客户信息(名称/纳税人识别号/地址、电话/开户行及账号) N
    private String customerInfo;
    //客户币别id 下拉框
    private Long  currencyId;
    //晶澳欠款额度 Y
    @NotNull(message = "晶澳欠款额度不能为空")
    private Double debtLimit;
    //手机号码 N
    private String mobile;
    //固定电话 N
    private String fixedPhone;
    //状态 1：新增(未分配销售员)  2-签核中 3-签核不通过  4-已分配销售员
    private int customerStatus;
    //联系人list
    private List<CustomerContacts> customerContactsList;
    //英文简称
    private String shortEnName;

    //晶澳币别id 下拉框
    private Long  jaCurrencyId;
    //中信保欠款额度
    @NotNull(message = "中信保欠款额度不能为空")
    private BigDecimal zxbArrears;

    //中信保币别
    private Long zxbCurrencyId;

    //国家id
    private Long countryId;

    public String getShortEnName() {
        return shortEnName;
    }

    public void setShortEnName(String shortEnName) {
        this.shortEnName = shortEnName;
    }

    public BigDecimal getZxbArrears() {
        return zxbArrears;
    }

    public void setZxbArrears(BigDecimal zxbArrears) {
        this.zxbArrears = zxbArrears;
    }

    public Long getZxbCurrencyId() {
        return zxbCurrencyId;
    }

    public void setZxbCurrencyId(Long zxbCurrencyId) {
        this.zxbCurrencyId = zxbCurrencyId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getModifiction() {
        return modifiction;
    }

    public void setModifiction(Integer modifiction) {
        this.modifiction = modifiction;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPaymentDays() {
        return paymentDays;
    }

    public void setPaymentDays(int paymentDays) {
        this.paymentDays = paymentDays;
    }

    public Long getAccountUnitId() {
        return accountUnitId;
    }

    public void setAccountUnitId(Long accountUnitId) {
        this.accountUnitId = accountUnitId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getCustomerLevelId() {
        return customerLevelId;
    }

    public void setCustomerLevelId(Long customerLevelId) {
        this.customerLevelId = customerLevelId;
    }

    public String getDutyParagraph() {
        return dutyParagraph;
    }

    public void setDutyParagraph(String dutyParagraph) {
        this.dutyParagraph = dutyParagraph;
    }

    public String getInvoiceSendingAddress() {
        return invoiceSendingAddress;
    }

    public void setInvoiceSendingAddress(String invoiceSendingAddress) {
        this.invoiceSendingAddress = invoiceSendingAddress;
    }

    public String getInvoiceSendingPcode() {
        return invoiceSendingPcode;
    }

    public void setInvoiceSendingPcode(String invoiceSendingPcode) {
        this.invoiceSendingPcode = invoiceSendingPcode;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Double getDebtLimit() {
        return debtLimit;
    }

    public void setDebtLimit(Double debtLimit) {
        this.debtLimit = debtLimit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
    }

    public List<CustomerContacts> getCustomerContactsList() {
        return customerContactsList;
    }

    public void setCustomerContactsList(List<CustomerContacts> customerContactsList) {
        this.customerContactsList = customerContactsList;
    }

    public Long getJaCurrencyId() {
        return jaCurrencyId;
    }

    public void setJaCurrencyId(Long jaCurrencyId) {
        this.jaCurrencyId = jaCurrencyId;
    }
}
