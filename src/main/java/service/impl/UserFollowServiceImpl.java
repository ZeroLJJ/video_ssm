package service.impl;

import dao.custom.UserFollowCustomMapper;
import dao.simple.UserFollowMapper;
import entity.bo.UserFollowBO;
import entity.po.UserFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserFollowService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/10.
 */
@Service
public class UserFollowServiceImpl extends BaseService implements UserFollowService {
    @Autowired
    private UserFollowMapper userFollowMapper;
    @Autowired
    private UserFollowCustomMapper userFollowCustomMapper;

    @Override
    public String insertFollow(String follow_uid, String followed_uid) throws Exception {
        //拼装查询条件用于检测是否已经关注过
        Map condition = new HashMap<String, Object>();
        condition.put("follow_uid", follow_uid);
        condition.put("followed_uid", followed_uid);
        //查询是否关注过
        int count = userFollowCustomMapper.countByFollow(condition);
        if(count > 0){  //不为空，表示关注过
            return "已关注过，请勿重复关注";
        }
        //未关注过则设置关注信息准备插入
        UserFollow userFollow = new UserFollow();
        userFollow.setUftime(new Date());
        userFollow.setUserFollowId(follow_uid);
        userFollow.setUserFollowedId(followed_uid);
        int num = userFollowMapper.insert(userFollow);
        if(num > 0){   //大于0表示插入成功
            return "关注成功";
        }
        return "关注失败";
    }

    @Override
    public String deleteFollow(String follow_uid, String followed_uid) throws Exception {
        Map condition = new HashMap<String, Object>();
        condition.put("follow_uid", follow_uid);
        condition.put("followed_uid", followed_uid);
        int num = userFollowCustomMapper.deleteFollow(condition);
        if(num > 0){
            return "取消关注成功";
        }
        return "取消关注失败";
    }

    @Override
    public List<UserFollowBO> getFollow(String follow_uid) throws Exception {
        List<UserFollowBO> list = userFollowCustomMapper.selectByFollow(follow_uid);
        return list;
    }

    @Override
    public List<UserFollowBO> getFan(String followed_uid) throws Exception {
        List<UserFollowBO> list = userFollowCustomMapper.selectByFollowed(followed_uid);
        return list;
    }

    @Override
    public Integer countByFollow(String follow_uid, String followed_uid) throws Exception {
        Map condition = new HashMap<String, Object>();
        condition.put("follow_uid", follow_uid);
        condition.put("followed_uid", followed_uid);
        int count = userFollowCustomMapper.countByFollow(condition);
        return count;
    }
}
