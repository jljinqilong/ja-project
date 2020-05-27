package com.champlink.common.form.sale.product;

import javax.validation.constraints.NotNull;

import com.champlink.common.form.BaseForm;

public class SaleProductForm extends BaseForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * bomID
	 */
	private Long bomId;
	
	/**
	 * bom名称
	 */
	private String bomNo;
	 
	/**
     * 产品型号
     */
    private String productNo;

    /**
     * 硅片类型
     */
    private String siliconType;
    
    /**
     * 硅片类型rowId
     */
    @NotNull(message = "硅片类型rowId不能为空")
    private Long siliconTypeId;

    /**
     * 电池片数
     */
    private Integer cellNumber;
    
    /**
     * 电池片数rowId
     */
    @NotNull(message = "电池片数rowId不能为空")
    private Long cellNumberId;

    /**
     * 组件类型
     */
    private String muduleType;
    
    /**
     * 组件类型rowId
     */
    @NotNull(message = "组件类型rowId不能为空")
    private Long muduleTypeId;

    /**
     * 组件关键信息码
     */
    private String muduleCode;
    
    /**
     * 组件关键信息码rowId
     */
    @NotNull(message = "组件关键信息码rowId不能为空")
    private Long muduleCodeId;
    
    /**
     * 组件功率
     */
    private String ratedPower;
    
    /**
     * 电池片技术
     */
    private String cellTechnology;
    
    /**
     * 电池片技术rowId
     */
    @NotNull(message = "电池片技术rowId不能为空")
    private Long cellTechnologyId;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo != null ? productNo.trim():null;
	}

	public String getSiliconType() {
		return siliconType;
	}

	public void setSiliconType(String siliconType) {
		this.siliconType = siliconType;
	}

	public Integer getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(Integer cellNumber) {
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

	public Long getBomId() {
		return bomId;
	}

	public void setBomId(Long bomId) {
		this.bomId = bomId;
	}

	public String getBomNo() {
		return bomNo;
	}

	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}

	public Long getSiliconTypeId() {
		return siliconTypeId;
	}

	public void setSiliconTypeId(Long siliconTypeId) {
		this.siliconTypeId = siliconTypeId;
	}

	public Long getCellNumberId() {
		return cellNumberId;
	}

	public void setCellNumberId(Long cellNumberId) {
		this.cellNumberId = cellNumberId;
	}

	public Long getMuduleTypeId() {
		return muduleTypeId;
	}

	public void setMuduleTypeId(Long muduleTypeId) {
		this.muduleTypeId = muduleTypeId;
	}

	public Long getMuduleCodeId() {
		return muduleCodeId;
	}

	public void setMuduleCodeId(Long muduleCodeId) {
		this.muduleCodeId = muduleCodeId;
	}

	public Long getCellTechnologyId() {
		return cellTechnologyId;
	}

	public void setCellTechnologyId(Long cellTechnologyId) {
		this.cellTechnologyId = cellTechnologyId;
	}

	


}
