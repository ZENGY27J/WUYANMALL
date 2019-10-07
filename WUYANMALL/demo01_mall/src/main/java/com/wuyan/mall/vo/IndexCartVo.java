package com.wuyan.mall.vo;

import com.wuyan.mall.bean.Cart;

import java.util.List;
import java.util.Map;

public class IndexCartVo {
    List<Cart> carts;

    Map<String, Object> cartTotal;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Map<String, Object> getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Map<String, Object> cartTotal) {
        this.cartTotal = cartTotal;
    }
}
