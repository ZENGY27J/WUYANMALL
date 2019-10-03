package com.wuyan.mall.service.commentSerivice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Accept.DeleteComment;
import com.wuyan.mall.bean.Comment;
import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.CommentExample;
import com.wuyan.mall.mapper.CommentMapper;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.ListCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BaseRespVo baseRespVo;

    @Override
    public BaseRespVo listAllComment(int page, int limit) {
        //新建view Object
        ListCommentVo listCommentVo = new ListCommentVo();


        //查找
        CommentExample commentExample=new CommentExample();
        CommentExample.Criteria criteria=commentExample.createCriteria();

        // 分页
        PageHelper.startPage(page,limit);
        // 按照插入的时间排序
        commentExample.setOrderByClause("add_time desc");
        // 查找goods存储在list中
        List<Comment> commentsList=commentMapper.selectByExample(commentExample);

        // 查询goods的总数
        PageInfo<Comment> commentPageInfo=new PageInfo(commentsList);
        long total=commentPageInfo.getTotal();

        // 给view goods 赋值
        listCommentVo.setTotal(total);
        listCommentVo.setItems(commentsList);

        // 给baseRespVo 赋值
        baseRespVo.setData(listCommentVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");

        return baseRespVo;
    }

    @Override
    public BaseRespVo listCommentByValueIdAndUserId(int page, int limit, int valueId, int userId) {
        //新建view Object
        ListCommentVo listCommentVo = new ListCommentVo();


        //查找
        CommentExample commentExample=new CommentExample();
        CommentExample.Criteria criteria=commentExample.createCriteria();

        // 分页
        PageHelper.startPage(page,limit);
        // 按照插入的时间排序
        commentExample.setOrderByClause("add_time desc");
        if (userId!=0){
            criteria.andUserIdEqualTo(userId);
        }
        if (valueId!=0){
            criteria.andValueIdEqualTo(valueId);
        }
        // 查找goods存储在list中
        List<Comment> commentsList=commentMapper.selectByExample(commentExample);

        // 查询goods的总数
        PageInfo<Comment> commentPageInfo=new PageInfo(commentsList);
        long total=commentPageInfo.getTotal();

        // 给view goods 赋值
        listCommentVo.setTotal(total);
        listCommentVo.setItems(commentsList);

        // 给baseRespVo 赋值
        baseRespVo.setData(listCommentVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");

        return baseRespVo;

    }

    @Override
    public BaseRespVo deleteComment(DeleteComment deleteComment) {
        CommentExample commentExample=new CommentExample();
        CommentExample.Criteria criteria=commentExample.createCriteria();
        criteria.andIdEqualTo(deleteComment.getId());
        Comment comment = new Comment();
        comment.setDeleted(true);
        commentMapper.updateByExampleSelective(comment,commentExample);

        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
