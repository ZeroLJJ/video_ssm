package service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Zero on 2017/4/20.
 */
@Service
public abstract class BaseService {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

}
