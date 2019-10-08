package com.wuyan.wx.service.orderService;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Override
    public void deleteOrder(int orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
        OrderGoodsExample example = new OrderGoodsExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        orderGoodsMapper.deleteByExample(example);
    }

    @Override
    public int submit(Map map) {
        Order order = new Order();
        Address address = addressMapper.selectByPrimaryKey((Integer) map.get("addressId"));
        order.setUserId(address.getUserId());
        order.setOrderStatus((short) 201);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        order.setOrderSn(format);
        order.setConsignee(address.getName());
        order.setMobile(address.getMobile());
        order.setAddress(address.getAddress());
        order.setMessage((String) map.get("message"));
        Cart cart = cartMapper.selectByPrimaryKey((Integer) map.get("cartId"));
        order.setGoodsPrice(cart.getPrice());
        Coupon coupon = couponMapper.selectByPrimaryKey((Integer) map.get("couponId"));
        order.setCouponPrice(coupon.getDiscount());
        order.setFreightPrice(BigDecimal.valueOf(0));
        order.setIntegralPrice(BigDecimal.valueOf(0));
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey((Integer) map.get("grouponRulesId"));
        order.setGrouponPrice(grouponRules.getDiscount());
        order.setOrderPrice(order.getGoodsPrice().add(order.getFreightPrice()).subtract(order.getCouponPrice()));
        order.setActualPrice(order.getOrderPrice().subtract(order.getIntegralPrice()));
        orderMapper.insert(order);
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderSnEqualTo(order.getOrderSn());
        List<Order> orders = orderMapper.selectByExample(example);
        Integer id = orders.get(0).getId();
        return id;
    }


}
