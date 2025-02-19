package jp.nagua.mobile_order.elements;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderContent implements Serializable {

    public static int count = 0;

    private int id;
    private String uuid;
    private List<ItemContent> itemContents;
    private String status;

    public OrderContent(List<ItemContent> itemContents) {
        this.id = count;
        this.uuid = UUID.randomUUID().toString();
        this.itemContents = itemContents;
        this.status = "preparation";
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        OrderContent.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ItemContent> getItemContents() {
        return itemContents;
    }

    public void setItemContents(List<ItemContent> itemContents) {
        this.itemContents = itemContents;
    }

    public void addItemContent(ItemContent content) {
        this.itemContents.add(content);
    }

    public void removeItemContent(ItemContent content) {
        this.itemContents.remove(content);
    }

    public int getTotalCost() {
        int cost = 0;
        for(ItemContent content : this.itemContents) {
            cost += content.getCost();
        }
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
