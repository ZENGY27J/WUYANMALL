package com.wuyan.wx.controller;


import com.wuyan.mall.service.commentSerivice.CommentService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.vo.CommentCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/comment")
public class GoodsCommentController {

    @Autowired
    CommentService commentService;

    /**
     * 根据valueId(goodsId)和type查看总共有几个评价和几个有图评价
     * @param valueId
     * @param type
     * @return
     */
    @RequestMapping("count")
    public BaseRespVo listComment(int valueId,int type){
        CommentCountVo commentCountVo = commentService.countComment(valueId, type);
        return  BaseRespVo.ok(commentCountVo);
    }

}
