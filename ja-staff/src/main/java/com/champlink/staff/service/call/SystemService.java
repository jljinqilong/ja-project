package com.champlink.staff.service.call;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.champlink.common.domain.system.ModuleLog;

@FeignClient(value = "JA-SYSTEM", fallback = SystemServiceFallback.class)
public interface SystemService {

    /**
     * 查询所有字典项
     * 
     * @author natyu
     * @date 2018年7月25日 下午2:45:34
     * @return
     */
    @RequestMapping(value = "/code/getAllCode")
    public String getAllCode();

    /**
     * 查询字典列表
     * 
     * @param typeCode
     * @return
     */
    @RequestMapping(value = "/code/getByTpye")
    public String getByTpye(@RequestParam("typeCode") String typeCode);

    /**
     * 变更记录
     * 
     * @author natyu
     * @date 2018年7月25日 上午11:59:20
     * @param moduleLog
     * @return
     */
    @RequestMapping(value = "/moduleLog/add")
    public String add(@RequestBody ModuleLog moduleLog);

    /**
     * 生成编码
     * 
     * @param code
     * @return
     */
    @RequestMapping(value = "/code/generate/{code}/{num}")
    public String generate(@PathVariable("code") String code, @PathVariable("num") String num);

    /**
     * 批量生成员工
     * 
     * @author natyu
     * @date 2018年7月27日 下午4:32:03
     * @param list
     * @return
     */
    @RequestMapping(value = "/user/batchadd")
    public String batchAdd(@RequestParam("list") List<String> list);

    /**
     * 根据用户名删除用户
     * 
     * @author natyu
     * @date 2018年8月15日 上午10:04:01
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/user/delByUserName/{userName}")
    public String delByUserName(@PathVariable("userName") String userName);

    /**
     * 根据用户名更改用户状态
     * 
     * @author natyu
     * @date 2018年8月15日 上午10:02:25
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/user/changeByUserName")
    public String changeByUserName(@RequestParam("userName") String userName, @RequestParam("status") int status);

    /**
     * 根据userNameList更改用户状态
     * 
     * @author natyu
     * @date 2018年8月15日 上午10:02:25
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/user/changeByUserNameList")
    public String changeByUserNameList(@RequestParam("userNameList") List<String> userNameList, @RequestParam("status") int status);

    /**
     * 
     * 根据原用户名修改为新用户名
     * 
     * @author jinlin.tang
     * @date 2018年9月26日 下午7:14:40
     * @param oldUserName
     * @param newUserName
     * @return
     */
    @RequestMapping(value = "/user/updateUserName")
    public String updateUserName(@RequestParam("oldUserName") String oldUserName, @RequestParam("newUserName") String newUserName);

    @RequestMapping(value = "/codetype/allList", produces = "text/json;charset=UTF-8")
	public String allList();
}
