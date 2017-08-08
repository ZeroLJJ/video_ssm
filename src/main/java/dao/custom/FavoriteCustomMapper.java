package dao.custom;

import entity.bo.FavoriteBO;
import entity.po.Favorite;

import java.util.List;

/**
 * Created by Zero on 2017/4/24.
 */
public interface FavoriteCustomMapper {
    /**
     * 获取用户收藏夹信息
     * @param userId 用户编号
     * @return 收藏夹信息
     * @throws Exception
     */
    List<FavoriteBO> getFavorite(String userId) throws Exception;

    /**
     * 插入收藏夹信息并返回主键
     * @param favorite 收藏夹信息
     * @return 插入影响条数
     * @throws Exception
     */
    Integer insert(Favorite favorite) throws Exception;

    /**
     * 获取最近收藏夹信息
     * @param userId 用户编号
     * @return 收藏夹列表
     * @throws Exception
     */
    List<FavoriteBO> getRecentlyFavorite(String userId) throws Exception;

    /**
     * 获取用户所有收藏夹信息
     * @param userId 用户编号
     * @return 收藏夹列表
     * @throws Exception
     */
    List<FavoriteBO> getAllFavorite(String userId) throws Exception;

}
