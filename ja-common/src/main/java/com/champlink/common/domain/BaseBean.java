package com.champlink.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import com.alibaba.fastjson.annotation.JSONField;

public abstract class BaseBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8458281164895732290L;

    /** 行号 */
    private Long rowId;

    /**
     * 是否删除（0 正常 1 删除）
     */
    private Integer delFlag;

    private Integer status;

    /**
     * rowId到rowName的映射，key为rowId的属性名，value为rowName的属性值
     */
    private HashMap<String, Object> transNames;

    private Long createdBy; //创建人
    private String createName;//创建人名字

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private Long lastUpdateBy; //最后修改人
    private String updateName;//更新人名字

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public HashMap<String, Object> getTransNames() {
        return transNames;
    }

    public void setTransNames(HashMap<String, Object> transNames) {
        this.transNames = transNames;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

}