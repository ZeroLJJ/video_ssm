package service;

import entity.bo.ReplyBO;
import util.Page;

/**
 * Created by Zero on 2017/2/26.
 */
public interface ReplyService {
    /**
     * 获取回复信息（分页）
     * @param commentId 评论编号
     * @param pageNo 分页页数
     * @return 分页信息
     * @throws Exception
     */
    Page getReplysByPage(Integer commentId, Integer pageNo) throws Exception;

    /**
     * 插入一条回复
     * @param videoId 视频编号
     * @param commentId 评论编号
     * @param userId 用户编号
     * @param text 回复内容
     * @return 新添加的回复BO对象
     * @throws Exception
     */
    ReplyBO insertReply(Integer videoId, Integer commentId, String userId, String text) throws Exception;

    /**
     * 删除一条回复
     * @param videoId 视频编号
     * @param commentId 评论编号
     * @param replyId 回复编号
     * @return 返回影响条数
     * @throws Exception
     */
    Integer deleteReply(Integer replyId, Integer commentId, Integer videoId) throws Exception;
}
