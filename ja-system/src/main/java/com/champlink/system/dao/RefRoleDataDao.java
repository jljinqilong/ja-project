package com.champlink.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefRoleDataDao {

    int addBatch(@Param("list") List<Long> list, @Param("roleId") Long roleId, @Param("isHalf") Integer isHalf);

    List<String> queryRoleDataForId(@Param("roleId") Long roleId);

    int delByRoleId(@Param("roleId") Long roleId);

    List<Long> searchRoleDataByUserId(@Param("userId") Long userId);

	int addOrgToUpdateData(@Param("orgIds") List<Long> orgIds);

}
