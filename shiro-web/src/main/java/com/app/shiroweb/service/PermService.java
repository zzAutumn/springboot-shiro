package com.app.shiroweb.service;

import java.util.Set;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午5:16
 * describe:
 **/
public interface PermService {

    Set<String> findPermsByUsername(String username);
}
