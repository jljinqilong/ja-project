package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.RelationshipInner;

@Mapper
public interface RelationshipInnerMapper {

    public Integer insertRelationshipInner(RelationshipInner relationshipInner);

    public Integer updateRelationshipInner(RelationshipInner relationshipInner);

    public Integer delRelationshipInnerListByStaffId(@Param("rowId") Long rowId);

    public List<RelationshipInner> queryRelationshipInnerList(@Param("staffId") Long staffId);

    public RelationshipInner getByRowId(@Param("rowId") Long rowId);
    
    public int delAllByStaffId(@Param("staffId") Long staffId);

}