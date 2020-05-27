package com.champlink.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.CodeType;
import com.champlink.common.form.system.CodeForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.service.AutogenerateCoding;
import com.champlink.system.service.CodeService;
import com.champlink.system.service.CodeTypeService;

@RestController
@RequestMapping("/code")
public class CodeController extends BaseController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private CodeTypeService codeTypeService;

    @Autowired /* 自动生成编码 */
    private AutogenerateCoding autogenerateCodingService;

    /**
     * 分页列表
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(CodeForm form) {
        PageListVO<Code> pageListVO = codeService.pageList(form);
        List<CodeType> typeList = codeTypeService.allList();
        List<Code> list = super.translateIdToName(pageListVO.getList(), typeList, new String[] {"typeId,rowId,name"});
        pageListVO.setList(list);
        return getSuccessJson(pageListVO);
    }

    /**
     * 添加
     * 
     * @param code
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
    public String add(Code code) {
        if (codeService.add(code)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 批量删除
     * 
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/del", produces = "text/json;charset=UTF-8")
    public String del(@RequestParam("codeIds") String codeIds) {
        if (codeService.delByIds(codeIds)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更改
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/change", produces = "text/json;charset=UTF-8")
    public String del(@RequestParam("codeIds") String codeIds, @RequestParam("status") Integer status) {
        if (codeService.changeStatus(codeIds, status)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", produces = "text/json;charset=UTF-8")
    public String update(Code code) {
        if (codeService.update(code)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        Code code = codeService.getById(rowId);
        return getSuccessJson(code);
    }

    /**
     * 根据 code,name 查询详细
     *
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/getId", produces = "text/json;charset=UTF-8")
    public String getByCodeAndName(@RequestParam("typeCode") String typeCode, @RequestParam("name") String name) {
        Code code = codeService.getByCodeAndName(typeCode, name);
        return getSuccessJson(code);
    }

    /**
     * 查询列表
     * 
     * @param typeCode
     * @return
     */
    @RequestMapping(value = "/getByTpye")
    public String getByTpye(@RequestParam("typeCode") String typeCode) {
        List<Code> list = codeService.getByTypeCode(typeCode);
        return getSuccessJson(list);
    }

    /**
     * 返回所有字典项
     * 
     * @param typeCode
     * @return
     */
    @RequestMapping(value = "/getAllCode")
    public String getAllCode() {
        return getSuccessJson(codeService.getAllCode());
    }

    /**
     * 根据code自动生成编码 需要递增
     * 
     * @param num 递增规则需要的当前值
     * @return
     */
    @RequestMapping(value = "/generate/{code}/{num}", produces = "text/json;charset=UTF-8")
    public String generate(@PathVariable("code") String code, @PathVariable("num") String num) {
        if (code != null || !"".equals(code)) {
            String coding = autogenerateCodingService.getCoding(code, num);// getCoding("YES_OR_NO", num)
            if (coding != null && !"".equals(coding)) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("codeResult", coding);
                return getSuccessJson(jsonObj);
            }
        }
        return getFailJson();
    }

    /**
     * 根据code自动生成编码 随机生成
     * 
     * @return
     */
    @RequestMapping(value = "/generate/{code}", produces = "text/json;charset=UTF-8")
    public String generate(@PathVariable("code") String code) {
        if (code != null || !"".equals(code)) {
            String coding = autogenerateCodingService.getCoding(code, null);// getCoding("YES_OR_NO", num)
            if (coding != null && !"".equals(coding)) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("codeResult", coding);
                return getSuccessJson(jsonObj);
            }
        }
        return getFailJson();
    }
}
