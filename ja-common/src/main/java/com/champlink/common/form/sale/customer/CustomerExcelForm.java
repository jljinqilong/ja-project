package com.champlink.common.form.sale.customer;

import com.champlink.common.domain.BaseBean;
import com.champlink.common.util.annotations.Excel;

/**
　* 客户信息Excel导入模板
　* 
　*/
public class CustomerExcelForm extends BaseBean {
    private static final long serialVersionUID = 1L;
    
    /**
	 * 序列
	 */
	@Excel(title = "序列", isNull = false)
	private Integer order;
    /**
     * 客户名称 Y 唯一
     */
    @Excel(title = "客户名称", isNull = false)
    private String customerName;
    
    /**
     * 简称 N 唯一
     */
    @Excel(title = "简称")
    private String shortName;
    
    /**
     * 英文简称
     */
    @Excel(title = "英文简称")
    private String shortEnName;
    
    /**
     * 地址 N
     */
    @Excel(title = "地址")
    private String address;
    
    /**
     * 账期 Y
     */
    @Excel(title = "账期", isNull = false)
    private String paymentDays;
    
    /**
     * 账期单位 Y 选择框
     */
    @Excel(title = "账期单位", isNull = false)
    private String accountUnitId;
    
    /**
     * 区域名称
     */
    @Excel(title = "区域名称", isNull = false)
    private String regionName;
    
    /**
     * 邮政编码 N
     */
    @Excel(title = "邮政编码")
    private String postalCode;
    
    /**
     * 传真 N
     */
    @Excel(title = "传真")
    private String  fax;
    
    /**
     * 客户等级
     */
    @Excel(title = "客户等级")
    private String customerLevel;
    
    /**
     * 税号 N
     */
    @Excel(title = "税号")
    private String  dutyParagraph;
    
    /**
     * 发票寄送地址 N
     */
    @Excel(title = "发票寄送地址")
    private String invoiceSendingAddress;
    
    /**
     * 发票寄送邮编 N
     */
    @Excel(title = "发票寄送邮编")
    private String invoiceSendingPcode;
    
    /**
     * 收货地址 N
     */
    @Excel(title = "收货地址")
    private String  deliveryAddress;
    
    /**
     * 银行账户 N
     */
    @Excel(title = "银行账户")
    private String  bankAccount;
    
    /**
     * 成立时间 N
     */
    @Excel(title = "成立时间")
    private String regTime;
    
    /**
     * 注册资本 N
     */
    @Excel(title = "注册资本")
    private String  regCapital;
    
    /**
     * 付款方式 id Y 下拉框
     */
    @Excel(title = "付款方式", isNull = false)
    private String  paymentTypeId;
    
    /**
     * 网址 N
     */
    @Excel(title = "网址")
    private String  webSite;
    
    /**
     * 客户信息(名称/纳税人识别号/地址、电话/开户行及账号) N
     */
    @Excel(title = "客户信息")
    private String customerInfo;
    
    /**
     * 晶澳欠款额度 Y
     */
    @Excel(title = "晶澳欠款额度", isNull = false)
    private String debtLimit;
    
    /**
     * 中信保欠款额度
     */
    @Excel(title = "中信保欠款额度", isNull = false)
    private String zxbArrears;
    
    /**
     * 晶澳币别id 
     */
    @Excel(title = "晶澳币别币")
    private String  jaCurrency;
    
    /**
     * 中信保币别
     */
    @Excel(title = "中信保币别")
    private String zxbCurrency;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

	public String getShortEnName() {
		return shortEnName;
	}

	public void setShortEnName(String shortEnName) {
		this.shortEnName = shortEnName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentDays() {
		return paymentDays;
	}

	public void setPaymentDays(String paymentDays) {
		this.paymentDays = paymentDays;
	}

	public String getAccountUnitId() {
		return accountUnitId;
	}

	public void setAccountUnitId(String accountUnitId) {
		this.accountUnitId = accountUnitId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
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

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
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

	public String getDebtLimit() {
		return debtLimit;
	}

	public void setDebtLimit(String debtLimit) {
		this.debtLimit = debtLimit;
	}

	public String getZxbArrears() {
		return zxbArrears;
	}

	public void setZxbArrears(String zxbArrears) {
		this.zxbArrears = zxbArrears;
	}

	public String getJaCurrency() {
		return jaCurrency;
	}

	public void setJaCurrency(String jaCurrency) {
		this.jaCurrency = jaCurrency;
	}

	public String getZxbCurrency() {
		return zxbCurrency;
	}

	public void setZxbCurrency(String zxbCurrency) {
		this.zxbCurrency = zxbCurrency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
