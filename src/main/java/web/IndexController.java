package web;

import entity.bo.BigTypeBO;
import entity.bo.VideoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.VideoService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static util.Constant.*;

/**
 * 跳转控制层，专门用于controller跳转和jsp跳转
 * Created by Zero on 2017/2/22.
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private VideoService videoService;
    // @RequestMapping(value = "/init", params = {"id=myValue"})
    // 只有存在了请求参数id=myValue  /init?id=myValue 才会被处理
    // @RequestMapping(value={"test", "test1"})
    // 可以通过 http://localhost:8080/test或者http://localhost:8080/test1来访问

    //打开首页
    @RequestMapping(value = {"/","","/index"})
    public String pageIndex(Map dataMap) throws Exception{
        //获取热门视频
        List<VideoBO> hotList = videoService.getHotVideo();
        //获取各类型随机视频和排行信息
        List<BigTypeBO> typeList = videoService.getTypeInformation();
        dataMap.put("hotList", hotList);
        dataMap.put("typeList", typeList);
        return URL_INDEX;
    }

    //用于重定向到web-inf下的jsp页面，因为重定向是跳到另一个controller方法，无法直接返回个jsp页面，只有forward可以
    @RequestMapping(value = "/success")
    public String success(){
        return URL_SUCCESS;
    }

    @RequestMapping(value = "/error")
    public String error(){
        return URL_ERROR;
    }

    //返回登录界面
    @RequestMapping(value = "/login")
    public String login(){
        return URL_LOGIN;
    }

    //返回注册界面
    @RequestMapping(value = "/register")
    public String register(){
        return URL_REGISTER;
    }

    //Flash属性RedirectAttributes，即重定向之后仍然有效（通过session，重定向之后从session移除）
    @RequestMapping(value = "/exit")
    public String exit(HttpSession session, RedirectAttributes redirectAttribute){
        session.setAttribute("user",null);
        String msg= "退出成功";
        //addFlashAttribute是把属性加入到http报文中
        //addAttribute是把属性加入到url当成参数
        redirectAttribute.addFlashAttribute("msg",msg);
        //redirect不会返回个视图资源，而是返回个地址，需要controller方法去接收
        return "redirect:" + URL_SUCCESS;
    }


    @RequestMapping(value = "/adminLogin")
    public String getLogin(){
        return MANAGE_LOGIN;
    }

    @RequestMapping(value = "/adminExit")
    public String adminExit(HttpSession session, RedirectAttributes redirectAttribute){
        session.setAttribute("admin",null);
        String msg= "退出成功";
        //addFlashAttribute是把属性加入到http报文中
        //addAttribute是把属性加入到url当成参数
        redirectAttribute.addFlashAttribute("msg",msg);
        //redirect不会返回个视图资源，而是返回个地址，需要controller方法去接收
        return "redirect:" + URL_SUCCESS;
    }

    @RequestMapping(value = "/video/rank")
    public String getRank() throws Exception{
        return URL_RANK;
    }

    @RequestMapping(value = "/post")
    public String getPost() throws Exception{
        return URL_POST;
    }

}
