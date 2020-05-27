package com.champlink.sale.controller.inquiry;

import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.sale.inquiry.InquiryRecordDetail;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.sale.inquiry.InquiryRecordDetailForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.util.method.MethodUtil;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.sale.service.call.SaleCommonService;
import com.champlink.sale.service.call.StaffService;
import com.champlink.sale.service.inquiry.InquiryRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 多条询价明细对应一条询价记录
 * @Author created by barrett in 18-8-8 上午9:53
 */
@RestController
@RequestMapping("/inquiryRecordDetail")
public class InquiryRecordDetailController extends BaseController {

    @Autowired
    private InquiryRecordDetailService inquiryRecordDetailService;
    @Autowired
    private SaleCommonService saleCommonService;
    @Autowired
    private StaffService staffService;

    /**
     * 分页查询
     * @Author created by barrett in 18-8-8 下午5:12
     */
    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String pageList(InquiryRecordDetailForm form) {
        PageListVO<InquiryRecordDetail> pageListVO = inquiryRecordDetailService.pageList(form);
        List<InquiryRecordDetail> list = pageListVO.getList();
        String allCode = saleCommonService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list,allCodeList,
                        new String[]{"tradeModeId,rowId,name"});
            }

        }
        pageListVO.setList(list);

        return getSuccessJson(pageListVO);
    }

    /**
    　* 添加
    　* @author created by barrett in 18-8-8 下午2:03
    　*/
    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String add(@Valid InquiryRecordDetail inquiryRecord, BindingResult results) {
        if (results.hasErrors())
            return getFailJson(results.getFieldError().getDefaultMessage());

        setUserInfoByRedisToken(inquiryRecord,true);

        if (inquiryRecordDetailService.add(inquiryRecord)) {
            return getSuccessJson();
        }
        return getFailJson();
    }


    /**
      * 修改询价记录
      * @author created by barrett in 18-8-8 下午5:34
      */
    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String update(@Valid InquiryRecordDetail inquiryRecordDetail, BindingResult results) {
        if (results.hasErrors())
            return results.getFieldError().getDefaultMessage();

        setUserInfoByRedisToken(inquiryRecordDetail,false);

        if (inquiryRecordDetailService.update(inquiryRecordDetail)) {
            return getSuccessJson();
        }
        return getFailJson();
    }



    /**
     * 查看询价详情
     * @author created by barrett in 18-8-8 下午3:14
     */
    @RequestMapping(value = "/getInquiryRecord/{rowId}", produces = "application/json;charset=UTF-8")
    public String getInquiryRecord(@PathVariable Long rowId) {
        List<InquiryRecordDetail> list = inquiryRecordDetailService.getInquiryRecord(rowId);

        return getSuccessJson(list);
    }

    /**
     * 设置创建人
     * @param record
     */
    public void setUserInfoByRedisToken(InquiryRecordDetail record,boolean flag){
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
     * （逻辑删除）询价
     * @Author created by barrett in 18-8-8 下午4:40
     * @Param
     * @return
     */
    @RequestMapping(value="/delete",produces = "application/json;charset=UTF-8")
    public String delete(@RequestParam(value="ids")String ids){

        if (inquiryRecordDetailService.deleteByIds(ids)) {
            return getSuccessJson();
        }
        return getFailJson();
    }
}
