package dao.custom;

import entity.bo.VideoBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/2/26.
 */
public interface VideoCustomMapper {
    /**
     * 获取某个页面的视频列表
     * @param condition 使用Map传入查询条件，用于视频分页
     * @return 返回视频列表
     * @throws Exception
     */
    List<VideoBO> getVideoPage(Map condition) throws Exception;

    /**
     * 获取符合条件的视频记录数
     * @param condition 视频条件
     * @return 视频记录数
     * @throws Exception
     */
    Integer getVideosCount(Map condition) throws Exception;

    /**
     * 获取视频详情信息
     * @param vid 视频编号
     * @return 返回视频详情信息
     * @throws Exception
     */
    VideoBO getVideoDetail(Integer vid) throws Exception;

    /**
     * 获取某个页面的视频列表
     * @param condition 传入查询条件，用于视频分页
     * @return 返回视频列表
     * @throws Exception
     */
    List<VideoBO> getVideoPageByTag(Map condition) throws Exception;

    /**
     * 获取符合条件的视频记录数
     * @param condition 视频条件
     * @return 视频记录数
     * @throws Exception
     */
    Integer getVideosCountByTag(Map condition) throws Exception;

    /**
     * 获取最近投稿的视频
     * @param condition 查询条件
     * @return 视频列表
     * @throws Exception
     */
    List<VideoBO> getVideoByRecentlyPost(Map condition) throws Exception;

    /**
     * 获取某个投稿人的投稿记录数
     * @param uid 投稿人编号
     * @return 记录数
     * @throws Exception
     */
    Integer countVideoByPost(String uid) throws Exception;

    /**
     * 获取某个投稿人某页的投稿列表
     * @param condition 查询条件
     * @return 投稿列表
     * @throws Exception
     */
    List<VideoBO> getVideoPageByPost(Map condition) throws Exception;

    /**
     * 获取热门视频
     * @param condition 查询条件
     * @return 热门视频列表
     * @throws Exception
     */
    List<VideoBO> getHotVideo(Map condition) throws Exception;

    /**
     * 获取各类型的随机视频
     * @param condition 查询条件
     * @return 随机视频列表
     * @throws Exception
     */
    List<VideoBO> getRandom(Map condition) throws Exception;

    /**
     * 获取符合条件的视频列表
     * @param condition 查询条件
     * @return 视频列表
     * @throws Exception
     */
    List<VideoBO> getAllVideo(Map condition) throws Exception;
}
