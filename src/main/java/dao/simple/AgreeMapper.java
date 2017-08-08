package dao.simple;

import entity.po.Agree;

public interface AgreeMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Agree record);

    int insertSelective(Agree record);

    Agree selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Agree record);

    int updateByPrimaryKey(Agree record);
}