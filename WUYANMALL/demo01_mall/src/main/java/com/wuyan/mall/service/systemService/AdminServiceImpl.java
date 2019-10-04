package com.wuyan.mall.service.systemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Admin;
import com.wuyan.mall.bean.AdminExample;
import com.wuyan.mall.mapper.AdminMapper;
import com.wuyan.mall.vo.AdminInfo;
import com.wuyan.mall.vo.ResultInfos;
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
        adminMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Admin record) {
        return 0;
    }

    @Override
    public int insertSelective(Admin record) {
        int flag = adminMapper.insertSelective(record);
        return flag;
    }

    @Override
    public ResultInfos selectByExample(int page, int limit, String username) {
        PageHelper.startPage(page,limit);
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if(username != null){
            criteria.andUsernameLike("%" + username + "%");
        }
        List<AdminInfo> admins = adminMapper.selectByExample(example);

        PageInfo<AdminInfo> adminInfoPageInfo = new PageInfo<>(admins);
        long total = adminInfoPageInfo.getTotal();

        ResultInfos results = new ResultInfos();
        results.setTotal(total);
        results.setItems(admins);
        return results;
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
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
        int flag = adminMapper.updateByPrimaryKeySelective(record);
        return flag;
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return 0;
    }
}
