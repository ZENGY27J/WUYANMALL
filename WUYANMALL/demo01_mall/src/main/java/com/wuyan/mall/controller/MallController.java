package com.wuyan.mall.controller;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.mallBean.MallPage;
import com.wuyan.mall.service.mallService.MallService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商场管理相关模块
 */
@RestController
public class MallController {
    String s = "^[0-9]*$";
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
     * 显示品牌制造商
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/brand/list")
    public BaseRespVo brandList(PageInfo pageInfo){
        if(pageInfo.getId() != null && !"".equals(pageInfo.getId())){
            if (!pageInfo.getId().matches(s)){
                return BaseRespVo.error(null);
            }
        }
        MallPage brands =mallService.getBrand(pageInfo);
        return BaseRespVo.ok(brands);
    }

    /**
     * 增加品牌制造商
     * @param brand
     * @return
     */
    @RequestMapping("admin/brand/create")
    public BaseRespVo addBrand(@RequestBody Brand brand){
        Brand brand1 = mallService.addBrand(brand);
        return BaseRespVo.ok(brand1);
    }

    /**
     * 修改品牌商信息
     * @param brand
     * @return
     */
    @RequestMapping("admin/brand/update")
    public BaseRespVo updateBrand(@RequestBody Brand brand){
        Brand brand1 = mallService.updateBrand(brand);
        return BaseRespVo.ok(brand1);
    }

    /**
     * 删除指定品牌商
     * @param brand
     * @return
     */
    @RequestMapping("admin/brand/delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand){
        mallService.deleteBrand(brand);
        return BaseRespVo.ok(null);
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

    /**
     * 增加商品类目
     * @param category
     * @return
     */
    @RequestMapping("admin/category/create")
    public BaseRespVo categoryCreate(Category category){
        Category category1 = mallService.addCategory(category);
        return BaseRespVo.ok(category1);
    }

    /**
     * 更新商品类目信息
     * @param category
     * @return
     */
    @RequestMapping("admin/category/update")
    public BaseRespVo updateCategory(@RequestBody Category category){
        mallService.updateCategory(category);
        return BaseRespVo.ok(null);
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

    /**
     * 获取订单相关信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/order/list")
    public BaseRespVo orderList(PageInfo pageInfo){
        if (pageInfo.getUserId() != null && !"".equals(pageInfo.getUserId())){
            if (!pageInfo.getUserId().matches(s)){
                return BaseRespVo.error(null);
            }
        }
        MallPage orders = mallService.getOrder(pageInfo);
        return BaseRespVo.ok(orders);
    }

    /**
     * 显示订单详情
     * @param id
     * @return
     */
    @RequestMapping("admin/order/detail")
    public BaseRespVo orderDetail(int id){
        Order order = mallService.getOrderById(id);
        User user = mallService.getUserById(order.getUserId());
        OrderGoods orderGoods = mallService.getOrderGoods(id);
        Map<String,Object> map = new HashMap<>();
        map.put("order",order);
        map.put("user",user);
        map.put("orderGoods",orderGoods);
        return BaseRespVo.ok(map);
    }
    /**
     * 获取通用问题相关信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/issue/list")
    public BaseRespVo issueList(PageInfo pageInfo){
        MallPage mallPage = mallService.getIssue(pageInfo);
        return BaseRespVo.ok(mallPage);
    }

    /**
     * 添加通用问题
     * @param issue
     * @return
     */
    @RequestMapping("admin/issue/create")
    public BaseRespVo addIssue(@RequestBody Issue issue){
        Issue issue1 = mallService.addIssue(issue);
        return BaseRespVo.ok(issue1);
    }

    /**
     * 修改通用问题信息
     * @param issue
     * @return
     */
    @RequestMapping("admin/issue/update")
    public BaseRespVo updateIssue(@RequestBody Issue issue){
        Issue issue1 = mallService.updateIssue(issue);
        return BaseRespVo.ok(issue1);
    }
    /**
     * 删除指定的通用问题
     * @param issue
     * @return
     */
    @RequestMapping("admin/issue/delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue){
        mallService.deleteIssue(issue);
        return BaseRespVo.ok(null);
    }
    /**
     * 显示所有关键词信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/keyword/list")
    public BaseRespVo keywordList(PageInfo pageInfo){
        MallPage mallKeyword = mallService.getKeyword(pageInfo);
        return BaseRespVo.ok(mallKeyword);
    }

    /**
     * 添加关键词
     * @param keyword
     * @return
     */
    @RequestMapping("admin/keyword/create")
    public BaseRespVo addKeyword(@RequestBody Keyword keyword){
        Keyword keyword1 = mallService.addKeyword(keyword);
        return BaseRespVo.ok(keyword1);
    }

    /**
     * 更新关键词信息
     * @param keyword
     * @return
     */
    @RequestMapping("admin/keyword/update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword){
       Keyword keyword1 = mallService.updateKeyword(keyword);
        return BaseRespVo.ok(keyword1);
    }

    /**
     * 删除指定关键词
     * @param keyword
     * @return
     */
    @RequestMapping("admin/keyword/delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword){
        mallService.deleteKeyword(keyword);
        return BaseRespVo.ok(null);
    }
}

