package service;

import entity.bo.CommentBO;
import util.Page;

/**
 * Created by Zero on 2017/2/26.
 */
public interface CommentService {
    /**
     * 获取评论信息（分页）
     * @param videoId 视频编号
     * @param pageNo 分页页数
     * @return 分页信息
     * @throws Exception
     */
    Page getCommentsByPage(Integer videoId, Integer pageNo) throws Exception;

    /**
     * 插入一条评论
     * @param videoId 视频编号
     * @param userId 用户编号
     * @param text 评论内容
     * @return 刚插入的评论的BO对象
     * @throws Exception
     */
    CommentBO insertComment(Integer videoId, String userId, String text) throws Exception;

    /**
     * 删除一条评论
     * @param videoId 视频编号
     * @param commentId 评论编号
     * @return 返回影响条数
     * @throws Exception
     */
    Integer deleteComment(Integer commentId, Integer videoId) throws Exception;
}
