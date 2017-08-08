package dao.simple;

import entity.po.SmallType;

public interface SmallTypeMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(SmallType record);

    int insertSelective(SmallType record);

    SmallType selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(SmallType record);

    int updateByPrimaryKey(SmallType record);
}