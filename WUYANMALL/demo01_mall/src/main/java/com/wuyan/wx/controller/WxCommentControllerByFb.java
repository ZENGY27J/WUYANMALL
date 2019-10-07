package com.wuyan.wx.controller;

import com.wuyan.mall.bean.Comment;
import com.wuyan.mall.service.commentSerivice.CommentService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.TopicCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/comment")
public class WxCommentControllerByFb {

    @Autowired
    CommentService commentService;

    @RequestMapping("list")
    public BaseRespVo listComment(String valueId, String type, String showType, String page, String size) {
        TopicCommentVo topicCommentVo = commentService.listTopicComment(valueId, type, showType, page, size);
        return BaseRespVo.ok(topicCommentVo);
    }

    @RequestMapping("post")
    public BaseRespVo postComment(@RequestBody Comment comment) {
        /*String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);*/
        int userId = 1;

        Comment commentReturn = commentService.addComment(comment, userId);
        return BaseRespVo.ok(commentReturn);
    }
}
