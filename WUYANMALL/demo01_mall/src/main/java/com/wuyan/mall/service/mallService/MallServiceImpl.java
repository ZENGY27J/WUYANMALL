package com.wuyan.mall.service.mallService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.bean.Category;
import com.wuyan.mall.bean.Region;
import com.wuyan.mall.bean.mallBean.MallBrand;
import com.wuyan.mall.mapper.BrandMapper;
import com.wuyan.mall.mapper.CategoryMapper;
import com.wuyan.mall.mapper.RegionMapper;
import com.wuyan.mall.util.MallUtil;
import com.wuyan.mall.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
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
    public MallBrand getBrand(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getLimit());
        String order ="`" + pageInfo.getOrder() + "`";
        PageHelper.orderBy(order);
        long l = 0;
        int id;
        List<Brand> brands;
        if(pageInfo.getId() != null && pageInfo.getId() != ""){
            id = Integer.parseInt(pageInfo.getId());
        }else{
            id = 0;
        }
        String name = pageInfo.getName();
        if(id == 0 && name == null){
            brands = brandMapper.selectByExample(MallUtil.getBrandAll(id,name,false));
            l = brandMapper.countByExample(MallUtil.getBrandAll(id,name,false));
        }else {
            brands = brandMapper.selectByExample(MallUtil.getBrandAll(id,name,true));
            l = brandMapper.countByExample(MallUtil.getBrandAll(id,name,true));
        }
        MallBrand mallBrand = new MallBrand();
        mallBrand.setTotal(l);
        mallBrand.setItems(brands);
        return mallBrand;
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
        categoryMapper.insert(category);
        return (Category) categoryMapper.selectByExample(MallUtil.getCategory(category.getName()));
    }

    @Override
    public void deleteCategory(Category category) {
        categoryMapper.deleteByExample(MallUtil.getCategoryById(category.getId()));
    }
}
