package com.champlink.common.form.sale.product;

import com.champlink.common.form.BaseForm;
import com.champlink.common.util.annotations.Excel;

public class ImportProductForm extends BaseForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 序列
	 */
	@Excel(title = "序列", isNull = false)
	private Integer order;
	
    /**
     * 硅片类型
     */
    @Excel(title = "硅片类型")
    private String siliconType;

    /**
     * 电池片数
     */
    @Excel(title = "电池片数")
    private String cellNumber;

    /**
     * 组件类型
     */
    @Excel(title = "组件类型" )
    private String muduleType;

    /**
     * 组件关键信息码
     */
    @Excel(title = "组件关键信息码")
    private String muduleCode;
    /**
     * 组件功率
     */
    @Excel(title = "组件功率")
    private String ratedPower;
    
    /**
     * 电池片技术
     */
    @Excel(title = "电池片技术")
    private String cellTechnology;

	public String getSiliconType() {
		return siliconType;
	}

	public void setSiliconType(String siliconType) {
		this.siliconType = siliconType;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getMuduleType() {
		return muduleType;
	}

	public void setMuduleType(String muduleType) {
		this.muduleType = muduleType;
	}

	public String getMuduleCode() {
		return muduleCode;
	}

	public void setMuduleCode(String muduleCode) {
		this.muduleCode = muduleCode;
	}

	public String getRatedPower() {
		return ratedPower;
	}

	public void setRatedPower(String ratedPower) {
		this.ratedPower = ratedPower;
	}

	public String getCellTechnology() {
		return cellTechnology;
	}

	public void setCellTechnology(String cellTechnology) {
		this.cellTechnology = cellTechnology;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}


}
