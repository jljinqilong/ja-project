package com.champlink.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.User;
import com.github.pagehelper.Page;

@Mapper
public interface UserDao {

    int add(User user);

    int batchAdd(@Param("list") List<User> list);

    int delByIds(@Param("list") List<Long> list);

    int delByUserName(@Param("userName") String usreName);

    int update(User user);

    int changeByUserName(User user);

    int changeByUserNameList(@Param("list") List<String> userNameList, @Param("status") int status);

    User getByName(String userName);

    User getById(Long rowId);

    Page<User> pageList(Paginater paginater);

    int checkName(@Param("name") String name, @Param("rowId") Long rowId);

    int checkCellphoneNo(@Param("cellphoneNo") String cellphoneNo, @Param("rowId") Long rowId);

    int checkEmail(@Param("email") String email, @Param("rowId") Long rowId);
}
