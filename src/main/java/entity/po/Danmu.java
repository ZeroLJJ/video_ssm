package entity.po;

public class Danmu {
    private Integer did;

    private String dtext;

    private String dcolor;

    private String dposition;

    private String dsize;

    private Integer dtime;

    private String userId;

    private Integer videoId;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDtext() {
        return dtext;
    }

    public void setDtext(String dtext) {
        this.dtext = dtext == null ? null : dtext.trim();
    }

    public String getDcolor() {
        return dcolor;
    }

    public void setDcolor(String dcolor) {
        this.dcolor = dcolor == null ? null : dcolor.trim();
    }

    public String getDposition() {
        return dposition;
    }

    public void setDposition(String dposition) {
        this.dposition = dposition == null ? null : dposition.trim();
    }

    public String getDsize() {
        return dsize;
    }

    public void setDsize(String dsize) {
        this.dsize = dsize == null ? null : dsize.trim();
    }

    public Integer getDtime() {
        return dtime;
    }

    public void setDtime(Integer dtime) {
        this.dtime = dtime;
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