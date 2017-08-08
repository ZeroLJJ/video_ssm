package service;

import entity.bo.BigTypeBO;
import entity.bo.VideoBO;
import entity.po.Video;
import util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/2/24.
 */
public interface VideoService {
    /**
     * 获取视频详情信息，增加点击量，并添加播放记录
     * @param videoId 视频编号
     * @param userId 用户编号
     * @return 视频详情信息
     * @throws Exception
     */
    VideoBO getVideoDetail(Integer videoId, String userId) throws Exception;

    /**
     * 获取视频信息（分页）
     * @param condition 搜索条件
     * （可能包含以下条件
     *  name 搜索名称
     *  bigType 视频大类
     *  smallType 视频小类
     *  orderType 排序类型
     *  duration_level 时长类型 )
     * @param pageNo 分页页码
     * @param tag 标签编号
     * @return 分页信息
     */
    Page getVideoPage(Map<String, Object> condition, Integer pageNo, Integer tag) throws Exception;

    /**
     * 获取用户最近投稿的视频（8个）
     * @param userId 用户编号
     * @return 视频列表
     * @throws Exception
     */
    List<VideoBO> getRecentlyPost(String userId) throws Exception;

    /**
     * 获取最新投稿的视频（3个）
     * @return 视频列表
     * @throws Exception
     */
    List<VideoBO> getRecentlyPost() throws Exception;

    /**
     * 通过投稿人编号和页码获取对应的稿件视频分页信息
     * @param userId 投稿人编号
     * @param pageNo 页码
     * @return 稿件视频分页信息
     * @throws Exception
     */
    Page getVideoPageByPost(String userId, Integer pageNo) throws Exception;

    /**
     * 查找最近热门视频(三天，前5名)
     * @return 热门视频列表
     * @throws Exception
     */
    List<VideoBO> getHotVideo() throws Exception;

    /**
     * 获取各类型的首页信息
     * @return 随机视频列表和排行信息
     * @throws Exception
     */
    List<BigTypeBO> getTypeInformation() throws Exception;

    /**
     * 获取某类型的热门视频
     * @param bigTypeId 类型编号
     * @return 热门视频
     * @throws Exception
     */
    List<VideoBO> getHotByType(Integer bigTypeId) throws Exception;

    /**
     * 获取某类型的随机视频
     * @param bigTypeId 类型编号
     * @return 随机视频
     * @throws Exception
     */
    List<VideoBO> getRandomByType(Integer bigTypeId) throws Exception;

    /**
     * 视频转码
     * @param ffmpegPath    转码工具的存放路径
     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath    格式转换后的的文件保存路径
     * @param mediaPicPath    截图保存路径
     * @return
     * @throws Exception
     */
    Boolean executeCodecs(String ffmpegPath, String upFilePath,
                          String codcFilePath, String mediaPicPath)throws Exception;

    /**
     * 获取视频总时间
     * @param videoPath    视频路径
     * @param ffmpegPath   ffmpeg路径
     * @return
     * @throws Exception
     */
    Integer getVideoTime(String videoPath, String ffmpegPath) throws Exception;

    /**
     * 保存文件
     * @param video
     * @return
     * @throws Exception
     */
    Boolean saveVideo(Video video) throws Exception;

}
