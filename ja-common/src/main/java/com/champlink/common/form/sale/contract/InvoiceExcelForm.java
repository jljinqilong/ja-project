package com.champlink.common.form.sale.contract;

import com.champlink.common.util.annotations.Excel;

/**
 * 
 * @author jinxin
 *
 */
public class InvoiceExcelForm {
	
	/**
	 * 序列
	 */
	@Excel(title = "序列", isNull = false)
	private String order;
	
    /**
     * 合同编号
     **/
	@Excel(title = "合同编号", isNull = false)
    private String saleContractNo;
    
    /**
     * 开票日期
     **/
	@Excel(title = "开票日期", isNull = false)
    private String invoiceDate;

    /**
     * 票据内容
     **/
	@Excel(title = "票据内容", isNull = false)
    private String invoiceContext;
    
    /**
     * 开票金额
     **/
	@Excel(title = "开票金额", isNull = false)
    private String invoiceAmount;
    
    /**
     * 票据类型  
     **/
	@Excel(title = "票据类型", isNull = false)
    private String invoiceTypeName;
    
    /**
     * 发票号码   
     **/
	@Excel(title = "发票号码", isNull = false)
    private String invoiceNo;

    /**
     * 经手人
     **/
	@Excel(title = "经手人", isNull = false)
    private String invoiceUserName;

    /**
     * 备注    
     **/
	@Excel(title = "备注")
    private String remark;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSaleContractNo() {
		return saleContractNo;
	}

	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceContext() {
		return invoiceContext;
	}

	public void setInvoiceContext(String invoiceContext) {
		this.invoiceContext = invoiceContext;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceUserName() {
		return invoiceUserName;
	}

	public void setInvoiceUserName(String invoiceUserName) {
		this.invoiceUserName = invoiceUserName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
