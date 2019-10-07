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

    /*
    * 评论列表和查询评论，这里的查询是精确查询
    * */
    @RequestMapping(value = "list")
    public BaseRespVo listComment(AcceptComment acceptComment){
        if (("".equals(acceptComment.getValueId()) || acceptComment.getValueId()==null ) && ("".equals(acceptComment.getUserId()) || acceptComment.getUserId()==null)) {
            baseRespVo = commentService.listAllComment(acceptComment.getPage(),acceptComment.getLimit());
        }else {
            int userId=0;
            int valueId=0;
            if (!"".equals(acceptComment.getUserId()) && acceptComment.getUserId()!=null ) {
                userId = Integer.parseInt(acceptComment.getUserId());
            }
            if (!"".equals(acceptComment.getValueId()) && acceptComment.getValueId()!=null ){
                valueId = Integer.parseInt(acceptComment.getValueId());
            }
            baseRespVo = commentService.listCommentByValueIdAndUserId(acceptComment.getPage(), acceptComment.getLimit(), valueId, userId);
        }
        return baseRespVo;
    }

    /*
    * 删除评论
    * */
    @RequestMapping("delete")
    public BaseRespVo deleteComment(@RequestBody DeleteComment deleteComment){
       BaseRespVo baseRespVo= commentService.deleteComment(deleteComment);
        return baseRespVo;
    }


}
