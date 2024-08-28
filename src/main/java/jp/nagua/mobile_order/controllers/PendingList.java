package jp.nagua.mobile_order.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PendingList {

    @GetMapping(value = "/pendings")
    @ResponseBody
    public String pendinglist() {
        ArrayList<JsonElement> pendings = new ArrayList<>();
        for(OrderContent order : MobileOrderApplication.orders) {
            if(order.getStatus().equals("pending")) {
                HashMap<Object, Object> content = new HashMap<>();
                content.put("id", order.getOrder_num());
                content.put("uuid", order.getOrder_id());;
                pendings.add(new Gson().toJsonTree(content));
            }
        }
        return new Gson().toJson(pendings);
    }
}
