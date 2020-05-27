package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

/**
 * Role entity.
 * 
 * @author Generator 2014-10-15 11:30:41
 */
public class Role extends BaseBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 状态
     */
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
