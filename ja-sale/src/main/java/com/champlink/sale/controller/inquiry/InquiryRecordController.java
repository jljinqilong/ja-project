package com.champlink.sale.controller.inquiry;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.SaleConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.inquiry.InquiryRecord;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.inquiry.InquiryRecordForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.inquiry.InquiriesService;
import com.champlink.sale.service.inquiry.InquiryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 询价记录
 *
 * @Author created by barrett in 18-8-6 下午8:53
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inquiryRecord")
public class InquiryRecordController extends BaseController {

    @Autowired
    private InquiryRecordService inquiryRecordService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private InquiriesService inquiriesService;

    /**
     * 分页查询
     *
     * @return
     * @Author created by barrett in 18-8-7 下午5:12
     * @Param
     */
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String pageList(InquiryRecordForm form) {
        PageListVO<InquiryRecord> pageListVO = inquiryRecordService.pageList(form);
        List<InquiryRecord> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList,
                        new String[]{"tradeModeId,rowId,name",
                                "batteryTypeId,rowId,name",
                                "borderColorId,rowId,name",
                                "borderSpecificationId,rowId,name",
                                "backboardColorId,rowId,name",
                                "backboardMaterialId,rowId,name",
                                "junctionBoxId,rowId,name",
                                "salesPhaseId,rowId,name",
                                "projectAddressId,rowId,name",
                                "destinationCountryId,rowId,name",
                                "salesPhaseId,rowId,name",
                                "evaId,rowId,name"});
            }
        }
        pageListVO.setList(list);

        return getSuccessJson(pageListVO);
    }

    /**
     * 添加
     * @author created by barrett in 18-8-1 下午2:03
     *
     */
    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String add( @RequestBody InquiryRecord inquiryRecord) {
//@Valid
        if (inquiryRecordService.add(inquiryRecord)) {
            return getSuccessJson();
        }
        return getFailJson();
    }


    /**
     * 修改询价基本信息
     *
     * @author created by barrett in 18-8-3 下午5:34
     */
    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String update( @RequestBody InquiryRecord InquiryRecord) {
//@Valid
        if (inquiryRecordService.update(InquiryRecord)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查看询价记录
     *
     * @author created by barrett in 18-8-1 下午3:14
     */
    @RequestMapping(value = "/get/{rowId}", produces = "application/json;charset=UTF-8")
    public String getInquiryRecord(@PathVariable Long rowId) {
        InquiryRecord inquiryRecord = inquiryRecordService.getInquiryRecord(rowId);
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                inquiryRecord = (InquiryRecord) super.translateIdToNameObj(inquiryRecord, allCodeList,
                        new String[]{"tradeModeId,rowId,name",
                                "batteryTypeId,rowId,name",
                                "borderColorId,rowId,name",
                                "borderSpecificationId,rowId,name",
                                "backboardColorId,rowId,name",
                                "backboardMaterialId,rowId,name",
                                "junctionBoxId,rowId,name",
                                "projectAddressId,rowId,name",
                                "destinationCountryId,rowId,name",
                                "salesPhaseId,rowId,name",
                                "evaId,rowId,name"
                        });
            }
        }
        return getSuccessJson(inquiryRecord);
    }

    /**
     * （逻辑删除）询价记录
     *
     * @return
     * @Author created by barrett in 18-8-7 下午4:40
     * @Param
     */
    @PostMapping(value = "/delete/{ids}", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable(value="ids")  String ids) {

        if (inquiryRecordService.deleteByIds(ids)) {
            return getSuccessJson();
        }

        return getFailJson();
    }

    /**
     * 确认询价
     *
     * @Author created by barrett in 18-8-9 下午7:57
     */
    @PostMapping(value = "/confirmInquiry/{rowId}", produces = "application/json;charset=UTF-8")
    public String confirmInquiry(@PathVariable Long rowId) {
        if (inquiryRecordService.confirmInquiry(rowId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 转询单
     *
     * @Author created by barrett in 18-8-9 下午7:57
     */
    @PostMapping(value = "/turnInquiries/{rowId}", produces = "application/json;charset=UTF-8")
    public String turnInquiries(@PathVariable Long rowId) {
        if (inquiryRecordService.turnInquiries(rowId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }
}
