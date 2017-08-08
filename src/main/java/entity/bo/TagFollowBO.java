package entity.bo;

import entity.po.TagFollow;

/**
 * 标签关注扩展类
 * Created by Zero on 2017/4/2.
 */
public class TagFollowBO extends TagFollow {

    private TagBO tagBO;

    public TagBO getTagBO() {
        return tagBO;
    }

    public void setTagBO(TagBO tagBO) {
        this.tagBO = tagBO;
    }
}
