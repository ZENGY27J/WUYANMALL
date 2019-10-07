package com.wuyan.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 专题区评论list 返回的报文的data 链表的对象(data)
 * @Author: fangbo
 * @Date: 2019/10/7
 */
public class TopicCommentDataVo {
    UserInfo4Comment userInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date addTime;

    String[] picList;

    String content;


    public UserInfo4Comment getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo4Comment userInfo) {
        this.userInfo = userInfo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
