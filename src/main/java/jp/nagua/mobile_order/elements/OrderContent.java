package jp.nagua.mobile_order.elements;

import jp.nagua.mobile_order.MobileOrderApplication;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderContent implements Serializable {

    private int order_num;
    private UUID order_id;
    private List<OrderItem> order_list;
    private String status;
    private int total = 0;

    public OrderContent(List<OrderItem> order_list) {
        this.order_id = UUID.randomUUID();
        this.order_list = order_list;
        this.order_num = MobileOrderApplication.orders.size() + 1;
        this.status = "pending";
        for(OrderItem item : order_list) {
            total += item.getItem_cost();
        }
        MobileOrderApplication.orders.add((OrderContent) this);
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

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
