package dao.simple;

import entity.po.Favorite;

public interface FavoriteMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
}