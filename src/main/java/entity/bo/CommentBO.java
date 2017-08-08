package entity.bo;

import entity.po.Comment;
import util.Page;

/**
 * 评论扩展类
 * Created by Zero on 2017/4/2.
 */
public class CommentBO extends Comment {
    //评论用户
    private UserBO userBO;
    //回复列表页面信息
    private Page replyPage;

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    public Page getReplyPage() {
        return replyPage;
    }

    public void setReplyPage(Page replyPage) {
        this.replyPage = replyPage;
    }
}
