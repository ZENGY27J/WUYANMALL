package com.wuyan.wx.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.mall.vo.ShowAllCouponVo;
import com.wuyan.wx.service.couponService.CouponService;
import com.wuyan.wx.utils.GetUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("receive")
    public BaseRespVo couponReceive(@RequestBody Map map, HttpServletRequest request){
        int couponId = (int) map.get("couponId");
        Integer userId = GetUserId.getUserIdByRequest(request);
        if (userId == null){
            return BaseRespVo.LoginError();
        }
        int receive = couponService.couponReceive(couponId,userId);
        if (receive == -1){
            return BaseRespVo.couponError();
        }
        return BaseRespVo.ok(null);
    }
    @RequestMapping("exchange")
    public BaseRespVo exchang(@RequestBody Map map,HttpServletRequest request){
        String code = (String) map.get("code");
        Integer userId = GetUserId.getUserIdByRequest(request);
        if (userId == null){
            return BaseRespVo.LoginError();
        }
        int exchange = couponService.exchange(code,userId);
        if (exchange == -2){
            return BaseRespVo.exchangeError();
        }
        if (exchange == -1){
            return BaseRespVo.couponError();
        }
        return BaseRespVo.ok(null);
    }
}
