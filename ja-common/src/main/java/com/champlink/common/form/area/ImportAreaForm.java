package com.champlink.common.form.area;

import com.champlink.common.util.annotations.Excel;

import java.math.BigDecimal;

/**
 * @Author jsl
 * @Date 13:56 2018/8/21
 * @Description 区域导入实体类
 **/
public class ImportAreaForm  {

    private static final long serialVersionUID = 1L;
    //区域名称
    @Excel(title = "区域")
    private String regionName;
    //洲名
    @Excel(title = "洲")
    private String continentName;
    //国家名
    @Excel(title = "国家")
    private String countryName;
    //币别
    @Excel(title = "币别")
    private String currencyName;
    //单价
    @Excel(title = "单价")
    private String price;


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
