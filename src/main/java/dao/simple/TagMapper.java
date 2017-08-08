package dao.simple;

import entity.po.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}