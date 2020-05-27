package com.champlink.common.domain.attendance;

import com.champlink.common.domain.BaseBean;

/**
 * 考勤机
 * 
 * @author natyu
 * @date 2018年8月21日 下午6:55:07
 *
 */
public class Machine extends BaseBean {

    /**
     * 考勤机名称
     */
    private String machineName;

    /**
     * 基地ID
     */
    private String baseId;

    /**
     * 厂别ID
     */
    private String factoryId;

    /**
     * 序列号
     */
    private Long seq;

    /**
     * 最早提前签到（分钟）
     */
    private Long earliestSignIn;

    /**
     * 最晚推迟签到
     */
    private Long latestSignIn;

    /**
     * 最早提前签退（分钟）
     */
    private Long earliestSignOff;

    /**
     * 最晚推迟签退（分钟）
     */
    private Long latestSignOff;

    /**
     * 获取考勤机名称
     *
     * @return machine_name - 考勤机名称
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * 设置考勤机名称
     *
     * @param machineName 考勤机名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }


    public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}


    public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	/**
     * 获取序列号
     *
     * @return seq - 序列号
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * 设置序列号
     *
     * @param seq 序列号
     */
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    /**
     * 获取最早提前签到（分钟）
     *
     * @return earliest_sign_in - 最早提前签到（分钟）
     */
    public Long getEarliestSignIn() {
        return earliestSignIn;
    }

    /**
     * 设置最早提前签到（分钟）
     *
     * @param earliestSignIn 最早提前签到（分钟）
     */
    public void setEarliestSignIn(Long earliestSignIn) {
        this.earliestSignIn = earliestSignIn;
    }

    /**
     * 获取最晚推迟签到
     *
     * @return latest_sign_in - 最晚推迟签到
     */
    public Long getLatestSignIn() {
        return latestSignIn;
    }

    /**
     * 设置最晚推迟签到
     *
     * @param latestSignIn 最晚推迟签到
     */
    public void setLatestSignIn(Long latestSignIn) {
        this.latestSignIn = latestSignIn;
    }

    /**
     * 获取最早提前签退（分钟）
     *
     * @return earliest_sign_off - 最早提前签退（分钟）
     */
    public Long getEarliestSignOff() {
        return earliestSignOff;
    }

    /**
     * 设置最早提前签退（分钟）
     *
     * @param earliestSignOff 最早提前签退（分钟）
     */
    public void setEarliestSignOff(Long earliestSignOff) {
        this.earliestSignOff = earliestSignOff;
    }

    /**
     * 获取最晚推迟签退（分钟）
     *
     * @return latest_sign_off - 最晚推迟签退（分钟）
     */
    public Long getLatestSignOff() {
        return latestSignOff;
    }

    /**
     * 设置最晚推迟签退（分钟）
     *
     * @param latestSignOff 最晚推迟签退（分钟）
     */
    public void setLatestSignOff(Long latestSignOff) {
        this.latestSignOff = latestSignOff;
    }

}