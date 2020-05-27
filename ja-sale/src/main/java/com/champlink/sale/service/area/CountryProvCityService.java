package com.champlink.sale.service.area;

import com.champlink.common.domain.sale.area.CountryProvCity;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.sale.dao.area.CountryProvCityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CountryProvCityService {

    @Autowired
    private CountryProvCityDao countryProvCityDao;

    /**
     * 获取省市区
     *
     * @Author created by barrett in 2018/8/29 15:52
     */
    public List<CountryProvCity> getCountryProvCityList(CountryProvCity countryProvCity) {
        List<CountryProvCity> countryProvCities = countryProvCityDao.selectRegionList(countryProvCity);
        return countryProvCities;
    }

    /**
     * 拼接国家省市，用于展示
     * @Author created by barrett in 2018/8/30 16:47
     */
    public String getCountryPrivCityName(Inquiries inquiries) {
        String name = "";
        CountryProvCity country = countryProvCityDao.getCountryPrivCityName(inquiries.getCountryId());
        if(country != null)
            name += country.getName();

        CountryProvCity priv = countryProvCityDao.getCountryPrivCityName(inquiries.getProvinceId());
        if(priv != null)
            name += "/"+priv.getName();

        CountryProvCity city = countryProvCityDao.getCountryPrivCityName(inquiries.getCityId());
        if(city != null)
            name += "/"+city.getName();

        return name;
    }

	public List<CountryProvCity> searchDetailListByRowId(List<Long> ids) {
		List<CountryProvCity> countryProvCityList =countryProvCityDao.searchDetailListByRowId(ids);
		return countryProvCityList;
	}
	
	public List<CountryProvCity> selectRegionListByPId(Long pid) {
		List<CountryProvCity> countryProvCityList =countryProvCityDao.selectRegionListByPId(pid);
		return countryProvCityList;
	}
}
