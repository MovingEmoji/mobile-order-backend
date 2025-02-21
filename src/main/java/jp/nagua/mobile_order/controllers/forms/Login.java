package jp.nagua.mobile_order.controllers.forms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody String string) {
        return "";
    }
}
