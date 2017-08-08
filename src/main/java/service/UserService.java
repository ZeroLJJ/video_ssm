package service;

import entity.po.User;
import entity.vo.UserVO;

/**
 * Created by Zero on 2017/2/26.
 */
public interface UserService {
    /**
     * 获取用户头像
     * @param userId
     * @return
     * @throws Exception
     */
    String getUserImg(String userId) throws Exception;

    /**
     * 验证用户
     * @param userVO
     * @return
     * @throws Exception
     */
    User checkLogin(UserVO userVO) throws Exception;

    /**
     * 注册新用户
     * @param userVO
     * @return
     * @throws Exception
     */
    User saveNewUser(UserVO userVO) throws Exception;

    /**
     * 是否存在该用户名
     * @param userId
     * @return
     * @throws Exception
     */
    Boolean isExistUserId(String userId) throws Exception;

    /**
     * 更新用户资料
     * @param user
     * @return 更新后的user对象
     * @throws Exception
     */
    User updateUser(User user) throws Exception;

    /**
     * 修改用户密码
     * @param user 输入的用户账号和密码
     * @param newPwd  新密码
     * @return 修改的用户
     * @throws Exception
     */
    User modifyPassword(User user, String newPwd) throws Exception;

    /**
     * 通过用户编号获取用户信息
     * @param userId 用户编号
     * @return 用户信息
     * @throws Exception
     */
    User getUser(String userId) throws Exception;

}
