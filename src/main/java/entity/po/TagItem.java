package entity.po;

import java.util.Date;

public class TagItem {
    private Integer tiid;

    private Date tidate;

    private Integer tiagree;

    private Integer videoId;

    private String userId;

    private Integer tagId;

    public Integer getTiid() {
        return tiid;
    }

    public void setTiid(Integer tiid) {
        this.tiid = tiid;
    }

    public Date getTidate() {
        return tidate;
    }

    public void setTidate(Date tidate) {
        this.tidate = tidate;
    }

    public Integer getTiagree() {
        return tiagree;
    }

    public void setTiagree(Integer tiagree) {
        this.tiagree = tiagree;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}