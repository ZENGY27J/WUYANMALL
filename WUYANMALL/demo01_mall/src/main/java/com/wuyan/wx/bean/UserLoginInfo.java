package com.wuyan.wx.bean;

import com.wuyan.mall.bean.User;

import java.util.Map;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户登录返回信息
 * @Date: 2019-10-05-16:15
 */
public class UserLoginInfo {
    // 用户信息
    Map<String,String> userInfo;
    // token到期时间
    String tokenExpire;
    // session的id
    String token;

    public Map<String, String> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
    }

    public String getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
