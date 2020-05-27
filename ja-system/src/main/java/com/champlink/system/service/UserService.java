package com.champlink.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.champlink.common.domain.Paginater;
import com.champlink.common.domain.system.RefUserRole;
import com.champlink.common.domain.system.User;
import com.champlink.common.form.system.UserForm;
import com.champlink.common.util.encrypt.MD5Util;
import com.champlink.common.util.exception.AppException;
import com.champlink.common.vo.PageListVO;
import com.champlink.system.dao.RefRoleResourceDao;
import com.champlink.system.dao.RefUserResourceDao;
import com.champlink.system.dao.RefUserRoleDao;
import com.champlink.system.dao.ResourceDao;
import com.champlink.system.dao.RoleDao;
import com.champlink.system.dao.UserDao;

@Service
public class UserService {

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

    /**
     * 添加新用户
     * 
     * @param user
     * @return
     */
    @Transactional
    public boolean add(User user) {
        String name = user.getUserName();
        String cellphoneNo = user.getCellphoneNo();
        String email = user.getEmail();

        if (checkName(name, null) > 0) {
            AppException.create(200001);
        }
        if (checkCellphoneNo(cellphoneNo, null) > 0) {
            AppException.create(200002);
        }
        if (StringUtils.isNotEmpty(email) && checkEmail(email, null) > 0) {
            AppException.create(200003);
        }
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword());
        }
        if (userDao.add(user) > 0) {
            final RefUserRole refUserRole = new RefUserRole(String.valueOf(1), user.getRowId());
            refUserRoleDao.addBatch(new ArrayList<RefUserRole>() {
                {
                    add(refUserRole);
                }
            });
            return true;
        }
        return false;
    }

    /**
     * 批量添加用户
     * 
     * @param list
     * @return
     */
    @Transactional
    public boolean batchAdd(List<String> list) {
        List<User> userList = list.stream().map(str -> new User(str)).collect(Collectors.toList());
        if (userDao.batchAdd(userList) > 0) {
            List<Long> userIds = userList.stream().filter(item -> item.getRowId() != null).map(item -> item.getRowId()).collect(Collectors.toList());
            if (userIds.size() > 0) {
                if (refUserRoleDao.addBatch(userIds.stream().map(id -> new RefUserRole(String.valueOf(1), id)).collect(Collectors.toList())) > 0) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 删除用户
     * 
     * @param userId
     * @return
     */
    public boolean delByIds(String userIds) {
        List<Long> list = Arrays.asList(userIds.split(";")).stream().map(str -> Long.parseLong(str)).collect(Collectors.toList());
        if (userDao.delByIds(list) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除用户
     * 
     * @param userId
     * @return
     */
    public boolean delByUserName(String userName) {
        if (userDao.delByUserName(userName) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更改状态
     * 
     * @param userId
     * @param status
     * @return
     */
    public boolean changeStatus(Long userId, int status) {
        User user = new User();
        user.setRowId(userId);
        user.setStatus(status);
        if (userDao.update(user) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更改状态
     * 
     * @param userId
     * @param status
     * @return
     */
    public boolean changeByUserName(String userName, int status) {
        User user = new User();
        user.setUserName(userName);
        user.setStatus(status);
        if (userDao.changeByUserName(user) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更改状态
     * 
     * @param userId
     * @param status
     * @return
     */
    public boolean changeByUserNameList(List<String> userNameList, int status) {
        if (userDao.changeByUserNameList(userNameList, status) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新用户
     * 
     * @param user
     * @return
     */
    public boolean update(User user) {
        Long rowId = user.getRowId();
        String name = user.getUserName();
        String cellphoneNo = user.getCellphoneNo();
        String email = user.getEmail();

        if (checkName(name, rowId) > 0) {
            AppException.create(200001);
        }
        if (checkCellphoneNo(cellphoneNo, rowId) > 0) {
            AppException.create(200002);
        }
        if (StringUtils.isNotEmpty(email) && checkEmail(email, rowId) > 0) {
            AppException.create(200003);
        }

        if (user.getPassword() != null) {
            user.setPassword(user.getPassword());
        }
        if (userDao.update(user) > 0) {
            return true;
        }
        return false;
    }

    public User getByName(String userName) {
        return userDao.getByName(userName);
    }

    /**
     * 根据ID查询用户
     * 
     * @param id
     * @return
     */
    public User getById(Long id) {
        return userDao.getById(id);
    }

    /**
     * 获取列表
     * 
     * @param form
     * @return
     */
    public PageListVO<User> pageList(UserForm form) {
        Paginater paginater = Paginater.newInstance(form);
        PageListVO<User> pageListVO = PageListVO.newInstance(userDao.pageList(paginater));
        return pageListVO;
    }

    /**
     * 校验用户名是否重复
     * 
     * @author natyu
     * @date 2018年8月14日 下午2:29:58
     * @param name
     * @param rowId
     * @return
     */
    public int checkName(String name, Long rowId) {
        return userDao.checkName(name, rowId);
    }

    /**
     * 校验手机号是否重复
     * 
     * @author natyu
     * @date 2018年8月14日 下午2:30:15
     * @param cellphoneNo
     * @param rowId
     * @return
     */
    public int checkCellphoneNo(String cellphoneNo, Long rowId) {
        return userDao.checkCellphoneNo(cellphoneNo, rowId);
    }

    /**
     * 校验邮箱是否重复
     * 
     * @author natyu
     * @date 2018年8月14日 下午2:30:30
     * @param email
     * @param rowId
     * @return
     */
    public int checkEmail(String email, Long rowId) {
        return userDao.checkEmail(email, rowId);
    }

    /**
     * 修改密码
     * 
     * @author natyu
     * @date 2018年9月19日 下午5:35:07
     * @param userForm
     */
    public void changePassword(UserForm userForm) {
        User user = userDao.getByName(userForm.getUserName());
        if (user == null) {
            AppException.create(200007);
        }
        String oldPassword = userForm.getOldPassword();
        if (!user.getPassword().equals(MD5Util.getMD5Code(oldPassword))) {
            AppException.create(200008);
        }
        String newPassword = userForm.getNewPassword();
        if (StringUtils.isNotEmpty(newPassword) && newPassword.length() < 6) {
            AppException.create(200009);
        }
        user.setPassword(MD5Util.getMD5Code(newPassword));
        userDao.update(user);
    }

    /**
     * 根据原用户名修改为新用户名
     * 
     * @author natyu
     * @date 2018年9月26日 上午11:59:10
     * @param oldUserName
     * @param newUserName
     */
    public void updateUserName(String oldUserName, String newUserName) {
        if (StringUtils.isEmpty(oldUserName)) {
            AppException.create(200011, "oldUserName");
        }
        if (StringUtils.isEmpty(newUserName)) {
            AppException.create(200011, "newUserName");
        }
        User user = userDao.getByName(oldUserName);
        if (user == null) {
            AppException.create(200010, oldUserName);
        }
        user.setUserName(newUserName);
        userDao.update(user);
    }
}
