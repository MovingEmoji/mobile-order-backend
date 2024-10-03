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
public class UpdateStatus {

    @PostMapping(value = "/update")
    @ResponseBody
    public String update(@RequestBody String str) {
        JsonObject json = JsonParser.parseString(str).getAsJsonObject();
        if(json.get("token").getAsString().equals(MobileOrderApplication.TOKEN)) {
            UUID uuid = UUID.fromString(json.get("uuid").getAsString());
            String status =json.get("status").getAsString();
            for(OrderContent order : MobileOrderApplication.orders) {
                if(order.getOrder_id().toString().equals(uuid.toString())) {
                    order.setStatus(status);
                    return "success";
                }
            }
            return "failed";
        }
        return "failed";
    }
}
