package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.service.goodsService.WxGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {
    @Autowired
    WxGoodsService wxGoodsService;
    @RequestMapping("list")
    public BaseRespVo getGoodsList(PageInfo pageInfo){
        Databean databean = wxGoodsService.getGoodsList(pageInfo);
        return BaseRespVo.ok(databean);
    }
    @RequestMapping("category")
    public BaseRespVo getGoodsCategory(int id){
        CategoryBean categoryBean = wxGoodsService.getGoodsCategory(id);
        return BaseRespVo.ok(categoryBean);
    }
}
