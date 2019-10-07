package com.wuyan.wx.controller;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.service.commentSerivice.CommentService;
import com.wuyan.mall.vo.BaseRespVo;

import com.wuyan.wx.service.goodsService.WxGoodsServiceByZy;
import com.wuyan.wx.vo.CommentVo;
import com.wuyan.wx.vo.GoodsByCategoryIdVo;
import com.wuyan.wx.vo.GoodsDetail;
import com.wuyan.wx.vo.SpecificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
/*@RequestMapping("wx/goods")*/
public class WxGoodsControllerByZy {

    @Autowired
    BaseRespVo baseRespVo;

    @Autowired
    WxGoodsServiceByZy wxGoodsService;

    @Autowired
    CommentService commentService;

    /*
    * 商品页面：大家都在看
    *1. 根据goodsId查找到该商品
    *2，获取该商品的 categoryId
    *3.根据 categoryId在goods表中查询所有该类目下的商品
    *4. 将listgoods封装到ListGoodsBycategoryIdVo的goodsList中
    * */
    @RequestMapping("wx/goods/related")
    public BaseRespVo listGoodsByCategoryId(int id){
        //根据goodsid获取goods
        Goods goods = wxGoodsService.selectGoodsById(id);
        //根据goods的categgory_id获取该类目下所有的商品
        int categoryId=goods.getCategoryId();
        List<Goods> goodsList=wxGoodsService.selectListGoodsByCategoryId(categoryId);
        //返回
        GoodsByCategoryIdVo goodsByCategoryIdVo = new GoodsByCategoryIdVo();
        goodsByCategoryIdVo.setGoodsList(goodsList);
        return BaseRespVo.ok(goodsByCategoryIdVo);
    }

    /**
     * 根据goodsId查找商品相关信息
     * @param id
     * @return
     */
    @RequestMapping("wx/goods/detail")
    public BaseRespVo goodDetail (int id){

        //new 返回的类
        GoodsDetail goodsDetail = new GoodsDetail();

        //根据查找商品详情 info
        Goods goods=wxGoodsService.selectGoodsById(id);
        goodsDetail.setInfo(goods);

        //将goods中的分享图片赋给返回vo
        goodsDetail.setShareImage(goods.getShareUrl());

        //根据goods中的brandId查找制作商
        Brand brand = wxGoodsService.selectBrandById(goods.getBrandId());
        goodsDetail.setBrand(brand);

        //将issue表中的数据都取出来
        List<Issue> issues = wxGoodsService.listIssue();
        goodsDetail.setIssue(issues);

        //根据goodsId查询该商品的所有评价,并将评价总数赋给commentVo
        CommentVo commentVo = commentService.selectByGoodsId(goods.getId());
        goodsDetail.setComment(commentVo);

        //根据goodsId 获取拼团规则，从groupon_rules表中查询
        List<GrouponRules> grouponRules = wxGoodsService.selectGrouponByGoodsID(id);
        goodsDetail.setGroupon(grouponRules);

        //根据goodsId查找改商品的所有的product
        List<GoodsProduct> goodsProducts = wxGoodsService.selectProductListByGoodsId(id);
        goodsDetail.setProductList(goodsProducts);

        // 1. 根据goodsId 查询该商品的所有规格 list specification
        // 2. 遍历specification 将specification规格名都取出来存在set集合中
        // 2. 遍历第一步获取的specification ,遍历set集合，根据specification的不同放入不同的specificationVo集合中
        List<GoodsSpecification> goodsSpecifications = wxGoodsService.listSpecificationBygoodsId(id);
        HashSet<String> specificationName = new HashSet<>();
        for (GoodsSpecification specification:goodsSpecifications) {
            specificationName.add(specification.getSpecification());
        }
        ArrayList<SpecificationVo> specificationList = new ArrayList<>();
        for (String name:specificationName) {
            SpecificationVo specificationVo1 = new SpecificationVo();
            specificationVo1.setName(name);
            ArrayList<GoodsSpecification> goodsSpecifications1  = new ArrayList<>();
            for (GoodsSpecification specification:goodsSpecifications) {
                if (specification.getSpecification().equals(name)){
                   goodsSpecifications1.add(specification);
                }
            }
            specificationVo1.setValueList(goodsSpecifications1);
            specificationList.add(specificationVo1);
        }
        goodsDetail.setSpecificationList(specificationList);
        //根据goodsid获取该商品的attribute 集合
        List<GoodsAttribute> goodsAttributes = wxGoodsService.listAttribute(id);
        goodsDetail.setAttribute(goodsAttributes);
        //查询该用户是否已经收藏该商品,根据userId和valueId（goodsId）在
        /* 这里的userid先写死在代码中方便测试*/
        int userId=1;
        boolean flag = wxGoodsService.userHasCollect(id, userId);
        goodsDetail.setUserHasCollect(flag);

        return BaseRespVo.ok(goodsDetail);
    }


}
