package com.wuyan.mall.service.goodsService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.Accept.Cat;
import com.wuyan.mall.bean.Accept.CatAndBrand;
import com.wuyan.mall.bean.Accept.CreateGoods;
import com.wuyan.mall.mapper.*;
import com.wuyan.mall.service.mallService.MallService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.ListGoodsVo;
import com.wuyan.mall.vo.SingleGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    MallService mallService;

    @Autowired
    GoodsAttributeMapper attributeMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsSpecificationMapper specificationMapper;

    @Autowired
    GoodsProductMapper productMapper;

    /**
     * 查找所有的商品并返回
     *
     * @param page
     * @param limit
     * @return BaseRespVo
     */
    @Override
    public BaseRespVo ListAllGoods(int page, int limit) {
        //新建view Object
        ListGoodsVo<Object> listGoodsVo = new ListGoodsVo<>();
        BaseRespVo baseRespVo = new BaseRespVo();

        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        //按照插入的时间排序
        goodsExample.setOrderByClause("add_time desc");
        //分页
        PageHelper.startPage(page, limit);
        //查询所有的goods
        List<Goods> allgoods = goodsMapper.selectByExample(goodsExample);
        //查询goods的总数
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(allgoods);
        long total = goodsPageInfo.getTotal();

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
     *
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
        BaseRespVo baseRespVo = new BaseRespVo();

        //查找
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        //判断goodsSn和name是否为空
        if (name != null && !("".equals(name))) {
            criteria.andNameLike("%"+name+"%");
        }
        if (goodsSn != null && !("".equals(goodsSn))){
            criteria.andGoodsSnLike("%"+goodsSn+"%");
        }

     /*   criteria.andNameLike("%" + name + "%").andGoodsSnLike("%" + goodsSn + "%");*/
        // 分页
        PageHelper.startPage(page, limit);
        // 按照插入的时间排序
        goodsExample.setOrderByClause("add_time desc");
        // 查找goods存储在list中
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

        // 查询goods的总数
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        long total = goodsPageInfo.getTotal();

        // 给view goods 赋值
        listGoodsVo.setTotal(total);
        listGoodsVo.setItems(goodsList);

        // 给baseRespVo 赋值
        baseRespVo.setData(listGoodsVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");

        return baseRespVo;
    }


    /*
    * 新建商品
    * */
    @Override
    public void createGoods(CreateGoods createGoods) {
        //goods
        Date date = new Date();
        createGoods.getGoods().setAddTime(date);
        createGoods.getGoods().setUpdateTime(date);
        goodsMapper.insert(createGoods.getGoods());
        int goodsId=createGoods.getGoods().getId();


        //specification
        for (GoodsSpecification goodsSpecification : createGoods.getSpecifications()) {
            goodsSpecification.setAddTime(date);
            goodsSpecification.setUpdateTime(date);
            goodsSpecification.setGoodsId(goodsId);
            specificationMapper.insert(goodsSpecification);

        }

        //product
       for (GoodsProduct goodsProduct : createGoods.getProducts()) {
           goodsProduct.setAddTime(date);
           goodsProduct.setUpdateTime(date);
           goodsProduct.setGoodsId(goodsId);
           productMapper.insert(goodsProduct);

       }
        //attributes
        for (GoodsAttribute attribute : createGoods.getAttributes()) {
            attribute.setAddTime(date);
            attribute.setUpdateTime(date);
            attribute.setGoodsId(goodsId);
            attributeMapper.insert(attribute);
        }

    }

    @Override
    public BaseRespVo catAndBrand() {
        CategoryExample categoryExampleL1 = new CategoryExample();
        categoryExampleL1.createCriteria().andLevelEqualTo("L1");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExampleL1);
        ArrayList<Cat> cats = new ArrayList<>();
        for (Category category : categoryList) {
            Cat cat = new Cat();
            cat.setValue(category.getId());
            cat.setLabel(category.getName());
            Integer id = category.getId();
            CategoryExample categoryExampleL2 = new CategoryExample();
            categoryExampleL2.createCriteria().andPidEqualTo(id);
            List<Category> categoryListL2 = categoryMapper.selectByExample(categoryExampleL2);
            ArrayList<Cat> childList = new ArrayList<>();
            for (Category category1 : categoryListL2) {
                Cat cat1 = new Cat();
                cat1.setValue(category1.getId());
                cat1.setLabel(category1.getName());
                childList.add(cat1);
            }
            cat.setChildren(childList);
            cats.add(cat);
        }
        CatAndBrand catAndBrand = new CatAndBrand();
        catAndBrand.setCategoryList(cats);
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria();
        List<Brand> list = brandMapper.selectByExample(brandExample);
        ArrayList<Cat> brandList = new ArrayList<>();
        for (Brand brand : list) {
            Cat cat = new Cat();
            cat.setValue(brand.getId());
            cat.setLabel(brand.getName());
            brandList.add(cat);
        }
        catAndBrand.setBrandList(brandList);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(catAndBrand);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @Override
    public BaseRespVo selectGoodsById(int id) {
        SingleGoodsVo singleGoodsVo = new SingleGoodsVo();
        BaseRespVo baseRespVo = new BaseRespVo();
        //categros
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        Integer categorypid = categoryMapper.selectByPrimaryKey(goods.getCategoryId()).getPid();
        int[] categros = {categorypid, goods.getCategoryId()};
        singleGoodsVo.setCategoryIds(categros);

        //goods
        singleGoodsVo.setGoods(goodsMapper.selectByPrimaryKey(id));

        //goodsSpecification
        GoodsSpecificationExample specificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = specificationExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsMapper.selectByPrimaryKey(id).getId());
        List<GoodsSpecification> goodsSpecifications = specificationMapper.selectByExample(specificationExample);
        singleGoodsVo.setSpecifications(goodsSpecifications);

        //products
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria1 = goodsProductExample.createCriteria();
        criteria1.andGoodsIdEqualTo(goodsMapper.selectByPrimaryKey(id).getId());
        List<GoodsProduct> goodsProducts = productMapper.selectByExample(goodsProductExample);
        singleGoodsVo.setProducts(goodsProducts);

        //attributes
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria2 = goodsAttributeExample.createCriteria();
        criteria2.andGoodsIdEqualTo(goodsMapper.selectByPrimaryKey(id).getId());
        List<GoodsAttribute> goodsAttributes = attributeMapper.selectByExample(goodsAttributeExample);
        singleGoodsVo.setAttributes(goodsAttributes);

        baseRespVo.setData(singleGoodsVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");

        return baseRespVo;
    }

    /*
    * 更改商品
    * 我们这里作的操作是根据goods id 到specification 和 product 和 attributes 中将相关的数据全删除后
    * 重新添加
    * */
    @Override
    public void updateGoods(CreateGoods createGoods) {
        //goods
        Date date = new Date();
        createGoods.getGoods().setUpdateTime(date);
        goodsMapper.updateByPrimaryKey(createGoods.getGoods());
        int goodsId = createGoods.getGoods().getId();

        //删除该商品的规格
        GoodsSpecificationExample specificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = specificationExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        specificationMapper.deleteByExample(specificationExample);

        //删除改商品的商品设置
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria1 = goodsProductExample.createCriteria();
        criteria1.andGoodsIdEqualTo(goodsId);
        productMapper.deleteByExample(goodsProductExample);

        //删除商品的参数
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria2 = goodsAttributeExample.createCriteria();
        criteria2.andGoodsIdEqualTo(goodsId);
        attributeMapper.deleteByExample(goodsAttributeExample);

        //重新插入
        //specification
        for (GoodsSpecification goodsSpecification : createGoods.getSpecifications()) {
            goodsSpecification.setUpdateTime(date);
            goodsSpecification.setGoodsId(goodsId);
            specificationMapper.insert(goodsSpecification);

        }
        //product
        for (GoodsProduct goodsProduct : createGoods.getProducts()) {
            goodsProduct.setUpdateTime(date);
            goodsProduct.setGoodsId(goodsId);
            productMapper.insert(goodsProduct);

        }
        //attributes
        for (GoodsAttribute attribute : createGoods.getAttributes()) {
            attribute.setUpdateTime(date);
            attribute.setGoodsId(goodsId);
            attributeMapper.insert(attribute);
        }


        /*
        //specification
        for (GoodsSpecification goodsSpecification : createGoods.getSpecifications()) {
            if (goodsSpecification.getId()==null || "".equals(goodsSpecification.getId())){
                goodsSpecification.setAddTime(date);
                goodsSpecification.setUpdateTime(date);
                goodsSpecification.setGoodsId(goodsId);
                specificationMapper.insert(goodsSpecification);
            }else {
                goodsSpecification.setUpdateTime(date);
                goodsSpecification.setGoodsId(goodsId);

               *//* GoodsSpecificationExample specificationExample = new GoodsSpecificationExample();
                GoodsSpecificationExample.Criteria criteria = specificationExample.createCriteria();
                criteria.andGoodsIdEqualTo(goodsId);
                specificationMapper.updateByExampleSelective(goodsSpecification,specificationExample);*//*

                specificationMapper.updateByPrimaryKeySelective(goodsSpecification);
            }
        }

        //product
        for (GoodsProduct goodsProduct : createGoods.getProducts()) {
          *//*  if (goodsProduct.getId()==null || "".equals(goodsProduct.getId())){
                goodsProduct.setAddTime(date);
                goodsProduct.setUpdateTime(date);
                goodsProduct.setGoodsId(goodsId);
                productMapper.insert(goodsProduct);
            }*//*
            goodsProduct.setUpdateTime(date);
            goodsProduct.setGoodsId(goodsId);

            GoodsProductExample goodsProductExample = new GoodsProductExample();
            GoodsProductExample.Criteria criteria = goodsProductExample.createCriteria();
            criteria .andGoodsIdEqualTo(goodsId);
            productMapper.updateByExample(goodsProduct,goodsProductExample);

            //productMapper.updateByPrimaryKey(goodsProduct);
        }


        //attributes
        for (GoodsAttribute attribute : createGoods.getAttributes()) {
            if (attribute.getId()==null || "".equals(attribute.getId())){
                attribute.setAddTime(date);
                attribute.setUpdateTime(date);
                attribute.setGoodsId(goodsId);
                attributeMapper.insert(attribute);
            }else{
                attribute.setUpdateTime(date);
                attribute.setGoodsId(goodsId);
                attributeMapper.updateByPrimaryKey(attribute);
            }
        }*/
    }

    /*删除商品
    *
    *
    * */
    @Override
    public BaseRespVo deleteGoodsById(int id) {

      /*
        本来是将delete改为true
        Goods goods = new Goods();
        goods.setDeleted(true);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andIdEqualTo(id);
        goodsMapper.updateByExampleSelective(goods,goodsExample);*/
        goodsMapper.deleteByPrimaryKey(id);

        //删除该商品的规格
        GoodsSpecificationExample specificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = specificationExample.createCriteria();
        criteria.andGoodsIdEqualTo(id);
        specificationMapper.deleteByExample(specificationExample);

        //删除改商品的商品设置
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria1 = goodsProductExample.createCriteria();
        criteria1.andGoodsIdEqualTo(id);
        productMapper.deleteByExample(goodsProductExample);

        //删除商品的参数
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria2 = goodsAttributeExample.createCriteria();
        criteria2.andGoodsIdEqualTo(id);
        attributeMapper.deleteByExample(goodsAttributeExample);

        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


}
