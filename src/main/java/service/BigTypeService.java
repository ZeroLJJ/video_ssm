package service;

import entity.bo.BigTypeBO;

import java.util.List;

/**
 * Created by Zero on 2017/2/23.
 */
public interface BigTypeService {
    /**
     * 获取视频类型，大类中包含所拥有的小类
     * @return BigTypeBO 视频大类扩展类
     * @throws Exception
     */
    List<BigTypeBO> getVideoType() throws Exception;

}
