package com.champlink.staff.controller.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.OccupationalInjury;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.staff.contract.OccupationalInjuryForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.call.SystemService;
import com.champlink.staff.service.contract.OccupationalInjuryService;

@RestController
@RequestMapping("/occInjury")
public class OccupationalInjuryController extends BaseController {

    /**
     * 员工工伤信息
     */

    @Autowired
    private OccupationalInjuryService occupationalInjuryService;

    @Autowired
    private SystemService systemService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(OccupationalInjury occupationalInjury) {
        if (occupationalInjuryService.add(occupationalInjury)) {
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
        if (occupationalInjuryService.del(id)) {
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
    public String update(OccupationalInjury occupationalInjury) {
        if (occupationalInjuryService.update(occupationalInjury)) {
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
    public String pageList(OccupationalInjuryForm occupationalInjuryForm) {
        PageListVO<OccupationalInjury> pageListVO = occupationalInjuryService.pageList(occupationalInjuryForm);
        List<OccupationalInjury> list = pageListVO.getList();
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList,
                    new String[] {"occupationalInjuryTyle,rowId,name", "disabilityLevel,rowId,name", "nurseLevel,rowId,name", "accidentState,rowId,name"});
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
        OccupationalInjury occupationalInjury = occupationalInjuryService.getById(rowId);
        return getSuccessJson(occupationalInjury);
    }

}