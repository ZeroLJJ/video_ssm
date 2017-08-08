package entity.bo;

import entity.po.BigType;

import java.util.List;

/**
 * 视频大类扩展类
 * Created by Zero on 2017/4/2.
 */
public class BigTypeBO extends BigType{
    //视频大类所拥有的视频小类列表
    private List<SmallTypeBO> smallTypeBOList;
    //该视频大类近期投稿视频数量
    private Integer countRecentlyPost;
    //该视频大类获取的随机视频列表
    private List<VideoBO> randomVideoList;
    //该视频大类的排行信息
    private List<VideoBO> rankVideoList;
    //存储每月播放统计
    private List<MonthPlayLog> monthPlayLogs;

    public List<MonthPlayLog> getMonthPlayLogs() {
        return monthPlayLogs;
    }

    public void setMonthPlayLogs(List<MonthPlayLog> monthPlayLogs) {
        this.monthPlayLogs = monthPlayLogs;
    }

    public List<VideoBO> getRankVideoList() {
        return rankVideoList;
    }

    public void setRankVideoList(List<VideoBO> rankVideoList) {
        this.rankVideoList = rankVideoList;
    }

    public List<VideoBO> getRandomVideoList() {
        return randomVideoList;
    }

    public void setRandomVideoList(List<VideoBO> randomVideoList) {
        this.randomVideoList = randomVideoList;
    }

    public Integer getCountRecentlyPost() {
        return countRecentlyPost;
    }

    public void setCountRecentlyPost(Integer countRecentlyPost) {
        this.countRecentlyPost = countRecentlyPost;
    }

    public List<SmallTypeBO> getSmallTypeBOList() {
        return smallTypeBOList;
    }

    public void setSmallTypeBOList(List<SmallTypeBO> smallTypeBOList) {
        this.smallTypeBOList = smallTypeBOList;
    }
}
