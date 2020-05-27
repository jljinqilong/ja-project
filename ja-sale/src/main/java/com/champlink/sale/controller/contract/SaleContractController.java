package com.champlink.sale.controller.contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.champlink.common.util.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.org.OrgAndOrgInfo;
import com.champlink.common.domain.sale.contract.InvoiceForm;
import com.champlink.common.domain.sale.contract.SaleContract;
import com.champlink.common.domain.sale.contract.SaleContractDetail;
import com.champlink.common.domain.sale.contract.SalePayPlanDetail;
import com.champlink.common.domain.sale.payment.PaymentPlan;
import com.champlink.common.domain.sale.payment.PaymentPlanDetail;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.contract.InvoiceExcelForm;
import com.champlink.common.form.sale.contract.SaleContractDetailExcelForm;
import com.champlink.common.form.sale.contract.SaleContractExcelForm;
import com.champlink.common.form.sale.contract.SaleContractForm;
import com.champlink.common.util.excel.ImportExcelUtils;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.OrgService;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.contract.SaleContractService;
import com.champlink.sale.service.payment.PaymentService;

/**
 * @Desciption TODO
 * @Author JSL
 * @Date 2018/8/4 11:40
 **/
@RequestMapping("/saleContract")
@RestController
public class SaleContractController extends BaseController {

