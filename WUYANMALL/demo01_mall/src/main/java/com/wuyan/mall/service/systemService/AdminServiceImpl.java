package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Admin;
import com.wuyan.mall.bean.AdminExample;
import com.wuyan.mall.mapper.AdminMapper;
import com.wuyan.mall.vo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;

    @Override
    public long countByExample(AdminExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AdminExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Admin record) {
        return 0;
    }

    @Override
    public int insertSelective(Admin record) {
        return 0;
    }

    @Override
    public List<AdminInfo> selectByExample(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if(username != null){
            criteria.andUsernameLike("%" + username + "%");
        }
        List<AdminInfo> admins = adminMapper.selectByExample(example);
        return admins;
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Admin record, AdminExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Admin record, AdminExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return 0;
    }
}
