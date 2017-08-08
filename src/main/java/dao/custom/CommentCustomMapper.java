package dao.custom;

import entity.bo.CommentBO;
import entity.po.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/5.
 */
public interface CommentCustomMapper {
    /**
     * 获取某个视频的评论列表
     * @param map 评论分页查询的条件（视频编号，起始数据，数据量）
     * @return 评论信息列表
     * @throws Exception
     */
    List<CommentBO> getCommentsByPage(Map map) throws Exception;

    /**
     * 获取属于某视频的评论记录数
     * @param vid 视频编号
     * @return 评论记录数
     * @throws Exception
     */
    Integer getCommentsCount(Integer vid) throws Exception;

    /**
     * 通过主键获取单个评论BO对象
     * @param cid 评论编号
     * @return 评论信息
     * @throws Exception
     */
    CommentBO getCommentByPrimary(Integer cid) throws Exception;

    /**
     * 插入评论，并回填主键
     * @param comment 评论对象
     * @return 插入条数
     * @throws Exception
     */
    int insert(Comment comment) throws Exception;
}
