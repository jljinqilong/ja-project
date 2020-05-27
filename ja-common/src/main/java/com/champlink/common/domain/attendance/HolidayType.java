package com.champlink.common.domain.attendance;

import com.champlink.common.domain.BaseBean;

/**
 * 请假类型
 * 
 * @author natyu
 * @date 2018年8月21日 下午6:40:57
 *
 */
public class HolidayType extends BaseBean {

    /**
     * 请假类型名称
     */
    private String typeName;

    /**
     * 是否启用
     */
    private Integer status;

    /**
     * 请假时间单位
     */
    private String unit;

    /**
     * 最小请假时间
     */
    private Double minLeaveTime;

    /**
     * 获取请假类型名称
     *
     * @return type_name - 请假类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置请假类型名称
     *
     * @param typeName 请假类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取是否启用
     *
     * @return status - 是否启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否启用
     *
     * @param status 是否启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getMinLeaveTime() {
		return minLeaveTime;
	}

	public void setMinLeaveTime(Double minLeaveTime) {
		this.minLeaveTime = minLeaveTime;
	}

}