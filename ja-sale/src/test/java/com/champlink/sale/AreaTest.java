package com.champlink.sale;

import com.champlink.common.domain.sale.area.Area;
import com.champlink.common.domain.sale.area.AreaDetail;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.area.AreaForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.sale.dao.area.AreaDao;
import com.champlink.sale.service.area.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaTest {

    @Autowired
    private AreaService areaService;
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private RedisService redisService;

    @Test
    //添加
    public void add() {
        Area area = new Area();

        area.setUserName("ASZ00027");
        area.setRegionName("区域5");

        List<AreaDetail> list = new ArrayList<AreaDetail>();
        AreaDetail areaDetail = new AreaDetail();
        areaDetail.setContinentId(130L);
        areaDetail.setCountryId(132L);
        areaDetail.setzCurrencyId(135L);
        areaDetail.setfCurrencyId(134L);
        list.add(areaDetail);
        //
        AreaDetail areaDetail1 = new AreaDetail();
        areaDetail1.setContinentId(131L);
        areaDetail1.setCountryId(133L);
        areaDetail1.setzCurrencyId(134L);
        areaDetail1.setfCurrencyId(136L);
        list.add(areaDetail1);

        area.setAreaDetailList(list);

        areaService.addArea(area);
    }

    @Test
    public void test1(){
        List<Long> ll = new ArrayList<>();
        ll.add(19L);
        ll.add(21L);
        List<Area> areaByOrgIds = areaDao.getAreaByOrgIds(ll);
        System.out.println(areaByOrgIds);
    }


//    @Test
//    //查询
//    public void search(){
//        AreaForm areaForm = new AreaForm();
//       areaService.searchAreaList(areaForm);
//        System.out.println(areaService.searchAreaList(areaForm));
//    }

//    @Test
//    public void getById(){
//        Area area = new Area();
//        Long rowId = 9L;
//        area = areaService.getById(rowId);
//        System.out.println(area.toString());
//    }

    /**
     * 修改测试
     */
//    @Test
//    public void  update (){
//        Area area = new Area();
//        area.setRowId(9L);
//        area.setRegionName("区域6");
//
//        List<AreaDetail> areaDetailList = new ArrayList<AreaDetail>();
//        AreaDetail areaDetail = new AreaDetail();
//        areaDetail.setRegionId(9L);
//        areaDetail.setContinentId(131L);
//        areaDetail.setCountryId(132L);
//        areaDetail.setzCurrencyId(134L);
//        areaDetail.setfCurrencyId(135L);
//        areaDetailList.add(areaDetail);
//
//        area.setAreaDetailList(areaDetailList);
//
//        areaService.update(area);
//    }


//    @Test
//    public void del(){
//        Long rowId = 9L;
//        areaService.delById(rowId);
//    }
}
