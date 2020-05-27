package com.champlink.sale.dao.area;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.area.AreaDetail;
import com.github.pagehelper.Page;

@Mapper
public interface AreaDao  {

    Page<Area> searchAreaList(Paginater paginater);

    /**
     * 根据区域名称查询结果
     * jsl
     * @param regionName
     * @return
     */
    int searchAreaByRegionName(String regionName);

    /**
     * 增加区域
     * @param area
     * @return
     */
    int insertRegion(Area area);

    /**
     * 增加区域明细
     * jsl
     * @param areaDetailList
     * @return
     */
    int insertRegionDetail(@Param("regionId") Long regionId, @Param("areaDetailList") List<AreaDetail> areaDetailList);

    /**
     * jsl
     * 根据id查询信息
     * @param rowId
     * @return
     */
    Area getAreaById(Long rowId);

    /**
     * 区域修改
     * jsl
     * @param area
     * @return
     */
    int updateArea(Area area);

    /**
     * jsl
     * 根据区域名称和rowId查询是否存在其他数据
     * @param regionName
     * @param rowId
     * @return
     */
    int searchAreaByRegionNameAndId(@Param("regionName") String regionName,@Param("rowId") Long rowId);

    /**
     * 修改-删除
     * jsl
     * @param regionId
     */
    void deleteAreaDetail(Long regionId);

//    /**
//     * 删除
//     * jsl
//     * @param rowId
//     * @return
//     */
//    void delById(Long rowId);

    /**
     * 删除区域明细信息
     * jsl
     * @param rowId
     * @return
     */
    int delDetailById(Long rowId);

    /**
     * 删除区域
     * jsl
     * @param rowId
     * @return
     */
    int delAreaById(Long rowId);

    /**
     * @Author jsl
     * @Date 查询所有的 2018/8/8
     * @Description
     **/
    List<Area> getAllArea();

    /**
     * @Author jsl
     * @Date 查询区域明细数据 2018/8/16
     * @Description
     **/
    Page<AreaDetail> searchAreaDetail(Paginater paginater);

    int getCountry(@Param("regionName") String regionName, @Param("countryId") Long countryId);

    Area getArea(String regionName);

    /**
     * @Author jsl
     * @Date 插入国家 2018/8/21
     * @Description
     **/
    void insertDetail(AreaDetail areaDetail);

    /**
     * 通过区域Id查询 Area
     * @param orgId
     * @return
     */
    List<Area> getAreaByOrgIds(List<Long> orgId);
}
