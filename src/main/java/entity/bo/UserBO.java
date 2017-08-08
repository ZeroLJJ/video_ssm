package entity.bo;

import entity.po.User;

/**
 * 用户扩展类
 * Created by Zero on 2017/4/2.
 */
public class UserBO extends User {

    //标志某对该标签关注统计信息，大于0表示有关注
    private Integer userFollowCount;

    public Integer getUserFollowCount() {
        return userFollowCount;
    }

    public void setUserFollowCount(Integer userFollowCount) {
        this.userFollowCount = userFollowCount;
    }
}
