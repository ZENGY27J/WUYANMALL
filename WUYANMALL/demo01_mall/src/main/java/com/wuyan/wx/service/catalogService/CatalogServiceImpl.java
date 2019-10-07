package com.wuyan.wx.service.catalogService;

import com.wuyan.mall.bean.Category;
import com.wuyan.mall.mapper.CategoryMapper;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService{
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public CategoryBean getCatalogIndex() {
        List<Category> categories = categoryMapper.selectByExample(QueryUtils.getCategory(0,0));
        Category category = categories.get(0);
        List<Category> categories1 = categoryMapper.selectByExample(QueryUtils.getCategory(0, category.getPid()));
        CategoryBean categoryBean = new CategoryBean(categories, category, categories1);
        return categoryBean;
    }

    @Override
    public CategoryBean getCatalogCurrent(int id) {
        List<Category> categories = categoryMapper.selectByExample(QueryUtils.getCategoryById(id));
        Category category = categories.get(0);
        List<Category> categories1 = categoryMapper.selectByExample(QueryUtils.getCategory(0, category.getId()));
        return new CategoryBean(null,category,categories1);
    }
}
