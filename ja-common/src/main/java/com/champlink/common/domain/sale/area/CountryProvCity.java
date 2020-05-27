package com.champlink.common.domain.sale.area;

import com.champlink.common.domain.BaseBean;

/**
 * 国家、省 洲、市
 * @Author created by barrett in 2018/8/29 13:41
 */
public class CountryProvCity extends BaseBean {

    // 编号
    private String code;

    //名称
    private String name;

    //是否为叶子节点
    private boolean isLeaf;

    // 级别
    private Integer level;

    private Integer pid;

    private Integer levelTmp;

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
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
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevelTmp() {
        return levelTmp;
    }

    public void setLevelTmp(Integer levelTmp) {
        this.levelTmp = levelTmp;
    }
}