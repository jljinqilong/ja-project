package com.champlink.org.service.call;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.champlink.common.form.org.org.QueryCountBaseInfoByDeptId;

@FeignClient(value = "JA-STAFF", fallback = StaffServiceFallback.class)
public interface StaffService {

    @RequestMapping("/baseInfo/queryBaseInfo/{deptId}") // 查询该部门下的在职员工个数
    public String queryBaseInfo(@PathVariable("deptId") Long deptId); 

    @RequestMapping("/baseInfo/orgMerge/{sourceId}/{targetId}")
    public String orgMerge(@PathVariable("sourceId") Long sourceId, @PathVariable("targetId") Long targetId);

    @RequestMapping("/baseInfo/delDept_ids")
    public String delDept_ids(@RequestParam("dept_Ids") List<Long> dept_Ids); // 员工deptId等于ids中的员工，deptId置为null

    @RequestMapping(value = "/baseInfo/checkStaffNo/{staffNo}")
    public boolean checkStaffNo(@PathVariable("staffNo") String staffNo);
    
    @RequestMapping(value="/baseInfo/getBaseInfoByStaffNo/{staffNo}")
    public String getBaseInfoByStaffNo(@PathVariable("staffNo") String staffNo);
    
    @RequestMapping(value = "/baseInfo/queryCountBaseInfoByPositionId/{positionId}")
    public String queryCountBaseInfoByPositionId(@PathVariable("positionId") Long positionId);
    
    @RequestMapping(value = "/baseInfo/queryCountBaseInfoByDeptId")   //查询有员工的部门下的在职人数 [{"deptId": 161,"count": 4},{"deptId": 169,"count": 15}]
    public List<QueryCountBaseInfoByDeptId> queryCountBaseInfoByDeptId();
    
    @RequestMapping("/baseInfo/getBaseInfo/{userName}")
    public String getBaseInfo(@PathVariable("userName") String userName);
}
