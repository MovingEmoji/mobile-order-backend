package jp.nagua.mobile_order.controllers.registers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.User;
import jp.nagua.mobile_order.handlers.UserHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterUser {
    @PostMapping(value = "/registeruser")
    @ResponseBody
    public String registerUser(@RequestBody String string) {
        JsonObject json = JsonParser.parseString(string).getAsJsonObject();
        int id = MobileOrderApplication.userHandler.getCounts();
        String name = json.get("name").getAsString();
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();
        if(MobileOrderApplication.userHandler.getContentWithEmail(email) != null) {
            return "reject";
        }
        User user = new User(id, name, email, password);
        MobileOrderApplication.userHandler.addContent(user);
        Map<Object, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", user.getId());
        jsonMap.put("token", user.getToken());
        user.printUserData();
        return new Gson().toJson(new Gson().toJsonTree(jsonMap));

    }
}
