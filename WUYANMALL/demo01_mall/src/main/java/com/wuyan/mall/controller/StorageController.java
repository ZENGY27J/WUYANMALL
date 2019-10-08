package com.wuyan.mall.controller;

import com.wuyan.mall.service.storageService.UploadService;
import com.wuyan.mall.vo.BaseRespVo;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    UploadService uploadService;
    /**
     * 商品图片上传
     * @param file
     * @return
     * localhost/admin/storage/create
     */
    @RequestMapping("create")
    public BaseRespVo fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest){
        BaseRespVo baseRespVo = uploadService.uploadFile(file);
        return baseRespVo;
    }
}
