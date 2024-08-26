package jp.nagua.mobile_order.elements;

import java.io.Serializable;
import java.util.UUID;

public class OrderItem implements Serializable {

    public static class Material {
        public static final int PANCAKES = 1;
        public static final int WAFFLE = 2;
        public static final int DANGO = 3;

        public static int getMaterialFromString(String item_name) {
            switch (item_name) {
                case "パンケーキ":
                    return 1;
                case "ワッフル":
                    return 2;
                case "だんご":
                    return 3;
                default:
                    throw new IllegalArgumentException("引数が不正です");
            }
        }
    }

    private int item_id;
    private UUID item_uuid;
    private String item_name;
    private String item_image;
    private int item_count;
    private int item_cost;

    public OrderItem(int item_id, String item_name, String item_image, int item_count, int item_cost) {
        this.item_id = item_id;
        this.item_uuid = UUID.randomUUID();
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_count = item_count;
        this.item_cost = item_cost;
    }

    public UUID getItem_uuid() {
        return item_uuid;
    }

    public void setItem_uuid(UUID item_uuid) {
        this.item_uuid = item_uuid;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public int getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(int item_cost) {
        this.item_cost = item_cost;
    }
}
