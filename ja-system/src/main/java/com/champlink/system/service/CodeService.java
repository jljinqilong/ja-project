package com.champlink.system.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.Code;
import com.champlink.common.form.system.CodeForm;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.dao.CodeDao;

@Service
public class CodeService {

    @Autowired
    private CodeDao codeDao;

    /**
     * 新增
     * 
     * @param code
     * @return
     */
    public boolean add(Code code) {
    	
    	List<Code> codeList = codeDao.getByTypeId(code.getTypeId());
    	
    	for (Code code2 : codeList) {
			if(code2.getName().equals(code.getName())) {
				AppException.create(100008); //名称已存在！
			}
		}
    	
        if (codeDao.add(code) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新
     * 
     * @param code
     * @return
     */
    public boolean update(Code code) {
    	Code dbCode = codeDao.getById(code.getRowId());
    	if(!dbCode.getName().equals(code.getName())) {
    		List<Code> codeList = codeDao.getByTypeId(code.getTypeId());
        	for (Code code2 : codeList) {
    			if(code2.getName().equals(code.getName())) {
    				AppException.create(100008); //名称已存在！
    			}
    		}
    	}
    	
        if (codeDao.update(code) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    public boolean delByIds(String ids) {
        List<Long> list = Arrays.asList(ids.split(",")).stream().map(str -> Long.parseLong(str)).collect(Collectors.toList());
        if (codeDao.delByIds(list) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 批量改状态
     * 
     * @param ids
     * @return
     */
    public boolean changeStatus(String ids, Integer status) {
        List<Long> list = Arrays.asList(ids.split(",")).stream().map(str -> Long.parseLong(str)).collect(Collectors.toList());
        if (codeDao.changeStatus(list, status) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据ID查询详细
     * 
     * @param id
     * @return
     */
    public Code getById(Long id) {
        return codeDao.getById(id);
    }

    /**
     * 根据类型查询编码列表
     * 
     * @param typeCode
     * @return
     */
    public List<Code> getByTypeCode(String typeCode) {
        return codeDao.getByTypeCode(typeCode);
    }

    /**
     * 查询所有字典表
     * 
     * @author natyu
     * @date 2018年7月25日 下午2:38:10
     * @return
     */
    public List<Code> getAllCode() {
        return codeDao.getAllCode();
    }

    /**
     * 获取列表
     * 
     * @param form
     * @return
     */
    public PageListVO<Code> pageList(CodeForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<Code> pageListVO = PageListVO.newInstance(codeDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * @Author jsl
     * @Date 14:37 2018/8/21
     * @Description    根据typeCode和name 查询
     **/
    public Code getByCodeAndName(String typeCode, String name) {
        Code code = new Code();

        code = codeDao.getByCodeAndName(typeCode,name);
        return code;
    }
}
