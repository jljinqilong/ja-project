package com.champlink.staff.controller.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.CompanyRecord;
import com.champlink.common.form.staff.contract.CompanyRecordForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.contract.CompanyRecordService;

@RestController
@RequestMapping("/companyRecord")
public class CompanyRecordController extends BaseController {

    /**
     * 员工公司档案
     */

    @Autowired
    private CompanyRecordService companyRecordService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(CompanyRecord companyRecord) {
        if (companyRecordService.add(companyRecord)) {
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
        if (companyRecordService.del(id)) {
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
    public String update(CompanyRecord companyRecord) {
        if (companyRecordService.update(companyRecord)) {
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
    public String pageList(CompanyRecordForm companyRecordForm) {
        PageListVO pageListVO = companyRecordService.pageList(companyRecordForm);
        return getSuccessJson(pageListVO);
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long id) {
        CompanyRecord companyRecord = companyRecordService.getById(id);
        return getSuccessJson(companyRecord);
    }

}