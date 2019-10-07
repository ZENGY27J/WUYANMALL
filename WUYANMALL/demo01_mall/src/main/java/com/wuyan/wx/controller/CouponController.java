package com.wuyan.wx.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.mall.vo.ShowAllCouponVo;
import com.wuyan.wx.service.couponService.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    /**
     * 分页一页十条数据显示优惠券
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo couponList(PageInfo pageInfo) {
        List<Coupon> allCoupons = couponService.getAllCoupons(pageInfo);

        ShowAllCouponVo showAllCouponVo = new ShowAllCouponVo();
        showAllCouponVo.setData(allCoupons);
        showAllCouponVo.setCount(allCoupons.size());
        return BaseRespVo.ok(showAllCouponVo);
    }
}
