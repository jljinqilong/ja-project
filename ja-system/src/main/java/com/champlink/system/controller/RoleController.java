package com.champlink.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.champlink.common.controller.BaseController;
import com.champlink.common.domain.system.Role;
import com.champlink.common.form.system.RefRoleDataForm;
import com.champlink.common.form.system.RoleForm;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 保存用户角色
     * 
     * @param roleIds
     * @param userId
     * @return
     */
    @RequestMapping("/save/userRole")
    public String saveUserRole(@RequestParam("roleIds") String roleIds, @RequestParam("userId") Long userId) {
        if (roleService.saveUserRole(roleIds, userId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 添加角色
     * 
     * @param role
     * @return
     */
    @RequestMapping("/add")
    public String addRole(Role role) {
        if (roleService.addRole(role)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 删除角色
     * 
     * @param roleIds
     * @return
     */
    @RequestMapping("/del")
    public String delById(@RequestParam("roleId") Long roleId) {
        if (roleService.delById(roleId)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 更新角色
     * 
     * @param role
     * @return
     */
    @RequestMapping("/update")
    public String updateRole(Role role) {
        if (roleService.update(role)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    @RequestMapping(value = "/change/{id}", produces = "text/json;charset=UTF-8")
    public String del(@PathVariable("id") Long id, @RequestParam("status") int status) {
        if (roleService.changeStatus(id, status)) {
            return getSuccessJson();
        }
        return getFailJson();
    }

    /**
     * 用户角色列表
     * 
     * @param roleForm
     * @return
     */
    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String pageList(RoleForm roleForm) {
        PageListVO pageListVO = roleService.pageList(roleForm);
        return getSuccessJson(pageListVO);
    }

    /**
     * 获取全部的角色列表
     * 
     * @return
     */
    @RequestMapping(value = "/allList", produces = "text/json;charset=UTF-8")
    public String allList() {
        List<Role> list = roleService.allList();
        return getSuccessJson(list);
    }

    /**
     * 获取用户的所有角色
     * 
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userList", produces = "text/json;charset=UTF-8")
    public String userList(@RequestParam("userId") Long userId) {
        List<Role> list = roleService.getByUid(userId);
        return getSuccessJson(list);
    }

    /**
     * 查询详细
     * 
     * @param rowId
     * @return
     */
    @RequestMapping(value = "/get/{id}", produces = "text/json;charset=UTF-8")
    public String getById(@PathVariable("id") Long rowId) {
        Role role = roleService.getById(rowId);
        return getSuccessJson(role);
    }

    /**
     * 添加数据权限
     * 
     * @author natyu
     * @date 2018年9月14日 下午1:51:16
     * @param orgIds
     * @return
     */
    @PostMapping(value = "/saveRoleAuthorityData")
    public String saveRoleAuthorityData(@RequestBody RefRoleDataForm refRoleDataForm) {
        roleService.saveRoleAuthorityData(refRoleDataForm);
        return getSuccessJson();
    }

    /**
     * 查询数据权限
     * 
     * @author natyu
     * @date 2018年9月14日 下午1:51:16
     * @param orgIds
     * @return
     */
    @RequestMapping(value = "/searchRoleAuthorityData/{roleId}")
    public String searchRoleAuthorityData(@PathVariable("roleId") Long roleId) {
        List<String> orgIds = roleService.searchRoleAuthorityData(roleId);
        return getSuccessJson(orgIds);
    }
    
    
    /**
     * 新增组织架构，更新数据权限数据
     * 
     * @author natyu
     * @date 2018年9月14日 下午1:51:16
     * @param orgIds
     * @return
     */
    @RequestMapping(value = "/addOrgToUpdateData")
    public String addOrgToUpdateData(@RequestParam("orgIds") List<Long> orgIds) {
    	roleService.addOrgToUpdateData(orgIds);
        return getSuccessJson();
    }

}
