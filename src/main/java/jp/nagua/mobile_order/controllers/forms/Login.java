package jp.nagua.mobile_order.controllers.forms;

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
public class Login {
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody String string) {
        JsonObject json = JsonParser.parseString(string).getAsJsonObject();
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();
        if(MobileOrderApplication.userHandler.getContentWithEmail(email) != null) {
            User user = (User) MobileOrderApplication.userHandler.getContentWithEmail(email);
            if(user.getPassword().equals(password)) {
                Map<Object, Object> jsonMap = new HashMap<>();
                jsonMap.put("id", user.getId());
                jsonMap.put("token", user.getToken());
                return new Gson().toJson(new Gson().toJsonTree(jsonMap));
            }
            return "reject";
        }
        return "reject";
    }
}
