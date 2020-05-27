package com.champlink.attendance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.attendance.service.BusinessTravelService;
import com.champlink.attendance.service.call.SaleService;
import com.champlink.attendance.service.call.StaffService;
import com.champlink.common.constant.SaleConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.attendance.BusinessTravel;
import com.champlink.common.domain.sale.area.CountryProvCity;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.attendance.BusinessTravelForm;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;

@RestController
@RequestMapping("/businessTravel")
public class BusinessTravelController extends BaseController {

    @Autowired
    private BusinessTravelService businessTravelService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(BusinessTravel businessTravel) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);

        businessTravel.setCreatedBy(createdBy);
        businessTravel.setCreatedTime(new Date());
        businessTravelService.add(businessTravel);
        return getSuccessJson();
    }

    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        businessTravelService.del(id);
        return getSuccessJson();
    }

    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String queryOne(@PathVariable("id") Long id) {

        BusinessTravel businessTravel = businessTravelService.get(id);

        List<CountryProvCity> countryProvCityList1 = null;
        List<CountryProvCity> countryProvCityList2 = null;
        List<CountryProvCity> countryProvCityList3 = null;

        //查询国家，省，市区 list
        String selectRegionListByPId = saleService.selectRegionListByPId(SaleConstant.COUNTRY_ID);
        if (selectRegionListByPId != null) {
            JSONObject parseObject = JSONObject.parseObject(selectRegionListByPId);
            if ((Integer) parseObject.get("code") == 200) {
                countryProvCityList1 = JSONObject.parseArray(parseObject.getString("data"), CountryProvCity.class);
            }
        }
        businessTravel.setCountryList(countryProvCityList1);

        if (!StringUtils.isEmpty(businessTravel.getCountryId())) {
            String selectRegionListByPId2 = saleService.selectRegionListByPId(businessTravel.getCountryId());
            if (selectRegionListByPId2 != null) {
                JSONObject parseObject = JSONObject.parseObject(selectRegionListByPId2);
                if ((Integer) parseObject.get("code") == 200) {
                    countryProvCityList2 = JSONObject.parseArray(parseObject.getString("data"), CountryProvCity.class);
                }
            }
            businessTravel.setProvinceList(countryProvCityList2);
        }
        if (!StringUtils.isEmpty(businessTravel.getProvinceId())) {
            String selectRegionListByPId3 = saleService.selectRegionListByPId(businessTravel.getProvinceId());
            if (selectRegionListByPId3 != null) {
                JSONObject parseObject = JSONObject.parseObject(selectRegionListByPId3);
                if ((Integer) parseObject.get("code") == 200) {
                    countryProvCityList3 = JSONObject.parseArray(parseObject.getString("data"), CountryProvCity.class);
                }
            }
            businessTravel.setCityList(countryProvCityList3);
        }
        return getSuccessJson(businessTravel);
    }

    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(BusinessTravel businessTravel) {
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        businessTravel.setLastUpdateBy(createdBy);
        businessTravel.setLastUpdateTime(new Date());

        if (businessTravel.getCityId() == null) {
            businessTravel.setCityId(-1L);
        }

        businessTravelService.update(businessTravel);
        return getSuccessJson();
    }

    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(BusinessTravelForm businessTravelForm) {
        PageListVO pageListVO = businessTravelService.pageList(businessTravelForm);
        List<BusinessTravel> list = pageListVO.getList();
        Set<Long> set = new HashSet<Long>();
        for (BusinessTravel businessTravel : list) {
            set.add(businessTravel.getCountryId());
            set.add(businessTravel.getProvinceId());
            set.add(businessTravel.getCityId());
        }

        List<Long> ids = new ArrayList<Long>(set);
        String searchDetailListByRowId = saleService.searchDetailListByRowId(ids);
        List<CountryProvCity> countryProvCityList = null;
        if (searchDetailListByRowId != null) {
            JSONObject parseObject = JSONObject.parseObject(searchDetailListByRowId);
            if ((Integer) parseObject.get("code") == 200) {
                countryProvCityList = JSONObject.parseArray(parseObject.getString("data"), CountryProvCity.class);
            }
        }

        list = super.translateIdToName(list, countryProvCityList, new String[] {"countryId,rowId,name"});
        list = super.translateIdToName(list, countryProvCityList, new String[] {"provinceId,rowId,name"});
        list = super.translateIdToName(list, countryProvCityList, new String[] {"cityId,rowId,name"});

        return getSuccessJson(pageListVO);
    }

}
