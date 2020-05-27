package com.champlink.sale.controller.inquiry;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.common.domain.sale.inquiry.InquiriesProduct;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.sale.inquiry.InquiriesForm;
import com.champlink.common.util.file.FileClient;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.inquiry.InquiriesService;
import com.champlink.sale.service.inquiry.InquiryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 询单记录
 * @Author created by barrett in 18-8-6 下午8:53
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inquiries")
public class InquiriesController extends BaseController {

    @Autowired
    private InquiriesService inquiriesService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private InquiryRecordService inquiryRecordService;

    /**
     * 分页查询
     * @Author created by barrett in 18-8-7 下午5:12
     * @Param
     * @return
     */
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String pageList(InquiriesForm form) {
        PageListVO<Inquiries> pageListVO = inquiriesService.pageList(form);
        List<Inquiries> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();

        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list,allCodeList,
                        new String[]{
                                "productTypeId,rowId,name",
                                "tradeModeId,rowId,name",
                                "batteryTypeId,rowId,name",
                                "borderColorId,rowId,name",
                                "borderSpecificationId,rowId,name",
                                "backboardColorId,rowId,name",
                                "backboardMaterialId,rowId,name",
                                "junctionBoxId,rowId,name",
                                "salesPhaseId,rowId,name",
                                "projectAddressId,rowId,name",
                                "destinationCountryId,rowId,name",
                                "evaId,rowId,name",
                                "dispatchPlaceId,rowId,name",
                                "projectDistributionId,rowId,name",
                                "currencyId,rowId,name"
                });
            }
        }

        pageListVO.setList(list);

        return getSuccessJson(pageListVO);
    }

    /**
    　* 添加
    　* @author created by barrett in 18-8-1 下午2:03
    　*/
    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String add(@RequestBody Inquiries inquiries) {
        // @Valid
        Long loginId = inquiryRecordService.getLoginId();
        inquiries.setCreatedBy(loginId);
        if (inquiriesService.add(inquiries)) {
            return getSuccessJson();
        }
        return getFailJson();
    }


    /**
      * 修改询单基本信息
      * @author created by barrett in 18-8-3 下午5:34
      */
    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String update( @RequestBody Inquiries inquiries) {
        //@Valid
        Long loginId = inquiryRecordService.getLoginId();
        inquiries.setLastUpdateBy(loginId);
        if (inquiriesService.update(inquiries)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查看询单记录
     * @author created by barrett in 18-8-1 下午3:14
     */
    @RequestMapping(value = "/get/{rowId}", produces = "application/json;charset=UTF-8")
    public String getInquirires(@PathVariable Long rowId) {
        Inquiries inquiries = inquiriesService.getInquirires(rowId);
        if(inquiries == null)
            return null;

        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                inquiries = (Inquiries) super.translateIdToNameObj(inquiries,allCodeList,
                        new String[]{
                                "productTypeId,rowId,name",
                                "tradeModeId,rowId,name",
                                "batteryTypeId,rowId,name",
                                "borderColorId,rowId,name",
                                "borderSpecificationId,rowId,name",
                                "backboardColorId,rowId,name",
                                "backboardMaterialId,rowId,name",
                                "junctionBoxId,rowId,name",
                                "salesPhaseId,rowId,name",
                                "projectAddressId,rowId,name",
                                "destinationCountryId,rowId,name",
                                "evaId,rowId,name",
                                "dispatchPlaceId,rowId,name",
                                "projectDistributionId,rowId,name",
                                "currencyId,rowId,name"
                        });
                //产品list id翻译
                List<InquiriesProduct> inquiriesProductList = inquiries.getInquiriesProductList();
                inquiriesProductList = super.translateIdToName(inquiries.getInquiriesProductList(),allCodeList,
                        new String[]{
                                "cellNumberId,rowId,name",
                                "unitId,rowId,name",
                                "batteryTypeId,rowId,name",
                        });
            }

        }
        return getSuccessJson(inquiries);
    }

    /**
     * （逻辑删除）询单记录
     * @Author created by barrett in 18-8-7 下午4:40
     * @Param
     * @return
     */
    @PostMapping(value="/delete/{ids}",produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable String ids){

        if (inquiriesService.deleteByIds(ids)) {
            return getSuccessJson();
        }

        return getFailJson();
    }

    /**
     * 获取询单历史记录
     * @Author created by barrett in 2018/8/13 14:46
     * @Param
     * @return
     */
    @GetMapping(value = "/listInquiriesHistory", produces = "application/json;charset=UTF-8")
    public String getInquiriesHistory(InquiriesForm form) {
        PageListVO<Inquiries> pageListVO = inquiriesService.pageListHistory(form);
        List<Inquiries> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list,allCodeList,
                        new String[]{
                                "productTypeId,rowId,name",
                                "tradeModeId,rowId,name",
                                "batteryTypeId,rowId,name",
                                "borderColorId,rowId,name",
                                "borderSpecificationId,rowId,name",
                                "backboardColorId,rowId,name",
                                "backboardMaterialId,rowId,name",
                                "junctionBoxId,rowId,name",
                                "salesPhaseId,rowId,name",
                                "projectAddressId,rowId,name",
                                "destinationCountryId,rowId,name",
                                "evaId,rowId,name",
                                "dispatchPlaceId,rowId,name",
                                "projectDistributionId,rowId,name",
                                "currencyId,rowId,name"
                });
            }

        }
        pageListVO.setList(list);
        return getSuccessJson(pageListVO);
    }

    /**
     * 确认询单
     * @Author created by barrett in 18-8-9 下午7:57
     */
    @GetMapping(value = "/confirmInquiries/{rowId}",produces = "application/json;charset=UTF-8")
    public String confirmInquiries(@PathVariable Long rowId){
        if(inquiriesService.confirmInquiries(rowId)){
            return getSuccessJson();
        }
        return getFailJson();
    }
    /**
     * 补齐信息
     * @Author created by barrett in 2018/8/27 11:55
     */
    @PostMapping(value = "/supplementInquiries",produces = "application/json;charset=UTF-8")
    public String supplementInquiries( @RequestBody Inquiries inquiries){
        if(inquiriesService.supplementInquiries(inquiries)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 转评审
     * @Author created by barrett in 2018/8/27 10:31
     */
    @GetMapping(value = "/inquiriesTurnAppraisal/{rowId}",produces = "application/json;charset=UTF-8")
    public String inquiriesTurnAppraisal(@PathVariable Long rowId){
        if(inquiriesService.inquiriesTurnAppraisal(rowId)){
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 询单转合同
     * @Author created by barrett in 18-8-10 上午11:20
     */
    @GetMapping(value = "/transferContract/{rowId}",produces = "application/json;charset=UTF-8")
    public String  transferContract(@PathVariable Long rowId){
        if(inquiriesService.inquiriesTransferContract(rowId)){
            return getSuccessJson();
        }
        return getFailJson();
    }
    
    
    /**
	 * @Author WJ
     * @Description  导出询单
     */
    @RequestMapping(value = "/exportExcel", produces = "application/json;charset=UTF-8")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, Inquiries inquiries) throws Exception {

        inquiriesService.exportExcel(request,response,inquiries);

    }



}
