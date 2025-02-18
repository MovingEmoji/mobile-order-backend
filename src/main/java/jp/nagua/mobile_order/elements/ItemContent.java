package jp.nagua.mobile_order.elements;

import java.io.Serializable;

public class ItemContent implements Serializable {

    private int id;
    private String name;
    private String text;
    private int stock;
    private int cost;
    private String image;

    public ItemContent(int id, String name, String text, int stock, int cost, String image) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.stock = stock;
        this.cost = cost;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
