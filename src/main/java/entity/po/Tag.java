package entity.po;

public class Tag {
    private Integer tid;

    private String tname;

    private Integer tvideoNum;

    private Integer tsearch;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Integer getTvideoNum() {
        return tvideoNum;
    }

    public void setTvideoNum(Integer tvideoNum) {
        this.tvideoNum = tvideoNum;
    }

    public Integer getTsearch() {
        return tsearch;
    }

    public void setTsearch(Integer tsearch) {
        this.tsearch = tsearch;
    }
}