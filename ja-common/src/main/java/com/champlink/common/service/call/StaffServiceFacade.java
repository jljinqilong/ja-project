package com.champlink.common.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;

@FeignClient(value = "JA-STAFF", fallback = StaffServiceFacadeFallback.class)
public interface StaffServiceFacade {
    @RequestMapping("/baseInfo/get/{rowId}")
    String getUserInfo(@PathVariable("rowId") Long rowId);

    @RequestMapping("/baseInfo/getBaseInfo/{userName}")
    String getBaseInfo(@PathVariable("userName") String userName);

    /**
     * 根据rowId 只获取用户名和工号
     * @Author created by barrett in 2018/9/5 09:05
     */
    @RequestMapping("/baseInfo/getUserNameJobNumberById/{id}")
    String getUserNameById(@PathVariable("id") Long rowId);

    /**
     * 根据staffNo/staffName/deptId/identityNo查询用户信息列表
     * 
     * @author natyu
     * @date 2018年7月23日 上午9:48:47
     * @param form
     * @return
     */
    @PostMapping(value = "/queryBaseInfoForParams")
    public String queryBaseInfoForParams(@RequestBody SearchBaseInfoForm form);
}
