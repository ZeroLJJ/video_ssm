package util;

/**
 * Created by Zero on 2017/3/29.
 */
public class StringUtil {
    /**
     * 验证字符串是否为数字，只有正整数时才为true，空和null为false
     * @param str  待验证的字符串
     * @return 字符串是否为数字
     */
    public static boolean isNumeric(String str){
        if(str == null || str.equals("")){
            return false;
        }
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
