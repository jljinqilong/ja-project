package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.OuterExperience;
import com.champlink.staff.service.baseInfo.OuterExperienceService;

@RestController
@RequestMapping("/outerExperience")
public class OuterExperienceController extends BaseController {

    @Autowired
    private OuterExperienceService outerExperienceService;

    @RequestMapping(value = "/addOrEdit")
    public String addOuterExperience(OuterExperience outerExperience) {
        if (outerExperienceService.addOuterExperience(outerExperience)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delOuterExperienceListByStaffId(@PathVariable Long id) {
        if (outerExperienceService.delOuterExperienceListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    public String searchOuterExperienceList(@PathVariable Long id) {
        List<OuterExperience> list = outerExperienceService.getOuterExperienceList(id);
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        OuterExperience outerExperience = outerExperienceService.getByRowId(id);
        return getSuccessJson(outerExperience);
    }

}