package entity.vo;

import entity.bo.UserBO;

import java.util.List;

/**
 * 用户包装类
 * Created by Zero on 2017/3/12.
 */
public class UserVO {
    private UserBO user;

    private List<UserBO> userList;

    public UserBO getUser() {
        return user;
    }

    public void setUser(UserBO user) {
        this.user = user;
    }

    public List<UserBO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserBO> userList) {
        this.userList = userList;
    }
}
