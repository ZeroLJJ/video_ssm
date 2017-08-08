package entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Reply {
    private Integer rid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date rdate;

    private String rtext;

    private Integer ragree;

    private String userId;

    private Integer commentId;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public String getRtext() {
        return rtext;
    }

    public void setRtext(String rtext) {
        this.rtext = rtext == null ? null : rtext.trim();
    }

    public Integer getRagree() {
        return ragree;
    }

    public void setRagree(Integer ragree) {
        this.ragree = ragree;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}