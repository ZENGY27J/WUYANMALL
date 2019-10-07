package com.wuyan.wx.controller;

<<<<<<< HEAD
import com.wuyan.mall.bean.Category;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.service.catalogService.CatalogService;
=======
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.service.catalogService.WxCatalogService;
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
<<<<<<< HEAD
public class CatalogController {
    @Autowired
    CatalogService catalogService;
    @RequestMapping("wx/catalog/index")
=======
@RequestMapping("wx/catalog")
public class CatalogController {
    @Autowired
    WxCatalogService catalogService;
    @RequestMapping("index")
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
    public BaseRespVo catalogIndex(){
        CategoryBean categories = catalogService.getCatalogIndex();
        return BaseRespVo.ok(categories);
    }
<<<<<<< HEAD
    @RequestMapping("wx/catalog/current")
=======
    @RequestMapping("current")
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
    public BaseRespVo catalogCurrent(int id){
       CategoryBean catalogCurrent  = catalogService.getCatalogCurrent(id);
       return BaseRespVo.ok(catalogCurrent);
    }
}
