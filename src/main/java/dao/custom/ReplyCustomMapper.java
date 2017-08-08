package dao.custom;

import entity.bo.ReplyBO;
import entity.po.Reply;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/6.
 */
public interface ReplyCustomMapper {
    /**
     * 获取某个评论的回复列表
     * @param map 评论分页查询的条件（评论编号，起始数据，数据量）
     * @return 回复信息列表
     * @throws Exception
     */
    List<ReplyBO> getReplysByPage(Map map) throws Exception;

    /**
     * 获取属于某评论的回复记录数
     * @param cid 评论编号
     * @return 回复记录数
     * @throws Exception
     */
    Integer getReplysCount(Integer cid) throws Exception;

    /**
     * 通过主键获取单个回复BO对象
     * @param rid 回复编号
     * @return 回复信息
     * @throws Exception
     */
    ReplyBO getReplyByPrimary(Integer rid) throws Exception;

    /**
     * 插入回复，并回填主键
     * @param reply
     * @return 插入条数
     * @throws Exception
     */
    Integer insert(Reply reply) throws Exception;

    /**
     * 删除某个评论下的所有回复
     * @param cid 评论编号
     * @return 返回影响数量
     * @throws Exception
     */
    Integer deleteByCommentId(Integer cid) throws Exception;
}
