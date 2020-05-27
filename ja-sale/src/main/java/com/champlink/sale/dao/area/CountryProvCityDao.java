package com.champlink.sale.dao.area;

import com.champlink.common.domain.sale.area.CountryProvCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface CountryProvCityDao {

    List<CountryProvCity> selectRegionList(CountryProvCity example);

    List<CountryProvCity> selectRegionListByPId(Long pid);

    CountryProvCity getCountryPrivCityName(Long rowId);

	List<CountryProvCity> searchDetailListByRowId(@Param("ids") List<Long> ids);
}