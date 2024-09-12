package jp.nagua.mobile_order.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.OrderItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetOrder {
    @PostMapping(value = "order")
    @ResponseBody
    public String responseOrder(@RequestBody String str) {
        JsonObject json = JsonParser.parseString(str).getAsJsonObject();
        if(json.get("target").getAsString().equals("all")) {
            List<Object> jsonList = new ArrayList<>();
            for(OrderContent order : MobileOrderApplication.orders) {
                jsonList.add(getOrder(order.getOrder_id().toString()));
            }
            return new Gson().toJson(jsonList);
        } else {
            String uuid = json.get("target").getAsString();
            return new Gson().toJson(getOrder(uuid));
        }
    }

    private JsonElement getOrder(String uuid) {
        OrderContent order = MobileOrderApplication.getOrderContent(uuid);
        Map<Object, Object> jsonMap = new HashMap<>();
        jsonMap.put("number", order.getOrder_num());
        jsonMap.put("uuid", order.getOrder_id());
        jsonMap.put("status", order.getStatus());
        jsonMap.put("total", order.getTotal());
        List<Object> items = new ArrayList<>();
        for(OrderItem item : order.getOrder_list()) {
            Map<Object, Object> itemMap = new HashMap<>();
            itemMap.put("number", item.getItem_id());
            itemMap.put("uuid", item.getItem_uuid());
            itemMap.put("name", item.getItem_name());
            itemMap.put("image", item.getItem_image());
            itemMap.put("count", item.getItem_count());
            itemMap.put("cost", item.getItem_cost());
            items.add(itemMap);
        }
        jsonMap.put("items", items);
        return new Gson().toJsonTree(jsonMap);
    }
}
