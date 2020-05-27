package com.champlink.common.domain.sale.contract;

import com.champlink.common.domain.BaseBean;
import com.champlink.common.domain.sale.product.Product;

import java.math.BigDecimal;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/6 15:55
 **/
public class SaleContractDetail extends BaseBean {
    /**
     * @Author jsl
     * @Date 销售单id 2018/8/6 
     * @Description    
     **/
    private Long saleContractId;
    /*
     * @Author jsl
     * @Date 产品id 2018/8/6 
     * @Description    
     **/
    private Long productionId;

    /**
     * @Author jsl
     * @Date 产品编号 2018/8/9
     * @Description
     *
     **/
    private String productionNo;
    /**
     * @Author jsl
     * @Date 备注 2018/8/6
     * @Description    
     **/
    private String remark;

    /**
     * @Author jsl
     * @Date 产品数量 2018/8/10 
     * @Description    
     **/
    private BigDecimal num;
    /**
     * @Author jsl
     * @Date 产品单价 2018/8/10 
     * @Description    
     **/
    private BigDecimal unitPrice;
    /**
     * @Author jsl
     * @Date 单位 2018/8/10 
     * @Description    
     **/
    private Long unit;
    /**
     * @Author jsl
     * @Date 产品总额 2018/8/10
     * @Description    
     **/
    private BigDecimal amount;

    /**
     * @Author jsl
     * @Date 15:13 2018/8/27
     * @Description     赠送数量
     **/
    private String presentNum;

    public String getPresentNum() {
        return presentNum;
    }

    public void setPresentNum(String presentNum) {
        this.presentNum = presentNum;
    }

    //功率
    private String power;
    //总功率
    private String totalPower;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
    }

    public Long getProductionId() {
        return productionId;
    }

    public void setProductionId(Long productionId) {
        this.productionId = productionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductionNo() {
        return productionNo;
    }

    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
}
