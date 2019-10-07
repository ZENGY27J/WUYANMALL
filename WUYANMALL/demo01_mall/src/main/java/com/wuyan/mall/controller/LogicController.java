package com.wuyan.mall.controller;

import com.wuyan.mall.bean.permission.AdminPermInfo;
import com.wuyan.mall.service.AdminpremInfo.AdminpremInfoService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.LoginVo;
import com.wuyan.mall.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 控制层
 * @Date: 2019-09-30-11:53
 */
@RestController
public class LogicController {

    @Autowired
    AdminpremInfoService adminpremInfoService;

    @RequestMapping("admin/auth/login")
    public BaseRespVo logic(@RequestBody LoginVo loginVo) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(),loginVo.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);

        } catch (AuthenticationException e) {
            return BaseRespVo.fail();
        }
        Serializable id = subject.getSession().getId();
        BaseRespVo ok = BaseRespVo.ok(id);
        return ok;
    }
    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token) {
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
//        AdminPermInfo adminPermInfo = adminpremInfoService.getPermInfo(principal);
//        adminPermInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName("admin123");
        ArrayList perms = new ArrayList();
        perms.add("*");
        userInfo.setPerms(perms);
        ArrayList roles = new ArrayList();
        roles.add("超级管理员");
        userInfo.setRoles(roles);
        BaseRespVo ok = BaseRespVo.ok(userInfo);
        return ok;
    }
    @RequestMapping("admin/auth/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "admin/auth/login";
    }
    @RequestMapping("/fail")
    public String fail() {
        return "fail.....zy";
    }
    @RequestMapping("/fail123")
    public String fail123() {
        return "fail.....zy11112341231";
    }
}
