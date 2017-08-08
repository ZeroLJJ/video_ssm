package service;

import util.Page;

/**
 * Created by Zero on 2017/2/26.
 */
public interface FavoriteItemService {
    /**
     * 获取收藏夹明细分页
     * @param favoriteId 收藏夹编号
     * @param pageNo 页码
     * @return 收藏夹明细分页信息
     * @throws Exception
     */
    Page getFavoriteItemPage(Integer favoriteId, Integer pageNo) throws Exception;

    /**
     * 删除某个收藏夹中选中的明细项
     * @param idList 编号列表
     * @param favoriteId 收藏夹编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteByIdList(Integer[] idList, Integer favoriteId) throws Exception;
}
