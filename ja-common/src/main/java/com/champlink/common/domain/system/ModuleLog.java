package com.champlink.common.domain.system;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

/**
 * 模块操作日志
 * 
 * @author natyu
 * @date 2018年7月23日 上午11:20:47
 *
 */
public class ModuleLog extends BaseBean {

    /*
     * 操作时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date optTime;
    /*
     * 操作人ID
     */
    private Long optStaffId;
    /*
     * 操作人工号
     */
    private String optStaffNo;
    /*
     * 操作人姓名
     */
    private String optStaffName;
    /*
     * 操作类型
     */
    private String optType;
    /*
     * 操作描述
     */
    private String optDescribe;
    /*
     * 应用编码
     */
    private String appCode;

    /*
     * 相关业务ID
     */
    private Long tableId;

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public Long getOptStaffId() {
        return optStaffId;
    }

    public void setOptStaffId(Long optStaffId) {
        this.optStaffId = optStaffId;
    }

    public String getOptStaffNo() {
        return optStaffNo;
    }

    public void setOptStaffNo(String optStaffNo) {
        this.optStaffNo = optStaffNo;
    }

    public String getOptStaffName() {
        return optStaffName;
    }

    public void setOptStaffName(String optStaffName) {
        this.optStaffName = optStaffName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptDescribe() {
        return optDescribe;
    }

    public void setOptDescribe(String optDescribe) {
        this.optDescribe = optDescribe;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

}
