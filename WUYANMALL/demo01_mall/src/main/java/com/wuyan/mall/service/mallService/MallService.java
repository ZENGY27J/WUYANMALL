package com.wuyan.mall.service.mallService;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.mallBean.MallPage;
import com.wuyan.mall.vo.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallService {
    List<Region> getRegion();

    MallPage getBrand(PageInfo pageInfo);

    List<Category> getCategory();

    List<Category> getCategoryList();

    Category addCategory(Category category);

    void deleteCategory(Category category);

    MallPage getKeyword(PageInfo pageInfo);

    Keyword addKeyword(@Param("keyword") Keyword keyword);

    void deleteKeyword(Keyword keyword);

    Keyword updateKeyword(Keyword keyword);

    MallPage getOrder(PageInfo pageInfo);

    MallPage getIssue(PageInfo pageInfo);

    Issue addIssue(Issue issue);

    void deleteIssue(Issue issue);

    Issue updateIssue(Issue issue);

    Order getOrderById(int id);

    User getUserById(Integer userId);

    OrderGoods getOrderGoods(int id);
}
