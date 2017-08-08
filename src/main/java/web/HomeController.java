package web;

import entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import static util.Constant.*;

/**
 * Created by Zero on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/user/home")
public class HomeController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"","/","/detail"})
    public String getDetail(HttpSession session, Map dataMap) throws Exception{
        User user = (User)session.getAttribute("user");
        dataMap.put("user",user);
        return URL_HOME_DETAIL;
    }

    @RequestMapping(value = "/security")
    public String security(){
        return URL_HOME_SECURITY;
    }

    @RequestMapping("/editDetail")
    public void editItemsSubmit(User user, HttpSession session, MultipartFile uploadImg,
                                HttpServletResponse response, HttpServletRequest request) throws Exception {
        //原始名称
        String originalFilename = uploadImg.getOriginalFilename();
        //上传图片
        if(uploadImg!=null && originalFilename!=null && originalFilename.length()>0){
            // 获得保存文件的路径
            String basePath = request.getSession().getServletContext().getRealPath("/");
            // 获得文件名
            String fileUrl = uploadImg.getOriginalFilename();
            // 在某些操作系统上,item.getName()方法会返回文件的完整名称,即包括路径
            String fileType = fileUrl.substring(fileUrl.lastIndexOf(".")); // 截取文件格式
            // 自定义方式产生文件名
            String serialName = String.valueOf(System.currentTimeMillis());
            // 上传文件（待转码的文件）
            File uploadFile = new File(basePath + "/images/user/" + serialName + fileType);
            //将内存中的数据写入磁盘
            uploadImg.transferTo(uploadFile);
            //将新图片名称写到user中
            user.setUimg("user/" + serialName + fileType);
        }
        user = userService.updateUser(user);
        //设置响应编码，避免中文乱码
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(user != null){
            //更新session里的user对象
            session.setAttribute("user",user);
            //提示修改成功信息
            out.print("<script>$.tip('修改成功')</script>");
        }else {
            out.print("<script>$.tip('修改失败')</script>");
        }
        out.close();
    }

    @RequestMapping("/modifyPwd")
    public void modifyPwd(User user, HttpSession session, String newPwd,
                          HttpServletResponse response) throws Exception{
        user = userService.modifyPassword(user, newPwd);
        //设置响应编码，避免中文乱码
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(user != null){
            //更新session里的user对象
            session.setAttribute("user",user);
            //提示修改成功信息
            out.print("<script>$.tip('修改成功')</script>");
        }else {
            out.print("<script>$.tip('修改失败')</script>");
        }
        out.close();
    }

}
