package com.wuyan.mall.service.configService;

import java.util.Map;

/**
 * @Description: 商场配置的service接口
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/9/30
 */
public interface ConfigService {
    //查询所有配置
    Map<String, String> queryAllConfig();

    //修改配置
    void updateConfig(Map<String, String> map);


    /**
     * 查询商城信息
     * @return 返回一个包含商城信息的对象
     */
    Map<String, String> queryMallConfig();


    /**
     * 查询运费信息
     * @return 返回一个包含运费信息的map
     */
    Map<String, String> queryExpressConfig();

    /**
     * 查询订单信息
     * @return 返回一个包含订单信息的map
     */
    Map<String, String> queryOrderConfig();

    /**
     * 查询微信信息
     * @return 返回一个包含微信信息的map
     */
    Map<String, String> queryWxConfig();

}
