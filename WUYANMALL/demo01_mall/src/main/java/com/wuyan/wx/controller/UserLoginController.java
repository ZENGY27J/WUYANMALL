package com.wuyan.wx.controller;

import com.wuyan.mall.bean.User;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.*;
import com.wuyan.wx.config.SmsConfig;
import com.wuyan.wx.service.personService.PersonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Map;

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

    @RequestMapping("auth/register")
    public BaseRespVo register(@RequestBody Map map,HttpServletRequest httpServletRequest){
        User user = new User();
        user.setUsername((String) map.get("username"));
        user.setPassword((String) map.get("password"));
        user.setMobile((String) map.get("mobile"));
        Session session = SecurityUtils.getSubject().getSession();
        String codeFromSession = (String) session.getAttribute("code");
        String code = (String) map.get("code");
        if (!code.equals(codeFromSession)){
            return BaseRespVo.codeError();
        }
        System.out.println(session.getId());
        boolean flag = personService.registerUser(user,httpServletRequest);
        return BaseRespVo.ok(null);
    }
    @RequestMapping("auth/regCaptcha")
    public BaseRespVo regCaptcha(@RequestBody Map map){
        String code = (int)((Math.random() * 9 + 1) * 10000)+"";
        String mobile = (String) map.get("mobile");
        SmsConfig.sendMassage(mobile,code);
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("code",code);
        Serializable id = session.getId();
        System.out.println(id);
        return BaseRespVo.ok(id);
    }
}
