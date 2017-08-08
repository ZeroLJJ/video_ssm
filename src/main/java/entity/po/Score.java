package entity.po;

import java.util.Date;

public class Score {
    private Integer scid;

    private Integer scnum;

    private Date scdate;

    private String userId;

    private Integer videoId;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Integer getScnum() {
        return scnum;
    }

    public void setScnum(Integer scnum) {
        this.scnum = scnum;
    }

    public Date getScdate() {
        return scdate;
    }

    public void setScdate(Date scdate) {
        this.scdate = scdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}