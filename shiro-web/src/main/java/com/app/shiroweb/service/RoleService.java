package com.app.shiroweb.service;

import com.app.shiroweb.entity.Role;

import java.util.Set;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午5:08
 * describe:
 **/
public interface RoleService {

    Set<String> findRolesByUsername(String username);
}
