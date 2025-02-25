package jp.nagua.mobile_order.controllers.getters;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GetUserData {
    @PostMapping(value = "/userdata")
    @ResponseBody
    public String getUserData(@RequestBody String string) {
        JsonObject json = JsonParser.parseString(string).getAsJsonObject();
        String token = json.get("token").getAsString();
        if((User) MobileOrderApplication.userHandler.getContentWithToken(token) != null) {
            User user = (User) MobileOrderApplication.userHandler.getContentWithToken(token);
            Map<Object, Object> jsonMap = new HashMap<>();
            jsonMap.put("id", user.getId());
            jsonMap.put("name", user.getName());
            jsonMap.put("email", user.getEmail());
            jsonMap.put("password", user.getPassword());
            return new Gson().toJson(new Gson().toJsonTree(jsonMap));
        }
        return "reject";
    }
}
