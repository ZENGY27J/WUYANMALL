package com.wuyan.mall.service.commentSerivice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuyan.mall.bean.Accept.DeleteComment;
import com.wuyan.mall.bean.Comment;
import com.wuyan.mall.bean.CommentExample;
import com.wuyan.mall.bean.User;
import com.wuyan.mall.mapper.CommentMapper;
import com.wuyan.mall.mapper.UserMapper;
import com.wuyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserMapper userMapper;

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

        /*
        将delete中改为true
        CommentExample commentExample=new CommentExample();
        CommentExample.Criteria criteria=commentExample.createCriteria();
        criteria.andIdEqualTo(deleteComment.getId());
        Comment comment = new Comment();
        comment.setDeleted(true);
        commentMapper.updateByExampleSelective(comment,commentExample);*/

        commentMapper.deleteByPrimaryKey(deleteComment.getId());

        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * @return: 返回专题的评论信息
     * @Author: fangbo
     * @Date: 2019/10/7
     */
    @Override
    public TopicCommentVo listTopicComment(String valueId, String type, String showType, String page, String size) {
        TopicCommentVo topicCommentVo = new TopicCommentVo();
        List<TopicCommentDataVo> data = new ArrayList<>();

        int valueIdInt = Integer.parseInt(valueId);
        byte typeByte = Byte.parseByte(type);
        int showTypeInt = Integer.parseInt(showType);
        int pageInt = Integer.parseInt(page);
        int sizeInt = Integer.parseInt(size);

        PageHelper.startPage(pageInt, sizeInt);
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andValueIdEqualTo(valueIdInt).andTypeEqualTo(typeByte);
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        for (Comment comment : comments) {
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            UserInfo4Comment userInfo4Comment = new UserInfo4Comment();
            userInfo4Comment.setNickName(user.getNickname());
            userInfo4Comment.setAvatarUrl(user.getAvatar());

            //将要显示的每一条评论的用户信息, 添加日期, 图片, 和评论内容添加进来
            TopicCommentDataVo topicCommentDataVo = new TopicCommentDataVo();
            topicCommentDataVo.setUserInfo(userInfo4Comment);
            topicCommentDataVo.setAddTime(comment.getAddTime());
            topicCommentDataVo.setPicList(comment.getPicUrls());
            topicCommentDataVo.setContent(comment.getContent());
            data.add(topicCommentDataVo);
        }

        topicCommentVo.setData(data);
        topicCommentVo.setCount(comments.size());
        topicCommentVo.setCurrentPage(pageInt);

        return topicCommentVo;
    }

    @Override
    public Comment addComment(Comment comment, int userId) {
        comment.setAddTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setUserId(userId);
        commentMapper.insert(comment);
        return comment;
    }

}
