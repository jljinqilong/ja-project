package com.champlink.common.domain.sale.product;

import com.champlink.common.domain.BaseBean;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/6 15:49
 * 产品信息
 **/
public class Product extends BaseBean {
	
    /**
     * @Author jsl
     * @Date 15:50 2018/8/6
     * @Description    bomId
     **/
    private Long bomId;
    /**
     * @Author jsl
     * @Date bom名称（型号） 2018/8/6 
     * @Description    
     **/
    private String bomNo;
    
    /**
     * @Author jsl
     * @Date 产品型号 2018/8/6
     * @Description    
     **/
    private String productNo;
    
    /**
     * 硅片类型
     */
    private String siliconType;
    
    /**
     * 硅片类型RowId
     */
    private Long siliconTypeId;
    
    /**
     * 电池片数
     */
    private String cellNumber;
    
    /**
     * 电池片数RowId
     */
    private Long cellNumberId;

    /**
     * 组件类型
     */
    private String muduleType;
    
    /**
     * 组件类型RowId
     */
    private Long muduleTypeId;
    
    /**
     * 组件关键信息码
     */
    private String muduleCode;
    
    /**
     * 组件关键信息码RowId
     */
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
     * 电池片技术RowId
     */
    private Long cellTechnologyId;

    /**
     * 用于Select时的Value
     */
    private String name;
    
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

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
		setName(productNo);
	}

	public String getSiliconType() {
		return siliconType;
	}

	public void setSiliconType(String siliconType) {
		this.siliconType = siliconType;
	}

	public Long getSiliconTypeId() {
		return siliconTypeId;
	}

	public void setSiliconTypeId(Long siliconTypeId) {
		this.siliconTypeId = siliconTypeId;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public Long getCellNumberId() {
		return cellNumberId;
	}

	public void setCellNumberId(Long cellNumberId) {
		this.cellNumberId = cellNumberId;
	}

	public String getMuduleType() {
		return muduleType;
	}

	public void setMuduleType(String muduleType) {
		this.muduleType = muduleType;
	}

	public Long getMuduleTypeId() {
		return muduleTypeId;
	}

	public void setMuduleTypeId(Long muduleTypeId) {
		this.muduleTypeId = muduleTypeId;
	}

	public String getMuduleCode() {
		return muduleCode;
	}

	public void setMuduleCode(String muduleCode) {
		this.muduleCode = muduleCode;
	}

	public Long getMuduleCodeId() {
		return muduleCodeId;
	}

	public void setMuduleCodeId(Long muduleCodeId) {
		this.muduleCodeId = muduleCodeId;
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

	public Long getCellTechnologyId() {
		return cellTechnologyId;
	}

	public void setCellTechnologyId(Long cellTechnologyId) {
		this.cellTechnologyId = cellTechnologyId;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
