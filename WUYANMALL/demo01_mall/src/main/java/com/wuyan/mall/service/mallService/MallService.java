package com.wuyan.mall.service.mallService;

import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.bean.Category;
import com.wuyan.mall.bean.Region;
import com.wuyan.mall.bean.mallBean.MallBrand;
import com.wuyan.mall.vo.PageInfo;

import java.util.List;

public interface MallService {
    List<Region> getRegion();

    MallBrand getBrand(PageInfo pageInfo);

    List<Category> getCategory();

    List<Category> getCategoryList();

    Category addCategory(Category category);

    void deleteCategory(Category category);
}
