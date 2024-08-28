package jp.nagua.mobile_order.controllers;

import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class Confirm {

    @PostMapping(value = "/confirm")
    @ResponseBody
    public int Confirm(@RequestBody String str) {
        String uuid = str.replace("\"", "").replace("\"", "");
        for(OrderContent content : MobileOrderApplication.orders) {
            if(content.getOrder_id().toString().equals(uuid)) {
                return content.getOrder_num();
            }
        }
        return 0;
    }
}
