package com.wuyan.mall.service.goodsService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.GoodsExample;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.ListGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 查找所有的商品并返回
     * @param page
     * @param limit
     * @return BaseRespVo
     */
   @Override
    public BaseRespVo ListAllGoods(int page, int limit) {
       //新建view Object
       ListGoodsVo<Object> listGoodsVo = new ListGoodsVo<>();
       BaseRespVo baseRespVo=new BaseRespVo();

        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria criteria=goodsExample.createCriteria();

       //按照插入的时间排序
        goodsExample.setOrderByClause("add_time desc");
        //分页
        PageHelper.startPage(page,limit);
        //查询所有的goods
        List<Goods> allgoods=goodsMapper.selectByExample(goodsExample);
        //查询goods的总数
       PageInfo<Goods> goodsPageInfo=new PageInfo<>(allgoods);
       long total=goodsPageInfo.getTotal();

       //给view goods 赋值
       listGoodsVo.setTotal(total);
       listGoodsVo.setItems(allgoods);
        //给baseRespVo赋值
       baseRespVo.setData(listGoodsVo);
       baseRespVo.setErrno(0);
       baseRespVo.setErrmsg("成功");

        return baseRespVo;
    }

    /**
     * 根据name,goodsSn查找商品
     * @param goodsSn
     * @param name
     * @param page
     * @param limit
     * @return BaseRespVo
     */
    @Override
    public BaseRespVo ListGoodsBySnAndName(String goodsSn, String name, int page, int limit) {
        //新建view Object
        ListGoodsVo<Object> listGoodsVo = new ListGoodsVo<>();
        BaseRespVo baseRespVo=new BaseRespVo();

        //查找
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria criteria=goodsExample.createCriteria();

        /*
        //判断goodsSn和name是否为空
        if (name != null && !("".equals(name))) {
            criteria.andNameLike("%"+name+"%");
        }
        if (goodsSn != null && !("".equals(goodsSn))){
            criteria.andGoodsSnLike("%"+goodsSn+"%");
        }*/

        criteria.andNameLike("%"+name+"%").andGoodsSnLike("%"+goodsSn+"%");
        // 分页
        PageHelper.startPage(page,limit);
        // 按照插入的时间排序
        goodsExample.setOrderByClause("add_time desc");
        // 查找goods存储在list中
        List<Goods> goodsList=goodsMapper.selectByExample(goodsExample);

        // 查询goods的总数
        PageInfo<Goods> goodsPageInfo=new PageInfo<>(goodsList);
        long total=goodsPageInfo.getTotal();

        // 给view goods 赋值
        listGoodsVo.setTotal(total);
        listGoodsVo.setItems(goodsList);

        // 给baseRespVo 赋值
        baseRespVo.setData(listGoodsVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");

        return baseRespVo;
    }
}
