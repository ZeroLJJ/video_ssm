package service.impl;

import dao.custom.ScoreCustomMapper;
import dao.simple.ScoreMapper;
import dao.simple.UserMapper;
import dao.simple.VideoMapper;
import entity.bo.VideoBO;
import entity.po.Score;
import entity.po.User;
import entity.po.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ScoreService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/2/26.
 */
@Service
public class ScoreServiceImpl extends BaseService implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScoreCustomMapper scoreCustomMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public String insert(String userId, Integer num, Integer videoId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        //比对用户硬币数和投币数
        if(user.getUcoin() >= num) {
            //设置查询评分记录的条件，用于判断是否投过币
            Map condition = new HashMap<String, Object>();
            condition.put("uid", userId);
            condition.put("vid", videoId);
            Score score = scoreCustomMapper.selectByUserAndVideo(condition);
            //为空表示没投过
            if(score == null) {
                //插入一条评分记录
                score = new Score();
                score.setScdate(new Date());
                score.setUserId(userId);
                score.setScnum(num);
                score.setVideoId(videoId);
                int n = scoreMapper.insert(score);
                if (n > 0) {  //表示插入成功
                    //扣除相应用户的硬币
                    User updateUser = new User();
                    updateUser.setUid(userId);
                    updateUser.setUcoin(user.getUcoin() - num);
                    userMapper.updateByPrimaryKeySelective(updateUser);
                    //加上相应视频的硬币数
                    Video video = videoMapper.selectByPrimaryKey(videoId);
                    Video updateVideo = new Video();
                    updateVideo.setVid(videoId);
                    updateVideo.setVscore(video.getVscore() + num);
                    videoMapper.updateByPrimaryKeySelective(updateVideo);
//                    并发处理
//                    AtomicInteger atomicInteger = new AtomicInteger();
//                    atomicInteger.incrementAndGet();
                    return "投币成功";
                }
                return "投币失败";
            }
            return "您已投过币，请勿重复投币";
        }
        return "用户硬币不足，投币失败";
    }

    @Override
    public List<VideoBO> getRecentlyScoreVideo(String userId) throws Exception {
        List<VideoBO> videoList = scoreCustomMapper.getRecentlyScoreVideo(userId);
        return videoList;
    }
}
