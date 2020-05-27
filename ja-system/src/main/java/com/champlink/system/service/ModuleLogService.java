package com.champlink.system.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.ModuleLog;
import com.champlink.common.form.system.ModuleLogForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.dao.ModuleLogMapper;

@Service
public class ModuleLogService {

    @Autowired
    private ModuleLogMapper moduleLogMapper;

    /**
     * 新增
     * 
     * @param code
     * @return
     */
    public boolean add(ModuleLog moduleLog) {
        moduleLog.setOptTime(new Date());
        if (moduleLogMapper.add(moduleLog) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取列表
     * 
     * @param form
     * @return
     */
    public PageListVO pageList(ModuleLogForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(moduleLogMapper.pageList(paginater));
        return pageListVO;
    }

    /**
     * 获取所有信息
     * 
     * @param form
     * @return
     */
    public List<ModuleLog> allList(ModuleLog moduleLog) {
        List<ModuleLog> list = moduleLogMapper.allList(moduleLog);
        return list;
    }
}
