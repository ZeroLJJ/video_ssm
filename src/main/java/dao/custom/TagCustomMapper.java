package dao.custom;

import entity.bo.TagBO;
import entity.po.Tag;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/20.
 */
public interface TagCustomMapper {
    /**
     * 根据标签名查询标签
     * @param name 标签名
     * @return 标签对象
     * @throws Exception
     */
    Tag selectByName(String name) throws Exception;

    /**
     * 插入一条标签并返回主键
     * @param tag 标签编号
     * @return 插入影响数
     * @throws Exception
     */
    Integer insertReturnId(Tag tag) throws Exception;

    /**
     * 通过标签明细获取一条标签信息
     * @param tagItemId 标签明细编号
     * @return 标签对象
     * @throws Exception
     */
    Tag selectByTagItem(Integer tagItemId) throws Exception;

    /**
     * 查询符合条件的所有标签
     * @param condition 查询条件
     * @return 标签列表
     * @throws Exception
     */
    List<TagBO> getAllTag(Map condition) throws Exception;
}
