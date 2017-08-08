package dao.custom;

import entity.bo.TagItemBO;
import entity.po.TagItem;

import java.util.Map;

/**
 * Created by Zero on 2017/4/20.
 */
public interface TagItemCustomMapper {
    /**
     * 根据标签编号和视频编号查询标签明细
     * @param condition 条件（包含标签和视频编号）
     * @return 查询到的标签对象
     * @throws Exception
     */
    TagItem selectByTagAndVideo(Map condition) throws Exception;

    /**
     * 根据标签明细编号查询出扩展对象
     * @param tagItemId 标签明细编号
     * @return 扩展对象
     * @throws Exception
     */
    TagItemBO selectByPrimary(Integer tagItemId) throws Exception;

    /**
     * 插入一条标签明细并返回主键
     * @param tagItem 标签明细编号
     * @return 插入影响数
     * @throws Exception
     */
    Integer insertReturnId(TagItem tagItem) throws Exception;
}
