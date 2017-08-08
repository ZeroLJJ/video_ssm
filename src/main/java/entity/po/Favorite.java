package entity.po;

import java.util.Date;

public class Favorite {
    private Integer fid;

    private String fname;

    private Date fcreateDate;

    private Integer fhave;

    private Integer ftotal;

    private String fprivacy;

    private String userId;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public Date getFcreateDate() {
        return fcreateDate;
    }

    public void setFcreateDate(Date fcreateDate) {
        this.fcreateDate = fcreateDate;
    }

    public Integer getFhave() {
        return fhave;
    }

    public void setFhave(Integer fhave) {
        this.fhave = fhave;
    }

    public Integer getFtotal() {
        return ftotal;
    }

    public void setFtotal(Integer ftotal) {
        this.ftotal = ftotal;
    }

    public String getFprivacy() {
        return fprivacy;
    }

    public void setFprivacy(String fprivacy) {
        this.fprivacy = fprivacy == null ? null : fprivacy.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}