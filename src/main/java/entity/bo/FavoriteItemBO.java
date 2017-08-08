package entity.bo;

import entity.po.FavoriteItem;

/**
 * 收藏详情扩展类
 * Created by Zero on 2017/4/2.
 */
public class FavoriteItemBO extends FavoriteItem {

    private VideoBO videoBO;

    public VideoBO getVideoBO() {
        return videoBO;
    }

    public void setVideoBO(VideoBO videoBO) {
        this.videoBO = videoBO;
    }
}
