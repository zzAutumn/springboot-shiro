package com.app.shiroweb.service.impl;

import com.app.shiroweb.entity.User;
import com.app.shiroweb.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:44
 * describe:
 **/
@Service
public class UserServiceImpl implements UserService {

    /**
     * 模拟查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        String pwd = "123";
        Md5Hash md5Hash = new Md5Hash(pwd, username);

        User user = new User();
        user.setUid(new Random().nextLong());  //随机分配一个id
        user.setUsername(username);
        user.setNickname(username);
        user.setPassword(md5Hash.toString());
        user.setSalt(username);
        user.setCreateTime(new Date());
        return user;
    }
}
