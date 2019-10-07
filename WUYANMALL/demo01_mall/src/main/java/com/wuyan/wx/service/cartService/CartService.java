package com.wuyan.wx.service.cartService;

import com.wuyan.mall.bean.Accept.AcceptCartChecks;
import com.wuyan.mall.bean.Cart;
import com.wuyan.mall.vo.IndexCartVo;

public interface CartService {
    IndexCartVo showCart(Integer userId);

    IndexCartVo checkGoods(AcceptCartChecks acceptCartChecks, Integer userId);

    void update(Cart cart, Integer userId);

    void insert(Cart cart, Integer userId);

    int showGoodsCount(Integer userId);
}
