package com.champlink.staff.controller.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.contract.RecordIt;
import com.champlink.common.form.staff.contract.RecordItForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.staff.service.contract.RecordItService;

@RestController
@RequestMapping("/recordIt")
public class RecordItController extends BaseController {

    /**
     * 员工计算机能力档案
     */

    @Autowired
    private RecordItService recordItService;

    /**
     * 新增
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(RecordIt recordIt) {
        if (recordItService.add(recordIt)) {
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
        if (recordItService.del(id)) {
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
    public String update(RecordIt recordIt) {
        if (recordItService.update(recordIt)) {
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
    public String pageList(RecordItForm recordItForm) {
        PageListVO pageListVO = recordItService.pageList(recordItForm);
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
        RecordIt recordIt = recordItService.getById(rowId);
        return getSuccessJson(recordIt);
    }

    /**
     * 查询详细
     * 
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/getByStaffId/{id}", produces = "text/json;charset=UTF-8")
    public String getByStaffId(@PathVariable("id") Long staffId) {
        List<RecordIt> recordIt = recordItService.getByStaffId(staffId);
        return getSuccessJson(recordIt);
    }

}