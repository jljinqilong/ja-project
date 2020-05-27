package com.champlink.sale.controller.inquiry;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.inquiry.Inquiries;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.sale.inquiry.InquiriesForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.inquiry.InquiriesService;
import com.champlink.sale.service.inquiry.InquiryRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 询单评审
 *
 * @Author created by barrett in 18-8-6 下午8:53
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inquiriesAppraisal")
public class InquiriesAppraisalController extends BaseController {

    @Autowired
    private InquiriesService inquiriesService;
    @Autowired
    private SaleCommonService saleCommonService;

    /**
     * 询单评审分页查询
     *
     * @Author created by barrett in 2018/8/22 10:52
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String getInquiriesAppraisal(InquiriesForm form) {
        PageListVO<Inquiries> pageListVO = inquiriesService.pageListInquiriesAppraisal(form);
        List<Inquiries> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList,
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
     * 查看询单评审详情
     *
     * @author created by barrett in 18-8-1 下午3:14
     */
    @RequestMapping(value = "/get/{rowId}", produces = "application/json;charset=UTF-8")
    public String getAppraisal(@PathVariable Long rowId) {
        Inquiries inquiries = inquiriesService.getInquirires(rowId);
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                inquiries = (Inquiries) super.translateIdToNameObj(inquiries, allCodeList,
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
        return getSuccessJson(inquiries);
    }

    /**
     * 获取询单评审历史记录 分页 -- 暂无调用
     *
     * @return
     * @Author created by barrett in 2018/8/13 14:46
     * @Param
     */
    @GetMapping(value = "/listInquiriesAppraisalHistory", produces = "application/json;charset=UTF-8")
    public String getInquiriesAppraisalHistory(InquiriesForm form) {
        PageListVO<Inquiries> pageListVO = inquiriesService.pageListInquiriesAppraisalHistory(form);
        List<Inquiries> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList,
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
     * 确认评审记录
     *
     * @Author created by barrett in 2018/8/13 16:25
     */
    @PostMapping(value = "/confirmInquiriesAppraisal", produces = "application/json;charset=UTF-8")
    public String confirmInquiriesAppraisal(@RequestBody Inquiries inquiries) {
        if (inquiriesService.confirmInquiriesAppraisal(Long.valueOf(inquiries.getRowId()), inquiries.getStatus())) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 询单评审转合同 -- 暂无调用
     *
     * @Author created by barrett in 18-8-10 上午11:20
     */
    @GetMapping(value = "/appraisalTransferContract/{rowId}", produces = "application/json;charset=UTF-8")
    public String appraisalTransferContract(@PathVariable Long rowId) {
        if (inquiriesService.appraisalTransferContract(rowId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }


}
