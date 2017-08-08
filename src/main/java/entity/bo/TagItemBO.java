package entity.bo;

import entity.po.TagItem;

/**
 * 标签详情扩展类
 * Created by Zero on 2017/4/2.
 */
public class TagItemBO extends TagItem {
    //标签信息
    private TagBO tagBO;

    public TagBO getTagBO() {
        return tagBO;
    }

    public void setTagBO(TagBO tagBO) {
        this.tagBO = tagBO;
    }
}
