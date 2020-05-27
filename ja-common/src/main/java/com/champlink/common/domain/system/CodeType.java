package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

public class CodeType extends BaseBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编码类型
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

}
