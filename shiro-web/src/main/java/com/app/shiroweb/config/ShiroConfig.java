package com.app.shiroweb.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午6:27
 * describe: shiro配置类
 **/
@Configuration
public class ShiroConfig {

    //注入自定义的reaml，告诉shiro如何获取用户信息来做登录或权限控制
    @Bean
    public Realm realm() {
        return new MyShiroRealm();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug,在引入spring apo的情况下
         * 在controller注解的类的方法中加入@RequireRole注解，会导致该方法无法映射请求，导致返回404
         * 加入该项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        creator.setProxyTargetClass(true);
        return creator;
    }


    /**
     * 这里做统一鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
     * 这里只做鉴权，不做权限控制，权限用注解来做
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> chain = new LinkedHashMap<>();
        /**
         * 可以匿名访问'anon',需要验证访问'authc'
         */
        chain.put("/user/login","anon");
        chain.put("/page/401","anon");
        chain.put("/page/403","anon");
        chain.put("/t5/hello","anon");
        chain.put("/t5/guest","anon");

        //除了以上的请求外，其他请求都需要登录
        chain.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(chain);
        return shiroFilterFactoryBean;
    }
}
