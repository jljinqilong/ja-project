package com.champlink.sale.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.champlink.common.util.file.FileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.BaseSelect;
import com.champlink.common.domain.sale.customer.Customer;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.customer.CustomerContactsExcelForm;
import com.champlink.common.form.sale.customer.CustomerExcelForm;
import com.champlink.common.form.sale.customer.CustomerForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.customer.CustomerContactsService;
import com.champlink.sale.service.customer.CustomerService;
/**
 * 客户信息
 * @Author created by barrett in 18-8-10 下午6:28
 */
@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerContactsService customerContactsService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private StaffService staffService;
    /**
    　* 添加
    　* @author created by barrett in 18-8-1 下午2:03
    　*/
    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String add(@Valid @RequestBody Customer customer) {
        setUserInfoByRedisToken(customer,true);

        if (customerService.add(customer)) {
            return getSuccessJson();
        }
        return getFailJson();
    }
    /**
      * 分页查询
      * @author created by barrett in 18-8-1 下午3:14
      */
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String pageList(CustomerForm form) {
        PageListVO<Customer> pageListVO = customerService.pageList(form);
        List<Customer> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list,allCodeList,
                        new String[]{
                                "currencyId,rowId,name",
                                "zxbCurrencyId,rowId,name",
                                "countryId,rowId,name",
                                "accountUnitId,rowId,name",
                                "customerLevelId,rowId,name",
                                "paymentTypeId,rowId,name"});
            }

        }
        pageListVO.setList(list);

        return getSuccessJson(pageListVO);
    }

    /**
      * 修改客户信息
      * @author created by barrett in 18-8-3 下午5:34
      */
    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String update(@Valid @RequestBody Customer customer) {
        setUserInfoByRedisToken(customer,false);
        if (customerService.update(customer)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
      * 删除客户以及相关联系人
      * @author created by barrett in 18-8-4 下午1:27
      */
    @RequestMapping(value="/del/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable(value="id")String id){

        customerService.deleteByIds(id);
        return getSuccessJson();
    }

    /**
     * 获取客户详情 -- 暂无调用
     * @author created by barrett in 18-8-1 下午3:14
     */
//    @RequestMapping(value = "/get/{rowId}", produces = "application/json;charset=UTF-8")
//    public String getCustomerInfo(@PathVariable Long rowId) {
//        Customer customer = customerService.getCustomerInfo(rowId);
//        //翻译字段
//        translate(customer);
//
//        return getSuccessJson(customer);
//    }

    /**
     * 根据id翻译name
     * @Author created by barrett in 18-8-9 上午11:55
     */
    public void translate(Customer customer){
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                customer = (Customer) super.translateIdToNameObj(customer,allCodeList,
                        new String[]{
                                "currencyId,rowId,name",
                                "zxbCurrencyId,rowId,name",
                                "countryId,rowId,name",
                                "accountUnitId,rowId,name",
                                "customerLevelId,rowId,name",
                                "paymentTypeId,rowId,name",
                                "jaCurrencyId,rowId,name"
                        });
            }
        }
    }
    /**
     * 获取客户和联系人信息
     * @author created by barrett in 18-8-1 下午3:14
     */
    @RequestMapping(value = "/getCustomerAndContacts/{rowId}", produces = "application/json;charset=UTF-8")
    public String getCustomerAndContacts(@PathVariable Long rowId) {
        Customer customer = customerService.getCustomerAndContacts(rowId);
        //翻译字段
        translate(customer);
        return getSuccessJson(customer);
    }

    /**
     * 获取所有客户信息
     * @Author created by barrett in 18-8-10 上午9:53
     */
    @RequestMapping(value = "/getAllCustomer", produces = "application/json;charset=UTF-8")
    public String getAllCustomer() {
        List<BaseSelect> list = customerService.getAllCustomer();
        return getSuccessJson(list);
    }

    /**
     * 设置创建人
     * @param record
     */
    public void setUserInfoByRedisToken(Customer record,boolean flag){
        try {
            User loginUser = redisService.getLoginUserByToken(RequestContext.get().getToken());
            String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
            if(flag){
                //add
                record.setCreatedBy(MethodUtil.getStaffRowId(baseInfo));
            }else {
                //update
                record.setLastUpdateBy(MethodUtil.getStaffRowId(baseInfo));
            }
        } catch (Exception e) {
            log.error(">>> Exception{}" + e);
            AppException.create(100004);
        }
    }
    
    /**
	 *  导入客户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public String importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1.构建Excel接受的实体类
        List<CustomerExcelForm> list1 = new ArrayList<CustomerExcelForm>();
        
        List<CustomerContactsExcelForm> list2 = new ArrayList<CustomerContactsExcelForm>();
        
        //2.将Excel转成 CustomerExcelForm
        ImportExcelUtils.export(request, CustomerExcelForm.class, list1,1,0);
        
        //2.将Excel转成 CustomerContactsExcelForm
        ImportExcelUtils.export(request, CustomerContactsExcelForm.class, list2,1,1);
        
        //3.将CustomerExcelForm入库
        customerService.importExcelCustomer(list1);
        
        //4.将CustomerContactsExcelForm入库
        customerContactsService.importExcelCustomerContacts(list2);
        
        return getSuccessJson();
    }

    /**
     * @Author jsl
     * @Date 13:57 2018/8/25
     * @Description   查询销售部员工列表
     **/
    @RequestMapping(value = "/getSalePersonList", produces = "application/json;charset=UTF-8")
    public String getSalePersonList(){
	    String str = staffService.getSalePersonList();
        JSONObject parseObject = JSONObject.parseObject(str);
        List<BaseSelect> salePersonList = new ArrayList<BaseSelect>();
        if ((Integer) parseObject.get("code") == 200) {
            salePersonList = JSONObject.parseArray(parseObject.getString("data"), BaseSelect.class);
        }
	    return getSuccessJson(salePersonList);
    }


    /**
     * @Author jsl
     * @Date 15:17 2018/8/25
     * @Description   根据staffId查询销售人员下所有的客户信息
     **/

    @RequestMapping(value = "/getCustomerList/{staffId}", produces = "application/json;charset=UTF-8")
    public String getCustomerListById(@PathVariable("staffId") Long staffId){

        CustomerForm form = new CustomerForm();
        form.setTransferMan(staffId);
        PageListVO<Customer> pageListVO = customerService.getCustomerListById(form);
        return getSuccessJson(pageListVO);
    }


    /**
     * @Author jsl
     * @Date 18:14 2018/8/25
     * @Description    更新transferMan
     **/
    @RequestMapping(value = "/updateTransferMan/{staffId}/{rowId}", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String updateTransferMan(@PathVariable("staffId") Long staffId, @PathVariable("rowId") Long rowId) {
        if (customerService.updateTransferMan(staffId,rowId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 19:06 2018/8/21
     * @Description    下载模板
     **/
    @RequestMapping(value = "/download", produces = "application/json;charset=UTF-8")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }

}
















