package com.wuyan.mall.service.storageService;

import com.wuyan.mall.vo.BaseRespVo;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

public interface StorageService {
    BaseRespVo uploadFile(MultipartFile file);
    BaseRespVo uploadGoodsPicture(File file,String filePathNew);
}
