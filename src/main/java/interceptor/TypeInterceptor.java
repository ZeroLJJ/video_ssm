package interceptor;

import dao.custom.BigTypeCustomMapper;
import entity.bo.BigTypeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型拦截器，将视频类型放入视图
 * Created by Zero on 2017/4/13.
 */
public class TypeInterceptor implements HandlerInterceptor {
    @Autowired
    BigTypeCustomMapper bigTypeCustomMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        //获取session中的视频类型
        List<BigTypeBO> videoType = (List<BigTypeBO>)session.getAttribute("videoType");
        //若为空，则去获取并设置到session中
        if(videoType == null){
            videoType = bigTypeCustomMapper.getVideoType();
            //查询最近各种类型的投稿数（3天）
            for (BigTypeBO bigTypeBO : videoType) {
                //设置查询条件
                Map condition = new HashMap<String, Object>();
                //获取三天前的时间
                //String date = DateUtil.getDiffDay(3);
                //为了测试方便改为3个月
                String date = DateUtil.getDiffMonth(3);
                condition.put("date", date);
                condition.put("bid", bigTypeBO.getBid());
                int count = bigTypeCustomMapper.countRecentlyPostByType(condition);
                bigTypeBO.setCountRecentlyPost(count);
            }
            session.setAttribute("videoType", videoType);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
