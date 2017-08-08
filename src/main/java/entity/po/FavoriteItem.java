package entity.po;

import java.util.Date;

public class FavoriteItem {
    private Integer fiid;

    private Date fidate;

    private Integer videoId;

    private Integer favoriteId;

    public Integer getFiid() {
        return fiid;
    }

    public void setFiid(Integer fiid) {
        this.fiid = fiid;
    }

    public Date getFidate() {
        return fidate;
    }

    public void setFidate(Date fidate) {
        this.fidate = fidate;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }
}