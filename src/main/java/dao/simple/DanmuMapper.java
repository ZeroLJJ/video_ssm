package dao.simple;

import entity.po.Danmu;

public interface DanmuMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Danmu record);

    int insertSelective(Danmu record);

    Danmu selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Danmu record);

    int updateByPrimaryKey(Danmu record);
}