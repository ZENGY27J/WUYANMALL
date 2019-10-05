package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.service.catalogService.WxCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/catalog")
public class CatalogController {
    @Autowired
    WxCatalogService catalogService;
    @RequestMapping("index")
    public BaseRespVo catalogIndex(){
        CategoryBean categories = catalogService.getCatalogIndex();
        return BaseRespVo.ok(categories);
    }
    @RequestMapping("current")
    public BaseRespVo catalogCurrent(int id){
       CategoryBean catalogCurrent  = catalogService.getCatalogCurrent(id);
       return BaseRespVo.ok(catalogCurrent);
    }
}
