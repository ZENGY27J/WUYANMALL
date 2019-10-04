package com.wuyan.wx.utils;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.BrandExample;
import com.wuyan.mall.bean.CategoryExample;
import com.wuyan.mall.bean.CouponExample;
import com.wuyan.mall.bean.GoodsExample;

public class HomeUtils {
    public static BrandExample getBrand(Boolean flag){
        if(flag) {
            PageHelper.startPage(1, 3);
        }
        return new BrandExample();
    }
    public static CategoryExample getFirstCategory(Boolean flag){
        CategoryExample categoryExample = new CategoryExample();
        if(flag){
            PageHelper.startPage(1,4);
        }
        categoryExample.createCriteria().andPidEqualTo(0);
        return categoryExample;
    }
    public static CouponExample getCoupon(Boolean flag){
        if(flag) {
            PageHelper.startPage(1, 3);
        }
        return new CouponExample();
    }
    public static GoodsExample getGoods(int id,Boolean flag){
        GoodsExample goodsExample = new GoodsExample();
        if (flag){
            PageHelper.startPage(1,2);
        }
        goodsExample.createCriteria().andCategoryIdEqualTo(id);
        return goodsExample;
    }
}
