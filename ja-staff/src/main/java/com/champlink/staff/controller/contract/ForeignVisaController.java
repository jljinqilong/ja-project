package com.champlink.staff.controller.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.ForeignVisa;
import com.champlink.common.form.staff.contract.ForeignVisaForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.contract.ForeignVisaService;

@RestController
@RequestMapping("/foreignVisa")
public class ForeignVisaController extends BaseController {

    /**
     * 在华外籍员工工作许可证信息
     */

    @Autowired
    private ForeignVisaService foreignVisaService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(ForeignVisa foreignVisa) {
        if (foreignVisaService.add(foreignVisa)) {
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
        if (foreignVisaService.del(id)) {
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
    public String update(ForeignVisa foreignVisa) {
        if (foreignVisaService.update(foreignVisa)) {
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
    public String pageList(ForeignVisaForm foreignVisaForm) {
        PageListVO pageListVO = foreignVisaService.pageList(foreignVisaForm);
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
        ForeignVisa foreignVisa = foreignVisaService.getById(rowId);
        return getSuccessJson(foreignVisa);
    }

}