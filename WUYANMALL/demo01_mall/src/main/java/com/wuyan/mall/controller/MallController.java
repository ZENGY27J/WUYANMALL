package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Category;
import com.wuyan.mall.bean.Region;
import com.wuyan.mall.bean.mallBean.MallBrand;
import com.wuyan.mall.service.mallService.MallService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商场管理相关模块
 */
@RestController
public class MallController {
    @Autowired
    MallService mallService;

    /**
     * 行政管理模块
     * @return 返回行政信息
     */
    @RequestMapping("admin/region/list")
    public BaseRespVo regionList(){
        List<Region> regions = mallService.getRegion();
        return BaseRespVo.ok(regions);
    }

    /**
     * 分页显示品牌制造商
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/brand/list")
    public BaseRespVo brandList(PageInfo pageInfo){
        MallBrand brands =mallService.getBrand(pageInfo);
        return BaseRespVo.ok(brands);
    }

    /**
     * 展示一级商品商品类目
     * @return
     */
    @RequestMapping("admin/category/l1")
    public BaseRespVo categoryL1(){
        List<Category> category = mallService.getCategory();
    return BaseRespVo.ok(category);
    }

    /**
     * 展示所有商品类目
     * @return
     */
    @RequestMapping("admin/category/list")
    public BaseRespVo categoryList(){
        List<Category> categories = mallService.getCategoryList();
        return BaseRespVo.ok(categories);
    }

    @RequestMapping("admin/category/create")
    public BaseRespVo categoryCreate(Category category){
        Category category1 = mallService.addCategory(category);
        return BaseRespVo.ok(category1);
    }

    /**
     * 删除指定的类目
     * @param category
     * @return
     */
    @RequestMapping("admin/category/delete")
    public BaseRespVo categoryDelete(@RequestBody Category category){
        mallService.deleteCategory(category);
        return BaseRespVo.ok(null);
    }

    @RequestMapping("admin/keyword/list")
    public BaseRespVo keywordList(PageInfo pageInfo){
        return null;
    }
}

