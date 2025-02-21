package jp.nagua.mobile_order.controllers.getters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.handlers.OrderContentHandler;
import jp.nagua.mobile_order.handlers.UserHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetOrderContent {
    @PostMapping(value = "/orders")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String getContent(@RequestBody String string) {
        JsonObject json = JsonParser.parseString(string).getAsJsonObject();
        if(json.get("target").getAsString().equals("all")) {
            if(UserHandler.getInstance().checkToken(json.get("token").getAsString())) {
                List<Object> jsonList = new ArrayList<>();
                for(OrderContent content : (List<OrderContent>) OrderContentHandler.getInstance().getContents()) {
                    jsonList.add(convertToJson(content.getId()));
                }
                return new Gson().toJson(jsonList);
            }
            return "reject";
        } else {
            return new Gson().toJson(convertToJson(json.get("target").getAsInt()));
        }
    }

    public static JsonElement convertToJson(int id) {
        OrderContent orderContent = (OrderContent) OrderContentHandler.getInstance().getContentFromList(id);
        Map<Object, Object> jsonMap = new HashMap<>();
        List<Object> jsonList = new ArrayList<>();
        for(ItemContent content : orderContent.getItemContents()) {
            jsonList.add(GetItemContent.convertToJson(content.getId()));
        }
        jsonMap.put("id", orderContent.getId());
        jsonMap.put("uuid", orderContent.getUuid());
        jsonMap.put("cost", orderContent.getTotalCost());
        jsonMap.put("status", orderContent.getStatus());
        jsonMap.put("items", new Gson().toJson(jsonList));
        return new Gson().toJsonTree(jsonMap);
    }
}
