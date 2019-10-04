package com.wuyan.mall.service.mallService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.mallBean.MallPage;
import com.wuyan.mall.mapper.*;
import com.wuyan.mall.util.MallUtil;
import com.wuyan.mall.vo.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Override
    public List<Region> getRegion() {
        List<Region> regions = regionMapper.selectByExample(MallUtil.getRegion(0));
        for (Region region : regions) {
            List<Region> regions1 = regionMapper.selectByExample(MallUtil.getRegion(region.getId()));
            for (Region region1 : regions1) {
                List<Region> regions2 = regionMapper.selectByExample(MallUtil.getRegion(region1.getId()));
                region1.setChildren(regions2);
            }
            region.setChildren(regions1);
        }
        return regions;
    }

    @Override
    public MallPage getBrand(PageInfo pageInfo) {
        MallUtil.pageHelper(pageInfo);
        List<Brand> brands = brandMapper.selectByExample(MallUtil.getBrandAll(pageInfo.getId(), pageInfo.getName()));
        long l = brandMapper.countByExample(MallUtil.getBrandAll(pageInfo.getId(), pageInfo.getName()));
        return new MallPage(l,brands);
    }

    @Override
    public Brand addBrand(Brand brand) {
        brand.setAddTime(MallUtil.getNowTime());
        brand.setUpdateTime(MallUtil.getNowTime());
        brandMapper.insert(brand);
        List<Brand> brands = brandMapper.selectByExample(MallUtil.getBrandByName(brand.getName()));
        return brands.get(0);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        brand.setUpdateTime(MallUtil.getNowTime());
        brandMapper.updateByExample(brand,MallUtil.getBrandByName(brand.getName()));
        return brand;
    }

    @Override
    public void deleteBrand(Brand brand) {
        brandMapper.deleteByExample(MallUtil.getBrandByName(brand.getName()));
    }

    @Override
    public List<Category> getCategory() {
        return categoryMapper.selectAll(0);
    }

    @Override
    public List<Category> getCategoryList() {
        List<Category> categories = categoryMapper.selectByExample(MallUtil.getCategory(0));
        for (Category category : categories) {
            List<Category> categories1 = categoryMapper.selectByExample(MallUtil.getCategory(category.getId()));
            category.setChildren(categories1);
        }
        return categories;
    }

    @Override
    public Category addCategory(Category category) {
        category.setAddTime(MallUtil.getNowTime());
        category.setUpdateTime(MallUtil.getNowTime());
        categoryMapper.insert(category);
        return (Category) categoryMapper.selectByExample(MallUtil.getCategory(category.getName()));
    }

    @Override
    public void deleteCategory(Category category) {
        List<Category> children = category.getChildren();
        for (Category child : children) {
            categoryMapper.deleteByExample(MallUtil.getCategoryById(child.getId()));
        }
        categoryMapper.deleteByExample(MallUtil.getCategoryById(category.getId()));
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(MallUtil.getNowTime());
        categoryMapper.updateByExample(category,MallUtil.getCategoryById(category.getId()));
    }

    @Override
    public MallPage getOrder(PageInfo pageInfo) {
        MallUtil.pageHelper(pageInfo);
        List<Order> orders = orderMapper.selectByExample(MallUtil.getOrder(pageInfo));
        long l = orderMapper.countByExample(MallUtil.getOrder(pageInfo));
        return new MallPage(l,orders);
    }

    @Override
    public Order getOrderById(int id) {
        List<Order> orders = orderMapper.selectByExample(MallUtil.getOrderById(id));
        return orders.get(0);
    }

    @Override
    public User getUserById(Integer userId) {
        List<User> users = userMapper.selectByExample(MallUtil.getUser(userId));
        return users.get(0);
    }

    @Override
    public OrderGoods getOrderGoods(int id) {
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(MallUtil.getOrderGoods(id));
        return orderGoods.get(0);
    }

    @Override
    public MallPage getIssue(PageInfo pageInfo) {
        MallUtil.pageHelper(pageInfo);
        List<Issue> issues = issueMapper.selectByExample(MallUtil.getIssue(pageInfo.getQuestion()));
        long l = issueMapper.countByExample(MallUtil.getIssue(pageInfo.getQuestion()));
        return new MallPage(l,issues);
    }

    @Override
    public Issue addIssue(Issue issue) {
        issue.setAddTime(MallUtil.getNowTime());
        issue.setUpdateTime(MallUtil.getNowTime());
        issueMapper.insert(issue);
        List<Issue> issues = issueMapper.selectByExample(MallUtil.getIssue(issue.getQuestion()));
        return issues.get(0);
    }

    @Override
    public void deleteIssue(Issue issue) {
        issueMapper.deleteByExample(MallUtil.getIssueById(issue.getId()));
    }

    @Override
    public Issue updateIssue(Issue issue) {
        issue.setUpdateTime(MallUtil.getNowTime());
        issueMapper.updateByExample(issue, MallUtil.getIssueById(issue.getId()));
        return issue;
    }

    @Override
    public MallPage getKeyword(PageInfo pageInfo) {
        MallUtil.pageHelper(pageInfo);
        String url = pageInfo.getUrl();
        String keyword = pageInfo.getKeyword();
        List<Keyword> keywords = keywordMapper.selectByExample(MallUtil.getKeyword(url,keyword));
        long l = keywordMapper.countByExample(MallUtil.getKeyword(url,keyword));
        return new MallPage(l,keywords);
    }

    @Override
    public Keyword addKeyword(Keyword keyword) {
        keyword.setAddTime(MallUtil.getNowTime());
        keyword.setUpdateTime(MallUtil.getNowTime());
        keywordMapper.insert(keyword);
        List<Keyword> keywords = keywordMapper.selectByExample(MallUtil.getKeywordByKeyword(keyword.getKeyword()));
        return keywords.get(0);
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        keywordMapper.deleteByExample(MallUtil.getKeywordByKeyword(keyword.getKeyword()));
    }

    @Override
    public Keyword updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(MallUtil.getNowTime());
        keywordMapper.updateByExample(keyword,MallUtil.getKeywordById(keyword.getId()));
        return keyword;
    }
}
