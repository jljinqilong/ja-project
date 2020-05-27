package com.champlink.sale.controller.area;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.area.CountryProvCity;
import com.champlink.sale.service.area.CountryProvCityService;

/**
 * 国家、省（洲）、市
 * @Author created by barrett in 2018/8/29 13:36
 */
@RequestMapping("/countryCascader")
@RestController
public class CountryCascaderController extends BaseController {

    @Autowired
    private CountryProvCityService countryProvCityService;

    /**
     * @Author jsl
     * @Date 区域明细查询 2018/8/16
     * @Description
     **/
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String searchDetailList(@RequestBody CountryProvCity form) {
        List<CountryProvCity> countryProvCityList = countryProvCityService.getCountryProvCityList(form);
        return getSuccessJson(countryProvCityList);
    }

    @PostMapping(value = "/searchDetailListByRowId", produces = "application/json;charset=UTF-8")
    public String searchDetailListByRowId(@RequestBody List<Long> ids) {
        List<CountryProvCity> countryProvCityList = countryProvCityService.searchDetailListByRowId(ids);
        return getSuccessJson(countryProvCityList);
    }

    @PostMapping(value = "/selectRegionListByPId", produces = "application/json;charset=UTF-8")
    public String selectRegionListByPId(@RequestBody Long pid) {
        List<CountryProvCity> countryProvCityList = countryProvCityService.selectRegionListByPId(pid);
        return getSuccessJson(countryProvCityList);
    }

}
