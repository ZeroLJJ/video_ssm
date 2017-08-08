package entity.bo;

import entity.po.PlayLog;

/**
 * 播放记录扩展类
 * Created by Zero on 2017/4/2.
 */
public class PlayLogBO extends PlayLog {
    private VideoBO videoBO;  //播放记录所对应的视频信息

    public VideoBO getVideoBO() {
        return videoBO;
    }

    public void setVideoBO(VideoBO videoBO) {
        this.videoBO = videoBO;
    }
}
