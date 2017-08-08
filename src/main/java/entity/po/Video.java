package entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Video {
    private Integer vid;

    private String vname;

    private String vsummary;

    private String vmedia;

    private String vimg;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date vaddTime;

    private String vduration;

    private String vdurationLevel;

    private Integer vplayTimes;

    private Integer vfavoriteTimes;

    private Integer vdanmu;

    private Integer vscore;

    private Integer vcomment;

    private String vstatus;

    private Integer smallTypeId;

    private String uploadUserId;

    private String checkUserId;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname == null ? null : vname.trim();
    }

    public String getVsummary() {
        return vsummary;
    }

    public void setVsummary(String vsummary) {
        this.vsummary = vsummary == null ? null : vsummary.trim();
    }

    public String getVmedia() {
        return vmedia;
    }

    public void setVmedia(String vmedia) {
        this.vmedia = vmedia == null ? null : vmedia.trim();
    }

    public String getVimg() {
        return vimg;
    }

    public void setVimg(String vimg) {
        this.vimg = vimg == null ? null : vimg.trim();
    }

    public Date getVaddTime() {
        return vaddTime;
    }

    public void setVaddTime(Date vaddTime) {
        this.vaddTime = vaddTime;
    }

    public String getVduration() {
        return vduration;
    }

    public void setVduration(String vduration) {
        this.vduration = vduration == null ? null : vduration.trim();
    }

    public String getVdurationLevel() {
        return vdurationLevel;
    }

    public void setVdurationLevel(String vdurationLevel) {
        this.vdurationLevel = vdurationLevel == null ? null : vdurationLevel.trim();
    }

    public Integer getVplayTimes() {
        return vplayTimes;
    }

    public void setVplayTimes(Integer vplayTimes) {
        this.vplayTimes = vplayTimes;
    }

    public Integer getVfavoriteTimes() {
        return vfavoriteTimes;
    }

    public void setVfavoriteTimes(Integer vfavoriteTimes) {
        this.vfavoriteTimes = vfavoriteTimes;
    }

    public Integer getVdanmu() {
        return vdanmu;
    }

    public void setVdanmu(Integer vdanmu) {
        this.vdanmu = vdanmu;
    }

    public Integer getVscore() {
        return vscore;
    }

    public void setVscore(Integer vscore) {
        this.vscore = vscore;
    }

    public Integer getVcomment() {
        return vcomment;
    }

    public void setVcomment(Integer vcomment) {
        this.vcomment = vcomment;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus == null ? null : vstatus.trim();
    }

    public Integer getSmallTypeId() {
        return smallTypeId;
    }

    public void setSmallTypeId(Integer smallTypeId) {
        this.smallTypeId = smallTypeId;
    }

    public String getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId == null ? null : uploadUserId.trim();
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId == null ? null : checkUserId.trim();
    }
}