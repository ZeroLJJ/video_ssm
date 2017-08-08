package dao.simple;

import entity.po.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer scid);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer scid);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}