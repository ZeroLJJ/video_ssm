package web;

import entity.bo.*;
import entity.po.Favorite;
import entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.*;
import util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static util.Constant.*;

/**
 * Created by Zero on 2017/4/24.
 */
@Controller
@RequestMapping(value = "/space/{uid}")
public class SpaceController extends BaseController{
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private FavoriteItemService favoriteItemService;
    @Autowired
    private UserFollowService userFollowService;
    @Autowired
    private TagFollowService tagFollowService;

    //该controller都会执行这个方法，判断是否为空间的拥有者
    @ModelAttribute(value = "isOwner")
    public Boolean isOwner(@PathVariable("uid") String userId, HttpSession session){
        Boolean isOwner = false;  //默认不是空间的拥有者
        User user = (User) session.getAttribute("user");
        if(user != null && user.getUid().equals(userId)){   //如果查看的空间
            isOwner = true;
        }
        return isOwner;
    }

    //空间拥有者
    @ModelAttribute(value = "spaceUser")
    public User spaceUser(@PathVariable("uid") String userId) throws Exception{
        User spaceUser = userService.getUser(userId);
        return spaceUser;
    }

    //空间首页
    @RequestMapping(value = {"","/","/index"})
    public String getSpace(Map dataMap, @PathVariable("uid") String userId) throws Exception{
        List<VideoBO> videoList = videoService.getRecentlyPost(userId);
        List<FavoriteBO> favoriteList = favoriteService.getRencentlyFavorite(userId);
        List<VideoBO> scoreList = scoreService.getRecentlyScoreVideo(userId);
        dataMap.put("videoList", videoList);
        dataMap.put("favoriteList", favoriteList);
        dataMap.put("scoreList", scoreList);
        return URL_SPACE;
    }

    //空间收藏夹页
    @RequestMapping(value = "/favorite")
    public String getFavorite(Map dataMap, @PathVariable("uid") String userId) throws Exception{
        List<FavoriteBO> favoriteList = favoriteService.getAllFavorite(userId);
        dataMap.put("favoriteList", favoriteList);
        return URL_SPACE_FAVORITE;
    }

    //空间收藏夹明细页
    @RequestMapping(value = "/favorite_item")
    public String getFavoriteItem(Map dataMap, HttpServletRequest request) throws Exception{
        int favoriteId = 0; //默认为0，即不表示任何收藏夹
        int pageNo = 1; //默认为第一页
        if(request.getParameter("fid") != null && !request.getParameter("fid").equals("")){
            favoriteId = Integer.parseInt(request.getParameter("fid"));
        }
        if(request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals("")){
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        //收藏夹明细分页信息
        Page favoriteItemPage = favoriteItemService.getFavoriteItemPage(favoriteId, pageNo);
        //收藏夹信息
        Favorite favorite = favoriteService.getFavoriteById(favoriteId);
        dataMap.put("favItemPage",favoriteItemPage);
        dataMap.put("fav", favorite);
        return URL_SPACE_FAVORITE_ITEM;
    }

    //空间投稿页
    @RequestMapping(value = "/video")
    public String getPostVideo(Map dataMap, @PathVariable("uid") String userId,
                               HttpServletRequest request) throws Exception{
        int pageNo = 1; //默认为第一页
        if(request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals("")){
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        }
        //获取稿件视频分页信息
        Page postPage = videoService.getVideoPageByPost(userId, pageNo);
        dataMap.put("postPage", postPage);
        return URL_SPACE_VIDEO;
    }

    //空间关注页
    @RequestMapping(value = "/follow")
    public String getFollow(Map dataMap, @PathVariable("uid") String userId) throws Exception{
        //获取关注列表
        List<UserFollowBO> followList = userFollowService.getFollow(userId);
        dataMap.put("followList", followList);
        return URL_SPACE_FOLLOW;
    }

    //空间粉丝页
    @RequestMapping(value = "/fan")
    public String getFan(Map dataMap, @PathVariable("uid") String userId) throws Exception{
        //获取粉丝列表
        List<UserFollowBO> fanList = userFollowService.getFan(userId);
        //获取粉丝关注信息
        for (UserFollowBO userFollowBO : fanList) {
            UserBO userBO = userFollowBO.getUserBO();
            int count = userFollowService.countByFollow(userId, userBO.getUid());
            userBO.setUserFollowCount(count);
        }
        dataMap.put("fanList", fanList);
        return URL_SPACE_FAN;
    }

    //空间标签页
    @RequestMapping(value = "/tag")
    public String getTag(Map dataMap, @PathVariable("uid") String userId) throws Exception{
        List<TagFollowBO> tagList = tagFollowService.getTag(userId);
        dataMap.put("tagList", tagList);
        return URL_SPACE_TAG;
    }
}
