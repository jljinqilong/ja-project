package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.Education;

@Mapper
public interface EducationMapper {

    public Integer insertEducation(Education education);

    public Integer updateEducation(Education education);

    public Integer delEducationListByStaffId(@Param("rowId") Long rowId);

    public List<Education> queryEducationList(@Param("staffId") Long staffId);

    public Education getByRowId(@Param("rowId") Long rowId);
    
    public int delAllByStaffId(@Param("staffId") Long staffId);
}