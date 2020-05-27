package com.champlink.sale.generator.mapper;

import com.champlink.sale.generator.pojo.CountryProvCity;
import com.champlink.sale.generator.pojo.CountryProvCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountryProvCityMapper {
    int countByExample(CountryProvCityExample example);

    int deleteByExample(CountryProvCityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CountryProvCity record);

    int insertSelective(CountryProvCity record);

    List<CountryProvCity> selectByExample(CountryProvCityExample example);

    CountryProvCity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CountryProvCity record, @Param("example") CountryProvCityExample example);

    int updateByExample(@Param("record") CountryProvCity record, @Param("example") CountryProvCityExample example);

    int updateByPrimaryKeySelective(CountryProvCity record);

    int updateByPrimaryKey(CountryProvCity record);
}