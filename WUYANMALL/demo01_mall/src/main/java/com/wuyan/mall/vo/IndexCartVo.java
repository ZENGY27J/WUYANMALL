package com.wuyan.mall.vo;

import com.wuyan.mall.bean.Cart;

import java.util.List;
import java.util.Map;

public class IndexCartVo {
    List<Cart> cartList;

    Map<String, Object> cartTotal;

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public Map<String, Object> getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Map<String, Object> cartTotal) {
        this.cartTotal = cartTotal;
    }
}
