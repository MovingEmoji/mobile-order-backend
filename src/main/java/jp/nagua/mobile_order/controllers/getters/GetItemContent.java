package jp.nagua.mobile_order.controllers.getters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.handlers.ItemContentHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetItemContent {
    @PostMapping(value = "/items")
    @ResponseBody
    public String getContent(@RequestBody String string) {
        JsonObject json = JsonParser.parseString(string).getAsJsonObject();
        if(json.get("target").getAsString().equals("all")) {
            List<Object> jsonList = new ArrayList<>();
            for(ItemContent content : (List<ItemContent>) ItemContentHandler.getInstance().getContents()) {
                jsonList.add(convertToJson(content.getId()));
            }
            return new Gson().toJson(jsonList);
        } else {
            return new Gson().toJson(convertToJson(json.get("target").getAsInt()));
        }
    }

    public static JsonElement convertToJson(int id) {
        ItemContent content = (ItemContent) ItemContentHandler.getInstance().getContentFromList(id);
        Map<Object, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", content.getId());
        jsonMap.put("name", content.getName());
        jsonMap.put("text", content.getText());
        jsonMap.put("stock", content.getStock());
        jsonMap.put("cost", content.getCost());
        jsonMap.put("image", content.getImage());
        return new Gson().toJsonTree(jsonMap);
    }
}
