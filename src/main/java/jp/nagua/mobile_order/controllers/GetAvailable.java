package jp.nagua.mobile_order.controllers;

import jp.nagua.mobile_order.MobileOrderApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetAvailable {
    @PostMapping(value = "available")
    @ResponseBody
    public String getAvailable(@RequestBody String str) {
        String id = str.replace("\"", "").replace("\"", "");
        int count = 50;
        count -= MobileOrderApplication.countMap.get(Integer.valueOf(id));
        if(count < 0) {
            count = 0;
        }
        return Integer.toString(count);
    }
}
