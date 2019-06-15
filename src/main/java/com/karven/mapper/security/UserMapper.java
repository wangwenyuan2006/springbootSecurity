package com.karven.mapper.security;

import com.karven.entity.user.Permission;
import com.karven.entity.user.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询当前用户对象
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 查询当前用户拥有的权限
     * @param username
     * @return
     */
    List<Permission> findPermissionByUsername(String username);

    /**
     * 修改密码
     * @param user
     */
    void updatePassword(User user);
}
