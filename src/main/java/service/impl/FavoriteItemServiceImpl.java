package service.impl;

import dao.custom.FavoriteItemCustomMapper;
import dao.simple.FavoriteItemMapper;
import dao.simple.FavoriteMapper;
import entity.bo.FavoriteItemBO;
import entity.po.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FavoriteItemService;
import util.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/2/26.
 */
@Service
public class FavoriteItemServiceImpl extends BaseService implements FavoriteItemService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private FavoriteItemMapper favoriteItemMapper;
    @Autowired
    private FavoriteItemCustomMapper favoriteItemCustomMapper;

    @Override
    public Page getFavoriteItemPage(Integer favoriteId, Integer pageNo) throws Exception {
        int pageSize = 18;   //每页记录数
        Map<String, Object> condition = new HashMap<String, Object>();   // 存放所要搜索的相关信息
        List<FavoriteItemBO> favoriteItemList = new ArrayList<FavoriteItemBO>();   // 当前页的视频列表
        int offset = Page.countOffset(pageSize, pageNo);    // 当前页开始记录
        condition.put("offset",offset);
        condition.put("pageSize",pageSize);
        condition.put("fid",favoriteId);
        int allRow = favoriteItemCustomMapper.getFavoriteItemsCount(favoriteId);    // 总记录数
        int currentPage = Page.countCurrentPage(pageNo);    // 当前页
        int totalPage = Page.countTotalPage(pageSize,allRow);   // 总页数
        favoriteItemList = favoriteItemCustomMapper.getListByFavoriteIdAndPage(condition);  //查询视频列表
        Page page = new Page();
        //开始设置页面信息
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(favoriteItemList);
        Page.init(page, currentPage, totalPage);
        return page;
    }

    @Override
    public String deleteByIdList(Integer[] idList, Integer favoriteId) throws Exception {
        int count = 0; //统计删除条数
        for (Integer fiid : idList) {
            int num = favoriteItemMapper.deleteByPrimaryKey(fiid);
            count += num;
        }
        if(count == idList.length){ //删除成功
            //减去对应收藏夹拥有的视频数量
            Favorite favorite = favoriteMapper.selectByPrimaryKey(favoriteId);
            Favorite updateFavorite = new Favorite();
            updateFavorite.setFid(favorite.getFid());
            updateFavorite.setFhave(favorite.getFhave() - count);
            favoriteMapper.updateByPrimaryKeySelective(updateFavorite);
            return "删除成功";
        }
        return "删除失败";
    }
}
