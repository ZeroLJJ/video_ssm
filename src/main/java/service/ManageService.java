package service;

import entity.bo.TagBO;
import entity.bo.UserBO;
import entity.bo.VideoBO;
import entity.po.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/5/1.
 */
public interface ManageService {
    /**
     * 获取各类型每月播放量
     * @return 各类型每月播放量
     * @throws Exception
     */
    List<Map<String,Object>> getCount() throws Exception;

    /**
     * 获取登录的用户
     * @param id 用户账号
     * @param password 用户密码
     * @return 查询出的用户
     * @throws Exception
     */
    User selectUser(String id, String password) throws Exception;

    /**
     * 获取视频列表
     * @param minTime 起始时间
     * @param maxTime 结束时间
     * @param videoName 视频名称
     * @return 视频列表
     * @throws Exception
     */
    List<VideoBO> getAllVideo(Date minTime, Date maxTime,
                              String videoName) throws Exception;

    /**
     * 视频批量删除
     * @param idList 视频编号列表
     * @return 提示信息
     * @throws Exception
     */
    String deleteMultiVideo(Integer[] idList) throws Exception;

    /**
     * 冻结视频
     * @param videoId 视频编号
     * @return 提示信息
     * @throws Exception
     */
    String updateVideoFrozen(Integer videoId) throws Exception;

    /**
     * 解冻视频
     * @param videoId 视频编号
     * @return 提示信息
     * @throws Exception
     */
    String updateVideoUnfrozen(Integer videoId) throws Exception;

    /**
     * 审核视频
     * @param videoId 视频编号
     * @param pass 是否通过
     * @return 提示信息
     * @throws Exception
     */
    String checkVideo(Integer videoId, Boolean pass) throws Exception;

    /**
     * 删除视频
     * @param videoId 视频编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteVideo(Integer videoId) throws Exception;

    /**
     * 获取标签列表
     * @param tagName 标签名称
     * @return 标签列表
     * @throws Exception
     */
    List<TagBO> getAllTag(String tagName) throws Exception;

    /**
     * 标签批量删除
     * @param idList 标签编号列表
     * @return 提示信息
     * @throws Exception
     */
    String deleteMultiTag(Integer[] idList) throws Exception;

    /**
     * 删除标签
     * @param tagId 标签编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteTag(Integer tagId) throws Exception;

    /**
     * 获取符合条件的用户列表
     * @param userName 用户名称
     * @return 用户列表
     * @throws Exception
     */
    List<UserBO> getAllUser(String userName) throws Exception;

    /**
     * 用户批量删除
     * @param idList 用户编号列表
     * @return 提示信息
     * @throws Exception
     */
    String deleteMultiUser(String[] idList) throws Exception;

    /**
     * 冻结用户
     * @param userId 用户编号
     * @return 提示信息
     * @throws Exception
     */
    String updateUserFrozen(String userId) throws Exception;

    /**
     * 解冻用户
     * @param userId 用户编号
     * @return 提示信息
     * @throws Exception
     */
    String updateUserUnfrozen(String userId) throws Exception;

    /**
     * 删除用户
     * @param userId 用户编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteUser(String userId) throws Exception;

    /**
     * 获取符合条件的所有用户包括管理员
     * @param userName 用户昵称
     * @return 所有用户
     * @throws Exception
     */
    List<UserBO> getAllUserIncludeAdmin(String userName) throws Exception;

    /**
     * 任命用户为管理员
     * @param userId 用户编号
     * @return 提示信息
     * @throws Exception
     */
    String updateUserToAdmin(String userId) throws Exception;

    /**
     * 撤销该用户的管理员权限
     * @param userId 用户编号
     * @return 提示信息
     * @throws Exception
     */
    String updateAdminToUser(String userId) throws Exception;
}
