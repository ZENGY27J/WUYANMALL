package com.wuyan.mall.controller;

import com.wuyan.mall.service.configService.HomePageService;
import com.wuyan.mall.service.configService.HomePageServicedImpl;
import com.wuyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    @RequestMapping("admin/dashboard")
    public BaseRespVo dashboard() {
        Long goodsTotal = homePageService.getgoodsTotal();
        Long productTotal = homePageService.getProductTotal();
        Long orderTotal = homePageService.getOrderTotal();
        Long userTotal = homePageService.getUserTotal();

        HashMap<String, Long> map = new HashMap<>();
        map.put("goodsTotal", goodsTotal);
        map.put("productTotal", productTotal);
        map.put("orderTotal", orderTotal);
        map.put("userTotal", userTotal);

        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setData(map);

        return objectBaseRespVo;
    }
}
