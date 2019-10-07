package com.wuyan.wx.service.goodsService;

import com.wuyan.mall.bean.Category;
import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.mapper.CategoryMapper;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.mapper.SearchHistoryMapper;
import com.wuyan.mall.util.MallUtil;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxGoodsServiceImpl implements WxGoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Override
    public Databean getGoodsList(PageInfo pageInfo) {
        List<Goods> goods = new ArrayList<>();
        long l = 0;
        if(pageInfo.getIsNew()) {
            l = goodsMapper.countByExample(QueryUtils.getNewGoods(0));
            goods = goodsMapper.selectByExample(QueryUtils.getNewGoods(pageInfo));
        }
        if(pageInfo.getIsHot()){
            l = goodsMapper.countByExample(QueryUtils.getHotGoods(0));
            goods = goodsMapper.selectByExample(QueryUtils.getHotGoods(pageInfo));
        }
        int categoryId = pageInfo.getCategoryId();
        if(categoryId != 0){
            l = goodsMapper.countByExample(QueryUtils.getGoodsByCategoryId(categoryId,0));
            goods = goodsMapper.selectByExample(QueryUtils.getGoodsByCategoryId(pageInfo));
        }
        String keyword = pageInfo.getKeyword();
        if(keyword != null && !"".equals(keyword)){
            goodsMapper.countByExample(QueryUtils.getGoodsByKeyword(keyword));
            goods = goodsMapper.selectByExample(QueryUtils.getGoodsByKeyword(pageInfo));
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setUserId(1);
            searchHistory.setFrom("wx");
            searchHistory.setKeyword(keyword);
            searchHistory.setAddTime(MallUtil.getNowTime());
            searchHistory.setUpdateTime(MallUtil.getNowTime());
            searchHistoryMapper.insert(searchHistory);
        }
        List<Category> categories = categoryMapper.selectByExample(QueryUtils.getCategory("L2"));
        Databean databean = new Databean();
        databean.setCount(l);
        databean.setFilterCategoryList(categories);
        databean.setGoodsList(goods);
        return databean;
    }

    @Override
    public CategoryBean getGoodsCategory(int id) {
        CategoryBean categoryBean = new CategoryBean();
        List<Category> categories = categoryMapper.selectByExample(QueryUtils.getCategoryById(id));
        Category category = categories.get(0);
        if (category.getPid() == 0){
            categoryBean.setParentCategory(category);
            List<Category> brother = categoryMapper.selectByExample(QueryUtils.getCategory(0, category.getId()));
            categoryBean.setCurrentCategory(brother.get(0));
            categoryBean.setBrotherCategory(brother);
        }else{
            categoryBean.setCurrentCategory(category);
            List<Category> parents = categoryMapper.selectByExample(QueryUtils.getCategoryById(category.getPid()));
            categoryBean.setParentCategory(parents.get(0));
            categoryBean.setBrotherCategory(categoryMapper.selectByExample(QueryUtils.getCategory(0,category.getPid())));
        }
        return categoryBean;
    }
}
