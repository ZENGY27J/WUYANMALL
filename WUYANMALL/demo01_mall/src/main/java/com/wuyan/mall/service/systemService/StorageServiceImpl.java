package com.wuyan.mall.service.systemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Storage;
import com.wuyan.mall.bean.StorageExample;
import com.wuyan.mall.mapper.StorageMapper;
import com.wuyan.mall.vo.ResultInfos;
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
        storageMapper.deleteByPrimaryKey(id);
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
    public ResultInfos selectByExample(int page, int limit, String key, String name) {
        PageHelper.startPage(page,limit);
        StorageExample example = new StorageExample();
        StorageExample.Criteria criteria = example.createCriteria();
        if(key != null && name != null){
            if(!("".equals(key))){
                criteria.andKeyEqualTo(key).andNameLike("%" + name + "%");
            }
            //criteria.andKeyEqualTo(key).andNameLike("%" + name + "%");
        }else if(key == null && name != null){
            criteria.andNameLike("%" + name + "%");
        }else if(key != null && name == null){
            if(!("".equals(key))){
                criteria.andKeyEqualTo(key);
            }
            //criteria.andKeyEqualTo(key);
        }
        List<Storage> storages = storageMapper.selectByExample(example);

        PageInfo<Storage> storagePageInfo = new PageInfo<>(storages);
        long total = storagePageInfo.getTotal();

        ResultInfos results = new ResultInfos();
        results.setTotal(total);
        results.setItems(storages);
        return results;
    }

    @Override
    public Storage selectByPrimaryKey(Integer id) {
        Storage storage = storageMapper.selectByPrimaryKey(id);
        return storage;
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
        storageMapper.updateByPrimaryKeySelective(record);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Storage record) {
        return 0;
    }
}
