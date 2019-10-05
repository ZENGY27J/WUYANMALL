package com.wuyan.wx.controller;

import com.wuyan.mall.bean.Category;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.service.catalogService.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {
    @Autowired
    CatalogService catalogService;
    @RequestMapping("wx/catalog/index")
    public BaseRespVo catalogIndex(){
        CategoryBean categories = catalogService.getCatalogIndex();
        return BaseRespVo.ok(categories);
    }
    @RequestMapping("wx/catalog/current")
    public BaseRespVo catalogCurrent(int id){
       CategoryBean catalogCurrent  = catalogService.getCatalogCurrent(id);
       return BaseRespVo.ok(catalogCurrent);
    }
}
