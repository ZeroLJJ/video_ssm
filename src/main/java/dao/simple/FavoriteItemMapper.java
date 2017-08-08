package dao.simple;

import entity.po.FavoriteItem;

public interface FavoriteItemMapper {
    int deleteByPrimaryKey(Integer fiid);

    int insert(FavoriteItem record);

    int insertSelective(FavoriteItem record);

    FavoriteItem selectByPrimaryKey(Integer fiid);

    int updateByPrimaryKeySelective(FavoriteItem record);

    int updateByPrimaryKey(FavoriteItem record);
}