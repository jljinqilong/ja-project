package com.champlink.sale.generator.pojo;

public class CountryProvCity {
    private Integer id;

    private Integer code;

    private String name;

    private Integer level;

    private Integer parentId;

    private Integer levelTmp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevelTmp() {
        return levelTmp;
    }

    public void setLevelTmp(Integer levelTmp) {
        this.levelTmp = levelTmp;
    }
}