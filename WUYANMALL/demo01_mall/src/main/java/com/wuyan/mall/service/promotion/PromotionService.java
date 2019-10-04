package com.wuyan.mall.service.promotion;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.vo.PromotionPageInfo;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2019/9/30 0030 21:22
 */
@Service
public interface PromotionService {

    BaseData<Advertisement> getAddList(PromotionPageInfo promptionPageInfo);

    BaseData<Topic> getTopicList(PromotionPageInfo promotionPageInfo);

    void deleteTopicByID(Integer id);

    int updateTopicById(Topic topic);




    BaseData<GrouponRules> getGroupRulesList(PromotionPageInfo promotionPageInfo);

    void deleteGroupRules(Integer id);

    int updateGroupRules(GrouponRules grouponRules);




    Goods findById(Integer goodsId);

    int createGroupRules(GrouponRules grouponRules);


    void creatTopic(Topic topic);
}
