package com.app.shiroweb.contorller;

import com.app.shiroweb.entity.User;
import com.app.shiroweb.service.UserService;
import com.app.shiroweb.vo.ResultVO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:51
 * describe:
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResultVO login(@RequestParam String username, @RequestParam String password){
        System.out.println("RequestParam " + username + " :" + password);

        if (StringUtils.isEmpty(username)) {
            return ResultVO.error("403","用户名不能为空",null);
        }
        if (StringUtils.isEmpty(password)) {
            return ResultVO.error("403","密码不能为空",null);
        }

        //获得主体
        Subject currentUser = SecurityUtils.getSubject();

        try {
            //登录
            currentUser.login(new UsernamePasswordToken(username, password));
            //从session中取出用户信息
            User user = (User) currentUser.getPrincipal();
            if (user == null) throw new AuthenticationException();
            return ResultVO.success("200","success",user);
        }catch (UnknownAccountException uae) {
            return ResultVO.error("403",uae.getMessage(),null);
        } catch (IncorrectCredentialsException ice) {
            return ResultVO.error("403",ice.getMessage(),null);
        } catch (LockedAccountException lae) {
            return ResultVO.error("403",lae.getMessage(),null);
        } catch (AuthenticationException ae) {
            return ResultVO.error("403",ae.getMessage(),null);
        }
    }



}
