package jp.nagua.mobile_order.elements;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderContent implements Serializable {

    private UUID order_id;
    private List<OrderItem> order_list;

    public OrderContent(List<OrderItem> order_list) {
        this.order_id = UUID.randomUUID();
        this.order_list = order_list;
    }

    public UUID getOrder_id() {
        return order_id;
    }

    public void setOrder_id(UUID order_id) {
        this.order_id = order_id;
    }

    public List<OrderItem> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderItem> order_list) {
        this.order_list = order_list;
    }
}
