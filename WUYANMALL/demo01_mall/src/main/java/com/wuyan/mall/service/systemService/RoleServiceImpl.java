package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Role;
import com.wuyan.mall.bean.RoleExample;
import com.wuyan.mall.mapper.RoleMapper;
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
    public List<Role> selectByExample(String name) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if(name != null){
            criteria.andNameLike("%" + name + "%");
        }

        List<Role> roles = roleMapper.selectByExample(example);
        return roles;
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
        return 0;
    }
}
