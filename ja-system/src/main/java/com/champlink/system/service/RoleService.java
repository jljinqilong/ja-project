package com.champlink.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.RefUserRole;
import com.champlink.common.domain.system.Role;
import com.champlink.common.form.system.RefRoleDataForm;
import com.champlink.common.form.system.RoleForm;
import com.champlink.common.util.cache.RedisService;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.common.web.ctx.RequestContext;
import com.champlink.system.dao.RefRoleDataDao;
import com.champlink.system.dao.RefRoleResourceDao;
import com.champlink.system.dao.RefUserResourceDao;
import com.champlink.system.dao.RefUserRoleDao;
import com.champlink.system.dao.ResourceDao;
import com.champlink.system.dao.RoleDao;
import com.champlink.system.dao.UserDao;
import com.champlink.system.service.call.StaffService;

@Service
public class RoleService {

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
    private RefRoleDataDao refRoleDataDao;

    @Autowired
    private StaffService staffService;

    @Autowired
    private RedisService redisService;

    public PageListVO pageList(RoleForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO pageListVO = PageListVO.newInstance(roleDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * 查询全部的角色
     * 
     * @return
     */
    public List<Role> allList() {
        return roleDao.allList();
    }

    /**
     * 获取用户的角色
     * 
     * @param userId
     * @return
     */
    public List<Role> getByUid(Long userId) {
        return roleDao.getByUid(userId);
    }

    /**
     * 保存用户角色
     * 
     * @param roleIds
     * @param userId
     * @return
     */
    @Transactional
    public boolean saveUserRole(String roleIds, Long userId) {
        refUserRoleDao.delByUid(userId);
        if (!"".equals(roleIds)) {
            List<RefUserRole> list = Arrays.asList(roleIds.split(",")).stream().map(str -> new RefUserRole(str, userId)).collect(Collectors.toList());
            if (refUserRoleDao.addBatch(list) > 0) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    /**
     * 保存角色
     * 
     * @param role
     * @return
     */
    public boolean addRole(Role role) {
        if (roleDao.getRoleByName(role.getName(), null) > 0) {
            AppException.create(200000);
        }
        if (roleDao.add(role) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除角色
     * 
     * @param roleIds
     * @return
     */
    public boolean delById(Long roleId) {

        //查询该角色是否被使用
        if (roleDao.checkRoleIsUse(roleId) > 0) {
            AppException.create(200005);
        }

        if (roleDao.delById(roleId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新角色
     * 
     * @param role
     * @return
     */
    public boolean update(Role role) {
        if (roleDao.getRoleByName(role.getName(), role.getRowId()) > 0) {
            AppException.create(200000);
        }
        if (roleDao.update(role) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更改状态
     * 
     * @param roleId
     * @param status
     * @return
     */
    public boolean changeStatus(Long roleId, int status) {
        Role role = new Role();
        role.setRowId(roleId);
        role.setStatus(status);
        if (roleDao.update(role) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取详细
     * 
     * @param roleId
     * @return
     */
    public Role getById(Long roleId) {
        return roleDao.getById(roleId);
    }

    /**
     * 添加数据权限
     * 
     * @author natyu
     * @date 2018年9月14日 下午3:30:42
     * @param orgIds
     * @param roleId
     */
    public void saveRoleAuthorityData(RefRoleDataForm refRoleDataForm) {
        List<Long> orgIds = refRoleDataForm.getOrgIds();
        List<Long> halfOrgIds = refRoleDataForm.getHalfOrgIds();
        Long roleId = refRoleDataForm.getRoleId();
        refRoleDataDao.delByRoleId(roleId);
        if (orgIds != null && orgIds.size() > 0) {
            refRoleDataDao.addBatch(orgIds, roleId, 1);  // 全选中
        }
        if(halfOrgIds != null && halfOrgIds.size() > 0) {
        	refRoleDataDao.addBatch(halfOrgIds, roleId, 0);  // 半选中
        }
    }

    /**
     * 查询数据权限
     * 
     * @author natyu
     * @date 2018年9月14日 下午3:30:55
     * @param roleId
     * @return
     */
    public List<String> searchRoleAuthorityData(Long roleId) {
        return refRoleDataDao.queryRoleDataForId(roleId);
    }

    /**
     * 根据用户id查询数据权限
     * 
     * @author natyu
     * @date 2018年9月17日 下午3:31:18
     * @param userId
     * @return
     */
    public List<Long> searchRoleDataByUserId(Long userId, String token) {
        List<Long> list = refRoleDataDao.searchRoleDataByUserId(userId);

        if(list != null && list.size() == 0) {
        	list.add(-1L);
        }
        
        List<Long> staffIds = new ArrayList<Long>();
        if(list != null && list.get(0) != null) {
        	String jsonStr = staffService.getStaffIdByOrgIds(list);
        	if (jsonStr != null) {
        		JSONObject parseObject = JSONObject.parseObject(jsonStr);
        		if ((Integer) parseObject.get("code") == 200) {
        			staffIds = JSONObject.parseArray(parseObject.getString("data"), Long.class);
        		}
        	}
        }else {
        	staffIds.add(-1L);
         }
        
        redisService.writeValue(Constant.dataAuthDept + token, list, Constant.sessionTimeout * 60);
        
        return staffIds;
    }

	public int addOrgToUpdateData(List<Long> orgIds) {
		return refRoleDataDao.addOrgToUpdateData(orgIds);
	}

}
