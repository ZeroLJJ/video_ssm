package service.impl;

import dao.custom.*;
import dao.simple.TagMapper;
import dao.simple.UserMapper;
import dao.simple.VideoMapper;
import entity.bo.*;
import entity.po.User;
import entity.po.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ManageService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Zero on 2017/5/1.
 */
@Service
public class ManageServiceImpl extends BaseService implements ManageService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoCustomMapper videoCustomMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagCustomMapper tagCustomMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCustomMapper userCustomMapper;
    @Autowired
    private BigTypeCustomMapper bigTypeCustomMapper;
    @Autowired
    private PlayLogCustomMapper playLogCustomMapper;

    @Override
    public User selectUser(String id, String password) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        if(user != null) {
            if (user.getUpassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Map<String,Object>> getCount() throws Exception {
        List<BigTypeBO> typeList = bigTypeCustomMapper.getVideoType();
        //取出每种类型的每月播放量
        for (BigTypeBO item : typeList) {
            Map condition = new HashMap<String, Object>();
            condition.put("year", Calendar.getInstance().get(Calendar.YEAR));  //设置查询时间
            condition.put("bid",item.getBid());
            List<MonthPlayLog> monthPlayLogs = playLogCustomMapper.countMonth(condition);
            item.setMonthPlayLogs(monthPlayLogs);
        }
        //转成页面需要的格式
        List count = new ArrayList<Map<String,Object>>();
        for (BigTypeBO item : typeList) {
            Map map = new HashMap<String, Object>();
            List data = new ArrayList<Integer>();
            map.put("name",item.getBname());
            for (int i = 1; i <= 12; i++){
                boolean flag = false;
                for (MonthPlayLog monthPlayLog : item.getMonthPlayLogs()) {
                    if(i == monthPlayLog.getMonth()){
                        data.add(monthPlayLog.getPlayTimes());
                        flag = true;
                    }
                }
                if (flag == false){
                    data.add(0);
                }
            }
            map.put("data",data);
            count.add(map);
        }
        return count;
    }

    @Override
    public List<VideoBO> getAllVideo(Date minTime, Date maxTime,
                                     String videoName) throws Exception {
        Map condition = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(minTime != null){
            condition.put("minTime", simpleDateFormat.format(minTime));
        }
        if(maxTime != null){
            condition.put("maxTime", simpleDateFormat.format(maxTime));
        }
        if(videoName != null && !videoName.equals("")){
            condition.put("videoName", videoName);
        }
        List<VideoBO> list = videoCustomMapper.getAllVideo(condition);
        return list;
    }

    @Override
    public String deleteMultiVideo(Integer[] idList) throws Exception {
        int count = 0; //删除条数
        for (Integer id : idList) {
            int num = videoMapper.deleteByPrimaryKey(id);
            count += num;
        }
        if(count == idList.length){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String updateVideoFrozen(Integer videoId) throws Exception {
        Video updateVideo = new Video();
        updateVideo.setVid(videoId);
        updateVideo.setVstatus("2");
        int num = videoMapper.updateByPrimaryKeySelective(updateVideo);
        if(num > 0){
            return "冻结成功";
        }
        return "冻结失败";
    }

    @Override
    public String updateVideoUnfrozen(Integer videoId) throws Exception {
        Video updateVideo = new Video();
        updateVideo.setVid(videoId);
        updateVideo.setVstatus("1");
        int num = videoMapper.updateByPrimaryKeySelective(updateVideo);
        if(num > 0){
            return "解冻成功";
        }
        return "解冻失败";
    }

    @Override
    public String checkVideo(Integer videoId, Boolean pass) throws Exception {
        Video updateVideo = new Video();
        updateVideo.setVid(videoId);
        if(pass == true){
            updateVideo.setVstatus("1");
        }else {
            updateVideo.setVstatus("3");
        }
        int num = videoMapper.updateByPrimaryKeySelective(updateVideo);
        if(num > 0){
            return "审核成功";
        }
        return "审核失败";
    }

    @Override
    public String deleteVideo(Integer videoId) throws Exception {
        int num = videoMapper.deleteByPrimaryKey(videoId);
        if(num > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public List<TagBO> getAllTag(String tagName) throws Exception {
        Map condition = new HashMap<String, Object>();
        if(tagName != null && !tagName.equals("")){
            condition.put("tname", tagName);
        }
        List<TagBO> list = tagCustomMapper.getAllTag(condition);
        return list;
    }

    @Override
    public String deleteMultiTag(Integer[] idList) throws Exception {
        int count = 0; //删除条数
        for (Integer id : idList) {
            int num = tagMapper.deleteByPrimaryKey(id);
            count += num;
        }
        if(count == idList.length){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String deleteTag(Integer tagId) throws Exception {
        int num = tagMapper.deleteByPrimaryKey(tagId);
        if(num > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public List<UserBO> getAllUser(String userName) throws Exception {
        Map condition = new HashMap<String, Object>();
        if(userName != null && !userName.equals("")){
            condition.put("uname", userName);
        }
        List<UserBO> list = userCustomMapper.getAllUser(condition);
        return list;
    }

    @Override
    public String deleteMultiUser(String[] idList) throws Exception {
        int count = 0; //删除条数
        for (String id : idList) {
            int num = userMapper.deleteByPrimaryKey(id);
            count += num;
        }
        if(count == idList.length){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String updateUserFrozen(String userId) throws Exception {
        User user = new User();
        user.setUid(userId);
        user.setUstatus("1");
        int num = userMapper.updateByPrimaryKeySelective(user);
        if(num > 0){
            return "冻结成功";
        }
        return "冻结失败";
    }

    @Override
    public String updateUserUnfrozen(String userId) throws Exception {
        User user = new User();
        user.setUid(userId);
        user.setUstatus("0");
        int num = userMapper.updateByPrimaryKeySelective(user);
        if(num > 0){
            return "解冻成功";
        }
        return "解冻失败";
    }

    @Override
    public String deleteUser(String userId) throws Exception {
        int num = userMapper.deleteByPrimaryKey(userId);
        if(num > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public List<UserBO> getAllUserIncludeAdmin(String userName) throws Exception {
        Map condition = new HashMap<String, Object>();
        if(userName != null && !userName.equals("")){
            condition.put("uname", userName);
        }
        List<UserBO> list = userCustomMapper.getAllUserIncludeAdmin(condition);
        return list;
    }

    @Override
    public String updateUserToAdmin(String userId) throws Exception {
        User user = new User();
        user.setUid(userId);
        user.setUlevel("1");
        int num = userMapper.updateByPrimaryKeySelective(user);
        if(num > 0){
            return "任命成功";
        }
        return "任命失败";
    }

    @Override
    public String updateAdminToUser(String userId) throws Exception {
        User user = new User();
        user.setUid(userId);
        user.setUlevel("2");
        int num = userMapper.updateByPrimaryKeySelective(user);
        if(num > 0){
            return "撤销成功";
        }
        return "撤销失败";
    }
}
