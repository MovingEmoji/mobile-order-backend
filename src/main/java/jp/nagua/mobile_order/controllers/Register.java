package jp.nagua.mobile_order.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.OrderItem;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Register {

    @PostMapping(value="/register")
    @ResponseBody
    public String Register(@RequestBody String str) throws ParseException {
        JsonArray json_array = JsonParser.parseString(str).getAsJsonArray();
        List<OrderItem> items = new ArrayList<>();
        for(JsonElement element : json_array) {
            JsonObject json = element.getAsJsonObject();
            String name = json.get("name").getAsString();
            int id = OrderItem.Material.getMaterialFromString(name);
            String image = json.get("image").getAsString();
            int count = json.get("count").getAsInt();
            int cost = json.get("cost").getAsInt();
            items.add(new OrderItem(id, name, image, count, cost));
        }
        OrderContent order = new OrderContent(items);
        return order.getOrder_id().toString();
    }
}
