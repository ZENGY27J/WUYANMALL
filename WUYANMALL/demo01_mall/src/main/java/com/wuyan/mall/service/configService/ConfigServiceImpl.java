package com.wuyan.mall.service.configService;

import com.wuyan.mall.bean.System;
import com.wuyan.mall.bean.SystemExample;
import com.wuyan.mall.mapper.SystemMapper;
import com.wuyan.mall.util.TransKeyNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    SystemMapper systemMapper;

    @Override
    public Map<String, String> queryAllConfig() {
        SystemExample systemExample = new SystemExample();
        List<System> systems = systemMapper.selectByExample(systemExample);
        HashMap<String, String> map = new HashMap<>();

        for (System system : systems) {
            map.put(system.getKeyName(), system.getKeyValue());
        }

        return map;
    }

    @Override
    public void updateConfig(Map<String, String> map) {
        Map map1 = TransKeyNameUtil.transferKeyName(map);

        Set<String> set = map1.keySet();

        for (String keyName : set) {
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();

            //通过查找相同字段 来修改数据库
            criteria.andKeyNameEqualTo(keyName);

            List<System> systems = systemMapper.selectByExample(systemExample);

            for (System system : systems) {
                system.setKeyValue((String) map1.get(keyName));
                systemMapper.updateByExample(system, systemExample);
            }
        }
    }

    @Override
    public Map<String, String> queryMallConfig() {
        Map<String, String> map = queryAllConfig();
        HashMap<String, String> map1 = new HashMap<>();
        Set<String> keyNames = map.keySet();
        for (String keyName : keyNames) {
            if (keyName.contains("mall_mall")) {
                map1.put(keyName, map.get(keyName));
            }
        }
        return map1;
    }


    @Override
    public Map<String, String> queryExpressConfig() {
        Map<String, String> map = queryAllConfig();
        HashMap<String, String> map1 = new HashMap<>();
        Set<String> keyNames = map.keySet();
        for (String keyName : keyNames) {
            if (keyName.contains("mall_express")) {
                map1.put(keyName, map.get(keyName));
            }
        }
        return map1;
    }

    @Override
    public Map<String, String> queryOrderConfig() {
        Map<String, String> map = queryAllConfig();
        HashMap<String, String> map1 = new HashMap<>();
        Set<String> keyNames = map.keySet();
        for (String keyName : keyNames) {
            if (keyName.contains("mall_order")) {
                map1.put(keyName, map.get(keyName));
            }
        }
        return map1;
    }

    @Override
    public Map<String, String> queryWxConfig() {
        Map<String, String> map = queryAllConfig();
        HashMap<String, String> map1 = new HashMap<>();
        Set<String> keyNames = map.keySet();
        for (String keyName : keyNames) {
            if (keyName.contains("mall_wx")) {
                map1.put(keyName, map.get(keyName));
            }
        }
        return map1;
    }


}
