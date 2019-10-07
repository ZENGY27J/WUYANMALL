package com.wuyan.wx.vo;

import com.wuyan.mall.bean.Goods;

import java.util.List;

public class GoodsByCategoryIdVo {
    List<Goods> goodsList;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
