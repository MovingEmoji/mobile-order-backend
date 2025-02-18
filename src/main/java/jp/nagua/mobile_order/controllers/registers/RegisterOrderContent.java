package jp.nagua.mobile_order.controllers.registers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.handlers.ItemContentHandler;
import jp.nagua.mobile_order.handlers.OrderContentHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterOrderContent {
    @PostMapping(value = "/registerorder")
    @ResponseBody
    public String registerOrderContent(@RequestBody String string) {
        JsonArray jsonArray = JsonParser.parseString(string).getAsJsonArray();
        List<ItemContent> itemContents = new ArrayList<>();
        for(JsonElement element : jsonArray) {
            JsonObject json = element.getAsJsonObject();
            itemContents.add((ItemContent) ItemContentHandler.getInstance().getContentFromList(element.getAsJsonObject().get("id").getAsInt()));
        }
        OrderContentHandler.getInstance().addContentToList(new OrderContent(itemContents));
        return "success";
    }
}
