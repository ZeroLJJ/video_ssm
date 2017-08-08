package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AgreeService;

/**
 * Created by Zero on 2017/4/20.
 */
@Controller
public class AgreeController extends BaseController {
    @Autowired
    private AgreeService agreeService;

    @RequestMapping(value = "/agree")
    @ResponseBody
    public String agreeComment(String userId, String type, Integer targetId) throws Exception{
        String msg = agreeService.insertAgreeLog(userId, type, targetId);
        return msg;
    }

}
