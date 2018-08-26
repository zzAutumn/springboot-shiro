package com.app.shiroweb.service.impl;

import com.app.shiroweb.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午5:09
 * describe:
 **/

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Set<String> findRolesByUsername(String username) {
        Set<String> roles = new HashSet<>();
        //三种角色
        roles.add("js");
        roles.add("java");
        roles.add("cpp");

        return roles;
    }
}
