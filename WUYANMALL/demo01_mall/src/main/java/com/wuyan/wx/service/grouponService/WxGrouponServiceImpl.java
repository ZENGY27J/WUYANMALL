package com.wuyan.wx.service.grouponService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.GrouponRules;
import com.wuyan.mall.bean.GrouponRulesExample;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.mapper.GrouponRulesMapper;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.bean.GrouponList;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WxGrouponServiceImpl implements WxGrouponService {
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public Databean getGroupon(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize());
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(new GrouponRulesExample());
        List<GrouponList> grouponList = new ArrayList<>();
        for (GrouponRules grouponRule : grouponRules) {
            int goodsId = grouponRule.getGoodsId();
            List<Goods> goods = goodsMapper.selectByExample(QueryUtils.getGoodsById(goodsId));
            GrouponList grouponList1 = new GrouponList();
            grouponList1.setGroupon_member(String.valueOf(grouponRule.getDiscountMember()));
            Goods goods1 = goods.get(0);
            grouponList1.setGoods(goods1);
            BigDecimal retailPrice = goods1.getRetailPrice();
            BigDecimal discount = grouponRule.getDiscount();
            grouponList1.setGroupon_price(String.valueOf(retailPrice.subtract(discount)));
            grouponList.add(grouponList1);
        }
        Databean databean = new Databean();
        long l = grouponRulesMapper.countByExample(new GrouponRulesExample());
        databean.setCount(l);
        databean.setData(grouponList);
        return databean;
    }


}
