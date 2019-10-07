package com.wuyan.wx.bean;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 订单页面
 * @Date: 2019-10-05-19:50
 */
public class OrderPage {
    // 订单状态
    int showType;
    // 当前页面
    int page;
    // 页面大小
    int size;

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
