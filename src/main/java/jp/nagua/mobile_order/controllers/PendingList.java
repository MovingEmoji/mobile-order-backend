package jp.nagua.mobile_order.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import jp.nagua.mobile_order.MobileOrderApplication;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.OrderItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PendingList {

    @GetMapping(value = "/pendings")
    @ResponseBody
    public String pendinglist() {
        ArrayList<JsonElement> pendings = new ArrayList<>();
        for(OrderContent order : MobileOrderApplication.orders) {
            if(order.getStatus().equals("pending")) {
                HashMap<Object, Object> content = new HashMap<>();
                content.put("id", order.getOrder_num());
                content.put("uuid", order.getOrder_id());;
                int total = 0;
                int cake = 0;
                int waffle = 0;
                int dango = 0;
                for(OrderItem item : order.getOrder_list()) {
                    total += item.getItem_cost();
                    switch (item.getItem_id()) {
                        case 1:
                            cake += item.getItem_count();
                            break;
                        case 2:
                            waffle += item.getItem_count();
                            break;
                        case 3:
                            dango += item.getItem_count();
                            break;
                        default:
                            break;
                    }
                }
                content.put("cake", cake);
                content.put("waffle", waffle);
                content.put("dango", dango);
                content.put("totalCost", total);
                pendings.add(new Gson().toJsonTree(content));
            }
        }
        return new Gson().toJson(pendings);
    }
}
