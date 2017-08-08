package service.impl;

import dao.custom.TagFollowCustomMapper;
import dao.simple.TagFollowMapper;
import entity.bo.TagFollowBO;
import entity.po.TagFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TagFollowService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/10.
 */
@Service
public class TagFollowServiceImpl extends BaseService implements TagFollowService {
    @Autowired
    private TagFollowMapper tagFollowMapper;
    @Autowired
    private TagFollowCustomMapper tagFollowCustomMapper;

    @Override
    public String insertFollow(String userId, Integer tagId) throws Exception {
        //拼装查询条件用于检测是否已经关注过
        Map condition = new HashMap<String, Object>();
        condition.put("uid", userId);
        condition.put("tid", tagId);
        //查询是否关注过
        int count = tagFollowCustomMapper.countByUserAndTag(condition);
        if(count > 0){  //不为空，表示关注过
            return "已关注过，请勿重复关注";
        }
        //未关注过则设置关注信息准备插入
        TagFollow tagFollow = new TagFollow();
        tagFollow.setTftime(new Date());
        tagFollow.setUserId(userId);
        tagFollow.setTagId(tagId);
        int num = tagFollowMapper.insert(tagFollow);
        if(num > 0){
            return "关注成功";
        }
        return "关注失败";
    }

    @Override
    public String deleteFollow(String userId, Integer tagId) throws Exception {
        Map condition = new HashMap<String, Object>();
        condition.put("uid", userId);
        condition.put("tid", tagId);
        int num = tagFollowCustomMapper.deleteFollow(condition);
        if(num > 0){
            return "取消关注成功";
        }
        return "取消关注失败";
    }

    @Override
    public List<TagFollowBO> getTag(String userId) throws Exception {
        List<TagFollowBO> list = tagFollowCustomMapper.selectListByUserId(userId);
        return list;
    }
}
