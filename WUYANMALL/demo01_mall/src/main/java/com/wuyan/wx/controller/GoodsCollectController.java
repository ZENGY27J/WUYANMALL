package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.service.collectService.GoodsCollectService;
import com.wuyan.wx.vo.AddOrDeleteCollectVo;
import javafx.scene.layout.Background;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/collect")
public class GoodsCollectController {

    @Autowired
    GoodsCollectService collectService;
    /**
     * 商品添加收藏
     * 根据userid
     * 收藏返回的type 是delete 取消收藏返回的是add
     * 例如：data:{"type":"add"}
     *
     */
    @RequestMapping("addordelete")
    public BaseRespVo addCollect(@RequestBody Map<String, String> map){
        //这里的userid先写死在代码中
        int userId=1;
        String type = map.get("type");
        String valueId = map.get("valueId");
        String flag = collectService.addOrDelete(userId, Byte.parseByte(type), Integer.parseInt(valueId));
        AddOrDeleteCollectVo addOrDeleteCollectVo = new AddOrDeleteCollectVo();
        addOrDeleteCollectVo.setType(flag);

        return BaseRespVo.ok(addOrDeleteCollectVo);
    }
}
