package service.impl;

import dao.custom.TagCustomMapper;
import dao.custom.TagItemCustomMapper;
import dao.simple.TagItemMapper;
import dao.simple.TagMapper;
import entity.bo.TagItemBO;
import entity.po.Tag;
import entity.po.TagItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TagItemService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zero on 2017/3/20.
 */
@Service
public class TagItemServiceImpl extends BaseService implements TagItemService {
    @Autowired
    private TagItemMapper tagItemMapper;
    @Autowired
    private TagItemCustomMapper tagItemCustomMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagCustomMapper tagCustomMapper;

    @Override
    public Map add(String userId, Integer videoId, String name) throws Exception {
        //存放返回的信息，message为提示信息，object为对象
        Map result = new HashMap<String, Object>();
        TagItemBO tagItemBO = null;  //要返回的对象
        Tag tag = tagCustomMapper.selectByName(name);
        TagItem tagItem;
        if(tag != null){    //新增标签名已存在
            Map<String, Object> condition = new HashMap();
            condition.put("videoId", videoId);
            condition.put("tagId", tag.getTid());
            tagItem = tagItemCustomMapper.selectByTagAndVideo(condition);
            if(tagItem != null){ //不为空，说明同一视频已存在同一标签，不能重复添加
                result.put("message", "该视频已存在该标签");
                result.put("object", tagItemBO);
                return result;
            }
            //拥有视频数加1
            tag.setTvideoNum(tag.getTvideoNum() + 1);
            tagMapper.updateByPrimaryKeySelective(tag);
        }else {  //新增标签名未存在，则新建该标签
            tag = new Tag();
            tag.setTname(name);
            tag.setTvideoNum(1);
            tag.setTsearch(0);
            //插入新标签并回填主键
            tagCustomMapper.insertReturnId(tag);
        }
        //设置标签明细信息
        tagItem = new TagItem();
        tagItem.setTiagree(0);
        tagItem.setTidate(new Date());
        tagItem.setUserId(userId);
        tagItem.setVideoId(videoId);
        tagItem.setTagId(tag.getTid());
        int num = tagItemCustomMapper.insertReturnId(tagItem);
        if(num > 0){
            tagItemBO = tagItemCustomMapper.selectByPrimary(tagItem.getTiid());
            result.put("message", "新增成功");
            result.put("object", tagItemBO);
            return result;
        }
        result.put("message", "新增失败");
        result.put("object", tagItemBO);
        return result;
    }

    @Override
    public String delete(Integer tagItemId) throws Exception {
        //先获取标签信息，避免删除后无法获取
        Tag tag = tagCustomMapper.selectByTagItem(tagItemId);
        //删除标签明细
        int num = tagItemMapper.deleteByPrimaryKey(tagItemId);
        if(num > 0){  //删除成功
            //标签拥有视频数-1
            tag.setTvideoNum(tag.getTvideoNum() - 1);
            tagMapper.updateByPrimaryKeySelective(tag);
            return "删除成功";
        }
        return "删除失败";
    }
}
