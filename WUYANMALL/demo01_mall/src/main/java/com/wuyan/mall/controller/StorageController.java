package com.wuyan.mall.controller;

import com.wuyan.mall.service.storageService.StorageService;
import com.wuyan.mall.vo.BaseRespVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @Autowired
    StorageService storageService;
    /**
     * 商品图片上传
     * @param file
     * @return
     * localhost/admin/storage/create
     */
    @RequestMapping("create")
    public BaseRespVo fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest){
        BaseRespVo baseRespVo = storageService.uploadFile(file);
        return baseRespVo;
    }
}
