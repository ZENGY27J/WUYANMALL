package com.wuyan.mall.service.promotion.impl;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.AdvertisementMapper;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.mapper.GrouponRulesMapper;
import com.wuyan.mall.mapper.TopicMapper;
import com.wuyan.mall.service.promotion.PromotionService;
import com.wuyan.mall.vo.PromotionPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/10/2 0002 17:30
 */
@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    AdvertisementMapper advertisementMapper;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 广告管理查询
     *
     * @param promotionPageInfo
     * @return
     */
    @Override
    public BaseData<Advertisement> getAddList(PromotionPageInfo promotionPageInfo) {
        BaseData<Advertisement> advertisementBaseData = new BaseData<>();
        AdvertisementExample advertisementExample = new AdvertisementExample();
        AdvertisementExample.Criteria criteria = advertisementExample.createCriteria();
        // 分页
        PageHelper.startPage(promotionPageInfo.getPage(), promotionPageInfo.getLimit(), promotionPageInfo.getSort());

        List<Advertisement> advertisements = null;
        long total = 0;
        // 处理查找显示
        String content = promotionPageInfo.getContent();
        String name = promotionPageInfo.getName();
        if (content == null && name == null || content == "" && name == "") {
            total = advertisementMapper.countByExample(advertisementExample);
            advertisements = advertisementMapper.selectByExample(advertisementExample);
        } else if (content != null && name == null) {
            criteria.andContentLike("%" + content + "%");
            total = advertisementMapper.countByExample(advertisementExample);
            advertisements = advertisementMapper.selectByExample(advertisementExample);
        } else if (content == null && name != null) {
            criteria.andNameLike("%" + name + "%");
            total = advertisementMapper.countByExample(advertisementExample);
            advertisements = advertisementMapper.selectByExample(advertisementExample);
        } else {
            criteria.andContentLike("%" + content + "%");
            criteria.andNameLike("%" + name + "%");
            total = advertisementMapper.countByExample(advertisementExample);
            advertisements = advertisementMapper.selectByExample(advertisementExample);
        }
        advertisementBaseData.setItems(advertisements);
        advertisementBaseData.setTotal(total);
        return advertisementBaseData;
    }

    /**
     * 专题查询
     *
     * @param promotionPageInfo
     * @return
     */
    @Override
    public BaseData<Topic> getTopicList(PromotionPageInfo promotionPageInfo) {
        BaseData<Topic> topicBaseData = new BaseData<>();
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();

        //分页
        PageHelper.startPage(promotionPageInfo.getPage(), promotionPageInfo.getLimit(), promotionPageInfo.getSort());

        List<Topic> topics = null;
        long total = 0;

        //处理查找显示
        String title = promotionPageInfo.getTitle();
        String subtitle = promotionPageInfo.getSubtitle();

        if (title == null && subtitle == null) {
            total = topicMapper.countByExample(topicExample);
            topics = topicMapper.selectByExample(topicExample);
        } else if (title != null && subtitle == null) {
            criteria.andTitleLike("%" + title + "%");
            total = topicMapper.countByExample(topicExample);
            topics = topicMapper.selectByExample(topicExample);
        } else if (title == null && subtitle != null) {
            criteria.andSubtitleLike("%" + subtitle + "%");
            total = topicMapper.countByExample(topicExample);
            topics = topicMapper.selectByExample(topicExample);
        } else {
            criteria.andTitleLike("%" + title + "%");
            criteria.andSubtitleLike("%" + subtitle + "%");
            total = topicMapper.countByExample(topicExample);
            topics = topicMapper.selectByExample(topicExample);
        }
        topicBaseData.setItems(topics);
        topicBaseData.setTotal(total);

        return topicBaseData;
    }

    /**
     * 删除专题
     *
     * @param id
     */
    @Override
    public void deleteTopicByID(Integer id) {
        topicMapper.deleteByPrimaryKey(id);
    }

    /**
     * 编辑专题
     *
     * @param topic
     * @return
     */
    @Override
    public int updateTopicById(Topic topic) {
        topic.setUpdateTime(new Date());
        TopicExample example = new TopicExample();
        example.or().andIdEqualTo(topic.getId());
        return topicMapper.updateByExampleSelective(topic, example);
    }

    /**
     * 增加专题
     *
     * @param topic
     */
    @Override
    public void creatTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topic.setAddTime(new Date());
        topicMapper.insert(topic);
    }

    @Override
    public BaseData<GrouponRules> getGroupRulesList(PromotionPageInfo promotionPageInfo) {
        BaseData<GrouponRules> grouponRulesBaseData = new BaseData<>();
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();

        //分页
        PageHelper.startPage(promotionPageInfo.getPage(), promotionPageInfo.getLimit(), promotionPageInfo.getSort());

        List<GrouponRules> grouponRules = null;
        long total = 0;
        //处理查找显示
        Integer goodsId = promotionPageInfo.getGoodsId();
        if (goodsId == null) {
            total = grouponRulesMapper.countByExample(grouponRulesExample);
            grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        } else {
            criteria.andGoodsIdEqualTo(goodsId);
            total = grouponRulesMapper.countByExample(grouponRulesExample);
            grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);

        }
        grouponRulesBaseData.setItems(grouponRules);
        grouponRulesBaseData.setTotal(total);
        return grouponRulesBaseData;
    }

    /**
     * 删除团购规则
     *
     * @param id
     */
    @Override
    public void deleteGroupRules(Integer id) {
        grouponRulesMapper.deleteByPrimaryKey(id);
    }

    /**
     * 编辑团购规则
     *
     * @param grouponRules
     * @return
     */
    @Override
    public int updateGroupRules(GrouponRules grouponRules) {
        int i = 0;
        if (grouponRules.getDiscount() != null && grouponRules.getDiscountMember() != 0 && grouponRules.getExpireTime() != null) {
            BigDecimal discount = grouponRules.getDiscount();
            if (grouponRules.getDiscount() instanceof BigDecimal && grouponRules.getDiscountMember() instanceof Integer) {
                Integer goodsId = grouponRules.getGoodsId();
                GrouponRules grouponRules1 = grouponRulesMapper.selectByPrimaryKey(goodsId);
                if (grouponRules1 != null) {
                    i = grouponRulesMapper.updateByPrimaryKey(grouponRules);
                }
            }

        }
        return i;

    }


    /**
     * 增加团购规则
     * @param grouponRules
     * @return
     */
    @Override
    public int createGroupRules(GrouponRules grouponRules) {
        grouponRules.setAddTime(new Date());
        grouponRules.setUpdateTime(new Date());
        int i = grouponRulesMapper.insertSelective(grouponRules);
        return i;
    }


    /**
     * 获取商品信息
     * @param id
     * @return
     */
    public Goods findById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
}

