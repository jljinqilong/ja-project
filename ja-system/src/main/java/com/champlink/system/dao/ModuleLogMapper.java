package com.champlink.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.ModuleLog;
import com.github.pagehelper.Page;

@Mapper
public interface ModuleLogMapper {

    /**
     * 添加
     * 
     * @param code
     * @return
     */
    int add(ModuleLog moduleLog);

    /**
     * 分页列表
     * 
     * @param paginater
     * @return
     */
    Page<ModuleLog> pageList(Paginater paginater);

    List<ModuleLog> allList(ModuleLog moduleLog);

}
