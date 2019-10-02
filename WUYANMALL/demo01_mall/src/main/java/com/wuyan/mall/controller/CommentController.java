package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Accept.AcceptComment;
import com.wuyan.mall.bean.Accept.DeleteComment;
import com.wuyan.mall.service.commentSerivice.CommentService;
import com.wuyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    BaseRespVo baseRespVo;
    @RequestMapping(value = "list")
    public BaseRespVo listComment(AcceptComment acceptComment){

        if (acceptComment.getValueId()==0 && acceptComment.getUserId()==0) {
            baseRespVo = commentService.listAllComment(acceptComment.getPage(),acceptComment.getLimit());
        }else {
            baseRespVo = commentService.listCommentByValueIdAndUserId(acceptComment.getPage(), acceptComment.getLimit(), acceptComment.getValueId(), acceptComment.getUserId());
        }
        return baseRespVo;
    }
    @RequestMapping("delete")
    public BaseRespVo deleteComment(@RequestBody DeleteComment deleteComment){
       BaseRespVo baseRespVo= commentService.deleteComment(deleteComment);
        return baseRespVo;
    }


}
