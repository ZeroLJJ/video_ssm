package service.impl;

import dao.custom.CommentCustomMapper;
import dao.custom.ReplyCustomMapper;
import dao.simple.CommentMapper;
import dao.simple.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.bo.CommentBO;
import entity.po.Comment;
import entity.po.Video;
import service.CommentService;
import util.Page;

import java.util.*;

/**
 * Created by Zero on 2017/2/26.
 */
@Service
public class CommentServiceImpl extends BaseService implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentCustomMapper commentCustomMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ReplyCustomMapper replyCustomMapper;

    @Override
    public Page getCommentsByPage(Integer videoId, Integer pageNo) throws Exception {
        int pageSize = 5;   //每页记录数
        Map<String, Object> condition = new HashMap<String, Object>();   // 存放所要搜索的相关信息
        List<CommentBO> commentList = new ArrayList<CommentBO>();   // 当前页的视频列表
        int offset = Page.countOffset(pageSize, pageNo);    // 当前页开始记录
        condition.put("offset",offset);
        condition.put("pageSize",pageSize);
        condition.put("vid",videoId);
        int allRow = commentCustomMapper.getCommentsCount(videoId);    // 总记录数
        int currentPage = Page.countCurrentPage(pageNo);    // 当前页
        int totalPage = Page.countTotalPage(pageSize,allRow);   // 总页数
        commentList = commentCustomMapper.getCommentsByPage(condition);  //查询视频列表
        Page page = new Page();
        //开始设置页面信息
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(commentList);
        Page.init(page, currentPage, totalPage);
        return page;
    }

    @Override
    public CommentBO insertComment(Integer videoId, String userId, String text) throws Exception {
        //创建待插入的评论对象
        Comment comment = new Comment();
        comment.setCdate(new Date());
        comment.setVideoId(videoId);
        comment.setUserId(userId);
        comment.setCtext(text);
        comment.setCagree(0);
        comment.setCreply(0);
        int num = commentCustomMapper.insert(comment); //插入影响条数
        CommentBO commentBO = null; //新插入的CommentBO对象
        if(num == 1) {   //等于1表示插入成功
            //视频评论数+1
            Video video = videoMapper.selectByPrimaryKey(videoId);
            Video updateVideo = new Video();   //用于更新的对象
            updateVideo.setVid(videoId);
            updateVideo.setVcomment(video.getVcomment() + 1);
            videoMapper.updateByPrimaryKeySelective(updateVideo);
            //构造返回的CommentBO对象
            commentBO = commentCustomMapper.getCommentByPrimary(comment.getCid());
        }
        return commentBO;
    }

    @Override
    public Integer deleteComment(Integer commentId, Integer videoId) throws Exception {
        //先删除该评论下所有依赖的回复
        int replyNum = replyCustomMapper.deleteByCommentId(commentId);
        //再删除评论
        int commentNum = commentMapper.deleteByPrimaryKey(commentId);
        //将删除的评论和回复数更新至对应的视频
        int num = 0;   //存储删除的评论和回复数
        if(replyNum > 0){
            num += replyNum;
        }
        if(commentNum > 0){
            num += commentNum;
        }
        Video video = videoMapper.selectByPrimaryKey(videoId);
        Video updateVideo = new Video();   //用于更新的对象
        updateVideo.setVid(videoId);
        updateVideo.setVcomment(video.getVcomment() - num);
        videoMapper.updateByPrimaryKeySelective(updateVideo);
        return num;
    }

}
