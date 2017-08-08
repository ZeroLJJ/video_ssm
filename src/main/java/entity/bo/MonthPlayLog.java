package entity.bo;

/**
 * 用于存储每月统计播放量
 * Created by Zero on 2017/5/3.
 */
public class MonthPlayLog {
    private Integer month;
    private Integer playTimes;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(Integer playTimes) {
        this.playTimes = playTimes;
    }
}
