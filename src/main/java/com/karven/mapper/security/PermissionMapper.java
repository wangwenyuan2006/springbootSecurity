package com.karven.mapper.security;

import com.karven.entity.user.Permission;

import java.util.List;

public interface PermissionMapper {
    List<Permission> findAll();

    List<Permission> findByUserId(String userId);
}
