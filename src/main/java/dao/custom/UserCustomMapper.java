package dao.custom;

import entity.bo.UserBO;

import java.util.List;
import java.util.Map;

/**
 * Created by Zero on 2017/4/24.
 */
public interface UserCustomMapper {

    /**
     * 获取符合条件的用户列表
     * @param condition 查询条件
     * @return 用户列表
     * @throws Exception
     */
    List<UserBO> getAllUser(Map condition) throws Exception;

    /**
     * 获取符合条件的所有用户包括管理员
     * @param condition 查询条件
     * @return 所有用户
     * @throws Exception
     */
    List<UserBO> getAllUserIncludeAdmin(Map condition) throws Exception;
}
