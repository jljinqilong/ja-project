package com.champlink.staff.controller.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.staff.baseInfo.ProjectExperience;
import com.champlink.staff.service.baseInfo.ProjectExperienceService;

@RestController
@RequestMapping("/projectExperience")
public class ProjectExperienceController extends BaseController {

    @Autowired
    private ProjectExperienceService projectExperienceService;

    @RequestMapping(value = "/addOrEdit")
    public String addRelationshipInner(ProjectExperience projectExperience) {
        if (projectExperienceService.addProjectExperience(projectExperience)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/del/{id}")
    public String delProjectExperienceListByStaffId(@PathVariable Long id) {
        if (projectExperienceService.delProjectExperienceListByStaffId(id)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/list/{id}")
    public String searchRelationshipInnerList(@PathVariable Long id) {
        List<ProjectExperience> list = projectExperienceService.getProjectExperienceList(id);
        return getSuccessJson(list);
    }

    @RequestMapping(value = "/getByRowId/{id}")
    public String getByRowId(@PathVariable Long id) {
        ProjectExperience projectExperience = projectExperienceService.getByRowId(id);
        return getSuccessJson(projectExperience);
    }

}