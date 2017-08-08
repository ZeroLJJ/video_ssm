package dao.simple;

import entity.po.UserFollow;

public interface UserFollowMapper {
    int deleteByPrimaryKey(Integer ufid);

    int insert(UserFollow record);

    int insertSelective(UserFollow record);

    UserFollow selectByPrimaryKey(Integer ufid);

    int updateByPrimaryKeySelective(UserFollow record);

    int updateByPrimaryKey(UserFollow record);
}