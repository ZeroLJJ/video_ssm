package entity.po;

import java.util.Date;

public class TagFollow {
    private Integer tfid;

    private Date tftime;

    private String userId;

    private Integer tagId;

    public Integer getTfid() {
        return tfid;
    }

    public void setTfid(Integer tfid) {
        this.tfid = tfid;
    }

    public Date getTftime() {
        return tftime;
    }

    public void setTftime(Date tftime) {
        this.tftime = tftime;
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