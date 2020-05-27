package com.champlink.org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.org.Org;
import com.champlink.common.domain.org.OrgAndOrgInfo;
import com.champlink.common.util.tree.Node;

@Mapper
public interface OrgDao {

    int add(Org org);

    int delById(Long rowId);

    int update(Org org);

    Org findOne(Long rowId);

    List<Node> getNodeTree();

    List<Org> allList();

    List<Org> findListByParentId(Long parentId);

    List<OrgAndOrgInfo> queryOrgAndOrgInfoList();

    Org queryOrgByParentId(Org org);

    int queryOrgCount();

    int queryOrgCountByShortName(@Param("baseOrDeptShortName") String baseOrDeptShortName);

    int queryBaseCountByName(@Param("baseOrDeptName") String baseOrDeptName);
    
    List<Org> findListByRowIds(@Param("ids") List<Long> ids);
    
    Org queryMaxCountByParent(Org org);
}