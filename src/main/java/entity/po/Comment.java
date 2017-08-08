package entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comment {
    private Integer cid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cdate;

    private String ctext;

    private Integer cagree;

    private Integer creply;

    private String userId;

    private Integer videoId;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCtext() {
        return ctext;
    }

    public void setCtext(String ctext) {
        this.ctext = ctext == null ? null : ctext.trim();
    }

    public Integer getCagree() {
        return cagree;
    }

    public void setCagree(Integer cagree) {
        this.cagree = cagree;
    }

    public Integer getCreply() {
        return creply;
    }

    public void setCreply(Integer creply) {
        this.creply = creply;
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