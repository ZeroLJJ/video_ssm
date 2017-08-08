package service.impl;

import dao.simple.SmallTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SmallTypeService;

/**
 * Created by Zero on 2017/2/24.
 */
@Service
public class SmallTypeServiceImpl extends BaseService implements SmallTypeService {

    @Autowired
    private SmallTypeMapper smallTypeMapper;


}
