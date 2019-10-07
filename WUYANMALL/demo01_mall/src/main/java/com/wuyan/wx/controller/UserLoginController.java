package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
<<<<<<< HEAD
import com.wuyan.wx.bean.*;
import com.wuyan.wx.service.personService.PersonService;
import org.apache.shiro.SecurityUtils;
=======
import com.wuyan.wx.bean.UserLoginInfo;
import com.wuyan.wx.bean.UserToken;
import com.wuyan.wx.service.personService.PersonService;
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
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
        UserLoginInfo userLoginInfo = personService.queryUser(userToken);
<<<<<<< HEAD
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
=======

        //shiro整合时修改
        //Serializable id = SecurityUtils.getSubject().getSession().getId();

        userLoginInfo.setToken("94675545-4cfb-496a-b1b8-702838c26f8a");

        BaseRespVo ok = BaseRespVo.ok(userLoginInfo);
        return ok;
    }

>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
}
