package com.champlink.staff.service.baseInfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.champlink.common.domain.staff.baseInfo.ProjectExperience;
import com.champlink.staff.dao.baseInfo.ProjectExperienceMapper;

/**
 * 
 */
@Service
public class ProjectExperienceService {

    @Autowired
    private ProjectExperienceMapper projectExperienceMapper;

    /**
     * 添加
     * 
     */
    public boolean addProjectExperience(ProjectExperience projectExperience) {
        Long rowId = projectExperience.getRowId();
        if (StringUtils.isEmpty(rowId)) {
            if (projectExperienceMapper.insertProjectExperience(projectExperience) > 0) {
                return true;
            }
        } else {
            if (projectExperienceMapper.updateProjectExperience(projectExperience) > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 物理删除
     * 
     */
    public boolean delProjectExperienceListByStaffId(Long staffId) {
        if (projectExperienceMapper.delProjectExperienceListByStaffId(staffId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询
     * 
     * @return
     */
    public List<ProjectExperience> getProjectExperienceList(Long staffId) {
        return projectExperienceMapper.queryProjectExperienceList(staffId);
    }

    public ProjectExperience getByRowId(Long rowId) {
        return projectExperienceMapper.getByRowId(rowId);
    }

    public int delAllByStaffId(Long staffId) {
    	return projectExperienceMapper.delAllByStaffId(staffId);
    }
    
}
