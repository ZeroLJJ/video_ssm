package web;

import com.alibaba.fastjson.JSONObject;
import entity.bo.CommentBO;
import entity.bo.VideoBO;
import entity.po.User;
import entity.po.Video;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.*;
import util.Page;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import static util.Constant.*;

/**
 * Created by Zero on 2017/2/25.
 */
@Controller
@RequestMapping(value = "/video")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private DanmuService danmuService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private PlayLogService playLogService;
    @Autowired
    private ScoreService scoreService;

    //按照查询条件查询视频信息
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getVideos (Model model,HttpServletRequest request)throws Exception{
        //存放查询的条件
        Map<String, Object> condition = new HashMap<String, Object>();
        //问号后的参数通过request获取
        Integer pageNo = 1;  //页数,默认为1
        Integer tag = null;   //标签，默认为null，表示没有限定
        if(request.getParameter("name") != null && !request.getParameter("name").equals("")) {
            //String name = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
            String name = request.getParameter("name");
            condition.put("name",name);
        }
        if(StringUtil.isNumeric(request.getParameter("smallType"))) {
            condition.put("smallType",request.getParameter("smallType"));
        }else if(StringUtil.isNumeric(request.getParameter("bigType"))) {    //若有小类，则大类失效
            condition.put("bigType",request.getParameter("bigType"));
        }
        if(StringUtil.isNumeric(request.getParameter("orderType"))) {
            condition.put("orderType",request.getParameter("orderType"));
        }
        if(request.getParameter("duration_level") != null && !request.getParameter("duration_level").equals("")) {
            condition.put("duration_level",request.getParameter("duration_level"));
        }
        if(StringUtil.isNumeric(request.getParameter("pageNo"))) {
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        if(StringUtil.isNumeric(request.getParameter("tag"))) {
            tag = Integer.parseInt(request.getParameter("tag"));
        }
        //调用service查询视频分页信息
        Page videoPage = videoService.getVideoPage(condition, pageNo, tag);
        model.addAttribute("videoPage",videoPage);
        return URL_SEARCH;
    }

    //通过ajax刷新视频列表信息
    @RequestMapping(value = "/search")
    @ResponseBody
    public Page getVideosByAjax(String name, Integer pageNo, Integer bigType, Integer smallType,
                                Integer tag, Integer orderType, String duration_level)throws Exception{
        //存放查询的条件
        Map<String, Object> condition = new HashMap<String, Object>();
        if(name != null && !name.equals("")){
            condition.put("name", name);
        }
        if(smallType != null && smallType != 0){
            condition.put("smallType", smallType);
        }else if(bigType != null && bigType != 0){   //有小类则大类失效
            condition.put("bigType", bigType);
        }
        if(orderType != null && orderType != 0){
            condition.put("orderType", orderType);
        }
        if(duration_level != null && !duration_level.equals("") && !duration_level.equals("0")){
            condition.put("duration_level", duration_level);
        }
        Page videoPage = videoService.getVideoPage(condition, pageNo, tag);
        return videoPage;
    }

    //获取视频详情页信息
    @RequestMapping(value = "/show/{vid}")
    public String getVideoDetail (Model model, @PathVariable("vid") Integer videoId,
                                  HttpSession session) throws Exception{
        int pageNo = 1;  //页数,默认为1
        User user = (User) session.getAttribute("user");
        String userId = null;  //用户编号,默认为空，若有用户信息，则赋值
        if(user != null){
            userId = user.getUid();
        }
        //获取视频详情信息
        VideoBO videoDetail = videoService.getVideoDetail(videoId, userId);
        model.addAttribute("videoDetail", videoDetail);
        //获取评论页信息
        Page commentPage = commentService.getCommentsByPage(videoId, pageNo);
        for (CommentBO commentBO : (List<CommentBO>)commentPage.getList()) {
            //获取每条评论对应的回复页信息
            Page replyPage = replyService.getReplysByPage(commentBO.getCid(), pageNo);
            commentBO.setReplyPage(replyPage);
        }
        model.addAttribute("commentPage", commentPage);
        //获取该视频类型热门推荐
        List<VideoBO> hotList = videoService.getHotByType(videoDetail.getBigTypeBO().getBid());
        model.addAttribute("hotList", hotList);
        //获取该视频类型随机推荐
        List<VideoBO> randomList = videoService.getRandomByType(videoDetail.getBigTypeBO().getBid());
        model.addAttribute("randomList", randomList);
        //获取最新投稿视频推荐
        List<VideoBO> newPostList = videoService.getRecentlyPost();
        model.addAttribute("newPostList", newPostList);
        return URL_SHOW;
    }

    //获取弹幕信息(异步获取)
    //加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中
    //以String返回时默认以iso编码返回，以对象(如List)时默认以json，utf-8编码返回
    @RequestMapping(value = "/getDanmu" , method={RequestMethod.GET})
    public @ResponseBody List getDanmu (HttpServletRequest request) throws Exception{
        List<Map<String, Object>> danmuList = new ArrayList<Map<String,Object>>();
        int videoId = Integer.parseInt(request.getParameter("videoId"));   //视频名称
        danmuList = danmuService.getDanmuList(videoId);
        return danmuList;
    }

    //存储弹幕信息
    @RequestMapping(value = "/saveDanmu")
    public void saveDanmu (HttpServletRequest request, HttpSession session,
                                         @RequestBody JSONObject danmuJSON) throws Exception {
        int videoId = Integer.parseInt(request.getParameter("videoId"));   //视频名称
        String userId = null;  //用户名称，默认为空，登录后才会赋值
        User user = (User)session.getAttribute("user");
        if(user != null){
            userId = user.getUid();
        }
        danmuService.saveDanmu(danmuJSON, videoId, userId);
    }

    //获取视频排行信息（三种排行分别为日，周，月）,默认为全站的排行
    //dataMap同model,可以给request设值
    @RequestMapping(value = "/rankAjax/{type}")
    @ResponseBody
    public Map getRankByAjax(@PathVariable("type") String videoType) throws Exception{
        Map rankMap = new HashMap<String, Object>();
        //查询对应的排行信息
        List<VideoBO> dayRank = playLogService.getRank(videoType, RANK_TYPE_DAY);
        List<VideoBO> weekRank = playLogService.getRank(videoType, RANK_TYPE_WEEK);
        List<VideoBO> monthRank = playLogService.getRank(videoType, RANK_TYPE_MONTH);
        //将数据模型设置到视图
        rankMap.put("dayRank", dayRank);
        rankMap.put("weekRank", weekRank);
        rankMap.put("monthRank", monthRank);
        rankMap.put("videoType", videoType);
        return rankMap;
    }

    @RequestMapping(value = "/score")
    @ResponseBody
    public String score(String userId, Integer num, Integer videoId) throws Exception{
        String msg = scoreService.insert(userId, num, videoId);
        return msg;
    }

    @RequestMapping(value = "/uploadVideo")
    @ResponseBody
    public Map uploadVideo(@RequestParam("video") MultipartFile file,
                      HttpServletRequest request) throws Exception{
        Map result = new HashMap<String,Object>();
        String message = "上传失败";
        boolean flag = false; // 转码成功与否的标记
        // 获得保存文件的路径
        String basePath = request.getSession().getServletContext().getRealPath("/");
        // 获得文件名
        String fileUrl = file.getOriginalFilename();
        // 在某些操作系统上,item.getName()方法会返回文件的完整名称,即包括路径
        String fileType = fileUrl.substring(fileUrl.lastIndexOf(".")); // 截取文件格式
        // 自定义方式产生文件名
        String serialName = String.valueOf(System.currentTimeMillis());
        // 上传文件（待转码的文件）
        File uploadFile = new File(basePath + "/video/" + serialName + fileType);
        // 保存文件
        Streams.copy(file.getInputStream(),new FileOutputStream(uploadFile.getAbsolutePath()),true);
        // 判断文件的大小
        if (file.getSize() > 1000 * 1024 * 1024) {
            message = "上传失败！您上传的文件太大,系统允许最大文件1000M";
        }
//      因为使用H5所以就不进行flv格式转码了，只允许mp4格式，不进行转码
//      String codcFilePath = basePath + "/video/" + serialName + ".flv"; // 设置转换为flv格式后文件的保存路径
        String mediaPicPath = basePath + "/images/video/" + serialName + ".jpg"; // 设置上传视频截图的保存路径
        // 获取配置的转换工具（ffmpeg.exe）的存放路径
        String ffmpegPath = request.getServletContext().getRealPath("/tools/ffmpeg.exe");
        // 转码(第四个参数为转码地址，本系统不进行转码，设为空）
        flag = videoService.executeCodecs(ffmpegPath, uploadFile.getAbsolutePath(), null, mediaPicPath);
        // 获取视频时长（数字型，总共多少秒）
        int time = videoService.getVideoTime(uploadFile.getAbsolutePath(), ffmpegPath);
        // 获取（mm:ss）格式的时间字符串
        String duration = String.format("%02d", time / 60) + ":" + String.format("%02d", time % 60);
        // 获取时间长度级别
        int durationLevel;
        if(time < 600){
            durationLevel = 1; //1表示10分钟以内
        }else if(time < 1800) {
            durationLevel = 2; //2表示10分钟-30分钟以内
        }else if(time < 3600) {
            durationLevel = 3; //3表示30分钟-60分钟以内
        }else {
            durationLevel = 4; //4表示60分钟以上
        }
        if (flag == true) {
            // 转码成功,向数据表中添加该视频信息
            message="上传成功";
        }
        result.put("message", message);
        result.put("videoPath", serialName + fileType);
        result.put("imgPath", "video/" + serialName + ".jpg");
        result.put("duration", duration);
        result.put("durationLevel", durationLevel);
        return result;
    }

    @RequestMapping(value = "/uploadCover")
    @ResponseBody
    public String uploadCover(@RequestParam("uploadCover") MultipartFile file,
                              HttpServletRequest request) throws Exception{
        String path = "";
        //原始名称
        String originalFilename = file.getOriginalFilename();
        //上传图片
        if(file!=null && originalFilename!=null && originalFilename.length()>0){
            // 获得保存文件的路径
            String basePath = request.getSession().getServletContext().getRealPath("/");
            // 获得文件名
            String fileUrl = file.getOriginalFilename();
            // 在某些操作系统上,item.getName()方法会返回文件的完整名称,即包括路径
            String fileType = fileUrl.substring(fileUrl.lastIndexOf(".")); // 截取文件格式
            // 自定义方式产生文件名
            String serialName = String.valueOf(System.currentTimeMillis());
            // 上传文件（待转码的文件）
            File uploadFile = new File(basePath + "/images/video/" + serialName + fileType);
            //将内存中的数据写入磁盘
            file.transferTo(uploadFile);
            path = "video/" + serialName + fileType;
        }
        return path;
    }

    /**
     * 视频上传,解码并保存
     * @throws Exception
     */
    @RequestMapping(value = "/doPost", method = RequestMethod.POST)
    @ResponseBody
    public String doPost(HttpSession session, HttpServletResponse response,
                        Video video, RedirectAttributes redirectAttribute) throws Exception {
        User user = (User) session.getAttribute("user");
        video.setUploadUserId(user.getUid());
        Boolean flag = videoService.saveVideo(video);
        String msg= "";
        if(flag == true){
            msg= "上传成功";
            session.setAttribute("msg",msg);
            //redirectAttribute.addFlashAttribute("msg",msg);
        }
        return msg;
    }
}
