package com.wuyan.mall.service.goodsService;

import com.wuyan.mall.bean.Accept.CatAndBrand;
import com.wuyan.mall.bean.Accept.CreateGoods;
import com.wuyan.mall.vo.BaseRespVo;


public interface GoodsService {
    BaseRespVo ListAllGoods(int page, int limit);
    BaseRespVo ListGoodsBySnAndName(String goodsSn, String name, int page, int limit);
    void createGoods(CreateGoods createGoods);
    BaseRespVo catAndBrand();
    BaseRespVo selectGoodsById(int id);
    void updateGoods(CreateGoods createGoods);
    BaseRespVo deleteGoodsById(int id );

}
