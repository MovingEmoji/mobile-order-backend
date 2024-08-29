package jp.nagua.mobile_order.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class GetStatus {

    @PostMapping(value = "/status")
    @ResponseBody
    public String getStatus(@RequestBody String str) {
        String uuid = str.replace("\"", "").replace("\"", "");
        for(OrderContent order : MobileOrderApplication.orders) {
            if (order.getOrder_id().toString().equals(uuid)) {
                return order.getStatus();
            }
        }
        return "failed";
    }
}
