package web;

import entity.bo.PlayLogBO;
import entity.po.User;
import entity.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PlayLogService;
import service.UserFollowService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static util.Constant.*;

/**
 * Created by Zero on 2017/3/9.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlayLogService playLogService;
    @Autowired
    private UserFollowService userFollowService;

    @RequestMapping(value = "/getImg")
    public @ResponseBody String getUserImg(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        System.out.println(userId);
        String img = userService.getUserImg(userId);
        return img;
    }

    //用户登录
    @RequestMapping(value = "/signIn")
    public String signIn(Model model, UserVO userVO, HttpSession session) throws Exception {
        //获取用户信息
        User user = userService.checkLogin(userVO);
        if(user != null){       //若不为空，表示登录成功
            //将用户信息写入session
            session.setAttribute("user",user);
            return "redirect:/";
        }else{   //登录失败
            String errorMsg = "用户名或密码错误，请重新登录";
            model.addAttribute("errorMsg",errorMsg);
            return URL_LOGIN;
        }
    }

    //注册新用户
    @RequestMapping(value = "/signUp")
    public String signUp(UserVO userVO, HttpSession session) throws Exception {
        User user = userService.saveNewUser(userVO);
        if(user != null){
            session.setAttribute("user",user);
            return "redirect:/";
        }else{
            return URL_REGISTER;
        }
    }

    //验证用户名是否存在
    @RequestMapping(value = "/isExist")
    @ResponseBody
    public String isExist(@RequestParam(value = "userId") String userId) throws Exception {
        Boolean isExist = userService.isExistUserId(userId);
        if(isExist == true){
            return "用户名已存在，请重新输入";
        }
        return "";
    }

    //dataMap同Model,与Springmvc解耦，方便单元测试
    @RequestMapping(value = "/{uid}/history")
    public String getHistory(Map dataMap, @PathVariable("uid") String userId,
                             HttpServletRequest request, HttpServletResponse response,
                             HttpSession session) throws Exception{
        User user = (User) session.getAttribute("user");
        //验证用户与要查询的历史纪录拥有者是否为同一人
        if(user.getUid().equals(userId)){
            List<PlayLogBO> history= playLogService.getHistory(userId);
            dataMap.put("history", history);
            return URL_HISTORY;
        }
        //不为同一人则显示提示信息，并返回首页
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('错误访问，接下来将放回首页')</script>");
        out.print("<script>window.location.href='" + request.getContextPath() + "/'</script>");
        out.close();
        return "";
    }

    @RequestMapping(value = "/follow")
    @ResponseBody
    public String follow(String followUid, String followedUid) throws Exception{
        String msg = userFollowService.insertFollow(followUid, followedUid);
        return msg;
    }

    @RequestMapping(value = "/unfollow")
    @ResponseBody
    public String unfollow(String followUid, String followedUid) throws Exception{
        String msg = userFollowService.deleteFollow(followUid, followedUid);
        return msg;
    }

}
