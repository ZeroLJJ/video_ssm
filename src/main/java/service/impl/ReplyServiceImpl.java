package service.impl;

import dao.custom.ReplyCustomMapper;
import dao.simple.CommentMapper;
import dao.simple.ReplyMapper;
import dao.simple.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.bo.ReplyBO;
import entity.po.Comment;
import entity.po.Reply;
import entity.po.Video;
import service.ReplyService;
import util.Page;

import java.util.*;

/**
 * Created by Zero on 2017/2/26.
 */
@Service
public class ReplyServiceImpl extends BaseService implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ReplyCustomMapper replyCustomMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Page getReplysByPage(Integer commentId, Integer pageNo) throws Exception {
        int pageSize = 5;   //每页记录数
        Map<String, Object> map = new HashMap<String, Object>();   // 存放所要搜索的相关信息
        List<ReplyBO> ReplyList = new ArrayList<ReplyBO>();   // 当前页的视频列表
        int offset = Page.countOffset(pageSize, pageNo);    // 当前页开始记录
        map.put("offset",offset);
        map.put("pageSize",pageSize);
        map.put("cid",commentId);
        int allRow = replyCustomMapper.getReplysCount(commentId);    // 总记录数
        int currentPage = Page.countCurrentPage(pageNo);    // 当前页
        int totalPage = Page.countTotalPage(pageSize,allRow);   // 总页数
        ReplyList = replyCustomMapper.getReplysByPage(map);  //查询视频列表
        Page page = new Page();
        //开始设置页面信息
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(ReplyList);
        Page.init(page, currentPage, totalPage);
        return page;
    }

    @Override
    public ReplyBO insertReply(Integer videoId, Integer commentId, String userId, String text) throws Exception {
        //创建待插入的评论对象
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setRdate(new Date());
        reply.setUserId(userId);
        reply.setRtext(text);
        reply.setRagree(0);
        int num = replyCustomMapper.insert(reply); //插入影响条数
        ReplyBO replyBO = null; //新插入的ReplyBO对象
        if(num == 1) {  //等于1表示插入成功
            //视频评论数和回复数+1
            Comment comment = commentMapper.selectByPrimaryKey(commentId);
            Comment updateComment = new Comment();
            updateComment.setCid(commentId);
            updateComment.setCreply(comment.getCreply() + 1);
            commentMapper.updateByPrimaryKeySelective(updateComment);
            Video video = videoMapper.selectByPrimaryKey(videoId);
            Video updateVideo = new Video();   //用于更新的对象
            updateVideo.setVid(videoId);
            updateVideo.setVcomment(video.getVcomment() + 1);
            videoMapper.updateByPrimaryKeySelective(updateVideo);
            //构建新插入的ReplyBO对象
            replyBO = replyCustomMapper.getReplyByPrimary(reply.getRid());
        }
        return replyBO;
    }

    @Override
    public Integer deleteReply(Integer replyId, Integer commentId, Integer videoId) throws Exception {
        //删除回复，返回影响数
        int num = replyMapper.deleteByPrimaryKey(replyId);
        //将影响数更新至视频和评论
        if(num > 0) {
            Comment comment = commentMapper.selectByPrimaryKey(commentId);
            Comment updateComment = new Comment();
            updateComment.setCid(commentId);
            updateComment.setCreply(comment.getCreply() - num);
            commentMapper.updateByPrimaryKeySelective(updateComment);
            Video video = videoMapper.selectByPrimaryKey(videoId);
            Video updateVideo = new Video();   //用于更新的对象
            updateVideo.setVid(videoId);
            updateVideo.setVcomment(video.getVcomment() - num);
            videoMapper.updateByPrimaryKeySelective(updateVideo);
        }
        return num;
    }

}
