package top.docstorm.documentstormmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/9
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "lala";
    }

}
