package com.champlink.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.Role;
import com.github.pagehelper.Page;

@Mapper
public interface RoleDao {

    int add(Role role);

    int delById(@Param("roleId") Long roleId);

    int update(Role role);

    Page<Role> pageList(Paginater paginater);

    List<Role> allList();

    Role getById(Long rowId);

    Role getByName(String name);

    List<Role> getByUid(Long userId);

    int getRoleByName(@Param("name") String name, @Param("rowId") Long rowId);

    int checkRoleIsUse(@Param("rowId") Long rowId);

}
