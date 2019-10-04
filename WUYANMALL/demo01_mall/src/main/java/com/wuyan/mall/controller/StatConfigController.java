package com.wuyan.mall.controller;

import com.wuyan.mall.service.statService.GoodsStatService;
import com.wuyan.mall.service.statService.OrderStatService;
import com.wuyan.mall.service.statService.UserStatService;
import com.wuyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计报表的处理
 */

@RestController
@RequestMapping("admin/stat")
public class StatConfigController {

    @Autowired
    UserStatService userStatService;

    @Autowired
    OrderStatService orderStatService;

    @Autowired
    GoodsStatService goodsStatService;

    @RequestMapping("user")
    public BaseRespVo userStat() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        StatUserVo statUserVo= userStatService.statUser();

        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setData(statUserVo);
        return objectBaseRespVo;
    }

    @RequestMapping("order")
    public BaseRespVo orderStat() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        StatOrderVo statOrderVo = orderStatService.statOrder();

        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setData(statOrderVo);

        return objectBaseRespVo;
    }

    @RequestMapping("goods")
    public BaseRespVo goodsStat() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        StatGoodVo statGoodVo = goodsStatService.statGoods();
        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setData(statGoodVo);
        return objectBaseRespVo;
    }
}
