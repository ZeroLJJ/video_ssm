package dao.custom;

import java.util.Map;

/**
 * Created by Zero on 2017/4/20.
 */
public interface AgreeCustomMapper {
    /**
     * 统计符合条件的点赞记录数
     * @param condition 条件（包含以下条件）
     * param userId 用户编号
     * param type 目标类型
     * param targetId 目标编号
     * @return 统计数
     * @throws Exception
     */
    Integer getCountAgree(Map condition) throws Exception;
}
