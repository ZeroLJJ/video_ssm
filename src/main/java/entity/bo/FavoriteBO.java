package entity.bo;

import entity.po.Favorite;

import java.util.List;

/**
 * 收藏扩展类
 * Created by Zero on 2017/4/2.
 */
public class FavoriteBO extends Favorite {

    List<String> covorList; //封面路径列表

    public List<String> getCovorList() {
        return covorList;
    }

    public void setCovorList(List<String> covorList) {
        this.covorList = covorList;
    }
}