    @Autowired
    private SaleContractService saleContractService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private StaffService staffService;
    /**
     * @Author jsl
     * @Date 11:45 2018/8/4
     * @Description    查询销售合同
     **/
    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public String searchSaleContractList(SaleContractForm saleContractForm){
        PageListVO<SaleContract> pageListVO = saleContractService.searchSaleContractList(saleContractForm);
        //id转name
        String allCode = saleCommonService.getAllCode();
        List<SaleContract> detailList = pageListVO.getList();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                detailList = super.translateIdToName(detailList,allCodeList,new String[]{
                        "contractType,rowId,name",
                        "payMethod,rowId,name",
                        "saleCurrency,rowId,name"});
            }
        }
        pageListVO.setList(detailList);
        return getSuccessJson(pageListVO);
    }

    /**
     * @Author jsl
     * @Date 17:29 2018/8/4
     * @Description    增加合同
     **/
    @PostMapping(value = "add",produces = "application/json;charset=UTF-8")
    public String addSaleContract(@RequestBody SaleContract saleContract){
        //设置登陆人信息
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);
        saleContract.setUserName(loginUser.getUserName());

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        String staffName = MethodUtil.getStaffName(baseInfo);

        saleContract.setCreatedBy(createdBy);
        saleContract.setOurSignatory(staffName);

        // 合同默认状态-未完成
        saleContract.setStatus(0);
        saleContractService.add(saleContract);
        return getSuccessJson();
    }

    /**
     * @Author jsl
     * @Date 删除 2018/8/6
     * @Description
     **/
    @PostMapping(value = "/del/{id}", produces = "application/json;charset=UTF-8")
    public String del(@PathVariable("id") Long rowId){
        //执行删除
        if(saleContractService.del(rowId)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询合同信息
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        //查询
        SaleContract salecontract = saleContractService.getSaleContractById(rowId);
        //基地名称
        Long orgId = salecontract.getOrgId();
        if(orgId != null) {
            OrgAndOrgInfo baseInfo = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(), orgService.getOrgById(orgId));
            salecontract.setOrgName(baseInfo.getBaseOrDeptName());
        }
        //部门名称
        Long departId = salecontract.getDepartId();
        if(departId != null) {
            OrgAndOrgInfo deptInfo = (OrgAndOrgInfo) MethodUtil.getObject(OrgAndOrgInfo.class.getName(), orgService.getOrgById(departId));
            salecontract.setOrgName(deptInfo.getBaseOrDeptName());
        }
        //id转name
        List<SaleContractDetail> productList = salecontract.getProductList();

        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                salecontract = (SaleContract) super.translateIdToNameObj(salecontract,allCodeList,new String[]{
                        "contractType,rowId,name",
                        "payMethod,rowId,name",
                        "saleCurrency,rowId,name",
                        "saleCurrency,rowId,name",
                        "deliveryAddress,rowId,name"});
                productList = super.translateIdToName(productList,allCodeList,new String[]{
                   "unit,rowId,name"
                });

            }
        }

        return getSuccessJson(salecontract);
    }


    /**
     * 修改
     * @param
     * @return
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(@RequestBody SaleContract saleContract) {
        //更新
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long lastCreatedBy = MethodUtil.getStaffRowId(baseInfo);
        saleContract.setLastUpdateBy(lastCreatedBy);

        if(saleContractService.update(saleContract)){
            return getSuccessJson();
        }
        return getFailJson();
    }


    /**
     * @Author jsl
     * @Date 根据id查询回款信息 2018/8/6
     * @Description    
     **/
    @RequestMapping(value = "/getReturnMoney/{id}", produces = "application/json;charset=UTF-8")
    public String getReturnMoneyById(@PathVariable("id") Long rowId) {
        //查询回款信息
        //调用回款记录的查询接口  根据合同id 查询合同下的回款记录
        List<PaymentPlan> paymentPlanList = saleContractService.getPaymentDetailBySaleContractId(rowId);
        //id转name

        String allCode = saleCommonService.getAllCode();
        for(PaymentPlan paymentPlan : paymentPlanList){
            List<PaymentPlanDetail> paymentPlanDetailList = paymentPlan.getPaymentPlanDetailList();
            if (allCode != null) {
                JSONObject parseObject = JSONObject.parseObject(allCode);
                if ((Integer) parseObject.get("code") == 200) {
                    List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                    paymentPlanDetailList =  super.translateIdToName(paymentPlanDetailList,allCodeList,new String[]{ "payMethod,rowId,name","typeId,rowId,name"});
                }
            }
            paymentPlan.setPaymentPlanDetailList(paymentPlanDetailList);
        }
        return getSuccessJson(paymentPlanList);
    }

    /**
     * @Author jsl
     * @Date 查询发票信息 (根据销售单的id查询此销售单下所有的发票)2018/8/6
     * @Description
     **/
    @RequestMapping(value = "/getInvoice/{id}",produces = "application/json;charset=UTF-8")
    public String getInvoiceById(@PathVariable("id") Long saleContractId){
        //查询发票信息
        List<InvoiceForm> invoiceList = saleContractService.searchInvoiceById(saleContractId);
        //id转name
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                invoiceList = super.translateIdToName(invoiceList,allCodeList,new String[]{"continentId,rowId,name",
                        "invoiceTypeId,rowId,name" });
            }
        }

        return getSuccessJson(invoiceList);
    }


    /**
     * @Author jsl
     * @Date 出运备货计划 2018/8/6
     * @Description
     **/
    @RequestMapping(value = "/getDeliveryPlan/{id}",produces = "application/json;charset=UTF-8")
    public String getDeliveryPlan(@PathVariable("id") Long id){
        //出运备货计划信息
        //......

        return getSuccessJson();
    }


    /**
     * @Author jsl
     * @Date 实际出运信息 2018/8/6
     * @Description
     **/
    @RequestMapping(value = "/getTrueDelivery/{id}",produces = "application/json;charset=UTF-8")
    public String getTrueDelivery(@PathVariable("id") Long id){
        //实际出运信息信息
        //......

        return getSuccessJson();
    }


    /**
     * @Author jsl
     * @Date 操作日志 2018/8/6
     * @Description
     **/
    @RequestMapping(value = "/getLog/{id}",produces = "application/json;charset=UTF-8")
    public String getLog(@PathVariable("id") Long id){
        //操作日志
        //......

        return getSuccessJson();
    }

    /**
     * @Author jsl
     * @Date 新增发票信息 2018/8/6
     * @Description
     **/
    @PostMapping(value = "addInvoice",produces = "application/json;charset=UTF-8")
    public String addSaleInvoice(@RequestBody InvoiceForm invoiceForm){

        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long createdBy = MethodUtil.getStaffRowId(baseInfo);
        invoiceForm.setCreatedBy(createdBy);

        saleContractService.addInvoice(invoiceForm);
        return getSuccessJson();
    }


    /**
     * @Author jsl
     * @Date 删除发票信息(根据发票的id进行删除) 2018/8/6
     * @Description
     **/
    @PostMapping(value = "/delInvoice/{invoiceId}", produces = "application/json;charset=UTF-8")
    public String delInvoice(@PathVariable("invoiceId") Long rowId){
        //执行删除
        if(saleContractService.delInvoice(rowId)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 根据发票id查询单张发票的信息 2018/8/7
     * @Description    
     **/
    @RequestMapping(value = "/getSingleInvoice/{invoiceId}", produces = "application/json;charset=UTF-8")
    public String getSingleInvoice(@PathVariable("invoiceId") Long invoiceId){
        InvoiceForm invoiceForm = new InvoiceForm();
        invoiceForm = saleContractService.getSingleInvoiceById(invoiceId);

        //id转name
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                invoiceForm = (InvoiceForm) super.translateIdToNameObj(invoiceForm,allCodeList,new String[]{ "invoiceTypeId,rowId,name"});
            }
        }

        return getSuccessJson(invoiceForm);
    }
    
    
    /**
     * 修改发票信息（根据 发票id进行删除）
     * @param
     * @return
     */
    @PostMapping(value = "/updateInvoice", produces = "application/json;charset=UTF-8")
    public String updateInvoice(@RequestBody InvoiceForm invoiceForm) {
        //更新
        String token = RequestContext.get().getToken();
        User loginUser = redisService.getLoginUserByToken(token);

        String baseInfo = staffService.getBaseInfo(loginUser.getUserName());
        Long lastCreatedBy = MethodUtil.getStaffRowId(baseInfo);
        invoiceForm.setLastUpdateBy(lastCreatedBy);
        if(saleContractService.updateInvoice(invoiceForm)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 回款计划明细增加(修改合同的已付金额) 2018/8/8
     * @Description
     **/
    @PostMapping(value = "addPayPlanDetail",produces = "application/json;charset=UTF-8")
    public String addPayPlanDetail(@RequestBody SalePayPlanDetail salePayPlanDetail){

        String token = RequestContext.get().getToken();
        User user = redisService.getLoginUserByToken(token);
        salePayPlanDetail.setCreatedBy(user.getCreatedBy());
        //增加
        if(saleContractService.addPayPlanDetail(salePayPlanDetail)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 删除回款明细(更新合同里的已付金额) 2018/8/6
     * @Description
     **/
    @PostMapping(value = "/delPayPlanDetail/{id}", produces = "application/json;charset=UTF-8")
    public String delPayPlanDetail(@PathVariable("id") Long id){
        //执行删除
        if(saleContractService.delPayPlanDetail(id)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * @Author jsl
     * @Date 根据回款明细id查询 2018/8/7
     * @Description
     **/
    @RequestMapping(value = "/getPayPlanDetail/{id}", produces = "application/json;charset=UTF-8")
    public String getPayPlanDetail(@PathVariable("id") Long id){
        SalePayPlanDetail salePayPlanDetail = new SalePayPlanDetail();
        salePayPlanDetail = saleContractService.getPayPlanDetail(id);
        return getSuccessJson();
    }


    /**
     * 修改回款明细（根据 发票id进行删除）
     * @param
     * @return
     */
    @PostMapping(value = "/updatePayPlanDetail", produces = "application/json;charset=UTF-8")
    public String updatePayPlanDetail(@RequestBody SalePayPlanDetail salePayPlanDetail) {
        //更新
        if(saleContractService.updatePayPlanDetail(salePayPlanDetail)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 根据客户查询合同
     * @Author created by barrett in 18-8-8 下午4:17
     */
    @GetMapping(value="/getSaleContractByCustomer")
    public String getSaleContractByCustomer(SaleContractForm saleContractForm){
        PageListVO<SaleContract> pageListVO = saleContractService.searchSaleContractByCustomer(saleContractForm);
        String allCode = saleCommonService.getAllCode();
        List<SaleContract> detailList = pageListVO.getList();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                detailList = super.translateIdToName(detailList,allCodeList,new String[]{
                        "contractType,rowId,name",
                        "payMethod,rowId,name",
                        "saleCurrency,rowId,name"});
            }
        }
        pageListVO.setList(detailList);
        return getSuccessJson(pageListVO);
    }


    /**
     * @Author jsl
     * @Date 15:17 2018/8/25
     * @Description   根据staffId查询销售人员下所有的客户信息
     **/

    @RequestMapping(value = "/getSaleContractList/{staffId}", produces = "application/json;charset=UTF-8")
    public String getCustomerListById(@PathVariable("staffId") Long staffId){

        SaleContractForm form = new SaleContractForm();
        form.setTransferMan(staffId);
        PageListVO<SaleContract> pageListVO = saleContractService.getSaleContractListById(form);
        //id转name
        String allCode = saleCommonService.getAllCode();
        List<SaleContract> detailList = pageListVO.getList();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                detailList = super.translateIdToName(detailList,allCodeList,new String[]{
                        "contractType,rowId,name",
                        "payMethod,rowId,name",
                        "saleCurrency,rowId,name"});
            }
        }
        pageListVO.setList(detailList);
        return getSuccessJson(pageListVO);
    }


    /**
     * @Author jsl
     * @Date 18:14 2018/8/25
     * @Description    更新transferMan
     **/
    @RequestMapping(value = "/updateTransferMan/{staffId}/{rowId}", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String updateTransferMan(@PathVariable("staffId") Long staffId, @PathVariable("rowId") Long rowId) {
        if (saleContractService.updateTransferMan(staffId,rowId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }
    
    /**
	 *  导入客户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public String importExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        List<SaleContractExcelForm> list1 = new ArrayList<SaleContractExcelForm>();
        
        List<SaleContractDetailExcelForm> list2 = new ArrayList<SaleContractDetailExcelForm>();
        
        List<InvoiceExcelForm> list3 = new ArrayList<InvoiceExcelForm>();
        
        //1.将Excel转成 CustomerExcelForm
        ImportExcelUtils.export(request, SaleContractExcelForm.class, list1,1,0);
        
        //2.将Excel转成 CustomerContactsExcelForm
        ImportExcelUtils.export(request, SaleContractDetailExcelForm.class, list2,1,1);
        
        //2.将Excel转成 CustomerContactsExcelForm
        ImportExcelUtils.export(request, InvoiceExcelForm.class, list3,1,2);
        
        //3.将CustomerExcelForm入库
        if(!list1.isEmpty() && !list2.isEmpty()) {
            saleContractService.importExcelSaleContract(list1);

            //4.将CustomerContactsExcelForm入库
            saleContractService.importExcelSaleContractDetail(list2);
        }else if(list1.isEmpty()){
            throw new AppException("合同记录不能为空！");
        }else{
            throw new AppException("产品明细不能为空！");
        }
        
        //4.将InvoiceExcelForm入库
        saleContractService.importExcelInvoice(list3);
        
        return getSuccessJson();
    }
	
	/**
	 * @Author WJ
     * @Description    下载模板
     */
    @RequestMapping(value = "/download", produces = "application/json;charset=UTF-8")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath").split("&")[0];
        FileClient.downloadFile(filePath, response);
    }

}
