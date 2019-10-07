package com.wuyan.wx.bean;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 订单页面列表
 * @Date: 2019-10-05-19:55
 */
public class OrderPageList {
    // 订单总数
    int count;
    // 具体订单对象list
    List<UserOrderInfo> data;
    // 总页数
    int totalPages;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserOrderInfo> getData() {
        return data;
    }

    public void setData(List<UserOrderInfo> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
