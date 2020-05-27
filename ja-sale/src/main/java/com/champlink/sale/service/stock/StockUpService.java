package com.champlink.sale.service.stock;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.stock.StockUp;
import com.champlink.common.form.sale.stock.StockForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.dao.stock.StockUpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/14 19:26
 **/
@Service
public class StockUpService {

    @Autowired
    private StockUpDao stockUpDao;

    /**
     * @Author jsl
     * @Date 查询 2018/8/14
     * @Description
     **/
    public PageListVO<StockUp> searchList(StockForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<StockUp> listVO = PageListVO.newInstance(stockUpDao.searchList(paginater));
        return listVO;
    }

    /**
     * @Author jsl
     * @Date 增加 2018/8/14
     * @Description
     **/
    @Transactional
    public boolean add(StockUp stockUp) {
        //校验   备货编号不能重复
        String stockNo = stockUp.getStockNo();
        //查询是否存在
        int num = stockUpDao.selectStockNo(stockNo);

        if(num>0){
            AppException.create(310011);    //310011 = 备货编号已经存在
        }else{
            stockUpDao.add(stockUp);
        }

         return true;
    }

    /**
     * @Author jsl
     * @Date 删除 2018/8/14
     * @Description
     **/
    @Transactional
    public boolean del(Long rowId) {
        if(stockUpDao.del(rowId)){
            return true;
        }
        return false;
    }

    /**
     * @Author jsl
     * @Date 查询 2018/8/14
     * @Description
     **/
    public StockUp get(Long rowId) {
        StockUp stockUp = new StockUp();
        stockUp = stockUpDao.get(rowId);
        return stockUp;
    }

    /**
     * @Author jsl
     * @Date 修改 2018/8/14
     * @Description
     **/
    @Transactional
    public boolean update(StockUp stockUp) {
        if(stockUpDao.update(stockUp)){
            return true;
        }
        return false;
    }
}
