package com.wuyan.mall.vo;

import org.springframework.stereotype.Component;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 基本返回
 * @Date: 2019-09-30-12:10
 */
@Component
public class BaseRespVo<T> {
    T data;
    String errmsg;
    int errno;



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public static BaseRespVo ok(Object data) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
    public static BaseRespVo error(Object data) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        baseRespVo.setErrmsg("参数值不对");
        baseRespVo.setErrno(402);
        return baseRespVo;
    }
    public static BaseRespVo fail(){
        BaseRespVo baseRespVo = new BaseRespVo();

        baseRespVo.setErrmsg("失败");
        baseRespVo.setErrno(500);
        return baseRespVo;
    }
    public static BaseRespVo systemError(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("系统内部错误");
        baseRespVo.setErrno(502);
        return baseRespVo;
    }
    public static BaseRespVo payError(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("订单不能支付");
        baseRespVo.setErrno(742);
        return baseRespVo;
    }

    public static BaseRespVo badArgument(Object data) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        baseRespVo.setErrmsg("参数不对");
        baseRespVo.setErrno(401);
        return baseRespVo;
    }

    public static BaseRespVo updatedDataFiled(Object data) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        baseRespVo.setErrmsg("更新数据失败");
        baseRespVo.setErrno(505);
        return baseRespVo;
    }

    public static BaseRespVo codeError(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("验证码错误");
        baseRespVo.setErrno(703);
        return baseRespVo;
    }

    public static BaseRespVo couponError(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("优惠券已领完");
        baseRespVo.setErrno(740);
        return baseRespVo;
    }
    public static BaseRespVo exchangeError(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("优惠券不正确");
        baseRespVo.setErrno(742);
        return baseRespVo;
    }

    public static BaseRespVo LoginError(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("请登录");
        baseRespVo.setErrno(501);
        return baseRespVo;
    }
}
