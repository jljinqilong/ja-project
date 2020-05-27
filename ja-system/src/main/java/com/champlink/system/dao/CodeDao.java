package com.champlink.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.Code;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.PostMapping;

@Mapper
public interface CodeDao {

    /**
     * 添加
     * 
     * @param code
     * @return
     */
    int add(Code code);

    /**
     * 删除
     * 
     * @param list
     * @return
     */
    int delByIds(List<Long> list);

    /**
     * 更改状态
     * 
     * @param list
     * @return
     */
    int changeStatus(@Param("list") List<Long> list, @Param("status") Integer status);

    /**
     * 修改
     * 
     * @param code
     * @return
     */
    int update(Code code);

    /**
     * 根据ID查询详细
     * 
     * @param id
     * @return
     */
    Code getById(Long id);

    /**
     * 根据类型code查询列表
     * 
     * @param typeCode
     * @return
     */
    List<Code> getByTypeCode(String typeCode);

    /**
     * 返回所有字典项
     * 
     * @author natyu
     * @date 2018年7月25日 下午2:40:39
     * @return
     */
    List<Code> getAllCode();

    /**
     * 分页列表
     * 
     * @param paginater
     * @return
     */
    Page<Code> pageList(Paginater paginater);

    /**
     * 
     * @param typeId
     * @return
     */
    List<Code> findByTypeId(String typeId);
    
	/**
	 * 根据typeId查询返回codeList
	 * @return
	 */
    List<Code> getByTypeId(Long typeId);

    /**
     * @Author jsl
     * @Date 14:39 2018/8/21
     * @Description 根据typeCode 和 name 查询
     **/
    Code getByCodeAndName(@Param("typeCode") String typeCode, @Param("name") String name);
}
