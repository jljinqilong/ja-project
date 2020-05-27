package com.champlink.staff.controller.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.Punishment;
import com.champlink.common.form.staff.contract.PunishmentForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.contract.PunishmentService;

@RestController
@RequestMapping("/punishment")
public class PunishmentController extends BaseController {

    /**
     * 员工惩罚信息
     */

    @Autowired
    private PunishmentService punishmentService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(Punishment punishment) {
        if (punishmentService.add(punishment)) {
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
        if (punishmentService.del(id)) {
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
    public String update(Punishment punishment) {
        if (punishmentService.update(punishment)) {
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
    public String pageList(PunishmentForm punishmentForm) {
        PageListVO pageListVO = punishmentService.pageList(punishmentForm);
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
        Punishment punishment = punishmentService.getById(rowId);
        return getSuccessJson(punishment);
    }

}