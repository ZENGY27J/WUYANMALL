package com.wuyan.mall.service.statService;

import com.wuyan.mall.bean.Order;
import com.wuyan.mall.bean.OrderExample;
import com.wuyan.mall.mapper.OrderMapper;
import com.wuyan.mall.vo.StatOrderRowVo;
import com.wuyan.mall.vo.StatOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:将订单统计封装并返回
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */
@Service
public class OrderStatServiceImpl implements OrderStatService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public StatOrderVo statOrder() {
        StatOrderVo statOrderVo = new StatOrderVo();
        String[] columns = {"day", "orders", "customers", "amount", "pcr"};
        statOrderVo.setColumns(columns);

        //将每一天的订单封装在单独的对象中, 最后添加到这个集合中
        ArrayList<StatOrderRowVo> rows = new ArrayList<>();

        OrderExample orderExample = new OrderExample();
        List<Order> orders = orderMapper.selectByExample(orderExample);

        List<String> days = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //将不同日期找出来
        for (Order order : orders) {
            Date payTime = order.getPayTime();
            String StringPayTime = simpleDateFormat.format(payTime);
            if (!days.contains(StringPayTime)) {
                days.add(StringPayTime);
            }
        }

        //找出每天的总金额和下单用户数量
        for (String day : days) {
            StatOrderRowVo statOrderRowVo = new StatOrderRowVo();
            statOrderRowVo.setDay(day);

            int customers = 0;
            BigDecimal amount = new BigDecimal(0);
            BigDecimal pcr = new BigDecimal(0);
            List<Integer> uids = new ArrayList<>();

            for (Order order : orders) {
                if (day.equals(simpleDateFormat.format(order.getPayTime()))) {
                    amount = amount.add(order.getActualPrice());
                    if (!uids.contains(order.getUserId())) {
                        uids.add(order.getUserId());
                        customers++;
                    }
                }
            }
            statOrderRowVo.setAmount(amount);
            statOrderRowVo.setCustomers(customers);
            if (customers != 0) {
                pcr = amount.divide(new BigDecimal(customers));
            }

            statOrderRowVo.setPcr(pcr);
            rows.add(statOrderRowVo);
        }

        //返回封装的对象
        statOrderVo.setRows(rows);
        return statOrderVo;
    }
}
