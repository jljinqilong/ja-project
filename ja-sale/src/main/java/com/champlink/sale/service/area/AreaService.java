package com.champlink.sale.service.area;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.area.AreaDetail;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.area.AreaForm;
import com.champlink.common.form.area.ImportAreaForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.area.AreaDao;
import com.champlink.sale.service.call.SaleCommonService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private SaleCommonService saleCommonService;


    //查询区域
    public PageListVO<Area> searchAreaList(AreaForm form) {

        Paginater paginater = Paginater.newInstance(form);
        //区域list
        PageListVO<Area> listVO = PageListVO.newInstance(areaDao.searchAreaList(paginater));
        return listVO;
    }


    //增加
    @Transactional
    public boolean addArea(Area area) {
        //区域名称
        String regionName = area.getRegionName();
        if(regionName.isEmpty() || "".equals(regionName)){
            AppException.create(310000);
        }


        //区域判重
        //根据区域名称查询
        int num = areaDao.searchAreaByRegionName(regionName);
        if(num>0){
            AppException.create(310001);
        }
        //国家判重
        Map<Long,Long> countryIds = new HashMap<Long,Long>();
        List<AreaDetail> areaDetailList = area.getAreaDetailList();

        //不为空
        if(areaDetailList.size()>0) {
            for (AreaDetail areaDetail : areaDetailList) {
                areaDetail.setCreatedBy(area.getCreatedBy());
                if (countryIds.containsKey(areaDetail.getCountryId())) {
                    AppException.create(310002);
                } else {
                    countryIds.put(areaDetail.getCountryId(), areaDetail.getContinentId());
                }
            }
        }

        //校验通过，保存数据
        int num1 = areaDao.insertRegion(area);
        int num2 = 0;
        if(areaDetailList.size()>0) {
            num2 = areaDao.insertRegionDetail(area.getRowId(), areaDetailList);
        }

        if(num1>0 || num2>0){
            return true;
        }
        return false;
    }

    /**
     * 根据id查询区域信息
     * @param rowId
     * @return
     */
    public Area getById(Long rowId) {
        Area area = areaDao.getAreaById(rowId);
        return area;
    }

    /**
     * 区域修改
     * @param area
     * @return
     */
    @Transactional
    public boolean update(Area area) {
        //标记
        Boolean flag = false;
        //区域名称
        String regionName = area.getRegionName();
        Long regionId = area.getRowId();

        //是否存在除本记录以外的记录
        int num = areaDao.searchAreaByRegionNameAndId(regionName,regionId);
        if(num>0){
            AppException.create(310001);
        }else {
            //校验通过编辑区域明细记录
            //update sale_region
            areaDao.updateArea(area);

            //update sale_region_dtls  先删在插入
            areaDao.deleteAreaDetail(regionId);

            if(area.getAreaDetailList().size()>0) {
                //判断明细中是否存在相同的国家
                Map<Long,Long> countryIds = new HashMap<Long,Long>();
                for (AreaDetail areaDetail : area.getAreaDetailList()) {
                    areaDetail.setCreatedBy(area.getCreatedBy());
                    if (countryIds.containsKey(areaDetail.getCountryId())) {
                        AppException.create(310002);
                    } else {
                        countryIds.put(areaDetail.getCountryId(), areaDetail.getContinentId());
                    }
                }

                areaDao.insertRegionDetail(regionId, area.getAreaDetailList());
            }

            flag = true;
        }
        if (flag) {
            return true;
        }
        return false;
    }

    /**
     * 删除
     * @param rowId
     * @return
     */
    @Transactional
    public void delById(Long rowId) {
        try {
            //删除区域
            if (areaDao.delAreaById(rowId) > 0) {
                //删除区域明细数据
                areaDao.delDetailById(rowId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new AppException("当前区域被引用，无法删除");
        }
    }

    /**
     * @Author jsl
     * @Date 查询所有的区域 2018/8/8 
     * @Description    
     **/
    public List<Area> getAllArea() {
        List<Area> areaList = new ArrayList<Area>();
        areaList = areaDao.getAllArea();
        return areaList;
    }

    /**
     * @Author jsl
     * @Date 区域明细查询 2018/8/16
     * @Description
     **/
    public PageListVO<AreaDetail> searchAreaDetailList(AreaForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Area> listVO = PageListVO.newInstance(areaDao.searchAreaList(paginater));


        listVO.getList().forEach(area -> area.getAreaDetailList().forEach(areaDetail -> areaDetail.setRegionName(area.getRegionName())));

        List<AreaDetail> detailList = listVO.getList().stream().flatMap(item -> item.getAreaDetailList().stream()).collect(Collectors.toList());

        //区域明细list
        // PageListVO<AreaDetail> tmp = PageListVO.newInstance(areaDao.searchAreaDetail(paginater));

        //明细list
        Counter counter = null;
        for(AreaDetail detail : detailList) {
            if (counter == null) {
                counter = new Counter(detail);
            } else if(counter.isEqual(detail.getRegionName())) {
                counter.incr();
            } else if(!counter.isEqual(detail.getRegionName())) {
                AreaDetail target = counter.getTarget();
                target.setCountryNum(counter.getNum());
                counter = new Counter(detail);
            }
        }

        if (counter != null && counter.getNum() > 0) {
            AreaDetail target = counter.getTarget();
            target.setCountryNum(counter.getNum());
        }

        Page<AreaDetail> page = new Page<>();
        page.setPageNum(listVO.getPageNum());
        page.setPageSize(listVO.getPageSize());
        page.setTotal(listVO.getTotal());
        page.setPages(listVO.getPageTotal());
        PageListVO<AreaDetail> detailListVO = PageListVO.newInstance(page);
        detailListVO.setList(detailList);
        return detailListVO;
    }

    /**
     * @Author jsl
     * @Date 区域导入 2018/8/21
     * @Description
     **/
    @Transactional
    public void importExcelArea(Long createdBy,List<ImportAreaForm> list) {

        //去除第一条记录
//        list.remove(0);
        for(ImportAreaForm importAreaForm : list){

            //区域
            String regionName = importAreaForm.getRegionName().trim();
            if("".equals(regionName)){
                AppException.create(310000);        //区域不能为空
            }
            Area area = areaDao.getArea(regionName);
            Long regionId = null;
            if(area != null){
                regionId = area.getRowId();
            }


            //洲名
            String continentName = importAreaForm.getContinentName().trim();
            Long continentId = TransNameToId("CONTINENT",continentName);

            //国家
            String countryName = importAreaForm.getCountryName().trim();
            Long countryId = TransNameToId("COUNTRY",countryName);

            //币别
            String currencyName = importAreaForm.getCurrencyName().trim();
            Long currencyId = TransNameToId("CURRENCY",currencyName);

            //单价
            String price  = importAreaForm.getPrice();
            //区域是否存在
            int num = areaDao.searchAreaByRegionName(regionName);
            //存在
            if(num>0){
                //判断国家是否存在
                int countryNum = areaDao.getCountry(regionName,countryId);
                //国家存在
                if(countryNum>0){
                    throw new AppException(regionName+" 下，"+countryName+" 已经存在！");
//                    AppException.create(1,regionName+"下，"+countryName+"已经存在！");
                }else{
                    //不存在此国家
                    AreaDetail areaDetail = new AreaDetail();
                    areaDetail.setRegionId(regionId);
                    areaDetail.setContinentId(continentId);
                    areaDetail.setCountryId(countryId);
                    areaDetail.setzCurrencyId(currencyId);
                    areaDetail.setPrice(new BigDecimal(price));
                    //默认admin
                    areaDetail.setCreatedBy(createdBy);
                    areaDao.insertDetail(areaDetail);
                }
            }else{
                //不存在
                //1.保存区域信息
                Area newArea = new Area();
                newArea.setCreatedBy(createdBy);
                newArea.setRegionName(regionName);
                areaDao.insertRegion(newArea);

                //2.保存区域明细
                //不存在此国家
                AreaDetail areaDetail = new AreaDetail();
                areaDetail.setRegionId(newArea.getRowId());
                areaDetail.setContinentId(continentId);
                areaDetail.setCountryId(countryId);
                areaDetail.setzCurrencyId(currencyId);
                areaDetail.setPrice(new BigDecimal(price));
                //默认admin
                areaDetail.setCreatedBy(createdBy);
                areaDao.insertDetail(areaDetail);

            }
        }
    }


    /**
     * @Author jsl
     * @Date 18:54 2018/8/21
     * @Description     name转id
     **/
    public Long TransNameToId(String typeCode,String name){
        String  codeStr = saleCommonService.getByCodeAndName(typeCode,name);
        Code code = new Code();
        Long rowId=null;
        if(codeStr == null){
            throw new AppException(name +" 不存在，先在参数设置里添加");
        }else{
            JSONObject parseObject = JSONObject.parseObject(codeStr);
            code = JSONObject.toJavaObject((JSON) parseObject.get("data"),Code.class);
            rowId = code.getRowId();
            return rowId;
        }
    }



    private class Counter {
        private String regionName;
        private AreaDetail target;
        protected Integer num;

        Counter(AreaDetail target) {
            this.target = target;
            this.regionName = target.getRegionName();
            this.num = 1;
        }

        public boolean isEqual(String regionName) {
            return regionName.contains(this.regionName);
        }

        public void incr() {
            this.num++;
        }

        public String getRegionName() {
            return regionName;
        }

        public AreaDetail getTarget() {
            return target;
        }

        public Integer getNum() {
            return num;
        }
    }
}
