package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import entity.bo.CommentBO;
import entity.bo.ReplyBO;
import service.CommentService;
import service.ReplyService;
import util.Page;

import java.util.List;

/**
 * Created by Zero on 2017/4/7.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController extends BaseController{
    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/getCommentList")
    @ResponseBody
    public Page getCommentList(Integer videoId, Integer pageNo) throws Exception{
        Page commentPage = commentService.getCommentsByPage(videoId, pageNo);
        for (CommentBO commentBO : (List<CommentBO>)commentPage.getList()) {
            //page页数肯定为第一页
            Page replyPage = replyService.getReplysByPage(commentBO.getCid(), 1);
            commentBO.setReplyPage(replyPage);
        }
        return commentPage;
    }

    @RequestMapping(value = "/getReplyList")
    @ResponseBody
    public Page getReplyList(Integer commentId, Integer pageNo) throws Exception{
        Page replyPage = replyService.getReplysByPage(commentId, pageNo);
        return replyPage;
    }

    @RequestMapping(value = "/insertComment")
    @ResponseBody
    public CommentBO insertComment(Integer videoId, String userId, String text) throws Exception{
        CommentBO commentBO  = commentService.insertComment(videoId, userId, text);
        if(commentBO != null){
            return commentBO;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/insertReply")
    @ResponseBody
    public ReplyBO insertReply(Integer videoId, Integer commentId, String userId, String text) throws Exception{
        ReplyBO replyBO = replyService.insertReply(videoId, commentId, userId, text);
        if(replyBO != null){
            return replyBO;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/delComment/{cid}")
    @ResponseBody
    public Integer delComment(@PathVariable("cid") Integer commentId, Integer videoId) throws Exception{
        int num = commentService .deleteComment(commentId, videoId);
        if(num > 0){
            return num;
        }else{
            return 0;
        }
    }

    @RequestMapping(value = "/{cid}/delReply/{rid}")
    @ResponseBody
    public Integer delReply(@PathVariable("rid") Integer replyId,@PathVariable("cid") Integer commentId,
                            Integer videoId) throws Exception{
        int num = replyService.deleteReply(replyId, commentId, videoId);
        if(num > 0){
            return num;
        }else{
            return 0;
        }
    }


}
