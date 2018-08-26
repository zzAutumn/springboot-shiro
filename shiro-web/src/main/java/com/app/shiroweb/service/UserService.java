package com.app.shiroweb.service;

import com.app.shiroweb.entity.User;

import java.util.Optional;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:42
 * describe: 用户
 **/
public interface UserService {
    //查找用户by username
    User findUserByName(String username);

}
