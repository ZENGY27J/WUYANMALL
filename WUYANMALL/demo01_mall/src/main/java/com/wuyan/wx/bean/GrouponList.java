package com.wuyan.wx.bean;

import com.wuyan.mall.bean.Goods;

public class GrouponList {
    private String groupon_member;

    private String groupon_price;

    private Goods goods;

    public String getGroupon_member() {
        return groupon_member;
    }

    public void setGroupon_member(String groupon_member) {
        this.groupon_member = groupon_member;
    }

    public String getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(String groupon_price) {
        this.groupon_price = groupon_price;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
