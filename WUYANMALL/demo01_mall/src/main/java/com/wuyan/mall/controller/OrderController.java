package com.wuyan.mall.controller;

import com.wuyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    BaseRespVo baseRespVo;

    /*
    *
    * 回复评论
    * 因为无法抓包，写前台时再回来写
    * */
    @RequestMapping("reply")
    public BaseRespVo replyComment(){
        baseRespVo.setErrno(662);
        baseRespVo.setErrmsg("订单商品已回复");
        return baseRespVo;
    }
}
