package service.impl;

import dao.custom.PlayLogCustomMapper;
import dao.simple.PlayLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.bo.PlayLogBO;
import entity.bo.VideoBO;
import service.PlayLogService;
import util.DateUtil;

import java.util.*;

import static util.Constant.*;

/**
 * Created by Zero on 2017/2/25.
 */
@Service
public class PlayLogServiceImpl extends BaseService implements PlayLogService {
    @Autowired
    private PlayLogMapper playLogMapper;

    @Autowired
    private PlayLogCustomMapper playLogCustomMapper;

    @Override
    public List<VideoBO> getRank(String videoType,String rankType) throws Exception {
        Map condition = new HashMap<String, Object>();   //存放条件
        String beginTime = DateUtil.getToday();   //开始时间(默认为今天)
        String endTime = DateUtil.getToday();   //结束时间
        //获取对应视频类型编号
        switch (videoType){
            case VIDEO_TYPE_ALL :
                condition.put("videoType", null);
                break;
            case VIDEO_TYPE_ANIMEL :
                condition.put("videoType", 1);
                break;
            case VIDEO_TYPE_MOVIE :
                condition.put("videoType", 2);
                break;
            case VIDEO_TYPE_LIFE :
                condition.put("videoType", 3);
                break;
            case VIDEO_TYPE_GAME :
                condition.put("videoType", 4);
                break;
            default:
                System.out.println("没有匹配到该类型");
        }
        //获取对应排行类型的开始时间
        switch (rankType){
            case RANK_TYPE_DAY :
                beginTime = DateUtil.getPrevDay();
                break;
            case RANK_TYPE_WEEK :
                beginTime = DateUtil.getPrevWeek();
                break;
            case RANK_TYPE_MONTH :
                beginTime = DateUtil.getPrevMonth();
                break;
            default:
                System.out.println("没有匹配到该类型");
        }
        condition.put("beginTime", beginTime);
        condition.put("endTime", endTime);
        List<VideoBO> videoList = playLogCustomMapper.getRank(condition);
        return videoList;
    }

    @Override
    public List<PlayLogBO> getHistory(String userId) throws Exception {
        List<PlayLogBO> history = playLogCustomMapper.getHistory(userId);
        return history;
    }
}
