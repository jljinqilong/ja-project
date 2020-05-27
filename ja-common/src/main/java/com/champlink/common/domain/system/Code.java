package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

public class Code extends BaseBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编码类型ID
     */
    private Long typeId;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String desc;
    /**
     * 排序
     */
    private Integer orderNo;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

	@Override
	public String toString() {
		return "Code{" +
				"typeId=" + typeId +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", desc='" + desc + '\'' +
				", orderNo=" + orderNo +
				'}';
	}
}
