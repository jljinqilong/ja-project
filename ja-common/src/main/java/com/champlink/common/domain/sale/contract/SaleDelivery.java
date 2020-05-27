package com.champlink.common.domain.sale.contract;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import java.util.Date;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/27 15:06
 **/
public class SaleDelivery extends BaseBean {
    //合同id
    private Long saleContractId;
    //交期日期
    @JSONField(format = "yyyy-MM-dd")
    private Date deliveryTime;
    //交期数量
    private String deliveryNum;

    public Long getSaleContractId() {
        return saleContractId;
    }

    public void setSaleContractId(Long saleContractId) {
        this.saleContractId = saleContractId;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(String deliveryNum) {
        this.deliveryNum = deliveryNum;
    }
}
