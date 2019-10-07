package com.wuyan.wx.service.goodsService;

import com.wuyan.mall.bean.*;

import java.util.List;

public interface WxGoodsServiceByZy {
   Goods selectGoodsById(int goodsId);
   List<Goods> selectListGoodsByCategoryId(int categoryId);
   Brand selectBrandById(int brandId);
   List<Issue> listIssue();
   List<GrouponRules> selectGrouponByGoodsID(int goodsId);
   List<GoodsProduct> selectProductListByGoodsId(int goodsId);
   List<GoodsSpecification> listSpecificationBygoodsId(int goodsId);
   List<GoodsAttribute> listAttribute(int goodsId);
   boolean userHasCollect(int goodsId,int userId);
}
