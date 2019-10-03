package com.wuyan.mall.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.LoginVo;
import com.wuyan.mall.vo.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD

=======
>>>>>>> zydevone
import java.util.ArrayList;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 控制层
 * @Date: 2019-09-30-11:53
 */
@RestController
public class LogicController {
    @RequestMapping("admin/auth/login")
    public BaseRespVo logic(LoginVo loginVo) {
        BaseRespVo ok = BaseRespVo.ok("95e3b296-6159-47d5-bfea-78373ef2458d");
        return ok;
    }
    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token) {
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
}
