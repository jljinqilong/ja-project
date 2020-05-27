package com.champlink.sale.controller.customer;

import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.customer.CustomerContacts;
import com.champlink.sale.service.customer.CustomerContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 客户联系人
 * @Author created by barrett in 18-8-10 下午2:09
 */
@RestController
@RequestMapping("/customerContacts")
public class CustomerContactsController extends BaseController {

    @Autowired
    private CustomerContactsService customerContactsService;

    /**
      * 根据客户id查询联系人list
      * @author created by barrett in 18-8-1 下午3:14
      */
    @RequestMapping(value = "/getContactsByCustomerId/{customerId}", produces = "application/json;charset=UTF-8")
    public String pageList(@PathVariable Long customerId) {
        List<CustomerContacts> list = customerContactsService.getContactsByCustomerId(customerId);
        return getSuccessJson(list);
    }



}
