package com.wuyan.wx.service.cartService;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.Accept.AcceptCartChecks;
import com.wuyan.mall.mapper.*;
import com.wuyan.mall.vo.IndexCartVo;
import com.wuyan.mall.vo.WxCartCheckoutVo;
import com.wuyan.wx.service.couponService.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    AddressMapper addressMapper;


    //显示购物车商品状态
    @Override
    public IndexCartVo showCart(Integer userId) {
        //IndexCartVo类对应/wx/cart/index 接口需要返回的对象
        IndexCartVo indexCartVo = new IndexCartVo();

        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andIdEqualTo(userId);

        List<Cart> carts = cartMapper.selectByExample(cartExample);

        //要返回的详细数据的键值对
        HashMap<String, Object> cartTotal = new HashMap<>();
        BigDecimal checkedGoodsAmount = new BigDecimal(0);
        int checkedGoodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0);
        int goodsCount = 0;

        //遍历购物车里面的商品
        for (Cart cart : carts) {
            //求出购物车被勾选商品的总数, 及总价
            if (cart.getChecked() == true) {
                checkedGoodsCount += cart.getNumber();

                checkedGoodsAmount = checkedGoodsAmount.add(cart.getPrice());
            }

            //求出购物车中所有商品的总价
            goodsAmount = goodsAmount.add(cart.getPrice());
            //求出购物车中商品总数
            goodsCount += cart.getNumber();
        }

        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("goodsCount", goodsCount);

        indexCartVo.setCarts(carts);
        indexCartVo.setCartTotal(cartTotal);
        return indexCartVo;
    }

    @Override
    public IndexCartVo checkGoods(AcceptCartChecks acceptCartChecks, Integer userId) {
        IndexCartVo indexCartVo = new IndexCartVo();

        List<Integer> productIds = acceptCartChecks.getProductIds();

        //根据数据更改购物车里选定商品的选择状态
        for (Integer productId : productIds) {
            Cart cart = new Cart();
            cart.setChecked(acceptCartChecks.isChecked());
            cart.setId(productId);
            cartMapper.updateByPrimaryKey(cart);
        }

        //显示更改后的购物车页面状态
        indexCartVo.setCarts(showCart(userId).getCarts());
        indexCartVo.setCartTotal(showCart(userId).getCartTotal());
        return indexCartVo;
    }

    @Override
    public void update(Cart cart, Integer userId) {
        cartMapper.updateByPrimaryKey(cart);
    }

    //商品加入购物车
    @Override
    public void insert(Cart cart, Integer userId) {
        cart.setUserId(userId);
        cartMapper.insert(cart);
    }

    //返回购物车商品数量
    @Override
    public int showGoodsCount(Integer userId) {
        return showCart(userId).getCarts().size();
    }

    @Override
    public WxCartCheckoutVo checkoutGoods(String cartId, String addressId, String couponId, String grouponRulesId, int userId) {
        WxCartCheckoutVo wxCartCheckoutVo = new WxCartCheckoutVo();
        List<Cart> checkedGoodsList = new ArrayList<>();
        BigDecimal goodsTotalPrice = new BigDecimal(0);
        BigDecimal freightPrice = new BigDecimal(0);
        BigDecimal couponPrice = couponMapper.selectByPrimaryKey(Integer.parseInt(couponId)).getDiscount();


        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        //找出当前用户被选中的商品
        criteria.andUserIdEqualTo(userId).andCheckedEqualTo(true);

        checkedGoodsList = cartMapper.selectByExample(cartExample);

        for (Cart cart : checkedGoodsList) {
            //商品总价等于所有 被选中商品单价 乘上 被选中商品的数量 之和
            goodsTotalPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
        }
        //找到团购规则对应的团购折扣
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(Integer.parseInt(grouponRulesId));
        BigDecimal discount = grouponRules.getDiscount();

        Address address = addressMapper.selectByPrimaryKey(Integer.parseInt(addressId));


        wxCartCheckoutVo.setGrouponRulesId(Integer.parseInt(grouponRulesId));
        wxCartCheckoutVo.setGrouponPrice(discount);
        wxCartCheckoutVo.setAddress(address);
        wxCartCheckoutVo.setActualPrice(goodsTotalPrice.subtract(discount));
        //订单总价等于商品总价 + 运费 - 优惠金额 - 折扣金额
        wxCartCheckoutVo.setOrderTotalPrice(goodsTotalPrice.add(freightPrice).subtract(couponPrice).subtract(discount));
        wxCartCheckoutVo.setCouponPrice(couponPrice);
        wxCartCheckoutVo.setCouponId(Integer.parseInt(couponId));
        wxCartCheckoutVo.setCheckedGoodsList(checkedGoodsList);
        wxCartCheckoutVo.setGoodsTotalPrice(goodsTotalPrice);
        wxCartCheckoutVo.setAddressId(Integer.parseInt(addressId));

        return wxCartCheckoutVo;
    }
}
