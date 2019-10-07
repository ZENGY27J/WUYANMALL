package com.wuyan.wx.bean;

<<<<<<< HEAD
import java.time.LocalDateTime;

=======
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户登录名称和密码
 * @Date: 2019-10-05-15:54
 */
public class UserToken {
<<<<<<< HEAD
    Integer userId;
    String token;
    String sessionKey;
    LocalDateTime expireTime;
    LocalDateTime updateTime;
=======
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
<<<<<<< HEAD

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
=======
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
}
