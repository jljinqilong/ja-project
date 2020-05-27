package com.champlink.attendance.service.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.champlink.common.form.staff.baseInfo.SearchBaseInfoForm;

@FeignClient(value = "JA-STAFF", fallback = StaffServiceFallback.class)
public interface StaffService {

    @RequestMapping(value = "/baseInfo/checkStaffNo/{staffNo}")
    public boolean checkStaffNo(@PathVariable("staffNo") String staffNo);

    @PostMapping(value = "/baseInfo/queryBaseInfoForParams", produces = "application/json;charset=UTF-8")
    public String queryBaseInfoForParams(@RequestBody SearchBaseInfoForm form);

    @RequestMapping(value = "/baseInfo/get/{id}")
    public String getStfBaseInfo(@PathVariable("id") Long id);

    @RequestMapping("/baseInfo/getBaseInfo/{userName}")
    public String getBaseInfo(@PathVariable("userName") String userName);

    @RequestMapping(value = "/baseInfo/getBaseInfoByStaffNo/{staffNo}")
    public String getBaseInfoByStaffNo(@PathVariable("staffNo") String staffNo);
}
