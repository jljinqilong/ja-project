package com.champlink.system.service.call;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "JA-STAFF", fallback = StaffServiceFallback.class)
public interface StaffService {

    @RequestMapping(value = "/baseInfo/checkStaffNo/{staffNo}")
    public boolean checkStaffNo(@PathVariable("staffNo") String staffNo);

    /**
     * 根据工号获取员工信息
     * 
     * @author natyu
     * @date 2018年9月13日 上午11:47:36
     * @param staffNo
     * @return
     */
    @RequestMapping(value = "/baseInfo/getBaseInfoByStaffNo/{staffNo}")
    public String getBaseInfoByStaffNo(@PathVariable("staffNo") String staffNo);

    /**
     * 根据组织架构ids 获取员工id
     * 
     * @author natyu
     * @date 2018年9月17日 下午4:01:35
     * @param orgIds
     * @return
     */
    @RequestMapping("/baseInfo/getStaffIdByOrgIds")
    public String getStaffIdByOrgIds(@RequestParam("list") List<Long> list);

    /**
     * 根据组织id查询该组织下所有的部门集合
     * @param baseId
     */
    @RequestMapping("/baseInfo/getStaffNoBystaffIds")
    public String getStaffNoBystaffIds(@RequestParam("token") String token);
}
