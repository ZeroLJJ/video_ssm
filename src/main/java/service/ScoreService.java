package service;

import entity.bo.VideoBO;

import java.util.List;

/**
 * Created by Zero on 2017/2/26.
 */
public interface ScoreService {
    /**
     * 插入一条评分记录
     * @param userId 用户编号
     * @param num 分数
     * @param videoId 视频编号
     * @return 提示信息
     * @throws Exception
     */
    String insert(String userId, Integer num, Integer videoId) throws Exception;

    /**
     * 获取用户最近评分的视频列表（8个）
     * @param userId 用户编号
     * @return 视频列表
     * @throws Exception
     */
    List<VideoBO> getRecentlyScoreVideo(String userId) throws Exception;
}
