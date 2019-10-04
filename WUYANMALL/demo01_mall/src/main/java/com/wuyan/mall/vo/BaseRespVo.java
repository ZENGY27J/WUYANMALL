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

}
