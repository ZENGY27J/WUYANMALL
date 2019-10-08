package com.wuyan.wx.service.cartService;

import com.wuyan.mall.bean.Accept.AcceptCartChecks;
import com.wuyan.mall.bean.Cart;
import com.wuyan.mall.vo.IndexCartVo;

public interface CartService {
    IndexCartVo showCart(Integer userId);//显示购物车

    IndexCartVo checkGoods(AcceptCartChecks acceptCartChecks, Integer userId);//选择购物车的商品

    void update(Cart cart, Integer userId);//更新购物车

    void insert(Cart cart, Integer userId);//加入购物车

    int showGoodsCount(Integer userId);//显示购物车商品数量

    void checkoutGoods(String cartId, String addressId, String couponId, String grouponRulesId, int userId);//下单

    IndexCartVo delete(Integer[] productIds, int userId);
}
