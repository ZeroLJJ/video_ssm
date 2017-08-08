package entity.vo;

import entity.bo.VideoBO;

import java.util.List;

/**
 * 视频包装类
 * Created by Zero on 2017/3/12.
 */
public class VideoVO {
    private VideoBO video;

    private List<VideoBO> videoList;

    public VideoBO getVideo() {
        return video;
    }

    public void setVideo(VideoBO video) {
        this.video = video;
    }

    public List<VideoBO> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoBO> videoList) {
        this.videoList = videoList;
    }
}
