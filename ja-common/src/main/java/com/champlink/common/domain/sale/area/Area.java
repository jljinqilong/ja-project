package com.champlink.common.domain.sale.area;

import com.alibaba.fastjson.annotation.JSONField;
import com.champlink.common.domain.BaseBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Area extends BaseBean {

    /**
     * 创建人
     */
    private String userName;
    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date createdDate;

    /**
     * 区域下的地区
     */
    private String areasName;
    /**
     * @Author jsl
     * @Date 14:10 2018/8/4
     * @Description
     **/
    private String countryNames;

    /**
     * 区域下 地区list
     */
    private List<AreaDetail> areaDetailList;

    private String token;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAreasName() {
        return areasName;
    }

    public void setAreasName(String areasName) {
        this.areasName = areasName;
    }

    public List<AreaDetail> getAreaDetailList() {
        return areaDetailList;
    }

    public void setAreaDetailList(List<AreaDetail> areaDetailList) {
        this.areaDetailList = areaDetailList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(String countryNames) {
        this.countryNames = countryNames;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Area{" +
                "userName='" + userName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", createdDate=" + createdDate +
                ", areasName='" + areasName + '\'' +
                ", countryNames='" + countryNames + '\'' +
                ", areaDetailList=" + areaDetailList +
                '}';
    }
}
