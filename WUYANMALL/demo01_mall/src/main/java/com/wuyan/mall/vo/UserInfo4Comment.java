package com.wuyan.mall.vo;

/**
 * @Description: 专题列表list 接口返回数据的格式中需要的userInfo
 * @Author: fangbo
 * @Date: 2019/10/7
 */
public class UserInfo4Comment {
    String nickName;

    String avatarUrl;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
