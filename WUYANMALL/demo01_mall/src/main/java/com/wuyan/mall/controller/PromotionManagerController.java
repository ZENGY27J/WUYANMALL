package com.wuyan.mall.controller;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.service.promotion.PromotionService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PromotionPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询专题
     *
     * @param promotionPageInfo
     * @return
     */
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
