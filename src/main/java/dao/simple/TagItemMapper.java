package dao.simple;

import entity.po.TagItem;

public interface TagItemMapper {
    int deleteByPrimaryKey(Integer tiid);

    int insert(TagItem record);

    int insertSelective(TagItem record);

    TagItem selectByPrimaryKey(Integer tiid);

    int updateByPrimaryKeySelective(TagItem record);

    int updateByPrimaryKey(TagItem record);
}