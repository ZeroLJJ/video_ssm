package dao.simple;

import entity.po.BigType;

public interface BigTypeMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(BigType record);

    int insertSelective(BigType record);

    BigType selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(BigType record);

    int updateByPrimaryKey(BigType record);
}