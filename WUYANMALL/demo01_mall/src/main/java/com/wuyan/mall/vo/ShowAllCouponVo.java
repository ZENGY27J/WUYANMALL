package com.wuyan.mall.vo;

import com.wuyan.mall.bean.Coupon;

import java.util.List;

/**
 * 显示所有优惠券
 */
public class ShowAllCouponVo {
    List<Coupon> data;

    int count;


    public List<Coupon> getData() {
        return data;
    }

    public void setData(List<Coupon> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
