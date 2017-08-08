package service.impl;

import dao.custom.BigTypeCustomMapper;
import dao.simple.BigTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.bo.BigTypeBO;
import service.BigTypeService;

import java.util.List;

/**
 * Created by Zero on 2017/2/23.
 */
@Service
public class BigTypeServiceImpl extends BaseService implements BigTypeService {

    @Autowired
    private BigTypeMapper bigTypeMapper;

    @Autowired
    private BigTypeCustomMapper bigTypeCustomMapper;

    @Override
    public List<BigTypeBO> getVideoType() throws Exception {
        return bigTypeCustomMapper.getVideoType();
    }
}
