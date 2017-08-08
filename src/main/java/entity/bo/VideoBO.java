package entity.bo;

import entity.po.Video;

import java.util.List;

/**
 * 视频扩展类
 * Created by Zero on 2017/4/2.
 */
public class VideoBO extends Video {
    //视频所属大类
    private BigTypeBO bigTypeBO;
    //视频所属小类
    private SmallTypeBO smallTypeBO;
    //上传用户
    private UserBO userBO;
    //标签列表
    private List<TagItemBO> tagItemBOList;
    //评论列表
    private List<CommentBO> commentBOList;

    //无参数构造
    public VideoBO() {
    }

    public BigTypeBO getBigTypeBO() {
        return bigTypeBO;
    }

    public void setBigTypeBO(BigTypeBO bigTypeBO) {
        this.bigTypeBO = bigTypeBO;
    }

    public SmallTypeBO getSmallTypeBO() {
        return smallTypeBO;
    }

    public void setSmallTypeBO(SmallTypeBO smallTypeBO) {
        this.smallTypeBO = smallTypeBO;
    }

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    public List<TagItemBO> getTagItemBOList() {
        return tagItemBOList;
    }

    public void setTagItemBOList(List<TagItemBO> tagItemBOList) {
        this.tagItemBOList = tagItemBOList;
    }

    public List<CommentBO> getCommentBOList() {
        return commentBOList;
    }

    public void setCommentBOList(List<CommentBO> commentBOList) {
        this.commentBOList = commentBOList;
    }
}
