package service.impl;

import dao.custom.BigTypeCustomMapper;
import dao.custom.TagFollowCustomMapper;
import dao.custom.UserFollowCustomMapper;
import dao.custom.VideoCustomMapper;
import dao.simple.PlayLogMapper;
import dao.simple.VideoMapper;
import entity.bo.*;
import entity.po.PlayLog;
import entity.po.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.VideoService;
import util.DateUtil;
import util.Page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zero on 2017/2/24.
 */
@Service
public class VideoServiceImpl extends BaseService implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoCustomMapper videoCustomMapper;
    @Autowired
    private PlayLogMapper playLogMapper;
    @Autowired
    private TagFollowCustomMapper tagFollowCustomMapper;
    @Autowired
    private UserFollowCustomMapper userFollowCustomMapper;
    @Autowired
    private BigTypeCustomMapper bigTypeCustomMapper;

    @Override
    public VideoBO getVideoDetail(Integer videoId, String userId) throws Exception {
        VideoBO videoDetail = videoCustomMapper.getVideoDetail(videoId);
        if(videoDetail != null){  //获取成功，则进行相应业务处理
            //获取用户关注信息
            UserBO userBO = videoDetail.getUserBO();
            Map userCondition = new HashMap<String, Object>();
            userCondition.put("follow_uid", userId);
            userCondition.put("followed_uid", userBO.getUid());
            int userCount = userFollowCustomMapper.countByFollow(userCondition);
            userBO.setUserFollowCount(userCount);
            //获取标签关注信息
            for (TagItemBO tagItemBO : videoDetail.getTagItemBOList()) {
                TagBO tagBO = tagItemBO.getTagBO();
                //设置关注信息的查询条件
                Map tagCondition = new HashMap<String, Object>();
                tagCondition.put("uid", userId);
                tagCondition.put("tid", tagBO.getTid());
                //获取符合条件的统计数
                int tagCount = tagFollowCustomMapper.countByUserAndTag(tagCondition);
                tagBO.setTagFollowCount(tagCount);
            }
            //增加一次播放次数
            Video video = videoMapper.selectByPrimaryKey(videoId);
            Video updateVideo = new Video();   //用于更新的对象
            updateVideo.setVid(videoId);
            updateVideo.setVplayTimes(video.getVplayTimes() + 1);
            videoMapper.updateByPrimaryKeySelective(updateVideo);
            //增加播放记录
            PlayLog playLog = new PlayLog();
            playLog.setPdate(new Date());
            playLog.setUserId(userId);
            playLog.setVideoId(videoId);
            playLogMapper.insert(playLog);
        }
        return videoDetail;
    }

    @Override
    public Page getVideoPage(Map<String, Object> condition, Integer pageNo, Integer tag) throws Exception {
        int pageSize = 15;   //每页记录数
        List<VideoBO> videoList;   // 当前页的视频列表
        if(pageNo == null){
            pageNo = 1;   //若无页码参数，则置1
        }
        int offset = Page.countOffset(pageSize, pageNo);    // 当前页开始记录
        int allRow;  //总记录数
        //添加搜索条件
        condition.put("offset",offset);
        condition.put("pageSize",pageSize);
        if(tag == null){    //表示无标签限制
            videoList = videoCustomMapper.getVideoPage(condition);  //查询视频列表
            allRow = videoCustomMapper.getVideosCount(condition);
        }else {
            condition.put("tag", tag);
            videoList = videoCustomMapper.getVideoPageByTag(condition);  //查询标签拥有的视频列表
            allRow = videoCustomMapper.getVideosCountByTag(condition);
        }
        int currentPage = Page.countCurrentPage(pageNo);    // 当前页
        int totalPage = Page.countTotalPage(pageSize,allRow);   // 总页数
        Page page = new Page();
        //开始设置页面信息
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(videoList);
        Page.init(page, currentPage, totalPage);
        return page;
    }

    @Override
    public List<VideoBO> getRecentlyPost(String userId) throws Exception {
        Map condition = new HashMap<String, Object>();
        condition.put("uid", userId);
        condition.put("size", 8);
        List<VideoBO> videoList = videoCustomMapper.getVideoByRecentlyPost(condition);
        return videoList;
    }

    @Override
    public List<VideoBO> getRecentlyPost() throws Exception {
        Map condition = new HashMap<String, Object>();
        condition.put("size", 3);
        List<VideoBO> videoList = videoCustomMapper.getVideoByRecentlyPost(condition);
        return videoList;
    }

    @Override
    public Page getVideoPageByPost(String userId, Integer pageNo) throws Exception {
        int pageSize = 18;   //每页记录数
        Map<String, Object> condition = new HashMap<String, Object>();   // 存放所要搜索的相关信息
        List<VideoBO> postList;   // 当前页的视频列表
        int offset = Page.countOffset(pageSize, pageNo);    // 当前页开始记录
        condition.put("uid",userId);
        condition.put("offset",offset);
        condition.put("pageSize",pageSize);
        int allRow = videoCustomMapper.countVideoByPost(userId);    // 总记录数
        int currentPage = Page.countCurrentPage(pageNo);    // 当前页
        int totalPage = Page.countTotalPage(pageSize,allRow);   // 总页数
        postList = videoCustomMapper.getVideoPageByPost(condition);  //查询视频列表
        Page page = new Page();
        //开始设置页面信息
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(postList);
        Page.init(page, currentPage, totalPage);
        return page;
    }

    @Override
    public List<VideoBO> getHotVideo() throws Exception {
        //获取三天前的时间
        //String date = DateUtil.getDiffDay(3);
        //方便测试，改成3个月
        String date = DateUtil.getDiffMonth(3);
        Map condition = new HashMap<String, Object>();
        condition.put("date", date);
        condition.put("size", 5);  //获取前5
        List<VideoBO> hotList = videoCustomMapper.getHotVideo(condition);
        return hotList;
    }

    @Override
    public List<BigTypeBO> getTypeInformation() throws Exception {
        //获取视频各类型
        List<BigTypeBO> list = bigTypeCustomMapper.getVideoType();
        for (BigTypeBO bigTypeBO : list) {
            //获取各类型的随机视频
            Map randomCondition = new HashMap<String, Object>();
            randomCondition.put("bid", bigTypeBO.getBid());
            randomCondition.put("size", 6); //获取3个随机视频
            List<VideoBO> random = videoCustomMapper.getRandom(randomCondition);
            bigTypeBO.setRandomVideoList(random);
            //获取各类型的排行信息
            Map rankCondition = new HashMap<String, Object>();
            String date = DateUtil.getDiffMonth(3);  //方便测试先设为3个月内的排行信息
            rankCondition.put("bid", bigTypeBO.getBid());
            rankCondition.put("date", date);
            rankCondition.put("size", 4);
            List<VideoBO> rank = videoCustomMapper.getHotVideo(rankCondition);
            bigTypeBO.setRankVideoList(rank);
        }
        return list;
    }

    @Override
    public List<VideoBO> getHotByType(Integer bigTypeId) throws Exception {
        String date = DateUtil.getPrevMonth();
        Map condition = new HashMap<String, Object>();
        condition.put("date", date);
        condition.put("bid", bigTypeId);
        condition.put("size", 3); //获取前3
        List<VideoBO> hot = videoCustomMapper.getHotVideo(condition);
        return hot;
    }

    @Override
    public List<VideoBO> getRandomByType(Integer bigTypeId) throws Exception {
        Map condition = new HashMap<String, Object>();
        condition.put("bid", bigTypeId);
        condition.put("size", 3); //获取3个随机视频
        List<VideoBO> random = videoCustomMapper.getRandom(condition);
        return random;
    }

    @Override
    public Boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath, String mediaPicPath) throws Exception {
        // 创建一个List集合来保存转换视频文件为flv格式的命令
//        List<String> convert = new ArrayList<String>();
//        convert.add(ffmpegPath); // 添加转换工具路径
//        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
//        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
//        convert.add("-qscale");     //指定转换的质量
//        convert.add("6");
//        convert.add("-ab");        //设置音频码率
//        convert.add("64");
//        convert.add("-ac");        //设置声道数
//        convert.add("2");
//        convert.add("-ar");        //设置声音的采样频率
//        convert.add("22050");
//        convert.add("-r");        //设置帧频
//        convert.add("24");
//        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
//        convert.add(codcFilePath);

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("17"); // 添加起始时间为第17秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("960*600"); // 添加截取的图片大小为350*240
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            //转码部分
//            builder.command(convert);
//            builder.redirectErrorStream(true);
//            builder.start();

            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }

    @Override
    public Integer getVideoTime(String videoPath, String ffmpegPath) throws Exception {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(videoPath);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = DateUtil.getTimelen(m.group(1));
                System.out.println(videoPath+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Boolean saveVideo(Video video) throws Exception {
        //初始化一些基本信息
        video.setVaddTime(new Date());
        video.setVplayTimes(0);
        video.setVfavoriteTimes(0);
        video.setVdanmu(0);
        video.setVscore(0);
        video.setVcomment(0);
        video.setVstatus("0");
        //进行插入
        int num = videoMapper.insert(video);
        if(num > 0){ //插入成功
            return true;
        }
        return false;
    }
}
