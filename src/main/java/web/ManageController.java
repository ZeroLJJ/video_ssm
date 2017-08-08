package web;

import com.alibaba.fastjson.JSONArray;
import entity.bo.TagBO;
import entity.bo.UserBO;
import entity.bo.VideoBO;
import entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ManageService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static util.Constant.*;

/**
 * Created by Zero on 2017/5/1.
 */
@RequestMapping(value = "/manage")
@Controller
public class ManageController extends BaseController {
    @Autowired
    private ManageService manageService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpSession session, String id,
                        String password)throws Exception {
        User user = manageService.selectUser(id, password);
        if(user != null){
            if(user.getUstatus().equals("1")){
                return "账号已被冻结，请解冻后再登录";
            }
            if(user.getUlevel().equals("0")||user.getUlevel().equals("1")){
                session.setAttribute("admin", user);
                return "登录成功";
            }else {
                return "账号权限不足，登录失败";
            }
        }else{
            return "账号或密码错误，登录失败";
        }
    }

    @RequestMapping(value = {"","/","/index"})
    public String index(){
        return MANAGE_INDEX;
    }

    @RequestMapping(value = "/welcome")
    public String welcome(Map dataMap) throws Exception{
        //获取各类型每月播放量
        List count = manageService.getCount();
        JSONArray jsonArray = new JSONArray(count);
        dataMap.put("count", jsonArray);
        return MANAGE_WELCOME;
    }

    @RequestMapping(value = "/video-list")
    public String videoList(Map dataMap, Date minTime, Date maxTime,
                            String videoName) throws Exception{
        List<VideoBO> videoList = manageService.getAllVideo(minTime, maxTime, videoName);
        dataMap.put("videoList", videoList);
        return MANAGE_VIDEO_LIST;
    }

    @RequestMapping(value = "/video/multiDelete")
    @ResponseBody
    public String multiVideoDelete(@RequestParam(value = "idList[]") Integer[] idList) throws Exception{
        String msg = manageService.deleteMultiVideo(idList);
        return msg;
    }

    @RequestMapping(value = "/video/frozen")
    @ResponseBody
    public String videoFrozen(Integer videoId) throws Exception{
        String msg = manageService.updateVideoFrozen(videoId);
        return msg;
    }

    @RequestMapping(value = "/video/unfrozen")
    @ResponseBody
    public String videoUnfrozen(Integer videoId) throws Exception{
        String msg = manageService.updateVideoUnfrozen(videoId);
        return msg;
    }

    @RequestMapping(value = "/video/check")
    @ResponseBody
    public String videoCheck(Integer videoId, Boolean pass) throws Exception{
        String msg = manageService.checkVideo(videoId, pass);
        return msg;
    }

    @RequestMapping(value = "/video/delete")
    @ResponseBody
    public String videoDelete(Integer videoId) throws Exception{
        String msg = manageService.deleteVideo(videoId);
        return msg;
    }

    @RequestMapping(value = "/tag-list")
    public String tagList(Map dataMap, String tagName) throws Exception{
        List<TagBO> tagList = manageService.getAllTag(tagName);
        dataMap.put("tagList", tagList);
        return MANAGE_TAG_LIST;
    }

    @RequestMapping(value = "/tag/multiDelete")
    @ResponseBody
    public String multiTagDelete(@RequestParam(value = "idList[]") Integer[] idList) throws Exception{
        String msg = manageService.deleteMultiTag(idList);
        return msg;
    }

    @RequestMapping(value = "/tag/delete")
    @ResponseBody
    public String tagDelete(Integer tagId) throws Exception{
        String msg = manageService.deleteTag(tagId);
        return msg;
    }

    @RequestMapping(value = "/user-list")
    public String userList(Map dataMap, String userName) throws Exception{
        List<UserBO> userList = manageService.getAllUser(userName);
        dataMap.put("userList", userList);
        return MANAGE_USER_LIST;
    }

    @RequestMapping(value = "/user/multiDelete")
    @ResponseBody
    public String multiUserDelete(@RequestParam(value = "idList[]") String[] idList) throws Exception{
        String msg = manageService.deleteMultiUser(idList);
        return msg;
    }

    @RequestMapping(value = "/user/frozen")
    @ResponseBody
    public String userFrozen(String userId) throws Exception{
        String msg = manageService.updateUserFrozen(userId);
        return msg;
    }

    @RequestMapping(value = "/user/unfrozen")
    @ResponseBody
    public String userUnfrozen(String userId) throws Exception{
        String msg = manageService.updateUserUnfrozen(userId);
        return msg;
    }

    @RequestMapping(value = "/user/delete")
    @ResponseBody
    public String userDelete(String userId) throws Exception{
        String msg = manageService.deleteUser(userId);
        return msg;
    }

    @RequestMapping(value = "/admin-list")
    public String adminList(Map dataMap, String userName) throws Exception{
        List<UserBO> adminList = manageService.getAllUserIncludeAdmin(userName);
        dataMap.put("adminList", adminList);
        return MANAGE_ADMIN_LIST;
    }

    @RequestMapping(value = "/user/up")
    @ResponseBody
    public String userUp(String userId) throws Exception{
        String msg = manageService.updateUserToAdmin(userId);
        return msg;
    }

    @RequestMapping(value = "/user/down")
    @ResponseBody
    public String userDown(String userId) throws Exception{
        String msg = manageService.updateAdminToUser(userId);
        return msg;
    }

}
