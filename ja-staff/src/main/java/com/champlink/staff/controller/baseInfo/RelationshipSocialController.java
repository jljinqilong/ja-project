package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.RelationshipSocial;
import com.champlink.common.domain.system.Code;
import com.champlink.staff.service.baseInfo.RelationshipSocialService;
import com.champlink.staff.service.call.SystemService;

@RestController
@RequestMapping("/relationshipSocial")
public class RelationshipSocialController extends BaseController {

    @Autowired
    private RelationshipSocialService relationshipSocialService;

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/addOrEdit")
    @ResponseBody
    public String addRelationshipSocial(RelationshipSocial relationshipSocial) {
        if (relationshipSocialService.addRelationshipSocial(relationshipSocial)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    @ResponseBody
    public String delRelationshipSocialListByStaffId(@PathVariable Long id) {
        if (relationshipSocialService.delRelationshipSocialListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    @ResponseBody
    public String queryRelationshipSocialList(@PathVariable Long id) {
        List<RelationshipSocial> list = relationshipSocialService.getRelationshipSocialList(id);
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"sex,rowId,name"});
            }
        }
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    @ResponseBody
    public String getByRowId(@PathVariable Long id) {
        RelationshipSocial relationshipSocial = relationshipSocialService.getByRowId(id);
        return getSuccessJson(relationshipSocial);
    }

}