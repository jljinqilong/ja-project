package com.champlink.system.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.system.Resource;
import com.champlink.common.domain.system.User;
import com.champlink.system.service.ResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 获取用户菜单资源树
     * 
     * @param userId
     * @return
     */
    @RequestMapping("/tree/menuRes")
    public String getMenuResourceTree(@RequestParam("token") String token) {
        User loginUser = redisService.getLoginUserByToken(token);
        if (loginUser == null) {
            return getFailJson();
        }
        JSONArray jsonArr = resourceService.getMenuResTree(loginUser.getRowId(), true);
        return getSuccessJson(jsonArr);
    }

    /**
     * 获取用户的资源树
     * 
     * @param userId
     * @param force
     * @return
     */
    @RequestMapping("/tree/userRes")
    public String getUserResourceTree(@RequestParam("userId") Long userId, @RequestParam(value = "force", defaultValue = "false") boolean force) {
        List<Resource> userReslist = resourceService.getAllListByUid(userId);
        List<String> userResKey = userReslist.stream().filter(res -> res.getType() == Constant.RESOURCE_TYPE_IS_LEAF).map(res -> String.valueOf(res.getRowId())).collect(Collectors.toList());
        //        List<String> userResKey = userReslist.stream().map(res -> String.valueOf(res.getRowId())).collect(Collectors.toList());
        JSONArray resJA = resourceService.getUserResTree(userId, force);
        JSONObject json = new JSONObject();
        json.put("checkedKeys", userResKey);
        json.put("treeData", resJA);
        json.put("userReslist", userReslist.stream().map(res -> String.valueOf(res.getRowId())).collect(Collectors.toList()));
        return getSuccessJson(json);
    }

    /**
     * 获取角色树
     * 
     * @param roleId
     * @param force
     * @return
     */
    @RequestMapping("/tree/roleRes")
    public String getRoleResourceTree(@RequestParam("roleId") Long roleId, @RequestParam(value = "force", defaultValue = "false") boolean force) {
        List<Resource> roleResList = resourceService.getAllListByRoleId(roleId);
        List<String> roleResKey = roleResList.stream().filter(res -> res.getType() == Constant.RESOURCE_TYPE_IS_LEAF).map(res -> String.valueOf(res.getRowId())).collect(Collectors.toList());
        JSONArray resJA = resourceService.getRoleResTree(roleId, force);
        JSONObject json = new JSONObject();
        json.put("checkedKeys", roleResKey);
        json.put("treeData", resJA);
        json.put("roleResList", roleResList.stream().map(res -> String.valueOf(res.getRowId())).collect(Collectors.toList()));
        return getSuccessJson(json);
    }

    /**
     * 保存用户资源
     * 
     * @param resIds
     * @param userId
     * @return
     */
    @RequestMapping("/save/userRes")
    public String saveUserResource(@RequestParam("resIds") String resIds, @RequestParam("userId") Long userId) {
        if (resourceService.saveUserResource(resIds, userId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 保存角色资源
     * 
     * @param resIds
     * @param roleId
     * @return
     */
    @RequestMapping("/save/roleRes")
    public String saveRoleResource(@RequestParam("resIds") String resIds, @RequestParam("roleId") Long roleId) {
        resourceService.saveRoleResource(resIds, roleId);
        return getSuccessJson();
    }

}
