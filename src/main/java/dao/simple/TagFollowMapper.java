package dao.simple;

import entity.po.TagFollow;

public interface TagFollowMapper {
    int deleteByPrimaryKey(Integer tfid);

    int insert(TagFollow record);

    int insertSelective(TagFollow record);

    TagFollow selectByPrimaryKey(Integer tfid);

    int updateByPrimaryKeySelective(TagFollow record);

    int updateByPrimaryKey(TagFollow record);
}