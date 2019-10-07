package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.Home;
import com.wuyan.wx.service.homeService.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    HomeService homeService;

    @RequestMapping("wx/goods/count")
    public BaseRespVo goodsCount(){
        long goodsCount =homeService.getGoodsCount();
        Map<String,Object> map = new HashMap<>();
        map.put("goodsCount",goodsCount);
        return BaseRespVo.ok(map);
    }
    @RequestMapping("wx/home/index")
    public BaseRespVo homeIndex(){
        Home home = homeService.getHomeIndex();
        return BaseRespVo.ok(home);
    }
}
