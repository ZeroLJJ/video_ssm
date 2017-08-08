package web.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zero on 2017/4/18.
 */
public class DateConverter implements Converter<String,Date> {

    public Date convert(String source) {

        //实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
        String dateType;

        if(source.length() == 0){
            return null;
        }

        if(source.length() == 10){
            dateType = "yyyy-MM-dd";
        } else {
            dateType = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateType);

        try {
            //转成直接返回
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //如果参数绑定失败返回null
        return null;
    }

}