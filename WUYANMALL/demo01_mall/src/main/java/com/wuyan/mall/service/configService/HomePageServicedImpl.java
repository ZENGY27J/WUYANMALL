package com.wuyan.mall.service.configService;

import com.wuyan.mall.bean.GoodsExample;
import com.wuyan.mall.bean.GoodsProductExample;
import com.wuyan.mall.bean.OrderExample;
import com.wuyan.mall.bean.UserExample;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.mapper.GoodsProductMapper;
import com.wuyan.mall.mapper.OrderMapper;
import com.wuyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageServicedImpl implements HomePageService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Long getgoodsTotal() {
        GoodsExample goodsExample = new GoodsExample();
        long goodsTotal = goodsMapper.countByExample(goodsExample);
        return goodsTotal;
    }

    @Override
    public long getUserTotal() {
        UserExample userExample = new UserExample();
        long userTotal = userMapper.countByExample(userExample);
        return userTotal;
    }

    @Override
    public long getProductTotal() {
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        long goodsProductTotal = goodsProductMapper.countByExample(goodsProductExample);
        return goodsProductTotal;
    }

    @Override
    public long getOrderTotal() {
        OrderExample orderExample = new OrderExample();
        long orderTotal = orderMapper.countByExample(orderExample);
        return orderTotal;
    }
}
