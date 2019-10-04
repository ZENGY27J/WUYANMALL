package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Advertisement;
import com.wuyan.mall.bean.BaseData;
import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.bean.CouponUser;
import com.wuyan.mall.service.promotion.PromotionService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PromotionPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 推广管理控制层
 * @Date: 2019-10-01-15:31
 */
@RestController
@RequestMapping("admin")
public class PromotionManagerController {

    @Autowired
    PromotionService promotionService;

    /**
     * 广告页面显示
     * @param promotionPageInfo
     * @return
     */
    @RequestMapping("/ad/list")
    public BaseRespVo getAdList(PromotionPageInfo promotionPageInfo) {
        BaseData<Advertisement> advertisement = promotionService.getAddList(promotionPageInfo);
        if (advertisement == null) {
            return BaseRespVo.error(advertisement);
        }
        BaseRespVo ok = BaseRespVo.ok(advertisement);
        return ok;
    }

    /**
     * 删除对应广告条目
     * @param advertisement
     * @return
     */
    @RequestMapping("/ad/delete")
    public BaseRespVo adDelete(@RequestBody Advertisement advertisement) {
        int i = promotionService.adDelete(advertisement);
        BaseRespVo ok = BaseRespVo.ok(advertisement);
        return ok;
    }
    @RequestMapping("/ad/create")
    public BaseRespVo adCreate(@RequestBody Advertisement advertisement) {
        Advertisement AdvertisementBaseData = promotionService.createAdvertisement(advertisement);
        BaseRespVo ok = BaseRespVo.ok(AdvertisementBaseData);
        return ok;
    }
    @RequestMapping("/ad/update")
    public BaseRespVo adUpdate(@RequestBody Advertisement advertisement) {
        Advertisement AdvertisementBaseData = promotionService.updateAdvertisement(advertisement);
        BaseRespVo ok = BaseRespVo.ok(AdvertisementBaseData);
        return ok;
    }

    /**
     * 优惠券管理查找显示
     * @param promotionPageInfo
     * @return
     */
    @RequestMapping("/coupon/list")
    public BaseRespVo getCouponList(PromotionPageInfo promotionPageInfo) {
        BaseData<Coupon> coupon = promotionService.getCouponList(promotionPageInfo);
        BaseRespVo ok = BaseRespVo.ok(coupon);
        return ok;
    }

    /**
     * 新增新的优惠卷信息
     * @param coupon
     * @return
     */
    @RequestMapping("/coupon/create")
    public BaseRespVo createCoupon(@RequestBody Coupon coupon) {
        Coupon couponBaseData = promotionService.createCoupon(coupon);
        BaseRespVo ok = BaseRespVo.ok(couponBaseData);
        return ok;
    }

    /**
     * 删除优惠券信息
     * @param coupon
     * @return
     */
    @RequestMapping("/coupon/delete")
    public BaseRespVo couponDelete(@RequestBody Coupon coupon) {
        int i = promotionService.couponDelete(coupon);
        coupon = null;
        BaseRespVo ok = BaseRespVo.ok(coupon);
        return ok;
    }
    @RequestMapping("/coupon/read")
    public BaseRespVo readDetail(int id){
        Coupon coupon = promotionService.readDetailById(id);
        BaseRespVo ok = BaseRespVo.ok(coupon);
        return ok;
    }
    @RequestMapping("/coupon/listuser")
    public BaseRespVo getListUser(PromotionPageInfo promotionPageInfo) {
        BaseData<CouponUser> couponUser = promotionService.getListUser(promotionPageInfo);
        BaseRespVo ok = BaseRespVo.ok(couponUser);
        return ok;
    }
    @RequestMapping("/coupon/update")
    public BaseRespVo updateCoupon(@RequestBody Coupon coupon) {
        Coupon couponBaseData = promotionService.updateCoupon(coupon);
        BaseRespVo ok = BaseRespVo.ok(couponBaseData);
        return ok;
    }
    @RequestMapping("/groupon/listRecord")
    public BaseRespVo getGroupList(PromotionPageInfo promotionPageInfo) {
        BaseData<Object> object = promotionService.getGroupList(promotionPageInfo);
        BaseRespVo ok = BaseRespVo.ok(object);
        return ok;
    }

}
