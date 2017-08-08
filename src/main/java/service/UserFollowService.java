package service;

import entity.bo.UserFollowBO;

import java.util.List;

/**
 * Created by Zero on 2017/4/10.
 */
public interface UserFollowService {
    /**
     * 插入一条用户关注信息
     * @param follow_uid 关注用户编号
     * @param followed_uid 被关注用户编号
     * @return 提示信息
     * @throws Exception
     */
    String insertFollow(String follow_uid, String followed_uid) throws Exception;

    /**
     * 删除一条用户关注信息
     * @param follow_uid 关注用户编号
     * @param followed_uid 被关注用户编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteFollow(String follow_uid, String followed_uid) throws Exception;

    /**
     * 通过关注人编号获取关注信息列表
     * @param follow_uid 关注人编号
     * @return 关注信息列表
     * @throws Exception
     */
    List<UserFollowBO> getFollow(String follow_uid) throws Exception;

    /**
     * 通过被关注人编号获取粉丝信息列表
     * @param followed_uid 被关注人编号
     * @return 粉丝信息列表
     * @throws Exception
     */
    List<UserFollowBO> getFan(String followed_uid) throws Exception;

    /**
     * 通过关注人和被关注人编号统计关注记录数
     * @param follow_uid 关注人编号
     * @param followed_uid 被关注人编号
     * @return 记录数
     * @throws Exception
     */
    Integer countByFollow(String follow_uid, String followed_uid) throws Exception;
}

