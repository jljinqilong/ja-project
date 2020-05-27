package com.champlink.staff.controller.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.StaffConstant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.Disability;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.staff.contract.DisabilityForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.call.SystemService;
import com.champlink.staff.service.contract.DisabilityService;

@RestController
@RequestMapping("/disability")
public class DisabilityController extends BaseController {

    /**
     * 员工残疾信息
     */

    @Autowired
    private DisabilityService disabilityService;

    @Autowired
    private SystemService systemService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(Disability disability) {
        if (disabilityService.add(disability)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id) {
        if (disabilityService.del(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(Disability disability) {
        if (disabilityService.update(disability)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询列表
     * 
     * @param ContractFormForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(DisabilityForm disabilityForm) {
        PageListVO<Disability> pageListVO = disabilityService.pageList(disabilityForm);
        List<Disability> list = pageListVO.getList();
        String code = systemService.getByTpye(StaffConstant.DISABILITY_TYPE);
        if (code != null) {
            JSONObject parseObject = JSONObject.parseObject(code);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"disabilityType,rowId,name"});
            }
        }
        pageListVO.setList(list);
        return getSuccessJson(pageListVO);
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        Disability disability = disabilityService.getById(rowId);
        return getSuccessJson(disability);
    }

}