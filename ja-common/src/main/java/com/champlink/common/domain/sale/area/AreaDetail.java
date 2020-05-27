package com.champlink.common.domain.sale.area;

import com.champlink.common.domain.BaseBean;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class AreaDetail extends BaseBean {

    /**
     * 区域id
     */
    @NotBlank(message = "区域不能为空")
    private Long regionId;

    /**
     * 洲id
     */
    @NotBlank(message = "洲不能为空")
    private Long continentId;
    /**
     * 洲名称
     */
    private String continentName;

    /**
     * 国家id
     */
    @NotBlank(message = "国家不能为空")
    private Long countryId;
    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 主币别id
     * @return
     */
    @NotBlank(message = "主币别不能为空")
    private Long zCurrencyId;
    private String zCurrencyName;

    /**
     * 辅币别
     * @return
     */
    private Long fCurrencyId;
    private String fCurrencyName;

    /**
     * @Author jsl
     * @Date 单价 2018/8/7
     * @Description
     **/
    private BigDecimal price;
    
    /**
     * @Author jsl
     * @Date 区域名称 2018/8/16 
     * @Description    
     **/
    private String regionName;
    /**
     * @Author jsl
     * @Date 国家数量 2018/8/16 
     * @Description    
     **/
    private Integer countryNum;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCountryNum() {
        return countryNum;
    }

    public void setCountryNum(Integer countryNum) {
        this.countryNum = countryNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getContinentId() {
        return continentId;
    }

    public void setContinentId(Long continentId) {
        this.continentId = continentId;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getzCurrencyId() {
        return zCurrencyId;
    }

    public Long getZCurrencyId() {
        return zCurrencyId;
    }

    public void setzCurrencyId(Long zCurrencyId) {
        this.zCurrencyId = zCurrencyId;
    }

    public String getzCurrencyName() {
        return zCurrencyName;
    }

    public void setzCurrencyName(String zCurrencyName) {
        this.zCurrencyName = zCurrencyName;
    }

    public Long getfCurrencyId() {
        return fCurrencyId;
    }

    public void setfCurrencyId(Long fCurrencyId) {
        this.fCurrencyId = fCurrencyId;
    }

    public String getfCurrencyName() {
        return fCurrencyName;
    }

    public void setfCurrencyName(String fCurrencyName) {
        this.fCurrencyName = fCurrencyName;
    }

    @Override
    public String toString() {
        return "AreaDetail{" +
                "regionId=" + regionId +
                ", continentId=" + continentId +
                ", continentName='" + continentName + '\'' +
                ", countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", zCurrencyId=" + zCurrencyId +
                ", zCurrencyName='" + zCurrencyName + '\'' +
                ", fCurrencyId=" + fCurrencyId +
                ", fCurrencyName='" + fCurrencyName + '\'' +
                ", price=" + price +
                ", regionName='" + regionName + '\'' +
                ", countryNum='" + countryNum + '\'' +
                '}';
    }
}
