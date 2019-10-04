package com.wuyan.mall.service.systemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Role;
import com.wuyan.mall.bean.RoleExample;
import com.wuyan.mall.mapper.RoleMapper;
import com.wuyan.mall.vo.ResultInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    @Override
    public long countByExample(RoleExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(RoleExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        int flag = roleMapper.insertSelective(record);
        return flag;
    }

    @Override
    public ResultInfos selectByExample(int page, int limit, String name) {
        PageHelper.startPage(page,limit);
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if(name != null){
            criteria.andNameLike("%" + name + "%");
        }

        List<Role> roles = roleMapper.selectByExample(example);

        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long total = rolePageInfo.getTotal();

        ResultInfos results = new ResultInfos();
        results.setTotal(total);
        results.setItems(roles);
        return results;
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public int updateByExampleSelective(Role record, RoleExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Role record, RoleExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        roleMapper.updateByPrimaryKey(record);
        return 0;
    }
}
