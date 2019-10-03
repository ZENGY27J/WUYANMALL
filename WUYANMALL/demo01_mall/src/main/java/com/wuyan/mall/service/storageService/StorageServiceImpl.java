package com.wuyan.mall.service.storageService;

import com.wuyan.mall.bean.Storage;
import com.wuyan.mall.mapper.CommentMapper;
import com.wuyan.mall.mapper.StorageMapper;
import com.wuyan.mall.util.DateUtil;
import com.wuyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

@Service
public class StorageServiceImpl implements  StorageService{
    //file.rootPath: E:/img/

    //图片存放根路径
    @Value("${file.rootPath}")
    private String ROOT_PATH;
  /*  //图片存放根目录下的子目录
    @Value("${file.sonPath}")
    private String SON_PATH;*/
    //获取主机端口
    @Value("${server.port}")
    private String POST;

    @Autowired
    Storage storage;
    @Autowired
    StorageMapper storageMapper;

    @Override
    public BaseRespVo uploadFile(MultipartFile file) {
        storage.setName(file.getOriginalFilename());
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());

        //获取上传文件的后缀名
        String suffix=file.getOriginalFilename();//获取上传文件名
        String prefix=suffix.substring(suffix.lastIndexOf(".")+1);

        //设置文件上传后的路径
        //String filePath=ROOT_PATH+":"+SON_PATH;
        String filePath=ROOT_PATH;
        //防止文件名被冲突
        String uuid= UUID.randomUUID().toString();
        String fileName=uuid+"."+prefix;
        //创建文件路径
        File dest=new File(filePath+fileName);
        //检查是否存在目录
        if (!dest.getParentFile().exists()){
            //假设文件不存在就重新创建新的文件防止发生异常
            dest.getParentFile().mkdirs();
        }
        try {
            //transfetTo方法将上传文件写到服务器指定文件
            file.transferTo(dest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //保存file相关信息到指定数据库
        //String filePathNew=SON_PATH+fileName;
        String filePathNew="/"+fileName;
        BaseRespVo baseRespVo=uploadGoodsPicture(dest,filePathNew);

        return baseRespVo;
    }


    @Override
    public BaseRespVo uploadGoodsPicture(File file, String filePathNew)  {
        //获取本机ip
        String host=null;
        try {
            host= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //key 修改后的文件名
        storage.setKey(file.getName());
        //新增日期
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        //url 可以通过这个地址直接访问这张图片
        storage.setUrl("http://"+host+":"+POST+filePathNew);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setData(storage);
        baseRespVo.setErrmsg("成功");
        //存储到数据库中
       storageMapper.insert(storage);

        return baseRespVo;
    }
}
