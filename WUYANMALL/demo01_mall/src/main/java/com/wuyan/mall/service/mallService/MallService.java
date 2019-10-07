package com.wuyan.mall.service.mallService;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.mallBean.MallPage;
import com.wuyan.mall.vo.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallService {
    List<Region> getRegion();

    MallPage getBrand(PageInfo pageInfo);

    Brand addBrand(Brand brand);

    Brand updateBrand(Brand brand);

    void deleteBrand(Brand brand);

    List<Category> getCategory();

    List<Category> getCategoryList();

    Category addCategory(Category category);

    void deleteCategory(Category category);

    void updateCategory(Category category);

    MallPage getOrder(PageInfo pageInfo);

    Order getOrderById(int id);

    User getUserById(Integer userId);

    OrderGoods getOrderGoods(int id);

    MallPage getIssue(PageInfo pageInfo);

    Issue addIssue(Issue issue);

    void deleteIssue(Issue issue);

    Issue updateIssue(Issue issue);

    MallPage getKeyword(PageInfo pageInfo);

    Keyword addKeyword(@Param("keyword") Keyword keyword);

    void deleteKeyword(Keyword keyword);

    Keyword updateKeyword(Keyword keyword);

    List<Brand> getBrand();
}
