package com.wuyan.mall.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 将参数中的keyname 转换成表中的字段的名字
 */
public class TransKeyNameUtil {

   public static Map transferKeyName(Map<String, String> map) {
       Set<String> keyNames = map.keySet();

       HashMap<Object, Object> map1 = new HashMap<>();

       //改变 keyname
       for (String keyName : keyNames) {
           String replace = keyName.replace("litemall", "cskaoyan_mall");
           map1.put(replace, map.get(keyName));
       }
       return map1;
   }
}
