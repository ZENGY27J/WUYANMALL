package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Advertisement;
import com.wuyan.mall.bean.BaseData;
import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.bean.CouponUser;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.service.promotion.PromotionService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PromotionPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @RequestMapping("ad/list")
    public BaseRespVo getAdList(PromotionPageInfo promptionPageInfo) {
        BaseData<Advertisement> advertisement = promotionService.getAddList(promptionPageInfo);
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

    @RequestMapping("topic/list")
    public BaseRespVo getTopicList(PromotionPageInfo promotionPageInfo) {
        BaseData<Topic> topic = promotionService.getTopicList(promotionPageInfo);
        if (topic == null) {
            return BaseRespVo.error(topic);
        }
        BaseRespVo ok = BaseRespVo.ok(topic);
        return ok;
    }


    /**
     * 删除专题
     *
     * @param topic
     * @return
     */
    @RequestMapping("topic/delete")
    public BaseRespVo deleteTopic(@RequestBody Topic topic) {
        promotionService.deleteTopicByID(topic.getId());
        return BaseRespVo.ok(topic);

    }

    /**
     * 编辑专题
     *
     * @param topic
     * @return
     */
    @RequestMapping("topic/update")
    public BaseRespVo updateTopic(@RequestBody Topic topic) {
        if (topic.getContent() == "" || topic.getPrice() == null) {
            return BaseRespVo.badArgument(topic);
        }
        int update = promotionService.updateTopicById(topic);
        if (update == 0) {
            return BaseRespVo.updatedDataFiled(topic);
        }

        return BaseRespVo.ok(topic);

    }

    /**
     * 添加专题
     *
     * @param topic
     * @return
     */
    @RequestMapping("topic/create")
    public BaseRespVo createTopic(@RequestBody Topic topic) {

        if (topic.getContent() == null || topic.getPrice() == null) {
            return BaseRespVo.badArgument(topic);
        }
        promotionService.creatTopic(topic);
        return  BaseRespVo.ok(topic);
    }
// jdbcType=VARCHAR,typeHandler=com.wuyan.mall.mybatis.TransferStringArreyHander
    /**
     * 查询团购规则
     *
     * @param promotionPageInfo
     * @return
     */
    @RequestMapping("groupon/list")
    public BaseRespVo getGroupRulesList(PromotionPageInfo promotionPageInfo) {
        BaseData<GrouponRules> grouponRules = promotionService.getGroupRulesList(promotionPageInfo);
        return BaseRespVo.ok(grouponRules);
    }

    /**
     * 删除团购规则
     *
     * @param grouponRules
     * @return
     */
    @RequestMapping("groupon/delete")
    public BaseRespVo deleteGroupRules(@RequestBody GrouponRules grouponRules) {
        promotionService.deleteGroupRules(grouponRules.getId());
        return BaseRespVo.ok(grouponRules);
    }

    /**
     * 编辑团购规则
     *
     * @param grouponRules
     * @return
     */
    @RequestMapping("groupon/update")
    public BaseRespVo updateGroupRules(@RequestBody GrouponRules grouponRules) {
        int i = promotionService.updateGroupRules(grouponRules);
        if (i == 0) {
            BaseRespVo error = BaseRespVo.error(grouponRules);
        }
        return new BaseRespVo();

    }

    /**
     * 增加团购规则
     *
     * @param grouponRules
     * @return
     */
    @RequestMapping("groupon/create")
    public BaseRespVo createGroupRules(@RequestBody GrouponRules grouponRules) {
        Integer goodsId = grouponRules.getGoodsId();
        Goods goods = promotionService.findById(goodsId);
        if (goods == null) {
            return BaseRespVo.error(grouponRules);
        }

        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());

        int i = promotionService.createGroupRules(grouponRules);
        if (i == 0) {
            return BaseRespVo.error(grouponRules);
        }
        return BaseRespVo.ok(grouponRules);
    }
}
