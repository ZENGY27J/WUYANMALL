package com.wuyan.wx.service.collectService;

import com.wuyan.mall.bean.Collect;
import com.wuyan.mall.bean.CollectExample;
import com.wuyan.mall.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsCollectServiceImpl implements  GoodsCollectService{

    @Autowired
    CollectMapper collectMapper;
    /**
     * 商品添加收藏或者取消收藏
     * @return
     */
    @Override
    public String addOrDelete(int userId,byte type,int valueId) {
        //根据userId和type和valueId在collect表中查询是否有
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andTypeEqualTo( type).andValueIdEqualTo(valueId);

        List<Collect> collects = collectMapper.selectByExample(collectExample);
        if (collects.size()==0){
            //如果没有这个数据就添加，有就删除
            Date date = new Date();
            Collect collect = new Collect();
            collect.setType(type);
            collect.setAddTime(date);
            collect.setUpdateTime(date);
            collect.setValueId(valueId);
            collect.setUserId(userId);
            collect.setDeleted(false);
            collectMapper.insert(collect);
            return "add";
        }else {
            collectMapper.deleteByExample(collectExample);
            return "delete";
        }

    }
}
