package com.wuyan.wx.controller;

import com.wuyan.mall.bean.Accept.AcceptCartChecks;
import com.wuyan.mall.bean.Cart;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.IndexCartVo;
import com.wuyan.wx.service.cartService.CartService;
import com.wuyan.wx.utils.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("wx/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("/index")
    public BaseRespVo showCart(HttpServletRequest request) {
        /*String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        //IndexCartVo类对应/wx/cart/index 接口需要返回的对象
        int userId = 1;
        IndexCartVo indexCartVo = cartService.showCart(userId);

        return BaseRespVo.ok(indexCartVo);
    }

    @RequestMapping("goodscount")
    //展示购物车里面商品的数量
    public BaseRespVo showGoodsCountInCart(HttpServletRequest request) {
        /*String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        int userId = 1;
        int size = cartService.showGoodsCount(userId);

        return BaseRespVo.ok(size);
    }

    @RequestMapping("checked")
    //勾选购物车中的商品
    public BaseRespVo checkGoods(AcceptCartChecks acceptCartChecks, HttpServletRequest request) {
       /* String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        int userId = 1;
        IndexCartVo indexCartVo = cartService.checkGoods(acceptCartChecks, userId);
        return BaseRespVo.ok(indexCartVo);
    }

    @RequestMapping("update")
    //更新购物车里面商品数量
    public BaseRespVo updateGoods(Cart cart, HttpServletRequest request) {
        /*String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        int userId = 1;
        cartService.update(cart, userId);
        return BaseRespVo.ok(null);
    }

    @RequestMapping("add")
    //加入购物车
    public BaseRespVo addGoodsToCart(Cart cart, HttpServletRequest request) {
        /*String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        int userId = 1;
        cartService.insert(cart, userId);
        return BaseRespVo.ok(cartService.showGoodsCount(userId));
    }

    @RequestMapping("checkout")
    //下单接口
    public BaseRespVo checkOutCart(String cartId, String addressId, String couponId, String grouponRulesId, HttpServletRequest request) {
         /*String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        int userId = 1;

        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("系统内部错误");
        baseRespVo.setErrno(502);
        return baseRespVo;
    }
}