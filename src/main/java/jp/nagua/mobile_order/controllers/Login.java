package jp.nagua.mobile_order.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jp.nagua.mobile_order.MobileOrderApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {
    @PostMapping(value = "login")
    @ResponseBody
    public String doLogin(@RequestBody String str) {
        JsonObject json = JsonParser.parseString(str).getAsJsonObject();
        if(json.get("username").getAsString().equals("e2staff")) {
            if(json.get("password").getAsString().equals("2024festival")) {
                return MobileOrderApplication.TOKEN;
            }
        }
        return "failed";
    }
}
