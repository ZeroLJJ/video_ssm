package service.impl;

import dao.custom.FavoriteCustomMapper;
import dao.custom.FavoriteItemCustomMapper;
import dao.simple.FavoriteItemMapper;
import dao.simple.FavoriteMapper;
import dao.simple.VideoMapper;
import entity.bo.FavoriteBO;
import entity.po.Favorite;
import entity.po.FavoriteItem;
import entity.po.Video;
import exception.ExistVideoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FavoriteService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/2/26.
 */
@Service
public class FavoriteServiceImpl extends BaseService implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private FavoriteCustomMapper favoriteCustomMapper;
    @Autowired
    private FavoriteItemMapper favoriteItemMapper;
    @Autowired
    private FavoriteItemCustomMapper favoriteItemCustomMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Map getFavoriteByUserId(String userId) throws Exception {
        Map result = new HashMap<String, Object>();
        String msg; //返回提示信息
        List<FavoriteBO> list = favoriteCustomMapper.getFavorite(userId);
        if(list != null){
            msg = "获取成功";
        }else {
            msg = "收藏夹信息获取失败";
        }
        result.put("msg", msg);
        result.put("obj", list);
        return result;
    }

    @Override
    public Map add(String userId, String name, String privacy) throws Exception {
        Map result = new HashMap<String, Object>();
        String msg; //返回提示信息
        //设置要插入的收藏夹信息
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setFname(name);
        favorite.setFcreateDate(new Date());
        favorite.setFhave(0);
        favorite.setFtotal(100);
        favorite.setFprivacy(privacy);
        int num = favoriteCustomMapper.insert(favorite);
        if(num > 0){
            msg = "新增成功";
        }else {
            msg = "新增收藏夹失败";
        }
        result.put("msg", msg);
        result.put("obj", favorite);
        return result;
    }

    @Override
    public String commit(Integer[] idList, Integer videoId) throws Exception {
        for (Integer id : idList) {
            FavoriteItem favoriteItem = new FavoriteItem();
            favoriteItem.setFidate(new Date());
            favoriteItem.setFavoriteId(id);
            favoriteItem.setVideoId(videoId);
            int num = favoriteItemMapper.insert(favoriteItem);
            if(num > 0){
                Favorite favorite = favoriteMapper.selectByPrimaryKey(id);
                int nowHave = favorite.getFhave() + 1;
                //如果未超过收藏夹上限，则添加，否则返回错误信息
                if(nowHave <= favorite.getFtotal()) {
                    Favorite updateFavorite = new Favorite();
                    updateFavorite.setFid(id);
                    updateFavorite.setFhave(nowHave);
                    favoriteMapper.updateByPrimaryKeySelective(updateFavorite);
                }else {
                    return "收藏夹" + favorite.getFname() + "超出收藏夹上限，添加失败";
                }
            }else {
                return "添加收藏夹明细失败";
            }
        }
        //修改视频收藏次数
        Video video = videoMapper.selectByPrimaryKey(videoId);
        Video updateVideo = new Video();
        updateVideo.setVid(videoId);
        updateVideo.setVfavoriteTimes(video.getVfavoriteTimes() + 1);
        videoMapper.updateByPrimaryKeySelective(updateVideo);
        return "添加至收藏夹成功";
    }

    @Override
    public List<FavoriteBO> getRencentlyFavorite(String userId) throws Exception {
        List<FavoriteBO> favoriteList = favoriteCustomMapper.getRecentlyFavorite(userId);
        for (FavoriteBO favoriteBO : favoriteList) {
            //获取封面列表
            List<String> covorList = favoriteItemCustomMapper.getCovorList(favoriteBO.getFid());
            favoriteBO.setCovorList(covorList);
        }
        return favoriteList;
    }

    @Override
    public List<FavoriteBO> getAllFavorite(String userId) throws Exception {
        List<FavoriteBO> favoriteList = favoriteCustomMapper.getAllFavorite(userId);
        for (FavoriteBO favoriteBO : favoriteList) {
            //获取封面列表
            List<String> covorList = favoriteItemCustomMapper.getCovorList(favoriteBO.getFid());
            favoriteBO.setCovorList(covorList);
        }
        return favoriteList;
    }

    @Override
    public Favorite getFavoriteById(Integer favoriteId) throws Exception {
        Favorite favorite = favoriteMapper.selectByPrimaryKey(favoriteId);
        return favorite;
    }

    @Override
    public String editFavoriteById(Integer favoriteId, String name, String privacy) throws Exception {
        String msg; //提示信息
        Favorite favorite = new Favorite();
        favorite.setFid(favoriteId);
        favorite.setFname(name);
        favorite.setFprivacy(privacy);
        int num = favoriteMapper.updateByPrimaryKeySelective(favorite);
        if(num > 0){
            msg = "编辑成功";
        }else {
            msg = "编辑失败";
        }
        return msg;
    }

    @Override
    public String deleteById(Integer favoriteId) throws Exception {
        String msg; //提示信息
        int num = favoriteMapper.deleteByPrimaryKey(favoriteId);
        if(num > 0){
            msg = "删除成功";
        }else {
            msg = "删除失败";
        }
        return msg;
    }

    @Override
    public String moveItem(Integer[] idList, Integer sourceId, Integer targetId) throws Exception {
        int count = 0; //修改的条数统计
        for (Integer fiid : idList) {
            FavoriteItem favoriteItem = favoriteItemMapper.selectByPrimaryKey(fiid);
            //查看目标收藏夹是否有相同视频存在
            Map condition = new HashMap<String, Object>();
            condition.put("vid", favoriteItem.getVideoId());
            condition.put("fid", targetId);
            int n = favoriteItemCustomMapper.countByVideoAndFavorite(condition);
            if(n > 0){
                throw new ExistVideoException("目标收藏夹已存在选中视频");
            }
            //修改明细子项所属收藏夹
            FavoriteItem updateFavoriteItem = new FavoriteItem();
            updateFavoriteItem.setFiid(favoriteItem.getFiid());
            updateFavoriteItem.setFavoriteId(targetId);
            int num = favoriteItemMapper.updateByPrimaryKeySelective(updateFavoriteItem);
            count += num;
        }
        if(count == idList.length){ //修改条数等于传入的id数，表示修改成功
            //修改原收藏夹拥有视频数
            Favorite source = favoriteMapper.selectByPrimaryKey(sourceId);
            Favorite updateSource = new Favorite();
            updateSource.setFid(source.getFid());
            updateSource.setFhave(source.getFhave() - count);
            favoriteMapper.updateByPrimaryKeySelective(updateSource);
            //修改目标收藏夹拥有视频数
            Favorite target = favoriteMapper.selectByPrimaryKey(targetId);
            Favorite updateTarget = new Favorite();
            updateTarget.setFid(target.getFid());
            updateTarget.setFhave(target.getFhave() + count);
            favoriteMapper.updateByPrimaryKeySelective(updateTarget);
            return "移动成功";
        }
        return "移动失败";
    }

    @Override
    public String copyItem(Integer[] idList, Integer sourceId, Integer targetId) throws Exception {
        int count = 0; //修改的条数统计
        for (Integer fiid : idList) {
            FavoriteItem favoriteItem = favoriteItemMapper.selectByPrimaryKey(fiid);
            //查看目标收藏夹是否有相同视频存在
            Map condition = new HashMap<String, Object>();
            condition.put("vid", favoriteItem.getVideoId());
            condition.put("fid", targetId);
            int n = favoriteItemCustomMapper.countByVideoAndFavorite(condition);
            if(n > 0){
                throw new ExistVideoException("目标收藏夹已存在选中视频");
            }
            //修改明细子项所属收藏夹
            FavoriteItem copy = new FavoriteItem();
            copy.setFidate(favoriteItem.getFidate());
            copy.setVideoId(favoriteItem.getVideoId());
            copy.setFavoriteId(targetId);
            int num = favoriteItemMapper.insert(copy);
            count += num;
        }
        if(count == idList.length){ //修改条数等于传入的id数，表示修改成功
            //修改目标收藏夹拥有视频数
            Favorite target = favoriteMapper.selectByPrimaryKey(targetId);
            Favorite updateTarget = new Favorite();
            updateTarget.setFid(target.getFid());
            updateTarget.setFhave(target.getFhave() + count);
            favoriteMapper.updateByPrimaryKeySelective(updateTarget);
            return "复制成功";
        }
        return "复制失败";
    }
}
