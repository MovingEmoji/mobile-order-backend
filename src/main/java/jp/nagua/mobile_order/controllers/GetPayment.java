package jp.nagua.mobile_order.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.PaymentData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetPayment {
    @PostMapping(value = "getpayment")
    @ResponseBody
    public String responsePayment(@RequestBody String str) {
        JsonObject json = JsonParser.parseString(str).getAsJsonObject();
        if(json.get("target").getAsString().equals("all")) {
            List<Object> jsonList = new ArrayList<>();
            for(PaymentData data : MobileOrderApplication.payments) {
                jsonList.add(data.getUUID());
            }
            return new Gson().toJson(jsonList);
        } else {
            return new Gson().toJson(getPayment(json.get("target").getAsString()));
        }
    }

    private JsonElement getPayment(String uuid) {
        PaymentData data = MobileOrderApplication.getPaymentData(uuid);
        Map<Object, Object> jsonMap = new HashMap<>();
        jsonMap.put("uuid", data.getUUID());
        jsonMap.put("status", data.getStatus());
        jsonMap.put("total", data.getTotal());
        jsonMap.put("deposit", data.getDeposit());
        jsonMap.put("change", data.getChange());
        jsonMap.put("url" , data.getURL());
        return new Gson().toJsonTree(jsonMap);
    }
}
