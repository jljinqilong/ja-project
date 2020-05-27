package com.champlink.common.domain.emolument;

import java.util.Date;

import com.champlink.common.domain.BaseBean;

public class EltPerformance extends BaseBean {

    /**
     * 员工工号
     */
    private String staffNo;

    /**
     * 员工id
     */
    private Long staffId;
    
    /**
     * 月份
     */
    private Integer month;

    /**
     * 绩效金额
     */
    private Float amountOfPerformance;

    
    private Date createTime;

    private Long baseId;
    

    public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	/**
     * 获取月份
     *
     * @return month - 月份
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 设置月份
     *
     * @param month 月份
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取绩效金额
     *
     * @return amount_of_performance - 绩效金额
     */
    public Float getAmountOfPerformance() {
        return amountOfPerformance;
    }

    /**
     * 设置绩效金额
     *
     * @param amountOfPerformance 绩效金额
     */
    public void setAmountOfPerformance(Float amountOfPerformance) {
        this.amountOfPerformance = amountOfPerformance;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}