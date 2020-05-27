package com.champlink.common.domain.attendance;

import com.champlink.common.domain.BaseBean;

/**
 * 班次/特殊班次
 * 
 * @author natyu
 * @date 2018年8月21日 下午6:49:26
 *
 */
public class JobNumber extends BaseBean {

    /**
     * 班次名称
     */
    private String jobNoName;

    /**
     * 班次类型
     */
    private String typeId;

    /**
     * 上班时间设置
     */
    private String onWorkTime;

    /**
     * 下班时间设置
     */
    private String offWorkTime;

    /**
     * 午休时间（分钟）
     */
    private Long noonBreak;

    /**
     * 有效打卡时间（小时）/需要工作时间（分钟）
     */
    private Long effectivePunching;

    /**
     * 最早上班时间
     */
    private String earliestTime;

    /**
     * 最晚上班时间
     */
    private String latestTime;

    /**
     * 是否特殊班次：0：是，1：否
     */
    private Integer isSpecial;

    /**
     * 获取班次名称
     *
     * @return job_no_name - 班次名称
     */
    public String getJobNoName() {
        return jobNoName;
    }

    /**
     * 设置班次名称
     *
     * @param jobNoName 班次名称
     */
    public void setJobNoName(String jobNoName) {
        this.jobNoName = jobNoName;
    }


    /**
     * 获取上班时间设置
     *
     * @return on_work_time - 上班时间设置
     */
    public String getOnWorkTime() {
        return onWorkTime;
    }

    public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
     * 设置上班时间设置
     *
     * @param onWorkTime 上班时间设置
     */
    public void setOnWorkTime(String onWorkTime) {
        this.onWorkTime = onWorkTime;
    }

    /**
     * 获取下班时间设置
     *
     * @return off_work_time - 下班时间设置
     */
    public String getOffWorkTime() {
        return offWorkTime;
    }

    /**
     * 设置下班时间设置
     *
     * @param offWorkTime 下班时间设置
     */
    public void setOffWorkTime(String offWorkTime) {
        this.offWorkTime = offWorkTime;
    }

    /**
     * 获取午休时间（分钟）
     *
     * @return noon_break - 午休时间（分钟）
     */
    public Long getNoonBreak() {
        return noonBreak;
    }

    /**
     * 设置午休时间（分钟）
     *
     * @param noonBreak 午休时间（分钟）
     */
    public void setNoonBreak(Long noonBreak) {
        this.noonBreak = noonBreak;
    }

    /**
     * 获取有效打卡时间（小时）/需要工作时间（分钟）
     *
     * @return effective_punching - 有效打卡时间（小时）/需要工作时间（分钟）
     */
    public Long getEffectivePunching() {
        return effectivePunching;
    }

    /**
     * 设置有效打卡时间（小时）/需要工作时间（分钟）
     *
     * @param effectivePunching 有效打卡时间（小时）/需要工作时间（分钟）
     */
    public void setEffectivePunching(Long effectivePunching) {
        this.effectivePunching = effectivePunching;
    }

    /**
     * 获取最早上班时间
     *
     * @return earliest_time - 最早上班时间
     */
    public String getEarliestTime() {
        return earliestTime;
    }

    /**
     * 设置最早上班时间
     *
     * @param earliestTime 最早上班时间
     */
    public void setEarliestTime(String earliestTime) {
        this.earliestTime = earliestTime;
    }

    /**
     * 获取最晚上班时间
     *
     * @return latest_time - 最晚上班时间
     */
    public String getLatestTime() {
        return latestTime;
    }

    /**
     * 设置最晚上班时间
     *
     * @param latestTime 最晚上班时间
     */
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    /**
     * 获取是否特殊班次：0：是，1：否
     *
     * @return is_special - 是否特殊班次：0：是，1：否
     */
    public Integer getIsSpecial() {
        return isSpecial;
    }

    /**
     * 设置是否特殊班次：0：是，1：否
     *
     * @param isSpecial 是否特殊班次：0：是，1：否
     */
    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

}