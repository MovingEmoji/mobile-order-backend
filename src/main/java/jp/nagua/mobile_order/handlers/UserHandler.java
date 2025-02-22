package jp.nagua.mobile_order.handlers;

import jp.nagua.mobile_order.elements.User;
import jp.nagua.mobile_order.interfaces.ContentHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class UserHandler implements ContentHandler, Serializable {

    private ArrayList<User> users;

    public UserHandler() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addContent(Object object) {
        this.users.add((User) object);
    }

    @Override
    public void removeContent(Object object) {
        this.users.remove((User) object);
    }

    @Override
    public Object getContent(String name) {
        for(User user : this.users) {
            if(user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Object getContent(int id) {
        for(User user : this.users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Object getContentWithEmail(String email) {
        for(User user : this.users) {
            if(user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Object getContents() {
        return this.users.clone();
    }

    @Override
    public boolean contains(Object object) {
        if(this.users.contains((User) object)) {
            return true;
        }
        return false;
    }

    public int getCounts() {
        return users.size();
    }
}
