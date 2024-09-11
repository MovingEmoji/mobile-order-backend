package jp.nagua.mobile_order.elements;

import jp.nagua.mobile_order.MobileOrderApplication;

import java.util.UUID;

public class PaymentData {

    private int total;
    private int deposit;
    private int change;
    private String status;
    private final UUID uuid;

    public PaymentData(int total, UUID uuid) {
        this.total = total;
        this.uuid = uuid;
        this.deposit = -1;
        this.change = -1;
        this.status = "pending";
        MobileOrderApplication.payments.add((PaymentData) this);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }
}
