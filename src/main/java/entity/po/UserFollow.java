package entity.po;

import java.util.Date;

public class UserFollow {
    private Integer ufid;

    private Date uftime;

    private String userFollowId;

    private String userFollowedId;

    public Integer getUfid() {
        return ufid;
    }

    public void setUfid(Integer ufid) {
        this.ufid = ufid;
    }

    public Date getUftime() {
        return uftime;
    }

    public void setUftime(Date uftime) {
        this.uftime = uftime;
    }

    public String getUserFollowId() {
        return userFollowId;
    }

    public void setUserFollowId(String userFollowId) {
        this.userFollowId = userFollowId == null ? null : userFollowId.trim();
    }

    public String getUserFollowedId() {
        return userFollowedId;
    }

    public void setUserFollowedId(String userFollowedId) {
        this.userFollowedId = userFollowedId == null ? null : userFollowedId.trim();
    }
}