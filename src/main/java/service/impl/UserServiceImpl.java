package service.impl;

import dao.simple.UserMapper;
import exception.UserFrozenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.po.User;
import entity.vo.UserVO;
import service.UserService;
import util.DateUtil;

import java.util.Date;

/**
 * Created by Zero on 2017/2/26.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUserImg(String userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        String img;
        if(user != null) {
            img = user.getUimg();
        }else{      //查询不到用户
            img = "";
        }
        return img;
    }

    @Override
    public User checkLogin(UserVO userVO) throws Exception {
        //根据传入的id查找用户
        User user = userMapper.selectByPrimaryKey(userVO.getUser().getUid());
        if(user != null){
            //根据id查寻到用户，开始匹配密码
            if(user.getUpassword().equals(userVO.getUser().getUpassword())){
                if(user.getUstatus().equals("0")) {
                    User updateUser = new User();
                    updateUser.setUid(user.getUid());
                    //每日登录增加3硬币
                    if (user.getUlastdate() == null || DateUtil.isToday(user.getUlastdate()) == false) {
                        //如果最后登录时间日期不同于现在日期，则说明是每日登录，进行增加
                        updateUser.setUcoin(user.getUcoin() + 3);
                    }
                    //更新最后登录时间
                    updateUser.setUlastdate(new Date());
                    updateUser.setUlogin(user.getUlogin() + 1);
                    userMapper.updateByPrimaryKeySelective(updateUser);
                }else {
                    throw new UserFrozenException("用户已被冻结，登录失败");
                }
                return user;
            }else{
                return null;
            }
        }
        //根据id查询不到用户
        return null;
    }

    @Override
    public User saveNewUser(UserVO userVO) throws Exception {
        User user = userVO.getUser();
        user.setUimg("user/default.jpg");
        user.setUlevel("2");
        user.setUlogin(0);
        user.setUregdate(new Date());
        user.setUstatus("0");
        user.setUfan(0);
        user.setUcoin(0);
        user.setUfollow(0);
        user.setUworks(0);
        int flag = userMapper.insert(user);
        if(flag != 0){    //插入成功
            return user;
        }else{    //插入失败
            return null;
        }
    }

    @Override
    public Boolean isExistUserId(String userId) throws Exception {
        Boolean isExist = false;
        User user = userMapper.selectByPrimaryKey(userId);
        if(user != null){
            isExist = true;
        }
        return isExist;
    }

    @Override
    public User updateUser(User user) throws Exception {
        int num = userMapper.updateByPrimaryKeySelective(user);
        if(num > 0){  //更新数量大于0，表示更新成功
            user = userMapper.selectByPrimaryKey(user.getUid());
            return user;
        }
        return null;
    }

    @Override
    public User modifyPassword(User user, String newPwd) throws Exception {
        User oldUser = userMapper.selectByPrimaryKey(user.getUid());
        if(oldUser.getUpassword().equals(user.getUpassword())){  //将输入的密码与原密码匹配
            user.setUpassword(newPwd);   //相等则赋值并更新
            int num = userMapper.updateByPrimaryKeySelective(user);
            if(num > 0){  //更新数量大于0，表示更新成功
                user = userMapper.selectByPrimaryKey(user.getUid());
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUser(String userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }
}
