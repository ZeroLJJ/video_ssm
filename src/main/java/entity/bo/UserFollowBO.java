package entity.bo;

import entity.po.UserFollow;

/**
 * 用户关注扩展类
 * Created by Zero on 2017/4/2.
 */
public class UserFollowBO extends UserFollow {

    private UserBO userBO;

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }
}
