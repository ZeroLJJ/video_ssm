package service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/3/20.
 */
public interface DanmuService {
    /**
     *
     * @param videoId 视频编号
     * @return List<Map<String,Object>> 弹幕展示列表
     * @throws Exception
     */
    List<Map<String,Object>> getDanmuList(Integer videoId) throws Exception;

    /**
     *  保存一条弹幕信息
     * @param danmuJSON 弹幕信息（JSON格式）
     * @param videoId 视频编号
     * @param userId 用户编号
     * @throws Exception
     */
    void saveDanmu(JSONObject danmuJSON, Integer videoId, String userId) throws Exception;
}
