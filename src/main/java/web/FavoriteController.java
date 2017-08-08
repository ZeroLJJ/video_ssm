package web;

import entity.po.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FavoriteItemService;
import service.FavoriteService;

import java.util.Map;

/**
 * Created by Zero on 2017/4/24.
 */
@Controller
@RequestMapping(value = "/favorite")
public class FavoriteController extends BaseController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private FavoriteItemService favoriteItemService;

    @ResponseBody
    @RequestMapping(value = "/getFavorite")
    public Map getFavorite(String userId) throws Exception{
        Map result = favoriteService.getFavoriteByUserId(userId);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public Map add(String userId, String name, String privacy) throws Exception{
        if(privacy == null){
            privacy = "1"; //默认公开
        }
        Map result = favoriteService.add(userId, name, privacy);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/commit")
    public String commit(@RequestParam(value = "idList[]") Integer[] idList,
                         Integer videoId) throws Exception{
            String msg = favoriteService.commit(idList, videoId);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/getById")
    public Favorite getById(Integer favoriteId) throws Exception{
        Favorite favorite = favoriteService.getFavoriteById(favoriteId);
        return favorite;
    }

    @ResponseBody
    @RequestMapping(value = "/commitEdit")
    public String commitEdit(Integer favoriteId, String name, String privacy) throws Exception{
        String msg = favoriteService.editFavoriteById(favoriteId, name, privacy);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById")
    public String deleteById(Integer favoriteId) throws Exception{
        String msg = favoriteService.deleteById(favoriteId);
        return msg;
    }

    //sourceId原收藏夹，targetId目标收藏夹,idList操作的明细id列表
    @ResponseBody
    @RequestMapping(value = "/copy")
    public String copy(@RequestParam(value = "idList[]") Integer[] idList,
                       Integer sourceId, Integer targetId) throws Exception{
        String msg = favoriteService.copyItem(idList, sourceId, targetId);
        return msg;
    }

    //sourceId原收藏夹，targetId目标收藏夹,idList操作的明细id列表
    @ResponseBody
    @RequestMapping(value = "/move")
    public String move(@RequestParam(value = "idList[]") Integer[] idList,
                       Integer sourceId, Integer targetId) throws Exception{
        String msg = favoriteService.copyItem(idList, sourceId, targetId);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteByIdList")
    public String deleteByIdList(@RequestParam(value = "idList[]") Integer[] idList,
                                 Integer favoriteId) throws Exception{
        String msg = favoriteItemService.deleteByIdList(idList, favoriteId);
        return msg;
    }
}
