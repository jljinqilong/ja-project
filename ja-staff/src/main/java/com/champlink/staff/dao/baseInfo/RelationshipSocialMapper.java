package com.champlink.staff.dao.baseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.staff.baseInfo.RelationshipSocial;

@Mapper
public interface RelationshipSocialMapper {

    public Integer insertRelationshipSocial(RelationshipSocial relationshipSocial);

    public Integer updateRelationshipSocial(RelationshipSocial relationshipSocial);

    public Integer delRelationshipSocialListByStaffId(@Param("rowId") Long rowId);

    public List<RelationshipSocial> queryRelationshipSocialList(@Param("staffId") Long staffId);

    public RelationshipSocial getByRowId(@Param("rowId") Long rowId);

    public int delAllByStaffId(@Param("staffId") Long staffId);
}