package com.wuyan.mall.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 存储用户统计返回的rows 数据的对象
 */
public class StatUserRowVo {
    String day;
    int users;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
}
