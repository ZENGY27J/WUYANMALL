package com.wuyan.mall.controller;

import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.service.goodsService.GoodsService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.ListGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: WUYANMALL
 * @Author: zyy
 * @Description: 商品控制层
 * @Date: 2019-09-30-11:53
 */
@RestController
@RequestMapping("admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("list")
    public BaseRespVo listGoods(HttpServletRequest request){
        String goodsSn=request.getParameter("goodsSn");
        String name=request.getParameter("name");
        String pageString = request.getParameter("page");
        String limitString = request.getParameter("limit");
        int page = Integer.parseInt(pageString);
        int limit = Integer.parseInt(limitString);
        if(goodsSn==null&&name==null) {
            BaseRespVo baseRespVo = goodsService.ListAllGoods(page, limit);
            return baseRespVo;
        }

        BaseRespVo baseRespVo=goodsService.ListGoodsBySnAndName(goodsSn,name,page,limit);
        return baseRespVo;
    }

}
