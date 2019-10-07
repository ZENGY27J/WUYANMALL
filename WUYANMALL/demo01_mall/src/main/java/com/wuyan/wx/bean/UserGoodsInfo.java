package com.wuyan.wx.bean;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户商品信息
 * @Date: 2019-10-05-20:12
 */
public class UserGoodsInfo {
    // 商品id
    int id;
    // 商品名称
    String goodsName;
    // 商品数量
    int number;
    // 商品图片地址
    String picUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
