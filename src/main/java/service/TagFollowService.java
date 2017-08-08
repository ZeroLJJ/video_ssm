package service;

import entity.bo.TagFollowBO;

import java.util.List;

/**
 * Created by Zero on 2017/4/10.
 */
public interface TagFollowService {
    /**
     * 插入一条标签关注信息
     * @param userId 用户编号
     * @param tagId 便签编号
     * @return 提示信息
     * @throws Exception
     */
    String insertFollow(String userId, Integer tagId) throws Exception;

    /**
     * 删除一条标签关注信息
     * @param userId 用户编号
     * @param tagId 便签编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteFollow(String userId, Integer tagId) throws Exception;

    /**
     * 获取某个用户关注的标签信息列表
     * @param userId 用户编号
     * @return 标签信息列表
     * @throws Exception
     */
    List<TagFollowBO> getTag(String userId) throws Exception;
}
