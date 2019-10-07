package com.wuyan.mall.service.commentSerivice;

import com.wuyan.mall.bean.Accept.DeleteComment;
import com.wuyan.mall.bean.Comment;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.vo.CommentCountVo;
import com.wuyan.wx.vo.CommentVo;

import java.util.List;

public interface CommentService {
    BaseRespVo listAllComment(int page, int limit);
    BaseRespVo listCommentByValueIdAndUserId(int page,int limit,int valueId,int userId);
    BaseRespVo deleteComment(DeleteComment deletComment);
    CommentVo selectByGoodsId(int goodsId);
    CommentCountVo countComment(int valueId,int type);
}
