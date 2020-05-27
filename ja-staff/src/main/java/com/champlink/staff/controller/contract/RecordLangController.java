package com.champlink.staff.controller.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.RecordLang;
import com.champlink.common.form.staff.contract.RecordLangForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.contract.RecordLangService;

@RestController
@RequestMapping("/recordLang")
public class RecordLangController extends BaseController {

    /**
     * 员工语言能力档案
     */

    @Autowired
    private RecordLangService recordLangService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(RecordLang recordLang) {
        if (recordLangService.add(recordLang)) {
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
        if (recordLangService.del(id)) {
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
    public String update(RecordLang recordLang) {
        if (recordLangService.update(recordLang)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询列表
     * 
     * @param ContractForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(RecordLangForm recordLangForm) {
        PageListVO pageListVO = recordLangService.pageList(recordLangForm);
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
        RecordLang recordLang = recordLangService.getById(rowId);
        return getSuccessJson(recordLang);
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/getByStaffId/{id}", produces = "text/json;charset=UTF-8")
    public String getByStaffId(@PathVariable("id") Long staffId) {
        List<RecordLang> recordLang = recordLangService.getByStaffId(staffId);
        return getSuccessJson(recordLang);
    }

}