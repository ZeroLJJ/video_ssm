package dao.custom;

import entity.bo.BigTypeBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/13.
 */
public interface BigTypeCustomMapper {
    /**
     * 获取视频类型
     * @return 视频大小类型
     * @throws Exception
     */
    List<BigTypeBO> getVideoType() throws Exception;

    /**
     * 获取不同类型近期投稿新增的视频数量
     * @param condition 查询条件
     * @return 统计数
     * @throws Exception
     */
    Integer countRecentlyPostByType(Map condition) throws Exception;
}
