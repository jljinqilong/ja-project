package com.champlink.common.form.sale.contract;

import com.champlink.common.util.annotations.Excel;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/6 15:55
 **/
public class SaleContractDetailExcelForm{
	
	/**
	 * 序列
	 */
	@Excel(title = "序列", isNull = false)
	private Integer order;
	
    /**
     * 合同编号
     **/
	@Excel(title = "合同编号", isNull = false)
    private String saleContractNo;
    
    /**
     * 产品编号
     **/
    @Excel(title = "产品编号", isNull = false)
    private String productionNo;
    
    /**
     * 产品数量
     **/
    @Excel(title = "产品数量", isNull = false)
    private String num;
    
    /**
     * 产品单价
     **/
    @Excel(title = "产品单价", isNull = false)
    private String unitPrice;
    
    /**
     * 单位
     **/
    @Excel(title = "单位", isNull = false)
    private String unit;
    
    /**
     * 产品总额
     **/
    @Excel(title = "产品总额", isNull = false)
    private String amount;

    /**
     * 功率
     */
    @Excel(title = "功率", isNull = false)
    private String power;
    
    /**
     * 总功率
     */
    @Excel(title = "总功率", isNull = false)
    private String totalPower;

	public String getSaleContractNo() {
		return saleContractNo;
	}

	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}

	public String getProductionNo() {
		return productionNo;
	}

	public void setProductionNo(String productionNo) {
		this.productionNo = productionNo;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
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
