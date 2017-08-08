package dao.custom;

import entity.bo.UserFollowBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/21.
 */
public interface UserFollowCustomMapper {
    /**
     * 根据关注用户和被关注用户编号获取关注信息统计
     * @param condition 条件
     * param follow_uid 关注用户编号
     * param followed_uid 被关注用户编号
     * @return 统计数
     * @throws Exception
     */
    Integer countByFollow(Map condition) throws Exception;

    /**
     * 根据关注用户和被关注用户编号删除关注信息
     * @param condition 条件
     * param follow_uid 关注用户编号
     * param followed_uid 被关注用户编号
     * @return 删除条数
     * @throws Exception
     */
    Integer deleteFollow(Map condition) throws Exception;

    /**
     * 通过关注人编号获取关注信息列表
     * @param follow_uid 关注人编号
     * @return 关注信息列表
     * @throws Exception
     */
    List<UserFollowBO> selectByFollow(String follow_uid) throws Exception;

    /**
     * 通过被关注人编号获取粉丝信息列表
     * @param followed_uid 被关注人编号
     * @return 粉丝信息列表
     * @throws Exception
     */
    List<UserFollowBO> selectByFollowed(String followed_uid) throws Exception;

}
