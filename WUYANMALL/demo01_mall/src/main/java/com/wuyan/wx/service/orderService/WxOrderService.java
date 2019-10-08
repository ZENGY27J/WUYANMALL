package com.wuyan.wx.service.orderService;

import java.util.Map;

public interface WxOrderService {
    void deleteOrder(int orderId);

    int submit(Map map);
}
