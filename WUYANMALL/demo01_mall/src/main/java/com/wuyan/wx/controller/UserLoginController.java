package com.wuyan.wx.controller;

import com.wuyan.mall.shiro.CustomToken;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.*;
import com.wuyan.wx.service.personService.PersonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户登录个人中心
 * @Date: 2019-10-05-15:45
 */
@RestController
@RequestMapping("wx")
public class UserLoginController {

    @Autowired
    PersonService personService;

    @RequestMapping("/auth/login")
    public BaseRespVo login(@RequestBody UserToken userToken) {
        //判断userToken是否为空
        String username = userToken.getUsername();
        String password = userToken.getPassword();
        if(username == null || "".equals(username) || password == null || "".equals(userToken)) {
            BaseRespVo.error(null);
        }
        CustomToken token = new CustomToken(userToken.getUsername(),userToken.getPassword(),"wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);

        } catch (AuthenticationException e) {
            return BaseRespVo.fail();
        }
        UserLoginInfo userLoginInfo = personService.queryUser(userToken);
        BaseRespVo ok = BaseRespVo.ok(userLoginInfo);
        return ok;
    }

    @RequestMapping("/auth/logout")
    public BaseRespVo logout() {
        return BaseRespVo.ok(null);
    }


    @RequestMapping("/auth/bindPhone")
    public BaseRespVo bindPhone(@RequestBody BindPhone bindPhone) {
        return BaseRespVo.systemError();
    }
}
