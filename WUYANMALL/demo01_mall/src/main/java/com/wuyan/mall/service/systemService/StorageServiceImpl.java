package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Storage;
import com.wuyan.mall.bean.StorageExample;
import com.wuyan.mall.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public long countByExample(StorageExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StorageExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Storage record) {
        return 0;
    }

    @Override
    public int insertSelective(Storage record) {
        return 0;
    }

    @Override
    public List<Storage> selectByExample(String key,String name) {
        StorageExample example = new StorageExample();
        /*StorageExample.Criteria criteria = example.createCriteria();
        if(key != null && name != null){
            key = ""+key+"";
            criteria.andKeyEqualTo(key).andNameLike("%" + name + "%");
        }else if(key == null && name != null){
            criteria.andNameLike("%" + name + "%");
        }else if(key != null && name == null){
            key = ""+key+"";
            criteria.andKeyEqualTo(key);
        }*/
        List<Storage> storages = storageMapper.selectByExample(example);
        return storages;
    }

    @Override
    public Storage selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Storage record, StorageExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Storage record, StorageExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Storage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Storage record) {
        return 0;
    }
}
