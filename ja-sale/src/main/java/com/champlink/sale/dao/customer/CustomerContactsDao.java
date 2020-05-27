package com.champlink.sale.dao.customer;


import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerContactsDao {

    /**
      * 分页查询
      * @author created by barrett in 18-8-1 下午3:20
      */
    Page<CustomerContacts> pageList(Paginater paginater);

    /**
     * 添加
     * @param record
     * @return
     */
    int add(CustomerContacts record);
    /**
      * 修改
      * @author created by barrett in 18-8-3 下午5:32
      */
    int update(CustomerContacts record);
    /**
      * 批量添加联系人
      * @author created by barrett in 18-8-3 下午6:11
      */
    int insertContactsBatch(List<CustomerContacts> list);
    /**
      * 根据客户id删除联系人
      * @author created by barrett in 18-8-3 下午6:14
      */
    int deleteContactsByCustomerId(String[] customerId);

    /**
      * 效验客户简称是否存在
      * @author created by barrett in 18-8-3 下午3:53
      */
    Long checkCustomerByName(CustomerContacts record);
    /**
      * 根据客户id查询联系人信息
      * @author created by barrett in 18-8-4 下午2:10
      */
    List<CustomerContacts> getContactsByCustomerId(@Param("customerId") Long customerId);


}
