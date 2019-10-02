package com.wuyan.mall.service.commentSerivice;

import com.wuyan.mall.bean.Accept.DeleteComment;
import com.wuyan.mall.vo.BaseRespVo;

public interface CommentService {
    BaseRespVo listAllComment(int page, int limit);
    BaseRespVo listCommentByValueIdAndUserId(int page,int limit,int valueId,int userId);
    BaseRespVo deleteComment(DeleteComment deletComment);
}
