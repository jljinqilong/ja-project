package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.RelationshipInner;
import com.champlink.staff.service.baseInfo.RelationshipInnerService;

@RestController
@RequestMapping("/relationshipInner")
public class RelationshipInnerController extends BaseController {

    @Autowired
    private RelationshipInnerService relationshipInnerService;

    @RequestMapping(value = "/addOrEdit")
    public String addRelationshipInner(RelationshipInner relationshipInner) {
        if (relationshipInnerService.addRelationshipInner(relationshipInner)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delRelationshipInnerListByStaffId(@PathVariable Long id) {
        if (relationshipInnerService.delRelationshipInnerListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    public String searchRelationshipInnerList(@PathVariable Long id) {
        List<RelationshipInner> list = relationshipInnerService.getRelationshipInnerList(id);
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        RelationshipInner relationshipInner = relationshipInnerService.getByRowId(id);
        return getSuccessJson(relationshipInner);
    }

}