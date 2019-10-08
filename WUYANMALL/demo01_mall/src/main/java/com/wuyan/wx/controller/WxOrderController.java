package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.service.orderService.WxOrderService;
import com.wuyan.wx.utils.GetUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("wx/order")
public class WxOrderController {
    @Autowired
    WxOrderService wxOrderService;
    @RequestMapping("delete")
    public BaseRespVo prepay(@RequestBody Map map){
        int orderId = (int) map.get("orderId");
        wxOrderService.deleteOrder(orderId);
        return BaseRespVo.ok(null);
    }
    @RequestMapping("submit")
    public BaseRespVo submit(@RequestBody Map map){
        int orderId = wxOrderService.submit(map);
        return BaseRespVo.ok(orderId);
    }
}
