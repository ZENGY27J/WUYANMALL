package com.wuyan.wx.service.couponService;

import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.vo.PageInfo;

import java.util.List;

public interface CouponService {

    List<Coupon> getAllCoupons(PageInfo pageInfo);

}