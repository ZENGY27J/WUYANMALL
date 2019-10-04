package com.wuyan.mall.service.promotion;

import com.wuyan.mall.bean.Advertisement;
import com.wuyan.mall.bean.BaseData;
import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.bean.CouponUser;
import com.wuyan.mall.vo.PromotionPageInfo;

/**
 * 推广管理业务层接口
 */
public interface PromotionService {
    BaseData<Advertisement> getAddList(PromotionPageInfo promotionPageInfo);

    int adDelete(Advertisement advertisement);

    BaseData<Coupon> getCouponList(PromotionPageInfo promotionPageInfo);

    Coupon createCoupon(Coupon coupon);

    int couponDelete(Coupon coupon);

    Coupon readDetailById(int id);

    BaseData<CouponUser> getListUser(PromotionPageInfo promotionPageInfo);

    Coupon updateCoupon(Coupon coupon);

    BaseData<Object> getGroupList(PromotionPageInfo promotionPageInfo);

    Advertisement createAdvertisement(Advertisement advertisement);

    Advertisement updateAdvertisement(Advertisement advertisement);
}
