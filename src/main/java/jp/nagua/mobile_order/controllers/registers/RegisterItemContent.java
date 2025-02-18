package jp.nagua.mobile_order.controllers.registers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.handlers.ItemContentHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterItemContent {
    @PostMapping(value = "/registeritem")
    @ResponseBody
    public String registerItemContent(@RequestBody String string) {
        JsonObject json = JsonParser.parseString(string).getAsJsonObject();
        if(json.get("token").getAsString().equals(MobileOrderApplication.TOKEN)) {
            int id = json.get("id").getAsInt();
            String name = json.get("name").getAsString();
            String text = json.get("text").getAsString();
            int stock = json.get("stock").getAsInt();
            int cost = json.get("cost").getAsInt();
            String image = json.get("image").getAsString();
            ItemContentHandler.addItemContentToList(new ItemContent(id, name, text, stock, cost, image));
            return "success";
        }
        return "reject";
    }
}
