package dao.custom;

import entity.bo.DanmuBO;

import java.util.List;

/**
 * Created by Zero on 2017/4/13.
 */
public interface DanmuCustomMapper {
    /**
     * 根据视频编号获取弹幕信息列表
     * @param videoId 视频编号
     * @return 弹幕信息列表
     * @throws Exception
     */
    List<DanmuBO> selectByVideoId(Integer videoId) throws Exception;
}
