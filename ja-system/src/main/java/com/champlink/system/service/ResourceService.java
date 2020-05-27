package com.champlink.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.system.RefRoleResource;
import com.champlink.common.domain.system.RefUserResource;
import com.champlink.common.domain.system.Resource;
import com.champlink.common.domain.system.Role;
import com.champlink.common.util.cache.RedisService;
import com.champlink.system.dao.RefRoleResourceDao;
import com.champlink.system.dao.RefUserResourceDao;
import com.champlink.system.dao.RefUserRoleDao;
import com.champlink.system.dao.ResourceDao;
import com.champlink.system.dao.RoleDao;
import com.champlink.system.dao.UserDao;

@Service
public class ResourceService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RefUserRoleDao refUserRoleDao;

    @Autowired
    private RefUserResourceDao refUserResourceDao;

    @Autowired
    private RefRoleResourceDao refRoleResourceDao;

    @Autowired
    private RedisService redisService;

    /**
     * 保存用户资源
     * 
     * @param resIds
     * @param userId
     * @return
     */
    @Transactional
    public boolean saveUserResource(String resIds, Long userId) {
        refUserResourceDao.delByUid(userId);
        List<Resource> roleReslist = getRoleResListByUid(userId);
        String ids = String.join(",", roleReslist.stream().filter(res -> res.getType() == Constant.RESOURCE_TYPE_IS_LEAF).map(res -> String.valueOf(res.getRowId())).collect(Collectors.toList()))
            + ",";
        if (!"".equals(resIds)) {
            List<RefUserResource> list = Arrays.asList(resIds.split(",")).stream().filter(str -> !(ids.contains(str + ","))).map(str -> new RefUserResource(str, userId)).collect(Collectors.toList());
            if (list.size() > 0) {
                if (refUserResourceDao.addBatch(list) > 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 保存角色资源
     * 
     * @param resIds
     * @param roleId
     * @return
     */
    @Transactional
    public void saveRoleResource(String resIds, Long roleId) {
        refRoleResourceDao.delByRoleId(roleId);
        if (StringUtils.isNotEmpty(resIds)) {
            List<RefRoleResource> list = Arrays.asList(resIds.split(",")).stream().map(str -> new RefRoleResource(str, roleId)).collect(Collectors.toList());
            refRoleResourceDao.addBatch(list);
        }
    }

    /**
     * 获取全部资源
     * 
     * @return
     */
    public List<Resource> getAllList() {
        return resourceDao.allList();
    }

    /**
     * 获取用户的全部资源列表 (包括个人资源和角色资源) (去除重复)
     * 
     * @param userId
     * @return
     */
    public List<Resource> getAllListByUid(Long userId) {
        List<Resource> userResourceList = resourceDao.getListByUserId(userId);
        if (userResourceList == null) {
            userResourceList = new ArrayList<Resource>();
        }
        List<Role> userRoleList = roleDao.getByUid(userId);
        if (userRoleList != null && userRoleList.size() > 0) {
            List<Resource> roleResourceList = resourceDao.getListByRoleIds(userRoleList.stream().map(role -> role.getRowId()).collect(Collectors.toList()));
            userResourceList.addAll(roleResourceList);
        }
        userResourceList = userResourceList.stream()
            .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<Resource>(Comparator.comparingLong(Resource::getRowId))), ArrayList::new));
        userResourceList.sort((res1, res2) -> res1.getOrderNum() - res2.getOrderNum());
        redisService.writeValue(Constant.CACHE_NAME_USER_RESOURCE_LIST + userId, userResourceList);
        return userResourceList;
    }

    /**
     * 获取用户角色资源 (去除重复)
     * 
     * @param userId
     * @return
     */
    public List<Resource> getRoleResListByUid(Long userId) {
        List<Resource> roleResourceList = new ArrayList<>();
        List<Role> userRoleList = roleDao.getByUid(userId);
        if (userRoleList != null && userRoleList.size() > 0) {
            roleResourceList = resourceDao.getListByRoleIds(userRoleList.stream().map(role -> role.getRowId()).collect(Collectors.toList()));
        }
        roleResourceList = roleResourceList.stream()
            .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<Resource>(Comparator.comparingLong(Resource::getRowId))), ArrayList::new));
        return roleResourceList;
    }

    /**
     * 获取角色的资源列表 (去除重复)
     * 
     * @param roleId
     * @return
     */
    public List<Resource> getAllListByRoleId(Long roleId) {
        List<Resource> resList = resourceDao.getListByRoleIds(Arrays.asList(new Long[] {roleId}));
        resList = resList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<Resource>(Comparator.comparingLong(Resource::getRowId))), ArrayList::new));
        return resList;
    }

    /**
     * 获取全部的资源树
     * 
     * @param force
     * @return
     */
    public JSONArray getAllResTree(boolean force) {
        if (!force) {
            JSONArray jsonArr = redisService.readValue(Constant.CACHE_NAME_ALL_RESOURCE_TREE, JSONArray.class);
            if (jsonArr != null && jsonArr.size() > 0) {
                return jsonArr;
            }
        }
        List<Resource> list = getAllList();
        JSONArray jsonArr = getResourceTreeData(list, null, 0L);
        redisService.writeValue(Constant.CACHE_NAME_ALL_RESOURCE_TREE, jsonArr);
        return jsonArr;
    }

    /**
     * 菜单资源树
     * 
     * @param userId
     * @param force
     * @return
     */
    public JSONArray getMenuResTree(Long userId, boolean force) {
        if (!force) {
            JSONArray jsonArr = redisService.readValue(Constant.CACHE_NAME_MENU_RESOURCE_TREE, JSONArray.class);
            if (jsonArr != null && jsonArr.size() > 0) {
                return jsonArr;
            }
        }
        List<Resource> list = getAllListByUid(userId);

        if (list != null && list.size() > 0) {
            list = list.stream().filter(res -> res.getType() == Constant.RESOURCE_TYPE_NOT_LEAF_MENU || res.getType() == Constant.RESOURCE_TYPE_IS_LEAF_MENU).collect(Collectors.toList());
        }
        JSONArray jsonArr = getResourceMenuData(list, 0L);
        redisService.writeValue(Constant.CACHE_NAME_MENU_RESOURCE_TREE, jsonArr);
        return jsonArr;
    }

    /**
     * 返回用户可编辑的资源树
     * 
     * @param userId
     * @param force
     * @return
     */
    public JSONArray getUserResTree(Long userId, boolean force) {
        if (!force) {
            JSONArray jsonArr = redisService.readValue(Constant.CACHE_NAME_USER_RESOURCE_TREE + userId, JSONArray.class);
            if (jsonArr != null && jsonArr.size() > 0) {
                return jsonArr;
            }
        }
        List<Resource> list = getAllList();
        List<Resource> disabledList = getRoleResListByUid(userId);
        JSONArray jsonArr = getResourceTreeData(list, disabledList, 0L);
        redisService.writeValue(Constant.CACHE_NAME_USER_RESOURCE_TREE + userId, jsonArr);
        return jsonArr;
    }

    /**
     * 返回角色可编辑的资源树
     * 
     * @param force
     * @return
     */
    public JSONArray getRoleResTree(Long roleId, boolean force) {
        if (!force) {
            JSONArray jsonArr = redisService.readValue(Constant.CACHE_NAME_ROLE_RESOURCE_TREE + roleId, JSONArray.class);
            if (jsonArr != null && jsonArr.size() > 0) {
                return jsonArr;
            }
        }
        List<Resource> list = getAllList();
        JSONArray jsonArr = getResourceTreeData(list, null, 0L);
        redisService.writeValue(Constant.CACHE_NAME_ROLE_RESOURCE_TREE + roleId, jsonArr);
        return jsonArr;
    }

    /**
     * 生产树形结构 (包含禁用禁用)
     * 
     * @param list 全部资源
     * @param disabledList 禁用的资源
     * @param id
     * @return
     */
    private JSONArray getResourceTreeData(List<Resource> list, List<Resource> disabledList, Long id) {
        JSONArray jsonArr = new JSONArray();
        for (Resource resource : list) {
            if (resource.getPid().longValue() == id.longValue()) {
                JSONObject json = new JSONObject();
                json.put("title", resource.getNameZh());
                json.put("key", resource.getRowId());
                if (disabledList != null) {
                    for (Resource res : disabledList) {
                        if (res.getRowId().longValue() == resource.getRowId().longValue()) {
                            json.put("disableCheckbox", true);
                            break;
                        }
                    }
                }
                JSONArray children = getResourceTreeData(list, disabledList, resource.getRowId());
                if (children.size() > 0) {
                    json.put("children", children);
                }
                jsonArr.add(json);
            }
        }
        return jsonArr;
    }

    /**
     * 获取资源菜单数据
     * 
     * @param list
     * @param id
     * @return
     */
    private JSONArray getResourceMenuData(List<Resource> list, Long id) {
        JSONArray jsonArr = new JSONArray();
        for (Resource resource : list) {
            if (resource.getPid().longValue() == id.longValue()) {
                JSONObject json = new JSONObject();
                json.put("name", resource.getNameZh());
                //                json.put("path", resource.getKey());
                json.put("path", resource.getAccessUrl());
                if (resource.getIcon() != null && !"".equals(resource.getIcon())) {
                    json.put("icon", resource.getIcon());
                }
                JSONArray children = getResourceMenuData(list, resource.getRowId());
                if (children.size() > 0) {
                    json.put("children", children);
                }
                jsonArr.add(json);
            }
        }
        return jsonArr;
    }

}
