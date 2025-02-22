package jp.nagua.mobile_order.controllers.registers;

import com.google.gson.*;
import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.handlers.ItemContentHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//public class RegisterOrderContent {
//    @PostMapping(value = "/registerorder")
//    @ResponseBody
//    public String registerOrderContent(@RequestBody String string) {
//        JsonArray jsonArray = JsonParser.parseString(string).getAsJsonArray();
//        List<ItemContent> itemContents = new ArrayList<>();
//        for(JsonElement element : jsonArray) {
//            itemContents.add((ItemContent) ItemContentHandler.getInstance().getContentFromList(element.getAsJsonObject().get("id").getAsInt()));
//        }
//        OrderContent orderContent = new OrderContent(itemContents);
//        OrderContentHandler.getInstance().addContentToList(orderContent);
//        Map<Object, Object> jsonMap = new HashMap<>();
//        jsonMap.put("id", orderContent.getId());
//        jsonMap.put("uuid", orderContent.getUuid());
//        jsonMap.put("cost", orderContent.getTotalCost());
//        return new Gson().toJson(new Gson().toJsonTree(jsonMap));
//    }
//}
