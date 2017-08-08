package dao.custom;

import entity.bo.MonthPlayLog;
import entity.bo.PlayLogBO;
import entity.bo.VideoBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/12.
 */
public interface PlayLogCustomMapper {
    /**
     * 获取视频排行信息
     * @param condition 条件（包括视频类型，时段）
     * @return 排行信息列表
     * @throws Exception
     */
    List<VideoBO> getRank(Map condition) throws Exception;

    /**
     * 获取某用户历史播放记录
     * @param userId 用户编号
     * @return 历史播放信息列表
     * @throws Exception
     */
    List<PlayLogBO> getHistory(String userId) throws Exception;

    /**
     * 获取符合条件的每月播放统计信息
     * @param condition 查询条件
     * @return 每月播放统计信息
     * @throws Exception
     */
    List<MonthPlayLog> countMonth(Map condition) throws Exception;
}
