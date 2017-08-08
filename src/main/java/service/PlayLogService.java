package service;

import entity.bo.PlayLogBO;
import entity.bo.VideoBO;

import java.util.List;

/**
 * Created by Zero on 2017/2/25.
 */
public interface PlayLogService {
    /**
     * 获取视频日排行信息
     * @param videoType 视频类型
     * @param rankType 排行类型
     * @return 排行信息
     * @throws Exception
     */
    List<VideoBO> getRank(String videoType, String rankType) throws Exception;

    /**
     * 获取用户的历史播放记录
     * @param userId 用户变化
     * @return 历史播放记录的信息列表
     * @throws Exception
     */
    List<PlayLogBO> getHistory(String userId) throws Exception;

}
