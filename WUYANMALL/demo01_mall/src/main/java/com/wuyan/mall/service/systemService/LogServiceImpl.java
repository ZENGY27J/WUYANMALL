package com.wuyan.mall.service.systemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Log;
import com.wuyan.mall.bean.LogExample;
import com.wuyan.mall.mapper.LogMapper;
import com.wuyan.mall.vo.ResultInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    LogMapper logMapper;

    @Override
    public long countByExample(LogExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(LogExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Log record) {
        return 0;
    }

    @Override
    public int insertSelective(Log record) {
        return 0;
    }

    @Override
    public ResultInfos selectByExample(int page, int limit, String name) {
        PageHelper.startPage(page,limit);
        LogExample example = new LogExample();
        LogExample.Criteria criteria = example.createCriteria();

        if(name != null){
            criteria.andAdminLike("%" + name + "%");
        }

        List<Log> logs = logMapper.selectByExample(example);

        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        long total = logPageInfo.getTotal();

        ResultInfos results = new ResultInfos();
        results.setTotal(total);
        results.setItems(logs);
        return results;
    }

    @Override
    public Log selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Log record, LogExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Log record, LogExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Log record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Log record) {
        return 0;
    }
}
