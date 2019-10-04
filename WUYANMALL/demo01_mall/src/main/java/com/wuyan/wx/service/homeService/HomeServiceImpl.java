package com.wuyan.wx.service.homeService;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.*;
import com.wuyan.wx.bean.Home;
import com.wuyan.wx.utils.HomeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    TopicMapper topicMapper;
    @Override
    public long getGoodsCount() {
        return goodsMapper.countByExample(new GoodsExample());
    }

    @Override
    public Home getHomeIndex() {
        //获取横幅广告
        List<Advertisement> banner = advertisementMapper.selectByExample(new AdvertisementExample());
        //获取品牌商列表,true代表限制数量
        List<Brand> brandList = brandMapper.selectByExample(HomeUtils.getBrand(true));
        //获取商品一级类目
        List<Category> channel = categoryMapper.selectByExample(HomeUtils.getFirstCategory(false));
        //获取优惠券,true代表限制数量
        List<Coupon> couponList = couponMapper.selectByExample(HomeUtils.getCoupon(true));
        //获取指定类目下的商品,true代表限制数量
        List<Category> floorGoodsList = categoryMapper.selectByExample(HomeUtils.getFirstCategory(true));
        for (Category category : floorGoodsList) {
            List<Goods> goods = goodsMapper.selectByExample(HomeUtils.getGoods(category.getId(),true));
        }
        //获取团购信息
        List<Groupon> groupons = grouponMapper.selectByExample(new GrouponExample());
        //人气推荐
        //获取专题
        List<Topic> topics = topicMapper.selectByExample(new TopicExample());
        return null;
    }
}
