package com.champlink.sale.dao.stock;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.stock.StockUp;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockUpDao {
    /**
     * @Author jsl
     * @Date 查询 2018/8/14
     * @Description
     **/
    Page<StockUp> searchList(Paginater paginater);

    /**
     * @Author jsl
     * @Date 增加 2018/8/14
     * @Description
     **/
    boolean add(StockUp stockUp);

    /**
     * @Author jsl
     * @Date 删除 2018/8/14
     * @Description
     **/
    boolean del(Long rowId);

    /**
     * @Author jsl
     * @Date 根据id查询 2018/8/14
     * @Description
     **/
    StockUp get(Long rowId);

    /**
     * @Author jsl
     * @Date 修改 2018/8/14
     * @Description
     **/
    boolean update(StockUp stockUp);

    /**
     * @Author jsl
     * @Date 根据stockNo查询 2018/8/14
     * @Description
     **/
    int selectStockNo(String stockNo);
}
