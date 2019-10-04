package com.wuyan.mall.service.promotion.impl;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.PromptionMagerBean.GrouponInfo;
import com.wuyan.mall.mapper.*;
import com.wuyan.mall.mapper.AdvertisementMapper;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.mapper.GrouponRulesMapper;
import com.wuyan.mall.mapper.TopicMapper;
import com.wuyan.mall.service.promotion.PromotionService;
import com.wuyan.mall.vo.PromotionPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 推广管理业务层实现类
 * @Date: 2019-10-01-16:05
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    AdvertisementMapper advertisementMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    TopicMapper topicMapper;


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
        PageHelper.startPage(promotionPageInfo.getPage(),promotionPageInfo.getLimit(),promotionPageInfo.getSort());

        List<Advertisement> advertisements = null;
        long total = 0;
        // 处理查找显示
        String content = promotionPageInfo.getContent();
        String name = promotionPageInfo.getName();
        if (content== null && name == null || content == "" && name == "") {
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

    // 广告删除操作
    @Override
    public int adDelete(Advertisement advertisement) {
        int id = advertisement.getId();
        int i = advertisementMapper.deleteByPrimaryKey(id);
        return i;
    }

    // 新增广告
    @Override
    public Advertisement createAdvertisement(Advertisement advertisement) {
        Advertisement readvertisement = new Advertisement();
        int insert = advertisementMapper.insert(advertisement);
        Integer id = advertisement.getId();
        readvertisement = advertisementMapper.selectByPrimaryKey(id);
        return readvertisement;
        // 去mapper.xml改selectKey
    }

    // 编辑广告
    @Override
    public Advertisement updateAdvertisement(Advertisement advertisement) {
        advertisementMapper.updateByPrimaryKey(advertisement);
        return advertisement;
    }
    /**
     * 专题查询
     *
     * @param promotionPageInfo
     * @return
     */
    @Override
    public BaseData<Coupon> getCouponList(PromotionPageInfo promotionPageInfo) {
        BaseData<Coupon> couponBaseData = new BaseData<>();
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        //处理排序
        PageHelper.startPage(promotionPageInfo.getPage(),promotionPageInfo.getLimit(),promotionPageInfo.getSort());

        long total = 0;
        List<Coupon> coupons = null;
        // 处理查找
        String name = promotionPageInfo.getName();
        if (promotionPageInfo.getType() != null) {
            int type = Integer.valueOf(promotionPageInfo.getType());
            criteria.andTypeEqualTo((short) type);
        }
        if (promotionPageInfo.getStatus() != null) {
            int status = Integer.valueOf(promotionPageInfo.getStatus());
            criteria.andStatusEqualTo((short) status);
        }
        if (name != null && name != "") {
            criteria.andNameLike("%" + name + "%");
        }
        total = couponMapper.countByExample(couponExample);
        coupons = couponMapper.selectByExample(couponExample);
        couponBaseData.setItems(coupons);
        couponBaseData.setTotal(total);
        return couponBaseData;
    }

    /**
     * 新增优惠券信息
     * @param coupon
     * @return
     */
    @Override
    public Coupon createCoupon(Coupon coupon) {
        Coupon recoupon = new Coupon();
        int insert = couponMapper.insert(coupon);
        Integer id = coupon.getId();
        recoupon = couponMapper.selectByPrimaryKey(id);
        return recoupon;
    }

    /**
     * 删除优惠券信息
     * @param coupon
     * @return
     */
    @Override
    public int couponDelete(Coupon coupon) {
        int id = coupon.getId();
        int i = couponMapper.deleteByPrimaryKey(id);
        return i;
    }
    // 显示优惠券详细详细
    @Override
    public Coupon readDetailById(int id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }
    @Override
    public BaseData<CouponUser> getListUser(PromotionPageInfo promotionPageInfo) {
        BaseData<CouponUser> couponUserBaseData = new BaseData<>();
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        //处理排序
        PageHelper.startPage(promotionPageInfo.getPage(),promotionPageInfo.getLimit(),promotionPageInfo.getSort());
        long total = 0;
        List<CouponUser> couponUsers = null;
        // 处理查找
        if (promotionPageInfo.getCouponId() != null) {
            int couponId = Integer.valueOf(promotionPageInfo.getCouponId());
            criteria.andCouponIdEqualTo(couponId);
        }
        if (promotionPageInfo.getStatus() != null) {
            int status = Integer.valueOf(promotionPageInfo.getStatus());
            criteria.andStatusEqualTo((short) status);
        }
        total = couponUserMapper.countByExample(couponUserExample);
        couponUsers = couponUserMapper.selectByExample(couponUserExample);
        couponUserBaseData.setItems(couponUsers);
        couponUserBaseData.setTotal(total);
        return couponUserBaseData;
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        Coupon recoupon = new Coupon();
        int i = couponMapper.updateByPrimaryKey(coupon);
        Integer id = coupon.getId();
        recoupon = couponMapper.selectByPrimaryKey(id);
        return recoupon;
    }

    @Override
    public BaseData<Object> getGroupList(PromotionPageInfo promotionPageInfo) {
        BaseData<Object> baseData = new BaseData<>();
        GrouponExample grouponExample = new GrouponExample();
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(promotionPageInfo.getPage(),promotionPageInfo.getLimit(),promotionPageInfo.getSort());
        GrouponInfo grouponInfo = new GrouponInfo();
        List<Object> items = new LinkedList<>();
        String[] subGroupons = new String[]{};
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        List<Groupon> groupons = grouponMapper.selectByExample(grouponExample);
        int i = 0;
        for (Groupon groupon : groupons) {
            Integer rulesId = groupon.getRulesId();
            GrouponRules rules = grouponRulesMapper.selectByPrimaryKey(rulesId);
            Integer goodsId = rules.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            grouponInfo.setGoods(goods);
            grouponInfo.setGroupon(groupon);
            grouponInfo.setGrouponRules(rules);
            grouponInfo.setSubGroupons(subGroupons);
            items.add(grouponInfo);
        }

        long total = items.size();
        baseData.setItems(items);
        baseData.setTotal(total);
        return baseData;
    }
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

