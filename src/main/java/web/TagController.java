package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TagFollowService;
import service.TagItemService;
import service.TagService;

import java.util.Map;

/**
 * Created by Zero on 2017/4/20.
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController extends BaseController{
    @Autowired
    private TagService tagService;

    @Autowired
    private TagItemService tagItemService;

    @Autowired
    private TagFollowService tagFollowService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public Map add(String userId, Integer videoId, String name) throws Exception{
        Map result = tagItemService.add(userId, videoId, name);
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer tagItemId) throws Exception{
        String msg = tagItemService.delete(tagItemId);
        return msg;
    }

    @RequestMapping(value = "/follow")
    @ResponseBody
    public String follow(String userId, Integer tagId) throws Exception{
        String msg = tagFollowService.insertFollow(userId, tagId);
        return msg;
    }

    @RequestMapping(value = "/unfollow")
    @ResponseBody
    public String unfollow(String userId, Integer tagId) throws Exception{
        String msg = tagFollowService.deleteFollow(userId, tagId);
        return msg;
    }
}
