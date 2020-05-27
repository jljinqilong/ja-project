package com.champlink.common.domain.org.position;

import com.champlink.common.domain.BaseBean;

public class PositionType extends BaseBean {

    /**
     * 职衔类型名称
     */
    private String typeName;

    /**
     * 职衔类型描述
     */
    private String typeDesc;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

}
