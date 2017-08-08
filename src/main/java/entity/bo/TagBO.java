package entity.bo;

import entity.po.Tag;

/**
 * 标签扩展类
 * Created by Zero on 2017/4/2.
 */
public class TagBO extends Tag {

    //标志某对该标签关注统计信息，大于0表示有关注
    private Integer tagFollowCount;

    public Integer getTagFollowCount() {
        return tagFollowCount;
    }

    public void setTagFollowCount(Integer tagFollowCount) {
        this.tagFollowCount = tagFollowCount;
    }
}
