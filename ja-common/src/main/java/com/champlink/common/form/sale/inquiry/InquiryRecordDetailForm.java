package com.champlink.common.form.sale.inquiry;

import com.champlink.common.domain.BaseBean;
import com.champlink.common.form.BaseForm;

/**
 * 询价记录明细list
 * @Author created by barrett in 18-8-8 上午9:37
 * @Param
 * @return
 */
public class InquiryRecordDetailForm extends BaseForm {


    private Long inquiryId;


    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

}