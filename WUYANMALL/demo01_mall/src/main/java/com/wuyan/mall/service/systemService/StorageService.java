package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Storage;
import com.wuyan.mall.bean.StorageExample;
import com.wuyan.mall.vo.ResultInfos;
import org.apache.ibatis.annotations.Param;

public interface StorageService {
    long countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    ResultInfos selectByExample(int page, int limit, String key, String name);

    Storage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);
}
