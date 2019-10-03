package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Accept.CreateGoods;
import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.GoodsAttribute;
import com.wuyan.mall.bean.GoodsSpecification;
import com.wuyan.mall.service.goodsService.GoodsService;
import com.wuyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    BaseRespVo baseRespVo;

    /*
    * 商品列表和查询商品
    * */
    @RequestMapping("list")
    public BaseRespVo listGoods(HttpServletRequest request) {
        String goodsSn = request.getParameter("goodsSn");
        String name = request.getParameter("name");
        String pageString = request.getParameter("page");
        String limitString = request.getParameter("limit");
        int page = Integer.parseInt(pageString);
        int limit = Integer.parseInt(limitString);
        if (goodsSn == null && name == null) {
             baseRespVo = goodsService.ListAllGoods(page, limit);
            return baseRespVo;
        }

        baseRespVo = goodsService.ListGoodsBySnAndName(goodsSn, name, page, limit);
        return baseRespVo;
    }
    /*
    * 新增goods
    * */
    @RequestMapping("create")
    public BaseRespVo createGoods(@RequestBody CreateGoods createGoods){
        //参数效验
        if (parCheck(createGoods)) {
            goodsService.createGoods(createGoods);
            baseRespVo.setErrmsg("成功");
            baseRespVo.setErrno(0);
            return baseRespVo;
        }
        baseRespVo.setErrno(401);
        baseRespVo.setErrmsg("参数不对");
        return baseRespVo;
    }

    /*
    * 进入修改页面，查询 制造商 和 类名
    * */
    @RequestMapping("catAndBrand")
    public BaseRespVo getCatAndBrand(){
        BaseRespVo baseRespVo1 = goodsService.catAndBrand();
        return baseRespVo1;
    }
    /*
    * 进入修改页面根据goodsid获取到该goods
    * */
    @RequestMapping("detail")
    public BaseRespVo getGoodsById(int id){
        baseRespVo=goodsService.selectGoodsById(id);
        return  baseRespVo;
    }
    /*
    * 修改goods
    * */
    @RequestMapping("update")
    public  BaseRespVo updateGoods(@RequestBody  CreateGoods createGoods){
        //参数效验
        if (parCheck(createGoods)) {
            goodsService.updateGoods(createGoods);
            baseRespVo.setErrmsg("成功");
            baseRespVo.setErrno(0);
            return baseRespVo;
        }
        baseRespVo.setErrno(401);
        baseRespVo.setErrmsg("参数不对");
        return baseRespVo;
    }
    /*
    *删除goods
    * */
    @RequestMapping("delete")
    public BaseRespVo deleteGoods(@RequestBody Goods goods){
        baseRespVo=goodsService.deleteGoodsById(goods.getId());
        return baseRespVo;
    }

    //参数校验
    public static boolean  parCheck(CreateGoods createGoods){
        for (GoodsSpecification specification:createGoods.getSpecifications()){
            if (!"".equals(specification.getSpecification()) && specification.getSpecification()!=null){
                if (specification.getValue()==null || "".equals(specification.getValue()) ){
                   /* baseRespVo.setErrno(401);
                    baseRespVo.setErrmsg("参数不对");*/
                    return false;
                }
            }
        }
        //参数校验
        for (GoodsAttribute goodsAttribute:createGoods.getAttributes()){
            if (!"".equals(goodsAttribute.getAttribute()) && goodsAttribute.getAttribute()!=null){
                if ("".equals(goodsAttribute.getValue()) || goodsAttribute.getValue()==null ){
                    /*baseRespVo.setErrno(401);
                    baseRespVo.setErrmsg("参数不对");*/
                    return false;
                }
            }
        }
        return true;
    }
}

