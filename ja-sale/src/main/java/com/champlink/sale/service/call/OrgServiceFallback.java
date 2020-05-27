package com.champlink.sale.service.call;

import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 出现异常提示消息
 * @Author created by barrett in 18-8-7 上午10:18
 * @Param
 * @return
 */
@Component
public class OrgServiceFallback implements OrgService {

    @Override
    public String getOrgById(Long deptId) {
        // TODO Auto-generated method stub
        return null;
    }

}
