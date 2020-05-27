package com.champlink.common.form.system;

import java.util.Date;
import com.champlink.common.form.BaseForm;

/**
 * 模块操作日志
 * 
 * @author natyu
 * @date 2018年7月23日 上午11:20:47
 *
 */
public class ModuleLogForm extends BaseForm {

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /*
     * 模糊查询操作描述/工号/姓名
     */
    private String optKey;
    /*
     * 操作类型
     */
    private Long optType;
    /*
     * 应用编码
     */
    private String appCode;
    /**
     * 业务表ID
     */
    private String tableId;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOptKey() {
        return optKey;
    }

    public void setOptKey(String optKey) {
        this.optKey = optKey;
    }

    public Long getOptType() {
        return optType;
    }

    public void setOptType(Long optType) {
        this.optType = optType;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

}
