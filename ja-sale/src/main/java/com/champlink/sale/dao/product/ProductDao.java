package com.champlink.sale.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.product.Product;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {
	
	/**
	 * 根据给定的条件查询
	 * @param paginater
	 * @return
	 */
	Page<Product> searchSaleProductList(Paginater paginater);
	
	/**
	 * 增加一个产品
	 * @param product
	 */
	void insertProduct(Product product);
	
	
	/**
	 * 根据rowId删除该产品--物理删除
	 * @param productNo
	 */
	void delProductByRowId(Long rowId);
	
	/**
	 * 根据RowId修改产品信息
	 * @param product
	 */
	void updateProductByRowId(Product product);
	
	/**
	 * 根据条件查询
	 * @param product
	 * @return
	 */
	List<Product> getProductByProduct(Product productNo);
	
	/**
	 * 批量插入产品
	 * @param products
	 */
	void insertByForeachProdect(List<Product> products);
	
	/**
	 * 产品型号是否存在
	 * @param list
	 * @return
	 */
	List<Product> checkProductNoIsExist(List<String> list);

	/**
	 * @Author jsl
	 * @Date 根据rowId和status更新产品状态 2018/8/23
	 * @Description
	 **/
	boolean updateProductByIdAndStatus(@Param("rowId") Long rowId, @Param("status") String status);

	/**
	 * 获取所有产品
	 * @return
	 */
	List<Product> getAll();
}
