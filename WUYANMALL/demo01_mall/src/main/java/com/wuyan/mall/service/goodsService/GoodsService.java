package com.wuyan.mall.service.goodsService;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.ListGoodsVo;

import java.util.List;

public interface GoodsService {
    BaseRespVo ListAllGoods(int page, int limit);
    BaseRespVo ListGoodsBySnAndName(String goodsSn, String name, int page, int limit);
}
