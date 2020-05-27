package com.champlink.common.form.sale.contract;

import com.champlink.common.util.annotations.Excel;

/**
 * 合同导入Excel类
 * @author jinxin
 *
 */
public class SaleContractExcelForm  {
	
	/**
	 * 序列
	 */
	@Excel(title = "序列", isNull = false)
	private Integer order;

	/**
	 * 合同号
	 */
	@Excel(title = "合同编号", isNull = false)
	private String contractNo;
	
	/**
	 * 合同标题
	 */
	@Excel(title = "合同标题", isNull = false)
	private String contractTitle;

	/**
	 * 合同类型
	 */
	@Excel(title = "合同类型", isNull = false)
	private String contractTypeName;

	/**
	 * 客户名称
	 **/
	@Excel(title = "客户名称", isNull = false)
	private String customerName;

	/**
	 * 付款方式
	 */
	@Excel(title = "付款方式", isNull = false)
	private String payMethodName;
	
	/**
	 * 客户方签约人
	 */
	@Excel(title = "客户方签约人", isNull = false)
	private String clientContractor;

	/**
	 * 签约时间
	 */
	@Excel(title = "签约时间", isNull = false)
	private String signDate;
	
	/**
	 * 币别
	 **/
	@Excel(title = "币别", isNull = false)
	private String saleCurrency;

	/**
	 * 取号日期
	 **/
	@Excel(title = "取号日期", isNull = false)
	private String getNoDate;
	
	/**
	 * 合同总金额
	 */
	@Excel(title = "合同总金额", isNull = false)
	private String totalAmount;

	/**
	 * 已回款金额
	 */
	@Excel(title = "已回款金额")
	private String paymentAmount;

	/**
	 * 未回款金额
	 */
	@Excel(title = "未回款金额")
	private String unPaymentAmount;

	/**
	 * 已开票金额
	 */
	@Excel(title = "已开票金额")
	private String ticketAmount;

	/**
	 * 未开票金额
	 */
	@Excel(title = "未开票金额")
	private String unTicketAmount;
	
	/**
	 * 业务人员
	 **/
	@Excel(title = "业务人员")
	private String businessPerson;

	/**
	 * 区域名称
	 **/
	@Excel(title = "区域名称")
	private String regionName;

	/**
	 * 国家
	 **/
	@Excel(title = "国家")
	private String countryName;

	/**
	 * 总功率
	 **/
	@Excel(title = "总功率")
	private String totalPower;

	/*public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}*/

	/*public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}*/

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractTypeName() {
		return contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	public String getContractTitle() {
		return contractTitle;
	}

	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public String getClientContractor() {
		return clientContractor;
	}

	public void setClientContractor(String clientContractor) {
		this.clientContractor = clientContractor;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSaleCurrency() {
		return saleCurrency;
	}

	public void setSaleCurrency(String saleCurrency) {
		this.saleCurrency = saleCurrency;
	}

	public String getGetNoDate() {
		return getNoDate;
	}

	public void setGetNoDate(String getNoDate) {
		this.getNoDate = getNoDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getUnPaymentAmount() {
		return unPaymentAmount;
	}

	public void setUnPaymentAmount(String unPaymentAmount) {
		this.unPaymentAmount = unPaymentAmount;
	}

	public String getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getUnTicketAmount() {
		return unTicketAmount;
	}

	public void setUnTicketAmount(String unTicketAmount) {
		this.unTicketAmount = unTicketAmount;
	}

	public String getBusinessPerson() {
		return businessPerson;
	}

	public void setBusinessPerson(String businessPerson) {
		this.businessPerson = businessPerson;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(String totalPower) {
		this.totalPower = totalPower;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}


}
