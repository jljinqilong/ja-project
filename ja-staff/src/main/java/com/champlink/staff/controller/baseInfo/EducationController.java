package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.Education;
import com.champlink.common.domain.system.Code;
import com.champlink.staff.service.baseInfo.EducationService;
import com.champlink.staff.service.call.SystemService;

@RestController
@RequestMapping("/education")
public class EducationController extends BaseController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/addOrEdit")
    public String addEducation(Education education) {
        if (educationService.addEducation(education)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delEducationListByStaffId(@PathVariable Long id) {
        if (educationService.delEducationListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    public String searchEducationList(@PathVariable Long id) {
        List<Education> list = educationService.getEducationList(id);
        String allCode = systemService.getAllCode();
        if (allCode != null) {
            JSONObject parseObject = JSONObject.parseObject(allCode);
            if ((Integer) parseObject.get("code") == 200) {
                List<Code> allCodeList = JSONObject.parseArray(parseObject.getString("data"), Code.class);
                list = super.translateIdToName(list, allCodeList, new String[] {"isHighestDegree,rowId,name", "education,rowId,name", "degree,rowId,name", "graduationSituation,rowId,name",
                    "schoolingDocumentsType,rowId,name", "degreeCountry,rowId,name", "learningStyle,rowId,name"});
                list = super.translateIdToName(list, allCodeList, new String[] {"isHighestEducation,rowId,name"});
            }
        }
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        Education education = educationService.getByRowId(id);
        return getSuccessJson(education);
    }

}