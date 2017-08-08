package service;

import java.util.Map;

/**
 * Created by Zero on 2017/3/20.
 */
public interface TagItemService {
    /**
     * 添加一条标签明细信息
     * @param userId 用户编号
     * @param videoId 视频编号
     * @param name 姓名
     * @return 提示信息，状态，标签对象
     * @throws Exception
     */
    Map add(String userId, Integer videoId, String name) throws Exception;

    /**
     * 删除一条标签明细信息
     * @param tagItemId 标签明细编号
     * @return 提示信息
     * @throws Exception
     */
    String delete(Integer tagItemId) throws Exception;
}
