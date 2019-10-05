package com.wuyan.wx.utils;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;

public class QueryUtils {
    public static BrandExample getBrand(int size){
        PageHelper.startPage(1, size);
        return new BrandExample();
    }
    public static CategoryExample getCategory(int size,int pid){
        CategoryExample categoryExample = new CategoryExample();
        PageHelper.startPage(1,size);
        categoryExample.createCriteria().andPidEqualTo(pid);
        return categoryExample;
    }
    public static CategoryExample getCategoryById(int id) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(id);
        return categoryExample;
    }
    public static CouponExample getCoupon(int size){
        PageHelper.startPage(1, size);
        return new CouponExample();
    }
    public static GoodsExample getGoodsByCategoryId(int categoryId,int size){
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(1,size);
        return goodsExample;
    }
    public static GoodsExample getHotGoods(int size){
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(1,size);
        return goodsExample;
    }
    public static GoodsExample getNewGoods(int size){
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(1,size);
        return goodsExample;
    }
    public static GoodsExample getGoodsById(int id){
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(id);
        return goodsExample;
    }
    //获取团购信息
    public static GrouponRulesExample getGroupon(int size){
        PageHelper.startPage(1,5);
        return new GrouponRulesExample();
    }
    public static TopicExample getTopic(int size){
        PageHelper.startPage(1,size);
        return new TopicExample();
    }
}
