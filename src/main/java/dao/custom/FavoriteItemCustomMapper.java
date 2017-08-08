package dao.custom;

import entity.bo.FavoriteItemBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/24.
 */
public interface FavoriteItemCustomMapper {

    /**
     * 获取封面路径列表
     * @param fid 收藏夹编号
     * @return 封面路径列表
     * @throws Exception
     */
    List<String> getCovorList(Integer fid) throws Exception;

    /**
     * 通过收藏夹编号和页码获取收藏夹明细列表
     * @param condition 查询条件
     * @return 收藏夹明细列表
     */
    List<FavoriteItemBO> getListByFavoriteIdAndPage(Map condition) throws Exception;

    /**
     * 获取某个收藏夹的总记录数
     * @param fid 收藏夹编号
     * @return 记录数
     */
    Integer getFavoriteItemsCount(Integer fid) throws Exception;

    /**
     * 统计某个条件下的明细记录数
     * @param condition 条件（视频编号和收藏夹编号）
     * @return 记录数
     * @throws Exception
     */
    Integer countByVideoAndFavorite(Map condition) throws Exception;
}
