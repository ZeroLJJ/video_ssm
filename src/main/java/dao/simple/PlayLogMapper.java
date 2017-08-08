package dao.simple;

import entity.po.PlayLog;

public interface PlayLogMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(PlayLog record);

    int insertSelective(PlayLog record);

    PlayLog selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PlayLog record);

    int updateByPrimaryKey(PlayLog record);
}