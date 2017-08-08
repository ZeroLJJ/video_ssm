package service.impl;

import dao.custom.AgreeCustomMapper;
import dao.simple.AgreeMapper;
import dao.simple.CommentMapper;
import dao.simple.ReplyMapper;
import dao.simple.TagItemMapper;
import entity.po.Agree;
import entity.po.Comment;
import entity.po.Reply;
import entity.po.TagItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AgreeService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zero on 2017/4/20.
 */
@Service
public class AgreeServiceImpl extends BaseService implements AgreeService{
    @Autowired
    private AgreeMapper agreeMapper;

    @Autowired
    private AgreeCustomMapper agreeCustomMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private TagItemMapper tagItemMapper;

    @Override
    public String insertAgreeLog(String userId, String type, Integer targetId) throws Exception {
        String agreeType;  //存储点赞类型代码
        switch (type){   //匹配点赞类型
            case "评论" :
                agreeType = "0";
                break;
            case "回复" :
                agreeType = "1";
                break;
            case "标签" :
                agreeType = "2";
                break;
            default:
                logger.info("输入的点赞类型不匹配");
                return "输入的点赞类型不匹配";
        }
        //组装匹配条件
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userId", userId);
        condition.put("type", agreeType);
        condition.put("targetId", targetId);
        //进入点赞记录表匹配是否已经点赞过同一数据
        int count = agreeCustomMapper.getCountAgree(condition);
        if(count > 0){
            return "您已点赞过，请勿重复点赞";
        }
        //设置点赞记录对象
        Agree agree = new Agree();
        agree.setAdate(new Date());
        agree.setAtype(agreeType);
        agree.setUserId(userId);
        agree.setTargetId(targetId);
        int num = agreeMapper.insert(agree);  //插入条数，大于0表示成功
        if(num > 0){
            switch (type){
                case "评论" :   //对评论点赞数+1
                    Comment comment = commentMapper.selectByPrimaryKey(targetId);
                    Comment updateComment = new Comment();   //用于更新的对象
                    updateComment.setCid(targetId);
                    updateComment.setCagree(comment.getCagree() + 1);
                    commentMapper.updateByPrimaryKeySelective(updateComment);
                    break;
                case "回复" :   //对回复点赞数+1
                    Reply reply = replyMapper.selectByPrimaryKey(targetId);
                    Reply updateReply = new Reply();
                    updateReply.setRid(targetId);
                    updateReply.setRagree(reply.getRagree() + 1);
                    replyMapper.updateByPrimaryKeySelective(updateReply);
                    break;
                case "标签" :   //对标签点赞数+1
                    TagItem tagItem = tagItemMapper.selectByPrimaryKey(targetId);
                    TagItem updateTagItem = new TagItem();
                    updateTagItem.setTiid(targetId);
                    updateTagItem.setTiagree(tagItem.getTiagree() + 1);
                    tagItemMapper.updateByPrimaryKeySelective(updateTagItem);
                    break;
                default:
                    logger.info("点赞类型异常");
            }
            return "点赞成功";
        }
        return "点赞失败";
    }

}
