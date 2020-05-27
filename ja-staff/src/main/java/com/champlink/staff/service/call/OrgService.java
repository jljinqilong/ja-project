package com.champlink.staff.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "JA-ORG", fallback = OrgServiceFallback.class)
public interface OrgService {
    /**
     * 获取所有组织机构
     * 
     * @date 2018年7月25日 下午3:39:42
     * @return
     */
    @RequestMapping(value = "/org/getAllOrg")
    public String getAllOrg();

    /**
     * 获取所有基地
     * 
     * @author natyu
     * @date 2018年7月28日 下午12:40:24
     * @return
     */
    @RequestMapping(value = "/org/baseList")
    public String baseList();

    /**
     * 获取所有部门
     * 
     * @author natyu
     * @date 2018年7月28日 下午12:40:50
     * @return
     */
    @RequestMapping(value = "/org/deptList")
    public String deptList();

    /**
     * 查询单个
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/position/get/{id}")
    public String findOnePositon(@PathVariable("id") Long id);

    @RequestMapping(value = "/grade/get/{id}")
    public String findOneGrade(@PathVariable("id") Long id);

    @RequestMapping(value = "/rank/get/{id}")
    public String findOneRank(@PathVariable("id") Long id);

    /**
     * 查询所有的职衔
     * @return
     */
    @RequestMapping(value = "/position/getAllPosition")
    public String getAllPosition();

    /**
     * 查询所有的职等/赋值名称
     * @return
     */
    @RequestMapping(value = "/grade/getAllGrade")
    public String getAllGrade();

    /**
     * 查询所有的职级
     * @return
     */
    @RequestMapping(value = "/rank/getAllRank")
    public String getAllRank();

    /**
     * 根据组织id查询该组织下所有的部门集合
     * 
     * @author natyu
     * @date 2018年8月7日 下午7:08:09
     * @param baseId
     * @return
     */
    @RequestMapping(value = "/org/getAllSonOrg/{baseId}")
    public String getAllSonOrg(@PathVariable("baseId") Long baseId);

    @RequestMapping(value = "/org/get/{id}")
    public String get(@PathVariable("id") Long id);

    /**
     * 查询单个职衔下对应的所有的职等/赋值名称
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/position/getAllGradeByPosition/{positionId}")
    public String getAllGradeByPosition(@PathVariable("positionId") Long positionId);

    /**
     * 查询单个职等/赋值名称下对应的所有的职级
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/position/getAllRankByPositionAndGrade/{positionId}/{gradeId}")
    public String getAllRankByPositionAndGrade(@PathVariable("positionId") Long positionId, @PathVariable("gradeId") Long gradeId);

}
