package service.impl;

import com.alibaba.fastjson.JSONObject;
import dao.custom.DanmuCustomMapper;
import dao.simple.DanmuMapper;
import dao.simple.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.bo.DanmuBO;
import entity.po.Danmu;
import entity.po.Video;
import service.DanmuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/3/20.
 */
@Service
public class DanmuServiceImpl extends BaseService implements DanmuService {
    @Autowired
    DanmuMapper danmuMapper;

    @Autowired
    DanmuCustomMapper danmuCustomMapper;

    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Map<String, Object>> getDanmuList(Integer videoId) throws Exception {
        //查询出视频的弹幕信息列表
        List<DanmuBO> list = danmuCustomMapper.selectByVideoId(videoId);
        //对弹幕信息列表进行处理，取出需要的信息
        List<Map<String,Object>> danmuList = new ArrayList<Map<String,Object>>(); //存放需要的弹幕信息
        for (DanmuBO item : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", item.getDtext());
            map.put("color", item.getDcolor());
            map.put("size", item.getDsize());
            map.put("position", item.getDposition());
            map.put("time", item.getDtime());
            danmuList.add(map);
        }
        return danmuList;
    }

    @Override
    public void saveDanmu(JSONObject danmuJSON, Integer videoId, String userId) throws Exception {
        Danmu danmu = new Danmu();
        danmu.setDtext(danmuJSON.getString("text"));
        danmu.setDcolor(danmuJSON.getString("color"));
        danmu.setDposition(danmuJSON.getString("position"));
        danmu.setDsize(danmuJSON.getString("size"));
        danmu.setDtime(danmuJSON.getInteger("time"));
        danmu.setVideoId(videoId);
        if(userId != null && !userId.equals("")){
            danmu.setUserId(userId);
        }
        danmuMapper.insert(danmu);
        Video video = videoMapper.selectByPrimaryKey(videoId);
        Video updateVideo = new Video();   //用于更新的对象
        updateVideo.setVid(videoId);
        updateVideo.setVdanmu(video.getVdanmu() + 1);
        videoMapper.updateByPrimaryKeySelective(updateVideo);
    }
}
