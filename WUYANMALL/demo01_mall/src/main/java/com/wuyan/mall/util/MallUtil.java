package com.wuyan.mall.util;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.vo.PageInfo;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MallUtil {
    /**
     * 行政区域工具
     * @param id
     * @return
     */
    public static RegionExample getRegion(int id){
        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteria = regionExample.createCriteria().andPidEqualTo(id);
        return regionExample;
    }

    /**
     * 品牌管理
     * @param id
     * @param name
     * @return
     */
    public static BrandExample getBrandAll(String id,String name){
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        if (!"".equals(id) && id != null){
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!"".equals(name) && name != null){
            criteria.andNameLike("%" + name + "%");
        }
        return  brandExample;
    }

    public static BrandExample getBrandByName(String name) {
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andNameEqualTo(name);
        return brandExample;
    }

    /**
     * 商品类目
     * @param id
     * @return
     */
    public static CategoryExample getCategory(Object id){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        if(id instanceof Integer) {
            criteria.andPidEqualTo((Integer)id);
        }else if(id instanceof String){
            criteria.andNameEqualTo((String)id);
        }
        return categoryExample;
    }
    public static CategoryExample getCategoryById(int id){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria().andIdEqualTo(id);
        return categoryExample;
    }

    /**
     * 订单管理
     * @param pageInfo
     * @return
     */
    public static OrderExample getOrder(PageInfo pageInfo) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(!"".equals(pageInfo.getUserId())&& pageInfo.getUserId() != null){
            criteria.andUserIdEqualTo(Integer.valueOf(pageInfo.getUserId()));
        }
        if (!"".equals(pageInfo.getOrderSn()) && pageInfo.getOrderSn() != null){
            criteria.andOrderSnEqualTo(pageInfo.getOrderSn());
        }
        List<Short> orderStatusArray = pageInfo.getOrderStatusArray();
        if (orderStatusArray != null){
            for (Short aShort : orderStatusArray) {
                criteria.andOrderStatusEqualTo(aShort);
            }
        }
        return orderExample;
    }

    public static UserExample getUser(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(userId);
        return userExample;
    }

    public static OrderGoodsExample getOrderGoods(int id) {
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(id);
        return orderGoodsExample;
    }

    public static OrderExample getOrderById(int id) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(id);
        return orderExample;
    }

    /**
     * 通用问题
     * @param question
     * @return
     */
    public static IssueExample getIssue(String question) {
        IssueExample issueExample = new IssueExample();
        IssueExample.Criteria criteria = issueExample.createCriteria();
        if(!"".equals(question) && question == null){
        }else{
            criteria.andQuestionLike("%" + question + "%");
        }
        return issueExample;
    }

    public static IssueExample getIssueById(Integer id) {
        IssueExample issueExample = new IssueExample();
        IssueExample.Criteria criteria = issueExample.createCriteria().andIdEqualTo(id);
        return issueExample;
    }

    /**
     * 关键词
     * @return
     */
    public static KeywordExample getKeyword(String url,String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        if(!"".equals(keyword) && keyword != null){
            criteria.andKeywordLike("%" + keyword + "%");
        }
        if(!"".equals(url) && url != null){
            criteria.andUrlLike("%" + url + "%");
        }
        return keywordExample;
    }

    public static KeywordExample getKeywordByKeyword(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria().andKeywordEqualTo(keyword);
        return keywordExample;
    }

    public static KeywordExample getKeywordById(int id) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria().andIdEqualTo(id);
        return keywordExample;
    }

    /**
     * 分页
     * @param pageInfo
     */
    public static void pageHelper(PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getLimit());
        String order = pageInfo.getSort() + " " + pageInfo.getOrder();
        PageHelper.orderBy(order);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowTime(){
        Date time = null;
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            time = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
