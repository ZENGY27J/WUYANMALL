package com.wuyan.mall.service.statService;

import com.wuyan.mall.bean.User;
import com.wuyan.mall.bean.UserExample;
import com.wuyan.mall.mapper.UserMapper;
import com.wuyan.mall.vo.StatUserRowVo;
import com.wuyan.mall.vo.StatUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 将用户统计信息封装在一个对象中返回
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */
@Service
public class UserStatServiceImpl implements UserStatService {

    @Autowired
    UserMapper userMapper;

    @Override
    public StatUserVo statUser() {
        StatUserVo statUserVo = new StatUserVo();

        List<StatUserRowVo> rows = new ArrayList<>();

        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);

        List<Date> days = new ArrayList<>();

        //将所有不同的日期挑选出来
        for (User user : userList) {
            Date date = user.getBirthday();
            if (date != null && !days.contains(date)) {
                days.add(date);
            }
        }

        //将不同日期的用户统计出来封装返回
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Date day : days) {
            String dayString = simpleDateFormat.format(day);
            StatUserRowVo statUserRowVo = new StatUserRowVo();
            statUserRowVo.setDay(dayString);
            int count = 0;
            for (User user : userList) {
                if (dayString.equals(simpleDateFormat.format(user.getBirthday()))) {
                    count++;
                }
            }
            statUserRowVo.setUsers(count);

            rows.add(statUserRowVo);
        }

        statUserVo.setRows(rows);

        String[] columns = {"day", "users"};

        statUserVo.setColumns(columns);
        return statUserVo;
    }
}
