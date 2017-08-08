package dao.custom;

import entity.bo.VideoBO;
import entity.po.Score;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/21.
 */
public interface ScoreCustomMapper {
    /**
     * 通过用户和视频编号获取评分记录
     * @param condition（包含用户和视频编号）
     * @return 评分记录
     * @throws Exception
     */
    Score selectByUserAndVideo(Map condition) throws Exception;

    /**
     * 获取最近评分的视频信息（8个）
     * @param uid 用户编号
     * @return 视频信息列表
     * @throws Exception
     */
    List<VideoBO> getRecentlyScoreVideo(String uid) throws Exception;
}
