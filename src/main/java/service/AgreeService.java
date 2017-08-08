package service;

/**
 * Created by Zero on 2017/4/20.
 */
public interface AgreeService {
    /**
     * 插入一条点赞记录信息
     * @param userId  点赞用户编号
     * @param type  点赞类型名
     * @param targetId  点赞目标的编号
     * @return 提示信息
     * @throws Exception
     */
    String insertAgreeLog(String userId, String type, Integer targetId) throws Exception;
}
