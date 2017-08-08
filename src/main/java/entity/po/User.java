package entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private String uid;

    private String upassword;

    private String uname;

    private String uimg;

    private String usex;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ubirthday;

    private String uemail;

    private String uphone;

    private String usign;

    private Integer ufollow;

    private Integer ufan;

    private Integer ucoin;

    private Integer uworks;

    private String ulevel;

    private Date uregdate;

    private String ustatus;

    private Date ulastdate;

    private Integer ulogin;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg == null ? null : uimg.trim();
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex == null ? null : usex.trim();
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone == null ? null : uphone.trim();
    }

    public String getUsign() {
        return usign;
    }

    public void setUsign(String usign) {
        this.usign = usign == null ? null : usign.trim();
    }

    public Integer getUfollow() {
        return ufollow;
    }

    public void setUfollow(Integer ufollow) {
        this.ufollow = ufollow;
    }

    public Integer getUfan() {
        return ufan;
    }

    public void setUfan(Integer ufan) {
        this.ufan = ufan;
    }

    public Integer getUcoin() {
        return ucoin;
    }

    public void setUcoin(Integer ucoin) {
        this.ucoin = ucoin;
    }

    public Integer getUworks() {
        return uworks;
    }

    public void setUworks(Integer uworks) {
        this.uworks = uworks;
    }

    public String getUlevel() {
        return ulevel;
    }

    public void setUlevel(String ulevel) {
        this.ulevel = ulevel == null ? null : ulevel.trim();
    }

    public Date getUregdate() {
        return uregdate;
    }

    public void setUregdate(Date uregdate) {
        this.uregdate = uregdate;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus == null ? null : ustatus.trim();
    }

    public Date getUlastdate() {
        return ulastdate;
    }

    public void setUlastdate(Date ulastdate) {
        this.ulastdate = ulastdate;
    }

    public Integer getUlogin() {
        return ulogin;
    }

    public void setUlogin(Integer ulogin) {
        this.ulogin = ulogin;
    }
}