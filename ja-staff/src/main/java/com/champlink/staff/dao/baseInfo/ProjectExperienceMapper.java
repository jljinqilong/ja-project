package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.ProjectExperience;

@Mapper
public interface ProjectExperienceMapper {

    public Integer insertProjectExperience(ProjectExperience projectExperience);

    public Integer updateProjectExperience(ProjectExperience projectExperience);

    public Integer delProjectExperienceListByStaffId(@Param("rowId") Long rowId);

    public List<ProjectExperience> queryProjectExperienceList(@Param("staffId") Long staffId);

    public ProjectExperience getByRowId(@Param("rowId") Long rowId);
    
    public int delAllByStaffId(@Param("staffId") Long staffId);

}