package com.wuyan.wx.service.goodsService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.*;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.vo.GoodsByCategoryIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WxGoodsServiceImplByZy implements WxGoodsServiceByZy {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    IssueMapper issueMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    GoodsSpecificationMapper specificationMapper;

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    CollectMapper collectMapper;
    /*
    * param: goodsId  商品的id
    * return: Goods 返回查找的商品
    * */
    @Override
    public Goods selectGoodsById(int goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        return goods;
    }

    /*
    * param: categoryId 商品类目id
    * return: List<Goods> 返回根据类目id查找的商品list
    * */
    @Override
    public List<Goods> selectListGoodsByCategoryId(int categoryId) {
        GoodsExample goodsExample=new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);


        PageHelper.startPage(1,6);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

        return goodsList;
    }

    /**
     * 根据brandid查找brand
     * @param brandId
     * @return brand
     */
    @Override
    public Brand selectBrandById(int brandId) {
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        return brand;
    }

    /**
     * 将issue表中数据都取出来
     */
    @Override
    public List<Issue> listIssue() {
        List<Issue> issues = issueMapper.selectByExample(new IssueExample());
        return issues;
    }

    /**
     * 根据goodsId查找所有的拼团规则，是从groupon_rules表中查询
     * @param goodsId
     * @return
     */
    @Override
    public List<GrouponRules> selectGrouponByGoodsID(int goodsId) {
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);

        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        return grouponRules;
    }

    /**
     * 根据goodsId查找这个商品所有的product
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsProduct> selectProductListByGoodsId(int goodsId) {
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria = goodsProductExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);

        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByExample(goodsProductExample);
        return goodsProducts;
    }

    /**
     * 根据goodsid查找到该商品的所有规格
     * @param goodsId
     * @return List<GoodsSpecification>
     */
    @Override
    public List<GoodsSpecification> listSpecificationBygoodsId(int goodsId) {
        GoodsSpecificationExample specificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = specificationExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);

        List<GoodsSpecification> goodsSpecifications = specificationMapper.selectByExample(specificationExample);
        return  goodsSpecifications;
    }

    /**
     * 根据goodsid获取该商品的attribute
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsAttribute> listAttribute(int goodsId) {
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria = goodsAttributeExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);

        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        return goodsAttributes;
    }

    /**
     * 根据goodsId和userId 去collect查找这个用户是否已经收藏该商品
     * @param goodsId
     * @param userId
     * @return
     */
    @Override
    public boolean userHasCollect(int goodsId, int userId) {
        CollectExample collectExample=new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andValueIdEqualTo(goodsId);
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        if (collects.size()==0){
            return false;
        }
        return true;
    }
}
