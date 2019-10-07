package com.wuyan.wx.utils;

import com.wuyan.wx.config.UserTokenManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 通过request请求获得当前userId
 * @Date: 2019-10-05-21:46
 */
public class GetUserId {
    public static Integer getUserIdByRequest(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        return userId;
    }
}
