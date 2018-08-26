package com.app.shiroweb.service.impl;

import com.app.shiroweb.service.PermService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午5:17
 * describe:
 **/

@Service
public class PermServiceImpl implements PermService {
    @Override
    public Set<String> findPermsByUsername(String username) {
        Set<String> perms = new HashSet<>();
        //js程序员的权限
        perms.add("html:edit");
        //c++程序员的权限
        perms.add("hardware:debug");
        //java程序员的权限
        perms.add("mvn:clean");
        perms.add("mvn:install");
        perms.add("mvn:test");
        return perms;
    }
}
