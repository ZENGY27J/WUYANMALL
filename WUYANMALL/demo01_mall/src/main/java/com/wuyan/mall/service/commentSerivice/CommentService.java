package com.wuyan.mall.service.commentSerivice;

import com.wuyan.mall.bean.Accept.DeleteComment;
import com.wuyan.mall.bean.Comment;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.TopicCommentVo;

public interface CommentService {
    BaseRespVo listAllComment(int page, int limit);
    BaseRespVo listCommentByValueIdAndUserId(int page,int limit,int valueId,int userId);
    BaseRespVo deleteComment(DeleteComment deletComment);

    TopicCommentVo listTopicComment(String valueId, String type, String showType, String page, String size);

    Comment addComment(Comment comment, int userId);
}
