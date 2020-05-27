package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.OuterExperience;

@Mapper
public interface OuterExperienceMapper {

    public Integer insertOuterExperience(OuterExperience outerExperience);

    public Integer updateOuterExperience(OuterExperience outerExperience);

    public Integer delOuterExperienceListByStaffId(@Param("rowId") Long rowId);

    public List<OuterExperience> queryOuterExperienceList(@Param("staffId") Long staffId);

    public OuterExperience getByRowId(@Param("rowId") Long rowId);
    
    public int delAllByStaffId(@Param("staffId") Long staffId);
    
}