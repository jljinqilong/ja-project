package com.champlink.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.system.ModuleLog;
import com.champlink.common.form.system.ModuleLogForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.service.ModuleLogService;

@RequestMapping("/moduleLog")
@RestController
public class ModuleLogController extends BaseController {

    @Autowired
    private ModuleLogService moduleLogService;

    /**
     * 分页列表
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(ModuleLogForm form) {
        PageListVO pageListVO = moduleLogService.pageList(form);
        return getSuccessJson(pageListVO);
    }

    @RequestMapping(value = "/allList", produces = "text/json;charset=UTF-8")
    public String allList(ModuleLog moduleLog) {
        List<ModuleLog> list = moduleLogService.allList(moduleLog);
        return getSuccessJson(list);
    }

    /**
     * 添加
     * 
     * @param code
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(@RequestBody ModuleLog moduleLog) {
        if (moduleLogService.add(moduleLog)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

}
