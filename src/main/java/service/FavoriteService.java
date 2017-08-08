package service;

import entity.bo.FavoriteBO;
import entity.po.Favorite;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/2/26.
 */
public interface FavoriteService {
    /**
     * 获取某个用户的收藏夹信息
     * @param userId 用户编号
     * @return 收藏夹信息
     * @throws Exception
     */
    Map getFavoriteByUserId(String userId) throws Exception;

    /**
     * 新增一个收藏夹
     * @param userId 用户编号
     * @param name 收藏夹名
     * @param privacy 隐私级别
     * @return
     * @throws Exception
     */
    Map add(String userId, String name, String privacy) throws Exception;

    /**
     * 提交视频添加至收藏夹
     * @param idList 收藏夹id列表
     * @param videoId 视频编号
     * @return 提示信息
     * @throws Exception
     */
    String commit(Integer[] idList, Integer videoId) throws Exception;

    /**
     * 获取最近创建的收藏夹（8个）
     * @param userId 用户编号
     * @return 收藏夹列表
     * @throws Exception
     */
    List<FavoriteBO> getRencentlyFavorite(String userId) throws Exception;

    /**
     * 获取用户所有收藏夹
     * @param userId 用户编号
     * @return 收藏夹列表
     * @throws Exception
     */
    List<FavoriteBO> getAllFavorite(String userId) throws Exception;

    /**
     * 通过主键获取收藏夹信息
     * @param favoriteId 收藏夹编号
     * @return 收藏夹信息
     * @throws Exception
     */
    Favorite getFavoriteById(Integer favoriteId) throws Exception;

    /**
     * 编辑收藏夹信息通过编号
     * @param favoriteId 收藏夹编号
     * @param name 收藏夹名
     * @param privacy 收藏夹私密级别
     * @return 提示信息
     * @throws Exception
     */
    String editFavoriteById(Integer favoriteId, String name, String privacy) throws Exception;

    /**
     * 删除收藏夹
     * @param favoriteId 收藏夹编号
     * @return 提示信息
     * @throws Exception
     */
    String deleteById(Integer favoriteId) throws Exception;

    /**
     * 将一些明细子项从原收藏夹移动到目标收藏夹
     * @param idList 子项id列表
     * @param sourceId 原收藏夹
     * @param targetId 目标收藏夹
     * @return 提示信息
     * @throws Exception
     */
    String moveItem(Integer[] idList, Integer sourceId, Integer targetId) throws Exception;

    /**
     * 将一些明细子项从原收藏夹复制到目标收藏夹
     * @param idList 子项id列表
     * @param sourceId 原收藏夹
     * @param targetId 目标收藏夹
     * @return 提示信息
     * @throws Exception
     */
    String copyItem(Integer[] idList, Integer sourceId, Integer targetId) throws Exception;
}
