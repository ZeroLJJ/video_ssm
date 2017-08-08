package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zero on 2017/4/13.
 */
public class DateUtil {
    /**
     * 获取相较今天多少天的时间（格式为yyyy-MM-dd HH:mm:ss）
     * @param day 相差天数
     * @return 日期格式的字符串
     */
    public static String getDiffDay(Integer day){
        //先获取当前时间的日历实例
        Calendar time = Calendar.getInstance();
        //将天数往后推一天
        time.set(Calendar.DAY_OF_MONTH, time.get(Calendar.DAY_OF_MONTH) - day);
        //获取时间
        Date date = time.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String diffDay = sdf.format(date);
        return diffDay;
    }

    /**
     * 获取今天的日期（格式为yyyy-MM-dd HH:mm:ss）
     * @return 今天日期格式的字符串
     */
    public static String getToday(){
        //先获取当前时间的日历实例
        Calendar time = Calendar.getInstance();
        //获取时间
        Date date = time.getTime();
        //格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        return today;
    }

    /**
     * 获取前一天的日期（格式为yyyy-MM-dd HH:mm:ss）
     * @return 前一天日期格式的字符串
     */
    public static String getPrevDay(){
        //先获取当前时间的日历实例
        Calendar time = Calendar.getInstance();
        //将天数往后推一天
        time.set(Calendar.DAY_OF_MONTH, time.get(Calendar.DAY_OF_MONTH) - 1);
        //获取时间
        Date date = time.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String prevDay = sdf.format(date);
        return prevDay;
    }

    /**
     * 获取前一周的日期（格式为yyyy-MM-dd HH:mm:ss）
     * @return 前一周日期格式的字符串
     */
    public static String getPrevWeek(){
        //先获取当前时间的日历实例
        Calendar time = Calendar.getInstance();
        //将天数往后推一天
        time.set(Calendar.DAY_OF_MONTH, time.get(Calendar.DAY_OF_MONTH) - 7);
        //获取时间
        Date date = time.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String prevWeek = sdf.format(date);
        return prevWeek;
    }

    /**
     * 获取前一月的日期（格式为yyyy-MM-dd HH:mm:ss）
     * @return 前一月日期格式的字符串
     */
    public static String getPrevMonth(){
        //先获取当前时间的日历实例
        Calendar time = Calendar.getInstance();
        //将天数往后推一天
        time.set(Calendar.MONTH, time.get(Calendar.MONTH) - 1);
        //获取时间
        Date date = time.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String prevMonth = sdf.format(date);
        return prevMonth;
    }

    /**
     * 获取前某个月的日期（格式为yyyy-MM-dd HH:mm:ss）
     * @return 前某个月日期格式的字符串
     */
    public static String getDiffMonth(Integer month){
        //先获取当前时间的日历实例
        Calendar time = Calendar.getInstance();
        //将天数往后推一天
        time.set(Calendar.MONTH, time.get(Calendar.MONTH) - month);
        //获取时间
        Date date = time.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String prevMonth = sdf.format(date);
        return prevMonth;
    }

    /**
     * 判断输入的日期是否为当天
     * @param date 需要判断的日期
     * @return
     */
    public static Boolean isToday(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean flag;
        //判断日期是否为同一天
        flag = sdf.format(date).toString().equals(sdf.format(new Date()).toString());
        return flag;
    }

    /**
     * 获取int型的时间长度
     * @param timelen 时间字符串
     * @return
     */
    public static int getTimelen(String timelen){
        int min=0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min+=Integer.valueOf(strs[0])*60*60;//秒
        }
        if(strs[1].compareTo("0")>0){
            min+=Integer.valueOf(strs[1])*60;
        }
        if(strs[2].compareTo("0")>0){
            min+=Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }
}
