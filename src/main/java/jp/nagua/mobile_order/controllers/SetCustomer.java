package jp.nagua.mobile_order.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.PaymentData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class SetCustomer {
    @PostMapping(value = "/setcustomer")
    @ResponseBody
    public String setCustomer(@RequestBody String str) {
        JsonObject json = JsonParser.parseString(str).getAsJsonObject();
        if(json.get("token").getAsString().equals(MobileOrderApplication.TOKEN)) {
            if (json.get("uuid").getAsString().equals("reset")) {
                CustomerUI.customerUUID = "";
                return "success";
            } else {
                for (OrderContent order : MobileOrderApplication.orders) {
                    if (order.getOrder_id().toString().equals(json.get("uuid").getAsString())) {
                        CustomerUI.customerUUID = json.get("uuid").getAsString();
                        PaymentData data = new PaymentData(order.getTotal(), order.getOrder_id());
                        return "success";
                    }
                }
                return "failed";
            }
        }
        return "failed";
    }
}
