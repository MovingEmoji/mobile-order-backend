package jp.nagua.mobile_order.controllers.registers;

import com.google.gson.JsonArray;
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
            itemContents.add(ItemContentHandler.getItemContentFromList(element.getAsJsonObject().get("id").getAsInt()));
        }

        return "";
    }
}
