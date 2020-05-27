package com.champlink.staff.controller.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.ForeignReside;
import com.champlink.common.form.staff.contract.ForeignResideForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.contract.ForeignResideService;

@RestController
@RequestMapping("/foreignReside")
public class ForeignResideController extends BaseController {

    /**
     * 在华外籍员工居留许可证信息
     */

    @Autowired
    private ForeignResideService foreignResideService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(ForeignReside foreignReside) {
        if (foreignResideService.add(foreignReside)) {
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
        if (foreignResideService.del(id)) {
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
    public String update(ForeignReside foreignReside) {
        if (foreignResideService.update(foreignReside)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询列表
     * 
     * @param ForeignResideForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(ForeignResideForm foreignResideForm) {
        PageListVO pageListVO = foreignResideService.pageList(foreignResideForm);
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
        ForeignReside foreignReside = foreignResideService.getById(rowId);
        return getSuccessJson(foreignReside);
    }

}