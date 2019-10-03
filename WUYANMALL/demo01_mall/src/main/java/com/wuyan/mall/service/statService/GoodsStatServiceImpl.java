package com.wuyan.mall.service.statService;

import com.wuyan.mall.bean.OrderGoods;
import com.wuyan.mall.bean.OrderGoodsExample;
import com.wuyan.mall.mapper.OrderGoodsMapper;
import com.wuyan.mall.vo.StatGoodRowVo;
import com.wuyan.mall.vo.StatGoodVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class GoodsStatServiceImpl implements GoodsStatService {

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Override
    public StatGoodVo statGoods() {
        StatGoodVo statGoodVo = new StatGoodVo();
        String[] columns = {"day", "orders", "products", "amount"};
        statGoodVo.setColumns(columns);

        List<StatGoodRowVo> rows = new ArrayList<>();

        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);

        List<Date> days = new ArrayList<>();


        for (OrderGoods orderGood : orderGoods) {
            if (!days.contains(orderGood.getAddTime())) {
                days.add(orderGood.getAddTime());
            }
        }

        //将每一天的商品统计封装成一个StatGoodRowVo 对象
        for (Date day : days) {
            StatGoodRowVo statGoodRowVo = new StatGoodRowVo();
            int orders = 0;
            int products = 0;
            BigDecimal amount = new BigDecimal(0);
            List<Integer> orderIds = new ArrayList<>();

            //通过order_id 将订单统计, goods_number 将商品统计
            for (OrderGoods orderGood : orderGoods) {
                if (orderGood.getAddTime().equals(day)) {
                    //统计订单 和 商品数量当天的
                    for (OrderGoods good : orderGoods) {
                        if (!orderIds.contains(good.getOrderId())) {
                            orders++;
                            orderIds.add(good.getOrderId());
                        }
                        products = products + good.getNumber();
                        amount = amount.add(good.getPrice());
                    }
                    statGoodRowVo.setAmount(amount);
                    statGoodRowVo.setDay(day);
                    statGoodRowVo.setOrders(orders);
                    statGoodRowVo.setProducts(products);
                }
            }
            rows.add(statGoodRowVo);
        }

        statGoodVo.setRows(rows);
        return statGoodVo;
    }
}
