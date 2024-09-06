package jp.nagua.mobile_order.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
import java.util.UUID;

@Controller
public class CustomerUI {
    public static String customerUUID;
    @PostMapping(value = "/customer")
    @ResponseBody
    public String customer(@RequestBody String str) {
        for(OrderContent order : MobileOrderApplication.orders) {
            if (order.getOrder_id().toString().equals(customerUUID)) {
                HashMap<Object, Object> jsonMap = new HashMap<>();
                jsonMap.put("uuid", customerUUID);
                jsonMap.put("total", order.getTotal());
                jsonMap.put("status", order.getStatus());
                List<Object> items = new ArrayList<>();
                for(OrderItem item : order.getOrder_list()) {
                    HashMap<Object, Object> itemMap = new HashMap<>();
                    itemMap.put("id", item.getItem_id());
                    itemMap.put("uuid", item.getItem_uuid());
                    itemMap.put("name", item.getItem_name());
                    itemMap.put("image", item.getItem_image());
                    itemMap.put("count", item.getItem_count());
                    itemMap.put("cost", item.getItem_cost());
                    items.add(new Gson().toJsonTree(itemMap));
                }
                jsonMap.put("items", new Gson().toJsonTree(items));
                return new Gson().toJson(jsonMap);
            }
        }
        return "failed";
    }
}
