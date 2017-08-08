package dao.custom;

import entity.bo.TagFollowBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/21.
 */
public interface TagFollowCustomMapper {
    /**
     * 根据用户和标签编号获取关注信息统计
     * @param condition 条件
     * param uid 用户编号
     * param tid 标签编号
     * @return 统计数
     * @throws Exception
     */
    Integer countByUserAndTag(Map condition) throws Exception;

    /**
     * 根据用户和标签编号删除关注信息
     * @param condition 条件
     * param uid 用户编号
     * param tid 标签编号
     * @return 删除条数
     * @throws Exception
     */
    Integer deleteFollow(Map condition) throws Exception;

    /**
     * 通过用户编号获取关注的标签信息列表
     * @param uid 用户编号
     * @return 标签信息列表
     * @throws Exception
     */
    List<TagFollowBO> selectListByUserId(String uid) throws Exception;
}
