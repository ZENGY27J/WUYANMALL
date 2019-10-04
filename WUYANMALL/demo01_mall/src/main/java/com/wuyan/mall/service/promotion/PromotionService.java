package com.wuyan.mall.service.promotion;

import com.wuyan.mall.bean.*;
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

    BaseData<Topic> getTopicList(PromotionPageInfo promotionPageInfo);

    void deleteTopicByID(Integer id);

    int updateTopicById(Topic topic);

    BaseData<GrouponRules> getGroupRulesList(PromotionPageInfo promotionPageInfo);

    void deleteGroupRules(Integer id);

    int updateGroupRules(GrouponRules grouponRules);

    Goods findById(Integer goodsId);

    int createGroupRules(GrouponRules grouponRules);

    void creatTopic(Topic topic);

}
