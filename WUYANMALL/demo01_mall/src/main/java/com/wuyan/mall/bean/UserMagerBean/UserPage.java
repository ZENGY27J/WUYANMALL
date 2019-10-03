package com.wuyan.mall.bean.UserMagerBean;

import com.wuyan.mall.bean.User;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 所有user分页展示页
 * @Date: 2019-09-30-15:10
 */
public class UserPage {
    long total;
    List<User> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "UserPage{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
