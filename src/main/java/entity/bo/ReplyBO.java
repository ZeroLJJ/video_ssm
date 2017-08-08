package entity.bo;

import entity.po.Reply;

/**
 * 回复扩展类
 * Created by Zero on 2017/4/2.
 */
public class ReplyBO extends Reply {
    //回复用户
    private UserBO userBO;

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }
}
