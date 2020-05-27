package com.champlink.sale.dao.customer;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.champlink.common.domain.BaseSelect;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.customer.Customer;
import com.github.pagehelper.Page;

@Mapper
public interface CustomerDao {

    /**
      * 分页查询
      * @author created by barrett in 18-8-1 下午3:20
      */
    Page<Customer> pageList(Paginater paginater);

    /**
     * 添加
     * @param record
     * @return
     */
    int add(Customer record);
    /**
      * 修改
      * @author created by barrett in 18-8-3 下午5:32
      */
    int update(Customer record);

    /**
      * 效验客户简称是否存在
      * @author created by barrett in 18-8-3 下午3:53
      */
    Long checkCustomerByName(Customer record);
    /**
      * 根据id 查询客户信息
      * @author created by barrett in 18-8-3 下午6:21
      */
    Customer getCustomerById(Long rowId);
    /**
      * 判断客户是否是新增的
      * @author created by barrett in 18-8-4 下午1:36
      */
    Long getCustomerStatus(String[] ids);
    /**
      * 批量删除客户
      * @author created by barrett in 18-8-4 下午1:44
      */
    int deleteCustomerById(String[] ids);

    /**
     * 查询客户以及联系人list
     *
     * @Author created by barrett in 18-8-8 下午4:52
     */
    Customer getCustomerAndContacts(@Param("rowId")Long rowId);

    /**
     * 获取所有客户
     * @Author created by barrett in 18-8-10 上午9:59
     */
    List<BaseSelect> getAllCustomer();

    /**
     * 根据区域和国家查询币别
     * @Author created by barrett in 2018/8/13 18:59
     */
    Long getCurrencyId(@Param("regionId")Long regionId,@Param("countryId")Long countryId);
    
    /**
	 * 批量插入客户信息
	 * @param customers
	 */
	void insertByForeachCustomer(List<Customer> customers);
	
	/**
	 * 客户姓名是否存在
	 * @param list
	 * @return
	 */
	List<Customer> checkCustomerNoIsExist(List<String> list);

    /**
     * 根据ids 查询结果
     * @param list
     * @return
     */
    List<Customer> getCustomerByRowIds(List<Long> list);

	/**
	 * @Author jsl
	 * @Date 15:20 2018/8/25
	 * @Description 查询客户列表
	 **/
    Page<Customer> getCustomerListById(Paginater paginater);

    boolean updateTransferMan(@Param("staffId") Long staffId, @Param("rowId") Long rowId);
    
    Customer getCustomerByName(String customerName);
}
