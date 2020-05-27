package com.champlink.common.domain.system;

import com.champlink.common.domain.BaseBean;

/**
 * Resources entity.
 * 
 * @author Generator 2014-10-29 16:03:50
 */
public class Resource extends BaseBean {

    private static final long serialVersionUID = 1L;

    private Long pid;

    private String key;

    private String pKey;

    private int type;

    /** 资源名 */
    private String nameEn;
    private String nameZh;

    /**
     * 图标样式
     */
    private String icon;

    private int orderNum;

    private String accessUrl;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getpKey() {
        return pKey;
    }

    public void setpKey(String pKey) {
        this.pKey = pKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }
}
