package jp.nagua.mobile_order.elements;

import jp.nagua.mobile_order.handlers.ItemContentHandler;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private String token;
    private ItemContentHandler itemContentHandler;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this. email = email;
        this.password = password;
        this.token = UUID.randomUUID().toString();
        this.itemContentHandler = new ItemContentHandler();
    }

    public User(int id, String name, String email, String password, String token, ItemContentHandler itemContentHandler) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.itemContentHandler = itemContentHandler;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ItemContentHandler getItemContentHandler() {
        return itemContentHandler;
    }

    public void setItemContentHandler(ItemContentHandler itemContentHandler) {
        this.itemContentHandler = itemContentHandler;
    }

    public void printUserData() {
        System.out.println("ID: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Token: " + this.getToken());
    }
}
