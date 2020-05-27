package com.champlink.common.domain.sale.inquiry;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import java.util.Date;

/**
 * 询单 1vN 交期
 * @Author created by barrett in 2018/8/24 17:32
 */
public class InquiriesDeliveryTime extends BaseBean {

    // 交期时间
    private String deliveryTime;

    private Long num;

    private Long inquiriesId;

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getInquiriesId() {
        return inquiriesId;
    }

    public void setInquiriesId(Long inquiriesId) {
        this.inquiriesId = inquiriesId;
    }
}