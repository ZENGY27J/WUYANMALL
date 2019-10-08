package com.wuyan.wx.service.homeService;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.*;
import com.wuyan.wx.bean.GrouponList;
import com.wuyan.wx.bean.Home;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    AdvertisementMapper advertisementMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    TopicMapper topicMapper;
    @Override
    public long getGoodsCount() {
        return goodsMapper.countByExample(new GoodsExample());
    }

    @Override
    public Home getHomeIndex() {
        //获取横幅广告
        List<Advertisement> banner = advertisementMapper.selectByExample(new AdvertisementExample());
        //获取品牌商列表
        List<Brand> brandList = brandMapper.selectByExample(QueryUtils.getBrand(3));
        //获取商品一级类目
        List<Category> channel = categoryMapper.selectByExample(QueryUtils.getCategory(0,0));
        //获取优惠券
        List<Coupon> couponList = couponMapper.selectByExample(QueryUtils.getCoupon(1,3));
        //获取指定类目下的商品
        List<Category> floorGoodsList = categoryMapper.selectByExample(QueryUtils.getCategory(4,0));

        for (Category category : floorGoodsList) {
            int id = category.getId();
            List<Category> categories = categoryMapper.selectByExample(QueryUtils.getCategory(0, id));
            List<Goods> goodsList = new ArrayList<>();
            for (Category category1 : categories) {
                Integer id1 = category1.getId();
                List<Goods> goods = goodsMapper.selectByExample(QueryUtils.getGoodsByCategoryId(id1,0));
                for (Goods good : goods) {
                    if (good != null){
                        goodsList.add(good);
                    }
                    if (goodsList.size() >= 2){
                        break;
                    }
                }
            }
            category.setGoodsList(goodsList);
        }
        //获取团购信息
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(QueryUtils.getGroupon(5));
        List<GrouponList> grouponList = new ArrayList<>();

        for (GrouponRules grouponRule : grouponRules) {
            GrouponList grouponList1 = new GrouponList();
            Integer goodsId = grouponRule.getGoodsId();
            List<Goods> goods = goodsMapper.selectByExample(QueryUtils.getGoodsById(goodsId));
            grouponList1.setGroupon_member(String.valueOf(grouponRule.getDiscountMember()));
            Goods goods1 = goods.get(0);
            grouponList1.setGoods(goods1);
            BigDecimal retailPrice = goods1.getRetailPrice();
            BigDecimal discount = grouponRule.getDiscount();
            grouponList1.setGroupon_price(String.valueOf(retailPrice.subtract(discount)));
            grouponList.add(grouponList1);
        }
        //人气推荐
        List<Goods> hotGoodsList = goodsMapper.selectByExample(QueryUtils.getHotGoods(7));
        //新品推荐
        List<Goods> newGoodsList = goodsMapper.selectByExample(QueryUtils.getNewGoods(7));
        //获取专题
        List<Topic> topics = topicMapper.selectByExample(QueryUtils.getTopic(3));

        Home home = new Home(banner, brandList, channel, couponList, floorGoodsList, grouponList, hotGoodsList, newGoodsList, topics);
        return home;
    }
}
